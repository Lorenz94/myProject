package net.thumbtack.school.old.colors;

public class ColorException extends Exception {

    private ColorErrorCode errorCode;

    public ColorException(ColorErrorCode errorCode, String param) {
        super(String.format(errorCode.getErrorString(), param));
        this.errorCode = errorCode;
    }

    public ColorException(ColorErrorCode errorCode, Throwable cause) {
        super(errorCode.getErrorString(),cause);
        this.errorCode = errorCode;
    }

    public ColorException(ColorErrorCode errorCode) {
        super(errorCode.getErrorString());
        this.errorCode = errorCode;
    }

    public ColorErrorCode getErrorCode() {
        return errorCode;
    }

}
