#!/bin/bash

if [ -z "$TOMCATPWD" ] || [ -z "$TOMCATUSER" ]; then
	echo "Die Variablen TOMCATPWD und TOMCATUSER müssen gesetzt sein. Ggf. einmal 'export TOMCATUSER=\"tusp\"', 'export TOMCATPWD=\"***\"' ausführen"
	exit
fi

PWD=$TOMCATPWD
USER=$TOMCATUSER

if [ "$1" == "prod" ]; then
SRVS=(tune-prod-app05.rz.tu-harburg.de:8080 tune-prod-app06.rz.tu-harburg.de:8080)
else
	if [ "$1" == "tst" ]; then
        SRVS=(tune-tst-app01.rz.tu-harburg.de:8080 tune-tst-app02.rz.tu-harburg.de:8080)
	else
        if [ "$1" == "dev" ]; then
                SRVS=(tune-dev-app01.rz.tu-harburg.de:8080)
        else
	        if [ "$1" == "tst2" ]; then
	                SRVS=(tune-tst-app03.rz.tu-harburg.de:8080)
	        else
                echo "Parameter 1 muss dev, tst, tst2 oder prod sein!"
                exit
            fi
        fi
	fi
fi

SRV=''

if [ -z ${2+x} ]; then
    echo "Es wurde kein *.war Files angegeben"
    exit 1
fi

WARFILE=$2

if [ ! -f $WARFILE ]; then
    echo "Die angegebene Datei '$WARFILE' existiert nicht"
    exit 2
fi

for SRV in "${SRVS[@]}"
do
    echo Deploy on $SRV
    curl http://$USER:$PWD@$SRV/manager/text/undeploy?path=/tusp
    curl -X PUT --upload-file $WARFILE http://$USER:$PWD@$SRV/manager/text/deploy?path=/tusp
#curl http://$USER:$PWD@$SRV/manager/text/reload?path=/tusp
done

echo DONE
