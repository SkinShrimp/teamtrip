#!/bin/bash

# you can use this shell to commit dev branch or master
# example: master -->  sh submit.sh master 'add:hello.txt'
# example: branch -->  sh submit.sh dev 'add:hello.txt'

echo '---------before status------------'

git status

echo '----------------------------------'

git add .

git commit -m $2

# check is master or other branch
if [ $1 != 'master' ];then
echo '========change dev to master=========='
git checkout master
git pull origin master
git merge $1
fi

git push origin master

echo '+---------Result-----------------+'
echo '+                                                +'
echo '+       Finish Successfully            +'
echo '+--------------------------------+'
