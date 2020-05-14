SUMMARY = "tinc vpn software"
HOMEPAGE = "https://tinc-vpn.org"
MAINTAINER = "Robert Lehmann <robert.lehmann@sitec-systems.de>"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=a96f92622f623be94f66efc4690c6bb5"

PR = "r0"

SRC_URI = "https://www.tinc-vpn.org/packages/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "fb29dfa2e6d51cd5ab6c9d8c9bc95d48"
SRC_URI[sha256sum] = "61b9c9f9f396768551f39216edcc41918c65909ffd9af071feb3b5f9f9ac1c27"

DEPENDS = "openssl lzo zlib ncurses"
RDEPENDS_${PN} = "openssl lzo zlib ncurses readline"

inherit autotools

FILES_${PN} = "/"
