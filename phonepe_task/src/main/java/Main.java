import dao.UserDao;
import models.Task;
import models.User;
import service.TaskManager;
import service.TaskManagerImpl;
import service.UserManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManagerImpl();
        UserManager userManager = new UserManager();

//        User user1 = User.builder()
//                .userId(1)
//                .password("abc")
//                .build();
//
//        User user2 = User.builder()
//                .userId(2)
//                .password("pqr")
//                .build();

        userManager.createUser(1,"abc");
        userManager.createUser(2,"pqr");

        Task task1 = Task.builder()
                .description("task1")
                .deadline(123)
                .build();

        Task task2 = Task.builder()
                .description("task2")
                .deadline(567)
                .build();

        taskManager.createTask(task1);
        taskManager.createTask(task2);

        taskManager.assignTaskToUser(1,1);
        taskManager.assignTaskToUser(1,2);

        taskManager.getTasksForUser(1,new HashMap<>());

        taskManager.assignTaskToUser(2,1);

        taskManager.getTasksForUser(1,new HashMap<>());

        taskManager.getTasksForUser(2,new HashMap<>());
//
//        Map<String,String> params = new HashMap<>();
//
//        params.put("SORT","DESC");
//
//        taskManager.getTasksForUser(1,params);
////        taskManager.getTasksForUser(2,new HashMap<>());
//
//
//
        taskManager.createSubtask(1,Task.builder()
                .description("task3")
                .deadline(89)
                .build());
//
        taskManager.getTasksForStory(1);
//
        taskManager.moveTask(1,2);
//
        taskManager.getTasksForStory(1);
//
//        taskManager.readTask(1);


    }
}
