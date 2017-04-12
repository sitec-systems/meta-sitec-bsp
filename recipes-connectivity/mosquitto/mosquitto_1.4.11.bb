SUMMARY = "MQTT 3.1 compliant library"
HOMEPAGE = "https://mosquitto.org"
MAINTAINER = "Robert Lehmann <robert.lehmann@sitec-systems.de>"

LICENSE = "EPL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=62ddc846179e908dc0c8efec4a42ef20"

SRC_URI = "https://mosquitto.org/files/source/mosquitto-${PV}.tar.gz"
SRC_URI[md5sum] = "c217dea4bdc7573a2eaea8387c18a19e"
SRC_URI[sha256sum] = "ca47533bbc1b7c5e15d6e5d96d3efc59677f2515b6692263c34b7c48f33280c5"

DEPENDS = "openssl"
RDEPENDS_${PN} = "util-linux"

inherit cmake

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = " -DCMAKE_SKIP_RPATH=ON "

