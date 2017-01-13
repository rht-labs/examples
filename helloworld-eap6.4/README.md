# JBoss EAP 6.4 Helloworld

This example will show you how to build and deploy a WAR file to OpenShift in different ways. It has been tested using EAP 6.4 on localhost and [EAP 6.4 S2I for OpenShift](https://access.redhat.com/documentation/en/red-hat-jboss-middleware-for-openshift/3/paged/red-hat-jboss-enterprise-application-platform-for-openshift/). The REST endpoint is written at the base url and the port is set to 8080.

## Deployment
* Local: First, start up a JBoss EAP 6.4 instance and then run `mvn clean package jboss-as:deploy`
* OpenShift, build WAR with s2i: 
  * makes sure your `oc` client is in the right project.
  * `oc new-app registry.access.redhat.com/jboss-eap-6/eap64-openshift:latest~https://github.com/rht-labs/examples.git --context-dir=helloworld-eap6.4 --name=helloworld`
* OpenShift, use existing WAR with s2i:
  * TODO defined further
  * Will need to customize the assemble script per [the docs](https://docs.openshift.org/latest/dev_guide/builds.html#using-external-artifacts)
  * WAR for this binary exists in the [Labs Nexus](http://nexus.core.rht-labs.com/repository/labs-releases/org/jboss/quickstarts/eap/jboss-kitchensink/6.4.0.GA/jboss-kitchensink-6.4.0.GA.war)
  


## Origin

This app is taken from the [EAP 6.4. kitchen sink quick start](https://access.redhat.com/jbossnetwork/restricted/softwareDownload.html?softwareId=37373). 