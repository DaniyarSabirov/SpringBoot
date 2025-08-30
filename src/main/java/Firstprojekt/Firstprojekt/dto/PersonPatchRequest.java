package Firstprojekt.Firstprojekt.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor
public class PersonPatchRequest {
    @Size(min = 2, max = 50, message = "{person.name.size}")
    private String name;

    @Min(value = 0,  message = "{person.age.min}")
    @Max(value = 150, message = "{person.age.max}")
    private Integer age;

    @Size(min = 2, max = 50, message = "{person.workEmail.size}")
    @Email(message = "{person.workEmail.email}")
    private String workEmail;

    @Size(min = 2, max = 50, message = "{person.privateEmail.size}")
    @Email(message = "{person.privateEmail.email}")
    private String privateEmail;
}
