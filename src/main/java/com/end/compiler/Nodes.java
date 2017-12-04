package com.end.compiler;

import io.bretty.console.tree.PrintableTreeNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
abstract class Node implements PrintableTreeNode {
    protected Node parent;
    protected Position position;
    protected Stack<java.lang.Integer> index=new Stack<>();
}

@Data
@AllArgsConstructor
@EqualsAndHashCode
abstract class Expression extends Node {
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
abstract class Expr extends Expression {
    private Type castTo;
    private Type type;

    public void castTo(Type type) {
        castTo = type;
    }

    public void fillType(Type type) {
        this.type = type;
    }

    public String typeOrNull() {
        if (type != null) return " type: " + type.name();
        else return "";
    }

    public String castToIfNeed() {
        if (castTo != null) return " cast to : " + castTo.name();
        else return "";
    }

    public String errorName(){return this.type.name();}
}

@Data
@AllArgsConstructor
@EqualsAndHashCode
abstract class Type extends Node {
    public List<PrintableTreeNode> children() {
        return new ArrayList<>();
    }

    public String name() {
        return getClass().getName();
    }
    public String errorName(){return this.name();}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class FunParameter extends Node {
    private VariableReference variableName;
    private Type type;

    @Override
    public String name() {
        return variableName.getVarName() + ":" + type.name()+" index: "+Utils.stackToString(this.index);
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class FunDeclaration extends Node {
    private VariableReference funName;
    private Type returnType;
    private List<FunParameter> funParametersList;
    private List<Expression> expressionList;
    private ReturnExpr returnExpr;

    @Override
    public String name() {
        String returnStr = "fun " + funName.getVarName() + "(";
        if (funParametersList.size() > 0) {
            for (Node funParam : funParametersList)
                returnStr += funParam.name() + ", ";
            returnStr = returnStr.substring(0, returnStr.length() - 2);
        }
        returnStr += ") :";
        if (returnType != null) returnStr += returnType.name();
        else returnStr += "Unit";
        returnStr+=" index: "+Utils.stackToString(this.index);
        return returnStr;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        children.addAll(0, expressionList);
        if (returnExpr != null)
            children.add(returnExpr);
        return children;
    }
    @Override
    public String toString(){
        return  name();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class ClassDeclaration extends Node {

    private VariableReference className;
    private List<Node> propertiesDecls;
    private List<Node> funDeclarations;

    @Override
    public String name() {
        String i=Utils.stackToString(this.index);
        return "class " + className.getVarName()+" index: "+Utils.stackToString(this.index);
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        if (propertiesDecls != null) children.addAll(propertiesDecls);
        if (funDeclarations != null) children.addAll(funDeclarations);
        return children;
    }
    @Override
    public String toString(){
        return "class " + className.getVarName() ;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class Program extends Node {

    private List<ClassDeclaration> classDeclarationList;
    private List<FunDeclaration> funDeclarationList;

    @Override
    public String name() {
        return "program "+"index: "+Utils.stackToString(this.index);
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        if (classDeclarationList != null) children.addAll(classDeclarationList);
        if (funDeclarationList != null) children.addAll(funDeclarationList);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class Assignment extends Expression {
    private Expr left;
    private Expr value;

    @Override
    public String name() {
        return "=";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> childrenList = new ArrayList<>();
        childrenList.add(left);
        childrenList.add(value);
        return childrenList;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class WhileLoop extends Expression {
    private Expr condition;
    private List<Expression> expressions;

    @Override
    public String name() {
        return "while"+" index: "+Utils.stackToString(this.index);
    }

    @Override
    public ArrayList<PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> childrenList = new ArrayList<>();
        childrenList.add(condition);
        childrenList.addAll(1, expressions);
        return childrenList;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class ForLoop extends Expression {
    private VariableReference iterator;
    private Expr iterable;
    private List<Expression> expressions;

    @Override
    public String name() {
        return "for"+" index: "+Utils.stackToString(this.index);
    }

    @Override
    public ArrayList<PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> childrenList = new ArrayList<>();
        childrenList.add(iterator);
        childrenList.add(iterable);
        childrenList.addAll(2, expressions);
        return childrenList;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class DoWhileLoop extends Expression {
    private Expr condition;
    private List<Expression> expressions;

    @Override
    public String name() {
        return "do-while"+" index: "+Utils.stackToString(this.index);
    }

    @Override
    public ArrayList<PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> childrenList = new ArrayList<>();
        childrenList.add(condition);
        childrenList.addAll(1, expressions);
        return childrenList;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class Declaration extends Expression {
    private NewVariable newVariable;
    private Expr expr;

    @Override
    public String name() {
        return "=";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        children.add(newVariable);
        children.add(expr);
        return children;
    }

    @Override
    public String toString(){
        return newVariable.toString();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class IfOper extends Expression {
    private Expr condition;
    private ThenBlock thenBlock;
    private ElseBlock elseBlock;

    @Override
    public String name() {
        return "if"+" index: "+Utils.stackToString(this.index);
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        children.add(condition);
        children.add(thenBlock);
        if (elseBlock != null)
            children.add(elseBlock);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class ElseBlock extends Expression {
    private List<Expression> expressions;

    @Override
    public String name() {
        String returnStr="else ";
        if (this!=null )returnStr+=Utils.stackToString(this.index);
        return returnStr;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        children.addAll(expressions);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class ThenBlock extends Expression {
    private List<Expression> expressions;

    @Override
    public String name() {
        return "then index: "+Utils.stackToString(this.index);
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        children.addAll(expressions);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class BinaryExpr extends Expr {
    private Expr left;
    private Expr right;
    private String sign;

    @Override
    public String name() {
        return sign + typeOrNull() + castToIfNeed()+" index: "+Utils.stackToString(this.index);
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return Arrays.asList(left, right);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class NewVariable extends Expr {
    private String varVal;
    private VariableReference variable;
    private Type type;

    @Override
    public String name() {
        Stack<java.lang.Integer> st=this.index;
        String s=Utils.stackToString(this.index);
        return varVal + " " + variable.getVarName() + ":" + type.name() + typeOrNull() + castToIfNeed()
        +" index: "+Utils.stackToString(this.index);
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class VariableReference extends Expr {
    private String varName;
    @Override
    public String name() {
        String in=Utils.stackToString(this.index);
        return varName + typeOrNull() + castToIfNeed()+" index: "+Utils.stackToString(this.index);
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }

    @Override
    public String errorName() {
        return varName;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class IntegerVar extends Expr {
    private String value;

    @Override
    public String name() {
        return value + typeOrNull() + castToIfNeed()+" index: "+Utils.stackToString(this.index);
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class CharVar extends Expr {
    private String value;

    @Override
    public String name() {
        return value + typeOrNull() + castToIfNeed()+" index: "+Utils.stackToString(this.index);
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class DoubleVar extends Expr {
    private String value;

    @Override
    public String name() {
        return value + typeOrNull() + castToIfNeed()+" index: "+Utils.stackToString(this.index);
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class BooleanVar extends Expr {
    private String value;

    @Override
    public String name() {
        return value + typeOrNull() + castToIfNeed()+" index: "+Utils.stackToString(this.index);
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class FunCall extends Expr {
    private String name;
    private List<Expr> parameters;


    @Override
    public String name() {
//        String returnStr=className+"(";
//
//        if (parameters!=null && parameters.size()>0) {
//            for (Expr param : parameters) {
//                returnStr += param.className() + ", ";
//            }
//
//            returnStr = returnStr.substring(0, returnStr.length() - 2);
//        }
//            returnStr+=")";
//        return returnStr;
        return name + "( )" + typeOrNull() + castToIfNeed()+" index: "+Utils.stackToString(this.index);
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return parameters;
    }

//    @Override
//    public String errorName(){
//        String returnStr=name+"(";
//        if (this.parameters.size()>0){
//            returnStr+=this.getParameters().stream()
//                    .map(x->x.getType().name())
//                    .collect(Collectors.joining(","));
//        }
//        returnStr+=")";
//        return returnStr;
//    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class ArrayAccess extends Expr {
    private VariableReference variableReference;
    private Expr expr;

    @Override
    public String name() {
        return variableReference.getVarName() + "[ ]" + typeOrNull() + castToIfNeed()
                +" index: "+Utils.stackToString(this.index);
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        children.add(expr);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class ArrTypeSizeDefVal extends Expr {
    private Type nestedType;
    private List<Expr> exprList;

    @Override
    public String name() {
        return "Array<" + nestedType.name() + "> (  " + ", {  })" + typeOrNull() + castToIfNeed()
                +" index: "+Utils.stackToString(this.index);
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return exprList;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class ReturnExpr extends Expr {
    private Expr expr;

    @Override
    public String name() {
        return "return" + typeOrNull() + castToIfNeed()+" index: "+Utils.stackToString(this.index);
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        children.add(expr);
        return children;
    }
}

@NoArgsConstructor
class Integer extends Type {
    @Override
    public String name() {
        return "Int";
    }
}

@NoArgsConstructor
class Double extends Type {
    @Override
    public String name() {
        return "Double";
    }
}

@NoArgsConstructor
class Boolean extends Type {
    @Override
    public String name() {
        return "Boolean";
    }

}

@NoArgsConstructor
class Char extends Type {
    @Override
    public String name() {
        return "Char";
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class Array extends Type {
    private Type type;

    @Override
    public String name() {
        return "Array<" + type.name() + ">";
    }
}


@NoArgsConstructor
class Unit extends Type {
    @Override
    public String name() {
        return "Unit";
    }
}