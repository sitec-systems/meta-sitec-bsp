SUMMARY = "CANOpen Framework"
HOMEPAGE = "https://github.com/KITmedical/kacanopen"
MAINTAINER = "Robert Lehmann <robert.lehmann@sitec-systems.de>"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING.BSD.txt;md5=e571b2183bb2015b7df6f35de4fcc513"

SRC_URI = "git://github.com/KITmedical/kacanopen.git"
SRC_URI += "file://0001-CMakeLists-Remove-Error-message-for-poky-compiler.patch"
SRCREV = "5e628d0c71ab84cd672186ef550f9c65239c34ff"

S = "${WORKDIR}/git"

INSANE_SKIP_${PN}-dev += "dev-elf"

DEPENDS ="boost"

inherit cmake

EXTRA_OECMAKE = " -DDRIVER=socket -DNO_ROS=ON "

FILES_${PN} = "/usr/lib/*"
FILES_${PN} += "/usr/share/*"

FILES_${PN}-dev = "/usr/include/*"
