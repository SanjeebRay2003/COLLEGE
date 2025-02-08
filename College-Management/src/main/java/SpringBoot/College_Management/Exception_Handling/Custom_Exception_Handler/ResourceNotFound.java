package SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String message) {
        super(message);
    }
}
