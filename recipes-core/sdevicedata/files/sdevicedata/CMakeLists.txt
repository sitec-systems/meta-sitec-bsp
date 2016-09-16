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

cmake_minimum_required(VERSION 2.8)
project(sdevicedata CXX)

option(BUILD_TEST "Build application which reads the complete structure and prints it out" OFF)
option(BUILD_SHARED "Build shared library" OFF)

# Project Search Paths
set(CMAKE_MODULE_PATH "${CMAKE_CURRENT_SOURCE_DIR}/cmake")

# Compile settings
include(EnableStdCXX11)
ENABLE_STDCXX11()

include(FindBoost)
find_package(Boost 1.55 REQUIRED COMPONENTS system filesystem)
include_directories(SYSTEM ${Boost_INCLUDE_DIRS})
link_directories(SYSTEM ${Boost_LIBRARY_DIRS})

add_subdirectory(src)
