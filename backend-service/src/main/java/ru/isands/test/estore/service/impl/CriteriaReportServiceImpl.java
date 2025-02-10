package ru.isands.test.estore.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.dao.entity.Purchase;
import ru.isands.test.estore.dao.repo.EmployeeRepository;
import ru.isands.test.estore.dao.repo.PurchaseRepository;
import ru.isands.test.estore.dto.CriteriasReportDTO;
import ru.isands.test.estore.service.CriteriaReportService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CriteriaReportServiceImpl implements CriteriaReportService {

    private final PurchaseRepository purchaseRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public CriteriasReportDTO getCriteriasReport() {
        // Поиск лучшего младшего продавца-консультанта, продавшего больше всех умных часов
        Map.Entry<Long, Integer> best = getBestManagerEntry();

        Long sum = purchaseRepository.countPurchasesSumForType(1L);

        CriteriasReportDTO.CriteriasReportDTOBuilder builder = CriteriasReportDTO.builder();
        if (best != null) {
            employeeRepository.findById(best.getKey())
                    .ifPresent(employee ->
                            builder.id(getFullName(employee)));
        }
        builder.sum(sum);

        return builder.build();
    }

    private static String getFullName(Employee employee) {
        return String.format("%s %s %s", employee.getLastName(), employee.getFirstName(), employee.getPatronymic());
    }

    private Map.Entry<Long, Integer> getBestManagerEntry() {
        List<Purchase> purchases = purchaseRepository.findByElectroType(5L);
        Map<Long, List<Purchase>> collect = purchases.stream().collect(Collectors.groupingBy(Purchase::getEmployeeId));
        Map<Long, Integer> sizeMap = collect.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,          // Key remains the same
                        entry -> entry.getValue().size()  // Value is the size of the list
                ));
        return sizeMap.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);
    }
}
