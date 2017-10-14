package com.end.compiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.end.compiler.KParser;

public class ToAst{

    public static VariableReference toAst(KParser.IdentContext ident) {
        return new VariableReference(ident.getText());
    }
    public static Expr toAst(KParser.NumberContext number){
        if (number instanceof KParser.IntegerLitContext)
            return new IntegerVar(number.getText());
        else if (number instanceof KParser.DoubleLitContext)
            return new DoubleVar(number.getText());
        else throw new UnsupportedOperationException();
    }
    public static Expr toAst(KParser.Char_varContext char_var){
        return  new CharVar(char_var.getText());
    }
    public static Expr toAst(KParser.Boolean_varContext boolean_var){
        return  new BooleanVar(boolean_var.getText());
    }
    public  static Expr toAst (KParser.Concrete_varContext concrete_var){
        if (concrete_var instanceof KParser.NumberLitContext)
            return toAst(((KParser.NumberLitContext) concrete_var).number());
        else if (concrete_var instanceof KParser.BooleanLitContext)
            return toAst(((KParser.BooleanLitContext) concrete_var).boolean_var());
        else if (concrete_var instanceof KParser.CharLitContext)
            return toAst(((KParser.CharLitContext) concrete_var).char_var());
            else throw new UnsupportedOperationException();
    }

    public static Expr toAst(KParser.VariableContext variable) {
        if (variable instanceof KParser.ConcreteVariableContext)
            return toAst(((KParser.ConcreteVariableContext) variable).concrete_var());
        else if (variable instanceof KParser.IdentifierContext)
            return toAst(((KParser.IdentifierContext) variable).ident());
        else throw new UnsupportedOperationException();
    }

    public static Expr toAst(KParser.ExprContext expr) {
        if (expr instanceof KParser.BinaryExprContext)
            return new BinaryExpr(ToAst.toAst(((KParser.BinaryExprContext) expr).left), ToAst.toAst(((KParser.BinaryExprContext) expr).right), ((KParser.BinaryExprContext)expr).operator.getText());
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

    public  static Expr toAst(KParser.Fun_callContext fun_call){
        if (fun_call.expr() != null)
            return new FunCall(fun_call.ident().getText(), fun_call.expr().stream().map(x -> toAst(x)).collect(Collectors.toList()));
        else return new FunCall(fun_call.ident().getText(), new ArrayList<>());
    }

    public static Expr toAst(KParser.Array_accessContext array_access){
        return new ArrayAccess(array_access.ident().getText(), toAst(array_access.expr()));
    }

    public static Expr toAst(KParser.Arr_type_size_def_valContext expr){
        return new ArrTypeSizeDefVal(toAst(expr.type()), expr.expr().stream().map(x->toAst(x)).collect(Collectors.toList()));
    }

    public static  Type toAst (KParser.TypeContext type){
        if (type instanceof KParser.IntTypeContext)
            return  new Integer();
        else if(type instanceof KParser.DoubleTypeContext)
            return new Double();
        else if (type instanceof KParser.BooleanTypeContext)
            return new Boolean();
        else if (type instanceof KParser.CharTypeContext)
            return new Char();
        else if (type instanceof KParser.ArrayTypeContext)
            return new Array((toAst(((KParser.ArrayTypeContext) type).type())));
        else throw new UnsupportedOperationException();
    }

   public static Expression toAst(KParser.DeclarationContext statement){
        if (statement.expr()!=null) {
            if (statement.KEYWORD_val() != null)
                return new Declaration(statement.KEYWORD_val().toString(), toAst(statement.ident()), toAst(statement.type()), toAst(statement.expr()));
            else if (statement.KEYWORD_var() != null)
                return new Declaration(statement.KEYWORD_var().toString(), toAst(statement.ident()), toAst(statement.type()), toAst(statement.expr()));
            else throw new UnsupportedOperationException();
        }
        else {
            if (statement.KEYWORD_val() != null)
                return new NewVariable(statement.KEYWORD_val().toString(), toAst(statement.ident()), toAst(statement.type()));
            else if (statement.KEYWORD_var() != null)
                return new NewVariable(statement.KEYWORD_var().toString(), toAst(statement.ident()), toAst(statement.type()));
            else throw new UnsupportedOperationException();
        }
    }

   public static Expression toAst (KParser.AssignmentContext assignment){
       if (assignment.ident()!=null)
       return new Assignment(toAst(assignment.ident()), toAst(assignment.expr()));
       else if (assignment.array_access()!=null)
           return new Assignment(toAst(assignment.array_access()), toAst(assignment.expr()));
       else throw new UnsupportedOperationException();
   }


   public static Expression toAst(KParser.ExpressionContext expression){
       if (expression instanceof KParser.AssigContext)
           return toAst(((KParser.AssigContext) expression).assignment());
       else if(expression instanceof KParser.DeclContext)
           return toAst(((KParser.DeclContext) expression).declaration());
      else if (expression instanceof KParser.IfElseContext)
          return  toAst(((KParser.IfElseContext) expression).if_else());
       else if (expression instanceof KParser.ExprExpContext)
           return toAst(((KParser.ExprExpContext) expression).expr());
       else if (expression instanceof KParser.LoopExpContext)
         return toAst(((KParser.LoopExpContext) expression).loop());
       else throw new UnsupportedOperationException();
   }


   public  static Expression toAst(KParser.LoopContext loop){
       if (loop instanceof KParser.WhileLoopContext)
           return toAst(((KParser.WhileLoopContext) loop).while_loop());
       else  if (loop instanceof KParser.ForLoopContext)
           return  toAst(((KParser.ForLoopContext) loop).for_loop());
     else if (loop instanceof KParser.DoWhileLoopContext)
         return  toAst(((KParser.DoWhileLoopContext) loop).do_while_loop());
       else throw new UnsupportedOperationException();
   }

   public static List<Expression> toAst (KParser.ExpressionsContext expressions){
      if (expressions.expression()!=null)
          return  expressions.expression().stream().map(x->toAst(x)).collect(Collectors.toList());
      else return  new ArrayList<>();
   }

   public static List<Expression> toAst (KParser.BlockContext block){
       return toAst(block.expressions());
   }

   public static Expression toAst(KParser.If_elseContext if_elseContext){
       if (if_elseContext.firstExpression!=null && if_elseContext.secondExpression!=null)
           return new IfElse(toAst(if_elseContext.expr()), Arrays.asList(toAst(if_elseContext.firstExpression)),new ElseBlock(Arrays.asList(toAst(if_elseContext.secondExpression))));
      else if (if_elseContext.firstBlock!=null && if_elseContext.secondBlock!=null)
           return new IfElse(toAst(if_elseContext.expr()), toAst(if_elseContext.firstBlock),new ElseBlock(toAst(if_elseContext.secondBlock)));
      else if (if_elseContext.firstExpression!=null && if_elseContext.secondBlock!=null)
           return new IfElse(toAst(if_elseContext.expr()), Arrays.asList(toAst(if_elseContext.firstExpression)),new ElseBlock(toAst(if_elseContext.secondBlock)));
      else if (if_elseContext.firstBlock!=null && if_elseContext.secondExpression!=null)
           return new IfElse(toAst(if_elseContext.expr()), toAst(if_elseContext.firstBlock),new ElseBlock(toAst(if_elseContext.secondBlock)));
      else if (if_elseContext.firstBlock!=null)// && if_elseContext.secondExpression==null && if_elseContext.secondBlock==null
           return new IfElse(toAst(if_elseContext.expr()), toAst(if_elseContext.firstBlock),null);
       else if (if_elseContext.firstExpression!=null)// && if_elseContext.secondExpression==null && if_elseContext.secondBlock==null
           return new IfElse(toAst(if_elseContext.expr()), Arrays.asList(toAst(if_elseContext.firstExpression)),null);
       else throw new UnsupportedOperationException();
   }

   public  static Expression toAst(KParser.While_loopContext while_loop){
       if (while_loop.expression()==null)
           return new WhileLoop(toAst(while_loop.expr()), while_loop.block().expressions().expression().stream().map(x->toAst(x)).collect(Collectors.toList()));
       else  if (while_loop.block()==null)
       return  new WhileLoop(toAst(while_loop.expr()), Arrays.asList(toAst(while_loop.expression())));
       else throw new UnsupportedOperationException();
   }

   public static Expression toAst (KParser.For_loopContext forLoop){
       if (forLoop.expression()==null)
           return new ForLoop(forLoop.ident().stream().map(x->toAst(x)).collect(Collectors.toList()), forLoop.block().expressions().expression().stream().map(x->toAst(x)).collect(Collectors.toList()));
       else  if (forLoop.block()==null)
           return  new ForLoop(forLoop.ident().stream().map(x->toAst(x)).collect(Collectors.toList()), Arrays.asList(toAst(forLoop.expression())));
       else throw new UnsupportedOperationException();
   }

   public  static Expression toAst (KParser.Do_while_loopContext do_while_loop){
       return  new DoWhileLoop(toAst(do_while_loop.expr()), toAst(do_while_loop.block()));
   }

   public static Node toAst (KParser.Fun_parameterContext fun_parameter){
       return new FunParameter(toAst(fun_parameter.ident()), toAst(fun_parameter.type()));
    }

    /*public  static Node toAst(KParser.Fun_parametersContext fun_parameters) {
       if (fun_parameters.fun_parameter()!=null)
           return new FunParameters(fun_parameters.fun_parameter().stream().map(x->toAst(x)).collect(Collectors.toList()));
       else return new FunParameters();
    }*/

    public  static Node toAst(KParser.Fun_declarationContext fun_declaration){
       if (fun_declaration.type()!=null && fun_declaration.KEYWORD_return()!=null)
       return  new FunDeclaration(toAst(fun_declaration.ident()), toAst(fun_declaration.type()),
               fun_declaration.fun_parameters().fun_parameter().stream().map(x->toAst(x)).collect(Collectors.toList()),
               fun_declaration.expressions().expression().stream().map(x->toAst(x)).collect(Collectors.toList()), new ReturnExpr(toAst(fun_declaration.expr())));

       else if (fun_declaration.type()!=null && fun_declaration.KEYWORD_return()==null)
       return new FunDeclaration(toAst(fun_declaration.ident()), toAst(fun_declaration.type()),
               fun_declaration.fun_parameters().fun_parameter().stream().map(x->toAst(x)).collect(Collectors.toList()),
               fun_declaration.expressions().expression().stream().map(x->toAst(x)).collect(Collectors.toList()),null);

       else if (fun_declaration.type()==null && fun_declaration.KEYWORD_return()!=null)
           return new FunDeclaration(toAst(fun_declaration.ident()), null,
                   fun_declaration.fun_parameters().fun_parameter().stream().map(x->toAst(x)).collect(Collectors.toList()),
                   fun_declaration.expressions().expression().stream().map(x->toAst(x)).collect(Collectors.toList()),new ReturnExpr(toAst(fun_declaration.expr())));
       else if (fun_declaration.type()==null && fun_declaration.KEYWORD_return()==null)
           return new FunDeclaration(toAst(fun_declaration.ident()), null,
                   fun_declaration.fun_parameters().fun_parameter().stream().map(x->toAst(x)).collect(Collectors.toList()),
                   fun_declaration.expressions().expression().stream().map(x->toAst(x)).collect(Collectors.toList()),null);
       else throw new UnsupportedOperationException();
    }


    public  static Node toAst(KParser.Class_declarationContext class_declaration){
        if(class_declaration.class_body().declaration()!=null)
        return new ClassDeclaration(toAst(class_declaration.ident()),
                class_declaration.class_body().fun_declaration().stream().map(x->toAst(x)).collect(Collectors.toList()),
                null);
        else  return new ClassDeclaration(toAst(class_declaration.ident()),null,
                class_declaration.class_body().declaration().stream().map(x->toAst(x)).collect(Collectors.toList()));
    }

    public static  Node toAst(KParser.ProgramContext program){
        return new Program(program.class_declaration().stream().map(x->toAst(x)).collect(Collectors.toList()));
    }
}
