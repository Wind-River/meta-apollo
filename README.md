# meta-apollo

This layer depends on meta-ros and meta-java. Add the absolute paths to these two layers, as well as the absolute path to meta-apollo, to your bblayers.conf file

Add the following lines to your local.conf:

PNWHITELIST_remove += "apollo meta-java ros-layer"

If you have issues with your Java version, also adding the following lines may help:

PREFERRED_PROVIDER_virtual/java-initial-native = "cacao-initial-native"
PREFERRED_PROVIDER_virtual/java-native = "jamvm-native"
PREFERRED_PROVIDER_virtual/javac-native = "ecj-bootstrap-native"

Run "bitbake apollo-common" to attempt to build the package