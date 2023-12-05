from Table import TableString, extCode
from Code import Code

file = open('input.txt').readlines()
rfile = open('out.txt', 'a')
# TableString('MEM', 'CODE', 'CMD', 'OPERAND').render()
# print('-----------+------------+-------------+------------')
r = ''
for s in file:
    s = s.split()
    if (len(s) != 2):
        print('Invalid line!')
    else:
        cell = s[0]
        command = Code(s[1])
        r+=command.command + ' ' + command.operand + '\n'
        TableString(cell, command.code, command.command, command.operand).render()

rfile.write(r)
rfile.close()