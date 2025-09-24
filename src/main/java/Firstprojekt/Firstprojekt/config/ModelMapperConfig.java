package Firstprojekt.Firstprojekt.config;

import Firstprojekt.Firstprojekt.dto.AddressDto;
import Firstprojekt.Firstprojekt.dto.PersonDto;
import Firstprojekt.Firstprojekt.model.Address;
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

        mm.typeMap(Person.class, PersonDto.class)
                .addMapping(Person::getAddress, PersonDto::setAddressDto);

        mm.typeMap(Address.class, AddressDto.class);

        return mm;
    }
}
