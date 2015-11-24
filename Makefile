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

uninstall:
	rm -v /usr/share/DroidQuest/chips/*
	rm -v /usr/share/DroidQuest/$(JAR)
	rmdir -v /usr/share/DroidQuest/chips
	rmdir -v /usr/share/DroidQuest
	rm -v /usr/bin/$(BIN)

clean:
	rm -vR -f *.o *.~ target/classes/ target/maven-archiver target/maven-status
