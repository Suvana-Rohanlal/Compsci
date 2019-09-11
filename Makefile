JAVAC=/usr/bin/javac

.SUFFIXES: .java .class
bin/%.class: src/%.java
	javac -d bin -sourcepath src $<
.java.class:
	$(JAVAC) $<

classes: bin/Vector.class bin/CloudData.class bin/Time.class bin/ParallelCloud.class bin/SequentialCloud.class bin/TestCloud.class

default: $(CLASSES)

run:
	java -cp bin/ TestCloud

clean:
	rm bin/*.class
	
java_doc:
	javadoc -d doc src/*.java
