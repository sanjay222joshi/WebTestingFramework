#!/usr/bin/env bash
echo "OVERWRITING THE TESTCONFIG PROPERTIES FILE WITH THE LOCAL CHROME WEBDRIVER"
cp -f src/test/resources/properties/TestConfig-LocalChrome.properties src/test/resources/properties/TestConfig.properties