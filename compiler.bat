@echo off

set RESTVAR=
shift
:loop1
if "%0"=="" goto after_loop
set RESTVAR=%RESTVAR% %0
shift
goto loop1
:after_loop

java -jar compiler-1.0-SNAPSHOT-jar-with-dependencies.jar %RESTVAR%
pause