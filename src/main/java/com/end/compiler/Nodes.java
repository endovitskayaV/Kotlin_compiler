package com.end.compiler;

import io.bretty.console.tree.PrintableTreeNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
abstract class Node implements PrintableTreeNode {
    protected Node parent;
    protected Position position;
}

@Data
@AllArgsConstructor
abstract class Expression extends Node {
}

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}

@Data
@AllArgsConstructor
abstract class Type extends Node {
    public List<PrintableTreeNode> children() {
        return new ArrayList<>();
    }

    public String name() {
        return getClass().getName();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class FunParameter extends Node {
    private VariableReference variableName;
    private Type type;

    @Override
    public String name() {
        return variableName.name() + ":" + type.name();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class FunDeclaration extends Node {
    private VariableReference funName;
    private Type returnType;
    private List<FunParameter> funParametersList;
    private List<Expression> expressionList;
    private ReturnExpr returnExpr;

    @Override

    public String name() {
        String returnStr = "fun " + funName.name() + "(";
        if (funParametersList.size() > 0) {
            for (Node funParam : funParametersList)
                returnStr += funParam.name() + ", ";
            returnStr = returnStr.substring(0, returnStr.length() - 2);
        }
        returnStr += ") :";
        if (returnType != null) returnStr += returnType.name();
        else returnStr += "Unit";
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
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ClassDeclaration extends Node {

    private VariableReference className;
    private List<Node> propertiesDecls;
    private List<Node> funDeclarations;

    @Override
    public String name() {
        return "class " + className.name();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        if (propertiesDecls != null) children.addAll(propertiesDecls);
        if (funDeclarations != null) children.addAll(funDeclarations);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Program extends Node {

    private List<ClassDeclaration> classDeclarationList;
    private List<FunDeclaration> funDeclarationList;

    @Override
    public String name() {
        return "program";
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
class WhileLoop extends Expression {
    private Expr condition;
    private List<Expression> expressions;

    @Override
    public String name() {
        return "while";
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
class ForLoop extends Expression {
    private VariableReference iterator;
    private Expr iterable;
    private List<Expression> expressions;

    @Override
    public String name() {
        return "for";
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
class DoWhileLoop extends Expression {
    private Expr condition;
    private List<Expression> expressions;

    @Override
    public String name() {
        return "do-while";
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
class Declaration extends Expression {
    private String varVal;
    private VariableReference variable;
    private Type type;
    private Expr expr;

    @Override
    public String name() {
        return "=";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        NewVariable newVariable = new NewVariable(varVal, variable, type);
        children.add(newVariable);
        // children.add(type);
        children.add(expr);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class IfElse extends Expression {
    private Expr condition;
    private List<Expression> thenExpression;
    private ElseBlock elseBlock;

    @Override
    public String name() {
        return "if";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        children.add(condition);
        children.addAll(thenExpression);
        if (elseBlock != null)
            children.add(elseBlock);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ElseBlock extends Expression {
    private List<Expression> expressions;

    @Override
    public String name() {
        return "else";
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
class BinaryExpr extends Expr {
    private Expr left;
    private Expr right;
    private String sign;

    @Override
    public String name() {
        return sign + typeOrNull() + castToIfNeed();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return Arrays.asList(left, right);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class NewVariable extends Expr {
    private String varVal;
    private VariableReference variable;
    private Type type;

    @Override
    public String name() {
        return varVal + " " + variable.name() + ":" + type.name() + typeOrNull() + castToIfNeed();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class VariableReference extends Expr {
    private String name;
    @Override
    public String name() {
        return name + typeOrNull() + castToIfNeed();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class IntegerVar extends Expr {
    private String value;

    @Override
    public String name() {
        return value + typeOrNull() + castToIfNeed();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class CharVar extends Expr {
    private String value;

    @Override
    public String name() {
        return value + typeOrNull() + castToIfNeed();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class DoubleVar extends Expr {
    private String value;

    @Override
    public String name() {
        return value + typeOrNull() + castToIfNeed();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class BooleanVar extends Expr {
    private String value;

    @Override
    public String name() {
        return value + typeOrNull() + castToIfNeed();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
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

        return name + "( )" + typeOrNull() + castToIfNeed();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return parameters;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ArrayAccess extends Expr {
    private String name;
    private Expr expr;

    @Override
    public String name() {
        return name + "[ ]" + typeOrNull() + castToIfNeed();
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
class ArrTypeSizeDefVal extends Expr {
    private Type type;
    private List<Expr> exprList;

    @Override
    public String name() {
        return "Array<" + type.name() + "> (  " + ", {  })" + typeOrNull() + castToIfNeed();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return exprList;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ReturnExpr extends Expr {
    private Expr expr;

    @Override
    public String name() {
        return "return" + typeOrNull() + castToIfNeed();
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
        return "Array<" + type.name() + ">";
    }
}
