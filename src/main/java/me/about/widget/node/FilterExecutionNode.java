package me.about.widget.node;

import me.about.widget.RuleContext;
import me.about.widget.model.ExecutionResult;
import org.joo.libra.PredicateContext;

import java.util.Collection;

public abstract class FilterExecutionNode implements ExecutionNode {

	private  String indexName;

	private  IfExecutionNode ifNode;

	private ExpressionExecutionNode expressionNode;
	public FilterExecutionNode(String indexName, IfExecutionNode ifNode,ExpressionExecutionNode expressionNode) {
		this.indexName = indexName;
		this.ifNode = ifNode;
		this.expressionNode = expressionNode;
	}

	@Override
	public boolean execute(RuleContext context, ExecutionResult result) {
		expressionNode.execute(context, result);
		Object listValue = expressionNode.getPredicate().calculateLiteralValue(context);
		if (listValue == null)
			return satisfiesAsArray(new Object[0], context,result);
		if (listValue instanceof Object[])
			return satisfiesAsArray((Object[]) listValue, context,result);
		if (listValue instanceof Collection)
			return satisfiesAsCollection((Collection<?>) listValue, context,result);
		return false;
	}

	protected abstract boolean satisfiesAsCollection(Collection<?> listValue, RuleContext context,ExecutionResult result);

	protected abstract boolean satisfiesAsArray(Object[] listValue, RuleContext context,ExecutionResult result);

	public boolean test(Object value,RuleContext context, ExecutionResult result) {
		context.setTempVariable(indexName, value);
		return ifNode.execute(context, result);
	}
}
