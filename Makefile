JFLAGS = -g
JC = javac
JVM= java 

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

MAIN = Fenetre
CLASSES = \
	Forme.java \
	Fenetre.java \
	MatrixRec.java \
	Optimiseur.java \
	Panneau.java \
	Parser.java \
	Point.java \
	Rectangle.java \
	Rouleau.java

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class
