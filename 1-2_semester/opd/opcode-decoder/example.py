class BasicComputer:
    def __init__(self):
        self.ram = [0x0]*2048
        self.ac = 0x0

    def CLA(self):
        self.ac = 0x0

    def ADD(self, cell):
        self.ac += self.ram[cell]

    def SUB(self, cell):
        self.ac -= self.ram[cell]

    def ST(self, cell):
        self.ram[cell] = self.ac

    def LD(self, cell):
        self.ac = self.ram[cell]

    def AND(self, cell):
        self.ac = self.ac & self.ram[cell]

    def OR(self, cell):
        self.ac = self.ac | self.ram[cell]

    def SETRAM(self, cell, val):
        self.ram[cell] = val

    def dump(self):
        print("--------BasicPC DUMP--------")
        print('[AC]  ' , hex(self.ac))
        for i in range(0, len(self.ram)):
            if self.ram[i] != 0x0:
                print('['+str(hex(i))+'] '+ str(hex(self.ram[i])))
        print("----------------------------")

def program1():
    pc = BasicComputer()

    pc.SETRAM(0x058, 0x4070)
    pc.SETRAM(0x059, 0x3071)
    pc.SETRAM(0x06e, 0xe070)
    pc.SETRAM(0x06f, 0xa073)
    pc.SETRAM(0x070, 0x3070)
    pc.SETRAM(0x071, 0xe070)
    pc.SETRAM(0x072, 0x0200)
    pc.SETRAM(0x073, 0x6058)

    pc.CLA()
    pc.ADD(0x72)
    pc.SUB(0x058)
    pc.ST(0x070)
    pc.LD(0x073)
    pc.AND(0x070)
    pc.ST(0x070)
    pc.CLA()
    pc.SUB(0x06f)
    pc.ADD(0x070)
    pc.ST(0x070)
    pc.CLA()
    pc.OR(0x071)
    pc.OR(0x070)
    pc.ST(0x070)
    pc.CLA()
    # print(pc.ac)
    pc.SUB(0x06e)
    # print(pc.ac, pc.ram[0x070])
    pc.ADD(0x070)
    # print(pc.ac)
    pc.ST(0x059)
    pc.dump()

program1()

def program2():
    pc = BasicComputer()
    pc.dump()
    pc.SETRAM(0x058, 0x4070)
    pc.SETRAM(0x059, 0x3071)
    pc.SETRAM(0x06e, 0xe070)
    pc.SETRAM(0x06f, 0xa073)
    pc.SETRAM(0x070, 0x3070)
    pc.SETRAM(0x071, 0xe070)
    pc.SETRAM(0x072, 0x0200)
    pc.SETRAM(0x073, 0x6058)

    pc.CLA()
    pc.ADD(0x072)
    pc.SUB(0x058)
    pc.ST(0x070)
    pc.LD(0x073)
    pc.AND(0x070)
    pc.SUB(0x06f)
    pc.OR(0x071)
    pc.SUB(0x06e)
    pc.ST(0x059)


    pc.dump()

print("  ")
program2()

