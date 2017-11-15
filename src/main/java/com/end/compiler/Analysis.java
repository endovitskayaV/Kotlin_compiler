package com.end.compiler;


import java.util.Optional;

public class Analysis {

    public static void analyze(Program program) {
        Utils.getAllChildren(program, Expr.class).forEach(/*fill type*/x-> x.fillType(getType(x)));
    }

    private static Type getType(Expr expr) {
        if (expr.getType() != null) {
            return expr.getType();
        } else {
            expr.fillType(exploreType(expr));
            return expr.getType();
        }
    }

    private Node closestParent(Node node){
        return node.getParent();
    }

    private static Type exploreType(Expr expr) {
       if(expr.getType().getClass().getSimpleName().equals(VariableReference.class.getSimpleName()))
           return exploreType((VariableReference) expr);
       else if(expr.getType().getClass().getSimpleName().equals(BinaryExpr.class.getSimpleName()))
           return exploreType((BinaryExpr) expr);
       else
    }
private static Type exploreType(BinaryExpr binaryExpr){

    }

    private static Type resolveType(Type type1, Type type2, Expr expr1, Expr expr2){
        if (equals(type1, type2)) return  type1;
        else if() return
        else return null;
    }

    private static  boolean equals (Type type1, Type type2){
        return ( type1!=null || type2!=null &&
                (type1.getClass().getSimpleName().equals(type2.getClass().getSimpleName())));
    }
    //это тип узлов, которые мы ищем. чтобы узнать тип переменной, надо найти ее объявление
    //тут я недавно обнаружил такую штуку: переменная можеть существовать без объявления в котлине.
    //если она используется как счетчик в for. поэтому иногда мы не найдем declaration
    private static Type exploreType (VariableReference variableReference ){
       Optional<Declaration> declaration=Utils.getAllVisibleNodes(variableReference, Declaration.class)
               .stream().filter( decl -> decl.getVariable().getName().equals(variableReference.getName()))
               .findFirst();
       if (declaration.isPresent())return  declaration.get().getType();
       else{
           //кажется, у меня там тоже ошибка. нужно сначала найти нужный fo
           Optional<ForLoop> forLoop = Utils.getAllVisibleNodes(variableReference ,ForLoop.class)
                   .stream().filter( iterable -> iterable.getIdents().get(0).getName()
                   .equals(variableReference.getName()))
                   .findFirst();
           if( forLoop.isPresent()) {
               if (forLoop.get().getIdents().get(1).getType() instanceof Array) { //проверяем, что перебираемая переменная - массив
                   return ((Array)forLoop.get().getIdents().get(1).getType()).getType();  //т.к. мы перебираем, то тип переменной будет внутренний тип массива
               }
           }
           //мы нашли все видимые циклы. но мы не знаем, в каком из них нужная нам переменная. ищем именно тот, в
           // котором название переменной такое
           // стопе  почему get 0 ? 1 же. 1 это что перебираем. 0 это какая переменная
           // ясно  тгда почемы ты тип сравниваешь с name это не я, там было Value почему name
           //у тебя name это название переменной
           //почему просто не посмотерть тип  get(0)? потому что мы его ищем разве не надо пистаь тип ?нет в котлине нет
          // for (i in Array<Int>())
       }
       return null;
    }
}
