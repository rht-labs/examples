# Spring App

This app will be built and deployed as a fat JAR. It has been test on localhost and using the [Fabric8 Java S2I](https://hub.docker.com/r/fabric8/s2i-java/) image for OpenShift. The REST endpoint is written at the base url and the port is set to 8082.

## Deployment

* Local: `compile vertx:run`
* OpenShift: 
  * make sure your `oc` client is in the project in which you want to deploy the app. 
  * `./deploy-to-openshift.sh`

## Configuration


## TODO
* Use a [git repo volume](https://kubernetes.io/docs/user-guide/volumes/#gitrepo) to hold properties files that hold the FQDN
* Write a template for this app

## Origin
This demo was bootstrapped with [Obsidian Toaster](http://front-generator-obsidian-alpha1.e8ca.engint.openshiftapps.com/wizard)