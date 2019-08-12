DESCRIPTION = "Bazel build and test tool"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"


SRC_URI[md5sum] = "af70d9c983d166e2d199b2831c524220"

SRC_URI = "https://github.com/bazelbuild/bazel/releases/download/${PV}/bazel-${PV}-dist.zip file://ErrorPronePlugin.java.patch "
#           file://0001-HttpDownloader-save-download-tarball-to-distdir.patch 


inherit native

INHIBIT_SYSROOT_STRIP = "1"

DEPENDS = "coreutils-native \
           zip-native \
           openjdk-8-native \
          "

S="${WORKDIR}"

TS_DL_DIR ??= "${DL_DIR}"
do_compile () {
    export JAVA_HOME="${STAGING_LIBDIR_NATIVE}/jvm/openjdk-8-native"
    TMPDIR="${TOPDIR}/bazel" \
    VERBOSE=yes \
    ./compile.sh
}
#    EXTRA_BAZEL_ARGS="--distdir=${TS_DL_DIR}" 
do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${S}/output/bazel ${D}${bindir}
}

# Explicitly disable uninative
UNINATIVE_LOADER = ""