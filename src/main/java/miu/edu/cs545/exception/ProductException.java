package miu.edu.cs545.exception;

public class ProductException  extends RuntimeException  {
    public String getMessage() {
        return message;
    }

    private  String message;
    public ProductException(String message) {
        this.message=message;
    }

}
