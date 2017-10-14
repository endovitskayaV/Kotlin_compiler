package com.end.compiler;
import  io.bretty.console.tree.PrintableTreeNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface Node extends PrintableTreeNode {}
interface Expression extends Node {}
interface Expr extends Node, Expression {}

abstract class Type implements  Node {
    public List<PrintableTreeNode> children(){
        return new ArrayList<>();
    }
    public String name(){
        return getClass().getName();
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

/*
@Data
@AllArgsConstructor
@NoArgsConstructor
class FunParameters implements Node{
    private  List<Node> funParameters;
    @Override
    public String name(){
        return funParameters.toString();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
class FunDeclaration implements Node{
    private VariableReference funName;
    private Type returnType;
    private List<Node> funParametersList;
    private List<Expression> expressionList;
    private  ReturnExpr returnExpr;
    @Override

    public String name() {
        String returnStr="fun "+ funName.name()+"(";
        if (funParametersList.size()>0) {
            for (Node funParam : funParametersList)
                returnStr += funParam.name() + ", ";
            returnStr = returnStr.substring(0, returnStr.length() - 2);
        }
        returnStr+=") :";
        if (returnType!=null)returnStr+=returnType.name();
        else returnStr+="Unit";
        return returnStr;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children=new ArrayList<PrintableTreeNode>();
        children.addAll(0, expressionList);
        if (returnExpr!=null)
            children.add(returnExpr);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ClassDeclaration implements Node{

    private VariableReference name;
    private List<Node> propertiesDecls;
    private List<Node> funDeclarations;
    @Override
    public String name() {
        return "class "+name.name();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children=new ArrayList<PrintableTreeNode>();
        if(propertiesDecls!=null) children.addAll(propertiesDecls);
        if(funDeclarations!=null) children.addAll(funDeclarations);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Program implements Node{

    private List<Node> classDeclarations;
    @Override
    public String name() {
        return "program";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return classDeclarations;
    }
}



@Data
@AllArgsConstructor
@NoArgsConstructor
class Assignment implements Expression {
    private  Expr left;
    private Expr value;
    @Override
    public String name() {
        return "=";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> childrenList=new ArrayList<PrintableTreeNode>();
        childrenList.add(left);
        childrenList.add(value);
        return childrenList;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class WhileLoop implements Expression {
    private Expr condition;
    private List<Expression> expressions;
    @Override
    public String name() {
        return "while";
    }

    @Override
    public ArrayList<PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> childrenList=new ArrayList<PrintableTreeNode>();
        childrenList.add(condition);
        childrenList.addAll(1, expressions);
        return  childrenList;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ForLoop implements Expression {
    private List<VariableReference> idents;
    private List <Expression> block;
    @Override
    public String name() {
        return "for";
    }

    @Override
    public ArrayList<PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> childrenList=new ArrayList<PrintableTreeNode>();
        childrenList.addAll(idents);
        childrenList.addAll(idents.size(), block);
        return  childrenList;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class DoWhileLoop implements Expression {
    private Expr expr;
    private List<Expression> block;
    @Override
    public String name() {
        return "do-while";
    }

    @Override
    public ArrayList<PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> childrenList=new ArrayList<PrintableTreeNode>();
        childrenList.add(expr);
        childrenList.addAll(1,block);
        return  childrenList;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Declaration implements Expression {
    private  String varVal;
    private VariableReference variable;
    private Type type;
    private Expr expr;

    @Override
    public String name() {
        return "=";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children=new ArrayList<PrintableTreeNode>();
        NewVariable newVariable=new NewVariable(varVal,variable,type);
        children.add(newVariable);
       // children.add(type);
        children.add(expr);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class IfElse implements Expression {
    private  Expr condition;
    private  List<Expression> thenExpression;
    private ElseBlock elseExpression;
    @Override
    public String name() {
        return "if";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children=new ArrayList<PrintableTreeNode>();
        children.add(condition);
        children.addAll(thenExpression);
        if(elseExpression !=null)
        children.add(elseExpression);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ElseBlock implements Expression{
    private List<Expression> expressions;
    @Override
    public String name() {
        return "else";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children=new ArrayList<PrintableTreeNode>();
        children.addAll(expressions);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class BinaryExpr implements Expr {
    private Expr left;
    private Expr right;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
class NewVariable implements Expr {
   private  String varVal;
   private VariableReference variable;
   private Type type;
    @Override
    public String name() {
        return varVal+" "+variable.name()+":"+type.name();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class VariableReference implements Expr {
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
class IntegerVar implements Expr {
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
class CharVar implements Expr {
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
class DoubleVar implements Expr {
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
class BooleanVar implements Expr {
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
class Negation implements Expr {
    private Expr expr;
    @Override
    public String name() {
        return "!";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return Arrays.asList(expr);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class FunCall implements Expr {
    private  String name;
    private List<Expr> parameters;

    @Override
    public String name() {
        String returnStr=name+"(";

        if (parameters!=null && parameters.size()>0) {
            for (Expr param : parameters) {
                returnStr += param.name() + ", ";
            }

            returnStr = returnStr.substring(0, returnStr.length() - 2);
        }
            returnStr+=")";
        return returnStr;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class  ArrayAccess implements Expr {
    private String name;
    private Expr expr;
    @Override
    public String name() {
        return name+"[ ]";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children=new ArrayList<PrintableTreeNode>();
        children.add(expr);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ArrTypeSizeDefVal implements Expr {
    private Type type;
    private List<Expr> exprList;
    @Override
    public String name() {
        return "Array<"+type.name()+"> (  "+", {  })";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return exprList;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ReturnExpr implements Expr{
    private Expr expr;
    @Override
    public String name() {
        return "return";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children=new ArrayList<PrintableTreeNode>();
        children.add(expr);
        return children;
    }
}

@NoArgsConstructor
class Integer extends Type {
    @Override
    public String name() {
        return "Integer";
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
class Array extends Type {
    private Type type;
    @Override
    public String name() {
        return "Array<"+type.name()+">";
    }
}
