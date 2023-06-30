package ru.practicum.mainmicroservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.mainmicroservice.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//    Category findByName(String name);
    Page<Category> findAll(Pageable pageable);

}
