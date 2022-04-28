package dev.alexanastasyev.nirbackend.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import dev.alexanastasyev.nirbackend.entity.CustomerCSVModel;
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

@RestController
@RequestMapping("/api/data")
public class CsvController {

    @GetMapping("")
    public ResponseEntity<List<CustomerCSVModel>> readCSV() {

        try {
            List<CustomerCSVModel> result = new ArrayList<>();

            Reader reader = Files.newBufferedReader(Paths.get("marketing_campaign.csv"));
            CsvToBean<CustomerCSVModel> csv = new CsvToBeanBuilder<CustomerCSVModel>(reader)
                    .withType(CustomerCSVModel.class)
                    .withSeparator('\t')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (CustomerCSVModel customerCSVModel : csv) {
                result.add(customerCSVModel);
            }

            result.remove(0);

            return ResponseEntity.ok(result);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(new ArrayList<>());
        }

    }

}
