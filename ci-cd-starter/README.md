# CI / CD Starter

## Introduction

This example will build a baseline CI / CD environment with an example Java app and a web app. The goal is to put in place common tools and ensure their integration, and then provide a few apps to show you the environment in action. We're using automation provide by our [ansible-stacks](https://github.com/rht-labs/ansible-stacks) repository to power this example. Our hope is that you'll extend the approach we've taken here to model your own environment with an infrastructure as code mindset.

## What's In The Box?

- Jenkins 2.x, configured with Blue Ocean and all OCP plugins. Jenkins is not backed by a persistent volume at this time.
- Nexus 3.x, configured with Red Hat maven repositories and backed by a persistent volume
- Java 8, Maven 3.3.x build slave for Jenkins, pre-configured to use Nexus
- SonarQube 6.3 (latest) for performing quality analysis on projects
- Example CI / CD pipeline, configured to work with Jenkins pipelines and OpenShift Pipeline builds
- Example Java

Still TODO:
- Example web app (i.e. JS/HTML/CSS)
- Example CI / CD pipeline for the web app
- Integrate SonarQube with CI/CD pipelines

## Requirements

The following must be installed and on your `$PATH`

- `oc` 3.5.x.x
- `ansible` 2.2.0.0 or 2.2.1.0
- `git`
- `openshift` 3.5.x.x cluster with at least (1) PV with `RWX`. Default required size is 10Gi, but can be configured in the [the nexus template](vars/ci-cd-starter-vars.json). This is a fairly resource intensive example, so it's probably not fit for the [CDK](https://developers.redhat.com/products/cdk/overview/).

## Usage

``` bash
$ ./run.sh
```
### By Default...

The script will prompt you for the following variables

* `openshift_user`: user to login into OpenShift
* `openshift_password`: password for above user
* `openshift_url`: OpenShift REST API endpoint

### Advanced Users Might Prefer...

To put their credential information in an ansible vars file so they aren't prompted for it on CLI every time. Therefore; we wrote `run.sh` to detect the presence of `vars/openshift-vars.json`, `vars/openshift-vars.yaml` or `vars/openshift-vars.yml` and use those variable files instead of prompting you. These files are also `.gitignored` so you don't accidentally commit your credentials to SCM.

### If You Want To Change the Projects/Namespaces Used in the Example

You'll need to edit the value of `"project_name_prefix"` in [the project_names.json var file](vars/project-names.json). The postfixes of `-ci-cd`, `-dev`, `-test` & `-uat` are static and required by other parts of automation, but you can update the prefix to your liking.

## Access Control

1. Nexus is configured to use the default user `admin` & password `admin123`
2. Jenkins is configured to use the [OpenShift OAuth plugin](https://github.com/openshift/jenkins-openshift-login-plugin), which will inspect a users OCP `rolebindings` for the project Jenkins lives in (by default `pipelines` in this example), and then assign a user Jenkins permissions accordingly. Therefore; make sure any users accessing Jenkins have the related permissions (e.g. `edit`, `view`, `admin`) in the Jenkins OCP project.


## How to Know It's Working

You want to see that a basic Java app has been built my Jenkins, deployed the nexus and awaiting deployment. Here's how to do that.

1. Login into Jenkins. You'll see a `ci-cd/java-app-pipeline` build. If it's already run, jump to step TODO
2. If it hasn't run yet, kick it off.
3. If the build begins to checkout source code and building the app, then jump to step 4. If the build just hangs for a minute or so, with a message like "[Pipeline] node Still waiting to schedule task Waiting for next available executor:", you need to redeploy Jenkins (via the UI by clicking on the Jenkins deployment or via CLI). What's happened here is that Jenkins was deployed before the `mvn-build-pod` slave image it needs was finished building. Jenkins will automatically pick it up on reboot. Go back to step 1.
4. The build should finish compiling the app, deploying to nexus, and creating a container image. It should now wait for confirmation to deploy the image. Approve the deployment and check that the deployment in the `Dev` project completes. If it fails, then something is wrong - open a ticket in this repo.
5. Navigate to the Nexus webpage and click on browse -> components -> labs-snapshots. You should see an entry for "automation-api." If not, something went wrong - open a ticket in this repo.

If you prefer, you can actually do the above steps by using the new Builds -> Pipelines tab in OpenShift Console as well. Be advised that "Build #1" in the Pipelines view may show build started, when in fact the build is not running because Jenkins redeployed. In this case, you'll need to kick a new build.

## Conventions in Use

- 2 BuildConfig per app. One for s2i and one for a pipeline. They should be named `foo-app` and `foo-app-pipeline`
- 3 projects in user, all prefixed with a common identifier e.g. `labs-dev`, `labs-test`, `labs-uat`
- use environment variables set by OCP and Jenkins to obtain info like tokens, namespace etc. instead of setting it yourself in pipeline script
- TODO: probably others

## Known Issues

- Jenkins deploys once immediately after instantiation, and then once as the Jenkins s2i build completes. Expected behavior is that deployment waits until s2i build populates image stream. Somehow the imagestream is being populated before. This causes pipeline build objects in OCP to start and then never complete, so "build #1" always shows "running" in the UI. Forces end user to kick builds back off in Jenkins, instead of having them automagically run.
