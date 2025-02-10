package ru.isands.test.estore.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.isands.test.estore.dao.entity.PositionType;

import java.util.List;

@Repository
public interface PositionTypeRepository extends JpaRepository<PositionType, Long> {

    @Query("select e.id from PositionType e")
    List<Long> findAllPositionTypeIds();
}