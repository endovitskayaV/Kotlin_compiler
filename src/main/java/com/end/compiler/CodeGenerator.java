package com.end.compiler;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.List;

public class CodeGenerator {

    private static int counter;

    public static String generateCode(Program program){
        StringBuilder resultStr=new StringBuilder();
        for (ClassDeclaration classDeclaration:program.getClassDeclarationList()) {
            resultStr.append(generateCode(classDeclaration));
        }

        return resultStr.toString();
    }
    public static String generateCode(ClassDeclaration classDeclaration){
        StringBuilder resultStr=new StringBuilder();
        resultStr.append(".class private auto ansi beforefieldinit\n" +
                        classDeclaration.getClassName().getVarName()+"\n" +
                "    extends [mscorlib]System.Object\n" +
                "        {");

        for (FunDeclaration funDeclaration: classDeclaration.getFunDeclarations()) {
            resultStr.append(generateCode(funDeclaration));
        }

        resultStr.append(".method public hidebysig specialname rtspecialname instance void \n" +
                "    .ctor() cil managed \n" +
                "  {\n" +
                "    .maxstack 8\n" +
                "\n" +
                "    IL_0000: ldarg.0      // this\n" +
                "    IL_0001: call         instance void [mscorlib]System.Object::.ctor()\n" +
                "    IL_0006: nop          \n" +
                "    IL_0007: ret          \n" +
                "\n" +
                "  }}");
        return resultStr.toString();
    }

    private static String generateCode(FunDeclaration funDeclaration){
        StringBuilder resultStr=new StringBuilder();
        resultStr.append(" .method private hidebysig static void \n" +funDeclaration.getFunName().getVarName()+
                "(\n");

        funDeclaration.getFunParametersList().forEach(
                x-> resultStr.append(generateCode(x)+", "));
        resultStr.substring(0, resultStr.length()-2);

        resultStr.append("    ) cil managed \n" +"  {");

        resultStr.append(" .entrypoint\n" +
                "    .maxstack 2\n" +
                "    .locals init (\n");

        List<Declaration> declarationList=
                Utils.getAllTargetClassChildren(funDeclaration, Declaration.class);
        for (int i=0; i<declarationList.size(); i++) {
            resultStr.append(
                    "["+i+"] "+
            declarationList.get(0).getNewVariable().getVariable().getType().name()+" "+
                    declarationList.get(0).getNewVariable().getVariable().getVarName()+", ");
        }
        resultStr.substring(0, resultStr.length()-2);
        resultStr.append("    )\n");

        counter=0;
        resultStr.append("IL_"+counterToString(counter++)+": nop   ");

        funDeclaration.getExpressionList().forEach(x->resultStr.append(
                "IL_"+counterToString(counter++)+": "+generateCode(x)));

        resultStr.append("}");
        return resultStr.toString();
    }

    private static String generateCode(FunParameter funParameter){
        return generateCode(funParameter.getType())+" "
                +funParameter.getVariable().getVarName();
    }

    private static String generateCode(Type type){
        if (type.name().equals(new Array().name()))
            return ((Array) type).getNestedType().name().toLowerCase()+"[]";
        else if (type.name().equals(new Integer().name())) return "int32";
            else return type.name().toLowerCase();
    }

    private static String generateCode(Expression expression){
      return "";
    }

    private static String counterToString(int counter){
        return new DecimalFormat("000").format(counter);
    }
}
