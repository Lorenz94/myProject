package net.thumbtack.school.old.ttschool;

public enum TrainingErrorCode {
    TRAINEE_WRONG_FIRSTNAME("FirstName can't be null"),
    TRAINEE_WRONG_LASTNAME("LastName cant'be null"),
    TRAINEE_WRONG_RATING("Rating must be between 1 and 5. Your rating = %s"),
    TRAINEE_NOT_FOUND("Trainee is not found"),
    GROUP_WRONG_NAME("Group name can't be null"),
    GROUP_WRONG_ROOM("Room can't be null"),
    GROUP_NOT_FOUND("Group is not found"),
    DUPLICATE_GROUP_NAME("Group already exists"),
    DUPLICATE_TRAINEE("Trainee already exists"),
    SCHOOL_WRONG_NAME("School name can't be null"),
    SCHOOL_WRONG_DATE("Wrong date"),
    EMPTY_TRAINEE_QUEUE("Queue is empty");


    private String errorCode;

    TrainingErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
