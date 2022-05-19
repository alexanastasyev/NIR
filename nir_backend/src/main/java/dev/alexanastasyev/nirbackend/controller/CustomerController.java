package dev.alexanastasyev.nirbackend.controller;

import dev.alexanastasyev.nirbackend.model.CustomerCSVModel;
import dev.alexanastasyev.nirbackend.model.CustomerClusteringModel;
import dev.alexanastasyev.nirbackend.service.CustomerService;
import dev.alexanastasyev.nirbackend.util.clustering.OnEmptyFieldStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/data")
    public ResponseEntity<List<CustomerCSVModel>> getCustomerModels(@RequestParam String strategy) {
        try {
            OnEmptyFieldStrategy onEmptyFieldStrategy = OnEmptyFieldStrategy.valueOf(strategy.toUpperCase());
            List<CustomerCSVModel> csvModels = customerService.getCustomerCsvModels(onEmptyFieldStrategy);
            return ResponseEntity.ok(csvModels);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(new ArrayList<>());
        }
    }

    @GetMapping("/clusters")
    public ResponseEntity<List<Set<Long>>> getCustomerIdsClusters(@RequestParam double level,
                                                                  @RequestParam String strategy) {
        try {
            OnEmptyFieldStrategy onEmptyFieldStrategy = OnEmptyFieldStrategy.valueOf(strategy.toUpperCase());
            List<Set<Long>> clusters = customerService.getCustomerIdsClusters(level, onEmptyFieldStrategy);
            return ResponseEntity.ok(clusters);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(new ArrayList<>());
        }
    }

}
