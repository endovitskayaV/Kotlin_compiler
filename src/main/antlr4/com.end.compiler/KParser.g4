parser grammar KParser;

//? 0 1
//* 0-..
//+ 1-..
//TODO: сhar

options {tokenVocab=KLexer; }

ident:  NAME;
number: INTEGER #integerLit
       | DOUBLE #doubleLit
       ;
char_var: CHAR;
boolean_var: KEYWORD_true | KEYWORD_false;
concrete_var:
      number        #numberLit
    | boolean_var   #booleanLit
    | char_var      #charLit
    ;
variable:
    concrete_var    #concreteVariable
    |ident          #Identifier
    ;
//могут быть записаны как exp, так и при присвоении
expr: RBO+ expr RBO+            #parenExpr
     | fun_call                 #funcCall
     | variable                 #var
     | arr_type_size_def_val    #arrTypeSizeDefVal
     | array_access             #arrayAccess
     |  left=expr operator=(MUL|DIV) right=expr     #binaryExpr
     | left=expr operator=(ADD|SUB) right=expr      #binaryExpr
     | left=expr operator=(GE|LE|NEQUALS|EQUALS|GT|LT) right=expr #binaryExpr
     | NOT (expr)                 #neg
     ;

type: KEYWORD_int               #intType
    | KEYWORD_double            #doubleType
    | KEYWORD_boolean           #booleanType
    | KEYWORD_array '<'type'>'  #arrayType
    | KEYWORD_char              #charType
    ;

declaration: (KEYWORD_val|KEYWORD_var) ident COLON type  (ASSIGN expr)?;
assignment: (ident| array_access) ASSIGN expr;

arr_type_size_def_val: KEYWORD_array '<'type'>' RBO expr COMMA CBO expr CBC RBC;
array_access: ident SBO expr SBC;

//полноценные выражения, имеющие смысл
expression:    assignment        #assig
             | declaration       #decl
             | if_else           #ifElse
             | loop              #loopExp
             | expr              #exprExp
             ;

expressions: (SEMICOLON* expression SEMICOLON*)*;
block: CBO  expressions CBC;

if_else:KEYWORD_if RBO (expr) RBC (firstExpression=expression | firstBlock=block)
       (KEYWORD_else (secondExpression=expression | secondBlock=block ))?;

loop:  while_loop     #whileLoop
     | for_loop       #forLoop
     | do_while_loop  #doWhileLoop
     ;

while_loop: KEYWORD_while  RBO (expr) RBC (expression | block);
for_loop:KEYWORD_for  RBO ( ident KEYWORD_in ident) RBC  (expression | block);
do_while_loop:KEYWORD_do block  NL* KEYWORD_while RBO expr RBC;

//TODO:check it
 fun_parameter: ident COLON type;
 fun_parameters: RBO (fun_parameter (COMMA fun_parameter)*)? RBC;
 fun_declaration: KEYWORD_fun ident fun_parameters COLON (type|KEYWORD_Unit) CBO expressions (KEYWORD_return expr)? CBC;
 fun_call: ident RBO (expr (COMMA expr)* )? RBC;

 class_body: CBO (declaration| fun_declaration)*  CBC;
 class_declaration: KEYWORD_class ident class_body;

 topLevelObject: class_declaration | fun_declaration;
 program: topLevelObject+;








