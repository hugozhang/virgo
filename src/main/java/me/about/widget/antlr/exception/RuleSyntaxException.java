package me.about.widget.antlr.exception;

import org.joo.libra.support.exceptions.MalformedSyntaxException;

public class RuleSyntaxException extends MalformedSyntaxException {
    public RuleSyntaxException(String rule,int line,int pos,String msg) {
        super("\nrule: " + rule + "\nline: " + line + "\npos: " + pos + "\nmsg: " + msg);
    }

    public RuleSyntaxException(Throwable cause) {
        super(cause);
    }
}
