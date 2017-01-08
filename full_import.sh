#!/bin/bash
#mvn install && ./grape_clear.sh 1.0-SNAPSHOT && ./import_data_from_small_business.groovy --url http://localhost:3000 `echo $*`
mvn clean install -Dexec.args="--url http://localhost:3000 `echo $*`"
