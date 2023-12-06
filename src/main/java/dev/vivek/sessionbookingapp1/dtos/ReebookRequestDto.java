package dev.vivek.sessionbookingapp1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReebookRequestDto {
    private Long clientId;
    private Long consultantId;
    private String startDate;
    private String endDate;
    private String startTime;
    private String frequency;
}
