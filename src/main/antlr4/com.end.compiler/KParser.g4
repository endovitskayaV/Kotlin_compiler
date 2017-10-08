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

boolean_var: KEYWORD_true | KEYWORD_false;
concrete_var:
      number        #numberLit
    | boolean_var   #booleanLit
    ;
variable:
    concrete_var    #concreteVariable
    |ident          #Identifier
    ;

//могут быть записаны как expr;
//                    так и при присвоении
expr: RBO expr RBC              #parenExpr
     | variable                 #var
     | arr_type_size_def_val    #arrTypeSizeDefVal
     | array_access             #arrayAccess
     | left=expr operator=(MUL|DIV|ADD|SUB|GE|LE|NEQUALS|EQUALS|GT|LT) right=expr   #binaryExpr
     | fun_call                 #funcCall
     | NOT (expr)                 #neg
     ;

type: KEYWORD_int               #intType
    | KEYWORD_double            #doubleType
    | KEYWORD_boolean           #booleanType
    | KEYWORD_array '<'type'>'  #arrayType
    ;

declaration: (KEYWORD_val|KEYWORD_var) ident COLON type  (ASSIGN expr)?;
assignment: ident ASSIGN expr;

arr_type_size_def_val: KEYWORD_array '<'type'>' RBO expr COMMA CBO expr CBC RBC;
array_access: ident SBO (expr) SBC;

//полноценные выражения, имеющие смысл
expression:    assignment  #assig
             | declaration #decl
             | if_else     #ifElse
             | loop        #loopExp
             | expr        #exprExp
             ;

expressions: (SEMICOLON* expression SEMICOLON+)*;
block: CBO expressions CBC;

if_else:KEYWORD_if RBO (expr) RBC (expression | block) (KEYWORD_else (expression | block ))?;

loop:  while_loop     #whileLoop
     | for_loop       #forLoop
     | do_while_loop  #doWhileLoop
     ;

while_loop: KEYWORD_while  RBO (expr) RBC (expression | block);
for_loop:KEYWORD_for  RBO ( ident KEYWORD_in ident) RBC  (expression | block);
do_while_loop:KEYWORD_do block  KEYWORD_while RBO (expr) RBC;

//TODO:check it
 fun_parameter: ident COLON type;
 fun_parameters: RBO (fun_parameter (COMMA fun_parameter)*)? RBC;
 fun_declaration: KEYWORD_fun ident fun_parameters(COLON type)? block;
 fun_call: ident RBO (expr (COMMA expr)* )? RBC;

 class_body: CBO (declaration| fun_declaration)* CBC;
 class_declaration: KEYWORD_class ident class_body;

 program:  class_declaration+ EOF;








