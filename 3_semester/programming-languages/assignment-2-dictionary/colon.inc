%define pointer 0

%macro colon 2
	%2: dq pointer
	db %1, 0
	; выделяет 8 байт (запишу пока тут что бы не забыть)
	%define pointer %2
%endmacro
