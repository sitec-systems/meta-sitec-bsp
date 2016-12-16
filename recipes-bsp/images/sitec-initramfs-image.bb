require recipes-core/images/core-image-minimal.bb

PR = "r0"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

IMAGE_INSTALL += "packagegroup-core-ssh-openssh"
IMAGE_INSTALL += "mtd-utils"
IMAGE_INSTALL += "mtd-utils-ubifs"
