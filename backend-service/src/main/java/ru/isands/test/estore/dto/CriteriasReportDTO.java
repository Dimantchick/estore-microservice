package ru.isands.test.estore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CriteriasReportDTO {

    private String id;

    private Long sum;
}
