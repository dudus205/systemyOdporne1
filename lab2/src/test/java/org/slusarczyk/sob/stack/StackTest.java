package org.slusarczyk.sob.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slusarczyk.sob.stack.exception.EmptyStackException;
import org.slusarczyk.sob.stack.exception.InvalidStackCapacityException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    private Stack<String> stack;

    StackTest() {
    }

    @BeforeEach
    void setUp() throws InvalidStackCapacityException {
        this.stack = new Stack<>(String.class, 10);
    }

    @Test
    void whenNoElementOnStack_ExpectIsEmptyReturnsTrue() {
        assertTrue(stack.isEmpty());
    }

    @Test
    void whenNoElementOnStack_ThrowsEmptyStackExceptionOnPopAndTop() {
        assertThrows(EmptyStackException.class, () -> stack.pop());
        assertThrows(EmptyStackException.class, () -> stack.top());
    }

    @Test
    void whenElementsOnStack_ExpectIsEmptyReturnsFalse() {
        stack.push("Hello");
        stack.push(" ");
        stack.push("World");
        stack.push("!");

        assertTrue(IntStream.range(0, 3).allMatch(i -> stack.top().equals("!")));
        assertFalse(stack.isEmpty());
    }

    @Test
    void whenPushElementOnStack_ExpectSameElementOnPop() {
        String str = "Hello World!";
        stack.push(str);
        String stackElement = stack.pop();

        assertSame(str, stackElement);
        assertTrue(stack.isEmpty());
    }

    @Test
    void whenElementsOnStack_ExpectPopReturnsElementsInOrder() {
        List<String> elements = Arrays.asList("Hello", " ", "World", "!");
        elements.forEach(el -> stack.push(el));
        int last = elements.size() - 1;

        IntStream.rangeClosed(0, last).forEach(i -> assertSame(stack.pop(), elements.get(last - i)));
        assertTrue(stack.isEmpty());
    }

    @Test
    void whenPushNullOnStack_ExpectSameElementOnPop() {
        stack.push(null);
        assertFalse(stack.isEmpty());

        String stackElement = stack.pop();
        assertNull(stackElement);
        assertTrue(stack.isEmpty());
    }

    @Test
    void whenExceptionThrownFromStack_ExpectPushAndPopWork() {
        assertThrows(EmptyStackException.class, () -> stack.pop());
        stack.push("Hello World!");
        assertFalse(stack.isEmpty());

        stack.pop();
        assertTrue(stack.isEmpty());
    }
}