//package SpringBoot.College_Management.Logger_Section.Configuration;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.web.client.RestClient;
//
//import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@Configuration
//public class RestClient_Config {
//
//    @Value("${employeeService.base.url}")
//    private String BASE_URL;
//
//    @Bean
//    @Qualifier("getEmployeeServiceRestClient")
//    RestClient getEmployeeServiceRestClient() {
//        return RestClient.builder()
//                .baseUrl(BASE_URL)
//                .defaultHeader(CONTENT_TYPE,APPLICATION_JSON_VALUE)
//                .defaultStatusHandler(HttpStatusCode::is5xxServerError,(request, response) -> {
//                    throw new RuntimeException("Server error Occurred");
//                })
//                .build();
//    }
//
//}
