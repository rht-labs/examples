# EE App

This app will be built and deployed as a WAR file. It has been tested using EAP 6.4 on localhost and [EAP 6.4 S2I for OpenShift](https://access.redhat.com/documentation/en/red-hat-jboss-middleware-for-openshift/3/paged/red-hat-jboss-enterprise-application-platform-for-openshift/). The REST endpoint is written at the base url and the port is set to 8080.

## Deployment

* Local: First, start up a JBoss EAP 6.4 instance and then run `mvn clean package jboss-as:deploy`
* OpenShift: 
  * makes sure your `oc` client is in the right project.
  * `oc new-app registry.access.redhat.com/jboss-eap-6/eap64-openshift:latest~https://github.com/rht-labs/examples.git --context-dir=k8s-service-discovery/ee-app --name=ee-app`
  * Update the `JAVA_TOOL_OPTIONS` environment variable for the `ee-app` deployment to include `-DspringUrl=<value>`. see below for the contexts of this `<value>`.

## Configuration

* `spring.url` FQDN for the Spring REST Service. Defaults to `http://localhost:8081` if the property is not present. Remember to use the [internal service DNS entry](https://docs.openshift.com/enterprise/3.0/architecture/additional_concepts/networking.html) when deploying to OpenShift

## TODO
* Use a [git repo volume](https://kubernetes.io/docs/user-guide/volumes/#gitrepo) to hold properties files that hold the FQDN

## Origin
This demo is derived from the [JAX-RS server](https://github.com/jboss-developer/jboss-eap-quickstarts/tree/6.4.x/helloworld-rs) and the [JAX-RS client](https://github.com/jboss-developer/jboss-eap-quickstarts/tree/6.4.x/jax-rs-client) JBoss quick starts.