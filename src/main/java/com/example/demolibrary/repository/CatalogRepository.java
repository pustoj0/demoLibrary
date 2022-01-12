package com.example.demolibrary.repository;

import com.example.demolibrary.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
}
