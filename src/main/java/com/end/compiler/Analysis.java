package com.end.compiler;


import org.antlr.v4.runtime.ParserRuleContext;

import java.util.*;

public class Analysis {

    //---------------------------------NODE analysis---------------------------------------//
    public static void analyze(Program program) {
        //fill types for all condition
        Utils.getAllChildren(program, Expr.class).forEach(x -> x.fillType(getType(x)));

        List<ClassDeclaration> classDeclarations = new ArrayList<>();
        classDeclarations.addAll(Utils.getAllChildren(program, ClassDeclaration.class));
        //dublictations
        classDeclarations.stream().filter
                (x -> Collections.frequency(classDeclarations, x) > 1)
                .forEach(x -> PrintableErrors.printDublicatesError("class " + x.getClassName().name(), x.position));
        classDeclarations.forEach(Analysis::analyze);

        List<FunDeclaration> funDeclarations = new ArrayList<>();
        funDeclarations.addAll(Utils.getAllChildren(program, FunDeclaration.class));
        //dublictations
        funDeclarations.stream().filter
                (x -> Collections.frequency(funDeclarations, x) > 1)
                .forEach(x -> PrintableErrors.printDublicatesError("function " + x.getFunName().name(), x.position));
        funDeclarations.forEach(Analysis::analyze);
    }

    private static void analyze(ClassDeclaration classDeclaration) {
        ArrayList<FunDeclaration> funDeclarations = new ArrayList<>();
        funDeclarations.addAll(Utils.getAllChildren(classDeclaration, FunDeclaration.class));
        //dublictations
        funDeclarations.stream().filter
                (x -> Collections.frequency(funDeclarations, x) > 1)
                .forEach(x -> PrintableErrors.printDublicatesError("function " + x.getFunName().name(), x.position));
        funDeclarations.forEach(Analysis::analyze);
    }

    private static void analyze(FunDeclaration funDeclaration) {
        List<Expression> expressionList = new ArrayList<>();
        expressionList.addAll(Utils.getAllChildren(funDeclaration, Expression.class));

        //dublictations
        expressionList.stream().filter
                (x -> Collections.frequency(expressionList, x) > 1)
                .forEach(x -> PrintableErrors.printDublicatesError("expression " + x.name(), x.position));
        expressionList.forEach(Analysis::analyze);
        analyze(funDeclaration.getReturnExpr());

        Type returnExpressionType = getType(funDeclaration.getReturnExpr());
        if (returnExpressionType == null && funDeclaration.getReturnType() != null)
            PrintableErrors.printTypeMismatchError(
                    funDeclaration.getReturnType(),
                    returnExpressionType,
                    funDeclaration.getPosition());
        else if ((funDeclaration.getReturnExpr() != null) &&
                (!typesAreEqualOrAutoCastPossible(returnExpressionType, funDeclaration.getReturnType())))
            PrintableErrors.printTypeMismatchError(
                    funDeclaration.getReturnType(),
                    returnExpressionType,
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
        else if (expression.getClass().getSimpleName().equals(IfElse.class.getSimpleName()))
            analyze((IfElse) expression);
        else if (expression.getClass().getSimpleName().equals(ElseBlock.class.getSimpleName()))
            analyze((ElseBlock) expression);
    }

    private static void analyze(Declaration declaration) {
        analyze(declaration.getExpr());

        Type foundType = getType(declaration.getExpr());
        if (!typesAreEqualOrAutoCastPossible(foundType, declaration.getType()))
            PrintableErrors.printTypeMismatchError(
                    declaration.getType(),
                    foundType,
                    declaration.position);
    }

    private static void analyze(Assignment assignment) {
        analyze(assignment.getValue());
        analyze(assignment.getLeft());

        Optional<Declaration> declaration = Utils.getAllVisibleNodes(assignment, Declaration.class).stream()
                .filter(x -> assignment.getLeft().name().equals(x.name())).findFirst();
        if (!declaration.isPresent())
            PrintableErrors.printUnresolvedReferenceError(assignment.name(), assignment.position);
        else {
            Type expectedType = declaration.get().getType();
            Type actualType = assignment.getValue().getType();

            //TODO: or typesAreEqualOrCanCast?
            if (!typesAreEqual(declaration.get().getType(), assignment.getLeft().getType()))
                PrintableErrors.printTypeMismatchError(
                        expectedType,
                        actualType,
                        assignment.position);
        }
    }

    private static void analyze(WhileLoop whileLoop) {
        analyze(whileLoop.getCondition());

        Type actualType = whileLoop.getCondition().getType();
        if (actualType != new Boolean())
            PrintableErrors.printTypeMismatchError( new Boolean(), actualType,  whileLoop.getCondition().position);

        List<Expression> expressionList = new ArrayList<>();
        expressionList.addAll(Utils.getAllChildren(whileLoop, Expression.class));

        //dublictations
        expressionList.stream().filter
                (x -> Collections.frequency(expressionList, x) > 1)
                .forEach(x -> PrintableErrors.printDublicatesError("expression " + x.name(), x.position));
        expressionList.forEach(Analysis::analyze);
    }

    private static void analyze(DoWhileLoop doWhileLoop) {
        analyze(doWhileLoop.getCondition());

        Type actualType = doWhileLoop.getCondition().getType();
        if (actualType != new Boolean())
            PrintableErrors.printTypeMismatchError( new Boolean(), actualType,  doWhileLoop.getCondition().position);

        List<Expression> expressionList = new ArrayList<>();
        expressionList.addAll(Utils.getAllChildren(doWhileLoop, Expression.class));

        //dublictations
        expressionList.stream().filter
                (x -> Collections.frequency(expressionList, x) > 1)
                .forEach(x -> PrintableErrors.printDublicatesError("expression " + x.name(), x.position));
        expressionList.forEach(Analysis::analyze);
    }

    private static void analyze(ForLoop forLoop) {

        if(typesAreEqual(forLoop.getIterable().getType(),new Array()))
            PrintableErrors.printIsNotIterableError(forLoop.getIterable().position);

        //TODO: сравнить тип iterator и тип массива iterable
        //TODO: or typesAreEqualOrCanCast?
       // if(typesAreEqual(forLoop.getIterator().getType(), forLoop.))
        analyze(forLoop.getIterator());
        analyze(forLoop.getIterable());
        List<Expression> expressionList = new ArrayList<>();
        expressionList.addAll(Utils.getAllChildren(forLoop, Expression.class));

        //dublictations
        expressionList.stream().filter
                (x -> Collections.frequency(expressionList, x) > 1)
                .forEach(x -> PrintableErrors.printDublicatesError("expression " + x.name(), x.position));
        expressionList.forEach(Analysis::analyze);
    }

    private static void analyze(IfElse ifElse) {

        analyze(ifElse.getCondition());

        Type actualType = ifElse.getCondition().getType();
        if (actualType != new Boolean())
            PrintableErrors.printTypeMismatchError( new Boolean(), actualType,  ifElse.getCondition().position);

        List<Expression> expressionList = new ArrayList<>();
        expressionList.addAll(Utils.getAllChildren(ifElse, Expression.class));

        //dublictations
        expressionList.stream().filter
                (x -> Collections.frequency(expressionList, x) > 1)
                .forEach(x -> PrintableErrors.printDublicatesError("expression " + x.name(), x.position));
        expressionList.forEach(Analysis::analyze);

        analyze(ifElse.getElseBlock());

    }

    private static void analyze(ElseBlock elseBlock) {

        List<Expression> expressionList = new ArrayList<>();
        expressionList.addAll(Utils.getAllChildren(elseBlock, Expression.class));

        //dublictations
        expressionList.stream().filter
                (x -> Collections.frequency(expressionList, x) > 1)
                .forEach(x -> PrintableErrors.printDublicatesError("expression " + x.name(), x.position));

        expressionList.forEach(Analysis::analyze);


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
        //IntegerVar, BooleanVar, CharVar, DoubleVar->nothing to analyze
    }

    private static void analyze(BinaryExpr binaryExpr) {
        Type resolvedType = resolveType(
                binaryExpr.getLeft().getType(), binaryExpr.getRight().getType(),
                binaryExpr.getLeft(), binaryExpr.getRight());
        if (resolvedType == null)
            PrintableErrors.printIncompatibleTypesError(
                    binaryExpr.getLeft().getType(),
                    binaryExpr.getRight().getType(),
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

    private static void analyze(NewVariable newVariable) { //TODO: check it
        //была ли такая уже
        if (Utils.getAllVisibleNodes(newVariable, NewVariable.class)
                .stream().anyMatch(x ->
                {
                    return (x.name()).equals(newVariable.name());
                }))
            PrintableErrors.printDublicatesError("variable", newVariable.position);
    }

    private static void analyze(FunCall funCall) {
        //была ли вызываемая функция объявлена
        if (Utils.getAllVisibleNodes(funCall, FunDeclaration.class)
                .stream().noneMatch(x ->
                {
                    return (x.getFunName().name().equals(funCall.getName())
                            && (paramsListsAreEqual(x.getFunParametersList(), funCall.getParameters())));
                })) PrintableErrors.printNoSuchFunctionError(funCall, funCall.position);
    }

    private static void analyze(VariableReference variableReference) {
        //была ли переменная объявлена
        if (Utils.getAllVisibleNodes(variableReference, NewVariable.class)
                .stream().anyMatch(x ->
                {
                    return (x.name()).equals(variableReference.getName());
                })
                &&
                Utils.getAllVisibleNodes(variableReference, ForLoop.class)
                        .stream().anyMatch(x ->
                {
                    return (x.getIterator().getName()).equals(variableReference.getName());
                }))
            PrintableErrors.printUnresolvedReferenceError(variableReference.getName(), variableReference.position);
    }

    private static boolean paramsListsAreEqual(List<FunParameter> list1, List<Expr> list2) {
        if (list1 == null && list2 == null) return true;
        if (list1 != null && list2 != null) {
            if (list1.size() != list2.size()) return false;
            for (int i = 0; i < list1.size(); i++) {
                if (!typesAreEqualOrAutoCastPossible(list1.get(i).getType(), list2.get(i).getType())) return false;
            }
        } else return false;
        return true;
    }

    private static void analyze(ArrayAccess arrayAccess) {
        analyze(arrayAccess.getExpr());
        Optional<Declaration> declaration = Utils.getAllVisibleNodes(arrayAccess, Declaration.class).stream()
                .filter(x -> arrayAccess.getName().equals(x.name())).findFirst();
        if (!declaration.isPresent())
            PrintableErrors.printUnresolvedReferenceError(arrayAccess.name(), arrayAccess.position);
        else {
            //TODO: or typesAreEqualOrCanCast?
            if (!typesAreEqual(declaration.get().getType(), arrayAccess.getType()))
                PrintableErrors.printTypeMismatchError(
                        declaration.get().getType(),
                        arrayAccess.getType(),
                        arrayAccess.position);
        }
    }

    private static void analyze(ArrTypeSizeDefVal arrTypeSizeDefVal) {
        Utils.getAllChildren(arrTypeSizeDefVal, Expr.class).forEach(Analysis::analyze);
    }

    private static void analyze(ReturnExpr returnExpr) {
        analyze(returnExpr.getExpr());
    }
    //----------------------------------------------------------------------------------------------------//

    //----------------------TYPE analysis--------------------------------------------------------------------//
    private static Type getType(Expr expr) {
        if (expr.getType() != null) {
            return expr.getType();
        } else {
            expr.fillType(exploreType(expr));
            return expr.getType();
        }
    }

    private static Type exploreType(Expr expr) {
        if (expr.getType().getClass().getSimpleName().equals(BinaryExpr.class.getSimpleName()))
            return exploreType((BinaryExpr) expr);
        else if (expr.getType().getClass().getSimpleName().equals(NewVariable.class.getSimpleName()))
            return exploreType((NewVariable) expr);
        else if (expr.getType().getClass().getSimpleName().equals(VariableReference.class.getSimpleName()))
            return exploreType((VariableReference) expr);
        else if (expr.getType().getClass().getSimpleName().equals(IntegerVar.class.getSimpleName()))
            return new Integer(); //TODO: check if it is correct
        else if (expr.getType().getClass().getSimpleName().equals(CharVar.class.getSimpleName()))
            return new Char();
        else if (expr.getType().getClass().getSimpleName().equals(DoubleVar.class.getSimpleName()))
            return new Double();
        else if (expr.getType().getClass().getSimpleName().equals(BooleanVar.class.getSimpleName()))
            return new Boolean();
        else if (expr.getType().getClass().getSimpleName().equals(FunCall.class.getSimpleName()))
            return exploreType((FunCall) expr);
        else if (expr.getType().getClass().getSimpleName().equals(ArrayAccess.class.getSimpleName()))
            return exploreType((ArrayAccess) expr);
        else if (expr.getType().getClass().getSimpleName().equals(ArrTypeSizeDefVal.class.getSimpleName()))
            return exploreType((ArrTypeSizeDefVal) expr);
        else if (expr.getType().getClass().getSimpleName().equals(ReturnExpr.class.getSimpleName()))
            return ((ReturnExpr) expr).getExpr().getType();
        else throw new UnsupportedOperationException(expr.getClass().getCanonicalName());

    }

    private static Type exploreType(NewVariable newVariable) {
        return null;
        //TODO: write code here
    }

    private static Type exploreType(ArrTypeSizeDefVal arrTypeSizeDefVal) {
        return null;
        //TODO: write code here
    }

    private static Type exploreType(ArrayAccess arrayAccess) {
        return null;
        //TODO: write code here
    }

    private static Type exploreType(FunCall funCall) {
        return null;
        //TODO: write code here
    }

    private static Type exploreType(BinaryExpr binaryExpr) {
        return null;
        //TODO: write code here
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

    private static boolean typesAreEqualOrAutoCastPossible(Type type1, Type type2) {
        return ((type1 != null || type2 != null &&
                (type1.getClass().getSimpleName().equals(type2.getClass().getSimpleName())))
                || (isAutoCastPossible(type1, type2)));
    }

    private static boolean typesAreEqual(Type type1, Type type2) {
        return (type1 != null || type2 != null &&
                (type1.getClass().getSimpleName().equals(type2.getClass().getSimpleName())));
    }

    private static boolean isAutoCastPossible(Type type1, Type type2) {
        return false;
    }

    private static Type AutoCastType(Type type1, Type type2) {
        return null;
        //TODO: write code here
    }

    //это тип узлов, которые мы ищем. чтобы узнать тип переменной, надо найти ее объявление
    // переменная можеть существовать без объявления
    //если она используется как счетчик в for. поэтому иногда мы не найдем declaration
    private static Type exploreType(VariableReference variableReference) {
        Optional<Declaration> declaration = Utils.getAllVisibleNodes(variableReference, Declaration.class)
                .stream().filter(decl -> decl.getVariable().getName().equals(variableReference.getName()))
                .findFirst();
        if (declaration.isPresent()) return declaration.get().getType();
        else {
            // нужно сначала найти нужный for
            Optional<ForLoop> forLoop = Utils.getAllVisibleNodes(variableReference, ForLoop.class)
                    .stream().filter(x -> x.getIterator().getName()
                            .equals(variableReference.getName()))
                    .findFirst();
            if (forLoop.isPresent()) {
                if (forLoop.get().getIterable().getType() instanceof Array) {//перебираемая переменная - массив?
                    //тип переменной - это тип элемента массива
                    return ((Array) forLoop.get().getIterable().getType()).getType();
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
}
