DESCRIPTION = "Tool for controlling cellular modules of S Core based products"
HOMEPAGE = "http://www.sitec-systems.de"
MAINTAINER = "Robert Lehmann <robert.lehmann@sitec-systems.de>"

LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"
SECTION = "base"

RDEPENDS_${PN} += "gsmmux (>=1.0) bash"

PR = "r1"

S = "${WORKDIR}"

SRC_URI += "file://cellularctl.bash"

do_install() {
			 install -d ${D}${sysconfdir}/init.d
			 install -m 0755 ${S}/cellularctl.bash ${D}${sysconfdir}/init.d/cellularctl
}