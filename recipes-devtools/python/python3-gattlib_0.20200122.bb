DESCRIPTION = "Library to access Bluetooth LE devices"
HOMEPAGE = "https://pypi.org/project/gattlib/"
SECTION = "devel/python"

DEPENDS = "bluez5"

LICENSE = "APSL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=d056e552994ef4c3875b27e7d694b89e"

SRC_URI += "hg://bitbucket.org/OscarAcena/pygattlib;module=default;protocol=https"
SRC_URI += "file://fix-setuppy.patch"
SRCREV = "ef6664d03577546fdf60c68e07b788e55d3d77b0"

#PYPI_PACKAGE = "gattlib"
#PYPI_PACKAGE_EXT = "zip"
#inherit pypi setuptools3
inherit setuptools3

S = "${WORKDIR}/default"

DEPENDS = "\
    boost \
"

RDEPENDS_${PN} += "\
    bluez5 \
"
