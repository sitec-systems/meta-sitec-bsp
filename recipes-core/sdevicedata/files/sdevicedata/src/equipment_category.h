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

#ifndef __SDEVICEDATA_EQUIP_CAT_H__
#define __SDEVICEDATA_EQUIP_CAT_H__

#include "equipment_type.h"

#include <string>
#include <vector>

namespace sdevicedata {
	/**
	 * Class holds all availible equipment types for a specific
	 * equipment category
	 */
	class EquipmentCategory {
	public:
		/**
		 * Constructor.
		 *
		 * @param equipTypes std::vector of Equipment Types which are included
		 * in this category
		 * @param name name of the Equipment Category
		 */
		EquipmentCategory(const std::vector<EquipmentType> equip_types, const std::string name);

		/**
		 * Gets a std::vector with the instances of Equipment Types
		 *
		 * @return std::vector of EquipmentTypes
		 */
		std::vector<EquipmentType> getEquipTypes(void);

		/**
		 * Gets the name of the Equipment Category
		 *
		 * @return std::name of EquipmentCategory
		 */
		std::string getName(void);

	private:
		std::vector<EquipmentType> equip_types;
		std::string name;
	};
} // namespace sdevicedata
#endif // __SDEVICEDATA_EQUIP_CAT_H__
