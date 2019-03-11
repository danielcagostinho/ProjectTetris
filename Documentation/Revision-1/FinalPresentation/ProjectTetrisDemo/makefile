all: classes run manifest

run: $(classes)
	chmod 754 run.sh

classes:
	javac *.java

manifest: $(classes)
	echo "Manifest-Version: 1.0" > Manifest.txt
	echo "Class-Path: ." >> Manifest.txt l
	echo "Main-Class: ProjectTetris" >> Manifest.txt
	jar cfm ProjectTetris.jar Manifest.txt *.class
clean:
	rm *.class
	rm *.jar
	rm Manifest.txt
