package com.end.compiler;

import java.util.*;

public class Analysis {
    private static int index;

    //---------------------------------NODE analysis---------------------------------------//
    public static void analyze(Program program) {

        Utils.getAllTargetClassChildren(program, Expr.class).forEach(x -> x.fillType(getType(x)));

        program.getClassDeclarationList().forEach(Analysis::analyze);
        program.getFunDeclarationList().forEach(Analysis::analyze);
    }

    private static void analyze(ClassDeclaration classDeclaration) {
        //class varName dublications
        List<ClassDeclaration> classList = new ArrayList<>();
        classList.addAll(Utils.getAllVisibleTagertClassNodes(classDeclaration, ClassDeclaration.class));
        if (classList.size() > 0) {
            classList.remove(classDeclaration);
            classList.forEach(x -> {
                if ((x.getClassName().getVarName()).equals(classDeclaration.getClassName().getVarName()))
                    PrintableErrors.printConflict(classDeclaration.position,
                            classDeclaration, x);
            });
        }

        Utils.getAllTargetClassChildren(classDeclaration, FunDeclaration.class).forEach(Analysis::analyze);
    }

    private static void analyze(FunDeclaration funDeclaration) {
        index=0;
        fillIndex(funDeclaration.getFunParametersList());

        //fun dubliations
        List<FunDeclaration> funList = new ArrayList<>();
        funList.addAll(Utils.getAllVisibleTagertClassNodes(funDeclaration, FunDeclaration.class));
        if (funList.size() > 0) {
            funList.remove(funDeclaration);
            funList.forEach(x -> {
                if ((x.getFunName().getVarName().equals(funDeclaration.getFunName().getVarName())) &&
                        (areParamsListsEqual(funDeclaration.getFunParametersList(), x.getFunParametersList())))
                    PrintableErrors.printConflict(funDeclaration.position,
                            funDeclaration, x);
            });
        }

        funDeclaration.getExpressionList().forEach(Analysis::analyze);
        if (funDeclaration.getReturnExpr()!=null)analyze(funDeclaration.getReturnExpr());
        //Type: returnExpr and declared
        if (funDeclaration.getReturnType() != null && funDeclaration.getReturnExpr() != null) {
            Type returnExpressionType = getType(funDeclaration.getReturnExpr());
//            if (returnExpressionType == null && funDeclaration.getReturnType() != null) {
//                analyze(funDeclaration.getReturnExpr());
//                PrintableErrors.printTypeMismatchError(
//                        funDeclaration.getReturnType(),
//                        returnExpressionType,
//                        funDeclaration.getPosition());
//            }
//            else
            if (returnExpressionType != null &&
                    !typesAreEqual(funDeclaration.getReturnType(), getType(funDeclaration.getReturnExpr())))
                PrintableErrors.printTypeMismatchError(
                        funDeclaration.getReturnType(),
                        returnExpressionType,
                        funDeclaration.getReturnExpr().getPosition());
        } else if (funDeclaration.getReturnType() != null && funDeclaration.getReturnExpr() == null)
            PrintableErrors.printNoReturnStatement(funDeclaration.position);
        else if (funDeclaration.getReturnType() == null && funDeclaration.getReturnExpr() != null)
            PrintableErrors.printTypeMismatchError(new Unit(), getType(funDeclaration.getReturnExpr()),
                    funDeclaration.getReturnExpr().getPosition());
    }
    //-------------------------------------------------------------------------------------------------------------//

    //------------------------EXPRESSION analysis-----------------------------------------------------------------//
    private static void analyze(Expression expression) {
        if (expression.getClass().getSimpleName().equals(Assignment.class.getSimpleName()))
            analyze((Assignment) expression);
        else if (expression.getClass().getSimpleName().equals(WhileLoop.class.getSimpleName()))
            analyze((WhileLoop) expression);
        else if (expression.getClass().getSimpleName().equals(ForLoop.class.getSimpleName()))
            analyze((ForLoop) expression);
        else if (expression.getClass().getSimpleName().equals(DoWhileLoop.class.getSimpleName()))
            analyze((DoWhileLoop) expression);
        else if (expression.getClass().getSimpleName().equals(Declaration.class.getSimpleName()))
            analyze((Declaration) expression);
        else if (expression.getClass().getSimpleName().equals(IfOper.class.getSimpleName()))
            analyze((IfOper) expression);
        else if (expression.getClass().getSimpleName().equals(ElseBlock.class.getSimpleName()))
            analyze((ElseBlock) expression);
        else if (expression.getClass().getSimpleName().equals(ThenBlock.class.getSimpleName()))
            analyze((ThenBlock) expression);
        else if (expression instanceof Expr)
            analyze((Expr) expression);
    }

    private static void analyze(Declaration declaration) {
        fillIndex(declaration.getNewVariable().getVariable());

        //variable was declared
        List<Declaration> declList = new ArrayList<>();
        declList.addAll(Utils.getAllVisibleTagertClassNodes(declaration, Declaration.class));
        declList.remove(declaration);
        declList.forEach(x -> {
            if (x.getNewVariable().getVariable().getVarName().equals
                    (declaration.getNewVariable().getVariable().getVarName()))
            {PrintableErrors.printConflict(declaration.position,
                        declaration, x);}
        });
        analyze(declaration.getExpr());

        Type foundType = getType(declaration.getExpr());
        if (!typesAreEqual(foundType, declaration.getNewVariable().getType()))
            PrintableErrors.printTypeMismatchError(
                    declaration.getNewVariable().getType(),
                    foundType,
                    declaration.position);

        if (declaration.getExpr() instanceof ArrTypeSizeDefVal) {
            String expectedType = declaration.getNewVariable().getType().name();
            if (!(expectedType.equals(foundType.name())))
                PrintableErrors.printTypeMismatchError(
                        declaration.getNewVariable().getType(),
                        foundType,
                        declaration.position);
        }
    }

    private static void analyze(Assignment assignment) {
        analyze(assignment.getLeft());
        analyze(assignment.getValue());

        //was variable declared
        Optional<Declaration> declaration = Utils.getAllVisibleTagertClassNodes(assignment, Declaration.class).stream()
                .filter(x -> {
                    String name = "";
                    if (assignment.getLeft() instanceof VariableReference)
                        name = ((VariableReference) assignment.getLeft()).getVarName();
                    else name = ((ArrayAccess) assignment.getLeft()).getVariableReference().getVarName();
                    return name.equals(x.getNewVariable().getVariable().getVarName());
                }).findFirst();
        if (declaration.isPresent()) { //if variable was declared check types

            if (assignment.getLeft() instanceof ArrayAccess) {
//                analyze((ArrayAccess) assignment.getLeft());
//                Type actualType =  getType(assignment.getValue());
//                Type expectedType = ((ArrTypeSizeDefVal) declaration.get().getExpr()).getNestedType();
//                if (!typesAreEqual(expectedType, actualType))
//                    PrintableErrors.printTypeMismatchError(
//                            expectedType,
//                            actualType,
//                            assignment.position);
            }
            //именно equals type, not castable
            // нельзя var wrong: Double=9;
            else if (!typesAreEqual(declaration.get().getNewVariable().getType(), getType(assignment.getValue())))
                PrintableErrors.printTypeMismatchError(
                        declaration.get().getNewVariable().getType(),//expectedType
                        getType(assignment.getValue()),//actual type
                        assignment.position);
        }
    }

    private static void analyze(WhileLoop whileLoop) {

        analyze(whileLoop.getCondition());

        Type actualType = getType(whileLoop.getCondition());
        if (!typesAreEqual(actualType, new Boolean()))
            PrintableErrors.printTypeMismatchError
                    (new Boolean(), actualType, whileLoop.getCondition().position);
        whileLoop.getExpressions().forEach(Analysis::analyze);
    }

    private static void analyze(DoWhileLoop doWhileLoop) {
        analyze(doWhileLoop.getCondition());

        Type actualType = getType(doWhileLoop.getCondition());
        if (!typesAreEqual(actualType, new Boolean()))
            PrintableErrors.printTypeMismatchError
                    (new Boolean(), actualType, doWhileLoop.getCondition().position);
        doWhileLoop.getExpressions().forEach(Analysis::analyze);
    }

    private static void analyze(ForLoop forLoop) {
       fillIndex(forLoop.getIterator());

        if (!(getType(forLoop.getIterable()) instanceof Array))
            PrintableErrors.printIsNotIterableError(forLoop.getIterable().position);

        analyze(forLoop.getIterator());
        analyze(forLoop.getIterable());
        forLoop.getExpressions().forEach(Analysis::analyze);
    }

    private static void analyze(IfOper ifOperBlock) {
        analyze(ifOperBlock.getCondition());

        Type actualType = getType(ifOperBlock.getCondition());
        if (!typesAreEqual(actualType, new Boolean()))
            PrintableErrors.printTypeMismatchError(new Boolean(), actualType, ifOperBlock.getCondition().position);

        analyze(ifOperBlock.getThenBlock());
        analyze(ifOperBlock.getElseBlock());

    }

    private static void analyze(ElseBlock elseBlock) {
        if (elseBlock != null)
            elseBlock.getExpressions().forEach(Analysis::analyze);
    }

    private static void analyze(ThenBlock thenBlock) {
        if (thenBlock != null)
            thenBlock.getExpressions().forEach(Analysis::analyze);
    }
//----------------------------------------------------------------------------------------------------------------//

    //-----------------------------EXPR analysis--------------------------------------------------------------------//
    private static void analyze(Expr expr) {
        if (expr.getClass().getSimpleName().equals(BinaryExpr.class.getSimpleName()))
            analyze((BinaryExpr) expr);
        else if (expr.getClass().getSimpleName().equals(NewVariable.class.getSimpleName()))
            analyze((NewVariable) expr);
        else if (expr.getClass().getSimpleName().equals(VariableReference.class.getSimpleName()))
            analyze((VariableReference) expr);
        else if (expr.getClass().getSimpleName().equals(FunCall.class.getSimpleName()))
            analyze((FunCall) expr);
        else if (expr.getClass().getSimpleName().equals(ArrayAccess.class.getSimpleName()))
            analyze((ArrayAccess) expr);
        else if (expr.getClass().getSimpleName().equals(ArrTypeSizeDefVal.class.getSimpleName()))
            analyze((ArrTypeSizeDefVal) expr);
        else if (expr.getClass().getSimpleName().equals(ReturnExpr.class.getSimpleName()))
            analyze(((ReturnExpr) expr).getExpr());
        else if (expr.getClass().getSimpleName().equals(IntegerVar.class.getSimpleName()))
            analyze(((IntegerVar) expr));
        else if (expr.getClass().getSimpleName().equals(BooleanVar.class.getSimpleName()))
            analyze(((BooleanVar) expr));
        else if (expr.getClass().getSimpleName().equals(CharVar.class.getSimpleName()))
            analyze(((CharVar) expr));
        else if (expr.getClass().getSimpleName().equals(DoubleVar.class.getSimpleName()))
            analyze(((DoubleVar) expr));
    }

    private static void analyze (DoubleVar doubleVar){
        doubleVar.fillIndex(doubleVar.getValue());
    }

    private static void analyze (CharVar charVar){
        charVar.fillIndex(java.lang.Integer.toString
                (((int) charVar.getValue().charAt(1))));
    }

    private static void analyze (IntegerVar integerVar){
        integerVar.fillIndex(integerVar.getValue());
    }

    private static void analyze (BooleanVar booleanVar){
        if (booleanVar.getValue().equals("true")) booleanVar.fillIndex("1");
        else if (booleanVar.getValue().equals("false")) booleanVar.fillIndex("0");
        else throw new UnsupportedOperationException();
    }

    private static void analyze(BinaryExpr binaryExpr) {
        analyze(binaryExpr.getLeft());
        analyze(binaryExpr.getRight());
        Type resolvedType = resolveType(
                getType(binaryExpr.getLeft()), getType(binaryExpr.getRight()),
                binaryExpr.getLeft(), binaryExpr.getRight());
        if (resolvedType == null)
            if (getType(binaryExpr.getRight()) != null && getType(binaryExpr.getLeft()) != null)
                PrintableErrors.printIncompatibleTypesError(
                        getType(binaryExpr.getLeft()),
                        getType(binaryExpr.getRight()),
                        binaryExpr.position);
            else analyzeOperation(binaryExpr, resolvedType);
    }

    private static void analyzeOperation(BinaryExpr binaryExpr, Type resolvedType) {

//        switch (binaryExpr.getSign()){
//            case ">=":
//            case "<=":
//            case "<":
//            case ">":
//            case "==":
//            case "!=":
//
        //Char подерживает все операции
        if (typesAreEqual(resolvedType, new Array()) || typesAreEqual(resolvedType, new Boolean()))
            PrintableErrors.printOperationDoesNotSupportError
                    (binaryExpr.getSign(), resolvedType, binaryExpr.position);
    }

    private static void analyze(NewVariable newVariable) {
        fillIndex(newVariable.getVariable());
        List<NewVariable> newVariableList=Utils.getAllVisibleTagertClassNodes(newVariable, NewVariable.class);
        if (newVariableList.size()>0) newVariableList.remove(newVariable);
        //была ли такая уже
        if (newVariableList
                .stream().anyMatch(x ->
                {
                    return (x.name()).equals(newVariable.name());
                }))
            PrintableErrors.printDublicatesError(newVariable.name(), newVariable.position);
    }

    private static void analyze(FunCall funCall) {
        List<FunDeclaration> f= Utils.getAllVisibleTagertClassNodes(funCall, FunDeclaration.class);
        //была ли вызываемая функция объявлена
        if (!(Utils.getAllVisibleTagertClassNodes(funCall, FunDeclaration.class)
                .stream().filter(
                        x->(
                                (x.getFunName().getVarName().equals(funCall.getName()))
                                &&
                                (paramsListsAreEqual(x.getFunParametersList(),funCall.getParameters()))
                        )).findFirst().isPresent()))
            PrintableErrors.printNoSuchFunctionError(funCall, funCall.position);
        funCall.getParameters().forEach(Analysis::analyze);
    }

    private static void analyze(VariableReference variableReference) {
        //была ли переменная объявлена (if not then Error)
        if (!((Utils.getAllVisibleTagertClassNodes(variableReference, NewVariable.class)
                .stream().anyMatch(x -> (x.getVariable().getVarName()).equals(variableReference.getVarName()))
                ||
                Utils.getAllVisibleTagertClassNodes(variableReference, ForLoop.class)
                        .stream().anyMatch(x ->
                        (x.getIterator().getVarName()).equals(variableReference.getVarName())))
                ||
                Utils.getAllVisibleTagertClassNodes(variableReference, Declaration.class)
                        .stream().anyMatch(x -> (x.getNewVariable().getVariable().getVarName()).equals(variableReference.getVarName()))))

            PrintableErrors.printUnresolvedReferenceError(variableReference.getVarName(), variableReference.position);

        else {
           Optional<NewVariable> newVariable=
                   Utils.getAllVisibleTagertClassNodes(variableReference, NewVariable.class)
                    .stream().filter((x -> (x.getVariable().getVarName()).equals(variableReference.getVarName())))
                    .findFirst();
           if(newVariable.isPresent()) variableReference.fillIndex(newVariable.get().getVariable().getIndex());

            Optional<ForLoop> forLoop=
                    Utils.getAllVisibleTagertClassNodes(variableReference, ForLoop.class)
                            .stream().filter
                                    (x -> (x.getIterator().getVarName().equals(variableReference.getVarName())))
                            .findFirst();
            if(forLoop.isPresent()) variableReference.fillIndex(forLoop.get().getIterator().getIndex());
            Optional<Declaration> declaration=
                    Utils.getAllVisibleTagertClassNodes(variableReference, Declaration.class)
                            .stream().filter(
                                    (x -> (x.getNewVariable().getVariable().getVarName()).
                                            equals(variableReference.getVarName())))
                            .findFirst();

            if(declaration.isPresent()) variableReference.fillIndex(
                    declaration.get().getNewVariable().getVariable().getIndex());

        }
    }

    private static boolean paramsListsAreEqual(List<FunParameter> list1, List<Expr> list2) {
        if (list1 == null || list2 == null) return true;
        else {
            if (list1.size() != list2.size()) return false;
            for (int i = 0; i < list1.size(); i++) {
                if (!typesAreEqual(list1.get(i).getType(), list2.get(i).getType())) return false;
            }
        }
        return true;
    }

    private static boolean areParamsListsEqual(List<FunParameter> list1, List<FunParameter> list2) {
        if (list1 == null && list2 == null) return true;
        if (list1 != null && list2 != null) {
            if (list1.size() != list2.size()) return false;
            for (int i = 0; i < list1.size(); i++) {
                if (!typesAreEqual(list1.get(i).getType(), list2.get(i).getType())) return false;
            }
        } else return false;
        return true;
    }

    private static void analyze(ArrayAccess arrayAccess) {
        Type expectedType=new Integer();
        Type foundType=getType(arrayAccess.getExpr());
        if (!(typesAreEqual(foundType, expectedType)))
            PrintableErrors.printTypeMismatchError(expectedType, foundType,arrayAccess.getExpr().getPosition());

        analyze(arrayAccess.getVariableReference());
        analyze(arrayAccess.getExpr());
    }

    private static void analyze(ArrTypeSizeDefVal arrTypeSizeDefVal) {
        Utils.getAllTargetClassChildren(arrTypeSizeDefVal, Expr.class).forEach(Analysis::analyze);
    }

    private static void analyze(ReturnExpr returnExpr) {
        analyze(returnExpr.getExpr());
    }
    //----------------------------------------------------------------------------------------------------//

    //----------------------TYPE analysis--------------------------------------------------------------------//
    public static Type getType(Expr expr) {
        if (expr.getType() != null) {
            return expr.getType();
        } else {
            expr.fillType(exploreType(expr));
            return expr.getType();
        }
    }

    private static Type exploreType(Expr expr) {
        if (expr.getClass().getSimpleName().equals(BinaryExpr.class.getSimpleName())) {
            AutoCastType(getType(((BinaryExpr) expr).getLeft()), getType(((BinaryExpr) expr).getRight()));
            if (Arrays.asList("!=", "==", ">=", "<=", ">", "<")
                    .contains(((BinaryExpr) expr).getSign()))
                return new Boolean();
            else return resolveType(getType(((BinaryExpr) expr).getLeft()), getType(((BinaryExpr) expr).getRight()),
                    ((BinaryExpr) expr).getLeft(), ((BinaryExpr) expr).getRight());
        } else if (expr.getClass().getSimpleName().equals(NewVariable.class.getSimpleName()))
            return exploreType((NewVariable) expr);
        else if (expr.getClass().getSimpleName().equals(VariableReference.class.getSimpleName()))
            return exploreType((VariableReference) expr);
        else if (expr.getClass().getSimpleName().equals(IntegerVar.class.getSimpleName()))
            return new Integer();
        else if (expr.getClass().getSimpleName().equals(CharVar.class.getSimpleName()))
            return new Char();
        else if (expr.getClass().getSimpleName().equals(DoubleVar.class.getSimpleName()))
            return new Double();
        else if (expr.getClass().getSimpleName().equals(BooleanVar.class.getSimpleName()))
            return new Boolean();
        else if (expr.getClass().getSimpleName().equals(FunCall.class.getSimpleName()))
            return exploreType((FunCall) expr);
        else if (expr.getClass().getSimpleName().equals(ArrayAccess.class.getSimpleName()))
            return exploreType((ArrayAccess) expr);
        else if (expr.getClass().getSimpleName().equals(ArrTypeSizeDefVal.class.getSimpleName()))
            return exploreType((ArrTypeSizeDefVal) expr);
        else if (expr.getClass().getSimpleName().equals(ReturnExpr.class.getSimpleName()))
            return getType(((ReturnExpr) expr).getExpr());
        else throw new UnsupportedOperationException(expr.getClass().getCanonicalName());

    }

    private static Type exploreType(NewVariable newVariable) {
        return newVariable.getType();
    }

    private static Type exploreType(ArrTypeSizeDefVal arrTypeSizeDefVal) {
        return new Array(arrTypeSizeDefVal.getNestedType());
    }

    private static Type exploreType(ArrayAccess arrayAccess) {
        Optional<Declaration> declaration = Utils.getAllVisibleTagertClassNodes(arrayAccess, Declaration.class).stream()
                .filter(x -> (x.getNewVariable().getVariable().getVarName().equals(arrayAccess.getVariableReference().getVarName())
                        && (typesAreEqual(x.getNewVariable().getType(), new Array())))).findFirst();
        if (declaration.isPresent())
            return ((Array)declaration.get().getNewVariable().getType()).getType();
        return null;
    }

    private static Type exploreType(FunCall funCall) {
        List<FunDeclaration> f=  Utils.getAllVisibleTagertClassNodes(funCall, FunDeclaration.class);
        Optional<FunDeclaration> funDeclaration =
                Utils.getAllVisibleTagertClassNodes(funCall, FunDeclaration.class).stream()
                        .filter(x ->
                                (x.getFunName().getVarName().equals(funCall.getName())
                                        && (paramsListsAreEqual(x.getFunParametersList(), funCall.getParameters())))).
                        findFirst();
        if (funDeclaration.isPresent()) return funDeclaration.get().getReturnType();
        return null;
    }

    private static Type resolveType(Type type1, Type type2, Expr expr1, Expr expr2) {
        if (typesAreEqual(type1, type2)) return type1;
        else {
            Type autoCastType = AutoCastType(type1, type2);
            if (autoCastType != null) {
                if (!typesAreEqual(autoCastType, type1)) expr1.setCastTo(autoCastType);
                if (!typesAreEqual(autoCastType, type2)) expr2.setCastTo(autoCastType);
                return autoCastType;
            } else return null;
        }
    }

    private static boolean typesAreEqual(Type type1, Type type2) {
        if (type1 == null || type2 == null) return false;
        else return (type1.getClass().getSimpleName().equals(type2.getClass().getSimpleName()));
    }

    private static Type AutoCastType(Type type1, Type type2) {
        if (type1 == null || type2 == null) return null;
        if (type1.getClass().getSimpleName().equals(Integer.class.getSimpleName())) return resolveByInt(type2);
        if (type1.getClass().getSimpleName().equals(Double.class.getSimpleName())) return resolveByDouble(type2);
        if (type1.getClass().getSimpleName().equals(Char.class.getSimpleName())) return resolveByChar(type2);
        if (type1.getClass().getSimpleName().equals(Boolean.class.getSimpleName())) return resolveByBoolean(type2);
        if (type1.getClass().getSimpleName().equals(Array.class.getSimpleName()))
            if (type1.getClass().getSimpleName().equals(type2.getClass().getSimpleName()))
                return type1;
            else return null;
        return null;
    }

    private static Type resolveByInt(Type type) {
        if (type.getClass().getSimpleName().equals(Integer.class.getSimpleName())) return new Integer();
        if (type.getClass().getSimpleName().equals(Double.class.getSimpleName())) return new Double();
        if (type.getClass().getSimpleName().equals(Char.class.getSimpleName())) return null;
        if (type.getClass().getSimpleName().equals(Boolean.class.getSimpleName())) return null;
        if (type.getClass().getSimpleName().equals(Array.class.getSimpleName())) return null;
        return null;
    }

    private static Type resolveByDouble(Type type) {
        if (type.getClass().getSimpleName().equals(Integer.class.getSimpleName())) return new Double();
        if (type.getClass().getSimpleName().equals(Double.class.getSimpleName())) return new Double();
        if (type.getClass().getSimpleName().equals(Char.class.getSimpleName())) return null;
        if (type.getClass().getSimpleName().equals(Boolean.class.getSimpleName())) return null;
        if (type.getClass().getSimpleName().equals(Array.class.getSimpleName())) return null;
        return null;
    }

    private static Type resolveByChar(Type type) {
        if (type.getClass().getSimpleName().equals(Integer.class.getSimpleName())) return null;
        if (type.getClass().getSimpleName().equals(Double.class.getSimpleName())) return null;
        if (type.getClass().getSimpleName().equals(Char.class.getSimpleName())) return new Char();
        if (type.getClass().getSimpleName().equals(Boolean.class.getSimpleName())) return null;
        if (type.getClass().getSimpleName().equals(Array.class.getSimpleName())) return null;
        return null;
    }

    private static Type resolveByBoolean(Type type) {
        if (type.getClass().getSimpleName().equals(Integer.class.getSimpleName())) return null;
        if (type.getClass().getSimpleName().equals(Double.class.getSimpleName())) return null;
        if (type.getClass().getSimpleName().equals(Char.class.getSimpleName())) return null;
        if (type.getClass().getSimpleName().equals(Boolean.class.getSimpleName())) return new Boolean();
        if (type.getClass().getSimpleName().equals(Array.class.getSimpleName())) return null;

        return null;
    }

    //это тип узлов, которые мы ищем. чтобы узнать тип переменной, надо найти ее объявление
    // переменная можеть существовать без объявления
    //если она используется как счетчик в for. поэтому иногда мы не найдем declaration
    private static Type exploreType(VariableReference variableReference) {
        Optional<Declaration> declaration = Utils.getAllVisibleTagertClassNodes(variableReference, Declaration.class)
                .stream().filter(decl -> decl.getNewVariable().getVariable().getVarName().equals(variableReference.getVarName()))
                .findFirst();
        if (declaration.isPresent()) return declaration.get().getNewVariable().getType();
        else {
            // нужно сначала найти нужный for
            Optional<ForLoop> forLoop = Utils.getAllVisibleTagertClassNodes(variableReference, ForLoop.class)
                    .stream().filter(x -> x.getIterator().getVarName()
                            .equals(variableReference.getVarName()))
                    .findFirst();
            if (forLoop.isPresent()) {
                if (getType(forLoop.get().getIterable()) instanceof Array) {//перебираемая переменная - массив?
                    //тип переменной - это тип элемента массива
                    return ((Array) getType(forLoop.get().getIterable())).getType();
                }
            }
            //мы нашли все видимые циклы. но мы не знаем, в каком из них нужная нам переменная. ищем именно тот, в
            // котором название переменной такое
            // стопе  почему get 0 ? 1 же. 1 это что перебираем. 0 это какая переменная
            // ясно  тгда почемы ты тип сравниваешь с className это не я, там было Value почему className
            //у тебя className это название переменной
            //почему просто не посмотерть тип  get(0)? потому что мы его ищем разве не надо пистаь тип ?нет в котлине нет
            // for (i in Array<Int>())
        }
        return null;
    }


    private static void fillIndex(List<FunParameter> funParameters){
        int i=0;
        for (FunParameter funParameter:funParameters) {
            funParameter.getVariable().fillIndex(java.lang.Integer.toString(i));
            i++;
        }
    }

    private  static <T extends Indexable> void fillIndex(T indexableNode){
        indexableNode.fillIndex(java.lang.Integer.toString(index));
        index++;
    }
}
