# How to run:
#	as -o loop_out.o loop_out.asm
#	ld -o loop_out loop_out.o
#	./loop_out

.intel_syntax
.data
num1:           .long   1
num2:           .long   2

.text
.global         _start          
_start:
main:           mov     %eax, 0
loop:           add     %eax, [num2]
                sub     %eax, [num1]
                cmp     %eax, 10
                jl      loop
                add     %eax, 123
                imul    %eax, [num2]
                mov     %eax, 0
loop2:          add     %eax, 2
                sub     %eax, [num1]
                cmp     %eax, 5
                jl      loop2
halt:           mov     %eax, 0x1
                mov     %ebx, 0x0
                int     0x80
                mov     %eax, 0x1
                mov     %ebx, 0x0
                int     0x80
