package com.end.compiler;


import java.util.*;

public class Analysis {

    public static void analyze(Program program) {
        //fill types for all expr
        Utils.getAllChildren(program, Expr.class).forEach(x -> x.fillType(getType(x)));

        ArrayList<ClassDeclaration> classDeclarations=new ArrayList<>();
        classDeclarations.addAll(  Utils.getAllChildren(program, ClassDeclaration.class));
        //dublictations?
        classDeclarations.stream().filter
                (x-> Collections.frequency(classDeclarations,x)>1)
                .forEach(x->PrintableErrors.printDublicatesError("class "+x.getClassName().name(),x.position));
        classDeclarations.forEach(Analysis::analyze);

        ArrayList<FunDeclaration> funDeclarations=new ArrayList<>();
        funDeclarations.addAll(  Utils.getAllChildren(program, FunDeclaration.class));
        //dublictations?
        funDeclarations.stream().filter
                (x-> Collections.frequency(funDeclarations,x)>1)
                .forEach(x->PrintableErrors.printDublicatesError("function "+x.getFunName().name(),x.position));
        funDeclarations.forEach(Analysis::analyze);
    }

//    private static <T extends Node> boolean hasDublicate(Program program, Class<T> clazz) {
//        HashSet<ClassDeclaration> set=new HashSet<>();
//        if (Utils.getAllChildren(program, clazz) == null) return true;
//        else {
//            for (ClassDeclaration classDecl : Utils.getAllChildren(program, ClassDeclaration.class)) {
//                if (!set.add(classDecl)) return true;
//            }
//        }
//        return false;
//    }

    private static void analyze(ClassDeclaration classDeclaration){}
    private static void analyze(FunDeclaration funDeclaration){}
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
        if (equals(type1, type2)) return type1;
        else {
            Type autoCastType = AutoCastType(type1, type2);
            if (autoCastType != null) {
                if (!equals(autoCastType, type1)) expr1.setCastTo(autoCastType);
                if (!equals(autoCastType, type2)) expr2.setCastTo(autoCastType);
                return autoCastType;
            } else return null;
        }
    }

    private static boolean equals(Type type1, Type type2) {
        return (type1 != null || type2 != null &&
                (type1.getClass().getSimpleName().equals(type2.getClass().getSimpleName())));
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
                    .stream().filter(iterable -> iterable.getIdents().get(0).getName()
                            .equals(variableReference.getName()))
                    .findFirst();
            if (forLoop.isPresent()) {
                if (forLoop.get().getIdents().get(1).getType() instanceof Array) {//перебираемая переменная - массив?
                    //тип переменной - это тип элемента массива
                    return ((Array) forLoop.get().getIdents().get(1).getType()).getType();
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
