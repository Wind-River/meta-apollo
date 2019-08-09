SUMMARY = "Autonomous driving platform"
LICENSE = "Apache-2.0"
LIC_FILES_CHECKSUM = "file://LICENSE"
SECTION = "apollo"

SRC_URI = "https://github.com/ApolloAuto/apollo/archive/v5.0.0.tar.gz"

DEPENDS = " \
	packagegroup-core-buildessential \
	cmake \
	curl \
	gcc \
	g++ \
	git \
	unzip \
	zip \
	vim \
	wget \
	bc \
	gdb \
	uuid-dev \
	python \
	python-dev \
	python3 \
	python3-dev \
	libasio-dev \
	libtinyxml2 \
	libncurses5-dev \
	libavcodec57 \
	libavcodec-dev \
	libswscale4 \
	libswscale-dev \
	libcurl4-nss-dev \
	libpoco-dev \
	libeigen3-dev \
	libflann-dev \
	qhull \
	qhull-dev \
	libpcap0.8 \
	libpcap0.8-dev \
	libusb \
	libusb-dev \
	libopenni \
	libopenni-dev \
	libopenni2 \
	libopenni2-dev \
	openjdk-8-jdk \
	software-properties-common \
	protobuf \
	osqp \
	libboost \
	libboost-dev \
	boost \
	boost-dev \
	libmkl \
	opencv \
	openmpi \
	gdal-dev \
	vtk \
	xdmf-dev \
	python3-mpi \
	python3-vtk \
	tcl-vtk \
	poco \
	fastrtps \
	pcl \
"
	


S = "${WORKDIR}/apollo-5.0.0"


inherit bazel

BBCLASSEXTEND = "native"
