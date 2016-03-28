# kiteclub
Example application for using Keycloak

## Setup WildFly 10.0.0.Final
Steps to setup:

* Download the tar.gz/zip
* extract it
* find the line containing 'port-offset="${jboss.socket.binding.port-offset:0}"'
* change ':0' into ':500'
* now you can start the server using either Eclipse (add a server) or manually by using ./bin/standalone.sh in the WildFly-home-dir

