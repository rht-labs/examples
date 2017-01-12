# Kubernetes Service Discovery Example

This example demonstrates applications using [Kubernetes service discovery through DNS](https://fabric8.io/guide/develop/serviceDiscovery.html#service-discovery-via-dns), which is done by deploying 3 Java REST services that communicate with each other in the following order:

EE => Spring => Vert.x

The EE & Spring apps will use a Java System property with a Fully Qualified Domain Name (FQDN) to locate the service they will call. The service response will include the timestamp of when that service came up to provide evidence that the service is unique.

It should be noted that if these applications are deployed to the same k8s namespace/ OpenShift project, it's possible to hardcode the service name for the FQDN and k8s will resolve the namespace details. However; it's generally best practice to put services in their own namespace to support fine grained [Resource Quotas](https://kubernetes.io/docs/admin/resourcequota/). Therefore; this demo is written to support DNS required for services in different k8s namespaces / OpenShift projects.

## TODO

* Move from timestamps to pod names to indicate uniqueness of the pod.
* Use a [git repo volume](https://kubernetes.io/docs/user-guide/volumes/#gitrepo) to hold properties files that hold the FQDNs
* Introduce other languages, like Node.js to showing this polyglot