package me.about.widget;

import java.util.Optional;

import me.about.widget.model.ExecutionResult;

public interface BusinessRule {

	 Optional<ExecutionResult> execute(RuleContext context);
}
