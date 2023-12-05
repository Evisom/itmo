%include "lib.inc"

global find_word

%define CELL_SIZE 8
find_word:
    ; rdi - строка 
    ; rsi - начало словаря
    ; => rax 0/адрес
    mov r12, rsi
    .loop:
        test r12, r12  ; смотрит равно ли нулю, если 0 => конец словаря
        jz .not_found 

        mov rsi, r12
        add rsi, CELL_SIZE
        call string_equals ; сравниваем равна ли строка с искомой

        test rax, rax
        jnz .found 

        mov r12, [r12]
        jmp .loop

    .found:
        mov rax, r12
        ret
    .not_found:
        xor rax, rax
        ret