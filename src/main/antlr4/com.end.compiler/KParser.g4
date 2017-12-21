parser grammar KParser;

//? 0 1
//* 0-..
//+ 1-..


options {tokenVocab=KLexer; }

ident:  NAME;
number: INTEGER #integerLit
       | DOUBLE #doubleLit
       ;
char_var: CHAR;
boolean_var: KEYWORD_true | KEYWORD_false;
string_var:STRING;

concrete_var:
      number        #numberLit
    | boolean_var   #booleanLit
    | char_var      #charLit
    | string_var    #stringLit
    ;
variable:
    concrete_var    #concreteVariable
    |ident          #Identifier
    ;

    //TODO: negotoiation
//могут быть записаны как condition, так и при присвоении
expr: RBO+ expr RBC+            #parenExpr
     | fun_call                 #funcCall
     | variable                 #var
     | array_initialization    #arrayInitailization
     | array_access             #arrayAccess
     |  left=expr operator=(MUL|DIV) right=expr     #binaryExpr
     | left=expr operator=(ADD|SUB) right=expr      #binaryExpr
     | left=expr operator=(GE|LE|NEQUALS|EQUALS|GT|LT) right=expr #binaryExpr
     ;

type: KEYWORD_int               #intType
    | KEYWORD_double            #doubleType
    | KEYWORD_boolean           #booleanType
    | KEYWORD_array '<'type'>'  #arrayType
    | KEYWORD_char              #charType
    | KEYWORD_string            #stringType
    ;

declaration: (KEYWORD_val|KEYWORD_var) ident COLON type  (ASSIGN expr)?;
assignment: (ident| array_access) ASSIGN expr;

array_initialization: KEYWORD_array '<'type'>' RBO expr /*COMMA CBO expr CBC*/ RBC;
array_access: ident SBO expr SBC;

//полноценные выражения, имеющие смысл
expression:    assignment        #assig
             | declaration       #decl
             | if_else           #ifOper
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
for_loop:KEYWORD_for  RBO (variable KEYWORD_in expr) RBC  (expression | block);
do_while_loop:KEYWORD_do block  NL* KEYWORD_while RBO expr RBC;

 fun_modificator: KEYWORD_external;
 annotation: AT SimpleName (RBO ident RBC)?;
 fun_parameter: ident COLON type;
 fun_parameters: RBO (fun_parameter (COMMA fun_parameter)*)? RBC;
 fun_declaration: (annotation|fun_modificator)* KEYWORD_fun ident fun_parameters COLON (type|KEYWORD_Unit)
                  (CBO expressions (KEYWORD_return expr)? CBC)?;
 fun_call: ident RBO (expr (COMMA expr)* )? RBC;

 class_body: CBO (declaration| fun_declaration)*  CBC;
 class_declaration: KEYWORD_class ident class_body;


 fun_signature: annotation? KEYWORD_fun ident fun_parameters COLON (type|KEYWORD_Unit);
 interface_declaration: KEYWORD_interface ident
                        CBO (declaration|fun_signature)*  CBC;

 program: (class_declaration | fun_declaration | interface_declaration)*;






