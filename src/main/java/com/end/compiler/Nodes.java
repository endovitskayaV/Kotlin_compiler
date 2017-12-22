package com.end.compiler;

import io.bretty.console.tree.PrintableTreeNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
abstract class Node implements PrintableTreeNode {
    protected Node parent;
    protected Position position;
}

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
abstract class Expression extends Node {
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
        if (type != null) return " type:" + type.name();
        else return "";
    }

    public String castToIfNeed() {
        if (castTo != null) return " cast to:" + castTo.name();
        else return "";
    }

    // public String errorName(){return this.nestedType.name();}
}

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
abstract class Type extends Node {
    public List<PrintableTreeNode> children() {
        return new ArrayList<>();
    }

    public String name() {
        return getClass().getName();
    }
    //public String errorName(){return this.name();}
}

interface Indexable {
    void fillIndex(String index);

    String indexStr();

}

interface Constantable {
    void fillCILValue(String CILValue);

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class FunDeclaration extends Node {
    private List<Annotation> annotationList;
    private List<Modificator> modificatorList;
    private VariableReference funName;
    private Type returnType;
    private List<FunParameter> funParametersList;
    private List<Expression> expressionList;
    private ReturnExpr returnExpr;

    @Override
    public String name() {
        StringBuilder returnStr = new StringBuilder("fun " + funName.getVarName() + "(");
        if (funParametersList.size() > 0) {
            for (Node funParam : funParametersList)
                returnStr.append(funParam.name()).append(", ");
            returnStr = new StringBuilder(returnStr.substring(0, returnStr.length() - 2));
        }
        returnStr.append(") :");
        if (returnType != null) returnStr.append(returnType.name());
        else returnStr.append("Unit");
        return returnStr.toString();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        if (annotationList != null) children.addAll(annotationList);
        if (modificatorList != null) children.addAll(modificatorList);
        if (expressionList != null) children.addAll(0, expressionList);
        if (returnExpr != null)
            children.add(returnExpr);
        return children;
    }

    @Override
    public String toString() {
        StringBuilder returnStr = new StringBuilder("fun " + funName.getVarName() + "(");
        if (funParametersList.size() > 0) {
            for (FunParameter funParam : funParametersList)
                returnStr.append(funParam.getType().name()).append(", ");
            returnStr = new StringBuilder(returnStr.substring(0, returnStr.length() - 2));
        }
        returnStr.append(") :");
        if (returnType != null) returnStr.append(returnType.name());
        else returnStr.append("Unit");
        return returnStr.toString();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class ClassDeclaration extends Node {

    private VariableReference className;
    private List<Declaration> propertiesDecls;
    private List<FunDeclaration> funDeclarations;

    @Override
    public String name() {
        return "class " + className.getVarName();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        if (propertiesDecls != null) children.addAll(propertiesDecls);
        if (funDeclarations != null) children.addAll(funDeclarations);
        return children;
    }

    @Override
    public String toString() {
        return name();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class FunSignature extends Node {
    private Annotation annotation;
    private VariableReference funName;
    private Type returnType;
    private List<FunParameter> funParametersList;

    @Override
    public String name() {
        StringBuilder returnStr = new StringBuilder("fun " + funName.getVarName() + "(");
        if (funParametersList.size() > 0) {
            for (Node funParam : funParametersList)
                returnStr.append(funParam.name()).append(", ");
            returnStr = new StringBuilder(returnStr.substring(0, returnStr.length() - 2));
        }
        returnStr.append(") :");
        if (returnType != null) returnStr.append(returnType.name());
        else returnStr.append("Unit");
        return returnStr.toString();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        if (annotation != null) children.add(annotation);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class InterfaceDeclaration extends Node {
    private VariableReference interfaceName;
    private List<Declaration> propertiesDecls;
    private List<FunSignature> funSignatureList;

    @Override
    public String name() {
        return "interface " + interfaceName.getVarName();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        if (propertiesDecls != null) children.addAll(propertiesDecls);
        if (funSignatureList != null) children.addAll(funSignatureList);
        return children;
    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class Program extends Node {

    private List<ClassDeclaration> classDeclarationList;
    private List<FunDeclaration> funDeclarationList;
    private List<InterfaceDeclaration> interfaceDeclarationList;

    @Override
    public String name() {
        return "program ";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        if (interfaceDeclarationList != null) children.addAll(interfaceDeclarationList);
        if (classDeclarationList != null) children.addAll(classDeclarationList);
        if (funDeclarationList != null) children.addAll(funDeclarationList);
        return children;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class Annotation extends Node {

    private String name;
    private String parameter;

    @Override
    public String name() {
        return "@" + name;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class Modificator extends Node {
    private String name;

    @Override
    public String name() {
        return name;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class WhileLoop extends Expression {
    private Expr condition;
    private List<Expression> expressions;
    private VariableReference conditionVar;

    public WhileLoop(Expr condition, List<Expression> expressions) {
        setCondition(condition);
        setExpressions(expressions);

        conditionVar = new VariableReference("");
        conditionVar.setVisibility(Visibility.LOCAL);
        conditionVar.setType(new Boolean());
    }

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
@EqualsAndHashCode(callSuper = true)
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
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class DoWhileLoop extends Expression {
    private Expr condition;
    private List<Expression> expressions;
    private VariableReference conditionVar;

    public DoWhileLoop(Expr condition, List<Expression> expressions) {
        setCondition(condition);
        setExpressions(expressions);

        conditionVar = new VariableReference("");
        conditionVar.setVisibility(Visibility.LOCAL);
        conditionVar.setType(new Boolean());
    }


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
@EqualsAndHashCode(callSuper = true)
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
        if (expr != null) children.add(expr);
        return children;
    }

    @Override
    public String toString() {
        return newVariable.toString();
    }
}

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class IfOper extends Expression {
    private Expr condition;
    private ThenBlock thenBlock;
    private ElseBlock elseBlock;
    private VariableReference conditionVar;

    public IfOper(Expr condition, ThenBlock thenBlock, ElseBlock elseBlock) {
        this.condition = condition;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
        conditionVar = new VariableReference("");
        conditionVar.setVisibility(Visibility.LOCAL);
        conditionVar.setType(new Boolean());
    }

    @Override
    public String name() {
        return "if";
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
@EqualsAndHashCode(callSuper = true)
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
@EqualsAndHashCode(callSuper = true)
class ThenBlock extends Expression {
    private List<Expression> expressions;

    @Override
    public String name() {
        return "then";
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        children.addAll(expressions);
        return children;
    }
}

enum Visibility {LOCAL, ARG}

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class FunParameter extends Expr {
    private VariableReference variable;
    private Type type;

    public FunParameter(VariableReference variable, Type type) {
        setVariable(variable);
        setType(type);
        this.variable.visibility = Visibility.ARG;
    }

    @Override
    public String name() {
        return variable.getVarName() + ":" + type.name() + variable.indexStr();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return variable.getVarName();
    }


}


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
@EqualsAndHashCode(callSuper = true)
class NewVariable extends Expr {
    private String varVal;
    private VariableReference variable;
    private Type type;

    @Override
    public String name() {
        return varVal + " " + variable.getVarName() + typeOrNull() + castToIfNeed()
                + variable.indexStr();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return variable.getVarName();
    }
}

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class VariableReference extends Expr implements Indexable {
    private String varName;
    private String index = "-1";
    Visibility visibility;

    public VariableReference(String varName) {
        setVarName(varName);
    }

    @Override
    public String name() {
        String name = varName + typeOrNull() + castToIfNeed() + indexStr();
        if (visibility != null) name += " " + visibility.toString().toLowerCase();
        return name;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }

    @Override
    public void fillIndex(String index) {
        this.index = index;
    }

    @Override
    public String indexStr() {
        return " index:" + index;
    }

//    @Override
//    public String errorName() {
//        return varName;
//    }
}

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class IntegerVar extends Expr implements Constantable {
    private String value;
    private String CILValue;

    public IntegerVar(String value) {
        setValue(value);
    }

    @Override
    public String name() {
        return value + typeOrNull() + castToIfNeed() + " const " + CILValue;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }


    @Override
    public void fillCILValue(String CILValue) {
        this.CILValue = CILValue;
    }
}

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class CharVar extends Expr implements Constantable {
    private String value;
    private String CILValue;

    public CharVar(String value) {
        setValue(value);
    }

    @Override
    public String name() {
        return value + typeOrNull() + castToIfNeed() + " const " + CILValue;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }


    @Override
    public void fillCILValue(String CILValue) {
        this.CILValue = CILValue;
    }
}


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class StringVar extends Expr implements Constantable {
    private String value;
    private String CILValue;

    public StringVar(String value) {
        setValue(value);
    }

    @Override
    public String name() {
        return value + typeOrNull() + castToIfNeed() + " const " + CILValue;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }


    @Override
    public void fillCILValue(String CILValue) {
        this.CILValue = CILValue;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class DoubleVar extends Expr implements Constantable {
    private String value;
    private String CILValue;

    public DoubleVar(String value) {
        setValue(value);
    }

    @Override
    public String name() {
        return value + typeOrNull() + castToIfNeed() + " const " + CILValue;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }

    @Override
    public void fillCILValue(String CILValue) {
        this.CILValue = CILValue;
    }
}

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class BooleanVar extends Expr implements Constantable {
    private String value;
    private String CILValue;

    public BooleanVar(String value) {
        setValue(value);
    }

    @Override
    public String name() {
        return value + typeOrNull() + castToIfNeed() + " const " + CILValue;
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        return new ArrayList<>();
    }


    @Override
    public void fillCILValue(String CILValue) {
        this.CILValue = CILValue;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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

//    @Override
//    public String errorName(){
//        String returnStr=name+"(";
//        if (this.parameters.size()>0){
//            returnStr+=this.getParameters().stream()
//                    .map(x->x.getNestedType().name())
//                    .collect(Collectors.joining(","));
//        }
//        returnStr+=")";
//        return returnStr;
//    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class ArrayAccess extends Expr {
    private VariableReference variableReference;
    private Expr expr;

    @Override
    public String name() {
        return "[ ]" + typeOrNull() + castToIfNeed();
    }

    @Override
    public List<? extends PrintableTreeNode> children() {
        ArrayList<PrintableTreeNode> children = new ArrayList<>();
        children.add(variableReference);
        children.add(expr);
        return children;
    }


}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class ArrayInitailization extends Expr {
    private Type nestedType;
    //private List<Expr> exprList;
    private Expr expr;

    @Override
    public String name() {
        return typeOrNull() + castToIfNeed();
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
@EqualsAndHashCode(callSuper = true)
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

@NoArgsConstructor
class StringType extends Type {
    @Override
    public String name() {
        return "String";
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class Array extends Type {
    private Type nestedType;

    @Override
    public String name() {
        return "Array<" + nestedType.name() + ">";
    }
}


@NoArgsConstructor
class Unit extends Type {
    @Override
    public String name() {
        return "Unit";
    }
}