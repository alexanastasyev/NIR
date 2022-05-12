package dev.alexanastasyev.nirbackend.util.clustering;

import dev.alexanastasyev.nirbackend.exception.SelectionIsEmptyException;
import dev.alexanastasyev.nirbackend.model.CustomerClusteringModel;
import dev.alexanastasyev.nirbackend.util.lambda.DoubleGetter;
import dev.alexanastasyev.nirbackend.util.lambda.DoubleSetter;

import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class CustomerClusteringNormalizer {
    private static final double CONST_VALUE = 0.0;

    @SuppressWarnings("DuplicatedCode")
    public static void normalizeCustomerModels(List<CustomerClusteringModel> clusteringModels) {
        List<CustomerClusteringModel> clusteringModelsCopy = clusteringModels.parallelStream()
                .map(CustomerClusteringModel::copy)
                .collect(Collectors.toList());

        clusteringModels.parallelStream().forEach(model -> {
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getBirthYear, model::getBirthYear, model::setBirthYear);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getEducation, model::getEducation, model::setEducation);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getMaritalStatus, model::getMaritalStatus, model::setMaritalStatus);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getIncome, model::getIncome, model::setIncome);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getChildrenAmount, model::getChildrenAmount, model::setChildrenAmount);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getEnrollmentDate, model::getEnrollmentDate, model::setEnrollmentDate);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getRecency, model::getRecency, model::setRecency);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getComplains, model::getComplains, model::setComplains);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getWineAmount, model::getWineAmount, model::setWineAmount);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getFruitsAmount, model::getFruitsAmount, model::setFruitsAmount);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getMeatAmount, model::getMeatAmount, model::setMeatAmount);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getFishAmount, model::getFishAmount, model::setFishAmount);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getSweetAmount, model::getSweetAmount, model::setSweetAmount);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getGoldAmount, model::getGoldAmount, model::setGoldAmount);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getDiscountPurchasesAmount, model::getDiscountPurchasesAmount, model::setDiscountPurchasesAmount);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getAcceptedCampaignsAmount, model::getAcceptedCampaignsAmount, model::setAcceptedCampaignsAmount);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getWebPurchasesAmount, model::getWebPurchasesAmount, model::setWebPurchasesAmount);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getCatalogPurchasesAmount, model::getCatalogPurchasesAmount, model::setCatalogPurchasesAmount);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getStorePurchasesAmount, model::getStorePurchasesAmount, model::setStorePurchasesAmount);
            normalizeField(clusteringModelsCopy, CustomerClusteringModel::getWebsiteVisitsAmount, model::getWebsiteVisitsAmount, model::setWebsiteVisitsAmount);
        });
    }

    private static void normalizeField(List<CustomerClusteringModel> clusteringModels,
                                       ToDoubleFunction<CustomerClusteringModel> getterReference,
                                       DoubleGetter getter, DoubleSetter setter) {
        double minValue = findMinFieldValue(clusteringModels, getterReference);
        double maxValue = findMaxFieldValue(clusteringModels, getterReference);
        setter.setValue( calculateNormalizedValue( getter.getValue(), minValue, maxValue ) );
    }

    private static double findMaxFieldValue(List<CustomerClusteringModel> clusteringModels,
                                     ToDoubleFunction<CustomerClusteringModel> fieldGetter) {
        return clusteringModels.parallelStream()
                .mapToDouble(fieldGetter)
                .max().orElseThrow(SelectionIsEmptyException::new);
    }

    private static double findMinFieldValue(List<CustomerClusteringModel> clusteringModels,
                                     ToDoubleFunction<CustomerClusteringModel> fieldGetter) {
        return clusteringModels.parallelStream()
                .mapToDouble(fieldGetter)
                .min().orElseThrow(SelectionIsEmptyException::new);
    }

    private static double calculateNormalizedValue(double baseValue, double min, double max) {
        if (max == min) {
            return CONST_VALUE;
        } else {
            return (baseValue - min) / (max - min);
        }
    }
}
