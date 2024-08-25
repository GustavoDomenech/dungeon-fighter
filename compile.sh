#!/bin/sh

set -xe

./clean.sh
javac Main.java && java Main
