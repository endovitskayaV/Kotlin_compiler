parser grammar KParser;

//? 0 1
//* 0-..
//+ 1-..

//TODO: сhar

options {tokenVocab=KLexer; }


number: RBO* (INTEGER|DOUBLE) RBC*;
boolean_var:  RBO* (KEYWORD_true|KEYWORD_false) RBC*;
ident: RBO* NAME RBO* ;
concrete_var:
    number      #numberLit
    | boolean_var   #booleabLit
    ;
variable:
    concrete_var    #concreteVariable
    |ident          #Identifier
    ;

//TODO: ПРИОРИРЕТ СКОБОК
//multiply: left=expr operator=(MUL|DIV) right=expr;
//add: multiply((ADD|SUB) (multiply))*;

//arithExpr:add;

//compare: expr (GE|LE|NEQUALS|EQUALS|GT|LT)arithExpr;

negation: NOT (boolean_var);

div_op:(INTEGER|ident) DOT KEYWORD_div RBO (INTEGER|ident) RBC;
rem_op:(INTEGER|ident) DOT KEYWORD_rem RBO (INTEGER|ident) RBC;
inc_op:(INTEGER|ident) DOT KEYWORD_inc RBO RBC;
dec_op:(INTEGER|ident) DOT KEYWORD_dec RBO RBC;
print_op: KEYWORD_print RBO expr RBC;
println_op: KEYWORD_println RBO expr RBC;
readLine_op:KEYWORD_readLine RBO RBC NNV;

//могут быть записаны как expr;
//                    так и при присвоении
expr: RBO expr RBC  #parenExpr
     | variable     #var
     | arr_type_size_def_val    #arrTypeSizeDefVal
     | array_access     #arrayAccess
     | left=expr operator=(MUL|DIV|ADD|SUB|GE|LE|NEQUALS|EQUALS|GT|LT) right=expr   #binaryExpr
     | fun_call #funcCall
     ;

type:KEYWORD_int|KEYWORD_double|KEYWORD_boolean|KEYWORD_array '<'type'>';

declaration: (KEYWORD_val|KEYWORD_var) NAME COLON type  (ASSIGN expr)?;
assignment: NAME ASSIGN expr;

arr_type_size_def_val: KEYWORD_array '<'type'>' RBO INTEGER COMMA CBO expr CBC RBC;
array_access: NAME SBO (INTEGER | ident) SBC;

//полноценные выражения, имеющие смысл
expression:    assignment
             | declaration
             | if_else
             | loop
             | print_op
             | println_op
             | expr;

expressions: (SEMICOLON* expression SEMICOLON+)*;
block:  CBO (  expressions | expression) CBC;

if_else:KEYWORD_if RBO (expr) RBC (expression | block) (KEYWORD_else (expression | block ))?;

loop:(KEYWORD_while  RBO (expr) RBC (expression | block) )
     |(KEYWORD_for  RBO ( ident KEYWORD_in ident) RBC  (expression | block) )
     |(KEYWORD_do block  KEYWORD_while RBO (expr) RBC)
     ;

//TODO:check it
 fun_parameter: NAME COLON type;
 fun_parameters: RBO (fun_parameter (COMMA fun_parameter)*)? RBC;
 fun_declaration: KEYWORD_fun NAME fun_parameters(COLON type)? block;
 fun_call: NAME RBO (variable (COMMA variable)* )? RBC;

 class_body: CBO (declaration| fun_declaration)* CBC;
 class_declaration: KEYWORD_class NAME class_body;

 program:  class_declaration+ EOF;








