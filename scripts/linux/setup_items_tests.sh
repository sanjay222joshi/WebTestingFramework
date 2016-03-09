#!/usr/bin/env bash
echo "RUNNING THE SETUP FOR THE ITEMS FEATURES"
mvn clean test -Dcucumber.options="--tags @setup --tags @items"