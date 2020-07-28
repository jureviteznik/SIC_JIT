test	START	0
main	LDA	#0

.loops 10 times
loop    ADD #2
	    SUB	#1
	    COMP	#30
        JLT	loop
        
halt	J	halt
	    END	main

num1	WORD	1
num2	WORD	2