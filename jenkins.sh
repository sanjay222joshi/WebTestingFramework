#!/bin/bash
rm -f src/test/resources/features/processed/*.feature
mvn clean test -Dcucumber.options="--tags '@brands'"