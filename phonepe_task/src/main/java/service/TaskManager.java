package service;

import models.Task;
import models.enums.TaskStatus;

import java.util.List;
import java.util.Map;

public interface TaskManager {


    void createTask(Task task);

    void createSubtask(Integer parentTaskId, Task task);

    void readTask(Integer taskId);

    void createStory(String title, String description, List<Integer> taskList);

    void getTasksForStory(Integer storyId);

    void assignTaskToUser(Integer userId, Integer taskId);

    void updateTaskStatus(Integer taskId, TaskStatus taskStatus);

    void moveTask(Integer parentTaskId, Integer taskId);

    void getTasksForUser(Integer userId, Map<String,String> args);



}
