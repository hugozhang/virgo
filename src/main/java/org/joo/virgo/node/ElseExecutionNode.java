package org.joo.virgo.node;

import org.joo.virgo.RuleContext;
import org.joo.virgo.model.ExecutionResult;

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
