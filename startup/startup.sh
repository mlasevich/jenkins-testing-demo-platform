#!/bin/bash

if test -f /startup/plugins.txt; then
  jenkins-plugin-cli --plugin-file /startup/plugins.txt
  #/usr/local/bin/install-plugins.sh < /startup/plugins.txt
fi
exec /usr/local/bin/jenkins.sh
