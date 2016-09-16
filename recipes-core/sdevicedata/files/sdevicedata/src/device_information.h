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

#ifndef __SDEVICEDATA_DEVICE_INFORMATION_H__
#define __SDEVICEDATA_DEVICE_INFORMATION_H__

#include <vector>
#include <string>
#include <regex>

#include <boost/filesystem.hpp>

#include "device.h"
#include "equipment_category.h"
#include "equipment_type.h"

namespace sdevicedata {
	/**
	 * Data Object which contains Device instances an other information about the device
	 */
	class DeviceInformation {
	public:
		/**
		 * Constructor of DeviceInformation object.
		 */
		DeviceInformation() {}

		/**
		 * Gets a vector with instance of the Device object
		 *
		 * Returns a std::vector of Device instances
		 */
		std::vector<Device> getDevice(void) const;

		/**
		 * Gets the unit name
		 *
		 * Returns std::string with the unit name
		 */
		std::string getUnitName(void) const;

		/**
		 * Gets the product code
		 *
		 * Retruns std::string with the product code
		 */
		std::string getPc(void) const;

		bool isParsed(void) const;

		void parse(void);
	private:
		std::string readFile(const boost::filesystem::path& p) const;
		std::string readAttr(const boost::filesystem::path& p) const;
		bool isAttr(const boost::filesystem::path& p, const std::regex& attr_regex) const;
		std::string checkForCategory(const boost::filesystem::path& p) const;
		Device parseDevice(const boost::filesystem::path& p) const;
		std::vector<EquipmentCategory> parseEquipmentCategory(const boost::filesystem::path& p) const;
		std::vector<EquipmentType> parseEquipmentType(const boost::filesystem::path& p,
													  const std::string& category_name) const;
		Equipment parseEquipment(const boost::filesystem::path& p) const;
	
		std::vector<Device> device;
		std::string unit_name = "";
		std::string pc = "";
		bool parsed = false;
	};
} // namespace sdevicedata
#endif // __SDEVICEDATA_DEVICE_INFORMATION_H__
