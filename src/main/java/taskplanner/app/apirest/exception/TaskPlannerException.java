package taskplanner.app.apirest.exception;

public class TaskPlannerException extends Exception{
    private static final long serialVersionUID = 1L;

    public TaskPlannerException(String message) {
        super(message);
    }

    public TaskPlannerException(String message, Throwable cause) {
        super(message, cause);
    }
}