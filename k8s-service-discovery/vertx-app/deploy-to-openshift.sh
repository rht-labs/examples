#!/bin/bash

mvn clean package
oc new-app https://github.com/rht-labs/examples.git --name=vertx-app --context-dir=k8s-service-discovery/vertx-app
oc start-build vertx-app --from-dir=../../