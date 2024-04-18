package me.about.widget.antlr.test;

import me.about.widget.antlr.AntlrBusinessRuleVisitor;
import me.about.widget.antlr.grammar.BusinessRuleParser;
import me.about.widget.antlr.grammar.BusinessRuleLexer;
import me.about.widget.node.ExecutionNode;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Print {

    public static void main(String[] args) throws Exception {
//        String input = "print \"Hello, World!\"";
//        PrintStringLexer lexer = new PrintStringLexer(CharStreams.fromString(input));
//        PrintStringParser parser = new PrintStringParser(new CommonTokenStream(lexer));
//        ParseTree tree = parser.printString();  // 解析输入文本
//        System.out.println(tree.toStringTree(parser));  // 输出解析树

        String input = "name=123;";
        BusinessRuleLexer lexer = new BusinessRuleLexer(CharStreams.fromString(input));
        BusinessRuleParser parser = new BusinessRuleParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.businessRule();  // 解析输入文本
        System.out.println(tree.toStringTree(parser));  // 输出解析树


        AntlrBusinessRuleVisitor visitor = new AntlrBusinessRuleVisitor();
        ExecutionNode visit = visitor.visit(tree);
        System.out.println(visit);
    }
}
