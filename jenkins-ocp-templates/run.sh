#!/bin/bash

# for development, it may help to ln -s a local set of roles instead of cloning them from the master repo
# for everyone else, this makes it easy
if [ ! -d roles ]; then
  git clone https://github.com/sherl0cks/ansible-stacks.git
  cd ansible-stacks
  git checkout template-processing
  cd ..
  mv ansible-stacks/roles .
fi

ansible-playbook jenkins-playbook.yml -i inventory_cluster
