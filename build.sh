#!/bin/bash

XML_FILES="$(pwd)/XMLfiles"
# compile all java files  
javac $(pwd)/*.java


echo ""
echo "--> Test program with valid file"
java -cp $(pwd) Main -f "$XML_FILES/normal-file.arxml"

echo ""
echo "--> Test program with invaild file"
java -cp $(pwd) Main -f "$XML_FILES/invalid-file"

echo ""
echo "--> Test program with empty file"
java -cp $(pwd) Main -f "$XML_FILES/empty-file.arxml"

echo ""
echo "--> Test program with empty autosar file"
java -cp $(pwd) Main -f "$XML_FILES/empty-autrm-file.arxml"