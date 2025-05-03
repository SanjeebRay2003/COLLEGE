package SpringBoot.College_Management.Exception_Handling;

import SpringBoot.College_Management.Exception_Handling.Custom_Exception_Handler.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class Global_Exception_Handler {

    // it handles resource not found error
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Api_Response<?>> employeeNotFound(ResourceNotFound exception){
        Api_Error apiError = Api_Error.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return buildErrorResponse(apiError);
    }

    // It handle internal server error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Api_Response<?>> handleInternalServerError(Exception exception){
        Api_Error apiError = Api_Error.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildErrorResponse(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Api_Response<?>> handleAccessDeniedException(AccessDeniedException exception){
        Api_Error apiError = Api_Error.builder()
                .status(HttpStatus.FORBIDDEN)
                .message(exception.getMessage())
                .build();
        return buildErrorResponse(apiError);
    }




    // It handles all the validation error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Api_Response<?>> handleInputValidationErrors( MethodArgumentNotValidException exception){
        // bind all the exceptions in lists for clear understanding
        List<String> errors = exception
                .getBindingResult() // binds all the exception in a list
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage()) // it shows the msg we define during validation in DTO
                .collect(Collectors.toList());

        Api_Error apiError = Api_Error.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input Validation Failed")
                .error(errors)
                .build();
        return buildErrorResponse(apiError);


    }

    private ResponseEntity<Api_Response<?>> buildErrorResponse(Api_Error apiError) {
        return new ResponseEntity<>(new Api_Response<>(apiError),apiError.getStatus());
    }


}
