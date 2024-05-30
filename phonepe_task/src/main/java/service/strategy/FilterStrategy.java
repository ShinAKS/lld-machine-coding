package service.strategy;

import models.Task;
import models.enums.TaskType;

import java.util.List;

public interface FilterStrategy {

    public List<Task> getTasksBasedOnFilter(List<Task> tasks,TaskType taskType);
}
