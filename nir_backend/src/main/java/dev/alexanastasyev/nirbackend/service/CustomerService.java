package dev.alexanastasyev.nirbackend.service;

import dev.alexanastasyev.nirbackend.exception.SelectionIsEmptyException;
import dev.alexanastasyev.nirbackend.model.CustomerCSVModel;
import dev.alexanastasyev.nirbackend.model.CustomerClusteringModel;
import dev.alexanastasyev.nirbackend.repository.CustomerRepository;
import dev.alexanastasyev.nirbackend.util.clustering.AdjustmentMatrix;
import dev.alexanastasyev.nirbackend.util.clustering.CustomerClusteringNormalizer;
import dev.alexanastasyev.nirbackend.util.clustering.OnEmptyFieldStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

@Component
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerCSVModel> getCustomerCsvModels() throws IOException {
        return provideCustomerCsvModels();
    }

    public List<Set<Long>> getCustomerIdsClusters(double level, OnEmptyFieldStrategy strategy) throws IOException {
        long startTime = System.currentTimeMillis();

        AdjustmentMatrix adjustmentMatrix = provideAdjustmentMatrix(strategy);

        List<Set<Integer>> indexClusters = adjustmentMatrix.getClusters(level);

        List<Set<Long>> idsClusters = new ArrayList<>();
        List<CustomerClusteringModel> customerClusteringModels =
                provideConvertedCustomerClusteringModels(strategy);
        indexClusters.forEach(cluster ->
                idsClusters.add(
                        cluster.stream().map(index ->
                                customerClusteringModels.get(index).getId()
                        ).collect(Collectors.toSet())
                )
        );

        long endTime = System.currentTimeMillis();
        System.out.println("Level=" + level + ";\tstrategy=" + strategy + ";\t time = " + (endTime - startTime) + " ms.");

        return idsClusters;
    }

    private AdjustmentMatrix provideAdjustmentMatrix(OnEmptyFieldStrategy strategy) throws IOException {
        AdjustmentMatrix adjustmentMatrix =
                new AdjustmentMatrix(provideConvertedCustomerClusteringModels(strategy).size());

        List<CustomerClusteringModel> clusteringModels =
                provideConvertedCustomerClusteringModels(strategy);

        for (int i = 0; i < clusteringModels.size() - 1; i++) {
            for (int j = i + 1; j < clusteringModels.size(); j++) {
                double distance = calculateDistance(clusteringModels.get(i), clusteringModels.get(j));
                adjustmentMatrix.addDistance(i, j, distance);
            }
        }

        return adjustmentMatrix;
    }

    private List<CustomerClusteringModel> provideConvertedCustomerClusteringModels(OnEmptyFieldStrategy strategy)
            throws IOException {

        List<CustomerClusteringModel> clusteringModels = provideCustomerCsvModels().parallelStream()
                .filter(CustomerCSVModel::hasNoEmptyFields)
                .map(CustomerClusteringModel::new)
                .collect(Collectors.toList());

        switch (strategy) {
            case SKIP:
                 break;
            case AVERAGE:
                CustomerClusteringModel averageModel = findAverageModel(clusteringModels);

                clusteringModels = provideCustomerCsvModels().parallelStream()
                        .map(csvModel -> {
                            CustomerClusteringModel clusteringModel = new CustomerClusteringModel(csvModel);
                            clusteringModel.replaceEmptyFieldsWithAverage(averageModel);
                            return clusteringModel;
                        })
                        .collect(Collectors.toList());
                break;
            default:
                clusteringModels = new ArrayList<>();
        }

        CustomerClusteringNormalizer.normalizeCustomerModels(clusteringModels);
        return clusteringModels;
    }

    private List<CustomerCSVModel> provideCustomerCsvModels() throws IOException {
        return customerRepository.getCustomerCsvModels().parallelStream().collect(Collectors.toList());
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

    @SuppressWarnings("DuplicatedCode")
    private CustomerClusteringModel findAverageModel(List<CustomerClusteringModel> clusteringModels) {
        double birthYear = findAverageValue(clusteringModels, CustomerClusteringModel::getBirthYear);
        double education = findAverageValue(clusteringModels, CustomerClusteringModel::getEducation);
        double maritalStatus = findAverageValue(clusteringModels, CustomerClusteringModel::getMaritalStatus);
        double income = findAverageValue(clusteringModels, CustomerClusteringModel::getIncome);
        double childrenAmount = findAverageValue(clusteringModels, CustomerClusteringModel::getChildrenAmount);
        double enrollmentDate = findAverageValue(clusteringModels, CustomerClusteringModel::getEnrollmentDate);
        double recency = findAverageValue(clusteringModels, CustomerClusteringModel::getRecency);
        double complains = findAverageValue(clusteringModels, CustomerClusteringModel::getComplains);
        double wineAmount = findAverageValue(clusteringModels, CustomerClusteringModel::getWineAmount);
        double fruitsAmount = findAverageValue(clusteringModels, CustomerClusteringModel::getFruitsAmount);
        double meatAmount = findAverageValue(clusteringModels, CustomerClusteringModel::getMeatAmount);
        double fishAmount = findAverageValue(clusteringModels, CustomerClusteringModel::getFishAmount);
        double sweetAmount = findAverageValue(clusteringModels, CustomerClusteringModel::getSweetAmount);
        double goldAmount = findAverageValue(clusteringModels, CustomerClusteringModel::getGoldAmount);
        double discountPurchasesAmount = findAverageValue(clusteringModels, CustomerClusteringModel::getDiscountPurchasesAmount);
        double acceptedCampaignsAmount = findAverageValue(clusteringModels, CustomerClusteringModel::getAcceptedCampaignsAmount);
        double webPurchasesAmount = findAverageValue(clusteringModels, CustomerClusteringModel::getWebPurchasesAmount);
        double catalogPurchasesAmount = findAverageValue(clusteringModels, CustomerClusteringModel::getCatalogPurchasesAmount);
        double storePurchasesAmount = findAverageValue(clusteringModels, CustomerClusteringModel::getStorePurchasesAmount);
        double websiteVisitsAmount = findAverageValue(clusteringModels, CustomerClusteringModel::getWebsiteVisitsAmount);

        CustomerClusteringModel clusteringModel = new CustomerClusteringModel();

        clusteringModel.setBirthYear(birthYear);
        clusteringModel.setEducation(education);
        clusteringModel.setMaritalStatus(maritalStatus);
        clusteringModel.setIncome(income);
        clusteringModel.setChildrenAmount(childrenAmount);
        clusteringModel.setEnrollmentDate(enrollmentDate);
        clusteringModel.setRecency(recency);
        clusteringModel.setComplains(complains);
        clusteringModel.setWineAmount(wineAmount);
        clusteringModel.setFruitsAmount(fruitsAmount);
        clusteringModel.setMeatAmount(meatAmount);
        clusteringModel.setFishAmount(fishAmount);
        clusteringModel.setSweetAmount(sweetAmount);
        clusteringModel.setGoldAmount(goldAmount);
        clusteringModel.setDiscountPurchasesAmount(discountPurchasesAmount);
        clusteringModel.setAcceptedCampaignsAmount(acceptedCampaignsAmount);
        clusteringModel.setWebPurchasesAmount(webPurchasesAmount);
        clusteringModel.setCatalogPurchasesAmount(catalogPurchasesAmount);
        clusteringModel.setStorePurchasesAmount(storePurchasesAmount);
        clusteringModel.setWebsiteVisitsAmount(websiteVisitsAmount);

        return clusteringModel;
    }

    private double findAverageValue(List<CustomerClusteringModel> clusteringModels,
                                    ToDoubleFunction<CustomerClusteringModel> getterReference) {
        return clusteringModels.parallelStream()
                .mapToDouble(getterReference)
                .average().orElseThrow(SelectionIsEmptyException::new);
    }

}
