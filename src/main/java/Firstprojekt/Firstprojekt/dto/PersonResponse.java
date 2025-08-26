package Firstprojekt.Firstprojekt.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PersonResponse {
    private Long id;
    private String name;
    private Integer age;
    private String workEmail;
}
