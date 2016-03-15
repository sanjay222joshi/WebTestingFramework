#!/usr/bin/env bash
./scripts/linux/copy_localhtmlunit_properties.sh
./scripts/linux/delete_processed_features.sh
./scripts/linux/copy_testdata_from_external_items_data_folder.sh
./scripts/linux/copy_features_from_external_items_features_folder.sh