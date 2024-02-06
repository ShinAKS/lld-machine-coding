package models;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class User {

    private String userId;
    private int tokenId;

    private static int tokenCounter = 0;

    public User(String userId){
        this.userId = userId;
        this.tokenId = tokenCounter++;
    }
}
