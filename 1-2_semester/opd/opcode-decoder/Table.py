addTypes = { # COMMAND AR
    "0000" : "ABSOLUTE", # [AR]
    "0001" : "ABSOLUTE", # [AR]
    "0010" : "ABSOLUTE", # [AR]
    "0101" : "ABSOLUTE", # [AR]
    "0100" : "ABSOLUTE", # [AR]
    "0111" : "ABSOLUTE", # [AR]
    "0110" : "ABSOLUTE", # [AR]
    "1000" : "IN_RELATIVE", # [[IP + AR]]
    "1010" : "INC", # [[IP + AR + 1]]
    "1011" : "DEC", # [[IP + AR - 1]]
    "1100" : "SP_RELATIVE", # [[SP + AR]]
    "1110" : "ST_RELATIVE",  # [IP+AR]
    "1111" : "STRAIGHT", # AR

    "1001" : "RESERVE",
    "1101" : "RESERVE",
}

def extCode(code):
    negative = code[0]
    if negative=="1":
        code = code[1:]
        code = code.replace("0", "O")
        code = code.replace("1", "0")
        code = code.replace("O", "1")
        return -1 * int(negative) * int(code, 2) - 1
    else:
        return int(code, 2)

class TableString:
    def __init__(self, cell,  code, command, operand):
        self.cell = cell + " "*(3-len(cell))
        self.code = code + " "*(4-len(code))
        self.command = command + " "*(5-len(command))

        if operand!="":
            self.bin = bin(int(operand, 16))[2:]
            self.addCode = self.bin[0:4]
            try:
                self.addType = addTypes[self.addCode]
                self.add = self.bin[4:]
                self.addD = extCode(self.add)
            except:
                self.addType=""
                self.add= ""
                self.addD = ""
        else:
            self.bin = ""
            self.addCode=""
            self.addType = ""
            self.add= ""
            self.addD = ""
        self.operand = operand + " "*(4-len(operand))
        self.string = f'    {self.cell}        {self.code}       {self.command}  {self.operand} {self.addCode} {self.addType} {self.add} {self.addD}'
    def render(self):
        print(self.string)