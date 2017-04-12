require recipes-bsp/images/sitec-image.bb

PR = "r0"

IMAGE_INSTALL += "gtest"
IMAGE_INSTALL += "gcc"
IMAGE_INSTALL += "make"
IMAGE_INSTALL += "gtest"
IMAGE_INSTALL += "glog"
IMAGE_INSTALL += "boost"
IMAGE_INSTALL += "bc"
IMAGE_INSTALL += "vim"
IMAGE_INSTALL += "openjre-8"
IMAGE_INSTALL += "mosquitto"

EXTRA_IMAGE_FEATURES_append = " dev-pkgs"
EXTRA_IMAGE_FEATURES_append = " staticdev-pkgs"
