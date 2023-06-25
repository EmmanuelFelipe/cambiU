#!/bin/bash

# Pega as variáveis do ambiente
ACCESSKEY=$AWS_ACCESSKEY
SECRETKEY=$AWS_SECRETKEY

# Faz a substituição no arquivo usando sed
sed -i "s/APP_AWS_ACCESSKEY/$ACCESSKEY/g" aplication.properties
sed -i "s/APP_AWS_SECRETKEY/$SECRETKEY/g" aplication.properties

sed -i "s/${AWS_ACCESSKEY}/$ACCESSKEY/g" Dockerfile
sed -i "s/${AWS_SECRETKEY}/$SECRETKEY/g" Dockerfile
