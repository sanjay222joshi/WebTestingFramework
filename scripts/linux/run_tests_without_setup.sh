#!/usr/bin/env bash
echo "RUNNING THE TESTS WITHOUT THE SETUP"
mvn clean test -Dcucumber.options="--tags ~@setup"