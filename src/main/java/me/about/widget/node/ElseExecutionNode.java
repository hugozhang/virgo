package me.about.widget.node;

import me.about.widget.model.ExecutionResult;
import me.about.widget.RuleContext;

public class ElseExecutionNode implements ExecutionNode {

	private final ExecutionNode leftPhrase;
	private final ExecutionNode rightPhrase;

	public ElseExecutionNode(ExecutionNode leftPhrase, ExecutionNode rightPhrase) {
		this.leftPhrase = leftPhrase;
		this.rightPhrase = rightPhrase;
	}

	@Override
	public boolean execute(RuleContext context, ExecutionResult result) {
		if (leftPhrase.execute(context, result))
			return true;
		return rightPhrase.execute(context, result);
	}
}
