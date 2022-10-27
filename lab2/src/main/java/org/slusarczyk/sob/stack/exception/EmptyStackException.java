package org.slusarczyk.sob.stack.exception;

public class EmptyStackException extends RuntimeException {

    public EmptyStackException() {
        super("No object has been pushed to stack yet!");
    }
}
