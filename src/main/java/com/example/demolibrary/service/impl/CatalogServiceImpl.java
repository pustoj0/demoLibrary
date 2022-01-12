package com.example.demolibrary.service.impl;

import com.example.demolibrary.model.Book;
import com.example.demolibrary.model.Catalog;
import com.example.demolibrary.repository.CatalogRepository;
import com.example.demolibrary.service.CatalogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private CatalogRepository catalogRepository;

    @Override
    public Catalog add(Catalog catalog) {
        return catalogRepository.save(catalog);
    }
}
