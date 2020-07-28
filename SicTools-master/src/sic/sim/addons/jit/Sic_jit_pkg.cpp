#include <jni.h>
#include <iostream>
#include "sic_sim_addons_jit_JustInTime.h"
//#include "src\asmjit\asmjit.h

#include "asmjit-master\src\asmjit\asmjit.h"
#include "asmtk-master\src\asmtk\asmtk.h"

using namespace std;
using namespace asmjit;
using namespace asmtk;


// Signature of the generated function.
typedef int (*Func)(void);

/**
 * executeAsm(String, String)
 */
JNIEXPORT void JNICALL Java_sic_sim_addons_jit_JustInTime_executeAsm
  (JNIEnv *env, jobject self, jstring asmInstrs, jstring asmData){
    
    const char* nativeAsmInstr = env -> GetStringUTFChars(asmInstrs, NULL);
    const char* nativeAsmData = env -> GetStringUTFChars(asmData, NULL);

    JitRuntime rt;                          // Runtime designed for JIT code execution.
    StringLogger logger;         // Logger should always survive CodeHolder.
 
    CodeHolder code;                        // Holds code and relocation information.
    code.init(rt.environment());            // Initialize CodeHolder to match JIT environment.
    code.setLogger(&logger);     // Attach the `logger` to `code` holder.
    x86::Assembler a(&code);                // Create and attach x86::Assembler to `code`.
    AsmParser parInstr(&a);                 // Create AsmParser that will emit to x86::Assembler.
    AsmParser parData(&a);

    // Parse assembly.
    Error err2 = parInstr.parse(nativeAsmInstr);
    if (err2) {
      printf("PARSE INSTR ERROR: %08x (%s)\n", err2, DebugUtils::errorAsString(err2));
      return ;
    }
    
    /* save register state*/
    int regA;
    int regX;
    int regB;
    int regS;
    int regT;

    a.mov(x86::rsi, uint64_t(&regA));       //alternative would be to use mov [absolute address], al|ax|eax|rax
    a.mov(x86::ptr(x86::rsi), x86::eax);    //its a single instruction but this is safer?

    a.mov(x86::rsi, uint64_t(&regX));
    a.mov(x86::ptr(x86::rsi), x86::edi);

    a.mov(x86::rsi, uint64_t(&regB));
    a.mov(x86::ptr(x86::rsi), x86::ebx);

    a.mov(x86::rsi, uint64_t(&regS));
    a.mov(x86::ptr(x86::rsi), x86::ecx);

    a.mov(x86::rsi, uint64_t(&regT));
    a.mov(x86::ptr(x86::rsi), x86::edx);

    a.ret();                               // Return from function.
    
    // Parse data.
    Error err3 = parData.parse(nativeAsmData);
    if (err3) {
      printf("PARSE DATA ERROR: %08x (%s)\n", err3, DebugUtils::errorAsString(err3));
      return ;
    }

    printf("Logger content: %s\n", logger.data());

    Func fn;
    Error err = rt.add(&fn, &code);         // Add the generated code to the runtime.
    if (err){                               // Handle a possible error returned by AsmJit.
      printf("EXE ERROR: %08x (%s)\n", err, DebugUtils::errorAsString(err));
      return;
    }                     

    int result = fn();                      // Execute the generated code.
    
    //print register state
    printf("regA: %d\nregX: %d\nregB: %d\nregS: %d\nregT: %d\n", regA,regX,regB,regS,regT);

    // All classes use RAII, all resources will be released before `main()` returns,
    // the generated function can be, however, released explicitly if you intend to
    // reuse or keep the runtime alive, which you should in a production-ready code.
    rt.release(fn);


    //TODO: CONSTRUCT AN JAVA OBJECT WITH REGISTER VALUES AND CHANGED MEMORY, RETURN THAT OBJECT
    return;
  }

  /**
 * helloWorld() -  random function for testing
 */
JNIEXPORT void JNICALL Java_sic_sim_addons_jit_JustInTime_helloWorld
  (JNIEnv *env, jobject self){
      printf("Hello World from cpp! :D");


      JitRuntime rt;                          // Runtime designed for JIT code execution.

      CodeHolder code;                        // Holds code and relocation information.
      code.init(rt.environment());            // Initialize CodeHolder to match JIT environment.

      x86::Assembler a(&code);                // Create and attach x86::Assembler to `code`.
      a.mov(x86::eax, 1);                     // Move one to 'eax' register.
      AsmParser p(&a);                        // Create AsmParser that will emit to x86::Assembler.

      // Parse some assembly.
      Error err2 = p.parse(
        "mov eax, 1\n"
        "mov eax, 123\n");

      // Error handling (use asmjit::ErrorHandler for more robust error handling).
      if (err2) {
        printf("ERROR: %08x (%s)\n", err2, DebugUtils::errorAsString(err2));
        return ;
      }
      a.ret();                                // Return from function.
      
      Func fn;
      Error err = rt.add(&fn, &code);         // Add the generated code to the runtime.
      if (err) return;                      // Handle a possible error returned by AsmJit.

      int result = fn();                      // Execute the generated code.
      printf("%d\n", result);                 // Print the resulting "1".

      // All classes use RAII, all resources will be released before `main()` returns,
      // the generated function can be, however, released explicitly if you intend to
      // reuse or keep the runtime alive, which you should in a production-ready code.
      rt.release(fn);

      return;
  }

