#!/bin/bash
cd ~/Documentos/Proyectos/javafutbolee/javafutbolee/javafutbolee-web/src/main/webapp/resources

#clean
rm -rf resources1.tar.gz

if [ "$1" == "all" ]
then
	rm -rf resources2.tar.gz
fi

#compress
tar -zcvf resources1.tar.gz dt1c primefaces-dtic_theme

if [ "$1" == "all" ]
then
	tar -zcvf resources2.tar.gz fonts
fi


#upload
~/dropbox_uploader.sh upload resources1.tar.gz resources1.tar.gz
if [ "$1" == "all" ]
then
	~/dropbox_uploader.sh upload resources2.tar.gz resources2.tar.gz
fi
#push
git push openshift HEAD

cd ~/Documentos/Proyectos/javafutbolee/javafutbolee



