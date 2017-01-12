#!/bin/bash

mvn clean package
oc new-app https://github.com/rht-labs/examples.git --name=spring-app --context-dir=k8s-service-discovery/spring-app
oc start-build spring-app --from-dir=../../