SUMMARY = "Autonomous driving platform"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d7ae77d86a5df6d064d29ea4d8ad2beb"
SECTION = "apollo"

SRC_URI = "https://github.com/ApolloAuto/apollo/archive/v5.0.0.tar.gz \
           file://0001-install_bazel_packages.sh-remove-hardcoded-home-tmp.patch \
           file://0001-install_google_styleguide.sh-remove-hardcoded-home-t.patch \
           file://0001-protobuf.bzl-compatible-with-future-versions-of-Baze.patch \
           file://0001-support-cross-compiling-on-Yocto.patch \
           file://0001-support-cross-compiling-on-Yocto-2.patch \
           file://0001-fix-build-error-while-gcc-7.1.patch \
           file://WORKSPACE \
           file://BUILD \
           file://BUILD.yocto_compiler \
           file://CROSSTOOL.tpl \
           file://yocto_compiler_configure.bzl \
"

DEPENDS = " \
    util-linux-native \
    gflags \
    glog \
    pcl \
    fastrtps \
    fastcdr \
    protobuf \
    osqp-apollo \
    curl \
    poco \
    libtinyxml2 \
    asio \
"
	
SRC_URI[md5sum] = "7ba09a3712767dceba58835f70811d03"


S = "${WORKDIR}/apollo-5.0.0"

inherit bazel

do_patch[postfuncs] += "download_third_src"
download_third_src() {
    [ -e ${WORKDIR}/third_src ] && exit 0
    mkdir ${WORKDIR}/third_src;
    cd ${WORKDIR}/third_src;
    ${S}/docker/build/installers/install_bazel_packages.sh
    ${S}/docker/build/installers/install_google_styleguide.sh
    cd -
}

do_configure_append () {
    install -m 644 ${WORKDIR}/WORKSPACE ${S}/

    mkdir -p ${S}/third_party/toolchains/yocto/
    install -m 644 ${WORKDIR}/BUILD ${S}/third_party/toolchains/yocto/
    install -m 644 ${WORKDIR}/CROSSTOOL.tpl ${S}/third_party/toolchains/yocto/
    install -m 644 ${WORKDIR}/yocto_compiler_configure.bzl ${S}/third_party/toolchains/yocto/
    install -m 644 ${WORKDIR}/BUILD.yocto_compiler ${S}

    CT_NAME=$(echo ${HOST_PREFIX} | rev | cut -c 2- | rev)
    SED_COMMAND="s#%%CT_NAME%%#${CT_NAME}#g"
    SED_COMMAND="${SED_COMMAND}; s#%%WORKDIR%%#${WORKDIR}#g"
    SED_COMMAND="${SED_COMMAND}; s#%%YOCTO_COMPILER_PATH%%#${BAZEL_OUTPUTBASE_DIR}/external/yocto_compiler#g"

    sed -i "${SED_COMMAND}" ${S}/BUILD.yocto_compiler \
                            ${S}/third_party/toolchains/yocto/CROSSTOOL.tpl \
                            ${S}/WORKSPACE
}


do_compile () {
    #mv WORKSPACE.in WORKSPACE
    unset CC

    BUILD_TARGETS=`${BAZEL} query //cyber/...`
    echo "BUILD_TARGETS: ${BUILD_TARGETS}"
    ${BAZEL} build \
        -c opt \
        --subcommands --explain=${T}/explain.log \
        --verbose_explanations --verbose_failures \
        --verbose_failures \
        --cpu=armeabi \
        --crosstool_top=@local_config_yocto_compiler//:toolchain \
        ${BUILD_TARGETS}

    ${BAZEL} shutdown
}
