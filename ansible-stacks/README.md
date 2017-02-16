# ansible-stacks
A set of example definitions that can be used with the [ansible-stacks](https://github.com/rht-labs/ansible-stacks) tooling. 

## Loading up a local .json file
* Clone the ansible-stacks repo, or use a stable release.
```
> git clone https://github.com/rht-labs/ansible-stacks
``` 
* Prepare for execution
 * Install `ansible`
 * Get the `oc` client and make it availble locally, e.g.: add it to ~/bin and set `export PATH=$PATH:~/bin`
 * As `system:admin` on the OpenShift cluster, grant the proper admin rights to the user, e.g.: `oadm policy add-cluster-role-to-user cluster-admin joe`

* Prepare a .json file, e.g.: based on the labs-env-example.json found in this directory and add it to the `playbooks` directory of `ansible-stacks`, and use the `playbooks/load_infra_local.json` to load it up. In this example, the user `joe` with password `password` is used. 

*NOTE: The user used to run the playbook needs to have the proper admin rights in the OpenShift cluster, e.g.: cluster-admin - see above*
```
> cd ansible-stacks/playbooks
> cp <path>/examples/ansible-stacks/labs-env-example.json infra_env.json 
> vi infra_env.json # make changes to the setup here...
> ansible-playbook -e "openshift_user=joe" -e "openshift_password=password" load_infra_local.json 
```
