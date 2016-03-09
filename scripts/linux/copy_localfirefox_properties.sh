#!/usr/bin/env bash
echo "OVERWRITING THE TESTCONFIG PROPERTIES FILE WITH THE LOCAL FIREFOX WEBDRIVER"
cp -f src/test/resources/properties/TestConfig-LocalFirefox.properties src/test/resources/properties/TestConfig.properties