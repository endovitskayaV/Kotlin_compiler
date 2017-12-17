package com.end.compiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.Boolean;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.List;

public class CodeGenerator {

    private static int counter;

    public static String generateCode(Program program) {
        StringBuilder resultStr = new StringBuilder();
        resultStr.append(".assembly extern mscorlib\n" +
                "{\n" +
                "  .publickeytoken = (B7 7A 5C 56 19 34 E0 89 )\n" +
                "  .ver 4:0:0:0\n" +
                "}\n" +
                ".assembly ConsoleApp\n" +
                "{\n" +
                "  .custom instance void [mscorlib]System.Runtime.CompilerServices.CompilationRelaxationsAttribute::.ctor(int32) = ( 01 00 08 00 00 00 00 00 ) \n" +
                "  .custom instance void [mscorlib]System.Runtime.CompilerServices.RuntimeCompatibilityAttribute::.ctor() = ( 01 00 01 00 54 02 16 57 72 61 70 4E 6F 6E 45 78   \n" +
                "                                                                                                             63 65 70 74 69 6F 6E 54 68 72 6F 77 73 01 )       \n" +
                " \n" +
                "  .custom instance void [mscorlib]System.Reflection.AssemblyTitleAttribute::.ctor(string) = ( 01 00 0A 43 6F 6E 73 6F 6C 65 41 70 70 00 00 )    \n" +
                "  .custom instance void [mscorlib]System.Reflection.AssemblyDescriptionAttribute::.ctor(string) = ( 01 00 00 00 00 ) \n" +
                "  .custom instance void [mscorlib]System.Reflection.AssemblyConfigurationAttribute::.ctor(string) = ( 01 00 00 00 00 ) \n" +
                "  .custom instance void [mscorlib]System.Reflection.AssemblyCompanyAttribute::.ctor(string) = ( 01 00 00 00 00 ) \n" +
                "  .custom instance void [mscorlib]System.Reflection.AssemblyProductAttribute::.ctor(string) = ( 01 00 0A 43 6F 6E 73 6F 6C 65 41 70 70 00 00 )    \n" +
                "  .custom instance void [mscorlib]System.Reflection.AssemblyCopyrightAttribute::.ctor(string) = ( 01 00 12 43 6F 70 79 72 69 67 68 74 20 C2 A9 20   \n" +
                "                                                                                                  20 32 30 31 37 00 00 )                           \n" +
                "  .custom instance void [mscorlib]System.Reflection.AssemblyTrademarkAttribute::.ctor(string) = ( 01 00 00 00 00 ) \n" +
                "  .custom instance void [mscorlib]System.Runtime.InteropServices.ComVisibleAttribute::.ctor(bool) = ( 01 00 00 00 00 ) \n" +
                "  .custom instance void [mscorlib]System.Runtime.InteropServices.GuidAttribute::.ctor(string) = ( 01 00 24 34 34 36 35 32 31 63 32 2D 33 63 35 63   \n" +
                "                                                                                                  2D 34 64 30 65 2D 61 63 32 37 2D 61 38 31 32 63   \n" +
                "                                                                                                  30 64 39 61 33 36 63 00 00 )                      \n" +
                "  .custom instance void [mscorlib]System.Reflection.AssemblyFileVersionAttribute::.ctor(string) = ( 01 00 07 31 2E 30 2E 30 2E 30 00 00 )            \n" +
                "  .custom instance void [mscorlib]System.Runtime.Versioning.TargetFrameworkAttribute::.ctor(string) = ( 01 00 1C 2E 4E 45 54 46 72 61 6D 65 77 6F 72 6B   \n" +
                "                                                                                                        2C 56 65 72 73 69 6F 6E 3D 76 34 2E 35 2E 32 01   \n" +
                "                                                                                                        00 54 0E 14 46 72 61 6D 65 77 6F 72 6B 44 69 73   \n" +
                "                                                                                                        70 6C 61 79 4E 61 6D 65 14 2E 4E 45 54 20 46 72   \n" +
                "                                                                                                        61 6D 65 77 6F 72 6B 20 34 2E 35 2E 32 )          \n" +
                "  .hash algorithm 0x00008004\n" +
                "  .ver 1:0:0:0\n" +
                "}\n" +
                ".module ConsoleApp.exe\n" +
                ".imagebase 0x00400000\n" +
                ".file alignment 0x00000200\n" +
                ".stackreserve 0x00100000\n" +
                ".subsystem 0x0003       \n" +
                ".corflags 0x00020003   \n");

        //TODO: handle fun without class case
        for (ClassDeclaration classDeclaration : program.getClassDeclarationList()) {
            resultStr.append(generateCode(classDeclaration));
        }

        try {
            resultStr.append(new String(Files.readAllBytes(Paths.get("CSharpStandartFuns.il"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultStr.toString();
    }

    private static String generateCode(ClassDeclaration classDeclaration) {
        StringBuilder resultStr = new StringBuilder();
        resultStr.append(".class private auto ansi beforefieldinit " +
                classDeclaration.getClassName().getVarName() + "\n" +
                "    extends [mscorlib]System.Object\n" +
                "        {\n");

        if (classDeclaration.getFunDeclarations() != null) {
            for (FunDeclaration funDeclaration : classDeclaration.getFunDeclarations()) {
                resultStr.append(generateCode(funDeclaration));
            }
        }
        resultStr.append(".method public hidebysig specialname rtspecialname instance void \n" +
                "    .ctor() cil managed \n" +
                "  {\n" +
                "    .maxstack 8\n" +
                "\n" +
                "ldarg.0 \n" +
                "call         instance void [mscorlib]System.Object::.ctor()\n" +
                "nop \n" +
                "ret\n" +
                "  }}");
        return resultStr.toString();
    }

    private static String generateCode(FunDeclaration funDeclaration) {
        StringBuilder resultStr = new StringBuilder();
        resultStr.append(" .method private hidebysig static void " + funDeclaration.getFunName().getVarName() +
                "(\n");

        StringBuilder paramsStr = new StringBuilder();
        funDeclaration.getFunParametersList().forEach(
                x -> paramsStr.append(generateCode(x) + ", "));
        if (paramsStr.toString().length() > 2)
            resultStr.append(paramsStr.toString().substring(0, paramsStr.length() - 2));

        resultStr.append(") cil managed \n" + "  {");

        resultStr.append(" .entrypoint\n" +
                "    .maxstack 2\n" +
                "    .locals init (\n");

        //TODO: new var???
        List<Declaration> declarationList =
                Utils.getAllTargetClassChildren(funDeclaration, Declaration.class);
        //TODO: i- д б индекс???
        for (int i = 0; i < declarationList.size(); i++) {
            resultStr.append(
                    "[" + i + "] " +
                            generateCode(declarationList.get(i).getNewVariable().getVariable().getType()) + " " +
                            declarationList.get(i).getNewVariable().getVariable().getVarName() + ", ");
        }
        resultStr.substring(0, resultStr.length() - 2);
        resultStr.append("    )\n");


        resultStr.append(" nop \n  ");

        funDeclaration.getExpressionList().forEach(x -> resultStr.append(generateCode(x)));

        resultStr.append("\n nop \n ret \n");
        resultStr.append("}");
        return resultStr.toString();
    }

    private static String generateCode(FunParameter funParameter) {
        return generateCode(funParameter.getType()) + " "
                + funParameter.getVariable().getVarName();
    }


    private static String generateCode(Type type) {
        if (type instanceof Integer) return "int32";
        else if (type instanceof Double) return "float64";
        else if (type instanceof com.end.compiler.Boolean) return "bool";
        else if (type instanceof Char) return "char";
        else if (type instanceof StringType)
            return "string";
        else if (type instanceof Unit || type == null) return "void";
        else if (type instanceof Array)//.name().equals(new Array().name()))
            return generateCode(((Array) type).getNestedType()) + "[]";
        else throw new UnsupportedOperationException();
    }

    private static String generateCode(Expression expression) {
        if (expression.getClass().getSimpleName().equals(Declaration.class.getSimpleName()))
            return generateCode((Declaration) expression);
        else if (expression instanceof Expr)
            return generateCode((Expr) expression);
        return "";
    }

    private static String generateCode(Declaration declaration) {
        return "";
    }

    private static String generateCode(Expr expr) {
        if (expr.getClass().getSimpleName().equals(FunCall.class.getSimpleName()))
            return generateCode((FunCall) expr);
        return "";
    }

    private static String generateCode(FunCall funCall) {
        //код генерируется по-разному для функций, реализация которых написана в текущем файле,
        // и которые просто объявлены в CSharpFuncDeclarations.vl

        //вызывается ли функция из CSharpFuncDeclarations.vl
        FunDeclaration funDeclaration = Analysis.wasFunDeclared(Main.cSharpFunDeclarationList, funCall);
        if (funDeclaration != null)
            return generateCSharpFunCallCode(funCall, funDeclaration);
        else {
            funDeclaration = Analysis.wasFunDeclared
                    (Utils.getAllVisibleTagertClassNodes(funCall, FunDeclaration.class), funCall);
            return generateCustomFunCallCode(funCall, funDeclaration);
        }
    }

    private static String generateCSharpFunCallCode(FunCall funCall, FunDeclaration funDeclaration) {
        StringBuilder resultStr = new StringBuilder();
        if (funCall.getParameters().size() > 0)
            resultStr.append("ldstr " + ((StringVar) funCall.getParameters().get(0)).getValue());
        resultStr.append("\n call ");
        resultStr.append(generateCode(funDeclaration.getReturnType()) +
                " CSharpFunctions::" + funDeclaration.getFunName().getVarName() + "(");

        StringBuilder paramsStr = new StringBuilder();
        funDeclaration.getFunParametersList().forEach(
                x -> paramsStr.append(generateCode(x) + ", "));
        if (paramsStr.toString().length() > 2)
            resultStr.append(paramsStr.substring(0, paramsStr.length() - 2));

        resultStr.append(")");
        return resultStr.toString();
    }


    //TODO:  сделать что-то. если функция все класса, токласс-родитель никогда не найдется
    private static String generateCustomFunCallCode(FunCall funCall, FunDeclaration funDeclaration) {
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("call ");
        resultStr.append(generateCode(funDeclaration.getReturnType()) + " " +
                Utils.getClosestTargetClassParent(funDeclaration, ClassDeclaration.class).getClassName().getVarName()
                + "::" +
                funDeclaration.getFunName().getVarName() + "(");
        funDeclaration.getFunParametersList().forEach(
                x -> resultStr.append(generateCode(x.getType()) + ", "));
        resultStr.substring(0, resultStr.length() - 2);
        resultStr.append(")");
        return resultStr.toString();
    }

    private static String counterToString(int counter) {
        return new DecimalFormat("000").format(counter);
    }
}
