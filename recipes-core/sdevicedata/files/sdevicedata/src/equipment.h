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

#ifndef __SDEVICEDATA_EQUIP_H__
#define __SDEVICEDATA_EQUIP_H__

#include <string>

namespace sdevicedata {
	/**
	 * Class holds all availible information for the relating equipment
	 */
	class Equipment {
	public:
		/**
		 * Constructor.
		 *
		 * @param interface The interface which will be used from the device
		 */
		Equipment(const std::string interface);

		/**
		 * Gets the interface of the Equipment
		 *
		 * @return string with the interface name of the equipment
		 */
		std::string getInterface(void);
	
	private:
		std::string interface;
	};
} // namespace sdevicedata
#endif // __SDEVICEDATA_EQUIP_H__
