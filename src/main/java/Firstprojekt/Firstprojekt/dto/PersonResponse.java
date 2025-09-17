package Firstprojekt.Firstprojekt.dto;

import lombok.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PersonResponse {
    private List<PersonDto> personDto;
    private boolean last;
    private int totalElements;
    private int totalPages;
    private boolean first;
    private int size;
    private int pageNumber;
}
