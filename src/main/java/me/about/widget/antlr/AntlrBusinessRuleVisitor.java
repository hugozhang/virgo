package me.about.widget.antlr;

import me.about.widget.antlr.grammar.BusinessRuleParser;
import me.about.widget.node.*;


public class AntlrBusinessRuleVisitor extends AbstractAntlrBusinessRuleVisitor {

	@Override
	public ExecutionNode visitMultiActionCtx(BusinessRuleParser.MultiActionCtxContext ctx) {
		ExecutionNode left = visit(ctx.left);
		if (ctx.right == null)
			return left;
		ExecutionNode right = visit(ctx.right);
		return new MultiActionExecutionNode(left, right);
	}

	@Override
	public ExecutionNode visitMultiAssignCtx(BusinessRuleParser.MultiAssignCtxContext ctx) {
		ExecutionNode left = visit(ctx.left);
		if (ctx.right == null)
			return left;
		ExecutionNode right = visit(ctx.right);
		return new MultiAssignExecutionNode(left, right);
	}

	@Override
	public ExecutionNode visitNestedPhraseCtx(BusinessRuleParser.NestedPhraseCtxContext ctx) {
		return visit(ctx.nested);
	}

	@Override
	public ExecutionNode visitNestedActionCtx(BusinessRuleParser.NestedActionCtxContext ctx) {
		return visit(ctx.nested);
	}

	@Override
	public ExecutionNode visitReturnCtx(BusinessRuleParser.ReturnCtxContext ctx) {
		return visit(ctx.nested);
	}


	@Override
	public ExecutionNode visitIfCtx(BusinessRuleParser.IfCtxContext ctx) {
		ExpressionExecutionNode condition = (ExpressionExecutionNode) visit(ctx.condition);
		ExecutionNode action = visit(ctx.impositions);
		return new IfExecutionNode(condition, action);
	}

	@Override
	public ExecutionNode visitElseCtx(BusinessRuleParser.ElseCtxContext ctx) {
		ExecutionNode leftPhrase = visit(ctx.left);
		ExecutionNode rightPhrase = visit(ctx.right != null ? ctx.right : ctx.impositions);
		return new ElseExecutionNode(leftPhrase, rightPhrase);
	}

	@Override
	public ExecutionNode visitAssignCtx(BusinessRuleParser.AssignCtxContext ctx) {
		String variableName = ctx.variable.getText();
		ExpressionExecutionNode expression = (ExpressionExecutionNode) visit(ctx.value);
		return new AssignExecutionNode(variableName, expression);
	}

	@Override
	public ExecutionNode visitActionCtx(BusinessRuleParser.ActionCtxContext ctx) {
		String variableName = ctx.variable.getText();
		ExpressionExecutionNode expression = (ExpressionExecutionNode) visit(ctx.value);
		return new ActionExecutionNode(variableName, expression);
	}

	@Override
	public ExecutionNode visitForInCtx(BusinessRuleParser.ForInCtxContext ctx) {
		String indexName = ctx.indexName.getText();
		ExpressionExecutionNode expressionExecutionNode = (ExpressionExecutionNode) visit(ctx.listName);
		IfExecutionNode ifExecutionNode = (IfExecutionNode) visit(ctx.condition);
		return new ForInIfExecutionNode(indexName, expressionExecutionNode, ifExecutionNode);
	}


	/***
	 * 当节点EOF时nextResult解出来是null 其它这个时候应该忽略它，用上一个节点的内容，不然会最终影响结果值
	 * @param aggregate
	 * @param nextResult
	 * @return
	 */
	@Override
	public ExecutionNode aggregateResult(ExecutionNode aggregate, ExecutionNode nextResult) {
		return nextResult == null ? aggregate : nextResult;
	}

}
