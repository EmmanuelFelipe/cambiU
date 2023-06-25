#!/bin/bash

echo "Executando secrets.sh"
ACCESSKEY=$AWS_ACCESSKEY
SECRETKEY=$AWS_SECRETKEY

# Faz a substituição no arquivo application.properties usando sed
sed -i "s/APP_AWS_ACCESSKEY/$ACCESSKEY/g" application.properties
sed -i "s/APP_AWS_SECRETKEY/$SECRETKEY/g" application.properties

# Faz a substituição no arquivo Dockerfile usando sed
sed -i "s/SUB_APP_AWS_ACCESSKEY/$ACCESSKEY/g" Dockerfile
sed -i "s/SUB_APP_AWS_SECRETKEY/$SECRETKEY/g" Dockerfile
echo "Sucesso"
