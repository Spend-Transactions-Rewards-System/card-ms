package sg.edu.smu.cs301.group3.cardms.models;

public class ExceptionResponse {
    private String status;
    private String message;

    public ExceptionResponse(String message) {
        this.status = "error";
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
