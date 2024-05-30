package service;

import dao.TaskDao;
import dao.UserDao;
import models.Task;
import models.User;
import models.enums.TaskStatus;
import models.enums.TaskType;
import service.strategy.DeadlineStrategy;
import service.strategy.FilterStrategy;
import service.strategy.SortingStrategy;
import service.strategy.TaskStatusStrategy;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TaskManagerImpl implements TaskManager{

    private TaskDao taskDao;
    private UserDao userDao;

    public TaskManagerImpl(){
        this.taskDao = TaskDao.getTaskDaoInstance();
        this.userDao = UserDao.getUserDaoInstance();
    }


    @Override
    public void createTask(Task task) {
        this.taskDao.createTask(task);
    }

    @Override
    public void createSubtask(Integer parentTaskId, Task task) {
        this.taskDao.createSubTask(parentTaskId,task);
    }

    @Override
    public void readTask(Integer taskId) {
        this.taskDao.readTask(taskId);
    }

    @Override
    public void createStory(String title, String description, List<Integer> taskList) {
        this.taskDao.createStory(title,description,taskList);
    }

    @Override
    public void getTasksForStory(Integer storyId) {
        List<Task> tasks = this.taskDao.getTasksForStory(storyId);
        System.out.println("Tasks for story :: " + storyId + " :: " + tasks);
    }

    @Override
    public void assignTaskToUser(Integer userId, Integer taskId) {
        User user = userDao.getUser(userId);
        Task task = taskDao.readTask(taskId);
        if (Objects.nonNull(task.getAssignedTo())){
            int previousUserAssignedTo = task.getAssignedTo();
            User previousUser = userDao.getUser(previousUserAssignedTo);
            if (Objects.nonNull(previousUser)){
                previousUser.getUserTasks().remove(task);
            }
        }
        task.setAssignedTo(userId);
        user.getUserTasks().add(task);
    }

    @Override
    public void updateTaskStatus(Integer taskId, TaskStatus taskStatus) {
        this.taskDao.updateTaskStatus(taskId,taskStatus);
    }

    @Override
    public void moveTask(Integer parentTaskId, Integer taskId) {
        Task task = taskDao.readTask(taskId);
        Task parentTask = taskDao.readTask(parentTaskId);
        if (Objects.nonNull(parentTask)){
            if (Objects.nonNull(parentTask)){
                parentTask.getSubtasks().remove(task);
            }

        }
        task.setParentId(parentTaskId);
        parentTask.getSubtasks().add(task);

    }

    @Override
    public void getTasksForUser(Integer userId, Map<String, String> filter) {

        User user = userDao.getUser(userId);
        List<Task> userTasks = user.getUserTasks()
                .stream().toList();

        if (filter.containsKey("SORT")){
            SortingStrategy deadlineStrategy = new DeadlineStrategy();
            userTasks = deadlineStrategy.getTasksBasedOnParam(userTasks,filter.get("SORT"));
        }

        if (filter.containsKey("TASKTYPE")){
            FilterStrategy filterStrategy = new TaskStatusStrategy();
            userTasks = filterStrategy.getTasksBasedOnFilter(userTasks, TaskType.valueOf(filter.get("TASKTYPE")));
        }

        System.out.println("UserTasks :: " + userTasks);

    }
}
