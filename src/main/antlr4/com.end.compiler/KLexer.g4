lexer grammar KLexer;

//TODO: external keyword-> no body

//? 0 1
//* 0-..
//+ 1-..

KEYWORD_var: 'var';
KEYWORD_val: 'val';

KEYWORD_class: 'class';
KEYWORD_fun: 'fun';
KEYWORD_Unit: 'Unit';
KEYWORD_return: 'return';

KEYWORD_int: 'Int';
KEYWORD_double: 'Double';
KEYWORD_boolean: 'Boolean';
KEYWORD_char:'Char';
KEYWORD_string:'String';
KEYWORD_array:'Array';

KEYWORD_true: 'true';
KEYWORD_false: 'false';

KEYWORD_if: 'if';
KEYWORD_else: 'else';
KEYWORD_while:'while';
KEYWORD_do:'do';
KEYWORD_for: 'for';
KEYWORD_in: 'in';

KEYWORD_interface: 'interface';
KEYWORD_external: 'external';

AT: '@';

NOT:'!';
NNV:'!!';
DOT:'.';
SQ:'\'';

WHITESPACE: [\t\r\n\f ]+ ->channel(HIDDEN);
MULTILINE_COMMENT : '/*' .*? '*/' -> channel(HIDDEN);
SINGLELINE_COMMENT: '//' .*? '\n' -> channel(HIDDEN);

fragment
//цифра будет распрознаваться только в совокупности с другими цифрами
DIGIT: '0'..'9';

INTEGER: ('-')? ('0'| ('1'..'9' DIGIT*));
DOUBLE : ('-')? DIGIT+ (DOT DIGIT+)?;
CHAR: '\'' (EscapeSeq | .) '\'' ;
STRING: UnterminatedStringLiteral DOUBLE_QUOTES;

UnterminatedStringLiteral: DOUBLE_QUOTES (~["\\\r\n] | '\\' (. | EOF))*;

fragment
EscapeSeq: UniCharacterLiteral | EscapedIdentifier;

fragment
 UniCharacterLiteral: '\\' 'u' HexDigit HexDigit HexDigit HexDigit;

fragment
HexDigit: [0-9a-fA-F];

fragment
EscapedIdentifier: '\\' ('t' | 'b' | 'r' | 'n');

fragment
LETTER: 'a' .. 'z' | 'A' .. 'Z' | '_';

NAME: LETTER (LETTER | DIGIT)*;

ADD:    '+';
SUB:    '-';
MUL:    '*';
DIV:    '/';

GE:       '>=';
LE:       '<=';
NEQUALS:  '!=';
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
SBC: ']';
SBO: '[';

DOUBLE_QUOTES: '"';