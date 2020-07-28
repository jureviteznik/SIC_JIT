//run sic
java -jar out/make/sictools.jar

Steps to make JNI work:

//create header file 
javac -h . file.java

//copy function description into Sic_jit_pkg.c
//write the function

// compile Sic_jit_pkg.c into sic_jit_pkg.dll lib file 
//FIX SOME PATHS FOR YOUR PC!

g++ -Wall -I "C:\Program Files\Java\jdk-14\include" -I "C:\Program Files\Java\jdk-14\include\win32" libasmjit.dll libasmtk.dll -shared -o sic_jit_pkg.dll Sic_jit_pkg.cpp

//put  sic_jit_pkg.dll, libasmjit.dll and libasmtk.dll into ..\Java\jdk-14\bin folder. jdk 



//random stuff
eax - A
edi - X
ebp - L
ebx - B
ecx - S
edx - T

missing:
F
sw
