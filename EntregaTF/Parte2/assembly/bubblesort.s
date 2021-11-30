.text

#	Guido Mainardi-18106136
#	Lucas Felix-18108826
#	Pedro Wagner-18106192
#	Renata Rittmann-18110282
#

.GLOBL _start


_start:
	PUSHL $10
	POPL %EDX
	MOVL %EDX, _i
	PUSHL %EDX
rot_04:
	PUSHL _i
	PUSHL $0
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETG  %AL
	PUSHL %EAX
	POPL %EAX   #Pula se falso...
	CMPL $0, %EAX
	JE rot_02
	JMP rot_03   # Continua caso não seja falso
rot_01:
	PUSHL _i
	PUSHL _i
	POPL %EDX
	ADDL $-1, %EDX
	MOVL %EDX, _i
	JMP rot_04   # Volta para a verificação
rot_03:
	PUSHL _i
	PUSHL $1
	POPL %EBX
	POPL %EAX
	SUBL %EBX, %EAX
	PUSHL %EAX
	PUSHL _i
	PUSHL $1
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
	MOVL %EDX, _vetor(%EAX)
	PUSHL %EDX
	POPL %EDX
		# terminou o bloco...
	JMP rot_01   # terminou cmd na linha de cima
rot_02:
	PUSHL $0
	POPL %EDX
	MOVL %EDX, _i
	PUSHL %EDX
rot_08:
	PUSHL _i
	PUSHL $10
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETL  %AL
	PUSHL %EAX
	POPL %EAX   #Pula se falso...
	CMPL $0, %EAX
	JE rot_06
	JMP rot_07   # Continua caso não seja falso
rot_05:
	PUSHL _i
	PUSHL _i
	POPL %EDX
	ADDL $1, %EDX
	MOVL %EDX, _i
	JMP rot_08   # Volta para a verificação
rot_07:
	PUSHL _j
	PUSHL $0
	POPL %EBX
	POPL %EAX
	SUBL %EBX, %EAX
	PUSHL %EAX
rot_12:
	PUSHL _j
	PUSHL $10
	PUSHL _i
	POPL %EBX
	POPL %EAX
	SUBL %EBX, %EAX
	PUSHL %EAX
	PUSHL $1
	POPL %EBX
	POPL %EAX
	SUBL %EBX, %EAX
	PUSHL %EAX
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETL  %AL
	PUSHL %EAX
	POPL %EAX   #Pula se falso...
	CMPL $0, %EAX
	JE rot_10
	JMP rot_11   # Continua caso não seja falso
rot_09:
	PUSHL _j
	PUSHL _j
	POPL %EDX
	ADDL $1, %EDX
	MOVL %EDX, _j
	JMP rot_12   # Volta para a verificação
rot_11:
	PUSHL _j
	PUSHL $4
	POPL %EBX
	POPL %EAX
	IMULL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL _vetor( %EDX ) , %EAX
	PUSHL %EAX
	PUSHL _j
	PUSHL $1
	POPL %EBX
	POPL %EAX
	ADDL %EBX, %EAX
	PUSHL %EAX
	PUSHL $4
	POPL %EBX
	POPL %EAX
	IMULL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL _vetor( %EDX ) , %EAX
	PUSHL %EAX
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETG  %AL
	PUSHL %EAX
	POPL %EAX
	CMPL $0, %EAX
	JE rot_13
	PUSHL _j
	PUSHL $4
	POPL %EBX
	POPL %EAX
	IMULL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL _vetor( %EDX ) , %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL %EDX, _aux
	PUSHL %EDX
	POPL %EDX
	PUSHL _j
	PUSHL _j
	PUSHL $1
	POPL %EBX
	POPL %EAX
	ADDL %EBX, %EAX
	PUSHL %EAX
	PUSHL $4
	POPL %EBX
	POPL %EAX
	IMULL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL _vetor( %EDX ) , %EAX
	PUSHL %EAX
	POPL %EDX
	PUSHL $4
	POPL %EBX
	POPL %EAX
	IMULL %EBX, %EAX
	PUSHL %EAX
	POPL %EAX
	MOVL %EDX, _vetor(%EAX)
	PUSHL %EDX
	POPL %EDX
	PUSHL _j
	PUSHL $1
	POPL %EBX
	POPL %EAX
	ADDL %EBX, %EAX
	PUSHL %EAX
	PUSHL _aux
	POPL %EDX
	PUSHL $4
	POPL %EBX
	POPL %EAX
	IMULL %EBX, %EAX
	PUSHL %EAX
	POPL %EAX
	MOVL %EDX, _vetor(%EAX)
	PUSHL %EDX
	POPL %EDX
		# terminou o bloco...
	JMP rot_14
rot_13:
rot_14:
		# terminou o bloco...
	JMP rot_09   # terminou cmd na linha de cima
rot_10:
		# terminou o bloco...
	JMP rot_05   # terminou cmd na linha de cima
rot_06:
	PUSHL $0
	POPL %EDX
	MOVL %EDX, _i
	PUSHL %EDX
rot_18:
	PUSHL _i
	PUSHL $10
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETL  %AL
	PUSHL %EAX
	POPL %EAX   #Pula se falso...
	CMPL $0, %EAX
	JE rot_16
	JMP rot_17   # Continua caso não seja falso
rot_15:
	PUSHL _i
	PUSHL _i
	POPL %EDX
	ADDL $1, %EDX
	MOVL %EDX, _i
	JMP rot_18   # Volta para a verificação
rot_17:
	MOVL $_str_0Len, %EDX
	MOVL $_str_0, %ECX
	CALL _writeLit
	PUSHL _i
	PUSHL $4
	POPL %EBX
	POPL %EAX
	IMULL %EBX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL _vetor( %EDX ) , %EAX
	PUSHL %EAX
	POPL %EAX
	CALL _write
	CALL _writeln
		# terminou o bloco...
	JMP rot_15   # terminou cmd na linha de cima
rot_16:



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
_vetor:	.zero 40
_i:	.zero 4
_j:	.zero 4
_aux:	.zero 4

#
# area de literais
#
__msg:
	.zero 30
__fim_msg:
	.byte 0


_str_0:
	 .ascii " "
_str_0Len = . - _str_0
