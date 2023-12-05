%include "lib.inc"
%include "dict.inc"
%include "words.inc"

%define READ_SIZE 256
%define CELL_SIZE 8
%define SUCCESS_EXIT 0
section .bss
input: resb READ_SIZE   ; выделяем буфер размером указанным в READ_SIZE

section .rodata
READ_ERROR: db 'READ_ERROR', 0, 11     ; сообщение об ошибке чтения
NOT_FOUND: db 'NOT_FOUND', 0, 10  

section .text
global _start

_start:
    mov rdi, input  
    mov rsi, READ_SIZE
    call read_word      ; читаем слово

    test rax, rax       ; проверяем на ошибку чтения
    jz .read_error

    mov rdi, rax
    mov rsi, element1   ; начало словаря
    call find_word      ; ищем

    test rax, rax       ; проверяем нашли ли 
    jz .not_found
    
    mov rdi, rax        
    add rdi, CELL_SIZE
    call string_length  ; прибавляем длинну строки + 1 за нуль терминатор
    add rdi, rax
    add rdi, 1
    mov rsi, 1          ; поток stdout
    call print_string
    call print_newline
    jmp .ret

    .read_error:
        mov rdi, READ_ERROR 
        jmp .print_error
        
    
    .not_found:
        mov rdi, NOT_FOUND 
        jmp .print_error

    .print_error:
        mov rsi, 2      ; поток stderr
        call print_string
        jmp .ret
        
    .ret:
        mov rdi, SUCCESS_EXIT
        call exit