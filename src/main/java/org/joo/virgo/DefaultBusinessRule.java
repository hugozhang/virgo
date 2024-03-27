package org.joo.virgo;

import java.util.Collections;
import java.util.Optional;

import org.joo.virgo.antlr.AntlrBusinessRuleParser;
import org.joo.virgo.antlr.BusinessRuleParser;
import org.joo.virgo.model.DefaultExecutionResult;
import org.joo.virgo.model.ExecutionResult;
import org.joo.virgo.model.TemporaryExecutionResult;
import org.joo.virgo.node.ExecutionNode;

import lombok.NonNull;

public class DefaultBusinessRule implements BusinessRule {

	private final ExecutionNode node;

	public DefaultBusinessRule(final String value,final RuleContext context) {
		this(value, new AntlrBusinessRuleParser(),context);
	}

	public DefaultBusinessRule(final String value, final @NonNull BusinessRuleParser parser,final RuleContext context) {
		this.node = parser.parse(value,context);
	}

	@Override
	public Optional<ExecutionResult> execute(RuleContext context) {
		if (node == null)
			return Optional.empty();
		TemporaryExecutionResult tmpResult = new TemporaryExecutionResult();
		node.execute(context, tmpResult);
		return Optional.of(new DefaultExecutionResult(Collections.unmodifiableMap(tmpResult.getResults())));
	}
}
