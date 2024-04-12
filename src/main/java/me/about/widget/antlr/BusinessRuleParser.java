package me.about.widget.antlr;

import org.joo.libra.support.exceptions.MalformedSyntaxException;
import me.about.widget.node.ExecutionNode;

public interface BusinessRuleParser {

	ExecutionNode parse(String predicate) throws MalformedSyntaxException;
}
