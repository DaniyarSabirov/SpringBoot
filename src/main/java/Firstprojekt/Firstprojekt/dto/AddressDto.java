package Firstprojekt.Firstprojekt.dto;


import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {


    @NonNull
    private Long id;

    private String city;
    private String street;
    private String houseNumber;
}
