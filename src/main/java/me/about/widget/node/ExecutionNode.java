package me.about.widget.node;

import me.about.widget.model.ExecutionResult;
import me.about.widget.RuleContext;

public interface ExecutionNode  {

 	 boolean execute(RuleContext context, ExecutionResult result);
}
