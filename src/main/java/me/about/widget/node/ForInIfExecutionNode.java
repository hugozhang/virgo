package me.about.widget.node;


import me.about.widget.RuleContext;
import me.about.widget.model.ExecutionResult;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ForInIfExecutionNode extends FilterExecutionNode {

    public ForInIfExecutionNode(String indexName, ExpressionExecutionNode listExpressionExecutionNode, IfExecutionNode ifExecutionNode) {
        super(indexName, ifExecutionNode,listExpressionExecutionNode);
    }

    @Override
    protected boolean satisfiesAsCollection(Collection<?> listValue, RuleContext context,ExecutionResult result) {
        List<?> collected = listValue.stream()
                .filter(value -> test(value, context, result))
                .collect(Collectors.toList());
        return !collected.isEmpty();
    }


    @Override
    protected boolean satisfiesAsArray(Object[] listValue, RuleContext context,ExecutionResult result) {
        List<?> collected = Arrays.stream(listValue)
                .filter(value -> test(value, context, result))
                .collect(Collectors.toList());
        return !collected.isEmpty();
    }
}
