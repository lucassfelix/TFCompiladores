.text

#	Guido Mainardi-18106136
#	Lucas Felix-18108826
#	Pedro Wagner-18106192
#	Renata Rittmann-18110282
#

.GLOBL _start


_start:
	PUSHL $0
	POPL %EDX
	MOVL %EDX, _num
	PUSHL %EDX
rot_04:
	PUSHL _num
	PUSHL $10
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETL  %AL
	PUSHL %EAX
	POPL %EAX   #Pula se falso...
	CMPL $0, %EAX
	JE rot_02
	JMP rot_03   # Continua caso não seja falso
rot_01:
	PUSHL _num
	PUSHL _num
	POPL %EDX
	ADDL $1, %EDX
	MOVL %EDX, _num
	JMP rot_04   # Volta para a verificação
rot_03:
	PUSHL _num
	PUSHL $10
	PUSHL _num
	POPL %EBX
	POPL %EAX
	SUBL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	PUSHL $4
	POPL %EBX
	POPL %EAX
	IMULL %EBX, %EAX
	PUSHL %EAX
	POPL %EAX
	MOVL %EDX, _nums(%EAX)
	PUSHL %EDX
	POPL %EDX
	PUSHL _num
	PUSHL $4
	POPL %EBX
	POPL %EAX
	IMULL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL _nums( %EDX ) , %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL %EDX, _a
	PUSHL %EDX
	POPL %EDX
	MOVL $_str_0Len, %EDX
	MOVL $_str_0, %ECX
	CALL _writeLit
	PUSHL _a
	POPL %EAX
	CALL _write
	CALL _writeln
		# terminou o bloco...
	JMP rot_01   # terminou cmd na linha de cima
rot_02:



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
_num:	.zero 4
_a:	.zero 4
_nums:	.zero 40

#
# area de literais
#
__msg:
	.zero 30
__fim_msg:
	.byte 0


_str_0:
	 .ascii "nums[num] = "
_str_0Len = . - _str_0
