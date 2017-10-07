package com.end.compiler;

import com.end.compiler.KParser;
public class ToAst{

    public static Expression toAst(KParser.IdentContext ident) {
        return new VariableReference(ident.getText());
    }

    public static Expression toAst(KParser.ExprContext expr) {
        if (expr instanceof KParser.BinaryExprContext)
            return new BinaryExpression(ToAst.toAst(((KParser.BinaryExprContext) expr).left), ToAst.toAst(((KParser.BinaryExprContext) expr).right), ((KParser.BinaryExprContext)expr).operator.getText());
        else if (expr instanceof KParser.VarContext)
            return toAst(((KParser.VarContext) expr).variable());
        else throw new UnsupportedOperationException();
    }

    public static Expression toAst(KParser.VariableContext expr) {
        if (expr instanceof KParser.ConcreteVariableContext)
            return new Variable(expr.getText());
        else throw new UnsupportedOperationException();
    }



}