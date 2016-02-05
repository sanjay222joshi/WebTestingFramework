#!/bin/bash
rm -f src/test/resources/features/processed/*.feature
cp -f src/test/resources/properties/TestConfig-LocalFirefox.properties src/test/resources/properties/TestConfig.properties
mvn clean test