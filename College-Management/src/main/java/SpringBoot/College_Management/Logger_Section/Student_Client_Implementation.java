//package SpringBoot.College_Management.Logger_Section;
//
//import SpringBoot.College_Management.Exception_Handling.Api_Response;
//import SpringBoot.College_Management.Students.Student_DTO;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClient;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class Student_Client_Implementation implements Student_Client {
//
//    private final RestClient restClient;
//
//    Logger logger = LoggerFactory.getLogger(Student_Client_Implementation.class);
//
//    @Override
//    public List<Student_DTO> getAllStudents() {
//        logger.trace("Trying to get List of employees");
//        try {
//            logger.info("Attempting to call restClient method in getAllEmployees");
//            Api_Response<List<Student_DTO>> employeeList = restClient.get() // restClient is used for create http request , get() shows GET request
//                    .uri("employees")
//                    .retrieve() // This sends the GET request to the server and retrieves the response. it fetch all the data but not in the format
//                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
//                        logger.error("error occurred " + new String(response.getBody().readAllBytes()));
//                        throw new RuntimeException("could not create student");
//                    })
//                    .body(new ParameterizedTypeReference<>() {
//                    }); // it converts the response data into java objects,it fetch the data in a json format
//
//            logger.debug("Successfully retrieve the all employees in getAllEmployees");
//            logger.trace("Received employee list in getAllEmployees : {}", employeeList.getData());
//
//            return employeeList.getData();
//        }catch (Exception e){
//            logger.error("Error occurred in getAllEmployees", e);
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @Override
//    public Student_DTO getStudentById(Long studentId) {
//        logger.trace("Attempting to get employee with id " + studentId);
//        try {
//            Api_Response<Student_DTO> employeeDto = restClient.get()
//                    .uri("employees/{employeeId}", studentId)
//                    .retrieve()
//                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
//                        logger.error("error occurred " + new String(response.getBody().readAllBytes()));
//                        throw new RuntimeException("could not create employee");
//                    })
//                    .body(new ParameterizedTypeReference<>() {
//                    });
//            logger.trace("Successfully retrieve the employees with id " + employeeDto.getData());
//            return employeeDto.getData();
//        }catch (Exception e){
//            logger.error("Error occurred in getEmployeeById ", e);
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @Override
//    public Student_DTO addNewStudent(Student_DTO inputStudent) {
//        logger.trace("Trying to create student with this information {}",inputStudent);
//        try {
//            ResponseEntity<Api_Response<Student_DTO>> employeeDto = restClient.post()
//                    .uri("employees")
//                    .body(inputStudent)
//                    .retrieve()
//                    .onStatus(HttpStatusCode::is4xxClientError,(request,response) -> {
//                        logger.error("is4xxClientError occurred during addNewStudent");
//                        logger.error("error occurred "+ new String(response.getBody().readAllBytes()));
//                        throw new RuntimeException("could not create student");
//                    })
//                    .toEntity(new ParameterizedTypeReference<>() {
//                    });
//            logger.trace("Successfully created new student {}",employeeDto);
//            return employeeDto.getBody().getData();
//        }catch (Exception e){
//            logger.error("Error occurred in getStudentById ", e);
//            throw new RuntimeException(e);
//        }
//    }
//}
//
