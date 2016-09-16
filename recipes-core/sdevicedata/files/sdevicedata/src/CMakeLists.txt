# Copyright (C) 2016  sitec systems GmbH
#
# This library is free software; you can redistribute it and/or modify it under the terms of the GNU
# Lesser General Public License as published by the Free Software Foundation; either version 2.1 of the
# License, or (at your option) any later version.
#
# This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
# the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
# General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public License along with this library; if
# not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110, USA

set(libsdevicedata_SRC
  device.cc
  device_information.cc
  equipment_category.cc
  equipment_type.cc
  equipment.cc
  )

if (BUILD_TEST)
  add_executable(sdevicedata_test main.cc)
endif()

if (BUILD_SHARED)
  add_library(sdevicedata SHARED ${libsdevicedata_SRC})
else()
  add_library(sdevicedata STATIC ${libsdevicedata_SRC})
endif()

target_link_libraries(sdevicedata ${Boost_LIBRARIES})

if (BUILD_TEST)
  target_link_libraries(sdevicedata_test sdevicedata)
endif()

if (BUILD_TEST)
  install(TARGETS sdevicedata_test DESTINATION bin)
endif()

install(TARGETS sdevicedata DESTINATION lib)
install(FILES ${CMAKE_CURRENT_SOURCE_DIR}/device.h
  DESTINATION include/sdevicedata)
install(FILES ${CMAKE_CURRENT_SOURCE_DIR}/device_information.h
  DESTINATION include/sdevicedata)
install(FILES ${CMAKE_CURRENT_SOURCE_DIR}/equipment_category.h
  DESTINATION include/sdevicedata)
install(FILES ${CMAKE_CURRENT_SOURCE_DIR}/equipment_type.h
  DESTINATION include/sdevicedata)
install(FILES ${CMAKE_CURRENT_SOURCE_DIR}/equipment.h
  DESTINATION include/sdevicedata)


