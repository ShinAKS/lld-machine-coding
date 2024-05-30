package dao;

import models.Task;
import models.enums.TaskStatus;
import models.enums.TaskType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskDao {

    public static TaskDao taskDaoInstance;
    private Map<Integer, Task> taskMap;
    private static int taskCounter;

    private TaskDao() {
        this.taskMap = new HashMap<>();
        taskCounter = 1;
    }

    public static TaskDao getTaskDaoInstance() {
        if (taskDaoInstance == null) {
            taskDaoInstance = new TaskDao();
        }
        return taskDaoInstance;
    }

    public void createTask(Task task) {
        task.setTaskId(taskCounter++);
        task.setTaskType(TaskType.TASK);
        task.setTaskStatus(TaskStatus.NOT_STARTED);
        this.taskMap.put(task.getTaskId(), task);
        System.out.println("Task created:: " + task);
    }

    public void createSubTask(int parentTaskId, Task subtask) {
        Task task = this.taskMap.get(parentTaskId);
        subtask.setTaskId(taskCounter++);
        subtask.setParentId(parentTaskId);
        subtask.setTaskType(TaskType.SUBTASK);
        subtask.setTaskStatus(TaskStatus.NOT_STARTED);
        task.getSubtasks().add(subtask);
        System.out.println("Subtask created:: " + subtask);
    }

    public Task readTask(int taskId) {
        Task task = this.taskMap.get(taskId);
        System.out.println("Task is :: " + task);
        return task;
    }

    public List<Task> getSubtasksForTask(int taskId) {
        Task task = this.taskMap.get(taskId);
        return task.getSubtasks();
    }


    public void createStory(String title, String description, List<Integer> taskList) {
        int storyId = taskCounter++;

        Task task = Task.builder()
                .taskId(storyId)
                .description(description)
                .taskType(TaskType.STORY)
                .build();

        List<Integer> validTasks = taskList.stream()
                .filter(taskId -> this.taskMap.containsKey(taskId))
                .collect(Collectors.toList());

        validTasks.stream()
                .forEach(validTaskId -> {
                    Task childTask = this.taskMap.get(validTaskId);
                    childTask.setParentId(storyId);
                });

        List<Task> validTaskObject = validTasks.stream()
                .map(taskId -> this.taskMap.get(taskId))
                .collect(Collectors.toList());

        task.setSubtasks(validTaskObject);
        System.out.println("Story created:: " + task);
    }

    public List<Task> getTasksForStory(int storyId) {
        Task story = this.taskMap.get(storyId);
        return story.getSubtasks();
    }

    public void updateTaskStatus(int taskId, TaskStatus taskStatus) {
        Task task = this.taskMap.get(taskId);
        task.setTaskStatus(taskStatus);

        if (task.getTaskType().equals(TaskType.SUBTASK)) {
            if (taskStatus.equals(TaskStatus.COMPLETE)) {
                markTaskCompleteIfAllSubtasksComplete(task);
            } else if (taskStatus.equals(TaskStatus.IN_PROGRESS)) {
                task.setTaskStatus(TaskStatus.IN_PROGRESS);
            }

        } else if (task.getTaskType().equals(TaskType.TASK)) {
            if (taskStatus.equals(TaskStatus.COMPLETE)) {
                markAllSubtasksComplete(task);
            }
        }


    }

    private void markAllSubtasksComplete(Task task) {
        for (Task subtask : task.getSubtasks()) {
            subtask.setTaskStatus(TaskStatus.COMPLETE);
        }
    }

    private void markTaskCompleteIfAllSubtasksComplete(Task task) {
        boolean allSubtaskComplete = task.getSubtasks()
                .stream()
                .allMatch(subtask -> subtask.getTaskStatus().equals(TaskStatus.COMPLETE));

        if (allSubtaskComplete) {
            task.setTaskStatus(TaskStatus.COMPLETE);
        }
    }


}
