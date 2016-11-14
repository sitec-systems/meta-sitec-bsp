require recipes-bsp/images/sitec-image.bb

IMAGE_INSTALL += "gtest"
IMAGE_INSTALL += "gcc"
IMAGE_INSTALL += "make"

EXTRA_IMAGE_FEATURES_append = " dev-pkgs"


