# Copyright (C) 2016 Robert Lehmann <robert.lehmann@sitec-systems.de>

DESCRIPTION = "Tool for controlling wireless interfaces of the S Core based products"
HOMEPAGE = "www.sitec-systems.de"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=1a6d268fd218675ffea8be556788b780"
SECTION = "base"
MAINTAINER = "Robert Lehmann <robert.lehmann@sitec-systems.de>"
RDEPENDS_${PN} += "gsmmux (>=1.0)"

PR = "r5"

SRC_URI = "file://LICENSE"
SRC_URI += "file://wirelessctl.sh"
SRC_URI += "file://wirelessctl.conf"

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/wirelessctl.sh ${D}${sysconfdir}/init.d/wirelessctl
    install -m 0644 ${WORKDIR}/wirelessctl.conf ${D}${sysconfdir}
}

FILES_${PN} += "${sysconfdir}/wirelessctl.conf"
FILES_${PN} += "${sysconfdir}/init.d/wirelessctl"

