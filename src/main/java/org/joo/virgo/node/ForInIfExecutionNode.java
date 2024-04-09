package org.joo.virgo.node;


import org.joo.virgo.RuleContext;
import org.joo.virgo.model.ExecutionResult;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ForInIfExecutionNode implements ExecutionNode {

    private final String indexName;

    private final IfExecutionNode ifNode;

    private final ExpressionExecutionNode expressionNode;

    public ForInIfExecutionNode(String indexName, ExpressionExecutionNode listExpressionExecutionNode, IfExecutionNode ifExecutionNode) {
        this.indexName = indexName;
        this.ifNode = ifExecutionNode;
        this.expressionNode = listExpressionExecutionNode;
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

    private boolean satisfiesAsCollection(Collection<?> listValue, RuleContext context,ExecutionResult result) {
        List<?> collected = listValue.stream().filter(value -> {
            context.setTempVariable(indexName, value);
            return ifNode.execute(context, result);
        }).collect(Collectors.toList());
        return !collected.isEmpty();
    }

    private boolean satisfiesAsArray(Object[] listValue, RuleContext context,ExecutionResult result) {
        List<?> collected = Arrays.stream(listValue).filter(value -> {
            context.setTempVariable(indexName, value);
            return ifNode.execute(context, result);
         }).collect(Collectors.toList());
        return !collected.isEmpty();
    }
}
