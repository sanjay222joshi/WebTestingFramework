#!/usr/bin/env bash
echo "RUNNING THE SETUP FOR THE BRANDS FEATURES"
mvn clean test -Dcucumber.options="--tags @setup --tags @brands"