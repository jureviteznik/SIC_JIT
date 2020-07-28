.arithetics without using register-register instructions
arit    START   0
prog	LDA 	X1		.addition
		LDX		33
		ADD		3, X
		STA		SUM
		LDA 	X1		.subtraction
		SUB		Y1
		STA		DIFF	
		LDA		X1		.multiplication
		MUL 	Y1
		STA		PROD
		LDA		X1		.division
		DIV 	Y1
		STA 	QUOT
		LDA		QUOT	.modulo - load X1/Y1
		MUL		Y1		.(X1/Y1) * Y1
		STA		MOD		.save result
		LDA		X1		.load X1 into A
		SUB 	MOD		.X1 - ((X1-Y1) * Y1) - modulo operation
		STA 	MOD		.save result
halt	J		halt

.variables
X1		WORD	20
Y1		WORD 	3
SUM		RESW	1
DIFF	RESW	1
PROD 	RESW	1
QUOT 	RESW  	1
MOD		RESW 	1
		END		prog