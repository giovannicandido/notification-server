#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" postgres  <<-EOSQL
    CREATE DATABASE notification_test;
EOSQL