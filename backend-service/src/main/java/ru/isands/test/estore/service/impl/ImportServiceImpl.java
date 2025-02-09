package ru.isands.test.estore.service.impl;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.isands.test.estore.dao.entity.*;
import ru.isands.test.estore.dao.repo.*;
import ru.isands.test.estore.service.ImportService;
import ru.isands.test.estore.service.StorageService;

import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Service
public class ImportServiceImpl implements ImportService {
    private final StorageService storageService;
    private final ElectroTypeRepository electroTypeRepository;
    private final PositionTypeRepository positionTypeRepository;
    private final PurchaseTypeRepository purchaseTypeRepository;
    private final ShopRepository shopRepository;
    private final EmployeeRepository employeeRepository;
    private final ElectroItemRepository electroItemRepository;
    private final PurchaseRepository purchaseRepository;
    private final ElectroEmployeeRepository electroEmployeeRepository;
    private final ElectroShopRepository electroShopRepository;

    public ImportServiceImpl(StorageService storageService, ElectroTypeRepository electroTypeRepository, PositionTypeRepository positionTypeRepository, PurchaseTypeRepository purchaseTypeRepository, ShopRepository shopRepository, EmployeeRepository employeeRepository, ElectroItemRepository electroItemRepository, PurchaseRepository purchaseRepository, ElectroEmployeeRepository electroEmployeeRepository, ElectroShopRepository electroShopRepository) {
        this.storageService = storageService;
        this.electroTypeRepository = electroTypeRepository;
        this.positionTypeRepository = positionTypeRepository;
        this.purchaseTypeRepository = purchaseTypeRepository;
        this.shopRepository = shopRepository;
        this.employeeRepository = employeeRepository;
        this.electroItemRepository = electroItemRepository;
        this.purchaseRepository = purchaseRepository;
        this.electroEmployeeRepository = electroEmployeeRepository;
        this.electroShopRepository = electroShopRepository;
    }

    @Override
    @Transactional
    public void importFile(MultipartFile file) {
        storageService.saveUnzippedFile(file);
        importElectroTypes();
        importPositionTypes();
        importPurchaseTypes();
        importShops();
        importEployees();
        importElectroItems();
        importPurchases();
        importElectroEmployees();
        importElectroShops();
        storageService.clean();
    }

    private void importElectroTypes() {
        Path file = storageService.getRootLocation().resolve("ElectroType.csv");
        if (Files.exists(file)) {
            List<ElectroType> electroTypes = loadObjectList(ElectroType.class, file);
            electroTypeRepository.saveAll(electroTypes);
        }
    }

    private void importPositionTypes() {
        Path file = storageService.getRootLocation().resolve("PositionType.csv");
        if (Files.exists(file)) {
            List<PositionType> positionTypes = loadObjectList(PositionType.class, file);
            positionTypeRepository.saveAll(positionTypes);
        }
    }

    private void importPurchaseTypes() {
        Path file = storageService.getRootLocation().resolve("PurchaseType.csv");
        if (Files.exists(file)) {
            List<PurchaseType> purchaseTypes = loadObjectList(PurchaseType.class, file);
            purchaseTypeRepository.saveAll(purchaseTypes);
        }
    }

    private void importShops() {
        Path file = storageService.getRootLocation().resolve("Shop.csv");
        if (Files.exists(file)) {
            List<Shop> shops = loadObjectList(Shop.class, file);
            shopRepository.saveAll(shops);
        }
    }

    private void importEployees() {
        List<PositionType> all = positionTypeRepository.findAll();
        log.info("Imported positionTypes: {}", all);
        Path file = storageService.getRootLocation().resolve("Employee.csv");
        if (Files.exists(file)) {
            List<Employee> employees = loadObjectList(Employee.class, file);
            employeeRepository.saveAll(employees);
        }
    }

    private void importElectroItems() {
        Path file = storageService.getRootLocation().resolve("ElectroItem.csv");
        if (Files.exists(file)) {
            List<ElectroItem> electroItems = loadObjectList(ElectroItem.class, file);
            electroItemRepository.saveAll(electroItems);
        }
    }

    private void importPurchases() {
        Path file = storageService.getRootLocation().resolve("Purchase.csv");
        if (Files.exists(file)) {
            List<Purchase> purchases = loadObjectList(Purchase.class, file);
            purchaseRepository.saveAll(purchases);
        }
    }

    private void importElectroEmployees() {
        Path file = storageService.getRootLocation().resolve("ElectroEmployee.csv");
        if (Files.exists(file)) {
            List<ElectroEmployee> electroEmployees = loadObjectList(ElectroEmployee.class, file);
            electroEmployeeRepository.saveAll(electroEmployees);
        }
    }

    private void importElectroShops() {
        Path file = storageService.getRootLocation().resolve("ElectroShop.csv");
        if (Files.exists(file)) {
            List<ElectroShop> electroShops = loadObjectList(ElectroShop.class, file);
            electroShopRepository.saveAll(electroShops);
        }
    }

    private  <T> List<T> loadObjectList(Class<T> type, Path path) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';');
            CsvMapper mapper = CsvMapper.builder()
                    .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                    .build();
            try (InputStreamReader bufferedReader = new InputStreamReader(Files.newInputStream(path), "windows-1251")) {
                MappingIterator<T> readValues = mapper.readerFor(type)
                        .with(bootstrapSchema)
                        .readValues(bufferedReader);
                return readValues.readAll();
            }
        } catch (Exception e) {
            log.error("Не удалось обработать файл {}", path.getFileName(), e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Не удалось обработать файл " + path.getFileName(), e);
        }
    }
}
