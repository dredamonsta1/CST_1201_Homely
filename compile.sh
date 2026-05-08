#!/bin/bash
mkdir -p out
find src -name "*.java" | xargs javac -d out
echo "Compiled successfully."
