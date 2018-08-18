#!/bin/bash

origin=`grep "TOOL_VERSION" gradle.properties | awk '{print $1}'`
notNeedChange=`grep "TOOL_VERSION" gradle.properties | cut -d '.' -f 1`
secondVersion=`grep "TOOL_VERSION" gradle.properties | cut -d '.' -f 2`
lastVersion=`grep "TOOL_VERSION" gradle.properties | cut -d '.' -f 3 `

str=${notNeedChange}"."${secondVersion}"."$[lastVersion+1]
echo ${str}

sed -i "" "s/${origin}/${str}/g" gradle.properties
#./gradlew clean build bintrayUpload -PbintrayUser=296777513 -PbintrayKey=0e35550c9145602f043f4cb98f2a12b9a6bbb98f -PdryRun=false
echo "begin upload please wait!!!~~~"

uploadStr=`./gradlew clean build bintrayUpload -PbintrayUser=296777513 -PbintrayKey=0e35550c9145602f043f4cb98f2a12b9a6bbb98f -PdryRun=false 2>&1`

echo ${uploadStr}

if [[ ${uploadStr} =~ "BUILD SUCCESSFUL" ]]
then
    echo "========UPLOAD SUCCESS "
    git status
    git add ./gradle.properties
    git commit -m "feature: upload version code $str"
else
    echo "SUCCESS~~~~"
    sed -i "" "s/${str}/${origin}/g" gradle.properties
 fi
