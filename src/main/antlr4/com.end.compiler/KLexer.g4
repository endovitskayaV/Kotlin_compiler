lexer grammar KLexer;

//? 0 1
//* 0-..
//+ 1-..

KEYWORD_var: 'var';
KEYWORD_val: 'val';
KEYWORD_class: 'class';
KEYWORD_function: 'fun';
KEYWORD_int: 'Int';
KEYWORD_double: 'Double';
KEYWORD_boolean: 'Boolean';
KEYWORD_char:'Char';
KEYWORD_array:'Array';
KEYWORD_true: 'true';
KEYWORD_false: 'false';
KEYWORD_if: 'if';
KEYWORD_else: 'else';
KEYWORD_while:'while';
KEYWORD_do:'do';
KEYWORD_for: 'for';
KEYWORD_in: 'in';
KEYWORD_div:'div';
KEYWORD_rem:'rem';
KEYWORD_inc:'inc';
KEYWORD_dec:'dec';
KEYWORD_print:'print';
KEYWORD_println:'println';
KEYWORD_readLine: 'readLine';

KEYWORD_null:'null';

NOT:'!';
NNV:'!!';
DOT:'.';
SQ:'\'';

WHITESPACE: [\t\n\r\f ]+ ->channel(HIDDEN);
MULTILINE_COMMENT : '/*' .*? '*/' -> channel(HIDDEN);
SINGLELINE_COMMENT: '//' .*? '\n' -> channel(HIDDEN);
LINEBREAK:'\n';

fragment
//цифра будет распрознаваться только в совокупности с другими цифрами
DIGIT: '0'..'9';

INTEGER: ('0'| '1'..'9' DIGIT*);
DOUBLE :('0'..'9')+ ('.' ('0'..'9')+)?;


fragment
LETTER: 'a' .. 'z' | 'A' .. 'Z' | '_';

NAME: LETTER (LETTER | DIGIT)*;

ADD:    '+';
SUB:    '-';
MUL:    '*';
DIV:    '/';

GE:       '>=';
LE:       '<=';
NEQUALS:  '<>';
EQUALS:   '==';
GT:       '>';
LT:       '<';

ASSIGN: '=';
COLON : ':';
COMMA : ',';
SEMICOLON : ';';
RBC: ')';
RBO: '(';
CBC: '}';
CBO: '{';