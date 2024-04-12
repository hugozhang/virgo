package me.about.widget.node;

import me.about.widget.RuleContext;
import me.about.widget.model.ExecutionResult;

public class AssignExecutionNode implements ExecutionNode {

	private final String variableName;
	private final ExpressionExecutionNode expression;

	public AssignExecutionNode(String variableName, ExpressionExecutionNode expression) {
		this.variableName = variableName;
		this.expression = expression;
	}

	@Override
	public boolean execute(RuleContext context, ExecutionResult result) {
		expression.execute(context, result);
		Object literalValue = expression.getPredicate().calculateLiteralValue(context);
//		Object literalValue = expression.execute(context,result);
		context.setTempVariable(variableName, literalValue);
		result.getResults().put(variableName, literalValue);
		return true;
	}
}
