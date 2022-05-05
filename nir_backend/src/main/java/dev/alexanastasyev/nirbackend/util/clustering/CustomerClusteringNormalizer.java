package dev.alexanastasyev.nirbackend.util.clustering;

import dev.alexanastasyev.nirbackend.exception.SelectionIsEmptyException;
import dev.alexanastasyev.nirbackend.model.CustomerClusteringModel;
import dev.alexanastasyev.nirbackend.util.lambda.DoubleGetter;
import dev.alexanastasyev.nirbackend.util.lambda.DoubleSetter;

import java.util.List;
import java.util.function.ToDoubleFunction;

public class CustomerClusteringNormalizer {

    @SuppressWarnings("DuplicatedCode")
    public static void normalizeCustomerModels(List<CustomerClusteringModel> clusteringModels) {
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
            return 0;
        } else {
            return (baseValue - min) / (max - min);
        }
    }
}
