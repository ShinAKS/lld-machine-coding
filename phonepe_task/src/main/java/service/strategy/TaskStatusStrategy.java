package service.strategy;

import models.Task;
import models.enums.TaskType;

import java.util.List;
import java.util.stream.Collectors;

public class TaskStatusStrategy implements FilterStrategy{
    @Override
    public List<Task> getTasksBasedOnFilter(List<Task> tasks, TaskType taskType) {
        return tasks.stream()
                .filter(task -> task.getTaskType().equals(taskType))
                .collect(Collectors.toList());
    }
}
