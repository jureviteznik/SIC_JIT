test	START	0
main	LDA	#0

.loops 10 times
loop    ADD	num2
	SUB	#1
	COMP	#10
	JLT	loop

.some random stuff
	ADD	#123
	MUL	num2
	LDA	#0

.loops 5 times
loop2	ADD	num2
	SUB	num1
	COMP	#500
	JLT	loop2
	
halt	J	halt
	END	main

num1	WORD	0x01
num2	WORD	2
