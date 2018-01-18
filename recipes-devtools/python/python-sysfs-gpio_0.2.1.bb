DESCRIPTION = "GPIO Library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=99d646e175d9e93f576233cc6c0f7aaa"

PR = "r0"

SRC_URI = "https://github.com/derekstavis/python-sysfs-gpio/archive/${PV}.tar.gz"
SRC_URI[md5sum] = "65214593a72431525c7e781cb9238d64"
SRC_URI[sha256sum] = "4b2e729c308766112d42624406ca32bda076ee6d2a2a046346661e2a2e73ad93"

RDEPENDS_${PN} = "python-twisted"

S = "${WORKDIR}/${PN}-${PV}"

inherit setuptools