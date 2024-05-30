package models;

import lombok.*;
import models.enums.TaskStatus;
import models.enums.TaskType;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@Builder
public class Task {
    private int taskId;
    @Builder.Default
    private Integer parentId = null ;
    private String description;
    private int deadline;
    private TaskType taskType;
    private TaskStatus taskStatus;
    @Builder.Default
    List<Task> subtasks = new ArrayList<>();

    @Builder.Default
    private Integer assignedTo = null;
}
