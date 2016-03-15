#!/usr/bin/env bash
./scripts/linux/create_testdata_from_template.sh
./scripts/linux/copy_localhtmlunit_properties.sh
./scripts/linux/delete_processed_features.sh
