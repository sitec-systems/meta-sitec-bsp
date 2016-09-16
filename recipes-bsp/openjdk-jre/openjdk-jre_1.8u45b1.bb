# Copyright (C) 2015 Robert Lehmann <robert.lehmann@sitec-systems.de>

DESCRIPTION = "Java Runtime Enviroment at the base of the openjdk zero port"
HOMEPAGE = "http://mirror.archlinuxarm.org/armv7h/extra/"
LICENSE = "MIT"
SECTION = "base"
DEPENDS = "libffi zlib"

LIC_FILES_CHKSUM = "file://etc/java-8-openjdk/arm/jvm.cfg;md5=bd9504a9e1203b006806aae2bf549c52"

PR = "r1"

SRC_URI = "file://openjdk-jre-${PV}.tar.gz"

inherit insane

INSANE_SKIP_${PN} = "already-stripped"

S = "${WORKDIR}"

do_configure() {
}

do_compile() {
}

do_install() {
    install -d ${D}${sysconfdir}/java-8-openjdk
    cp -r ${S}/etc/java-8-openjdk/* ${D}${sysconfdir}/java-8-openjdk
    install -d ${D}${libdir}/jvm/java-8-openjdk/jre
    install -d ${D}${libdir}/jvm/java-8-openjdk/jre/bin
    install -d ${D}${libdir}/jvm/java-8-openjdk/jre/lib
    install -m 0755 ${S}/usr/lib/jvm/java-8-openjdk/jre/bin/java ${D}${libdir}/jvm/java-8-openjdk/jre/bin
    install -m 0755 ${S}/usr/lib/jvm/java-8-openjdk/jre/bin/jjs ${D}${libdir}/jvm/java-8-openjdk/jre/bin
    install -m 0755 ${S}/usr/lib/jvm/java-8-openjdk/jre/bin/keytool ${D}${libdir}/jvm/java-8-openjdk/jre/bin
    install -m 0755 ${S}/usr/lib/jvm/java-8-openjdk/jre/bin/orbd ${D}${libdir}/jvm/java-8-openjdk/jre/bin
    install -m 0755 ${S}/usr/lib/jvm/java-8-openjdk/jre/bin/pack200 ${D}${libdir}/jvm/java-8-openjdk/jre/bin
    install -m 0755 ${S}/usr/lib/jvm/java-8-openjdk/jre/bin/rmid ${D}${libdir}/jvm/java-8-openjdk/jre/bin
    install -m 0755 ${S}/usr/lib/jvm/java-8-openjdk/jre/bin/rmiregistry ${D}${libdir}/jvm/java-8-openjdk/jre/bin
    install -m 0755 ${S}/usr/lib/jvm/java-8-openjdk/jre/bin/servertool ${D}${libdir}/jvm/java-8-openjdk/jre/bin
    install -m 0755 ${S}/usr/lib/jvm/java-8-openjdk/jre/bin/tnameserv ${D}${libdir}/jvm/java-8-openjdk/jre/bin
    install -m 0755 ${S}/usr/lib/jvm/java-8-openjdk/jre/bin/unpack200 ${D}${libdir}/jvm/java-8-openjdk/jre/bin
    cp -r ${S}/usr/lib/jvm/java-8-openjdk/jre/lib/* ${D}${libdir}/jvm/java-8-openjdk/jre/lib
}

pkg_postinst_${PN} () {
    #!/bin/sh -e
    ln -s /usr/lib/jvm/java-8-openjdk/jre/bin/java /usr/bin/java
}

FILES_${PN} = "${libdir}/jvm/*"
FILES_${PN} += "${sysconfdir}/java-8-openjdk/*"
