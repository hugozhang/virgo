parser grammar BusinessRuleParser;

//@header {
//package org.joo.virgo.antlr.grammar;
//}

options {
	tokenVocab = BusinessRuleLexer;
}

import SqlParserCommon;

businessRule
:
    bodyStatement
;

bodyStatement:
    assignmentVar = assignmentStatement ?
    ifStatementVar = ifStatement +   # forBodyStatementCtx
    |
    assignmentVar = assignmentStatement ? ?
    forInStatementVar = forInStatement +    # forBodyStatementCtx
;

assignmentStatement:
    indexName = TEMP_VAR EQUALS value = expression
;

forInStatement:
    FOR indexName = TEMP_VAR IN listName = factor condition = ifStatement # forInCtx
;



ifStatement
:
	IF condition = expression THEN impositions = actions # ifCtx
	| left = ifStatement ELSE right = ifStatement # elseCtx
	| left = ifStatement ELSE impositions = actions # elseCtx
;

actions
:
	variable = VARIABLE EQUALS value = expression # assignCtx
	| left = actions SEMICOLON right = actions # multiActionsCtx
	| left = actions SEMICOLON # multiActionsCtx
	| nested = ifStatement # nestedPhraseCtx
	| LBRACE nested = actions RBRACE # nestedActionCtx
	| term # termCtx
;