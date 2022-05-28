package service.impl;

import service.InputToken;
import service.Values;

import java.util.function.BiConsumer;

public class FacadeLambdaImpl implements InputToken, Values, BiConsumer<String, String> {

    @Override
    public String[] getTokens(String input) {
        InputToken values = (v) -> v.split("\\s+");
        return values.getTokens(input);
    }

    @Override
    public String aggregateData(String key, String value) {
        Values lambda = (k, v) -> k + " = " + v + System.lineSeparator();
        return lambda.aggregateData(key, value);
    }

    @Override
    public void accept(String key, String value) {
        System.out.println("Saved " + key + " = " + value);
    }
}
