package com.mockcompany.webapp.controller;

import com.mockcompany.webapp.data.ProductItemRepository;
import com.mockcompany.webapp.model.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class SearchController {

    private final ProductItemRepository productItemRepository;

    @Autowired
    public SearchController(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    @GetMapping("/api/products/search")
    public Collection<ProductItem> search(@RequestParam("query") String query) {
        Iterable<ProductItem> allItems = this.productItemRepository.findAll();
        List<ProductItem> itemList = new ArrayList<>();

        // Normalize the query for case-insensitive comparison
        String normalizedQuery = query.toLowerCase().trim();
        boolean exactMatch = normalizedQuery.startsWith("\"") && normalizedQuery.endsWith("\"");

        for (ProductItem item : allItems) {
            String itemName = item.getName().toLowerCase();
            String itemDescription = item.getDescription().toLowerCase();

            boolean matchesSearch;
            if (exactMatch) {
                // Remove quotes for exact match comparison
                String exactQuery = normalizedQuery.substring(1, normalizedQuery.length() - 1);
                matchesSearch = itemName.equals(exactQuery) || itemDescription.equals(exactQuery);
            } else {
                matchesSearch = itemName.contains(normalizedQuery) || itemDescription.contains(normalizedQuery);
            }

            if (matchesSearch) {
                itemList.add(item);
            }
        }
        return itemList;
    }
}
