package org.joo.virgo;

import java.util.Optional;

import org.joo.virgo.model.ExecutionResult;

public interface BusinessRule {

	 Optional<ExecutionResult> execute(RuleContext context);
}
