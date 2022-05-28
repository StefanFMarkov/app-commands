package utils;

import model.Warehouse;
import service.DataService;
import service.EngineService;
import service.impl.DataServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static constants.Constants.*;


public class Engine implements EngineService {
    private final Warehouse warehouse = Warehouse.getWarehouse();
    private final DataService dataService = new DataServiceImpl();
    private static final Engine engine = new Engine();

    private Engine() {
    }

    public static Engine getEngine() {
        return engine;
    }

    @Override
    public void run() {
        warehouse.actions();
        readData();
    }

    @Override
    public void readData() {
        try {
            String input = reader().readLine();
            while (test(input)) {
                String command = this.dataService.findCommand(input);
                switch (command) {
                    case GET -> this.dataService.getData(input);
                    case SET -> this.dataService.setData(input);
                    case SAVE -> this.dataService.saveData(input);
                    case LOAD -> this.dataService.loadData(input);
                    case REVERSE -> this.dataService.reverse(input);
                    case COUNT_WORDS -> this.dataService.countWords(input);
                    default -> System.out.println(VALID_ACTION);
                }

                input = reader().readLine();
            }
            System.out.println(GOODBYE);
        } catch (Exception e) {
            System.out.println(VALID_ACTION_DATA);
            // Recursion
            readData();
        }
    }

    private BufferedReader reader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }


    @Override
    public boolean test(String value) {
        return !value.equalsIgnoreCase(EXIT);
    }
}
