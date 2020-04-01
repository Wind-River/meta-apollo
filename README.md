# meta-apollo

# Overview

Apollo is an open autonomous driving platform. Apollo is a high performance, flexible architecture which accelerates the development, testing, and deployment of Autonomous Vehicles. (https://github.com/ApolloAuto/apollo)

The build system of Apollo is Bazel (https://bazel.build/).

meta-apollo layer (this layer) integrates Apollo to OE/Yocto platform
- Integrate Google's bazel to Yocto
- Add Yocto toolchain for bazel to support cross compiling.

This layer depends on meta-ros and meta-java. Add the absolute paths to these two layers, as well as the absolute path to meta-apollo, to your bblayers.conf file.

# Project License

The source code for this project is provided under the Apache 2.0 license license. Each source file should include a license notice that designates the licensing terms for the respective file.

# Prerequisite(s)

## Dependencies
```
$ sudo apt-get install gawk wget git-core diffstat unzip texinfo gcc-multilib  build-essential chrpath socat cpio python python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping libsdl1.2-dev xterm

$ sudo apt-get build-dep qemu
$ sudo apt install cpio
$ sudo apt-get install locales
$ sudo dpkg-reconfigure locales
```
Ensure that in /etc/apt/sources.list the deb-src lines are not commented out.
```
$ sudo locale-gen en_US.UTF-8

# Pick your favorite, e.g. en_US.UTF-8 UTF-8, then default the language to en_US.UTF-8
$ sudo update-locale LC_ALL=en_US.UTF-8 LANG=en_US.UTF-8
$ export LANG=en_US.UTF-8
```
## Build and Run
### 1. Clone
```
$ mkdir <apollo-project>
$ git clone --branch master --single-branch git://github.com/ros/meta-ros.git
$ git clone https://github.com/Wind-River/meta-apollo 
$ git clone --branch WRLINUX_10_18_BASE --single-branch https://github.com/WindRiver-Labs/wrlinux-x
$ <apollo-project>/wrlinux-x/setup.sh --machines renesas-rcar-h3 --distro wrlinux --all-layers --dl-layers
```
### 2. Prepare build
```
$ . <apollo-project>/oe-init-build-env <build>

# Disable multlib to workaround /usr/lib64 [installed-vs-shipped] QA error from meta-ros
$ sed -i "s:require conf/multilib.conf:#require conf/multilib.conf:g" <apollo-project>/layers/wrlinux/wrlinux-distro/conf/distro/wrlinux-common.inc

# Allow fetch from internet
$ sed -i "s/BB_NO_NETWORK/#BB_NO_NETWORK/" <build>/conf/local.conf

# Disable whitelist mechanism
$ echo 'INHERIT_DISTRO_remove = "whitelist"' >> <build>/conf/local.conf
```
Add the following lines to your local.conf:

```
PNWHITELIST_LAYERS_remove += "apollo meta-java ros-layer"
PNWHITELIST_openembedded-layer += "giflib-native bdwgc-native"
```
Add layers meta-ros and meta-apollo:

```
$ bitbake-layers add-layer <apollo-project>/meta-ros
$ bitbake-layers add-layer <apollo-project>/meta-apollo

# or in bblayers.conf insert the lines:
  /<path>/<apollo-project>/meta-ros \
  /<path>/<apollo-project>/meta-apollo \
```

### 3. Build image
```
cd <build>
$ bitbake apollo-common
```

# Legal Notices

All product names, logos, and brands are property of their respective owners. All company, product and service names used in this software are for identification purposes only. Wind River is a registered trademark of Wind River Systems, Inc.

Disclaimer of Warranty / No Support: Wind River does not provide support and maintenance services for this software, under Wind River’s standard Software Support and Maintenance Agreement or otherwise. Unless required by applicable law, Wind River provides the software (and each contributor provides its contribution) on an “AS IS” BASIS, WITHOUT WARRANTIES OF ANY KIND, either express or implied, including, without limitation, any warranties of TITLE, NONINFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are solely responsible for determining the appropriateness of using or redistributing the software and assume any risks associated with your exercise of permissions under the license.
