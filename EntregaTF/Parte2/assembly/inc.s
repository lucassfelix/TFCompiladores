.text

#	Guido Mainardi-18106136
#	Lucas Felix-18108826
#	Pedro Wagner-18106192
#	Renata Rittmann-18110282
#

.GLOBL _start


_start:
	PUSHL $1
	POPL %EDX
	MOVL %EDX, _m
	PUSHL %EDX
	POPL %EDX
	MOVL $_str_0Len, %EDX
	MOVL $_str_0, %ECX
	CALL _writeLit
	PUSHL _m
	POPL %EAX
	CALL _write
	CALL _writeln
	PUSHL $1
	PUSHL _m
	POPL %EBX
	POPL %EAX
	ADDL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL %EDX, _m
	PUSHL %EDX
	POPL %EDX
	MOVL $_str_1Len, %EDX
	MOVL $_str_1, %ECX
	CALL _writeLit
	PUSHL _m
	POPL %EAX
	CALL _write
	CALL _writeln
	MOVL $_str_2Len, %EDX
	MOVL $_str_2, %ECX
	CALL _writeLit
	PUSHL _m
	PUSHL _m
	POPL %EDX
	ADDL $1, %EDX
	MOVL %EDX, _m
	POPL %EAX
	CALL _write
	CALL _writeln
	MOVL $_str_3Len, %EDX
	MOVL $_str_3, %ECX
	CALL _writeLit
	PUSHL _m
	POPL %EDX
	ADDL $1, %EDX
	PUSHL %EDX
	MOVL %EDX, _m
	POPL %EAX
	CALL _write
	CALL _writeln
	PUSHL $1
	POPL %EDX
	MOVL %EDX, _m
	PUSHL %EDX
	POPL %EDX
	PUSHL _m
	POPL %EDX
	ADDL $1, %EDX
	PUSHL %EDX
	MOVL %EDX, _m
	PUSHL _m
	POPL %EBX
	POPL %EAX
	ADDL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL %EDX, _m
	PUSHL %EDX
	PUSHL _m
	PUSHL _m
	POPL %EDX
	ADDL $1, %EDX
	MOVL %EDX, _m
	POPL %EBX
	POPL %EAX
	ADDL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL $_str_4Len, %EDX
	MOVL $_str_4, %ECX
	CALL _writeLit
	PUSHL _m
	POPL %EAX
	CALL _write
	CALL _writeln



#
# devolve o controle para o SO (final da main)
#
	mov $0, %ebx
	mov $1, %eax
	int $0x80


#
# Funcoes da biblioteca (IO)
#


_writeln:
	MOVL $__fim_msg, %ECX
	DECL %ECX
	MOVB $10, (%ECX)
	MOVL $1, %EDX
	JMP _writeLit
_write:
	MOVL $__fim_msg, %ECX
	MOVL $0, %EBX
	CMPL $0, %EAX
	JGE _write3
	NEGL %EAX
	MOVL $1, %EBX
_write3:
	PUSHL %EBX
	MOVL $10, %EBX
_divide:
	MOVL $0, %EDX
	IDIVL %EBX
	DECL %ECX
	ADD $48, %DL
	MOVB %DL, (%ECX)
	CMPL $0, %EAX
	JNE _divide
	POPL %EBX
	CMPL $0, %EBX
	JE _print
	DECL %ECX
	MOVB $'-', (%ECX)
_print:
	MOVL $__fim_msg, %EDX
	SUBL %ECX, %EDX
_writeLit:
	MOVL $1, %EBX
	MOVL $4, %EAX
	int $0x80
	RET
_read:
	MOVL $15, %EDX
	MOVL $__msg, %ECX
	MOVL $0, %EBX
	MOVL $3, %EAX
	int $0x80
	MOVL $0, %EAX
	MOVL $0, %EBX
	MOVL $0, %EDX
	MOVL $__msg, %ECX
	CMPB $'-', (%ECX)
	JNE _reading
	INCL %ECX
	INC %BL
_reading:
	MOVB (%ECX), %DL
	CMP $10, %DL
	JE _fimread
	SUB $48, %DL
	IMULL $10, %EAX
	ADDL %EDX, %EAX
	INCL %ECX
	JMP _reading
_fimread:
	CMPB $1, %BL
	JNE _fimread2
	NEGL %EAX
_fimread2:
	RET



#
# area de dados
#
.data
#
# variaveis globais
#
_m:	.zero 4

#
# area de literais
#
__msg:
	.zero 30
__fim_msg:
	.byte 0


_str_0:
	 .ascii "m = "
_str_0Len = . - _str_0
_str_1:
	 .ascii "m += 1: "
_str_1Len = . - _str_1
_str_2:
	 .ascii "m++ :"
_str_2Len = . - _str_2
_str_3:
	 .ascii "++m :"
_str_3Len = . - _str_3
_str_4:
	 .ascii "m (5 ou 7):"
_str_4Len = . - _str_4
