package com.end.compiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    private static Expr toAst(com.end.compiler.KParser.NumberContext numberContext) {
        if (numberContext instanceof com.end.compiler.KParser.IntegerLitContext) {
            IntegerVar integerVar = new IntegerVar(numberContext.getText());
            Utils.setPosition(integerVar, numberContext);
            Utils.setChildrensParent(integerVar);
            return integerVar;
        } else if (numberContext instanceof com.end.compiler.KParser.DoubleLitContext) {
            DoubleVar doubleVar = new DoubleVar(numberContext.getText());
            Utils.setPosition(doubleVar, numberContext);
            Utils.setChildrensParent(doubleVar);
            return doubleVar;
        } else throw new UnsupportedOperationException();
    }

    @NotNull
    private static Expr toAst(com.end.compiler.KParser.String_varContext stringVarContext) {
       StringVar stringVar = new StringVar(stringVarContext.getText());
        Utils.setPosition(stringVar, stringVarContext);
        Utils.setChildrensParent(stringVar);
        return stringVar;
    }

    @NotNull
    private static Expr toAst(com.end.compiler.KParser.Char_varContext charVarContext) {
       CharVar charVar = new CharVar(charVarContext.getText());
        Utils.setPosition(charVar, charVarContext);
        Utils.setChildrensParent(charVar);
        return charVar;
    }


    @NotNull
    private static Expr toAst(com.end.compiler.KParser.Boolean_varContext booleanVarContext) {
        BooleanVar booleanVar = new BooleanVar(booleanVarContext.getText());
        Utils.setPosition(booleanVar, booleanVarContext);
        Utils.setChildrensParent(booleanVar);
        return booleanVar;
    }

    @NotNull
    @Contract("null -> fail")
    private static Expr toAst(com.end.compiler.KParser.Concrete_varContext concreteVarContext) {
        if (concreteVarContext instanceof com.end.compiler.KParser.NumberLitContext)
            return toAst(((com.end.compiler.KParser.NumberLitContext) concreteVarContext).number());
        else if (concreteVarContext instanceof com.end.compiler.KParser.BooleanLitContext)
            return toAst(((com.end.compiler.KParser.BooleanLitContext) concreteVarContext).boolean_var());
        else if (concreteVarContext instanceof com.end.compiler.KParser.CharLitContext)
            return toAst(((com.end.compiler.KParser.CharLitContext) concreteVarContext).char_var());
        else if (concreteVarContext instanceof com.end.compiler.KParser.StringLitContext)
            return toAst(((com.end.compiler.KParser.StringLitContext) concreteVarContext).string_var());
        else throw new UnsupportedOperationException();
    }

    @NotNull
    @Contract("null -> fail")
    private static Expr toAst(com.end.compiler.KParser.VariableContext variableContext) {
        if (variableContext instanceof com.end.compiler.KParser.ConcreteVariableContext)
            return toAst(((com.end.compiler.KParser.ConcreteVariableContext) variableContext).concrete_var());
        else if (variableContext instanceof com.end.compiler.KParser.IdentifierContext)
            return toAst(((com.end.compiler.KParser.IdentifierContext) variableContext).ident());
        else throw new UnsupportedOperationException();
    }

    @Contract("null -> fail")
    private static Expr toAst(com.end.compiler.KParser.ExprContext exprContext) {
        if (exprContext instanceof com.end.compiler.KParser.BinaryExprContext) {
            BinaryExpr binaryExpr = new BinaryExpr(ToAst.
                    toAst(((com.end.compiler.KParser.BinaryExprContext) exprContext).left),
                    ToAst.toAst(((com.end.compiler.KParser.BinaryExprContext) exprContext).right),
                    ((com.end.compiler.KParser.BinaryExprContext) exprContext).operator.getText());
            Utils.setPosition(binaryExpr, exprContext);
            Utils.setChildrensParent(binaryExpr);
            return binaryExpr;
        } else if (exprContext instanceof com.end.compiler.KParser.VarContext)
            return toAst(((com.end.compiler.KParser.VarContext) exprContext).variable());
        else if (exprContext instanceof com.end.compiler.KParser.ParenExprContext)
            return toAst(((com.end.compiler.KParser.ParenExprContext) exprContext).expr());
        else if (exprContext instanceof com.end.compiler.KParser.FuncCallContext)
            return toAst(((com.end.compiler.KParser.FuncCallContext) exprContext).fun_call());
        else if (exprContext instanceof com.end.compiler.KParser.ArrayAccessContext)
            return toAst(((com.end.compiler.KParser.ArrayAccessContext) exprContext).array_access());
        else if (exprContext instanceof com.end.compiler.KParser.ArrayInitailizationContext)
            return toAst(((com.end.compiler.KParser.ArrayInitailizationContext) exprContext).array_initialization());
        else throw new UnsupportedOperationException();
    }

    @NotNull
    private static Expr toAst(com.end.compiler.KParser.Fun_callContext funCallContext) {
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
    private static Expr toAst(com.end.compiler.KParser.Array_accessContext arrayAccessContext) {
        ArrayAccess arrayAccess = new ArrayAccess(
                toAst(arrayAccessContext.ident()),
                toAst(arrayAccessContext.expr()));
        Utils.setPosition(arrayAccess, arrayAccessContext);
        Utils.setChildrensParent(arrayAccess);
        return arrayAccess;
    }

    @NotNull
    private static Expr toAst(com.end.compiler.KParser.Array_initializationContext arrayInitializationContext) {
        ArrayInitailization arrayInitailization = new ArrayInitailization(
                toAst(arrayInitializationContext.type()),
                toAst(arrayInitializationContext.expr()));
        Utils.setPosition(arrayInitailization, arrayInitializationContext);
        Utils.setChildrensParent(arrayInitailization);
        return arrayInitailization;
    }

    @NotNull
    @Contract("null -> fail")
    private static Type toAst(com.end.compiler.KParser.TypeContext typeContext) {
        if (typeContext instanceof com.end.compiler.KParser.IntTypeContext) {
            Integer integerType = new Integer();
            Utils.setPosition(integerType, typeContext);
            Utils.setChildrensParent(integerType);
            return integerType;
        } else if (typeContext instanceof com.end.compiler.KParser.DoubleTypeContext) {
            Double doubleType = new Double();
            Utils.setPosition(doubleType, typeContext);
            Utils.setChildrensParent(doubleType);
            return doubleType;
        } else if (typeContext instanceof com.end.compiler.KParser.BooleanTypeContext) {
            Boolean booleanType = new Boolean();
            Utils.setPosition(booleanType, typeContext);
            Utils.setChildrensParent(booleanType);
            return booleanType;
        } else if (typeContext instanceof com.end.compiler.KParser.CharTypeContext) {
            Char charType = new Char();
            Utils.setPosition(charType, typeContext);
            Utils.setChildrensParent(charType);
            return charType;
        }  else if (typeContext instanceof com.end.compiler.KParser.StringTypeContext) {
           StringType stringType = new StringType();
            Utils.setPosition(stringType, typeContext);
            Utils.setChildrensParent(stringType);
            return stringType;
        } else if (typeContext instanceof com.end.compiler.KParser.ArrayTypeContext) {
            Array array = new Array((toAst(((com.end.compiler.KParser.ArrayTypeContext) typeContext).type())));
            Utils.setPosition(array, typeContext);
            Utils.setChildrensParent(array);
            return array;
        } else throw new UnsupportedOperationException();
    }

    @NotNull
    private static Declaration toAst(com.end.compiler.KParser.DeclarationContext declarationContext) {
        if (declarationContext.expr() != null) {
            if (declarationContext.KEYWORD_val() != null) {
                NewVariable newVariable = new NewVariable(
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
                NewVariable newVariable = new NewVariable(
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
        } else {//expr=null
            if (declarationContext.KEYWORD_val() != null) {
                NewVariable newVariable = new NewVariable(
                        declarationContext.KEYWORD_val().toString(),
                        toAst(declarationContext.ident()),
                        toAst(declarationContext.type()));
                Utils.setPosition(newVariable, declarationContext);
                Utils.setChildrensParent(newVariable);

                Declaration declaration = new Declaration(
                        newVariable,
                        null);
                Utils.setPosition(declaration, declarationContext);
                Utils.setChildrensParent(declaration);
                return declaration;
            } else if (declarationContext.KEYWORD_var() != null) {
                NewVariable newVariable = new NewVariable(
                        declarationContext.KEYWORD_var().toString(),
                        toAst(declarationContext.ident()),
                        toAst(declarationContext.type()));
                Utils.setPosition(newVariable, declarationContext);
                Utils.setChildrensParent(newVariable);

                Declaration declaration = new Declaration(
                        newVariable,
                        null);
                Utils.setPosition(declaration, declarationContext);
                Utils.setChildrensParent(declaration);
                return declaration;
            } else throw new UnsupportedOperationException();
        }
    }

    @NotNull
    private static Expression toAst(com.end.compiler.KParser.AssignmentContext assignmentContext) {
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
    private static Expression toAst(com.end.compiler.KParser.ExpressionContext expressionContext) {
        if (expressionContext instanceof com.end.compiler.KParser.AssigContext)
            return toAst(((com.end.compiler.KParser.AssigContext) expressionContext).assignment());
        else if (expressionContext instanceof com.end.compiler.KParser.DeclContext)
            return toAst(((com.end.compiler.KParser.DeclContext) expressionContext).declaration());
        else if (expressionContext instanceof com.end.compiler.KParser.IfOperContext)
            return toAst(((com.end.compiler.KParser.IfOperContext) expressionContext).if_else());
        else if (expressionContext instanceof com.end.compiler.KParser.ExprExpContext)
            return toAst(((com.end.compiler.KParser.ExprExpContext) expressionContext).expr());
        else if (expressionContext instanceof com.end.compiler.KParser.LoopExpContext)
            return toAst(((com.end.compiler.KParser.LoopExpContext) expressionContext).loop());
        else throw new UnsupportedOperationException();
    }

    @NotNull
    @Contract("null -> fail")
    private static Expression toAst(com.end.compiler.KParser.LoopContext loopContext) {
        if (loopContext instanceof com.end.compiler.KParser.WhileLoopContext)
            return toAst(((com.end.compiler.KParser.WhileLoopContext) loopContext).while_loop());
        else if (loopContext instanceof com.end.compiler.KParser.ForLoopContext)
            return toAst(((com.end.compiler.KParser.ForLoopContext) loopContext).for_loop());
        else if (loopContext instanceof com.end.compiler.KParser.DoWhileLoopContext)
            return toAst(((com.end.compiler.KParser.DoWhileLoopContext) loopContext).do_while_loop());
        else throw new UnsupportedOperationException();
    }

    private static List<Expression> toAst(com.end.compiler.KParser.ExpressionsContext expressionsContext) {
        if (expressionsContext.expression() != null)
            return expressionsContext.expression().stream().map(ToAst::toAst).collect(Collectors.toList());
        else return new ArrayList<>();
    }

    private static List<Expression> toAst(com.end.compiler.KParser.BlockContext blockContext) {
        return toAst(blockContext.expressions());
    }

    @NotNull
    private static Expression toAst(com.end.compiler.KParser.If_elseContext ifElseContext) {
        if (ifElseContext.firstExpression != null && ifElseContext.secondExpression != null) {
            ThenBlock thenBlock = new ThenBlock(Collections.singletonList(toAst(ifElseContext.firstExpression)));
            Utils.setPosition(thenBlock, ifElseContext);
            Utils.setChildrensParent(thenBlock);

            ElseBlock elseBlock = new ElseBlock(Collections.singletonList(toAst(ifElseContext.secondExpression)));
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
            ThenBlock thenBlock = new ThenBlock(Collections.singletonList(toAst(ifElseContext.firstExpression)));
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

            ElseBlock elseBlock = new ElseBlock(Collections.singletonList(toAst(ifElseContext.secondExpression)));
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
            ThenBlock thenBlock = new ThenBlock(Collections.singletonList(toAst(ifElseContext.firstExpression)));
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
    private static Expression toAst(com.end.compiler.KParser.While_loopContext whileLoopContext) {
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
                    toAst(whileLoopContext.expr()), Collections.singletonList(toAst(whileLoopContext.expression())));
            Utils.setPosition(whileLoop, whileLoopContext);
            Utils.setChildrensParent(whileLoop);
            return whileLoop;
        } else throw new UnsupportedOperationException();
    }

    @NotNull
    private static Expression toAst(com.end.compiler.KParser.For_loopContext forLoopContext) {
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
                    Collections.singletonList(toAst(forLoopContext.expression())));
            Utils.setPosition(forLoop, forLoopContext);
            Utils.setChildrensParent(forLoop);
            return forLoop;
        } else throw new UnsupportedOperationException();
    }

    @NotNull
    private static Expression toAst(com.end.compiler.KParser.Do_while_loopContext doWhileLoopContext) {
        DoWhileLoop doWhileLoop = new DoWhileLoop(
                toAst(doWhileLoopContext.expr()), toAst(doWhileLoopContext.block()));
        Utils.setPosition(doWhileLoop, doWhileLoopContext);
        Utils.setChildrensParent(doWhileLoop);
        return doWhileLoop;
    }

    @NotNull
    private static FunParameter toAst(com.end.compiler.KParser.Fun_parameterContext funParameterContext) {
        FunParameter funParameter = new FunParameter(
                toAst(funParameterContext.ident()), toAst(funParameterContext.type()));
        Utils.setPosition(funParameter, funParameterContext);
        Utils.setChildrensParent(funParameter);
        return funParameter;
    }


    private static Annotation toAst(com.end.compiler.KParser.AnnotationContext annotationContext) {
        if (annotationContext == null) return null;
        if (annotationContext.ident() != null) {
            Annotation annotation = new Annotation(
                    annotationContext.SimpleName().getText(),
                    annotationContext.ident().getText());
            Utils.setPosition(annotation, annotationContext);
            Utils.setChildrensParent(annotation);
            return annotation;
        } else {
            Annotation annotation = new Annotation(
                    annotationContext.SimpleName().getText(), null);
            Utils.setPosition(annotation, annotationContext);
            Utils.setChildrensParent(annotation);
            return annotation;
        }
    }


    private static FunSignature toAst(com.end.compiler.KParser.Fun_signatureContext funSignatureContext){
        FunSignature funSignature = new FunSignature(
                toAst(funSignatureContext.annotation()),
                toAst(funSignatureContext.ident()),
                toAst(funSignatureContext.type()),
                funSignatureContext.fun_parameters().fun_parameter()
                        .stream().map(ToAst::toAst).collect(Collectors.toList()));
        Utils.setPosition(funSignature, funSignatureContext);
        Utils.setChildrensParent(funSignature);
        return funSignature;
    }

    @NotNull
    private static InterfaceDeclaration toAst(com.end.compiler.KParser.Interface_declarationContext interfaceDeclarationContext) {
        List<Declaration> declarationList = new ArrayList<>();
        if (interfaceDeclarationContext.declaration() != null) {
            declarationList.
                    addAll(interfaceDeclarationContext.declaration()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()));
        }
        List<FunSignature> funSignatureList = new ArrayList<>();
        if (interfaceDeclarationContext.fun_signature() != null) {
            funSignatureList.
                    addAll(interfaceDeclarationContext.fun_signature()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()));
        }

        InterfaceDeclaration interfaceDeclaration = new InterfaceDeclaration(
                toAst(interfaceDeclarationContext.ident()),
                declarationList, funSignatureList);
        Utils.setPosition(interfaceDeclaration, interfaceDeclarationContext);
        Utils.setChildrensParent(interfaceDeclaration);
        return interfaceDeclaration;
    }

    private static List<Expression> getExpressionsList (com.end.compiler.KParser.Fun_declarationContext funDeclarationContext){
        if (funDeclarationContext.expressions()==null) return null;
        else {
           return funDeclarationContext.expressions().expression()
                    .stream().map(ToAst::toAst).collect(Collectors.toList());
        }
    }

    @NotNull
    private static FunDeclaration toAst(com.end.compiler.KParser.Fun_declarationContext funDeclarationContext) {
        if (funDeclarationContext.type() != null && funDeclarationContext.KEYWORD_return() != null) {
            ReturnExpr returnExpr = new ReturnExpr(toAst(funDeclarationContext.expr()));
            Utils.setPosition(returnExpr, funDeclarationContext.expr());
            Utils.setChildrensParent(returnExpr);


            FunDeclaration funDeclaration = new FunDeclaration(
                    toAst(funDeclarationContext.annotation()),
                    toAst(funDeclarationContext.ident()),
                    toAst(funDeclarationContext.type()),
                    funDeclarationContext.fun_parameters().fun_parameter()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()),
                    getExpressionsList(funDeclarationContext),
                    returnExpr);
            Utils.setPosition(funDeclaration, funDeclarationContext);
            Utils.setChildrensParent(funDeclaration);
            return funDeclaration;

        } else if (funDeclarationContext.type() != null && funDeclarationContext.KEYWORD_return() == null) {
            FunDeclaration funDeclaration = new FunDeclaration(
                    toAst(funDeclarationContext.annotation()),
                    toAst(funDeclarationContext.ident()),
                    toAst(funDeclarationContext.type()),
                    funDeclarationContext.fun_parameters().fun_parameter()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()),
                    getExpressionsList(funDeclarationContext),
                    null);
            Utils.setPosition(funDeclaration, funDeclarationContext);
            Utils.setChildrensParent(funDeclaration);
            return funDeclaration;

        } else if (funDeclarationContext.type() == null && funDeclarationContext.KEYWORD_return() != null) {
            ReturnExpr returnExpr = new ReturnExpr(toAst(funDeclarationContext.expr()));
            Utils.setPosition(returnExpr, funDeclarationContext.expr());
            Utils.setChildrensParent(returnExpr);

            FunDeclaration funDeclaration = new FunDeclaration(
                    toAst(funDeclarationContext.annotation()),
                    toAst(funDeclarationContext.ident()),
                    null,
                    funDeclarationContext.fun_parameters().fun_parameter()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()),
                    getExpressionsList(funDeclarationContext),
                    returnExpr);
            Utils.setPosition(funDeclaration, funDeclarationContext);
            Utils.setChildrensParent(funDeclaration);
            return funDeclaration;

        } else if (funDeclarationContext.type() == null && funDeclarationContext.KEYWORD_return() == null) {
            FunDeclaration funDeclaration = new FunDeclaration(
                    toAst(funDeclarationContext.annotation()),
                    toAst(funDeclarationContext.ident()),
                    null,
                    funDeclarationContext.fun_parameters().fun_parameter()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()),
                    getExpressionsList(funDeclarationContext),
                    null);
            Utils.setPosition(funDeclaration, funDeclarationContext);
            Utils.setChildrensParent(funDeclaration);
            return funDeclaration;
        } else throw new UnsupportedOperationException();
    }

    @NotNull
    private static ClassDeclaration toAst(com.end.compiler.KParser.Class_declarationContext classDeclarationContext) {
        if (classDeclarationContext.class_body().declaration() != null) {
            ClassDeclaration classDeclaration = new ClassDeclaration(
                    toAst(classDeclarationContext.ident()),
                    classDeclarationContext.class_body().declaration()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()),
                    null);
            Utils.setPosition(classDeclaration, classDeclarationContext);
            Utils.setChildrensParent(classDeclaration);
            return classDeclaration;
        } else {
            ClassDeclaration classDeclaration = new ClassDeclaration(
                    toAst(classDeclarationContext.ident()),
                    null,
                    classDeclarationContext.class_body().fun_declaration()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()));
            Utils.setPosition(classDeclaration, classDeclarationContext);
            Utils.setChildrensParent(classDeclaration);
            return classDeclaration;
        }
    }

    @NotNull
    static Program toAst(com.end.compiler.KParser.ProgramContext programContext) {
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
        List<InterfaceDeclaration> interfaceDeclarationList = new ArrayList<>();
        if (programContext.interface_declaration() != null) {
            interfaceDeclarationList.
                    addAll(programContext.interface_declaration()
                            .stream().map(ToAst::toAst).collect(Collectors.toList()));
        }

        Program program = new Program(classDeclarationList, funDeclarationList, interfaceDeclarationList);
        Utils.setPosition(program, programContext);
        Utils.setChildrensParent(program);
        return program;
    }
}
