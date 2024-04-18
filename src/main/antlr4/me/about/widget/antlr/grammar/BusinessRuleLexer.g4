lexer grammar BusinessRuleLexer;

//@header {
//package org.joo.virgo.antlr.grammar;
//}

import SqlLexerCommon;
RETURN:				('RETURN' | 'return') ;
SET:					('SET' | 'set') ;
//IF: 					('IF' | 'if') ;
THEN: 					('THEN' | 'then') ;
ELIF:					('ELIF' | 'elif') ;
ELSE: 					('ELSE' | 'else') ;

SEMICOLON:				';' ;

NEWLINE:                '\n';

//WS: 					(' ' | '\t')+ -> channel(HIDDEN) ;
WS : [ \t\r\n]+ -> skip;