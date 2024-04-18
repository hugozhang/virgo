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

NEWLINE:                '\n';

//WS: 					(' ' | '\t')+ -> channel(HIDDEN) ;
WS : [ \t\r\n]+ -> skip;