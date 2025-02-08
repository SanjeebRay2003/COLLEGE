package SpringBoot.College_Management.Exception_Handling;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Api_Response<T> {
    @JsonFormat(pattern = "YYYY-MM-DD  hh:mm:ss")
    private LocalDateTime timeStamp;
    private T data;
    private Api_Error error;

    public Api_Response() {
        this.timeStamp = LocalDateTime.now();
    }

    public Api_Response(T data) {
        this(); // call the default constructor ( timeStamp )
        this.data = data;
    }

    public Api_Response(Api_Error error) {
        this(); // call the default constructor ( timeStamp )
        this.error = error;
    }
}
