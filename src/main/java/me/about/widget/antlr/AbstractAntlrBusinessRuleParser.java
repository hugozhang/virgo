package me.about.widget.antlr;


import org.antlr.v4.runtime.*;
import me.about.widget.node.ExecutionNode;

public abstract class AbstractAntlrBusinessRuleParser<L extends Lexer, P extends Parser> implements ExecutionNodeParser {

	@Override
	public ExecutionNode parse(final String predicate) {
		CharStream stream = CharStreams.fromString(predicate);

		Lexer lexer = createLexer(stream);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		P parser = createParser(tokens);

//		BusinessRuleLexer lexer = new BusinessRuleLexer(CharStreams.fromString(predicate));
//		lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
//		lexer.addErrorListener(new BusinessRuleErrorListener());
//
//		BusinessRuleParser parser = new BusinessRuleParser(new CommonTokenStream(lexer));
//		parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
//		parser.addErrorListener(new BusinessRuleErrorListener());


		return doParse(parser);
	}

	protected abstract L createLexer(CharStream stream);

	protected abstract P createParser(CommonTokenStream tokens);

	protected abstract ExecutionNode doParse(P parser);
}