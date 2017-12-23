package com.end.compiler;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {

    private static int counter;
    private static VariableReference returnVariable;
    private static List<Declaration> localVarList;

    @NotNull
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


        try {
            resultStr.append(new String(Files.readAllBytes(Paths.get("CSharpStandartFuns.il"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultStr.append("\n");

        //TODO: handle fun without class case
        for (ClassDeclaration classDeclaration : program.getClassDeclarationList()) {
            resultStr.append(generateCode(classDeclaration));
        }
        return resultStr.toString();
    }

    @NotNull
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

    private static void addReturnExprToLocalVarList(FunDeclaration funDeclaration) {
        int index;
        if (localVarList.size() > 0) {
            index = java.lang.Integer.parseInt(
                    localVarList.get((localVarList.size() - 1))
                            .getNewVariable().getVariable().getIndex())+1;
        }
        else index = 0;

        VariableReference variableReference = new VariableReference("V_" + index);
        variableReference.setVisibility(Visibility.LOCAL);
        variableReference.setIndex(java.lang.Integer.toString(index));
        variableReference.fillType(funDeclaration.getReturnType());

        returnVariable = new VariableReference();
        returnVariable.setVarName(variableReference.getVarName());
        returnVariable.setVisibility(variableReference.getVisibility());
        returnVariable.setIndex(variableReference.getIndex());
        returnVariable.fillType(variableReference.getType());

        Declaration declaration = new Declaration(
                new NewVariable("var", variableReference, funDeclaration.getReturnType()), null);

        localVarList.add(declaration);
    }

    private static void addIfConditionToLocalVarList(IfOper ifOper){
        int index;
        if (localVarList.size() > 0) {
            index = java.lang.Integer.parseInt(
                    localVarList.get((localVarList.size() - 1))
                            .getNewVariable().getVariable().getIndex())+1;
        }
        else index = 0;
        ifOper.getConditionVar().setVarName("V_" + index);
        ifOper.getConditionVar().setIndex(java.lang.Integer.toString(index));

        Declaration declaration = new Declaration(
                new NewVariable("var",  ifOper.getConditionVar(), new Boolean()), ifOper.getCondition());
        localVarList.add(declaration);
    }

    private static void addWhileLoopToLocalVarList(WhileLoop whileLoop){
        int index;
        if (localVarList.size() > 0) {
            index = java.lang.Integer.parseInt(
                    localVarList.get((localVarList.size() - 1))
                            .getNewVariable().getVariable().getIndex())+1;
        }
        else index = 0;
       whileLoop.getConditionVar().setVarName("V_" + index);
        whileLoop.getConditionVar().setIndex(java.lang.Integer.toString(index));

        Declaration declaration = new Declaration(
                new NewVariable("var",  whileLoop.getConditionVar(), new Boolean()), whileLoop.getCondition());
        localVarList.add(declaration);
    }

    private static void addDoWhileLoopToLocalVarList(DoWhileLoop doWhileLoop){
        int index;
        if (localVarList.size() > 0) {
            index = java.lang.Integer.parseInt(
                    localVarList.get((localVarList.size() - 1))
                            .getNewVariable().getVariable().getIndex())+1;
        }
        else index = 0;
        doWhileLoop.getConditionVar().setVarName("V_" + index);
        doWhileLoop.getConditionVar().setIndex(java.lang.Integer.toString(index));

        Declaration declaration = new Declaration(
                new NewVariable("var",  doWhileLoop.getConditionVar(), new Boolean()), doWhileLoop.getCondition());
        localVarList.add(declaration);
    }

    private static void addForLoopToLocalVarList(ForLoop forLoop){
        int index;
        if (localVarList.size() > 0) {
            index = java.lang.Integer.parseInt(
                    localVarList.get((localVarList.size() - 1))
                            .getNewVariable().getVariable().getIndex())+1;
        }
        else index = 0;

        //---------------array (iterable) copy-------------//
        forLoop.getIterableCopy().setVarName("V_" + index);
        forLoop.getIterableCopy().setIndex(java.lang.Integer.toString(index));
        forLoop.getIterableCopy().fillType(forLoop.getIterable().getType());
        Declaration declaration = new Declaration(
                new NewVariable("var",  forLoop.getIterableCopy(),forLoop.getIterableCopy().getType()),
                null);
        localVarList.add(declaration);

        //--------------current index--------------------//
        index++;
        forLoop.getCurrentIndex().setVarName("V_" + index);
        forLoop.getCurrentIndex().setIndex(java.lang.Integer.toString(index));

        declaration = new Declaration(
                new NewVariable("var",  forLoop.getCurrentIndex(), new Integer()), null);
        localVarList.add(declaration);

        //-------------iterator-------------------------//
        index++;
        forLoop.getIterator().fillIndex(java.lang.Integer.toString(index));
        declaration = new Declaration(
                new NewVariable("var",  forLoop.getIterator(), forLoop.getIterator().getType()), null);
        localVarList.add(declaration);

    }

    @NotNull
    private static String generateCode(FunDeclaration funDeclaration) {

        StringBuilder resultStr = new StringBuilder();
        resultStr.append(" .method public hidebysig static " +
                generateCode(funDeclaration.getReturnType())+" "
                + funDeclaration.getFunName().getVarName() +
                "(\n");

        StringBuilder paramsStr = new StringBuilder();
        funDeclaration.getFunParametersList().forEach(
                x -> paramsStr.append(generateCode(x) + ", "));
        if (paramsStr.toString().length() > 2)
            resultStr.append(paramsStr.toString().substring(0, paramsStr.length() - 2));

        resultStr.append(") cil managed \n" + "  {");

        if (funDeclaration.getFunName().getVarName().toLowerCase().equals("main"))
            resultStr.append(".entrypoint\n");

        resultStr.append(" \n" +
                "    .maxstack 8\n" +
                "    .locals init (\n");

        localVarList =new ArrayList<>();
        localVarList.addAll(Utils.getAllTargetClassChildren(funDeclaration, Declaration.class));
        Utils.getAllTargetClassChildren(funDeclaration, IfOper.class)
                .forEach(CodeGenerator::addIfConditionToLocalVarList);
        Utils.getAllTargetClassChildren(funDeclaration, WhileLoop.class)
                .forEach(CodeGenerator::addWhileLoopToLocalVarList);
        Utils.getAllTargetClassChildren(funDeclaration, DoWhileLoop.class)
                .forEach(CodeGenerator::addDoWhileLoopToLocalVarList);
        Utils.getAllTargetClassChildren(funDeclaration, ForLoop.class)
                .forEach(CodeGenerator::addForLoopToLocalVarList);

        if (funDeclaration.getReturnExpr() != null) addReturnExprToLocalVarList(funDeclaration);

        StringBuilder paramsStr1 = new StringBuilder();
        for (int i = 0; i < localVarList.size(); i++) {
            paramsStr1.append(
                    "[" + localVarList.get(i).getNewVariable().getVariable().getIndex() + "] " +
                            generateCode(localVarList.get(i).getNewVariable().getType()) + " " +
                            localVarList.get(i).getNewVariable().getVariable().getVarName() + ", ");
        }
        if (paramsStr1.toString().length() > 2)
            resultStr.append(paramsStr1.toString().substring(0, paramsStr1.length() - 2));

        resultStr.append(")\n");
        resultStr.append(" nop \n  ");

        funDeclaration.getExpressionList().forEach(x -> resultStr.append(generateCode(x) + "\n"));
        if (funDeclaration.getReturnExpr() != null)
            resultStr.append(generateCode(funDeclaration.getReturnExpr()));

        resultStr.append("\n ret \n");
        resultStr.append("}");
        return resultStr.toString();
    }

    @NotNull
    private static String generateCode(FunParameter funParameter) {
        return generateCode(funParameter.getType()) + " "
                + funParameter.getVariable().getVarName();
    }

    @NotNull
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
        else if (expression.getClass().getSimpleName().equals(Assignment.class.getSimpleName()))
            return generateCode((Assignment) expression);
        else if (expression.getClass().getSimpleName().equals(WhileLoop.class.getSimpleName()))
            return generateCode((WhileLoop) expression);
        else if (expression.getClass().getSimpleName().equals(ForLoop.class.getSimpleName()))
            return generateCode((ForLoop) expression);
        else if (expression.getClass().getSimpleName().equals(DoWhileLoop.class.getSimpleName()))
            return generateCode((DoWhileLoop) expression);
        else if (expression.getClass().getSimpleName().equals(Declaration.class.getSimpleName()))
           return generateCode((Declaration) expression);
        else if (expression.getClass().getSimpleName().equals(IfOper.class.getSimpleName()))
           return generateCode((IfOper) expression);
        else if (expression instanceof Expr)
            return generateCode((Expr) expression);
        //TODO: clean it
        return "";
    }

    //TODO: comment this method
    @NotNull
    private static String generateCode(ForLoop forLoop){

        ArrayAccess arrayAccess=new ArrayAccess(forLoop.getIterator(), null);
        arrayAccess.fillType(forLoop.getIterator().getType());

        String labelConditionName="LABEL_"+counter;
        counter++;

        String labelNextIterName="LABEL_"+counter;
        counter++;


        StringBuilder resultStr=new StringBuilder();
        resultStr.append(generateLoadCode(forLoop.getIterable())+"\n");
        resultStr.append(generateSaveVariableCode(forLoop.getIterableCopy())+"\n");
        resultStr.append("ldc.i4.0 \n");
        resultStr.append(generateSaveVariableCode(forLoop.getCurrentIndex())+"\n");

        resultStr.append("br.s "+labelConditionName+"\n");

        resultStr.append(labelNextIterName+":\n");
        resultStr.append(generateLoadCode(forLoop.getIterableCopy())+"\n");
        resultStr.append(generateLoadCode(forLoop.getCurrentIndex())+"\n");
        resultStr.append(generateLoadElemCode(arrayAccess)+"\n");
        resultStr.append(generateSaveVariableCode(forLoop.getIterator())+"\n");

        forLoop.getExpressions().forEach(x->resultStr.append(generateCode(x)+"\n"));

        resultStr.append(generateLoadCode(forLoop.getCurrentIndex())+"\n");
        resultStr.append("ldc.i4.1 \n add\n");
        resultStr.append(generateSaveVariableCode(forLoop.getCurrentIndex())+"\n");

        resultStr.append(labelConditionName+":"+"\n");
        resultStr.append(generateLoadCode(forLoop.getCurrentIndex())+"\n");
        resultStr.append(generateLoadCode(forLoop.getIterableCopy())+"\n");
        resultStr.append("ldlen\n conv.i4 \n");
        resultStr.append("blt.s "+labelNextIterName+"\n");

        return resultStr.toString();
    }

    @NotNull
    private static String generateCode(WhileLoop whileLoop){
        StringBuilder resultStr=new StringBuilder();
        String labelConditionName="LABEL_"+counter;
        counter++;
        resultStr.append("br.s "+labelConditionName+"\n");

        String labelLoopBodyName="LABEL_"+counter;
        counter++;

        resultStr.append(labelLoopBodyName+":\n nop \n");
        whileLoop.getExpressions().forEach(x->resultStr.append(generateCode(x)+"\n"));
        resultStr.append("nop\n");

        resultStr.append(labelConditionName+":\n");
        resultStr.append(generateCode(whileLoop.getCondition())+"\n");

        resultStr.append(generateSaveVariableCode(whileLoop.getConditionVar())+"\n");
        resultStr.append(generateLoadCode(whileLoop.getConditionVar())+"\n");
        resultStr.append("brtrue.s "+labelLoopBodyName+"\n");
        return resultStr.toString();
    }

    @NotNull
    private static String generateCode(DoWhileLoop doWhileLoop){
        StringBuilder resultStr=new StringBuilder();
        String labelLoopBodyName="LABEL_"+counter;
        counter++;

        resultStr.append(labelLoopBodyName+":\n nop \n");
        doWhileLoop.getExpressions().forEach(x->resultStr.append(generateCode(x)+"\n"));
        resultStr.append("nop\n");

        resultStr.append(generateCode(doWhileLoop.getCondition())+"\n");

        resultStr.append(generateSaveVariableCode(doWhileLoop.getConditionVar())+"\n");
        resultStr.append(generateLoadCode(doWhileLoop.getConditionVar())+"\n");
        resultStr.append("brtrue.s "+labelLoopBodyName+"\n");
        return resultStr.toString();
    }

    @NotNull
    private static String generateCode(IfOper ifOper){
        StringBuilder resultStr=new StringBuilder();
        resultStr.append(generateCode(ifOper.getCondition()));
        resultStr.append(generateSaveVariableCode(ifOper.getConditionVar()));
        resultStr.append(generateCode(ifOper.getConditionVar()));
        String labelStartName="LABEL_"+counter;
        counter++;
        resultStr.append("brfalse.s "+labelStartName+"\n");

        String labelFinishName=null;
        if (ifOper.getElseBlock()!=null)
        labelFinishName="LABEL_"+counter;
        counter++;

        resultStr.append(generateCode(ifOper.getThenBlock(), labelFinishName));
        if (ifOper.getElseBlock()!=null)
            resultStr.append(generateCode(ifOper.getElseBlock(), labelStartName, labelFinishName));
        else  resultStr.append(labelStartName+":\n");
        return  resultStr.toString();

    }

    @NotNull
    private static String generateCode(ThenBlock thenBlock, String labelFinishName){
        StringBuilder resultStr=new StringBuilder();
        thenBlock.getExpressions().forEach(x->resultStr.append(generateCode(x)));
        if (labelFinishName!=null) //if there was no else block
            resultStr.append("br.s "+labelFinishName+"\n");
        return  resultStr.toString();
    }

    @NotNull
    private static String generateCode(ElseBlock elseBlock, String labelStartName, String labelFinishName){
        StringBuilder resultStr=new StringBuilder();
        resultStr.append(labelStartName+":\n");
        elseBlock.getExpressions().forEach(x->resultStr.append(generateCode(x)+"\n"));
        resultStr.append(labelFinishName+":\n");
        return  resultStr.toString();
    }

    @NotNull
    private static String generateCode(Declaration declaration) {
        if (declaration.getExpr()!=null) //если объявление без инициализации
        return generateCode(new Assignment(declaration.getNewVariable().getVariable(), declaration.getExpr()));
        else return "";
    }

    @NotNull
    private static String generateSaveVariableCode(Expr expr) {
        if (expr instanceof VariableReference) {
            if (((VariableReference) expr).getVisibility() == Visibility.ARG)
                return "starg.s " + ((VariableReference) expr).getVarName() + "\n";
            else return "stloc.s " + ((VariableReference) expr).getVarName() + "\n";
        }
        else /*if (expr instance of ArrayAccess)*/
        return generateSaveElemCode((ArrayAccess) expr)+"\n";
    }

    private static String generateCode(Expr expr) {
        if (expr.getClass().getSimpleName().equals(FunCall.class.getSimpleName()))
            return generateCode((FunCall) expr);
        else if (expr.getClass().getSimpleName().equals(IntegerVar.class.getSimpleName()))
            return generateCode(((IntegerVar) expr));
        else if (expr.getClass().getSimpleName().equals(BooleanVar.class.getSimpleName()))
            return generateCode(((BooleanVar) expr));
        else if (expr.getClass().getSimpleName().equals(CharVar.class.getSimpleName()))
            return generateCode(((CharVar) expr));
        else if (expr.getClass().getSimpleName().equals(DoubleVar.class.getSimpleName()))
            return generateCode(((DoubleVar) expr));
        else if (expr.getClass().getSimpleName().equals(StringVar.class.getSimpleName()))
            return generateCode(((StringVar) expr));
        else if (expr.getClass().getSimpleName().equals(VariableReference.class.getSimpleName()))
            return generateCode(((VariableReference) expr));
        else if (expr.getClass().getSimpleName().equals(BinaryExpr.class.getSimpleName()))
            return generateCode((BinaryExpr) expr);
        else if (expr.getClass().getSimpleName().equals(ArrayAccess.class.getSimpleName()))
            return generateCode((ArrayAccess) expr);
        else if (expr.getClass().getSimpleName().equals(ArrayInitailization.class.getSimpleName()))
            return generateCode((ArrayInitailization) expr);
        else if (expr.getClass().getSimpleName().equals(ReturnExpr.class.getSimpleName()))
            return generate(((ReturnExpr) expr));
        return "";
    }

    @NotNull
    private  static String generateCode(ArrayInitailization arrayInitailization){
        return generateCode(arrayInitailization.getExpr())+"\n"+
         "newarr "+ generateCode(arrayInitailization.getNestedType())+"\n";
    }

    @NotNull
    private static String generateCode(Assignment assignment) {
        StringBuilder resultStr = new StringBuilder();
        if (assignment.getLeft() instanceof ArrayAccess){
            resultStr.append(generateLoadCode(assignment.getLeft())+"\n");
        }
        resultStr.append(generateCode(assignment.getValue()));
        if (assignment.getValue() instanceof ArrayAccess)
            resultStr.append(generateLoadElemCode((ArrayAccess) assignment.getValue())+"\n");
        resultStr.append(generateSaveVariableCode(assignment.getLeft()));
        return resultStr.toString();
    }

    @NotNull
    private  static String generateCode(ArrayAccess arrayAccess){
        StringBuilder resultStr=new StringBuilder();
        resultStr.append(generateLoadCode(arrayAccess)+"\n");

        if (arrayAccess.getParent() instanceof FunCall ||
                arrayAccess.getParent() instanceof ReturnExpr ||
                arrayAccess.getParent() instanceof BinaryExpr)
            resultStr.append(generateLoadElemCode(arrayAccess)+"\n");
        return resultStr.toString();
    }

    @NotNull
    private static String generateSaveElemCode(ArrayAccess arrayAccess){
        StringBuilder resultStr=new StringBuilder();
        resultStr.append("stelem.");
        if (arrayAccess.getType().getClass().getSimpleName().equals(Integer.class.getSimpleName()))
            resultStr.append("i4");
        else if (arrayAccess.getType().getClass().getSimpleName().equals(Double.class.getSimpleName()))
            resultStr.append("r8");
        else if (arrayAccess.getType().getClass().getSimpleName().equals(Char.class.getSimpleName()))
            resultStr.append("i2");
        else if (arrayAccess.getType().getClass().getSimpleName().equals(StringType.class.getSimpleName()))
            resultStr.append("ref");
        else //Boolean
            resultStr.append("i1");
        return  resultStr.toString();
    }

    @NotNull
    private static String generateLoadElemCode(ArrayAccess arrayAccess){
        StringBuilder resultStr=new StringBuilder();
        resultStr.append("ldelem.");
        if (arrayAccess.getType().getClass().getSimpleName().equals(Integer.class.getSimpleName()))
            resultStr.append("i4");
        else if (arrayAccess.getType().getClass().getSimpleName().equals(Double.class.getSimpleName()))
            resultStr.append("r8");
        else if (arrayAccess.getType().getClass().getSimpleName().equals(Char.class.getSimpleName()))
            resultStr.append("u2");
        else if (arrayAccess.getType().getClass().getSimpleName().equals(StringType.class.getSimpleName()))
            resultStr.append("ref");
        else //Boolean
            resultStr.append("u1");

        if (arrayAccess.getCastTo()!=null) resultStr.append("\n"+generateCastCode(arrayAccess.getCastTo())+"\n");
        return  resultStr.toString();
    }

    @NotNull
    private static String generateCode(BinaryExpr binaryExpr){
        StringBuilder resultStr=new StringBuilder();
        resultStr.append(generateCode(binaryExpr.getLeft())+"\n");
        if (binaryExpr.getLeft().getCastTo()!=null)
            resultStr.append(generateCastCode(binaryExpr.getLeft().getCastTo())+"\n");
        resultStr.append(generateCode(binaryExpr.getRight())+"\n");
        if (binaryExpr.getRight().getCastTo()!=null)
            resultStr.append(generateCastCode(binaryExpr.getRight().getCastTo())+"\n");
        resultStr.append(generateSignCode(binaryExpr.getSign())+"\n");
        //this may cause double conv
        if (binaryExpr.getCastTo()!=null) resultStr.append(generateCastCode(binaryExpr.getCastTo())+"\n");
        return resultStr.toString();
    }

    @NotNull
    @Contract(pure = true)
    private  static String generateSignCode(String sign){
        switch (sign){
            case "+": return "add";
            case "-": return "sub";
            case "*": return "mul";
            case "/": return "div";
            case ">=": return "clt\n" + "ldc.i4.0\n" + "ceq\n";
            case "<=": return "cgt\n" + "ldc.i4.0\n" +"ceq\n";
            case "<": return "clt";
            case ">": return "cgt";
            case "==": return "ceq";
            case "!=": return "ceq\n" +"ldc.i4.0\n" +"ceq\n";
        }
        return "";
    }

    @NotNull
    private static String generate(ReturnExpr returnExpr) {
        StringBuilder resultStr = new StringBuilder();
        resultStr.append(generateCode(returnExpr.getExpr()));
        resultStr.append(generateSaveVariableCode(returnVariable) + "\n");
        resultStr.append("br.s   LABEL_" + counter + "\n " +
                "LABEL_" + counter + ":\n");
        counter++;
        resultStr.append(generateCode(returnVariable));
        return resultStr.toString();
    }

    @NotNull
    private static String generateCode(VariableReference variableReference) {
        return generateLoadCode(variableReference);
    }

    private static String generateCastCode(Type castToType){
        StringBuilder resultStr=new StringBuilder();
        resultStr.append("conv.");
//        if (castToType.getClass().getSimpleName().equals(Integer.class.getSimpleName()))
//            resultStr.append("i4");
       // else
            if (castToType.getClass().getSimpleName().equals(Double.class.getSimpleName()))
            resultStr.append("r8");
//        else if (castToType.getClass().getSimpleName().equals(Char.class.getSimpleName()))
//            resultStr.append("u2");
//        else if (castToType.getClass().getSimpleName().equals(StringType.class.getSimpleName()))
//            resultStr.append("ref");
//        else //Boolean
//            resultStr.append("u1");

        return  resultStr.toString();
    }

    @NotNull
    private static String generateLoadCode(Expr expr){
        StringBuilder resultStr=new StringBuilder();

        if (expr instanceof VariableReference){
            if (((VariableReference) expr).getVisibility() == Visibility.ARG)
                resultStr.append("ldarg.s " + ((VariableReference) expr).getVarName() + "\n");
            else resultStr.append("ldloc.s " + ((VariableReference) expr).getVarName() + "\n");

            if (expr.getCastTo()!=null) resultStr.append(generateCastCode(expr.getCastTo()));
        }
        else resultStr.append( generateLoadCode(((ArrayAccess)expr).getVariableReference())+
                generateCode(((ArrayAccess)expr).getExpr()));

       // if (expr.getCastTo()!=null) resultStr.append(generateCastCode(expr.getCastTo()));
        return resultStr.toString();
    }

    @NotNull
    private static String generateCode(StringVar stringVar) {
        return "ldstr " + stringVar.getCILValue() + "\n";
    }

    @NotNull
    private static String generateCode(IntegerVar integerVar) {
        return "ldc.i4.s " + integerVar.getCILValue() + "\n";
    }

    @NotNull
    private static String generateCode(DoubleVar doubleVar) {
        return "ldc.r8 " + doubleVar.getCILValue() + "\n";
    }

    @NotNull
    private static String generateCode(BooleanVar booleanVar) {
        return "ldc.i4." + booleanVar.getCILValue() + "\n";
    }

    @NotNull
    private static String generateCode(CharVar charVar) {
        return "ldc.i4.s " + charVar.getCILValue() + "\n";
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

    @NotNull
    private static String generateCSharpFunCallCode(FunCall funCall, FunDeclaration funDeclaration) {
        StringBuilder resultStr = new StringBuilder();
        if (funCall.getParameters().size() > 0)
            for (Expr funParam : funCall.getParameters()) {
                resultStr.append(generateCode(funParam));
            }
        resultStr.append("\n call ");
        resultStr.append(generateCode(funDeclaration.getReturnType()) +
                " CSharpFunctions::" + funDeclaration.getFunName().getVarName() + "(");

        StringBuilder paramsStr = new StringBuilder();
        funDeclaration.getFunParametersList().forEach(
                x -> paramsStr.append(generateCode(x.getType()) + ", "));
        if (paramsStr.toString().length() > 2)
            resultStr.append(paramsStr.substring(0, paramsStr.length() - 2));

        resultStr.append(")");
        resultStr.append("\n nop \n");
        return resultStr.toString();
    }

    //TODO:  сделать что-то. если функция все класса, токласс-родитель никогда не найдется
    @NotNull
    private static String generateCustomFunCallCode(FunCall funCall, FunDeclaration funDeclaration) {
        StringBuilder resultStr = new StringBuilder();
        if (funCall.getParameters().size() > 0)
            for (Expr funParam : funCall.getParameters()) {
                resultStr.append(generateCode(funParam));
            }
        resultStr.append("call ");
        resultStr.append(generateCode(funDeclaration.getReturnType()) + " " +
                Utils.getClosestTargetClassParent
                        (funDeclaration, ClassDeclaration.class).getClassName().getVarName()
                + "::" +
                funDeclaration.getFunName().getVarName() + "(");


        StringBuilder paramsStr = new StringBuilder();
        funDeclaration.getFunParametersList().forEach(
                x -> paramsStr.append(generateCode(x.getType()) + ", "));
        if (paramsStr.toString().length() > 2)
            resultStr.append(paramsStr.toString().substring(0, paramsStr.length() - 2));

        resultStr.append(")\n nop \n");
        return resultStr.toString();
    }

    @NotNull
    private static String counterToString(int counter) {
        return new DecimalFormat("000").format(counter);
    }
}
