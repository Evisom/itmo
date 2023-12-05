ASM=nasm
ASM_FLAGS=-felf64
LINKER=ld
LINKER_FLAGS=
PYTHON=python3

all: compile

.PHONY: clean 
clean:
	rm -f *.o main

%.o: %.asm
	$(ASM) $(ASM_FLAGS) -o $@ $+

compile: main.o  lib.o dict.o 
	$(LINKER) -o main $+ $(LINKER_FLAGS)
	
test:
	$(PYTHON) test.py
