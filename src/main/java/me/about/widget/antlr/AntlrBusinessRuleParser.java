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
		IntStream intStream = recognizer.getInputStream();

		String ruleText;
		if (intStream instanceof CharStream) {
			CharStream cs = (CharStream) intStream;
			ruleText = cs.getText(new Interval(0, cs.size()));
		} else if (intStream instanceof TokenStream) {
			TokenStream ts = (TokenStream) intStream;
			ruleText = ts.getText();
		} else {
			throw new IllegalArgumentException("Unsupported IntStream type");
		}

		throw new SyntaxException(ruleText, line, charPositionInLine, msg);
	}
}