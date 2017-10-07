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

class FunParameter implements Node{

    private  VariableReference variableName;

    public VariableReference getVariableName() {
        return variableName;
    }

    public void setVariableName(VariableReference variableName) {
        this.variableName = variableName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

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
class FunDeclaration implements Node{
    public VariableReference getVariableName() {
        return variableName;
    }

    public void setVariableName(VariableReference variableName) {
        this.variableName = variableName;
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public List<FunParameter> getFunParametersList() {
        return funParametersList;
    }

    public void setFunParametersList(List<FunParameter> funParametersList) {
        this.funParametersList = funParametersList;
    }

    public List<Statement> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<Statement> statementList) {
        this.statementList = statementList;
    }

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

class Assignment implements Statement{

    public VariableReference getVariableName() {
        return variableName;
    }

    public void setVariableName(VariableReference variableName) {
        this.variableName = variableName;
    }

    public Expression getValue() {
        return value;
    }

    public void setValue(Expression value) {
        this.value = value;
    }

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
class WhileLoop implements Statement{

    private Expression condition;

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

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
class Declaration implements Statement{

    public VariableReference getVariableName() {
        return variableName;
    }

    public void setVariableName(VariableReference variableName) {
        this.variableName = variableName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    private VariableReference variableName;
    private Type type;
    private Expression expression;

    @Override
    public String name() {
        String returnStr= variableName.name()+":"+type.name();
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

@AllArgsConstructor
class VariableReference implements Expression{
    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

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

class IntegerVar implements Expression {
    private  String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String name() {
        return value;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}
class DoubleVar implements Expression {
    private  String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String name() {
        return value;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}
class BooleanVar implements Expression {
    private  String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String name() {
        return value;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}


class Integer extends Type {
    @Override
    public String name() {
        return super.name();
    }
}
class Double extends Type {

    @Override
    public String name() {
        return super.name();
    }
}
class Boolean extends Type {

    @Override
    public String name() {
        return super.name();
    }

}
class Array extends Type {

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    private Type type;
    @Override
    public String name() {
        return "Array<"+type.name()+">";
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