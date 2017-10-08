package com.end.compiler;
import  io.bretty.console.tree.PrintableTreeNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface Node extends PrintableTreeNode {}
interface Statement extends Node {}
interface Expression extends Node, Statement {}

@Data
@AllArgsConstructor
@NoArgsConstructor
class BinaryExpression implements Expression{
    private Expression left;
    private Expression right;
    private String sign;

    @Override
    public String name() {
        return sign;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return Arrays.asList(left, right);
    }
}

abstract class Type implements  Node {
    public List<PrintableTreeNode> children(){
        return new ArrayList<>();
    }
    public String name(){
        return toString();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class FunParameter implements Node{
    private  VariableReference variableName;
    private Type type;

    @Override
    public String name() {
        return variableName.name()+":"+type.name();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class FunDeclaration implements Node{
    private VariableReference variableName;
    private Type returnType;
    private List<FunParameter> funParametersList;
    private List<Statement> statementList;
    @Override
    public String name() {
        return "fun"+variableName.name()+"("+funParametersList.toString()+") :"+returnType.name();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children=new ArrayList<PrintableTreeNode>();
        children.add(variableName);
        children.add(returnType);
        children.addAll(2, funParametersList);
        children.addAll(2+funParametersList.size(), statementList);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ClassDeclaration implements Node{

    private VariableReference name;
   // private
    @Override
    public String name() {
        return name.name();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Assignment implements Statement{
    private  VariableReference variableName;
    private  Expression value;
    @Override
    public String name() {
        return "=";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> childrenList=new ArrayList<PrintableTreeNode>();
        childrenList.add(value);
        childrenList.add(variableName);
        return childrenList;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class WhileLoop implements Statement{
    private Expression condition;
    private List<Statement> statements;
    @Override
    public String name() {
        return "while";
    }

    @Override
    public ArrayList<PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> childrenList=new ArrayList<PrintableTreeNode>();
        childrenList.add(condition);
        childrenList.addAll(1,statements);
        return  childrenList;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Declaration implements Statement{
    private String varVal;
    private VariableReference variableName;
    private Type type;
    private Expression expression;

    @Override
    public String name() {
        String returnStr= varVal+" "+variableName.name()+":"+type.name();
        if (expression!=new ArrayList<>()) returnStr+="="+ expression.name();
        return returnStr;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children=new ArrayList<PrintableTreeNode>();
        children.add(variableName);
        children.add(type);
        children.add(expression);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class VariableReference implements Expression{
    private String variableName;
    @Override
    public String name() {
        return variableName;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class IntegerVar implements Expression {
    private  String value;
    @Override
    public String name() {
        return value;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class DoubleVar implements Expression {
    private  String value;
    @Override
    public String name() {
        return value;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class BooleanVar implements Expression {
    private  String value;

    @Override
    public String name() {
        return value;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Variable implements Expression {
    private String value;

    @Override
    public String name() {
        return value;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Negation implements Expression{
    private Expression expr;
    @Override
    public String name() {
        return "!"+expr.name();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return Arrays.asList(expr);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class FunCall implements Expression{
    private  String name;
    private List<Expression> parameters;

    @Override
    public String name() {
        return name+"("+parameters.toString()+")";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children=new ArrayList<PrintableTreeNode>();
        children.addAll(0,parameters);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class  ArrayAccess implements  Expression{

    private String name;
    private Expression expression;
    @Override
    public String name() {
        return name+"["+expression.name()+"]";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children=new ArrayList<PrintableTreeNode>();
        children.add(expression);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ArrTypeSizeDefVal implements Expression{
    private Type type;
    private List<Expression> expressionList;
    @Override
    public String name() {
        return "Array<"+type.name()+"> ("+expressionList.get(0)+", {"+expressionList.get(1)+"})";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return expressionList;
    }
}



@NoArgsConstructor
class Integer extends Type {
    @Override
    public String name() {
        return super.name();
    }
}

@NoArgsConstructor
class Double extends Type {
    @Override
    public String name() {
        return super.name();
    }
}

@NoArgsConstructor
class Boolean extends Type {
    @Override
    public String name() {
        return super.name();
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Array extends Type {
    private Type type;
    @Override
    public String name() {
        return "Array<"+type.name()+">";
    }
}
