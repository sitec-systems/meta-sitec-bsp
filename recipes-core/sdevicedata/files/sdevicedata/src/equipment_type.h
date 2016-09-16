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

#ifndef __SDEVICEDATA_EQUIP_TYPE_H__
#define __SDEVICEDATA_EQUIP_TYPE_H__

#include <string>
#include <vector>

#include "equipment.h"

namespace sdevicedata {
	/**
	 * Class holds all availible equipments for a specific equipment.
	 */
	class EquipmentType {
	public:
		/**
		 * Constructor.
		 *
		 * @param equips std::vector of Equipments which are included in
		 * this equipment type
		 * @param name name of the Equipment type.
		 * @param vendor Vendor of the Equipment type.
		 */
		EquipmentType(const std::vector<Equipment> equips,
					  const std::string name,
					  const std::string vendor);

		/**
		 * Gets the included equipments
		 *
		 * @return std::vector with instances of Equipment class
		 */
		std::vector<Equipment> getEquipments(void);

		/**
		 * Gets the name of the Equipment Type
		 *
		 * @return std::string with the name of the Equipment
		 */
		std::string getName(void);

		/**
		 * Gets the vendor of the Equipment Type.
		 *
		 * @return std::string with the vendor of the Equipment Type
		 */
		std::string getVendor(void);
	
	private:
		std::vector<Equipment> equips;
		std::string name;
		std::string vendor;
	};
} // namespace sdevicedata
#endif // __SDEVICEDATA_EQUIP_TYPE_H__
