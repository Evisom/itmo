ORG 0x2FB
BEGIN: CLA
       ST 0xE18
       LD 0xE15
       PUSH
       CALL 0x6c0
       POP
       SUB  0xE13
       ST   0xE12
       LD   0xE10
       PUSH
       CALL 0x6c0
       POP
       DEC
       SUB  &0x30C
       ST   0xE0B
       LD   0xE07
       PUSH
       CALL 0x6C0
       POP
       INC
       ADD  0xE05
       ST   0xE04
       HLT
	   WORD 0x0001
	   WORD 0x0001
	   WORD 0x0001
	   WORD 0xFA94
ORG 0x6C0
F:    LD 0xC01
      BPL 0x08
      SUB 0xE0A
      BMI 0x06
      BEQ 0x05
      ADD 0xE07
      ADD &0xC01
      ADD &0xC01
      ADD 0xE05
      JUMP 0xe01
      LD 0xe02
      ST &0x1
      RET
      WORD 0xfa94
      WORD 0x00e1