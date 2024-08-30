#!/bin/sh

set -xe

. ./clean.sh

./clean.sh

javac $GAME_DIR/Main.java && java Main
