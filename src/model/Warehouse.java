package model;

import static constants.Constants.ACTIONS;

public class Warehouse extends Database {
    private static final Warehouse warehouse = new Warehouse();

    private Warehouse() {
    }

    public static Warehouse getWarehouse() {
        return warehouse;
    }

    @Override
    public void actions() {
        System.out.println(ACTIONS);
    }

    @Override
    public void accept(String key, String value) {
        this.getData().put(key, value);
    }

    @Override
    public boolean hold(String key) {
        return this.getData().containsKey(key);
    }

    @Override
    public String getKey(String key) {
        return this.getData().get(key);
    }
}
