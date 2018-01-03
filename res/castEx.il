.assembly extern mscorlib
{
  .publickeytoken = (B7 7A 5C 56 19 34 E0 89 )
  .ver 4:0:0:0
}
.assembly ConsoleApp
{
  .custom instance void [mscorlib]System.Runtime.CompilerServices.CompilationRelaxationsAttribute::.ctor(int32) = ( 01 00 08 00 00 00 00 00 ) 
  .custom instance void [mscorlib]System.Runtime.CompilerServices.RuntimeCompatibilityAttribute::.ctor() = ( 01 00 01 00 54 02 16 57 72 61 70 4E 6F 6E 45 78   
                                                                                                             63 65 70 74 69 6F 6E 54 68 72 6F 77 73 01 )       
 
  .custom instance void [mscorlib]System.Reflection.AssemblyTitleAttribute::.ctor(string) = ( 01 00 0A 43 6F 6E 73 6F 6C 65 41 70 70 00 00 )    
  .custom instance void [mscorlib]System.Reflection.AssemblyDescriptionAttribute::.ctor(string) = ( 01 00 00 00 00 ) 
  .custom instance void [mscorlib]System.Reflection.AssemblyConfigurationAttribute::.ctor(string) = ( 01 00 00 00 00 ) 
  .custom instance void [mscorlib]System.Reflection.AssemblyCompanyAttribute::.ctor(string) = ( 01 00 00 00 00 ) 
  .custom instance void [mscorlib]System.Reflection.AssemblyProductAttribute::.ctor(string) = ( 01 00 0A 43 6F 6E 73 6F 6C 65 41 70 70 00 00 )    
  .custom instance void [mscorlib]System.Reflection.AssemblyCopyrightAttribute::.ctor(string) = ( 01 00 12 43 6F 70 79 72 69 67 68 74 20 C2 A9 20   
                                                                                                  20 32 30 31 37 00 00 )                           
  .custom instance void [mscorlib]System.Reflection.AssemblyTrademarkAttribute::.ctor(string) = ( 01 00 00 00 00 ) 
  .custom instance void [mscorlib]System.Runtime.InteropServices.ComVisibleAttribute::.ctor(bool) = ( 01 00 00 00 00 ) 
  .custom instance void [mscorlib]System.Runtime.InteropServices.GuidAttribute::.ctor(string) = ( 01 00 24 34 34 36 35 32 31 63 32 2D 33 63 35 63   
                                                                                                  2D 34 64 30 65 2D 61 63 32 37 2D 61 38 31 32 63   
                                                                                                  30 64 39 61 33 36 63 00 00 )                      
  .custom instance void [mscorlib]System.Reflection.AssemblyFileVersionAttribute::.ctor(string) = ( 01 00 07 31 2E 30 2E 30 2E 30 00 00 )            
  .custom instance void [mscorlib]System.Runtime.Versioning.TargetFrameworkAttribute::.ctor(string) = ( 01 00 1C 2E 4E 45 54 46 72 61 6D 65 77 6F 72 6B   
                                                                                                        2C 56 65 72 73 69 6F 6E 3D 76 34 2E 35 2E 32 01   
                                                                                                        00 54 0E 14 46 72 61 6D 65 77 6F 72 6B 44 69 73   
                                                                                                        70 6C 61 79 4E 61 6D 65 14 2E 4E 45 54 20 46 72   
                                                                                                        61 6D 65 77 6F 72 6B 20 34 2E 35 2E 32 )          
  .hash algorithm 0x00008004
  .ver 1:0:0:0
}
.module ConsoleApp.exe
.imagebase 0x00400000
.file alignment 0x00000200
.stackreserve 0x00100000
.subsystem 0x0003       
.corflags 0x00020003   
// =============== CLASS MEMBERS DECLARATION ===================

.class public auto ansi beforefieldinit CSharpStandartFunctions
       extends [mscorlib]System.Object
{
  .method public hidebysig static void  readKey() cil managed
  {
    // Размер кода:       8 (0x8)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  call       valuetype [mscorlib]System.ConsoleKeyInfo [mscorlib]System.Console::ReadKey()
    IL_0006:  pop
    IL_0007:  ret
  } // end of method CSharpStandartFunctions::readKey

  .method public hidebysig static void  print(string str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::Write(string)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpStandartFunctions::print

  .method public hidebysig static void  print(int32 str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::Write(int32)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpStandartFunctions::print

  .method public hidebysig static void  print(bool str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::Write(bool)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpStandartFunctions::print

  .method public hidebysig static void  print(float64 str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::Write(float64)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpStandartFunctions::print

  .method public hidebysig static void  print(char str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::Write(char)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpStandartFunctions::print

  .method public hidebysig static void  println(string str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::WriteLine(string)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpStandartFunctions::println

  .method public hidebysig static void  println(int32 str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::WriteLine(int32)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpStandartFunctions::println

  .method public hidebysig static void  println(bool str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::WriteLine(bool)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpStandartFunctions::println

  .method public hidebysig static void  println(float64 str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::WriteLine(float64)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpStandartFunctions::println

  .method public hidebysig static void  println(char str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::WriteLine(char)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpStandartFunctions::println

  .method public hidebysig static int32  readInt() cil managed
  {
    // Размер кода:       11 (0xb)
    .maxstack  1
    .locals init ([0] int32 V_0)
    IL_0000:  nop
    IL_0001:  call       int32 [mscorlib]System.Console::Read()
    IL_0006:  stloc.0
    IL_0007:  br.s       IL_0009

    IL_0009:  ldloc.0
    IL_000a:  ret
  } // end of method CSharpStandartFunctions::readInt

  .method public hidebysig static float64 
          readDouble() cil managed
  {
    // Размер кода:       16 (0x10)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  call       int32 [mscorlib]System.Console::Read()
    IL_0006:  call       float64 [mscorlib]System.Convert::ToDouble(int32)
    IL_000b:  stloc.0
    IL_000c:  br.s       IL_000e

    IL_000e:  ldloc.0
    IL_000f:  ret
  } // end of method CSharpStandartFunctions::readDouble

  .method public hidebysig static char  readChar() cil managed
  {
    // Размер кода:       16 (0x10)
    .maxstack  1
    .locals init ([0] char V_0)
    IL_0000:  nop
    IL_0001:  call       int32 [mscorlib]System.Console::Read()
    IL_0006:  call       char [mscorlib]System.Convert::ToChar(int32)
    IL_000b:  stloc.0
    IL_000c:  br.s       IL_000e

    IL_000e:  ldloc.0
    IL_000f:  ret
  } // end of method CSharpStandartFunctions::readChar

  .method public hidebysig static string 
          readString() cil managed
  {
    // Размер кода:       16 (0x10)
    .maxstack  1
    .locals init ([0] string V_0)
    IL_0000:  nop
    IL_0001:  call       int32 [mscorlib]System.Console::Read()
    IL_0006:  call       string [mscorlib]System.Convert::ToString(int32)
    IL_000b:  stloc.0
    IL_000c:  br.s       IL_000e

    IL_000e:  ldloc.0
    IL_000f:  ret
  } // end of method CSharpStandartFunctions::readString

  .method public hidebysig static bool  readBoolean() cil managed
  {
    // Размер кода:       16 (0x10)
    .maxstack  1
    .locals init ([0] bool V_0)
    IL_0000:  nop
    IL_0001:  call       int32 [mscorlib]System.Console::Read()
    IL_0006:  call       bool [mscorlib]System.Convert::ToBoolean(int32)
    IL_000b:  stloc.0
    IL_000c:  br.s       IL_000e

    IL_000e:  ldloc.0
    IL_000f:  ret
  } // end of method CSharpStandartFunctions::readBoolean

  .method public hidebysig static string 
          readlnString() cil managed
  {
    // Размер кода:       11 (0xb)
    .maxstack  1
    .locals init ([0] string V_0)
    IL_0000:  nop
    IL_0001:  call       string [mscorlib]System.Console::ReadLine()
    IL_0006:  stloc.0
    IL_0007:  br.s       IL_0009

    IL_0009:  ldloc.0
    IL_000a:  ret
  } // end of method CSharpStandartFunctions::readlnString

  .method public hidebysig static int32  readlnInt() cil managed
  {
    // Размер кода:       16 (0x10)
    .maxstack  1
    .locals init ([0] int32 V_0)
    IL_0000:  nop
    IL_0001:  call       string [mscorlib]System.Console::ReadLine()
    IL_0006:  call       int32 [mscorlib]System.Convert::ToInt32(string)
    IL_000b:  stloc.0
    IL_000c:  br.s       IL_000e

    IL_000e:  ldloc.0
    IL_000f:  ret
  } // end of method CSharpStandartFunctions::readlnInt

  .method public hidebysig static float64 
          readlnDouble() cil managed
  {
    // Размер кода:       16 (0x10)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  call       string [mscorlib]System.Console::ReadLine()
    IL_0006:  call       float64 [mscorlib]System.Convert::ToDouble(string)
    IL_000b:  stloc.0
    IL_000c:  br.s       IL_000e

    IL_000e:  ldloc.0
    IL_000f:  ret
  } // end of method CSharpStandartFunctions::readlnDouble

  .method public hidebysig static char  readlnChar() cil managed
  {
    // Размер кода:       16 (0x10)
    .maxstack  1
    .locals init ([0] char V_0)
    IL_0000:  nop
    IL_0001:  call       string [mscorlib]System.Console::ReadLine()
    IL_0006:  call       char [mscorlib]System.Convert::ToChar(string)
    IL_000b:  stloc.0
    IL_000c:  br.s       IL_000e

    IL_000e:  ldloc.0
    IL_000f:  ret
  } // end of method CSharpStandartFunctions::readlnChar

  .method public hidebysig static bool  readlnBoolean() cil managed
  {
    // Размер кода:       16 (0x10)
    .maxstack  1
    .locals init ([0] bool V_0)
    IL_0000:  nop
    IL_0001:  call       string [mscorlib]System.Console::ReadLine()
    IL_0006:  call       bool [mscorlib]System.Convert::ToBoolean(string)
    IL_000b:  stloc.0
    IL_000c:  br.s       IL_000e

    IL_000e:  ldloc.0
    IL_000f:  ret
  } // end of method CSharpStandartFunctions::readlnBoolean

  .method public hidebysig static float64 
          sqrt(float64 number) cil managed
  {
    // Размер кода:       12 (0xc)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       float64 [mscorlib]System.Math::Sqrt(float64)
    IL_0007:  stloc.0
    IL_0008:  br.s       IL_000a

    IL_000a:  ldloc.0
    IL_000b:  ret
  } // end of method CSharpStandartFunctions::sqrt

  .method public hidebysig static float64 
          sqrt(int32 number) cil managed
  {
    // Размер кода:       13 (0xd)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  conv.r8
    IL_0003:  call       float64 [mscorlib]System.Math::Sqrt(float64)
    IL_0008:  stloc.0
    IL_0009:  br.s       IL_000b

    IL_000b:  ldloc.0
    IL_000c:  ret
  } // end of method CSharpStandartFunctions::sqrt

  .method public hidebysig static float64 
          power(float64 numberBase,
                float64 power) cil managed
  {
    // Размер кода:       13 (0xd)
    .maxstack  2
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  ldarg.1
    IL_0003:  call       float64 [mscorlib]System.Math::Pow(float64,
                                                            float64)
    IL_0008:  stloc.0
    IL_0009:  br.s       IL_000b

    IL_000b:  ldloc.0
    IL_000c:  ret
  } // end of method CSharpStandartFunctions::power

  .method public hidebysig static float64 
          power(int32 numberBase,
                int32 power) cil managed
  {
    // Размер кода:       15 (0xf)
    .maxstack  2
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  conv.r8
    IL_0003:  ldarg.1
    IL_0004:  conv.r8
    IL_0005:  call       float64 [mscorlib]System.Math::Pow(float64,
                                                            float64)
    IL_000a:  stloc.0
    IL_000b:  br.s       IL_000d

    IL_000d:  ldloc.0
    IL_000e:  ret
  } // end of method CSharpStandartFunctions::power

  .method public hidebysig static float64 
          power(int32 numberBase,
                float64 power) cil managed
  {
    // Размер кода:       14 (0xe)
    .maxstack  2
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  conv.r8
    IL_0003:  ldarg.1
    IL_0004:  call       float64 [mscorlib]System.Math::Pow(float64,
                                                            float64)
    IL_0009:  stloc.0
    IL_000a:  br.s       IL_000c

    IL_000c:  ldloc.0
    IL_000d:  ret
  } // end of method CSharpStandartFunctions::power

  .method public hidebysig static float64 
          power(float64 numberBase,
                int32 power) cil managed
  {
    // Размер кода:       14 (0xe)
    .maxstack  2
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  ldarg.1
    IL_0003:  conv.r8
    IL_0004:  call       float64 [mscorlib]System.Math::Pow(float64,
                                                            float64)
    IL_0009:  stloc.0
    IL_000a:  br.s       IL_000c

    IL_000c:  ldloc.0
    IL_000d:  ret
  } // end of method CSharpStandartFunctions::power

  .method public hidebysig static float64 
          e() cil managed
  {
    // Размер кода:       15 (0xf)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldc.r8     2.7182818284590451
    IL_000a:  stloc.0
    IL_000b:  br.s       IL_000d

    IL_000d:  ldloc.0
    IL_000e:  ret
  } // end of method CSharpStandartFunctions::e

  .method public hidebysig static float64 
          pi() cil managed
  {
    // Размер кода:       15 (0xf)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldc.r8     3.1415926535897931
    IL_000a:  stloc.0
    IL_000b:  br.s       IL_000d

    IL_000d:  ldloc.0
    IL_000e:  ret
  } // end of method CSharpStandartFunctions::pi

  .method public hidebysig static float64 
          abs(float64 number) cil managed
  {
    // Размер кода:       12 (0xc)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       float64 [mscorlib]System.Math::Abs(float64)
    IL_0007:  stloc.0
    IL_0008:  br.s       IL_000a

    IL_000a:  ldloc.0
    IL_000b:  ret
  } // end of method CSharpStandartFunctions::abs

  .method public hidebysig static int32  abs(int32 number) cil managed
  {
    // Размер кода:       12 (0xc)
    .maxstack  1
    .locals init ([0] int32 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       int32 [mscorlib]System.Math::Abs(int32)
    IL_0007:  stloc.0
    IL_0008:  br.s       IL_000a

    IL_000a:  ldloc.0
    IL_000b:  ret
  } // end of method CSharpStandartFunctions::abs

  .method public hidebysig static float64 
          round(float64 number) cil managed
  {
    // Размер кода:       12 (0xc)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       float64 [mscorlib]System.Math::Round(float64)
    IL_0007:  stloc.0
    IL_0008:  br.s       IL_000a

    IL_000a:  ldloc.0
    IL_000b:  ret
  } // end of method CSharpStandartFunctions::round

  .method public hidebysig static float64 
          round(float64 number,
                int32 numberPosition) cil managed
  {
    // Размер кода:       13 (0xd)
    .maxstack  2
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  ldarg.1
    IL_0003:  call       float64 [mscorlib]System.Math::Round(float64,
                                                              int32)
    IL_0008:  stloc.0
    IL_0009:  br.s       IL_000b

    IL_000b:  ldloc.0
    IL_000c:  ret
  } // end of method CSharpStandartFunctions::round

  .method public hidebysig static float64 
          trunk(float64 number) cil managed
  {
    // Размер кода:       12 (0xc)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       float64 [mscorlib]System.Math::Truncate(float64)
    IL_0007:  stloc.0
    IL_0008:  br.s       IL_000a

    IL_000a:  ldloc.0
    IL_000b:  ret
  } // end of method CSharpStandartFunctions::trunk

  .method public hidebysig static float64 
          remainder(int32 divider,
                    int32 dividend) cil managed
  {
    // Размер кода:       16 (0x10)
    .maxstack  3
    .locals init ([0] int32 result,
             [1] float64 V_1)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  ldarg.1
    IL_0003:  ldloca.s   result
    IL_0005:  call       int32 [mscorlib]System.Math::DivRem(int32,
                                                             int32,
                                                             int32&)
    IL_000a:  conv.r8
    IL_000b:  stloc.1
    IL_000c:  br.s       IL_000e

    IL_000e:  ldloc.1
    IL_000f:  ret
  } // end of method CSharpStandartFunctions::remainder

  .method public hidebysig static float64 
          exp(float64 number) cil managed
  {
    // Размер кода:       12 (0xc)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       float64 [mscorlib]System.Math::Exp(float64)
    IL_0007:  stloc.0
    IL_0008:  br.s       IL_000a

    IL_000a:  ldloc.0
    IL_000b:  ret
  } // end of method CSharpStandartFunctions::exp

  .method public hidebysig static float64 
          exp(int32 number) cil managed
  {
    // Размер кода:       13 (0xd)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  conv.r8
    IL_0003:  call       float64 [mscorlib]System.Math::Exp(float64)
    IL_0008:  stloc.0
    IL_0009:  br.s       IL_000b

    IL_000b:  ldloc.0
    IL_000c:  ret
  } // end of method CSharpStandartFunctions::exp

  .method public hidebysig static float64 
          cos(float64 number) cil managed
  {
    // Размер кода:       12 (0xc)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       float64 [mscorlib]System.Math::Cos(float64)
    IL_0007:  stloc.0
    IL_0008:  br.s       IL_000a

    IL_000a:  ldloc.0
    IL_000b:  ret
  } // end of method CSharpStandartFunctions::cos

  .method public hidebysig static float64 
          cos(int32 number) cil managed
  {
    // Размер кода:       13 (0xd)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  conv.r8
    IL_0003:  call       float64 [mscorlib]System.Math::Cos(float64)
    IL_0008:  stloc.0
    IL_0009:  br.s       IL_000b

    IL_000b:  ldloc.0
    IL_000c:  ret
  } // end of method CSharpStandartFunctions::cos

  .method public hidebysig static float64 
          sin(int32 number) cil managed
  {
    // Размер кода:       13 (0xd)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  conv.r8
    IL_0003:  call       float64 [mscorlib]System.Math::Sin(float64)
    IL_0008:  stloc.0
    IL_0009:  br.s       IL_000b

    IL_000b:  ldloc.0
    IL_000c:  ret
  } // end of method CSharpStandartFunctions::sin

  .method public hidebysig static float64 
          sin(float64 number) cil managed
  {
    // Размер кода:       12 (0xc)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       float64 [mscorlib]System.Math::Sin(float64)
    IL_0007:  stloc.0
    IL_0008:  br.s       IL_000a

    IL_000a:  ldloc.0
    IL_000b:  ret
  } // end of method CSharpStandartFunctions::sin

  .method public hidebysig static float64 
          tan(float64 number) cil managed
  {
    // Размер кода:       12 (0xc)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       float64 [mscorlib]System.Math::Tan(float64)
    IL_0007:  stloc.0
    IL_0008:  br.s       IL_000a

    IL_000a:  ldloc.0
    IL_000b:  ret
  } // end of method CSharpStandartFunctions::tan

  .method public hidebysig static float64 
          tan(int32 number) cil managed
  {
    // Размер кода:       13 (0xd)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  conv.r8
    IL_0003:  call       float64 [mscorlib]System.Math::Tan(float64)
    IL_0008:  stloc.0
    IL_0009:  br.s       IL_000b

    IL_000b:  ldloc.0
    IL_000c:  ret
  } // end of method CSharpStandartFunctions::tan

  .method public hidebysig static float64 
          factorial(float64 number) cil managed
  {
    // Размер кода:       52 (0x34)
    .maxstack  3
    .locals init ([0] bool V_0,
             [1] float64 V_1)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  ldc.r8     1.
    IL_000b:  cgt
    IL_000d:  stloc.0
    IL_000e:  ldloc.0
    IL_000f:  brfalse.s  IL_0026

    IL_0011:  ldarg.0
    IL_0012:  ldarg.0
    IL_0013:  ldc.r8     1.
    IL_001c:  sub
    IL_001d:  call       float64 CSharpStandartFunctions::factorial(float64)
    IL_0022:  mul
    IL_0023:  stloc.1
    IL_0024:  br.s       IL_0032

    IL_0026:  ldc.r8     1.
    IL_002f:  stloc.1
    IL_0030:  br.s       IL_0032

    IL_0032:  ldloc.1
    IL_0033:  ret
  } // end of method CSharpStandartFunctions::factorial

  .method public hidebysig static int32  factorial(int32 number) cil managed
  {
    // Размер кода:       28 (0x1c)
    .maxstack  3
    .locals init ([0] bool V_0,
             [1] int32 V_1)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  ldc.i4.1
    IL_0003:  cgt
    IL_0005:  stloc.0
    IL_0006:  ldloc.0
    IL_0007:  brfalse.s  IL_0016

    IL_0009:  ldarg.0
    IL_000a:  ldarg.0
    IL_000b:  ldc.i4.1
    IL_000c:  sub
    IL_000d:  call       int32 CSharpStandartFunctions::factorial(int32)
    IL_0012:  mul
    IL_0013:  stloc.1
    IL_0014:  br.s       IL_001a

    IL_0016:  ldc.i4.1
    IL_0017:  stloc.1
    IL_0018:  br.s       IL_001a

    IL_001a:  ldloc.1
    IL_001b:  ret
  } // end of method CSharpStandartFunctions::factorial

  .method public hidebysig static int32  size(int32[] arr) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  1
    .locals init ([0] int32 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  ldlen
    IL_0003:  conv.i4
    IL_0004:  stloc.0
    IL_0005:  br.s       IL_0007

    IL_0007:  ldloc.0
    IL_0008:  ret
  } // end of method CSharpStandartFunctions::size

  .method public hidebysig static int32  size(float64[] arr) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  1
    .locals init ([0] int32 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  ldlen
    IL_0003:  conv.i4
    IL_0004:  stloc.0
    IL_0005:  br.s       IL_0007

    IL_0007:  ldloc.0
    IL_0008:  ret
  } // end of method CSharpStandartFunctions::size

  .method public hidebysig static int32  size(char[] arr) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  1
    .locals init ([0] int32 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  ldlen
    IL_0003:  conv.i4
    IL_0004:  stloc.0
    IL_0005:  br.s       IL_0007

    IL_0007:  ldloc.0
    IL_0008:  ret
  } // end of method CSharpStandartFunctions::size

  .method public hidebysig static int32  size(string[] arr) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  1
    .locals init ([0] int32 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  ldlen
    IL_0003:  conv.i4
    IL_0004:  stloc.0
    IL_0005:  br.s       IL_0007

    IL_0007:  ldloc.0
    IL_0008:  ret
  } // end of method CSharpStandartFunctions::size

  .method public hidebysig static int32  size(bool[] arr) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  1
    .locals init ([0] int32 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  ldlen
    IL_0003:  conv.i4
    IL_0004:  stloc.0
    IL_0005:  br.s       IL_0007

    IL_0007:  ldloc.0
    IL_0008:  ret
  } // end of method CSharpStandartFunctions::size

  .method public hidebysig static string 
          concat(string str1,
                 string str2) cil managed
  {
    // Размер кода:       13 (0xd)
    .maxstack  2
    .locals init ([0] string V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  ldarg.1
    IL_0003:  call       string [mscorlib]System.String::Concat(string,
                                                                string)
    IL_0008:  stloc.0
    IL_0009:  br.s       IL_000b

    IL_000b:  ldloc.0
    IL_000c:  ret
  } // end of method CSharpStandartFunctions::concat

  .method public hidebysig static int32  length(string str) cil managed
  {
    // Размер кода:       12 (0xc)
    .maxstack  1
    .locals init ([0] int32 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  callvirt   instance int32 [mscorlib]System.String::get_Length()
    IL_0007:  stloc.0
    IL_0008:  br.s       IL_000a

    IL_000a:  ldloc.0
    IL_000b:  ret
  } // end of method CSharpStandartFunctions::length

  .method public hidebysig specialname rtspecialname 
          instance void  .ctor() cil managed
  {
    // Размер кода:       8 (0x8)
    .maxstack  8
    IL_0000:  ldarg.0
    IL_0001:  call       instance void [mscorlib]System.Object::.ctor()
    IL_0006:  nop
    IL_0007:  ret
  } // end of method CSharpStandartFunctions::.ctor

} // end of class CSharpStandartFunctions

.class private auto ansi beforefieldinit Cast
       extends [mscorlib]System.Object
{
  .method public hidebysig static int32  castToInt(char variable) cil managed
  {
    // Размер кода:       7 (0x7)
    .maxstack  1
    .locals init ([0] int32 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  stloc.0
    IL_0003:  br.s       IL_0005

    IL_0005:  ldloc.0
    IL_0006:  ret
  } // end of method Cast::castToInt

  .method public hidebysig static int32  castToInt(float64 variable) cil managed
  {
    // Размер кода:       12 (0xc)
    .maxstack  1
    .locals init ([0] int32 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       int32 [mscorlib]System.Convert::ToInt32(float64)
    IL_0007:  stloc.0
    IL_0008:  br.s       IL_000a

    IL_000a:  ldloc.0
    IL_000b:  ret
  } // end of method Cast::castToInt

  .method public hidebysig static int32  castToInt(string variable) cil managed
  {
    // Размер кода:       12 (0xc)
    .maxstack  1
    .locals init ([0] int32 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       int32 [mscorlib]System.Convert::ToInt32(string)
    IL_0007:  stloc.0
    IL_0008:  br.s       IL_000a

    IL_000a:  ldloc.0
    IL_000b:  ret
  } // end of method Cast::castToInt

  .method public hidebysig static float64 
          castToDouble(int32 variable) cil managed
  {
    // Размер кода:       12 (0xc)
    .maxstack  1
    .locals init ([0] float64 V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       float64 [mscorlib]System.Convert::ToDouble(int32)
    IL_0007:  stloc.0
    IL_0008:  br.s       IL_000a

    IL_000a:  ldloc.0
    IL_000b:  ret
  } // end of method Cast::castToDouble

  .method public hidebysig static string 
          castToString(char variable) cil managed
  {
    // Размер кода:       12 (0xc)
    .maxstack  1
    .locals init ([0] string V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       string [mscorlib]System.Convert::ToString(char)
    IL_0007:  stloc.0
    IL_0008:  br.s       IL_000a

    IL_000a:  ldloc.0
    IL_000b:  ret
  } // end of method Cast::castToString

  .method public hidebysig static string 
          castToString(int32 variable) cil managed
  {
    // Размер кода:       12 (0xc)
    .maxstack  1
    .locals init ([0] string V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       string [mscorlib]System.Convert::ToString(int32)
    IL_0007:  stloc.0
    IL_0008:  br.s       IL_000a

    IL_000a:  ldloc.0
    IL_000b:  ret
  } // end of method Cast::castToString

  .method public hidebysig static string 
          castToString(float64 variable) cil managed
  {
    // Размер кода:       12 (0xc)
    .maxstack  1
    .locals init ([0] string V_0)
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       string [mscorlib]System.Convert::ToString(float64)
    IL_0007:  stloc.0
    IL_0008:  br.s       IL_000a

    IL_000a:  ldloc.0
    IL_000b:  ret
  } // end of method Cast::castToString

  .method public hidebysig specialname rtspecialname 
          instance void  .ctor() cil managed
  {
    // Размер кода:       8 (0x8)
    .maxstack  8
    IL_0000:  ldarg.0
    IL_0001:  call       instance void [mscorlib]System.Object::.ctor()
    IL_0006:  nop
    IL_0007:  ret
  } // end of method Cast::.ctor

} // end of class Cast

.class private auto ansi beforefieldinit Program
    extends [mscorlib]System.Object
        {
 .method public hidebysig static void main(
string[] args) cil managed 
  {.entrypoint
 
    .maxstack 8
    .locals init (
[0] char charVar, [1] int32 intVar, [2] float64 doubleVar, [3] string stringVar)
 nop 
  ldc.i4.s 97
stloc.s charVar

ldloc.s charVar

 call int32 Cast::castToInt(char)
 nop 
stloc.s intVar

ldstr "charVar:"

 call void CSharpStandartFunctions::print(string)
 nop 

ldloc.s charVar

 call void CSharpStandartFunctions::print(char)
 nop 

ldstr " intVar:"

 call void CSharpStandartFunctions::print(string)
 nop 

ldloc.s intVar

 call void CSharpStandartFunctions::println(int32)
 nop 

ldstr ""

 call void CSharpStandartFunctions::println(string)
 nop 

ldc.i4.s 1

conv.r8
ldc.r8 0.0

add
stloc.s doubleVar

ldloc.s doubleVar

 call int32 Cast::castToInt(float64)
 nop 
stloc.s intVar

ldstr "doubleVar:"

 call void CSharpStandartFunctions::print(string)
 nop 

ldloc.s doubleVar

 call void CSharpStandartFunctions::print(float64)
 nop 

ldstr " intVar:"

 call void CSharpStandartFunctions::print(string)
 nop 

ldloc.s intVar

 call void CSharpStandartFunctions::println(int32)
 nop 

ldstr ""

 call void CSharpStandartFunctions::println(string)
 nop 

ldstr "1"
stloc.s stringVar

ldloc.s stringVar

 call int32 Cast::castToInt(string)
 nop 
stloc.s intVar

ldstr "stringVar:"

 call void CSharpStandartFunctions::print(string)
 nop 

ldloc.s stringVar

 call void CSharpStandartFunctions::print(string)
 nop 

ldstr " intVar:"

 call void CSharpStandartFunctions::print(string)
 nop 

ldloc.s intVar

 call void CSharpStandartFunctions::println(int32)
 nop 

ldstr ""

 call void CSharpStandartFunctions::println(string)
 nop 

ldc.i4.s 1
stloc.s intVar

ldloc.s intVar

 call float64 Cast::castToDouble(int32)
 nop 
stloc.s doubleVar

ldstr "intVar:"

 call void CSharpStandartFunctions::print(string)
 nop 

ldloc.s intVar

 call void CSharpStandartFunctions::print(int32)
 nop 

ldstr " doubleVar:"

 call void CSharpStandartFunctions::print(string)
 nop 

ldloc.s doubleVar

 call void CSharpStandartFunctions::println(float64)
 nop 

ldstr ""

 call void CSharpStandartFunctions::println(string)
 nop 

ldloc.s charVar

 call string Cast::castToString(char)
 nop 
stloc.s stringVar

ldstr "charVar:"

 call void CSharpStandartFunctions::print(string)
 nop 

ldloc.s charVar

 call void CSharpStandartFunctions::print(char)
 nop 

ldstr " stringVar:"

 call void CSharpStandartFunctions::print(string)
 nop 

ldloc.s stringVar

 call void CSharpStandartFunctions::println(string)
 nop 

ldstr ""

 call void CSharpStandartFunctions::println(string)
 nop 

ldc.i4.s 1
stloc.s intVar

ldloc.s intVar

 call string Cast::castToString(int32)
 nop 
stloc.s stringVar

ldstr "intVar:"

 call void CSharpStandartFunctions::print(string)
 nop 

ldloc.s intVar

 call void CSharpStandartFunctions::print(int32)
 nop 

ldstr " stringVar:"

 call void CSharpStandartFunctions::print(string)
 nop 

ldloc.s stringVar

 call void CSharpStandartFunctions::println(string)
 nop 

ldstr ""

 call void CSharpStandartFunctions::println(string)
 nop 

ldc.r8 7.8
stloc.s doubleVar

ldloc.s doubleVar

 call string Cast::castToString(float64)
 nop 
stloc.s stringVar

ldstr "doubleVar:"

 call void CSharpStandartFunctions::print(string)
 nop 

ldloc.s doubleVar

 call void CSharpStandartFunctions::print(float64)
 nop 

ldstr " stringVar:"

 call void CSharpStandartFunctions::print(string)
 nop 

ldloc.s stringVar

 call void CSharpStandartFunctions::println(string)
 nop 

ldstr ""

 call void CSharpStandartFunctions::println(string)
 nop 

ldstr "Press any key to exit..."

 call void CSharpStandartFunctions::println(string)
 nop 


 call void CSharpStandartFunctions::readKey()
 nop 


 ret 
}.method public hidebysig specialname rtspecialname instance void 
    .ctor() cil managed 
  {
    .maxstack 8

ldarg.0 
call         instance void [mscorlib]System.Object::.ctor()
nop 
ret
  }}