# Copyright (C) 2016 Robert Lehmann <robert.lehmann@sitec-systems.de>

DESCRIPTION = "UDEV Rules for u-Blox modems"
HOMEPAGE = "www.sitec-systems.de"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=1a6d268fd218675ffea8be556788b780"
SECTION = "base"
MAINTAINER = "Robert Lehmann <robert.lehmann@sitec-systems.de>"

PR = "r0"

SRC_URI += "file://LICENSE"
SRC_URI += "file://60-ublox-lisa.rules"
SRC_URI += "file://60-ublox-neo.rules"
SRC_URI += "file://60-ublox-lara.rules"
SRC_URI += "file://60-ublox-sara.rules"

do_install() {
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/60-ublox-lisa.rules ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/60-ublox-sara.rules ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/60-ublox-lara.rules ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/60-ublox-neo.rules ${D}${sysconfdir}/udev/rules.d
}

FILES_${PN} += "${sysconfdir}/udev/rules.d/60-ublox-lisa.rules"
FILES_${PN} += "${sysconfdir}/udev/rules.d/60-ublox-sara.rules"
FILES_${PN} += "${sysconfdir}/udev/rules.d/60-ublox-lara.rules"
FILES_${PN} += "${sysconfdir}/udev/rules.d/60-ublox-neo.rules"
