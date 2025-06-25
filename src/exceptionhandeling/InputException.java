package exceptionhandeling;

public class InputException extends RuntimeException{
    private String message;
    private String className;
    private String methodName;

    public InputException(String message, String className, String methodName) {
        this.message = message;
        this.className = className;
        this.methodName = methodName;
    }

    public String getMessage() {
        return message;
    }



    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }


}
