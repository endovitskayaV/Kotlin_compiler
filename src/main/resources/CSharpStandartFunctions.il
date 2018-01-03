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
