#!/bin/bash

git clone https://github.com/rht-labs/ansible-stacks.git
mv ansible-stacks/roles .
ansible-playbook jenkins.yml -i inventory_cluster
