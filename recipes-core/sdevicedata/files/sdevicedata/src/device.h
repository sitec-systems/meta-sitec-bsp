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

#ifndef __SDEVICEDATE_DEVICE_H__
#define __SDEVICEDATE_DEVICE_H__

#include "equipment_category.h"

#include <string>
#include <vector>

namespace sdevicedata {
	/**
	 * Holds all availible information about a specific device
	 */
	class Device {
	public:
		/**
		 * Constructor.
		 *
		 * @unitName std::string with the unit name of the device
		 * @hwRel std::string with the hardware release number of the device
		 * @pc std::string with the product code of the device
		 * @equip_cats std::vector with the availbile equipment categories
		 */
		Device(const std::string unit_name, const std::string hw_rel, const std::string pc,
			   const std::vector<EquipmentCategory> equip_cats);

		/**
		 * Gets the unit name of the device
		 *
		 * Return a std::string with the unit name of the device
		 */
		std::string getUnitName(void);

		/**
		 * Gets the hardware release number of the device
		 *
		 * Returns a std::string with the hardware release number of the device
		 */
		std::string getHwRel(void);

		/**
		 * Gets the product code of the device
		 *
		 * Returns a std::string with the product code of the device
		 */
		std::string getPc(void);

		/**
		 * Gets the availible equipment categories
		 *
		 * Returns a std::vector with all equipment categories which were
		 * found
		 */
		std::vector<EquipmentCategory> getEquipmentCategories(void);
		
	private:
		std::string unit_name;
		std::string hw_rel;
		std::string pc;
		std::vector<EquipmentCategory> equip_cats;
	};
} // namespace sdevicedata
#endif // __SDEVICEDATA_DEVICE_H__
