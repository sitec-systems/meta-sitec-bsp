DESCRIPTION = "Commandline tool for parsing the sitec device tree"
MAINTAINER = "Robert Lehmann <robert.lehmann@sitec-systems.de>"

SRC_URI = "file://${PN}-${PV}.zip"
S = "${WORKDIR}/sdevicedata"

PR = "r1"

LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://${S}/LICENSE.txt;md5=4fbd65380cdd255951079008b364516c"
	
DEPENDS = "boost"

inherit cmake
