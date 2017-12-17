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