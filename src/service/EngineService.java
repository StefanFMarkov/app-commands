package service;

import java.util.function.Predicate;

public interface EngineService extends Runnable , Predicate<String> {
      void readData();
}
