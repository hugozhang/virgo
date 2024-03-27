package org.joo.virgo.node;

import org.joo.virgo.RuleContext;
import org.joo.virgo.model.ExecutionResult;

public interface ExecutionNode  {

 	 boolean execute(RuleContext context, ExecutionResult result);
}
