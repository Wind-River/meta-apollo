SUMMARY = "The Operator Splitting QP Solver with apollo qdldl"
HOMEPAGE = "https://github.com/oxfordcontrol/osqp"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e7dee840ecc391ccb2fdb9f16a993069"

SRC_URI = " \
    https://github.com/oxfordcontrol/osqp/archive/v0.4.1.zip;name=osqp \
    https://apollocache.blob.core.windows.net/apollo-cache/qdldl.zip;name=apollo;subdir=${S}/lin_sys/direct/qdldl/qdldl_sources/ \
    file://0001-tweak-install-dir-while-enabling-multilib.patch \
"

SRC_URI[osqp.md5sum] = "16029a55602e8c8a9423caf90db6721e"
SRC_URI[osqp.sha256sum] = "f4b6a21564b4caefe9ef8163e62b06e40871806dc466bd0177231df208233ced"

SRC_URI[apollo.md5sum] = "ced1910eec344f3e5741f7afa5896afc"
SRC_URI[apollo.sha256sum] = "052b0e039c8f906c91def34433a826b34373d3cd709cd901b601e7d7a2957b0a"

S = "${WORKDIR}/osqp-0.4.1"

inherit cmake

FILES_${PN} = "${libdir}/libosqp.so ${libdir}/libosqp.a"
FILES_${PN}-dev = "${libdir}/cmake ${includedir}"
INSANE_SKIP_${PN} = "dev-elf"
