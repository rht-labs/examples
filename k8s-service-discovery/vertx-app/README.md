# Introduction

This project exposes a simple HTTP Server running on port 8080.

You can build and deploy it as described hereafter.

# Prerequisites

To get started with the starter you'll need the following prerequisites:

Name       | Description      | Version
---------- | ---------------- | -------
[java][1]  | Java JDK         | 8
[maven][2] | Apache Maven     | 3.2.x 
[oc][3]    | OpenShift Client | v3.3.x

[1]: http://www.oracle.com/technetwork/java/javase/downloads/
[2]: https://maven.apache.org/download.cgi?Preferred=ftp://mirror.reverse.net/pub/apache/
[3]: https://docs.openshift.com/enterprise/3.2/cli_reference/get_started_cli.html

In order to deploy this project, you must have an account on an OpenShift Online (OSO): https://console.dev-preview-int.openshift.com/ instance.

# Build the Project

To build the project, execute the following maven command:

```
mvn clean install
```

# Deploy in OpenShift Online

1. Go to [OpenShift Online](https://console.dev-preview-int.openshift.com/console/command-line) to get the token used by the oc client for authentication and project access. 

2. On the oc client, execute the following command to replace MYTOKEN with the one from the Web Console:

    ```
    oc login https://api.dev-preview-int.openshift.com --token=MYTOKEN
    ```
3. Use the Fabric8 Maven Plugin to launch the S2I process on the OpenShift Online machine & start the pod.

    ```
    mvn clean fabric8:deploy
    ```
4.  To access the HTTP Server, execute this oc command to get the HOST/PORT address exposed by the route
    ```
    oc get route/<artifactid>
    ```

 5. Next, simply curl or copy/paste the `http://HOST/PORT` address within your browser !

 6. Discover and play with Eclipse Vert.x - [Doc][1]
    [1] : http://vertx.io/docs/
