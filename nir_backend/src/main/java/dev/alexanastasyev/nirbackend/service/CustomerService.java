package dev.alexanastasyev.nirbackend.service;

import dev.alexanastasyev.nirbackend.model.CustomerCSVModel;
import dev.alexanastasyev.nirbackend.model.CustomerClusteringModel;
import dev.alexanastasyev.nirbackend.repository.CustomerRepository;
import dev.alexanastasyev.nirbackend.util.clustering.CustomerCSVToClusteringConverter;
import dev.alexanastasyev.nirbackend.util.clustering.AdjustmentMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerCSVModel> getCustomerCsvModels() throws IOException {
        return customerRepository.getCustomerCsvModels().stream()
                .filter(CustomerCSVModel::hasNoEmptyFields)
                .collect(Collectors.toList());
    }

    public List<Set<Long>> getCustomerIdsClusters(double level) throws IOException {
        AdjustmentMatrix adjustmentMatrix = getAdjustmentMatrix();

        List<Set<Integer>> indexClusters = adjustmentMatrix.getClusters(level);

        List<Set<Long>> idsClusters = new ArrayList<>();
        List<CustomerClusteringModel> customerClusteringModels = getConvertedCustomerClusteringModels();
        indexClusters.forEach(cluster ->
            idsClusters.add(
                cluster.stream().map(index ->
                    customerClusteringModels.get(index).getId()
                ).collect(Collectors.toSet())
            )
        );

        return idsClusters;
    }

    private AdjustmentMatrix getAdjustmentMatrix() throws IOException {
        AdjustmentMatrix adjustmentMatrix = new AdjustmentMatrix(getConvertedCustomerClusteringModels().size());

        List<CustomerClusteringModel> clusteringModels = getConvertedCustomerClusteringModels();

        for (int i = 0; i < clusteringModels.size() - 1; i++) {
            for (int j = i + 1; j < clusteringModels.size(); j++) {
                double distance = calculateDistance(clusteringModels.get(i), clusteringModels.get(j));
                adjustmentMatrix.addDistance(i, j, distance);
            }
        }

        return adjustmentMatrix;
    }

    private List<CustomerClusteringModel> getConvertedCustomerClusteringModels() throws IOException {
        List<CustomerCSVModel> csvModels = customerRepository.getCustomerCsvModels();
        return CustomerCSVToClusteringConverter.convertCSVModelsToClustering(csvModels);
    }

    private double calculateDistance(CustomerClusteringModel model1, CustomerClusteringModel model2) {
        return Math.sqrt(sum(
                sqrDiff(model1.getBirthYear(), model2.getBirthYear()),
                sqrDiff(model1.getEducation(), model2.getEducation()),
                sqrDiff(model1.getMaritalStatus(), model2.getMaritalStatus()),
                sqrDiff(model1.getIncome(), model2.getIncome()),
                sqrDiff(model1.getChildrenAmount(), model2.getChildrenAmount()),
                sqrDiff(model1.getEnrollmentDate(), model2.getEnrollmentDate()),
                sqrDiff(model1.getRecency(), model2.getRecency()),
                sqrDiff(model1.getComplains(), model2.getComplains()),
                sqrDiff(model1.getWineAmount(), model2.getWineAmount()),
                sqrDiff(model1.getFishAmount(), model2.getFishAmount()),
                sqrDiff(model1.getMeatAmount(), model2.getMeatAmount()),
                sqrDiff(model1.getFishAmount(), model2.getFishAmount()),
                sqrDiff(model1.getSweetAmount(), model2.getSweetAmount()),
                sqrDiff(model1.getGoldAmount(), model2.getGoldAmount()),
                sqrDiff(model1.getDiscountPurchasesAmount(), model2.getDiscountPurchasesAmount()),
                sqrDiff(model1.getAcceptedCampaignsAmount(), model2.getAcceptedCampaignsAmount()),
                sqrDiff(model1.getWebPurchasesAmount(), model2.getWebPurchasesAmount()),
                sqrDiff(model1.getCatalogPurchasesAmount(), model2.getCatalogPurchasesAmount()),
                sqrDiff(model1.getStorePurchasesAmount(), model2.getStorePurchasesAmount()),
                sqrDiff(model1.getWebsiteVisitsAmount(), model2.getWebsiteVisitsAmount())
        ));
    }

    private double sum(double... values) {
        return Arrays.stream(values).sum();
    }

    private double sqrDiff(double value1, double value2) {
        return Math.pow(value1 - value2, 2);
    }

}
