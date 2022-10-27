package org.slusarczyk.sob.stack.exception;

public class InvalidStackCapacityException extends Exception {

    public InvalidStackCapacityException(int capacity) {
        super(String.format("Given capacity %d is not valid! Value must be greater than 0.", capacity));
    }
}
