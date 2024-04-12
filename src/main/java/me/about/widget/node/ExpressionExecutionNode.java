package me.about.widget.node;

import me.about.widget.model.ExecutionResult;
import org.joo.libra.sql.SqlPredicate;
import me.about.widget.RuleContext;

import lombok.Getter;

@Getter
public class ExpressionExecutionNode implements ExecutionNode {

	private final SqlPredicate predicate;

	public ExpressionExecutionNode(String predicate) {
		this.predicate = new SqlPredicate(predicate);
		this.predicate.checkForErrorAndThrow();
	}

	@Override
	public boolean execute(RuleContext context, ExecutionResult result) {
		return predicate.test(context);
	}
}
