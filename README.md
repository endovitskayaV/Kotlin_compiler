# Kotlin_compiler
Kotlin compiler

This is a compier that can make *.exe from Kotlin language subset (*.vl).
It compiles to msil (*.il).

To enable this compiler you need to download compiler.bat, compiler-1.0-SNAPSHOT-jar-with-dependencies.jar and Present code folder.
Then type this command (in cmd):

compiler.bat kotlin-compiler compile (.\PresentCode\sortArithEx.vl)+ [-lib .\PresentCode\Cast]

where + means one or more,
      [] optional parameter

So,first agument is file that you want to compile and last is your lib name (there must be 2 files of your lib with the same name .vl and .il) 
