package SpringBoot.College_Management.Exception_Handling;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class Api_Error {

    private HttpStatus status;
    private String message;
    private List<String> error;
}
