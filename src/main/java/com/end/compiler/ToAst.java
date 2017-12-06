package com.end.compiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.end.compiler.KParser;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xpath.internal.operations.Variable;
import org.jetbrains.annotations.*;

class ToAst {

    @NotNull
    private static VariableReference toAst(com.end.compiler.KParser.IdentContext identContext) {
        VariableReference variableReference =
                new VariableReference(identContext.getText());
        Utils.setPosition(variableReference, identContext);
        Utils.setChildrensParent(variableReference);
        return variableReference;
    }

    @NotNull
    @Contract("null -> fail")
    private static Expr toAst(KParser.NumberContext numberContext) {
        if (numberContext instanceof KParser.IntegerLitContext) {
            IntegerVar integerVar = new IntegerVar(numberContext.getText());
            Utils.setPosition(integerVar, numberContext);
            Utils.setChildrensParent(integerVar);
            return integerVar;
        } else if (numberContext instanceof KParser.DoubleLitContext) {
            DoubleVar doubleVar = new DoubleVar(numberContext.getText());
            Utils.setPosition(doubleVar, numberContext);
            Utils.setChildrensParent(doubleVar);
            return doubleVar;
        } else throw new UnsupportedOperationException();
    }

    @NotNull
    private static Expr toAst(KParser.Char_varContext charVarContext) {
        CharVar charVar = new CharVar(charVarContext.getText());
        Utils.setPosition(charVar, charVarContext);
        Utils.setChildrensParent(charVar);
        return charVar;
    }

    @NotNull
    private static Expr toAst(KParser.Boolean_varContext booleanVarContext) {
        BooleanVar booleanVar = new BooleanVar(booleanVarContext.getText());
        Utils.setPosition(booleanVar, booleanVarContext);
        Utils.setChildrensParent(booleanVar);
        return booleanVar;
    }

    @NotNull
    @Contract("null -> fail")
    private static Expr toAst(KParser.Concrete_varContext concreteVarContext) {
        if (concreteVarContext instanceof KParser.NumberLitContext)
            return toAst(((KParser.NumberLitContext) concreteVarContext).number());
        else if (concreteVarContext instanceof KParser.BooleanLitContext)
            return toAst(((KParser.BooleanLitContext) concreteVarContext).boolean_var());
        else if (concreteVarContext instanceof KParser.CharLitContext)
            return toAst(((KParser.CharLitContext) concreteVarContext).char_var());
        else throw new UnsupportedOperationException();
    }

    @NotNull
    @Contract("null -> fail")
    private static Expr toAst(KParser.VariableContext variableContext) {
        if (variableContext instanceof KParser.ConcreteVariableContext)
            return toAst(((KParser.ConcreteVariableContext) variableContext).concrete_var());
        else if (variableContext instanceof KParser.IdentifierContext)
            return toAst(((KParser.IdentifierContext) variableContext).ident());
        else throw new UnsupportedOperationException();
    }

    @Contract("null -> fail")
    private static Expr toAst(KParser.ExprContext exprContext) {
        if (exprContext instanceof KParser.BinaryExprContext) {
            BinaryExpr binaryExpr = new BinaryExpr(ToAst.
                    toAst(((KParser.BinaryExprContext) exprContext).left),
                    ToAst.toAst(((KParser.BinaryExprContext) exprContext).right),
                    ((KParser.BinaryExprContext) exprContext).operator.getText());
            Utils.setPosition(binaryExpr, exprContext);
            Utils.setChildrensParent(binaryExpr);
            return binaryExpr;
        } else if (exprContext instanceof KParser.VarContext)
            return toAst(((KParser.VarContext) exprContext).variable());
        else if (exprContext instanceof KParser.ParenExprContext)
            return toAst(((KParser.ParenExprContext) exprContext).expr());
        else if (exprContext instanceof KParser.FuncCallContext)
            return toAst(((KParser.FuncCallContext) exprContext).fun_call());
        else if (exprContext instanceof KParser.ArrayAccessContext)
            return toAst(((KParser.ArrayAccessContext) exprContext).array_access());
        else if (exprContext instanceof KParser.ArrTypeSizeDefValContext)
            return toAst(((KParser.ArrTypeSizeDefValContext) exprContext).arr_type_size_def_val());
        else throw new UnsupportedOperationException();
    }

    @NotNull
    private static Expr toAst(KParser.Fun_callContext funCallContext) {
        if (funCallContext.expr() != null) {
            FunCall funCall = new FunCall(
                    funCallContext.ident().getText(),
                    funCallContext.expr().stream().map(ToAst::toAst).collect(Collectors.toList()));
            Utils.setPosition(funCall, funCallContext);
            Utils.setChildrensParent(funCall);
            return funCall;
        } else {
            FunCall funCall = new FunCall(funCallContext.ident().getText(), new ArrayList<>());
            Utils.setPosition(funCall, funCallContext);
            Utils.setChildrensParent(funCall);
            return funCall;
        }
    }

    @NotNull
    private static Expr toAst(KParser.Array_accessContext arrayAccessContext) {
//        VariableReference variableReference=new VariableReference(.getText());
//        Utils.setPosition(variableReference, arrayAccessContext);
//        Utils.setChildrensParent(variableReference);

        ArrayAccess arrayAccess = new ArrayAccess(
               toAst(arrayAccessContext.ident()),
                toAst(arrayAccessContext.expr()));
        Utils.setPosition(arrayAccess, arrayAccessContext);
        Utils.setChildrensParent(arrayAccess);
        return arrayAccess;
    }

    @NotNull
    private static Expr toAst(KParser.Arr_type_size_def_valContext arrTypeSizeDefValContext) {
        ArrTypeSizeDefVal arrTypeSizeDefVal = new ArrTypeSizeDefVal(
                toAst(arrTypeSizeDefValContext.type()),
               toAst(arrTypeSizeDefValContext.expr())); //.stream().map(ToAst::toAst).collect(Collectors.toList()));
        Utils.setPosition(arrTypeSizeDefVal, arrTypeSizeDefValContext);
        Utils.setChildrensParent(arrTypeSizeDefVal);
        return arrTypeSizeDefVal;
    }

    @NotNull
    @Contract("null -> fail")
    private static Type toAst(KParser.TypeContext typeContext) {
        if (typeContext instanceof KParser.IntTypeContext) {
            Integer integerType = new Integer();
            Utils.setPosition(integerType, typeContext);
            Utils.setChildrensParent(integerType);
            return integerType;
        } else if (typeContext instanceof KParser.DoubleTypeContext) {
            Double doubleType = new Double();
            Utils.setPosition(doubleType, typeContext);
            Utils.setChildrensParent(doubleType);
            return doubleType;
        } else if (typeContext instanceof KParser.BooleanTypeContext) {
            Boolean booleanType = new Boolean();
            Utils.setPosition(booleanType, typeContext);
            Utils.setChildrensParent(booleanType);
            return booleanType;
        } else if (typeContext instanceof KParser.CharTypeContext) {
            Char charType = new Char();
            Utils.setPosition(charType, typeContext);
            Utils.setChildrensParent(charType);
            return charType;
        } else if (typeContext instanceof KParser.ArrayTypeContext) {
            Array array = new Array((toAst(((KParser.ArrayTypeContext) typeContext).type())));
            Utils.setPosition(array, typeContext);
            Utils.setChildrensParent(array);
            return array;
        } else throw new UnsupportedOperationException();
    }

    @NotNull
    private static Expression toAst(KParser.DeclarationContext declarationContext) {
        if (declarationContext.expr() != null) {
            if (declarationContext.KEYWORD_val() != null) {
                NewVariable newVariable=new NewVariable(
                        declarationContext.KEYWORD_val().toString(),
                        toAst(declarationContext.ident()),
                        toAst(declarationContext.type()));
                Utils.setPosition(newVariable, declarationContext);
                Utils.setChildrensParent(newVariable);

                Declaration declaration = new Declaration(
                       newVariable,
                        toAst(declarationContext.expr()));
                Utils.setPosition(declaration, declarationContext);
                Utils.setChildrensParent(declaration);
                return declaration;
            } else if (declarationContext.KEYWORD_var() != null) {
                NewVariable newVariable=new NewVariable(
                        declarationContext.KEYWORD_var().toString(),
                        toAst(declarationContext.ident()),
                        toAst(declarationContext.type()));
                Utils.setPosition(newVariable, declarationContext);
                Utils.setChildrensParent(newVariable);
                Declaration declaration = new Declaration(
                        newVariable,
                        toAst(declarationContext.expr()));
                Utils.setPosition(declaration, declarationContext);
                Utils.setChildrensParent(declaration);
                return declaration;
            } else throw new UnsupportedOperationException();
        } else {
            if (declarationContext.KEYWORD_val() != null) {
                NewVariable newVariable = new NewVariable(
                        declarationContext.KEYWORD_val().toString(),
                        toAst(declarationContext.ident()),
                        toAst(declarationContext.type()));
                Utils.setPosition(newVariable, declarationContext);
                Utils.setChildrensParent(newVariable);
                return newVariable;
            } else if (declarationContext.KEYWORD_var() != null) {
                NewVariable newVariable = new NewVariable(
                        declarationContext.KEYWORD_var().toString(),
                        toAst(declarationContext.ident()),
                        toAst(declarationContext.type()));
                Utils.setPosition(newVariable, declarationContext);
                Utils.setChildrensParent(newVariable);
                return newVariable;
            } else throw new UnsupportedOperationException();
        }
    }

    @NotNull
    private static Expression toAst(KParser.AssignmentContext assignmentContext) {
        if (assignmentContext.ident() != null) {
            Assignment assignment = new Assignment(toAst(assignmentContext.ident()), toAst(assignmentContext.expr()));
            Utils.setPosition(assignment, assignmentContext);
            Utils.setChildrensParent(assignment);
            return assignment;
        } else if (assignmentContext.array_access() != null) {
            Assignment assignment = new Assignment(toAst(assignmentContext.array_access()), toAst(assignmentContext.expr()));
            Utils.setPosition(assignment, assignmentContext);
            Utils.setChildrensParent(assignment);
            return assignment;
        } else throw new UnsupportedOperationException();
    }

    @Contract("null -> fail")
    private static Expression toAst(KParser.ExpressionContext expressionContext) {
        if (expressionContext instanceof KParser.AssigContext)
            return toAst(((KParser.AssigContext) expressionContext).assignment());
        else if (expressionContext instanceof KParser.DeclContext)
            return toAst(((KParser.DeclContext) expressionContext).declaration());
        else if (expressionContext instanceof KParser.IfOperContext)
            return toAst(((KParser.IfOperContext) expressionContext).if_else());
        else if (expressionContext instanceof KParser.ExprExpContext)
            return toAst(((KParser.ExprExpContext) expressionContext).expr());
        else if (expressionContext instanceof KParser.LoopExpContext)
            return toAst(((KParser.LoopExpContext) expressionContext).loop());
        else throw new UnsupportedOperationException();
    }

    @NotNull
    @Contract("null -> fail")
    private static Expression toAst(KParser.LoopContext loopContext) {
        if (loopContext instanceof KParser.WhileLoopContext)
            return toAst(((KParser.WhileLoopContext) loopContext).while_loop());
        else if (loopContext instanceof KParser.ForLoopContext)
            return toAst(((KParser.ForLoopContext) loopContext).for_loop());
        else if (loopContext instanceof KParser.DoWhileLoopContext)
            return toAst(((KParser.DoWhileLoopContext) loopContext).do_while_loop());
        else throw new UnsupportedOperationException();
    }

    private static List<Expression> toAst(KParser.ExpressionsContext expressionsContext) {
        if (expressionsContext.expression() != null)
            return expressionsContext.expression().stream().map(ToAst::toAst).collect(Collectors.toList());
        else return new ArrayList<>();
    }

    private static List<Expression> toAst(KParser.BlockContext blockContext) {
        return toAst(blockContext.expressions());
    }

    @NotNull
    private static Expression toAst(KParser.If_elseContext ifElseContext) {
        if (ifElseContext.firstExpression != null && ifElseContext.secondExpression != null) {
            ThenBlock thenBlock = new ThenBlock(Arrays.asList(toAst(ifElseContext.firstExpression)));
            Utils.setPosition(thenBlock, ifElseContext);
            Utils.setChildrensParent(thenBlock);

            ElseBlock elseBlock = new ElseBlock(Arrays.asList(toAst(ifElseContext.secondExpression)));
            Utils.setPosition(elseBlock, ifElseContext);
            Utils.setChildrensParent(elseBlock);

            IfOper anIfOper = new IfOper(
                    toAst(ifElseContext.expr()),
                    thenBlock,
                    elseBlock);
            Utils.setPosition(anIfOper, ifElseContext);
            Utils.setChildrensParent(anIfOper);
            return anIfOper;
        } else if (ifElseContext.firstBlock != null && ifElseContext.secondBlock != null) {
            ThenBlock thenBlock = new ThenBlock(toAst(ifElseContext.firstBlock));
            Utils.setPosition(thenBlock, ifElseContext);
            Utils.setChildrensParent(thenBlock);

            ElseBlock elseBlock = new ElseBlock(toAst(ifElseContext.secondBlock));
            Utils.setPosition(elseBlock, ifElseContext);
            Utils.setChildrensParent(elseBlock);

            IfOper anIfOper = new IfOper(
                    toAst(ifElseContext.expr()),
                    thenBlock,
                    elseBlock);
            Utils.setPosition(anIfOper, ifElseContext);
            Utils.setChildrensParent(anIfOper);
            return anIfOper;
        } else if (ifElseContext.firstExpression != null && ifElseContext.secondBlock != null) {
            ThenBlock thenBlock = new ThenBlock(Arrays.asList(toAst(ifElseContext.firstExpression)));
            Utils.setPosition(thenBlock, ifElseContext);
            Utils.setChildrensParent(thenBlock);

            ElseBlock elseBlock = new ElseBlock(toAst(ifElseContext.secondBlock));
            Utils.setPosition(elseBlock, ifElseContext);
            Utils.setChildrensParent(elseBlock);

            IfOper anIfOper = new IfOper(
                    toAst(ifElseContext.expr()),
                    thenBlock,
                    elseBlock);
            Utils.setPosition(anIfOper, ifElseContext);
            Utils.setChildrensParent(anIfOper);
            return anIfOper;
        } else if (ifElseContext.firstBlock != null && ifElseContext.secondExpression != null) {
            ThenBlock thenBlock = new ThenBlock(toAst(ifElseContext.firstBlock));
            Utils.setPosition(thenBlock, ifElseContext);
            Utils.setChildrensParent(thenBlock);

            ElseBlock elseBlock = new ElseBlock(Arrays.asList(toAst(ifElseContext.secondExpression)));
            Utils.setPosition(elseBlock, ifElseContext);
            Utils.setChildrensParent(elseBlock);

            IfOper anIfOper = new IfOper(
                    toAst(ifElseContext.expr()),
                    thenBlock,
                    elseBlock);
            Utils.setPosition(anIfOper, ifElseContext);
            Utils.setChildrensParent(anIfOper);
            return anIfOper;
        } else if (ifElseContext.firstBlock != null) {//ifElseContext.secondExpression==null && secondBlock==null
            ThenBlock thenBlock = new ThenBlock(toAst(ifElseContext.firstBlock));
            Utils.setPosition(thenBlock, ifElseContext);
            Utils.setChildrensParent(thenBlock);

            IfOper anIfOper = new IfOper(
                    toAst(ifElseContext.expr()),
                    thenBlock,
                    null);
            Utils.setPosition(anIfOper, ifElseContext);
            Utils.setChildrensParent(anIfOper);
            return anIfOper;
        } else if (ifElseContext.firstExpression != null) {//ifElseContext.secondExpression==null && secondBlock==null
            ThenBlock thenBlock = new ThenBlock(Arrays.asList(toAst(ifElseContext.firstExpression)));
            Utils.setPosition(thenBlock, ifElseContext);
            Utils.setChildrensParent(thenBlock);
            IfOper anIfOper = new IfOper(
                    toAst(ifElseContext.expr()),
                    thenBlock,
                    null);
            Utils.setPosition(anIfOper, ifElseContext);
            Utils.setChildrensParent(anIfOper);
            return anIfOper;
        } else throw new UnsupportedOperationException();
    }

    @NotNull
    private static Expression toAst(KParser.While_loopContext whileLoopContext) {
        if (whileLoopContext.expression() == null) {
            WhileLoop whileLoop = new WhileLoop(
                    toAst(whileLoopContext.expr()),
                    whileLoopContext.block().expressions().expression()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()));
            Utils.setPosition(whileLoop, whileLoopContext);
            Utils.setChildrensParent(whileLoop);
            return whileLoop;
        } else if (whileLoopContext.block() == null) {
            WhileLoop whileLoop = new WhileLoop(
                    toAst(whileLoopContext.expr()), Arrays.asList(toAst(whileLoopContext.expression())));
            Utils.setPosition(whileLoop, whileLoopContext);
            Utils.setChildrensParent(whileLoop);
            return whileLoop;
        } else throw new UnsupportedOperationException();
    }

    //TODO: solve Arrays.AsList warnings
    @NotNull
    private static Expression toAst(KParser.For_loopContext forLoopContext) {
        VariableReference variableReference =
                new VariableReference(forLoopContext.variable().getText());
        Utils.setPosition(variableReference, forLoopContext.variable());
        Utils.setChildrensParent(variableReference);
        if (forLoopContext.expression() == null) {
            ForLoop forLoop = new ForLoop(
                    variableReference,
                    toAst(forLoopContext.expr()),
                    forLoopContext.block().expressions().expression()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()));
            Utils.setPosition(forLoop, forLoopContext);
            Utils.setChildrensParent(forLoop);
            return forLoop;
        } else if (forLoopContext.block() == null) {
            ForLoop forLoop = new ForLoop(
                    variableReference,
                    toAst(forLoopContext.expr()),
                    Arrays.asList(toAst(forLoopContext.expression())));
            Utils.setPosition(forLoop, forLoopContext);
            Utils.setChildrensParent(forLoop);
            return forLoop;
        } else throw new UnsupportedOperationException();
    }

    @NotNull
    private static Expression toAst(KParser.Do_while_loopContext doWhileLoopContext) {
        DoWhileLoop doWhileLoop = new DoWhileLoop(
                toAst(doWhileLoopContext.expr()), toAst(doWhileLoopContext.block()));
        Utils.setPosition(doWhileLoop, doWhileLoopContext);
        Utils.setChildrensParent(doWhileLoop);
        return doWhileLoop;
    }

    @NotNull
    private static FunParameter toAst(KParser.Fun_parameterContext funParameterContext) {
        FunParameter funParameter = new FunParameter(
                toAst(funParameterContext.ident()), toAst(funParameterContext.type()));
        Utils.setPosition(funParameter, funParameterContext);
        Utils.setChildrensParent(funParameter);
        return funParameter;
    }

    @NotNull
    private static FunDeclaration toAst(KParser.Fun_declarationContext funDeclarationContext) {
        if (funDeclarationContext.type() != null && funDeclarationContext.KEYWORD_return() != null) {
            ReturnExpr returnExpr = new ReturnExpr(toAst(funDeclarationContext.expr()));
            Utils.setPosition(returnExpr, funDeclarationContext.expr());
            Utils.setChildrensParent(returnExpr);

            FunDeclaration funDeclaration = new FunDeclaration(
                    toAst(funDeclarationContext.ident()),
                    toAst(funDeclarationContext.type()),
                    funDeclarationContext.fun_parameters().fun_parameter()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()),
                    funDeclarationContext.expressions().expression()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()),
                    returnExpr);
            Utils.setPosition(funDeclaration, funDeclarationContext);
            Utils.setChildrensParent(funDeclaration);
            return funDeclaration;

        } else if (funDeclarationContext.type() != null && funDeclarationContext.KEYWORD_return() == null) {
            FunDeclaration funDeclaration = new FunDeclaration(
                    toAst(funDeclarationContext.ident()),
                    toAst(funDeclarationContext.type()),
                    funDeclarationContext.fun_parameters().fun_parameter()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()),
                    funDeclarationContext.expressions().expression()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()),
                    null);
            Utils.setPosition(funDeclaration, funDeclarationContext);
            Utils.setChildrensParent(funDeclaration);
            return funDeclaration;

        } else if (funDeclarationContext.type() == null && funDeclarationContext.KEYWORD_return() != null) {
            ReturnExpr returnExpr = new ReturnExpr(toAst(funDeclarationContext.expr()));
            Utils.setPosition(returnExpr, funDeclarationContext.expr());
            Utils.setChildrensParent(returnExpr);

            FunDeclaration funDeclaration = new FunDeclaration(
                    toAst(funDeclarationContext.ident()),
                    null,
                    funDeclarationContext.fun_parameters().fun_parameter()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()),
                    funDeclarationContext.expressions().expression()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()),
                    returnExpr);
            Utils.setPosition(funDeclaration, funDeclarationContext);
            Utils.setChildrensParent(funDeclaration);
            return funDeclaration;

        } else if (funDeclarationContext.type() == null && funDeclarationContext.KEYWORD_return() == null) {
            FunDeclaration funDeclaration = new FunDeclaration(
                    toAst(funDeclarationContext.ident()),
                    null,
                    funDeclarationContext.fun_parameters().fun_parameter()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()),
                    funDeclarationContext.expressions().expression()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()),
                    null);
            Utils.setPosition(funDeclaration, funDeclarationContext);
            Utils.setChildrensParent(funDeclaration);
            return funDeclaration;
        } else throw new UnsupportedOperationException();
    }

    @NotNull
    private static ClassDeclaration toAst(KParser.Class_declarationContext classDeclarationContext) {
        if (classDeclarationContext.class_body().declaration() != null) {
            ClassDeclaration classDeclaration = new ClassDeclaration(
                    toAst(classDeclarationContext.ident()),
                    classDeclarationContext.class_body().fun_declaration()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()),
                    null);
            Utils.setPosition(classDeclaration, classDeclarationContext);
            Utils.setChildrensParent(classDeclaration);
            return classDeclaration;
        } else {
            ClassDeclaration classDeclaration = new ClassDeclaration(
                    toAst(classDeclarationContext.ident()),
                    null,
                    classDeclarationContext.class_body().declaration()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()));
            Utils.setPosition(classDeclaration, classDeclarationContext);
            Utils.setChildrensParent(classDeclaration);
            return classDeclaration;
        }
    }

    @NotNull
    static Program toAst(KParser.ProgramContext programContext) {
        List<FunDeclaration> funDeclarationList = new ArrayList<>();
        if (programContext.fun_declaration() != null) {
            funDeclarationList.
                    addAll(programContext.fun_declaration()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()));
        }
        List<ClassDeclaration> classDeclarationList = new ArrayList<>();
        if (programContext.class_declaration() != null) {
            classDeclarationList.
                    addAll(programContext.class_declaration()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()));
        }

        Program program = new Program(classDeclarationList, funDeclarationList);
        Utils.setPosition(program, programContext);
        Utils.setChildrensParent(program);
        return program;
    }
}
