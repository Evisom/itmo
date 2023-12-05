from subprocess import Popen, PIPE

STDIN = [
    b'key1',
    b'key2',
    b'key3',
    b'key_that_does_not_exist',
    str.encode('long_key' + 248*'_'),
    str.encode('long_key' + 247*'_')
    ]
STDOUT = [
    b'value1\n',
    b'value2\n',
    b'value3\n',
    b'',
    b'',
    b'value_of_long_key\n',
    ]
STDERR = [
    b'',
    b'',
    b'',
    b'NOT_FOUND',
    b'READ_ERROR',
    b'',
    ]
passed = True 

print('\nRunning tests...\n')

for i in range(len(STDIN)):
    p = Popen('./main', shell=None, stdin=PIPE, stdout=PIPE, stderr=PIPE)
    d = p.communicate(STDIN[i])
    if d[0] == STDOUT[i] and d[1] == STDERR[i]:
        print('[' + str(i) +  '] ✅ Test passed')
    else:
        print('[' + str(i) +  '] ❌ Test failed')
        passed = False 
        print(d[0], d[1])

if passed:
    print('\n-------------------------')
    print('✅ All tests passed!!! ✅')
    print('-------------------------\n')