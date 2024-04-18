grammar PrintString;  // 定义复合语法文件

// 词法分析器部分
tokens { PRINT, STRING_LITERAL }  // 声明打印关键字和字符串字面量为令牌类型

PRINT: 'print';  // 定义打印关键字
STRING_LITERAL: '"' (~["\\\r\n] | EscapeSequence)* '"';  // 定义字符串字面量
fragment EscapeSequence: '\\' [btnfr"'\\];  // 定义转义序列

WS: [ \t\r\n]+ -> skip;  // 忽略空格和换行

// 解析器部分
printString: PRINT STRING_LITERAL NEWLINE? EOF;  // 打印字符串语句，以一个可选的换行符和文件结束符结束

NEWLINE: '\r'? '\n' -> channel(HIDDEN);  // 匹配换行符，并将其发送到隐藏通道

// 定义解析器入口点
start: printString;  // 解析器从`printString`规则开始解析
