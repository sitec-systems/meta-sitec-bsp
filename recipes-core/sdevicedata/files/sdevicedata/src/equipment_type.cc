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

#include "equipment_type.h"

#include <string>
#include <vector>

#include "equipment.h"

using std::vector;
using std::string;

namespace sdevicedata {
	EquipmentType::EquipmentType(const vector<Equipment> equips,
								 const string name,
								 const string vendor)
		: equips(equips),
		  name(name),
		  vendor(vendor) {}

	vector<Equipment> EquipmentType::getEquipments() { return this->equips; }
	string EquipmentType::getName() { return this->name; }
	string EquipmentType::getVendor() { return this->vendor; }
} // namespace sdevicedata
