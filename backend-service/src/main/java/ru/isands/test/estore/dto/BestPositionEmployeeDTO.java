package ru.isands.test.estore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BestPositionEmployeeDTO {

    private Long id;
    private Long salesEmployeeId;
    private int sales;
    private Long sumEmployeeId;
    private Long sumSales;
}
