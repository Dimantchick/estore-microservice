package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.isands.test.estore.dao.entity.Employee;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where e.lastName = ?1 and e.firstName = ?2 and e.patronymic = ?3 and e.birthDate = ?4")
    public Employee findFullName(String lastName, String firstName, String patronymic, Date birthDate);

    @Query("select e from Employee e where e.positionId = ?1")
    public List<Employee> findByPosition(Long positionId);

    @Query("select e.id from Employee e where e.positionId = ?1")
    public List<Long> findEmployeeIdsByPositionId(Long positionId);

}