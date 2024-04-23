package me.about.widget.antlr.exception;

import org.joo.libra.support.exceptions.MalformedSyntaxException;

public class SyntaxException extends MalformedSyntaxException {
    public SyntaxException(String rule, int line, int pos, String msg) {
        super("\nrule: " + rule + ",line: " + line + ",pos: " + pos + "msg: " + msg);
    }

    public SyntaxException(Throwable cause) {
        super(cause);
    }
}
