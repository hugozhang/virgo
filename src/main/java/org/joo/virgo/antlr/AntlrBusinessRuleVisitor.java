package org.joo.virgo.antlr;

import org.joo.virgo.node.*;

import org.joo.virgo.antlr.grammar.BusinessRuleParser;


public class AntlrBusinessRuleVisitor extends AbstractAntlrBusinessRuleVisitor {

	@Override
	public ExecutionNode visitMultiActionsCtx(BusinessRuleParser.MultiActionsCtxContext ctx) {
		ExecutionNode left = visit(ctx.left);
		if (ctx.right == null)
			return left;
		ExecutionNode right = visit(ctx.right);
		return new MultiActionsExecutionNode(left, right);
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
	public ExecutionNode visitForBodyStatementCtx(BusinessRuleParser.ForBodyStatementCtxContext ctx) {
		BusinessRuleParser.ForInCtxContext forInCtxContext = (BusinessRuleParser.ForInCtxContext) ctx.forInStatementVar;
		if (ctx.assignmentVar != null) {
			if (ctx.ifStatementVar != null) {
				return new MultiActionsExecutionNode(new AssignExecutionNode(ctx.assignmentVar.indexName.getText(), (ExpressionExecutionNode) visit(ctx.assignmentVar.value)), visit(ctx.ifStatementVar));
			} else if (ctx.forInStatementVar != null) {
				BusinessRuleParser.IfCtxContext condition = (BusinessRuleParser.IfCtxContext)forInCtxContext.condition;

				IfExecutionNode ifExecutionNode = new IfExecutionNode((ExpressionExecutionNode) visit(condition.condition), visit(condition.impositions));

				ForInIfExecutionNode forInIfExecutionNode = new ForInIfExecutionNode(forInCtxContext.indexName.getText(), (ExpressionExecutionNode)visit(forInCtxContext.listName),ifExecutionNode);
				return new MultiActionsExecutionNode(new AssignExecutionNode(ctx.assignmentVar.indexName.getText(), (ExpressionExecutionNode) visit(ctx.assignmentVar.value)), forInIfExecutionNode);
			}
		} else {
			if (ctx.ifStatementVar != null) {
				if (forInCtxContext == null) {
					return visit(ctx.ifStatementVar);
				} else {
					BusinessRuleParser.IfCtxContext condition = (BusinessRuleParser.IfCtxContext)forInCtxContext.condition;
					return new IfExecutionNode((ExpressionExecutionNode) visit(condition.condition), visit(condition.impositions));
				}
			} else if (ctx.forInStatementVar != null) {
				BusinessRuleParser.IfCtxContext condition = (BusinessRuleParser.IfCtxContext)forInCtxContext.condition;
				IfExecutionNode ifExecutionNode = new IfExecutionNode((ExpressionExecutionNode) visit(condition.condition), visit(condition.impositions));
                return new ForInIfExecutionNode(forInCtxContext.indexName.getText(), (ExpressionExecutionNode)visit(forInCtxContext.listName),ifExecutionNode);
			}
		}
		throw new IllegalStateException("Invalid for body statement");
	}
}
