#!/bin/bash

# for development, it may help to ln -s a local set of roles instead of cloning them from the master repo
# for everyone else, this makes it easy

if [ ! -L roles ]; then
  rm -rf ansible-stacks roles
fi

if [ ! -d roles ]; then
  git clone https://github.com/rht-labs/ansible-stacks.git
  cd ansible-stacks
  cd ..
  mv ansible-stacks/roles .
fi


if [ -f vars/openshift-vars.json ] || [ -f vars/openshift-vars.yaml ] || [ -f vars/openshift-vars.yml ]; then

  # the user has provided an openshift vars file for us - select the playbook that will use it
  ansible-playbook playbook-with-include-vars.yml -i inventory_cluster

else

  # prompt the user for openshift vars
  ansible-playbook playbook-with-vars-prompt.yml -i inventory_cluster

fi

