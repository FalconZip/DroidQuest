CC = gcc
MV = mvn

CFLAGS=-Wall -Werror -ggdb

BIN=DroidQuest
JAR=dq-2.7.jar

all:
	$(CC) $(CFLAGS) dq.c -o dq.o
	$(CC) $(CFLAGS) dq.c -o $(BIN)
	$(MV) install

install:
	mkdir -vp /usr/share/DroidQuest/chips
	cp -v src/main/resources/chips/* /usr/share/DroidQuest/chips/
	cp -v target/$(JAR) /usr/share/DroidQuest/$(JAR)
	cp -v $(BIN) /usr/bin/$(BIN)
	cp -v DroidQuest.png /usr/share/pixmaps/DroidQuest.png
	cp -v DroidQuest.desktop /usr/share/applications/DroidQuest.desktop

uninstall:
	rm -v /usr/share/DroidQuest/chips/*
	rm -v /usr/share/DroidQuest/$(JAR)
	rmdir -v /usr/share/DroidQuest/chips
	rmdir -v /usr/share/DroidQuest
	rm -v /usr/bin/$(BIN)
	rm -v /usr/share/pixmaps/DroidQuest.png
	rm -v /usr/share/applications/DroidQuest.desktop

clean:
	rm -vR -f *.o *.~ target DroidQuest
