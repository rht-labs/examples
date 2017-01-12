# Spring App

This app will be built and deployed as a fat JAR. It has been test on localhost and using the [Fabric8 Java S2I](https://hub.docker.com/r/fabric8/s2i-java/) image for OpenShift. The REST endpoint is written at the base url and the port is set to 8081.

## Deployment

* Local: `mvn clean spring-boot:run`
* OpenShift: 
  * make sure your `oc` client is in the project in which you want to deploy the app. 
  * Then, `./deploy-to-openshift.sh`
  * update the `JAVA_OPTIONS` environment variable for the `spring-app` deployment to include `-Dvertx.url=<value>`. see below for the contexts of this `<value>`.

## Configuration

* `vertx.url` FQDN for the Vert.x REST Service. Defaults to `http://localhost:8082` if the property is not present. Remember to use the [internal service DNS entry](https://docs.openshift.com/enterprise/3.0/architecture/additional_concepts/networking.html) when deploying to OpenShift

## TODO
* Use a [git repo volume](https://kubernetes.io/docs/user-guide/volumes/#gitrepo) to hold properties files that hold the FQDN
* Write a template for this app

## Origin
This demo was bootstrapped with [Spring Initializer](http://start.spring.io/)