package service.strategy;

import models.Task;

import java.util.List;

public interface SortingStrategy {

    public List<Task> getTasksBasedOnParam(List<Task> tasks, String param);
}
