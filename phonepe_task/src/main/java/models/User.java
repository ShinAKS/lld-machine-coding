package models;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class User {
    private Integer userId;
    private String password;

    @Builder.Default
    private Set<Task> userTasks = new HashSet<>();
}
