package Firstprojekt.Firstprojekt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PersonDto {

    @NonNull
    private Long id;

    @NonNull
    @Size(min = 2, max = 50, message = "{person.name.size}")
    private String name;

    @NonNull
    @Min(value = 0,  message = "{person.age.min}")
    @Max(value = 150, message = "{person.age.max}")
    private Integer age;

    @NonNull
    @Size(min = 2, max = 50, message = "{person.workEmail.size}")
    @Email(message = "{person.workEmail.email}")
    private String workEmail;

}
