package Firstprojekt.Firstprojekt.dto;
import Firstprojekt.Firstprojekt.model.Address;
import jakarta.validation.constraints.*;
import lombok.*;

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public class PersonCreateRequest {

        @NotBlank(message = "{person.name.notBlank}")
        @Size(min = 2, max = 50, message = "{person.name.size}")
        private String name;

        @NotNull(message = "{person.age.notNull}")
        @Min(value = 0, message = "{person.age.min}")
        @Max(value = 150, message = "{person.age.max}")
        private Integer age;

        @NotBlank(message = "{person.workEmail.notBlank}")
        @Size(min = 2, max = 50, message = "{person.workEmail.size}")
        @Email(message = "{person.workEmail.email}")
        private String workEmail;

        @NotBlank(message = "{person.privateEmail.notBlank}")
        @Size(min = 2, max = 50, message = "{person.privateEmail.size}")
        @Email(message = "{person.privateEmail.email}")
        private String privateEmail;

        @NotNull(message = "{addressDto.addressDto.notNull}")
        private AddressDto addressDto;


}
