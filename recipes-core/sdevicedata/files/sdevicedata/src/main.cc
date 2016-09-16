/*
 * Copyright (C) 2016  sitec systems GmbH
 *
 * This library is free software; you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation; either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this library; if
 * not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110, USA
 */

#include <iostream>

#include "device_information.h"
#include "device.h"
#include "equipment_category.h"

using std::cout;
using std::endl;
using std::string;

using namespace sdevicedata;

int main(int argc, char **argv) {
	auto di = DeviceInformation();
	di.parse();
	auto dev = di.getDevice().at(0);

	cout << "Device Unit_Name" << dev.getUnitName() << endl;
	cout << "Device Equipment Categories" << endl;
	for (auto ec : dev.getEquipmentCategories()) {
		cout << ec.getName() << endl;
		auto type = ec.getEquipTypes();
		for (auto et : type) {
			cout << "Type Name: " << et.getName() << endl;
			cout << "Type Vendor: " << et.getVendor() << endl;
			for (auto eq : et.getEquipments()) {
				cout << "Equipment interface: " << eq.getInterface() << endl;
			}
		}
	}
}
