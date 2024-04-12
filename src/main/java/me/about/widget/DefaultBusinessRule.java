package me.about.widget;

import java.util.Collections;
import java.util.Optional;

import me.about.widget.antlr.BusinessRuleParser;
import me.about.widget.antlr.AntlrBusinessRuleParser;
import me.about.widget.model.DefaultExecutionResult;
import me.about.widget.model.ExecutionResult;
import me.about.widget.model.TemporaryExecutionResult;
import me.about.widget.node.ExecutionNode;

import lombok.NonNull;

public class DefaultBusinessRule implements BusinessRule {

	private final ExecutionNode node;

	public DefaultBusinessRule(final String value) {
		this(value, new AntlrBusinessRuleParser());
	}

	public DefaultBusinessRule(final String value, final @NonNull BusinessRuleParser parser) {
		this.node = parser.parse(value);
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
