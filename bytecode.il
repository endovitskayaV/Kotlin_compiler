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
.class private auto ansi beforefieldinit Program
    extends [mscorlib]System.Object
        {
 .method private hidebysig static void main(
string[] args) cil managed 
  { .entrypoint
    .maxstack 2
    .locals init (
    )
 nop 
  ldstr "Hello"
 call void CSharpFunctions::print(string str)
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
  }}.class public auto ansi beforefieldinit CSharpFunctions
       extends [mscorlib]System.Object
{
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