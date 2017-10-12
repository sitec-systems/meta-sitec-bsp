SUMMARY = "Set of i2c tools for linux - Python module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://smbusmodule.c;startline=1;endline=17;md5=fa24df321a520ff8e10f203425ab9fa8"

SRC_URI = "https://github.com/ev3dev/i2c-tools/archive/ev3dev-stretch/${PV}-3ev3dev1.tar.gz"

SRC_URI[md5sum] = "c7e97d8ba1badcbfc00018600e89d33f"
SRC_URI[sha256sum] = "9ca9593e54058a173012e5cd3ec34ed6124dd7430f57ba3f941bc356b9a7e579"

DEPENDS = "i2c-tools"

inherit distutils3

S = "${WORKDIR}/i2c-tools-ev3dev-stretch-${PV}-3ev3dev1/py-smbus/"

do_configure_prepend() {
    # Adjust for OE header rename
    sed -i s:linux/i2c-dev.h:linux/i2c-dev-user.h: Module.mk
    sed -i s:linux/i2c-dev.h:linux/i2c-dev-user.h: smbusmodule.c
}