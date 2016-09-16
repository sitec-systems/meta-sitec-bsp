SUMMARY = "sitec version of CanFestival library"
HOMEPAGE = "http://www.sitec-systems.de"
SECTION = "base"

PR = "r5"

SRC_URI = "git://rlehmann@172.16.40.5/redmine/projekte/15_0021/scm_15_0021.git;protocol=https;branch=master"

SRCREV = "460a2713d7033d493717fb02607d237ff6f1443e"
S = "${WORKDIR}/git"

LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://LICENCE;md5=085e7fb76fb3fa8ba9e9ed0ce95a43f9"

inherit pkgconfig gettext

do_configure() {
    ./configure --binutils=arm-poky-linux-gnueabi- --timers=unix --target=unix \
        --arch=arm --can=socket --enable-shared --SDO_MAX_LENGTH_TRANSFER=65536
}

do_compile() {
    oe_runmake canfestival
}

do_install() {
    install -d ${D}${libdir}/canfestival ${D}${includedir}/canfestival
    install -m 0655 ${S}/drivers/can_socket/libcanfestival_can_socket.so ${D}${libdir}/canfestival
    install -m 0655 ${S}/src/libcanfestival.so ${D}${libdir}/canfestival
    install -m 0655 ${S}/drivers/unix/libcanfestival_unix.so ${D}${libdir}/canfestival
    install -m 0655 ${S}/include/unix/applicfg.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/unix/canfestival.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/can_driver.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/can.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/config.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/data.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/dcf.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/def.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/emcy.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/lifegrd.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/lss.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/nmtMaster.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/nmtSlave.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/objacces.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/objdictdef.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/pdo.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/sdo.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/states.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/sync.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/sysdep.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/timer.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/timers_unix/timerscfg.h ${D}${includedir}/canfestival
    install -m 0655 ${S}/include/timers_driver.h ${D}${includedir}/canfestival
    install -d ${D}/var/lib/pkg_hash
    echo "${SRCREV}" > ${D}/var/lib/pkg_hash/${PN}_${PV}
}

pkg_postinst_${PN} () {
    #!/bin/sh -e
	rm -rf /usr/lib/libcanfestival.so
    rm -rf /usr/lib/libcanfestival_unix.so
    rm -rf /usr/lib/libcanfestival_can_socket.so
    ln -s /usr/lib/canfestival/libcanfestival.so /usr/lib/libcanfestival.so
    ln -s /usr/lib/canfestival/libcanfestival_unix.so /usr/lib/libcanfestival_unix.so
    ln -s /usr/lib/canfestival/libcanfestival_can_socket.so /usr/lib/libcanfestival_can_socket.so
}

pkg_postrm_${PN} () {
    #!/bin/sh -e
    rm -rf /usr/lib/libcanfestival.so
    rm -rf /usr/lib/libcanfestival_unix.so
    rm -rf /usr/lib/libcanfestival_can_socket.so
}

FILES_${PN} = "${libdir}/canfestival/*.so"
FILES_${PN} += "/var/lib/pkg_hash/${PN}_${PV}"
FILES_${PN}-dbg = "${libdir}/canfestival/.debug/*.so"
