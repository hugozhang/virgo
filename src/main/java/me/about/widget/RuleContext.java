package me.about.widget;

import org.joo.libra.PredicateContext;
import org.springframework.context.ApplicationContext;

public class RuleContext extends PredicateContext {

	public RuleContext(Object context) {
		super(context);
	}

	public RuleContext(Object context, ApplicationContext springContext) {
		super(context,springContext);
	}
}
