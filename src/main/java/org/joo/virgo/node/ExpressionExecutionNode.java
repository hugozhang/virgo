package org.joo.virgo.node;

import org.joo.libra.PredicateContext;
import org.joo.libra.sql.SqlPredicate;
import org.joo.virgo.RuleContext;
import org.joo.virgo.model.ExecutionResult;

import lombok.Getter;

@Getter
public class ExpressionExecutionNode implements ExecutionNode {

	private final SqlPredicate predicate;

	public ExpressionExecutionNode(String predicate, PredicateContext context) {
		this.predicate = new SqlPredicate(predicate,context);
		this.predicate.checkForErrorAndThrow();
	}

	@Override
	public boolean execute(RuleContext context, ExecutionResult result) {
		return predicate.satisfiedBy(context);
	}
}
