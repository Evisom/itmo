ORG 2FB
BEGIN: CLA
       ST 0xE18
       LD 0xE15
       PUSH
       CALL F
       POP
       SUB  0xE13
       ST   0xE12
       LD   0xE10
       PUSH
       CALL F
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
	   WORD 0x5
	   WORD 0x5
	   WORD 0x5
	   WORD 0xFA94
ORG 6C0
F:    LD 0xC01
      BPL 0x08
      SUB 0xE0A
      BMI 0x06
      BEQ 0x05
      ADD 0xE07
      ADD &0xC01
      ADD &0xC01
      ADD &0xC01
      ADD 0xE05
      JUMP 0xE01
      LD 0xE02
      ST 0xC01
      RET
      WORD 0xFA94
      WORD 0x00E1