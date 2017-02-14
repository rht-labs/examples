# Introduction

This example shows how to use the out of the box [Jenkins templates from OpenShift](https://github.com/openshift/origin/blob/master/examples/jenkins/jenkins-ephemeral-template.json) with the [Jenkins S2I builder](https://docs.openshift.com/container-platform/3.3/using_images/other_images/jenkins.html), and configuration manage the whole thing with [ansible-stacks](https://github.com/rht-labs/ansible-stacks).

# TODO

* Add requirements.yml

# Usage

This is an ansible playbook, so run it like normal playbooks
``` bash
$ ansible-playbook -i <inventory-file> jenkins.yml
```

 We've provided an example inventory file. You'll need to copy this and create your own, replacing:

* `openshift_user`: user to login into openshift
* `openshift_password`: password for above user
* `openshift_url`: OpenShift REST API endpoint
* `hostname`, although it work if you leave the example value

# Notes

General
* only `openshift_url` is supported, not `openshift_cluster.openshift_host_env`
* the Jenkins password is generated, you can add the below line beneath line 22 of `jenkins.json` to set the password:
``` json
"JENKINS_PASSWORD": "your_password_here",
```

The error handling isn't robust in this playbook. To make things go smoothly:
* pick a user that has privileges to create new projects
* ensure a project named `pipelines` does not exit when you run this playbook
  * this means deleting the existing project if you need to rerun for any reason
