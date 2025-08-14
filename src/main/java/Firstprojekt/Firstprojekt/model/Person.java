package Firstprojekt.Firstprojekt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = "{person.name.notBlank}")
    @Size(min = 2, max = 50, message = "{person.name.size}")
    @Column(nullable = false)
    private String name;

    @NotNull
    @Min(value = 0, message = "{person.age.min}")
    @Max(value = 150, message = "{person.age.max}")
    @Column(nullable = false)
    private int age;
}
