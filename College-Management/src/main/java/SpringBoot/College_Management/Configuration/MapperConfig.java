package SpringBoot.College_Management.Configuration;

//import SpringBoot.College_Management.Logger_Section.Audit_Aware_Implementation;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }




}
