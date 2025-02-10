package ru.isands.test.estore.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.ElectroItem;
import ru.isands.test.estore.dao.entity.Purchase;
import ru.isands.test.estore.dao.repo.EmployeeRepository;
import ru.isands.test.estore.dao.repo.PositionTypeRepository;
import ru.isands.test.estore.dao.repo.PurchaseRepository;
import ru.isands.test.estore.dto.BestPositionEmployeeDTO;
import ru.isands.test.estore.dto.EmployeeSales;
import ru.isands.test.estore.service.BestPositionEmployeeService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BestPositionEmployeeServiceImpl implements BestPositionEmployeeService {

    private final PositionTypeRepository positionTypeRepository;
    private final EmployeeRepository employeeRepository;
    private final PurchaseRepository purchaseRepository;

    public BestPositionEmployeeServiceImpl(PositionTypeRepository positionTypeRepository, EmployeeRepository employeeRepository, PurchaseRepository purchaseRepository) {
        this.positionTypeRepository = positionTypeRepository;
        this.employeeRepository = employeeRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    @Transactional
    public Page<BestPositionEmployeeDTO> getBestPositionEmployee(Pageable pageable) {
        // TODO implement sort and paging
        List<Long> allPositions = positionTypeRepository.findAllPositionTypeIds();
        List<BestPositionEmployeeDTO> collect = allPositions.stream()
                .map(this::calculate)
                .collect(Collectors.toList());

        return new PageImpl<>(collect);
    }

    private BestPositionEmployeeDTO calculate(Long positionId) {
        List<Long> employeeIdsByPositionId = employeeRepository.findEmployeeIdsByPositionId(positionId);
        LocalDate yearAgo = LocalDate.now().minusYears(1);
        java.sql.Date sqlDate = java.sql.Date.valueOf(yearAgo);
        Date date = new Date(sqlDate.getTime());
        List<EmployeeSales> employeeSales = employeeIdsByPositionId.stream()
                .map(employeeId -> {
                    List<Purchase> sales = purchaseRepository.findByEmployeeIdAndPurchaseDateAfter(employeeId, date);
                    getSalesSum(sales);
                    Long sum = getSalesSum(sales);
                    return new EmployeeSales(employeeId, sales.size(), sum);
                }).collect(Collectors.toList());
        EmployeeSales maxSales = employeeSales.stream().max(Comparator.comparing(EmployeeSales::getSales)).orElse(null);
        EmployeeSales maxSum = employeeSales.stream().max(Comparator.comparing(EmployeeSales::getSum)).orElse(null);

        BestPositionEmployeeDTO.BestPositionEmployeeDTOBuilder bestPositionEmployeeDTOBuilder = BestPositionEmployeeDTO.builder()
                .id(positionId);
        if (maxSales != null) {
            bestPositionEmployeeDTOBuilder
                    .salesEmployeeId(maxSales.getEmployeeId())
                    .sales(maxSales.getSales());
        }
        if (maxSum != null) {
            bestPositionEmployeeDTOBuilder
                    .sumEmployeeId(maxSum.getEmployeeId())
                    .sumSales(maxSum.getSum());
        }

        return bestPositionEmployeeDTOBuilder.build();
    }

    private Long getSalesSum(List<Purchase> sales) {
        return sales.stream()
                .map(Purchase::getElectroItem)
                .mapToLong(ElectroItem::getPrice)
                .sum();
    }


}
