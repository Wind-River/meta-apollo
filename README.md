# meta-apollo

This layer depends on meta-ros and meta-java. Add the absolute paths to these two layers, as well as the absolute path to meta-apollo, to your bblayers.conf file

Add the following lines to your local.conf:

PNWHITELIST_LAYERS_remove += "apollo meta-java ros-layer"
PNWHITELIST_openembedded-layer += "giflib-native bdwgc-native"

If you have issues with your Java version, also adding the following lines may help:

Run "bitbake apollo-common" to attempt to build the package
