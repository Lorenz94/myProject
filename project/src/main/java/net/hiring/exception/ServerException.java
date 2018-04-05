package net.thumbtack.school.hiring.exception;

public class ServerException extends Exception {
    private ServerErrorCode serverErrorCode;

    public ServerException(ServerErrorCode code, String param) {
        super(String.format(code.getErrorCode(), param));
        this.serverErrorCode = code;
    }

    public ServerException(ServerErrorCode code) {
        super(code.getErrorCode());
        this.serverErrorCode = code;
    }

    public ServerException(ServerErrorCode code, Throwable cause) {
        super(code.getErrorCode(), cause);
        this.serverErrorCode = code;
    }

    public ServerException(ServerErrorCode code, String param, Throwable cause) {
        super(String.format(code.getErrorCode(), param), cause);
        this.serverErrorCode = code;
    }


    public ServerErrorCode getErrorCode() {
        return serverErrorCode;
    }
}
