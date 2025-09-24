package Firstprojekt.Firstprojekt.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "{address.city.notBlank}")
    @Size(min = 1, max = 50, message = "{address.city.size}")
    @Column(nullable = false)
    private String city;

    @NotNull
    @NotBlank(message = "{address.street.notBlank}")
    @Size(min = 1, max = 50, message = "{address.street.size}")
    @Column(nullable = false)
    private String street;

    @NotNull
    @NotBlank(message = "{address.houseNumber.notBlank}")
    @Size(min = 1, max = 50, message = "{address.houseNumber.size}")
    @Column(nullable = false)
    private String houseNumber;

}
