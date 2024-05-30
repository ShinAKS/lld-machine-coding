package models.enums;

public enum TaskStatus {

    NOT_STARTED("NOT_STARTED"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETE("COMPLETE");


    TaskStatus(String value){
        this.value = value;
    }
    private String value;

    public String getValue() {
        return value;
    }

    public static TaskStatus fromString(String text) {
        for (TaskStatus status : TaskStatus.values()) {
            if (status.value.equalsIgnoreCase(text)) {
                return status;
            }
        }
        return null;
    }


}
