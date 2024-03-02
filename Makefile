# Set the directory path
LOX_DIR := $(CURDIR)/lox
JAVA_FILES := $(wildcard $(LOX_DIR)/**/*.java)

# Compile target
compile: classes
	@echo "Compiling Java files..."
	javac -d classes $(JAVA_FILES:.java=)

# Run target
run: classes
	@echo "Running the program..."
	cd ../ && ./gradlew run --args $(LOX_DIR)

# Default target
default: compile run

# Phony targets
.PHONY: java classes

# Java files rule
java: %.class
	@echo "Compiling Java file $<..."
	javac -d classes $<

# Classes directory
classes:
	mkdir -p classes