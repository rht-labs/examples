FROM registry.access.redhat.com/openshift3/jenkins-slave-base-rhel7

MAINTAINER RHT Ambassadors <rh-labs-ambassadors@redhat.com>

COPY contrib/bin/setup_6.x /usr/local/bin/setup_6.x

RUN yum repolist --disablerepo=* && \
    yum-config-manager --disable \* > /dev/null && \
    yum-config-manager --enable rhel-7-server-rpms > /dev/null && \
    yum-config-manager --enable rhel-7-server-optional-rpms > /dev/null \
    yum-config-manager --enable rhel-7-server-extras-rpms > /dev/null

RUN chmod 755 /usr/local/bin/setup_6.x && \
    /usr/local/bin/setup_6.x && \
    yum update -y && \
    yum install -y nodejs && \
    yum clean all -y

RUN npm install -g @angular/cli

RUN chmod 777 /home/jenkins/.npm

RUN chown -R 1001:0 $HOME && \
    chmod -R g+rw $HOME

USER 1001
