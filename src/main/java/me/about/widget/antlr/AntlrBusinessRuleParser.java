package me.about.widget.antlr;

import me.about.widget.antlr.exception.SyntaxException;
import me.about.widget.antlr.grammar.BusinessRuleParser;
import me.about.widget.node.ExecutionNode;
import org.antlr.v4.runtime.*;
import me.about.widget.antlr.grammar.BusinessRuleLexer;
import org.antlr.v4.runtime.misc.Interval;


public class AntlrBusinessRuleParser extends AbstractAntlrBusinessRuleParser<BusinessRuleLexer,BusinessRuleParser> {

	protected BusinessRuleLexer createLexer(final CharStream stream) {
		BusinessRuleLexer lexer = new BusinessRuleLexer(stream);
		lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
		lexer.addErrorListener(new BusinessRuleErrorListener());
		return lexer;
	}

	protected BusinessRuleParser createParser(final CommonTokenStream tokens) {
		BusinessRuleParser parser = new BusinessRuleParser(tokens);
		parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
		parser.addErrorListener(new BusinessRuleErrorListener());
		return parser;
	}

	protected ExecutionNode doParse(final BusinessRuleParser parser) {
		AntlrBusinessRuleVisitor visitor = new AntlrBusinessRuleVisitor();
		return visitor.visit(parser.businessRule());
	}
}

class BusinessRuleErrorListener extends BaseErrorListener {

	@Override
	public void syntaxError(final Recognizer<?, ?> recognizer, final Object offendingSymbol, final int line,
			final int charPositionInLine, final String msg, final RecognitionException e) {
		Parser parser = (Parser) recognizer;
		ParserRuleContext ctx = parser.getContext();

		CharStream cs = ctx.start.getTokenSource().getInputStream();
		String ruleText = cs.getText(new Interval(ctx.start.getStartIndex(),cs.size()));

		throw new SyntaxException(ruleText,line,charPositionInLine,msg);
	}
}