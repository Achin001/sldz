#!/usr/bin/env bash

mvn clean

git init

git add .

git commit -m “修复了为分组设置直推间推报酬报500错误”

git push origin master