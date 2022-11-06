package com.sob;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Fibonacci {

    private int calculateIterative(int num) {
        int current = 0;
        int previous = 1;

        for (int i = 0; i < num; i++) {
            int temp = current;
            current = current + previous;
            previous = temp;
        }
        return current;
    }

    private int calculateIterative2(int num) {
        int[] fib = new int[num + 2];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i <= num; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[num];
    }

    private int calculateRecursive(int num) {
        if (num <= 1) {
            return num;
        }
        return calculateRecursive(num - 1) + calculateRecursive(num - 2);
    }

    public void vote(int num) {
        List<Integer> results = calculate(num);
        validate(results);
        Entry<Integer, Integer> resultEntry = getNumberByMajority(results);

        System.out.println(String.format("Result for Fibonacci sequence number %d: %d (based on %d calculation results)",
                num, resultEntry.getKey(), resultEntry.getValue()));
    }

    private List<Integer> calculate(int num) {
        List<Integer> results = Arrays.asList(
                calculateIterative(num),
                calculateIterative2(num),
                calculateRecursive(num)
        );
        IntStream.range(0, results.size())
                .forEach(idx -> System.out.println(String.format("Result of calculation %d: %d", idx, results.get(idx))));

        return results;
    }

    private void validate(Collection<Integer> values) {
        if (values.stream().allMatch(new HashSet<>()::add)) {
            throw new IllegalStateException("All results have different values!");
        }
    }

    private Entry<Integer, Integer> getNumberByMajority(List<Integer> results) {
        Map<Integer, Integer> numberToOccurrences = countNumberOccurrences(results);
        return Collections.max(numberToOccurrences.entrySet(), Entry.comparingByValue());
    }

    private Map<Integer, Integer> countNumberOccurrences(List<Integer> results) {
        Map<Integer, List<Integer>> elementToOccurrences = results.stream().collect(Collectors.groupingBy(Function.identity()));
        return elementToOccurrences.entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey, it -> it.getValue().size()));
    }
}
