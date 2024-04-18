package me.about.widget.node;

import me.about.widget.RuleContext;
import me.about.widget.model.ExecutionResult;

public class TerminalExecutionNode implements ExecutionNode{
    @Override
    public boolean execute(RuleContext context, ExecutionResult result) {
        return true;
    }
}
