package service;

import java.io.IOException;
import java.util.Map;

public interface DataService {
    void setData(String input);

    void getData(String input);

    void reverse(String input);

    String findCommand(String input);

    void loadData(String input) throws IOException;

    void countWords(String input) throws IOException;

    void saveData(String input) throws IOException;

}
