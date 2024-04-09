package org.joo.virgo.node;

import org.joo.virgo.RuleContext;
import org.joo.virgo.model.ExecutionResult;

public class AssignExecutionNode implements ExecutionNode {

	private final String variableName;
	private final ExpressionExecutionNode expression;

	public AssignExecutionNode(String variableName, ExpressionExecutionNode expression) {
		this.variableName = variableName;
		this.expression = expression;
	}

	@Override
	public boolean execute(RuleContext context, ExecutionResult result) {
		Object literalValue = expression.getPredicate().calculateLiteralValue(context);
//		Object literalValue = expression.execute(context,result);
		context.setTempVariable(variableName, literalValue);
		result.getResults().put(variableName, literalValue);
		return true;
	}
}
