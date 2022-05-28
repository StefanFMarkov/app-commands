package service.impl;

import model.Warehouse;
import service.DataService;
import utils.Engine;

import java.io.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static constants.Constants.*;

public class DataServiceImpl extends FacadeLambdaImpl implements DataService, Predicate<String> {
    private final Warehouse warehouse = Warehouse.getWarehouse();
    private final FacadeLambdaImpl lambda = new FacadeLambdaImpl();
    private final Consumer<String> print = k -> System.out.println(k + " = " + warehouse.getKey(k));

    @Override
    public void setData(String input) {

        String[] data = lambda.getTokens(input);
        String key = data[1];
        String value = data[2];
        lambda.accept(key, value);
        warehouse.accept(key, value);
    }

    @Override
    public void getData(String input) {
        String key = getInputToken(input);
        if (warehouse.hold(key)) {
            print.accept(key);
        } else {
            System.out.printf(NO_KEY_FOUND, key);
        }
    }

    @Override
    public void reverse(String input) {
        input = input.replace(REVERSE, NOTHING);
        StringBuilder sb = new StringBuilder();

        for (int i = input.length() - 1; i >= 0; i--) {
            sb.append(input.charAt(i));
        }
        System.out.println(sb);
        warehouse.actions();
    }

    @Override
    public String findCommand(String input) {
        if (input.toLowerCase().startsWith(GET)) {
            return GET;
        } else if (input.toLowerCase().startsWith(SET)) {
            return SET;
        } else if (input.toLowerCase().startsWith(REVERSE)) {
            return REVERSE;
        } else if (input.toLowerCase().startsWith(LOAD)) {
            return LOAD;
        } else if (input.toLowerCase().startsWith(SAVE)) {
            return SAVE;
        } else if (input.toLowerCase().startsWith(COUNT_WORDS)) {
            return COUNT_WORDS;
        }
        return INCORRECT;
    }

    @Override
    public void loadData(String input) throws IOException {
        String fileName = getInputToken(input);

        // file.txt
        String path = FILE_PATH + fileName.trim();
        FileReader in = new FileReader(path);
        BufferedReader reader = new BufferedReader(in);

        String line;
        while ((line = reader.readLine()) != null) {
            if (test(line)) {
                String[] values = lambda.getTokens(line);
                warehouse.accept(values[0], values[2]);
            }
        }
        System.out.printf(LOADED_DATA, fileName);
        reader.close();
        warehouse.actions();
    }

    @Override
    public void countWords(String input) throws IOException {
        String fileName = getInputToken(input);

        String path = FILE_PATH + fileName.trim();
        FileReader in = new FileReader(path);
        BufferedReader reader = new BufferedReader(in);

        String line;
        long count = 0;
        while ((line = reader.readLine()) != null) {
            if (test(line)) {
                String[] values = this.lambda.getTokens(line);
                count += values.length;
            }
        }
        System.out.printf(WORDS_IN_TEXT, fileName, count);
        warehouse.actions();
    }

    @Override
    public void saveData(String input) throws IOException {
        String fileName = getInputToken(input);
        String path = FILE_PATH + fileName.trim();

        FileWriter writer = new FileWriter(path);
        warehouse
                .database()
                .stream()
                .map(k -> lambda
                        .aggregateData(k.getKey(), k.getValue())
                )
                .forEach((k) -> {
                    try {
                        writer.write(k);
                    } catch (IOException e) {
                        System.out.println(VALID_ACTION_DATA);
                        Engine.getEngine().readData();
                    }
                });
        writer.close();
        System.out.printf(EXPORTED_DATA, fileName);
        warehouse.actions();
    }

    private String getInputToken(String data) {
        return this.lambda.getTokens(data)[1];
    }

    @Override
    public boolean test(String value) {
        return !value.equals(NOTHING);
    }
}