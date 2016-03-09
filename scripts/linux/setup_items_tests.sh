#!/usr/bin/env bash
mvn clean test -Dcucumber.options="--tags @setup --tags @items"