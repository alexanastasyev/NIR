package dev.alexanastasyev.nirbackend.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import dev.alexanastasyev.nirbackend.model.CustomerCSVModel;
import dev.alexanastasyev.nirbackend.model.CustomerClusteringModel;
import dev.alexanastasyev.nirbackend.util.DoubleGetter;
import dev.alexanastasyev.nirbackend.util.DoubleSetter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

@Component
public class CustomerService {

    public List<CustomerClusteringModel> getCustomerClusteringModels() throws IOException {
        List<CustomerCSVModel> csvModels = readCsvModelsFromFile();
        List<CustomerClusteringModel> clusteringModels = convertCSVModelsToClustering(csvModels);
        normalizeCustomerModels(clusteringModels);
        return clusteringModels;
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

    @SuppressWarnings("DuplicatedCode")
    private void normalizeCustomerModels(List<CustomerClusteringModel> clusteringModels) {
        clusteringModels.parallelStream().forEach(model -> {
            normalizeField(clusteringModels, CustomerClusteringModel::getBirthYear, model::getBirthYear, model::setBirthYear);
            normalizeField(clusteringModels, CustomerClusteringModel::getEducation, model::getEducation, model::setEducation);
            normalizeField(clusteringModels, CustomerClusteringModel::getMaritalStatus, model::getMaritalStatus, model::setMaritalStatus);
            normalizeField(clusteringModels, CustomerClusteringModel::getIncome, model::getIncome, model::setIncome);
            normalizeField(clusteringModels, CustomerClusteringModel::getChildrenAmount, model::getChildrenAmount, model::setChildrenAmount);
            normalizeField(clusteringModels, CustomerClusteringModel::getEnrollmentDate, model::getEnrollmentDate, model::setEnrollmentDate);
            normalizeField(clusteringModels, CustomerClusteringModel::getRecency, model::getRecency, model::setRecency);
            normalizeField(clusteringModels, CustomerClusteringModel::getComplains, model::getComplains, model::setComplains);
            normalizeField(clusteringModels, CustomerClusteringModel::getWineAmount, model::getWineAmount, model::setWineAmount);
            normalizeField(clusteringModels, CustomerClusteringModel::getFruitsAmount, model::getFruitsAmount, model::setFruitsAmount);
            normalizeField(clusteringModels, CustomerClusteringModel::getMeatAmount, model::getMeatAmount, model::setMeatAmount);
            normalizeField(clusteringModels, CustomerClusteringModel::getFishAmount, model::getFishAmount, model::setFishAmount);
            normalizeField(clusteringModels, CustomerClusteringModel::getSweetAmount, model::getSweetAmount, model::setSweetAmount);
            normalizeField(clusteringModels, CustomerClusteringModel::getGoldAmount, model::getGoldAmount, model::setGoldAmount);
            normalizeField(clusteringModels, CustomerClusteringModel::getDiscountPurchasesAmount, model::getDiscountPurchasesAmount, model::setDiscountPurchasesAmount);
            normalizeField(clusteringModels, CustomerClusteringModel::getAcceptedCampaignsAmount, model::getAcceptedCampaignsAmount, model::setAcceptedCampaignsAmount);
            normalizeField(clusteringModels, CustomerClusteringModel::getWebPurchasesAmount, model::getWebPurchasesAmount, model::setWebPurchasesAmount);
            normalizeField(clusteringModels, CustomerClusteringModel::getCatalogPurchasesAmount, model::getCatalogPurchasesAmount, model::setCatalogPurchasesAmount);
            normalizeField(clusteringModels, CustomerClusteringModel::getStorePurchasesAmount, model::getStorePurchasesAmount, model::setStorePurchasesAmount);
            normalizeField(clusteringModels, CustomerClusteringModel::getWebsiteVisitsAmount, model::getWebsiteVisitsAmount, model::setWebsiteVisitsAmount);
        });
    }

    private void normalizeField(List<CustomerClusteringModel> clusteringModels,
                                ToDoubleFunction<CustomerClusteringModel> getterReference,
                                DoubleGetter getter, DoubleSetter setter) {
        double minValue = findMinFieldValue(clusteringModels, getterReference);
        double maxValue = findMaxFieldValue(clusteringModels, getterReference);
        setter.setValue( calculateNormalizedValue( getter.getValue(), minValue, maxValue ) );
    }

    private double findMaxFieldValue(List<CustomerClusteringModel> clusteringModels,
                                     ToDoubleFunction<CustomerClusteringModel> fieldGetter) {
        return clusteringModels.parallelStream()
                .mapToDouble(fieldGetter)
                .max().orElseThrow(NoSuchElementException::new);
    }

    private double findMinFieldValue(List<CustomerClusteringModel> clusteringModels,
                                     ToDoubleFunction<CustomerClusteringModel> fieldGetter) {
        return clusteringModels.parallelStream()
                .mapToDouble(fieldGetter)
                .min().orElseThrow(NoSuchElementException::new);
    }

    private double calculateNormalizedValue(double baseValue, double min, double max) {
        return (baseValue - min) / (max - min);
    }

}
