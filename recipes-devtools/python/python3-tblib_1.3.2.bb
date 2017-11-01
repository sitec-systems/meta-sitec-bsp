DESCRIPTION = "Traceback serialization library."
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=631c38709bb2adff681fe6c5dfd43f2f"

SRC_URI += "https://pypi.python.org/packages/ec/c4/8c651f3240a73c28a218194f3d527eb2be5a173d08501060cdee84ade33f/tblib-${PV}.tar.gz"
SRC_URI[md5sum] = "a0b3a444515b2afcdafab83104cbafec"
SRC_URI[sha256sum] = "436e4200e63d92316551179dc540906652878df4ff39b43db30fcf6400444fe7"

S = "${WORKDIR}/tblib-${PV}"

inherit setuptools3