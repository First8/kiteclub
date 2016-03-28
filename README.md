# kiteclub
Example application for using Keycloak

## Setup WildFly 10.0.0.Final
Steps to setup:

* Download the tar.gz/zip
* extract it
* find the line containing 'port-offset="${jboss.socket.binding.port-offset:0}"'
* change ':0' into ':500'
* now you can start the server using either Eclipse (add a server) or manually by using ./bin/standalone.sh in the WildFly-home-dir

## Starting keycloak
Steps to setup:

* download Keycloak 1.9.1
* extract it
* start it using ./bin/standalone.sh
* visit http://localhost:8080/auth
* add admin-user as requested
* visit the admin-page from the main page (http://localhost:8080/auth/admin/)
* Add a realm
* select the file 'keycloak-realm.json' for import
* select 'Create'

## Adding Keycloak adapter
Steps to setup:

* download the adapter for WildFly (keycloak-wildfly-adapter-dist-1.9.1.Final.tar.gz)
* extract it in the WildFly dir created above
* add the items mentioned in the manual (8.2.1. Adapter Installation) to you ./standalone/configuration/standalone.xml

```xml
<server xmlns="urn:jboss:domain:1.4">

    <extensions>
        <extension module="org.keycloak.keycloak-adapter-subsystem"/>
          ...
    </extensions>

    <profile>
        <subsystem xmlns="urn:jboss:domain:keycloak:1.1"/>
         ...
    </profile>

    <subsystem xmlns="urn:jboss:domain:security:1.2">
       <security-domains>
         ...
         <security-domain name="keycloak">
            <authentication>
              <login-module code="org.keycloak.adapters.jboss.KeycloakLoginModule"
                            flag="required"/>
             </authentication>
         </security-domain>
       </security-domains>
```

*Note*: the cli-scripts have not worked for me.

## REST-API
Check that the REST-API is still accesable: http://localhost:8580/kiteclub/rest/kites should yield nice json.

## Theme
The article speaks about the theme. Due to an upgrade of Keycloak (from 1.6.0 to 1.9.1) the themes dir hase been moved from ‘standalone/configuration/themes’ to ‘themes’.
The theme can be set in the admin page http://localhost:8080/auth/admin/master/console/#/realms/J-Fall-2015/theme-settings.
