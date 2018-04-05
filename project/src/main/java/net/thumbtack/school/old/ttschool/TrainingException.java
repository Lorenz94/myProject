package net.thumbtack.school.old.ttschool;

public class TrainingException extends Exception {

    private TrainingErrorCode errorCode;

    public TrainingException(TrainingErrorCode code, int param) {
        super(String.format(code.getErrorCode(),param));
        this.errorCode = code;
    }

    public TrainingException(TrainingErrorCode code) {
        super(code.getErrorCode());
        this.errorCode = code;
    }

    public TrainingException(TrainingErrorCode code, Throwable cause) {
        super(cause);
        this.errorCode = code;
    }

    public TrainingErrorCode getErrorCode() {
        return errorCode;
    }
}
