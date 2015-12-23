#!/bin/bash
cd ~/Documentos/Proyectos/javafutbolee/javafutbolee/javafutbolee-web/src/main/webapp/resources

#clean
if [ "$1" == "r1" ]
then	
	rm -rf resources1.tar.gz
fi

if [ "$1" == "all" ]
then
	rm -rf resources2.tar.gz
	rm -rf resources1.tar.gz
fi

#compress

if [ "$1" == "r1" ]
then	
	tar -zcvf resources1.tar.gz dt1c/css dt1c/js primefaces-dtic_theme
fi

if [ "$1" == "all" ]
then
	tar -zcvf resources1.tar.gz dt1c/css dt1c/js primefaces-dtic_theme
	tar -zcvf resources2.tar.gz dt1c/fonts ../../resources/email
fi


#upload
if [ "$1" == "r1" ]
then	
	~/dropbox_uploader.sh upload resources1.tar.gz resources1.tar.gz
fi

if [ "$1" == "all" ]
then
	~/dropbox_uploader.sh upload resources1.tar.gz resources1.tar.gz
	~/dropbox_uploader.sh upload resources2.tar.gz resources2.tar.gz
fi
#push
git push openshift HEAD

cd ~/Documentos/Proyectos/javafutbolee/javafutbolee



