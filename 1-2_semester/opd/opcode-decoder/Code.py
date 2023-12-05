import re

opcodes_dict = {
    "0000" : "NOP",
    "0100" : "HLT",
    "0200" : "CLA",
    "0280" : "NOT",
    "0300" : "CLC",
    "0380" : "CMC",
    "0400" : "ROL",
    "0480" : "ROR",
    "0500" : "0480",
    "0580" : "ASR",
    "0600" : "SXTB",
    "0680" : "SWAB",
    "0700" : "INC",
    "0740" : "DEC",
    "0780" : "NEG",
    "0800" : "POP",
    "0900" : "POPF",
    "0A00" : "RET",
    "0B00" : "IRET",
    "0C00" : "PUSH",
    "0D00" : "PUSHF",
    "0E00" : "SWAP",
    "10.." : "DI",
    "11.." : "EI",
    "12.." : "IN",
    "13.." : "OUT",
    "18.." : "INT",
    "2..." : "AND",
    "3..." : "OR",
    "4..." : "ADD",
    "5..." : "ADC",
    "6..." : "SUB",
    "7..." : "CMP",
    "8..." : "LOOP",
    "9..." : " ",
    "A..." : "LD",
    "B..." : "SWAM",
    "C..." : "JUMP",
    "D..." : "CALL",
    "E..." : "ST",
    "F0.." : "BEQ",
    "F1.." : "BNE",
    "F2.." : "BMI",
    "F3.." : "BPL",
    "F4.." : "BHIS",
    "F5.." : "BLO",
    "F6.." : "BVS",
    "F7.." : "BVC",
    "F8.." : "BLT",
    "F9.." : "BGE",
    "FA.." : " "
}

opcodes = list(opcodes_dict.keys())

class Code:
    def __init__(self, hexcode: str):
        self.code = hexcode
        self.type = None
        self.operand = ""
        self.command = None

        if hexcode[0] == "0":
            self.type = 0
        elif hexcode[0] == "1":
            self.type=1
        elif hexcode[0].lower() in "abcde23456789":
            self.type=2
        elif hexcode[0].lower() == "f":
            self.type=3
        else:
            self.command = ""
            print("Error! " + hexcode + " is not valid!")

        for i in opcodes:
            m = re.match(i, self.code)
            if m:
                self.command =opcodes_dict[i]
                break
        # print(self.code)


        if self.type == 1:
            if self.command != "DI" and self.command != "EI":
                self.operand = self.code[2:]
        elif self.type == 2:
            self.operand = self.code[1:]
        elif self.type == 3:
            self.operand = self.code[2:]
        if self.command == " ":
            self.operand = ""
    def info(self):
        print(self.command)


