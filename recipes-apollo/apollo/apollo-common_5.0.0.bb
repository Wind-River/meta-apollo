SUMMARY = "Autonomous driving platform"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d7ae77d86a5df6d064d29ea4d8ad2beb"
SECTION = "apollo"

SRC_URI = "https://github.com/ApolloAuto/apollo/archive/v5.0.0.tar.gz \
	file://WORKSPACE;subdir=apollo-5.0.0/"

DEPENDS = " \
"
	
SRC_URI[md5sum] = "7ba09a3712767dceba58835f70811d03"


S = "${WORKDIR}/apollo-5.0.0"


inherit bazel

#BBCLASSEXTEND = "native"

do_compile () {
    #mv WORKSPACE.in WORKSPACE
    unset CC
    ${S}/bazel build \
        -c opt \
        --subcommands --explain=${T}/explain.log \
        --verbose_explanations --verbose_failures \
        --verbose_failures \
        //modules/common

    ${S}/bazel shutdown
}