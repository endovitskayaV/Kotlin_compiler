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
