package dev.alexanastasyev.nirbackend.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import dev.alexanastasyev.nirbackend.model.CustomerCSVModel;
import dev.alexanastasyev.nirbackend.model.CustomerClusteringModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/data")
public class CsvController {

    @GetMapping("")
    public ResponseEntity<List<CustomerClusteringModel>> getCustomerModels() {
        try {
            List<CustomerCSVModel> csvModels = readCsvModelsFromFile();
            List<CustomerClusteringModel> clusteringModels = convertCSVModelsToClustering(csvModels);
            return ResponseEntity.ok(clusteringModels);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(new ArrayList<>());
        }
    }

    private List<CustomerCSVModel> readCsvModelsFromFile() throws IOException {
        List<CustomerCSVModel> csvModels = new ArrayList<>();

        Reader reader = Files.newBufferedReader(Paths.get("marketing_campaign.csv"));
        CsvToBean<CustomerCSVModel> csv = new CsvToBeanBuilder<CustomerCSVModel>(reader)
                .withType(CustomerCSVModel.class)
                .withSeparator('\t')
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        for (CustomerCSVModel customerCSVModel : csv) {
            csvModels.add(customerCSVModel);
        }
        csvModels.remove(0);

        return csvModels;
    }

    private List<CustomerClusteringModel> convertCSVModelsToClustering(List<CustomerCSVModel> csvModels) {
        return csvModels.parallelStream()
                .filter(CustomerCSVModel::hasNoEmptyFields)
                .map(CustomerClusteringModel::new)
                .collect(Collectors.toList());
    }

}
