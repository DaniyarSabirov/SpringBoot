package Firstprojekt.Firstprojekt.config;

import Firstprojekt.Firstprojekt.model.Person;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
        @Bean
        public ModelMapper modelMapper() {
            ModelMapper mm = new ModelMapper();
            mm.getConfiguration().setSkipNullEnabled(true);
            mm.typeMap(Person.class, Person.class)
                    .addMappings(m -> m.skip(Person::setId));
            return mm;
        }
}
