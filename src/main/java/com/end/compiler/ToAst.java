package com.end.compiler;

import com.end.compiler.KParser;

import java.util.ArrayList;
import java.util.List;

public class ToAst{

    public static VariableReference toAst(KParser.IdentContext ident) {
        return new VariableReference(ident.getText());
    }
    public static Expression toAst(KParser.NumberContext number){
        if (number instanceof KParser.IntegerLitContext)
            return new IntegerVar(number.getText());
        else if (number instanceof KParser.DoubleLitContext)
            return new DoubleVar(number.getText());
        else throw new UnsupportedOperationException();
    }
    public static Expression toAst(KParser.Boolean_varContext boolean_var){
        return  new BooleanVar(boolean_var.getText());
    }
    public  static  Expression toAst (KParser.Concrete_varContext concrete_var){
        if (concrete_var instanceof KParser.NumberLitContext)
            return toAst(((KParser.NumberLitContext) concrete_var).number());
        else if (concrete_var instanceof KParser.BooleanLitContext)
            return toAst(((KParser.BooleanLitContext) concrete_var).boolean_var());
            else throw new UnsupportedOperationException();
    }

    public static Expression toAst(KParser.VariableContext variable) {
        if (variable instanceof KParser.ConcreteVariableContext)
            return toAst(((KParser.ConcreteVariableContext) variable).concrete_var());
        else if (variable instanceof KParser.IdentifierContext)
            return toAst(((KParser.IdentifierContext) variable).ident());
        else throw new UnsupportedOperationException();
    }

    public static Expression toAst(KParser.ExprContext expr) {
        if (expr instanceof KParser.BinaryExprContext)
            return new BinaryExpression(ToAst.toAst(((KParser.BinaryExprContext) expr).left), ToAst.toAst(((KParser.BinaryExprContext) expr).right), ((KParser.BinaryExprContext)expr).operator.getText());
        else if (expr instanceof KParser.VarContext)
            return toAst(((KParser.VarContext) expr).variable());
        else if (expr instanceof KParser.ParenExprContext)
            return toAst(((KParser.ParenExprContext) expr).expr());
        else if (expr instanceof KParser.NegContext)
            return new Negation(ToAst.toAst(((KParser.NegContext) expr).expr()));
        else if (expr instanceof KParser.FuncCallContext)
            return toAst(((KParser.FuncCallContext) expr).fun_call());
        else if (expr instanceof KParser.ArrayAccessContext)
            return toAst(((KParser.ArrayAccessContext) expr).array_access());
        else if (expr instanceof KParser.ArrTypeSizeDefValContext)
            return  toAst(((KParser.ArrTypeSizeDefValContext) expr).arr_type_size_def_val());
        else throw new UnsupportedOperationException();
    }

    public  static  Expression toAst(List<KParser.Fun_callContext> fun_call){
        return new FunCall(fun_call.ident );
    }

    public static Expression toAst(KParser.Array_accessContext array_access){
        return new ArrayAccess(array_access.ident().getText(), toAst(array_access));
    }

    public static  Expression toAst(KParser.Arr_type_size_def_valContext expr){
        return new ArrTypeSizeDefVal(expr.type(), )
    }

    public static  Type toAst (KParser.TypeContext type){
        if (type instanceof KParser.IntTypeContext)
            return  new Integer();
        else if(type instanceof KParser.DoubleTypeContext)
            return new Double();
        else if (type instanceof KParser.BooleanTypeContext)
            return new Boolean();
        else if (type instanceof KParser.ArrayTypeContext)
            return new Array((toAst(type)));
        else throw new UnsupportedOperationException();
    }

   public static Statement toAst(KParser.DeclarationContext statement){
        if (statement.KEYWORD_val()!=null)
           return  new Declaration(statement.KEYWORD_val().toString(),toAst(statement.ident()), toAst(statement.type()),toAst(statement.expr()));
        else if (statement.KEYWORD_var()!=null)
            return  new Declaration(statement.KEYWORD_var().toString(),toAst(statement.ident()), toAst(statement.type()),toAst(statement.expr()));
        else throw new UnsupportedOperationException();
    }

   public static Statement toAst (KParser.AssignmentContext assignment){
       return new Assignment(toAst(assignment.ident()), toAst(assignment.expr()));
   }


   public static Expression toAst(KParser.ExpressionContext expression){
       if (expression instanceof KParser.AssigContext)
           return toAst(((KParser.AssigContext) expression).assignment().expr());
       else if(expression instanceof KParser.DeclContext)
           return toAst(((KParser.DeclContext) expression).declaration().expr());
       //else if (expression instanceof KParser.IfElseContext)
         //  return  to
       else if (expression instanceof KParser.ExprExpContext)
           return toAst(((KParser.ExprExpContext) expression).expr());
       //else if (expression instanceof KParser.LoopExpContext)
         //  return toAst()
       else throw new UnsupportedOperationException();
   }


   public static Expression toAst (KParser.ExpressionsContext expressions){
       return toAst(expressions.exp);
   }

   public static Expression toAst (KParser.BlockContext block){
       return toAst(block.expressions());
   }
    public static
}
