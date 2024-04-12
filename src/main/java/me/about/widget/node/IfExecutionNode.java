package me.about.widget.node;

import me.about.widget.RuleContext;
import me.about.widget.model.ExecutionResult;

public class IfExecutionNode implements ExecutionNode {

	private final ExpressionExecutionNode condition;
	private final ExecutionNode action;

	public IfExecutionNode(ExpressionExecutionNode condition, ExecutionNode action) {
		this.condition = condition;
		this.action = action;
	}

	@Override
	public boolean execute(RuleContext context, ExecutionResult result) {
		boolean conditionSatisfied = condition.execute(context, result);
		if (!conditionSatisfied)
			return false;
		action.execute(context, result);
		return true;
	}
}
