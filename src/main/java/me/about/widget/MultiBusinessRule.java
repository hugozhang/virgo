package me.about.widget;

import java.util.*;

import lombok.NonNull;

public class MultiBusinessRule {

	private final BusinessRule[] rules;

	public MultiBusinessRule(final @NonNull BusinessRule... rules) {
		this.rules = rules;
	}

	public List<Map<String, Object>> execute(RuleContext context) {
		List<Map<String, Object>> results = new ArrayList<>();
		for (BusinessRule rule : rules) {
            rule.execute(context).ifPresent(result -> results.add(result.getResults()));
        }
		return results;
//		return Optional.of(new DefaultExecutionResult(Collections.unmodifiableMap(tmpResult)));
	}
}
