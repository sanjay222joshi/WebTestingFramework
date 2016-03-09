#!/usr/bin/env bash
echo "COPYING THE PROCESSED TESTDATA EXCEL FILE TO THE TARGET FOLDER"
cp -f src/test/resources/data/TestData.xlsx target/TestData-$(date +"%m%d%y_%H%M").xlsx