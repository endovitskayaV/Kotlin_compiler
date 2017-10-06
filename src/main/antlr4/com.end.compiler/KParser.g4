parser grammar KParser;

//? 0 1
//* 0-..
//+ 1-..

//TODO: сhar????,  одномерные массивы, asc????, mod????

options {tokenVocab=KLexer; }


number: RBO* (INTEGER|DOUBLE) RBC*;
boolean_var:  RBO* (KEYWORD_true|KEYWORD_false) RBC*;
ident: RBO* NAME RBO* ;
variable:number| boolean_var|ident;

//TODO: ПРИОРИРЕТ СКОБОК
multiply: ( number|ident)((MUL|DIV) (number| ident))*;
add: multiply((ADD|SUB) (multiply))*;


arithExpr:add;
compare: arithExpr (GE|LE|NEQUALS|EQUALS|GT|LT)arithExpr;

div_op:(INTEGER|ident) DOT KEYWORD_div RBO (INTEGER|ident) RBC;
rem_op:(INTEGER|ident) DOT KEYWORD_rem RBO (INTEGER|ident) RBC;
inc_op:(INTEGER|ident) DOT KEYWORD_inc RBO RBC;
dec_op:(INTEGER|ident) DOT KEYWORD_dec RBO RBC;
print_op: KEYWORD_print RBO expr RBC;
println_op: KEYWORD_println RBO expr RBC;
readLine_op:KEYWORD_readLine RBO RBC NNV;

negation: NOT (boolean_var);

expr: arithExpr
     | number
     | boolean_var
     | compare
     | print_op
     | println_op
     | readLine_op
     | negation
     |div_op
     |rem_op
     |inc_op
     |dec_op
     | KEYWORD_null;



type:KEYWORD_int|KEYWORD_double|KEYWORD_boolean;
declaration: (KEYWORD_val|KEYWORD_var) NAME COLON type  (ASSIGN expr)?;
assignment: NAME ASSIGN expr;
block:  CBO ( | expressions | expression) CBC;

if_else:KEYWORD_if RBO (compare|BOOLEAN) RBC (expression | block) (KEYWORD_else (expression | block ))?;

expression:    assignment
             | declaration
             | if_else
             | loop
             | expr;

//TODO:FIX THIS
expressions: (expression ( (SEMICOLON* LINEBREAK) | SEMICOLON+) )*;

loop:(KEYWORD_while  RBO (compare|BOOLEAN) RBC (expression | block) )
     |(KEYWORD_for  RBO ( ident KEYWORD_in ident) RBC  (expression | block) )
     |(KEYWORD_do block  KEYWORD_while RBO (compare|BOOLEAN) RBC)
     ;

//TODO:check it
 parameter: NAME COLON type;
 funParameter: parameter;
 funParameters: RBO (funParameter (COMMA funParameter)*)? RBC;
 funDeclaration: KEYWORD_fun NAME funParameters(COLON type)? block;
 classBody: CBO (declaration| funDeclaration)* CBC;
 classDeclaration: KEYWORD_class NAME classBody;









