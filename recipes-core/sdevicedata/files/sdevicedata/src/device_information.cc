/*
 * Copyright (C) 2016  sitec systems GmbH
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110, USA
 */

#include "device_information.h"

#include <vector>
#include <string>
#include <regex>
#include <fstream>
#include <iostream>

#include "device.h"
#include "equipment_category.h"
#include "equipment_type.h"
#include "equipment.h"

using namespace std;
using namespace boost::filesystem;

namespace sdevicedata {
	namespace {
		const string kDtPath = "/proc/device-tree/sitec/v1/";

		const regex kUnitNameRegex = std::regex("unit_name");
		const regex kPcRegex = std::regex("pc");
		const regex kDeviceRegex = std::regex("device@\\d");
		const regex kHwRel = std::regex("hw_rel");
		const regex kEquipCat = std::regex("equipment_category");
		const regex kModName = std::regex("mod_name");
		const regex kName = std::regex("name");
		const regex kVendor = std::regex("vendor");
		const regex kEquipment = std::regex("equipment@\\d");
		const regex kInterface = std::regex("interface");

		const vector<string> kCategories = {"can_controller",
													  "gnss_receiver",
													  "gsm_module",
													  "led"};
	} // namespace

	// public methodes
	vector<Device> DeviceInformation::getDevice() const { return this->device; }

	string DeviceInformation::getUnitName() const { return this->unit_name; }

	string DeviceInformation::getPc() const { return this->pc; }

	bool DeviceInformation::isParsed() const { return this->parsed; }

	void DeviceInformation::parse()
	{
		string unit_name = "";
		string pc = "";
		vector<Device> devices;
		path p(kDtPath);

		for (auto de : directory_iterator(p)) {
			auto cur_path = de.path();

			if (isAttr(cur_path, kUnitNameRegex)) {
				unit_name = readAttr(cur_path);
			} else if (isAttr(cur_path, kPcRegex)) {
				pc = readAttr(cur_path);
			} else {
				if (isAttr(cur_path, kDeviceRegex)) {
					devices.push_back(parseDevice(de));
				}
			}
		}

		this->device = devices;
		this->unit_name = unit_name;
		this->pc = pc;
		this->parsed = true;
	}

	// private methodes
	bool DeviceInformation::isAttr(const path& p, const regex& attr_regex) const
	{
		if (regex_search(p.string(), attr_regex)) {
			return true;
		}

		return false;
	}

	string DeviceInformation::readFile(const path& p) const
	{
		string val;
		std::ifstream ifs;

		ifs.open(p.string());
		ifs >> val;
		ifs.close();

		return val;
	}

	string DeviceInformation::readAttr(const path& p) const { return readFile(p); }

	Device DeviceInformation::parseDevice(const path& p) const
	{
		string unit_name = "";
		string pc = "";
		string hw_rel = "";
		vector<EquipmentCategory> categories;

		for (auto de : directory_iterator(p)) {
			auto cur_path = de.path();
			if (isAttr(cur_path, kUnitNameRegex)) {
				unit_name = readAttr(cur_path);
			} else if (isAttr(cur_path, kPcRegex)) {
				pc = readAttr(cur_path);
			} else if (isAttr(cur_path, kHwRel)) {
				hw_rel = readAttr(cur_path);
			} else if (isAttr(cur_path, kEquipCat)) {
				categories = parseEquipmentCategory(cur_path);
			}
		}
		return Device(unit_name, hw_rel, pc, categories);
	}

	string DeviceInformation::checkForCategory(const path& p) const
	{
		string category = "";

		if (is_directory(p)) {
			for (auto c : kCategories) {
				const auto r = regex(c);
				if (isAttr(p, r)) {
					category = c;
					break;
				}
			}
		}

		return category;
	}

	vector<EquipmentCategory> DeviceInformation::parseEquipmentCategory(
		const path& p) const
	{
		vector<EquipmentCategory> categories;

		for (auto de : directory_iterator(p)) {
			auto cur_path = de.path();
			auto c = checkForCategory(cur_path);
			if (!c.empty()) {
				categories.push_back(EquipmentCategory(parseEquipmentType(cur_path, c), c));
			}
		}
		return categories;
	}

	vector<EquipmentType> DeviceInformation::parseEquipmentType(
		const path& p, const string& category_name) const
	{
		auto cr = regex(category_name + "@\\d");
		vector<EquipmentType> equipment_types;

		// This level contains equipment type directories like the
		// following example shows:
		// can_controller@1
		// can_controller@2
		for (auto de : directory_iterator(p)) {
			auto cur_path = de.path();
			if (isAttr(cur_path, cr)) {
				string mod_name = "";
				string vendor = "";
				vector<Equipment> equipments;

				// This level contains the attributes of the equipment type.
				for (auto type_entry : directory_iterator(cur_path)) {
					auto type_path = type_entry.path();
					if (isAttr(type_path, kModName)) {
						mod_name = readAttr(type_path);
					} else if (isAttr(type_path, kVendor)) {
						vendor = readAttr(type_path);
					} else if (isAttr(type_path, kEquipment)) {
						equipments.push_back(parseEquipment(type_path));
					}
				}
				equipment_types.push_back(EquipmentType(equipments, mod_name, vendor));
			}
		}

		return equipment_types;
	}

	Equipment DeviceInformation::parseEquipment(const path& p) const
	{
		string interface = "";

		for (auto de : directory_iterator(p)) {
			auto cur_path = de.path();
			if (isAttr(cur_path, kInterface)) {
				interface = readAttr(cur_path);
			}
		}

		return Equipment(interface);
	}
} // namespace sdevicedata
