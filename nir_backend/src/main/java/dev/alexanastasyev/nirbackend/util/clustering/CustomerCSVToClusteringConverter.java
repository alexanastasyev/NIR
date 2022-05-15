package dev.alexanastasyev.nirbackend.util.clustering;

import dev.alexanastasyev.nirbackend.model.CustomerCSVModel;
import dev.alexanastasyev.nirbackend.model.CustomerClusteringModel;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerCSVToClusteringConverter {

    public static List<CustomerClusteringModel> convertCSVModelsToClustering(List<CustomerCSVModel> csvModels) {
        List<CustomerClusteringModel> clusteringModels = csvModels.parallelStream()
                .filter(CustomerCSVModel::hasNoEmptyFields)
                .map(CustomerClusteringModel::new)
                .limit(500)
                .collect(Collectors.toList());
        CustomerClusteringNormalizer.normalizeCustomerModels(clusteringModels);
        return clusteringModels;
    }
}
