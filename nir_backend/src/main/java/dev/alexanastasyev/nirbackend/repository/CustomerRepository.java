package dev.alexanastasyev.nirbackend.repository;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import dev.alexanastasyev.nirbackend.model.CustomerCSVModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class CustomerRepository {

    private List<CustomerCSVModel> csvModels;

    public List<CustomerCSVModel> getCustomerCsvModels() throws IOException {
        if (csvModels == null || csvModels.isEmpty()) {
            readCsvModelsFromFile();
        }
        return csvModels;
    }

    private void readCsvModelsFromFile() throws IOException {
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

        this.csvModels = csvModels;
    }

}
