lexer grammar BusinessRuleLexer;

//@header {
//package org.joo.virgo.antlr.grammar;
//}

import SqlLexerCommon;
//IF: 					('IF' | 'if') ;
THEN: 					('THEN' | 'then') ;
ELIF:					('ELIF' | 'elif') ;
ELSE: 					('ELSE' | 'else') ;

SEMICOLON:				';' ;

//NEWLINE:                '\n';

// 匹配Windows（`\r\n`）、Unix（`\n`）或Mac（`\r`）风格的换行符，并将其发送到隐藏通道（不影响解析）
NEWLINE: '\r'? '\n' -> channel(HIDDEN);



WS: 					(' ' | '\t')+ -> channel(HIDDEN) ;
//WS : [ \t\r\n]+ -> skip;