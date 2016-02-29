@ECHO OFF
del /F src\test\resources\features\processed\*.feature
copy /Y src\test\resources\properties\TestConfig-LocalChrome.properties src\test\resources\properties\TestConfig.properties
mvn clean test -Dcucumber.options="--tags '@brands'"
