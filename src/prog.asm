@2
A=0
A=1
A=-1
A=D
A=A+1
A=!A
AM=0
AM=1
AM=A+1
AM=!D
AMD=-D
A=-A
D=D-A
D=D+A
M=D|A
M=D&A
MD=D
AMD=A-D

M=M
M=!M
M=M+1
M=M-1
M=D+M
M=D-M
M=M-D
M=D&M
M=D|M

// some comment here haha

A=0;JMP
A=1;JGT
A=-1;JGE
A=D;JNE
A=A+1;JLE
A=!A;JMP
AM=0;JEQ
AM=1;JEQ
A+1;JEQ
!D;JEQ
-D;JNE
D-A;JEQ
!M;JNE
D|M;JMP