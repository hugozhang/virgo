package me.about.widget.node;

import me.about.widget.model.ExecutionResult;
import me.about.widget.RuleContext;

public class MultiActionsExecutionNode implements ExecutionNode {

	private final ExecutionNode left;
	
	private final ExecutionNode right;

	public MultiActionsExecutionNode(ExecutionNode left, ExecutionNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public boolean execute(RuleContext context, ExecutionResult result) {
		left.execute(context, result);
		right.execute(context, result);
		return true;
	}

}
