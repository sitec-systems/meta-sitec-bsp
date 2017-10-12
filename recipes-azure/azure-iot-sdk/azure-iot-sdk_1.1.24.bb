DESCRIPTION = "Microsoft Azure IoT SDKs and libraries for C"
AUTHOR = "Microsoft Corporation"
HOMEPAGE = "https://github.com/Azure/azure-iot-sdk-c"
LICENSE = "MIT"
MAINTAINER = "Robert Lehmann <robert.lehmann@sitec-systems.de>"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4283671594edec4c13aeb073c219237a"

inherit cmake python3-dir

RPROVIDES_${PN} += "azure-iot-sdk-c"
RPROVIDES_${PN}-dev += "azure-iot-sdk-c-dev"

PR = "r1"

DEPENDS = "\
    azure-c-shared-utility \
    azure-uamqp-c \
    azure-umqtt-c \
    boost \
"

RDEPENDS_${PYTHON_PN}-${PN} += "boost"
RDEPENDS_${PYTHON_PN}-${PN} += "python3"

SRC_URI = "\
    git://github.com/Azure/azure-iot-sdk-python.git \
    file://0001-Refactor-cmake-if-statements.patch \
    file://0002-Only-run-tests-if-requested.patch \
    file://0003-CMakeLists-Fix-boost-python3-find-routine.patch \
"
SRCREV = "db0785dc35aeee45fcc03b8fad2c0ccf57ca24d8"

PR = "r0"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

OUTDIR ?= "${B}"

# List of packages to build
PACKAGES = "\
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
    ${PYTHON_PN}-${PN} \
"

PACKAGECONFIG ??= "python"
PACKAGECONFIG[python] = "-Dbuild_python:STRING=${PYTHON_BASEVERSION}, -Dbuild_python:BOOL=OFF, ${PYTHON_PN} boost, boost-python3"

#do_configure_prepend() {
#   cd ${S}
#    git config --global http.sslverify "false"
#    git submodule update --init c
#    
#    cd ${S}/c
#    git submodule update --init
#
#    cd ${S}/c/deps/uhttp
#    git submodule update --init
#
#    cd ${S}/c/dps_client/deps/utpm
#    git submodule update --init
#
#    cd ${S}/c/uamqp
#    git submodule update --init deps/azure-c-shared-utility
#
#    cd ${S}/c/umqtt
#    git submodule update --init
#}

do_configure_prepend() {
    cd ${S}
    git submodule update --init --recursive
}

## CMake ##
OECMAKE_SOURCEPATH = "${S}/c"
EXTRA_OECMAKE = "-DBUILD_SHARED_LIBS:BOOL=ON -Dskip_samples:BOOL=ON -Dskip_unittests:BOOL=ON -Duse_installed_dependencies:BOOL=ON"

do_install_append() {
    # Python
    if ${@bb.utils.contains('PACKAGECONFIG','python','true','false',d)}; then
        install -d ${D}${PYTHON_SITEPACKAGES_DIR}
        oe_libinstall -C ${OUTDIR}/python/src -so iothub_client ${D}${PYTHON_SITEPACKAGES_DIR}
        oe_libinstall -C ${OUTDIR}/python_service_client/src -so iothub_service_client ${D}${PYTHON_SITEPACKAGES_DIR}
    fi
}

sysroot_stage_all_append () {
    sysroot_stage_dir ${D}${exec_prefix}/cmake ${SYSROOT_DESTDIR}${exec_prefix}/cmake

    # Fix CMake configs
    sed -i 's#${libdir}#${STAGING_LIBDIR}#g' ${SYSROOT_DESTDIR}${exec_prefix}/cmake/azure_iot_sdksTargets*
    sed -i 's#${includedir}/azureiot#${STAGING_INCDIR}/azureiot#g' ${SYSROOT_DESTDIR}${exec_prefix}/cmake/azure_iot_sdksTargets*
}

FILES_${PN} = "${libdir}/*.so"

RDEPENDS_${PN}-dev = "\
    azure-iot-sdk \
    azure-umqtt-c-dev \
    azure-uamqp-c-dev \
    azure-c-shared-utility-dev \
    ${PYTHON_PN} \
"
FILES_${PN}-dev += "\
    ${includedir} \
    ${exec_prefix}/cmake \
"

FILES_${PN}-dbg += "\
    ${libdir}/.debug \
    ${PYTHON_SITEPACKAGES_DIR}/.debug \
"

FILES_${PYTHON_PN}-${PN} += "\
    ${PYTHON_SITEPACKAGES_DIR}/*.so \
"

RRECOMMENDS_azure-iot-sdk-dev[nodeprrecs] = "1"

INSANE_SKIP_${PYTHON_PN}-${PN} += "rpaths"
