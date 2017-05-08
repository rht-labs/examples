# Jenkins on OpenShift Container Platform Example

## Introduction

This example shows how to use the out of the box [Jenkins templates from OpenShift](https://github.com/openshift/origin/blob/master/examples/jenkins/jenkins-ephemeral-template.json) with the [Jenkins S2I builder](https://docs.openshift.com/container-platform/3.3/using_images/other_images/jenkins.html), and configuration manage the whole thing with [ansible-stacks](https://github.com/rht-labs/ansible-stacks).

## Requirements

The following must be installed and on your `$PATH`

- `oc`
- `ansible` 2.2.0.0 or 2.2.1.0
- `git`


## Usage

``` bash
$ ./run.sh
```

The script will prompt you for the following

* `openshift_user`: user to login into OpenShift
* `openshift_password`: password for above user
* `openshift_url`: OpenShift REST API endpoint

Advanced users might find that annoying, so feel free to add the above vars to the inventory file and then comments out the `vars_prompt` in [the playbook](jenkins-playbook.yml)

## Notes

**General:**
* only `openshift_url` is supported, not `openshift_cluster.openshift_host_env`
* the Jenkins password is generated, you can add the below line beneath line 22 of `jenkins.json` to set the password:
``` json
"JENKINS_PASSWORD": "your_password_here",
```