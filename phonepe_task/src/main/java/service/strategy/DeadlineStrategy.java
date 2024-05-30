package service.strategy;

import models.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DeadlineStrategy implements SortingStrategy{


    @Override
    public List<Task> getTasksBasedOnParam(List<Task> tasks, String param) {

        if ("ASC".equals(param)){
            return tasks.stream()
                    .sorted(Comparator.comparing(Task::getDeadline))
                    .collect(Collectors.toList());
        }
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDeadline,Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}
