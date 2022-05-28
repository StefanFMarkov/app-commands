package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Database {

    private final Map<String, String> data = new HashMap<>();

    public Map<String, String> getData() {
        return this.data;
    }

    protected abstract void actions();

    protected abstract void accept(String key, String value);

    protected abstract boolean hold(String key);

    protected abstract String getKey(String key);

    public Collection<Map.Entry<String, String>> database() {
        return this.getData().entrySet();
    }
}
