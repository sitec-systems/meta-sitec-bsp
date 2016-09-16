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

#include "device.h"

#include <string>
#include <vector>

#include "equipment_category.h"

using std::string;
using std::vector;

namespace sdevicedata {
	Device::Device(const string unit_name, const string hw_el, const string pc,
				   const vector<EquipmentCategory> equip_cats)
		: unit_name(unit_name),
		  hw_rel(hw_rel),
		  pc(pc),
		  equip_cats(equip_cats) {}

	string Device::getUnitName() { return this->unit_name; }
	string Device::getHwRel() { return this->hw_rel; }
	string Device::getPc() { return this->pc; }
	vector<EquipmentCategory> Device::getEquipmentCategories() { return this->equip_cats; }
} // namespace sdt
