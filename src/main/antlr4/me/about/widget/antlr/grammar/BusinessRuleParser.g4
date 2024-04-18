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
    actions
;

//bodyStatement:
//    assignmentVar = actions ?
//    ifStatementVar = ifStatement ?   # forBodyStatementCtx
//    |
//    assignmentVar = actions ?
//    forInStatementVar = forInStatement ?    # forBodyStatementCtx
//;
//
//assignmentStatement:
//    indexName = TEMP_VAR EQUALS value = expression
//;

forInStatement:
    FOR indexName = TEMP_VAR IN listName = factor condition = ifStatement # forInCtx
    | ifStatement # ifStatementCtx
;

ifStatement
:
	IF condition = expression THEN impositions = actions # ifCtx
	| left = ifStatement ELSE right = ifStatement # elseCtx
	| left = ifStatement ELSE impositions = actions # elseCtx
;

// 区分普通赋值语法和返回值赋值,这样可以在不同的上下文找
assigns:
   variable = VARIABLE EQUALS value = expression   # assignCtx
   | left = assigns SEMICOLON right = assigns  # multiAssignCtx
   | left = assigns SEMICOLON    # multiAssignCtx
;

actions
:
	variable = TEMP_VAR EQUALS value = expression # actionCtx
	| left = actions SEMICOLON right = actions # multiActionCtx
	| left = actions SEMICOLON # multiActionCtx
	| nested = forInStatement # nestedPhraseCtx
	| LBRACE nested = actions RBRACE # nestedActionCtx
	| expression # expressionCtx
	| nested = assigns # returnCtx
;