package net.thumbtack.school.old.colors;

public enum ColorErrorCode {
    WRONG_COLOR_STRING("Wrong color string %s"),
    NULL_COLOR("Null color");

    private String errorString;

    ColorErrorCode(String message) {
        this.errorString = message;
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

}
