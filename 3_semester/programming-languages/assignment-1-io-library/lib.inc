; #####################################################################################
; ###   Не судите строго, я только перевелся, у меня же не было лаб по БЭВМ......   ###
; #####################################################################################

%define SYS_EXIT    60
%define SYS_READ    0
%define SYS_WRITE   1

%define STDOUT      1
%define STDIN       0

%define LINE_FEED   0xA
%define TABULATION  0x9
%define SPACE       0x20
section .text


; Принимает код возврата и завершает текущий процесс
exit:
    mov rax, SYS_EXIT
    syscall 

; Принимает указатель на нуль-терминированную строку, возвращает её длину
string_length: ; +++
    ; rdi - указатель на строку
    xor rax, rax          ; пишем в rax 0
    .loop:
        cmp byte[rdi+rax], 0    ; сравниваем каждый байт с 0
        je .end                 ; если 0 это конец строки => конец
        inc rax                 ; добавляем в rax +1
        jne .loop               ; если не равен нулю, то в начало цикла
    .end:
        ret
    

; Принимает указатель на нуль-терминированную строку, выводит её в stdout
print_string: ; +++
    ; rdi - указатель на строку
    xor rax, rax
    push rdi                    ; сохраняем строку в стек
    call string_length          ; вызываем функцию которая нам запишет длинну в rax
    mov rdx, rax                ; пишем длинну из rax в rdx
    mov rdi, STDOUT                  ; пишем дескриптор stdout
    mov rax, SYS_WRITE          ; пишем в rax код sys_write
    pop rsi                     ; забираем в rsi нашу строку из стека
    syscall
    ret

; Принимает код символа и выводит его в stdout
print_char: ; +++
    ; rdi - код символа 
    push rdi            ; сохраняем символ в стек
    
    mov rax, SYS_WRITE  ; ставим syscall код вывода 
    mov rdx, STDOUT     ; stdout дескриптор
    mov rsi, rsp        ; сообщение для вывода  
    mov rdi, 1          ; длинна сообщение
    
    syscall
    pop rdi
    ret
; Переводит строку (выводит символ с кодом 0xA)
print_newline: ; +++
    mov rdi, LINE_FEED
    jmp print_char
    

; Выводит беззнаковое 8-байтовое число в десятичном формате
; Совет: выделите место в стеке и храните там результаты деления
; Не забудьте перевести цифры в их ASCII коды.
print_uint:
    ; rdi -   число 
    mov r10, rsp        ; ставим указатель стека на r10 (тут будет наше число строка)
    mov rax, rdi        ; пишем в rax наше число 
    mov rdi, 10         ; делитель 

    push 0

    .loop:
        mov rdx, 0      ; очищаем остаток 
        div rdi         ; rax = rax/rdi, rdx=rax%rdi
        add dl, '0'     ; преобразуем в ascii код 
        dec rsp         
        mov [rsp], dl   ; пишем наш остаток в виде ascii кода 
        
        test rax, rax      ; если целая часть от деления = 0, то цикл закончен
        jnz .loop 

    mov rdi, rsp        ; rsp=>rdi тк print_string rdi - строка
    push r10
    call print_string   ; печатаем нашу строку
    pop rsp
    ret



; Выводит знаковое 8-байтовое число в десятичном формате
print_int:
    ; rdi - число
    cmp rdi, 0          ; если число положительное то просто его печатаем
    jge .pos 
    neg rdi 
    push rdi 
    mov rdi, '-'       ; символ -
    call print_char     ; печатаем его
    pop rdi 
    .pos:
        jmp print_uint


; Принимает два указателя на нуль-терминированные строки, возвращает 1 если они равны, 0 иначе
string_equals: ; +++
    ; rdi, rsi - два указателя на строки 
    xor rax, rax         ; пишем в rax 0, будет нашим счетчиком

    .loop:
        mov r9b, byte[rdi+rax]  ; пишем в r9b rax-ый байт первой строки
        mov r10b, byte[rsi+rax] ; пишем в r10b rax-ый байт второй строки
        inc rax                 ; rax+1
        cmp r10b,r9b            ; сравниваем байты первой и второй строки
        je .e                   ; если равны, то прыгаем в .e
        jmp .ne                 ; если нет, то в .ne
    .ne:
        xor rax, rax              ; не равны, значит возвращаем в rax 0 
        ret
    .e:
        test r10b, r10b             ; проверяем одно из значений на 0
        jnz .loop               ; если не 0, то идем дальше по строке
        mov rax, 1              ; если 0, то значит строка закончилась и возвращаем 1
        ret


; Читает один символ из stdin и возвращает его. Возвращает 0 если достигнут конец потока
read_char: ; +++     
    mov rax, SYS_READ     ; syscall код для чтения
    mov rdi, STDIN        ; stdin дескриптор
    mov rdx, 1      ; длинна чтения
    push 0          ; выделяем в стеке место
    mov rsi, rsp    ; указываем в rsi на место в стеке
    syscall         ; вызываем syscall
    pop rax         ; пишем верх стека в rax
    ret

; Принимает: адрес начала буфера, размер буфера
; Читает в буфер слово из stdin, пропуская пробельные символы в начале, .
; Пробельные символы это пробел 0x20, табуляция 0x9 и перевод строки 0xA.
; Останавливается и возвращает 0 если слово слишком большое для буфера
; При успехе возвращает адрес буфера в rax, длину слова в rdx.
; При неудаче возвращает 0 в rax
; Эта функция должна дописывать к слову нуль-терминатор

read_word: ; +++
    ; rdi - буфер
    ; rsi - размер
    xor rax, rax        ; пишем 0 в rax
    push r12        ; сохраним наши callee-saved регистры
    push r13
    push r14
    mov r12, rdi     ; сохраним тут наш буфер
    mov r13, rsi     ; и размер тоже
    xor r14, r14       ; будет нашим счетчиком
    
    .loop:
        cmp r14, r13    ; проверяем на переполнение 
        jge .overflow   ; если переполнен прыгаем на .overflow

        call read_char  ; считываем одну букву

        cmp rax, TABULATION   ; после выполнения read_char наш символ хранится в rax, проверяем является ли он пробельным
        je .space        ; если да, то прыгаем в начало цикла
        cmp rax, LINE_FEED
        je .space
        cmp rax, SPACE
        je .space 

        mov [r12+r14], rax ; пишем в буфер символ
        
        test rax, rax      ; смотрим, конец ли строки 
        jz .end         ; если да, то прыгаем в конец
        inc r14         ; rcx+=1
        jmp .loop       ; в начало цикла

    .space:
        test r14, r14      ; если символы уже были
        jnz .end        ; идем в конец
        jmp .loop       ; иначе в начало цикла
    
    .end:               ; если конец строки и все ок, то возвращаем все согласно заданию
        mov rdx, r14
        mov rax, r12
        jmp .restore

    .overflow:           ; если переполнение, то возвращаем 0
        xor rax, rax
        jmp .restore

    .restore:   ; восстановим наши регистры назад
        pop r14
        pop r13
        pop r12
        ret

; Принимает указатель на строку, пытается
; прочитать из её начала беззнаковое число.
; Возвращает в rax: число, rdx : его длину в символах
; rdx = 0 если число прочитать не удалось
parse_uint:
    ;rdi - число
    xor rcx, rcx      ; будет нашим счетчиком
    xor rax, rax      ; результат умножения 
    mov r10, 10     ; множитель 10
    xor r9, r9      
    .loop:
        movzx r9, byte[rdi+rcx]     ; кладем в r9 каждый рассматриваемый байт
        sub r9, '0'                 ; переводим его из ascii кода 
        cmp r9, 0                   ; смотрим цифра ли это [0-9]
        jl .end 
        cmp r9, 9
        jg .end 
        mul r10                     ; rax = rax * rbx
        add rax, r9                 ; rax = rax + r9
        inc rcx                     ; увеличиваем счетчик
        jmp .loop 
    .end:
        mov rdx, rcx                ; возвращаем в rdx длинну 
        ret




; Принимает указатель на строку, пытается
; прочитать из её начала знаковое число.
; Если есть знак, пробелы между ним и числом не разрешены.
; Возвращает в rax: число, rdx : его длину в символах (включая знак, если он был)
; rdx = 0 если число прочитать не удалось

parse_int:
    xor rax, rax
    mov r9b, byte[rdi]

    cmp r9b, '-'   ; смотрим является ли первый символ минусом 
    je .neg 
    jne .pos 

    .neg:
        inc rdi ; со второго символа (без минуса что бы)
        call parse_uint     
        test rdx, rdx  ; если rdx 0 то не получилось => ret
        jz .end
        inc rdx     ; прибавляем 1 к rdx тк минус
        neg rax
        jmp .end
    .pos:
        jmp parse_uint

    .end:
        ret

; Принимает указатель на строку, указатель на буфер и длину буфера
; Копирует строку в буфер
; Возвращает длину строки если она умещается в буфер, иначе 0
string_copy: ; +++
    ; rdi - указатель на строку
    ; rsi - указатель на буфер
    ; rdx - длинна строки 
    xor rcx, rcx  ; пишем 0 в rcx, будет нашим счетчиком

    .loop: 
        cmp rcx, rdx    ; сравниваем с длинной буфера
        jge .overflow   ; если переполнилось то прыгаем в overflow
        mov r9b, byte[rdi+rcx]  ; храним каждый символ строки в r9b
        mov [rsi+rcx], r9b      ; пишем символ в буфер
        test r9b, r9b      ; если конец строки
        jz .end         ; прыгаем в end
        inc rcx         ; rcx +=1
        jmp .loop       ; в начало цикла

    .overflow:          ; возвращаем 9 при переполнении
        xor rax, rax
        ret 

    .end:               ; если все ок возвращаем длину строки 
        mov rax, rcx
        ret
