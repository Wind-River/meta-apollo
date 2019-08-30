# Build and Run
## 1. Clone away
```
$ mkdir <apollo-project>
$ git clone --branch master --single-branch git://github.com/ros/meta-ros.git
$ git clone http://stash.wrs.com/scm/~akholodn/meta-apollo.git
$ git clone --branch WRLINUX_10_18_BASE --single-branch git://lxgit.wrs.com/wrlinux-x
$ <apollo-project>/wrlinux-x/setup.sh --machines renesas-rcar-h3 --distro wrlinux --all-layers  --dl-layers

```

## 2. Prepare build
```
$ . <apollo-project>/oe-init-build-env <build>

# Disable multlib to workaround /usr/lib64 [installed-vs-shipped] QA error from meta-ros
$ sed -i "s:require conf/multilib.conf:#require conf/multilib.conf:g" <apollo-project>/layers/wrlinux/wrlinux-distro/conf/distro/wrlinux-common.inc

# Allow fetch from internet
$ sed -i "s/BB_NO_NETWORK/#BB_NO_NETWORK/" <build>/conf/local.conf

# Disable whitelist mechanism
$ echo 'INHERIT_DISTRO_remove = "whitelist"' >> <build>/conf/local.conf

# Add layers meta-ros and meta-apollo
$ bitbake-layers add-layer <apollo-project>/meta-ros
$ bitbake-layers add-layer <apollo-project>/meta-apollo

```

## 3. Build image
```
cd <build>
$ bitbake apollo-common

```


