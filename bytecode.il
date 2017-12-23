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
.class public auto ansi beforefieldinit CSharpFunctions
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
  } // end of method CSharpFunctions::readKey

  .method public hidebysig static void  print(string str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::Write(string)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpFunctions::print

  .method public hidebysig static void  print(int32 str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::Write(int32)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpFunctions::print

  .method public hidebysig static void  print(bool str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::Write(bool)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpFunctions::print

  .method public hidebysig static void  print(float64 str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::Write(float64)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpFunctions::print

  .method public hidebysig static void  print(char str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::Write(char)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpFunctions::print

  .method public hidebysig static void  println(string str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::WriteLine(string)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpFunctions::println

  .method public hidebysig static void  println(int32 str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::WriteLine(int32)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpFunctions::println

  .method public hidebysig static void  println(bool str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::WriteLine(bool)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpFunctions::println

  .method public hidebysig static void  println(float64 str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::WriteLine(float64)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpFunctions::println

  .method public hidebysig static void  println(char str) cil managed
  {
    // Размер кода:       9 (0x9)
    .maxstack  8
    IL_0000:  nop
    IL_0001:  ldarg.0
    IL_0002:  call       void [mscorlib]System.Console::WriteLine(char)
    IL_0007:  nop
    IL_0008:  ret
  } // end of method CSharpFunctions::println

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
  } // end of method CSharpFunctions::readInt

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
  } // end of method CSharpFunctions::readDouble

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
  } // end of method CSharpFunctions::readChar

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
  } // end of method CSharpFunctions::readString

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
  } // end of method CSharpFunctions::readBoolean

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
  } // end of method CSharpFunctions::readlnString

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
  } // end of method CSharpFunctions::readlnInt

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
  } // end of method CSharpFunctions::readlnDouble

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
  } // end of method CSharpFunctions::readlnChar

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
  } // end of method CSharpFunctions::readlnBoolean

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
  } // end of method CSharpFunctions::sqrt

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
  } // end of method CSharpFunctions::power

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
  } // end of method CSharpFunctions::e

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
  } // end of method CSharpFunctions::pi

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
  } // end of method CSharpFunctions::abs

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
  } // end of method CSharpFunctions::abs

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
  } // end of method CSharpFunctions::round

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
  } // end of method CSharpFunctions::trunk

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
  } // end of method CSharpFunctions::remainder

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
  } // end of method CSharpFunctions::exp

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
  } // end of method CSharpFunctions::cos

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
  } // end of method CSharpFunctions::sin

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
  } // end of method CSharpFunctions::tan

  

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
    IL_001d:  call       float64 CSharpFunctions::factorial(float64)
    IL_0022:  mul
    IL_0023:  stloc.1
    IL_0024:  br.s       IL_0032

    IL_0026:  ldc.r8     1.
    IL_002f:  stloc.1
    IL_0030:  br.s       IL_0032

    IL_0032:  ldloc.1
    IL_0033:  ret
  } // end of method CSharpFunctions::factorial

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
  } // end of method CSharpFunctions::size

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
  } // end of method CSharpFunctions::size

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
  } // end of method CSharpFunctions::size

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
  } // end of method CSharpFunctions::size
  
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
  } // end of method CSharpFunctions::concat

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
  } // end of method CSharpFunctions::length

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
  } // end of method CSharpFunctions::size
  
  .method public hidebysig specialname rtspecialname 
          instance void  .ctor() cil managed
  {
    // Размер кода:       8 (0x8)
    .maxstack  8
    IL_0000:  ldarg.0
    IL_0001:  call       instance void [mscorlib]System.Object::.ctor()
    IL_0006:  nop
    IL_0007:  ret
  } // end of method CSharpFunctions::.ctor

} // end of class CSharpFunctions
.class private auto ansi beforefieldinit Program
    extends [mscorlib]System.Object
        {
 .method public hidebysig static void main(
string[] args) cil managed 
  {.entrypoint
 
    .maxstack 8
    .locals init (
[0] int32[] arr, [1] int32 i, [2] float64 d, [3] float64[] arr1)
 nop 
  ldc.i4.s 6

newarr int32
stloc.s arr

ldloc.s arr
ldc.i4.s 5

ldc.i4.s 7
stelem.i4

ldc.i4.s 8
stloc.s i

ldc.r8 9.0
stloc.s d

ldloc.s arr
ldc.i4.s 5

ldelem.i4
conv.r8


ldc.r8 1.0

add
stloc.s d

ldloc.s d

 call void CSharpFunctions::println(float64)
 nop 

ldloc.s arr
ldc.i4.s 5

ldelem.i4

ldloc.s arr
ldc.i4.s 5

ldelem.i4

add
conv.r8

ldc.r8 1.0

add
stloc.s d

ldloc.s d

 call void CSharpFunctions::println(float64)
 nop 

ldloc.s arr
ldc.i4.s 5

ldelem.i4
conv.r8


ldloc.s d

add
stloc.s d

ldloc.s d

 call void CSharpFunctions::println(float64)
 nop 

ldc.i4.s 6

newarr float64
stloc.s arr1

ldloc.s arr1
ldc.i4.s 5

ldc.i4.s 7

ldc.r8 9.7

add
stelem.r8

ldloc.s arr1
ldc.i4.s 0

ldc.r8 7.8
stelem.r8

ldloc.s arr
ldc.i4.s 1

ldloc.s i
stelem.i4

ldloc.s i

ldloc.s i

add
conv.r8

ldc.r8 7.0

add
stloc.s d

ldloc.s d

 call void CSharpFunctions::println(float64)
 nop 

ldloc.s i
conv.r8
ldloc.s d

add
stloc.s d

ldloc.s d

 call void CSharpFunctions::println(float64)
 nop 

ldloc.s i
conv.r8
ldc.r8 8.0

div
stloc.s d

ldloc.s d

 call void CSharpFunctions::println(float64)
 nop 

ldloc.s i
conv.r8
ldc.r8 1.0

add
stloc.s d

ldloc.s d

 call void CSharpFunctions::println(float64)
 nop 


 call void CSharpFunctions::readKey()
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