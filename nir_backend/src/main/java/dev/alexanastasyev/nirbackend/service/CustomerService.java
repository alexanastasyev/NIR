package dev.alexanastasyev.nirbackend.service;

import dev.alexanastasyev.nirbackend.model.CustomerCSVModel;
import dev.alexanastasyev.nirbackend.model.CustomerClusteringModel;
import dev.alexanastasyev.nirbackend.repository.CustomerRepository;
import dev.alexanastasyev.nirbackend.util.clustering.CustomerCSVToClusteringConverter;
import dev.alexanastasyev.nirbackend.util.clustering.GraphClusteringUtil;
import dev.alexanastasyev.nirbackend.util.graph.Graph;
import dev.alexanastasyev.nirbackend.util.graph.Vertex;
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
    private Graph<CustomerClusteringModel> graph;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerClusteringModel> getCustomerClusteringModels() throws IOException {
        return getConvertedCustomerClusteringModels();
    }

    public List<Set<Long>> getCustomerIdsClusters(double level) throws IOException {
        Graph<CustomerClusteringModel> graph = getCustomerClusteringModelsGraph();

        GraphClusteringUtil<CustomerClusteringModel> graphUtil = new GraphClusteringUtil<>(graph);
        graphUtil.removeLongEdges(level);
        List<Set<CustomerClusteringModel>> modelClusters = graphUtil.getClustersFromGraph();

        List<Set<Long>> idClusters = new ArrayList<>();
        modelClusters.forEach(cluster -> idClusters.add(cluster.stream()
                .map(CustomerClusteringModel::getId)
                .collect(Collectors.toSet())
        ));

        return idClusters;
    }

    private Graph<CustomerClusteringModel> getCustomerClusteringModelsGraph() throws IOException {
        if (this.graph == null) {
            Graph<CustomerClusteringModel> graph = new Graph<>();
            getConvertedCustomerClusteringModels().forEach(graph::addVertex);

            for (int i = 0; i < graph.getVertices().size() - 1; i++) {
                for (int j = i + 1; j < graph.getVertices().size(); j++) {
                    Vertex<CustomerClusteringModel> vertexFrom = graph.getVertices().get(i);
                    Vertex<CustomerClusteringModel> vertexTo = graph.getVertices().get(j);
                    double distance = calculateDistance(vertexFrom.getValue(), vertexTo.getValue());
                    graph.addEdge(vertexFrom, vertexTo, distance);
                }
            }
            this.graph = graph;
        }

        // TODO: return clone of this.graph
        return this.graph;
    }

    private List<CustomerClusteringModel> getConvertedCustomerClusteringModels() throws IOException {
        List<CustomerCSVModel> csvModels = customerRepository.getCustomerCsvModels();
        return CustomerCSVToClusteringConverter.convertCSVModelsToClustering(csvModels);
    }

    private double calculateDistance(CustomerClusteringModel model1, CustomerClusteringModel model2) {
        return Math.sqrt(sum(
                sqrDif(model1.getBirthYear(), model2.getBirthYear()),
                sqrDif(model1.getEducation(), model2.getEducation()),
                sqrDif(model1.getMaritalStatus(), model2.getMaritalStatus()),
                sqrDif(model1.getIncome(), model2.getIncome()),
                sqrDif(model1.getChildrenAmount(), model2.getChildrenAmount()),
                sqrDif(model1.getEnrollmentDate(), model2.getEnrollmentDate()),
                sqrDif(model1.getRecency(), model2.getRecency()),
                sqrDif(model1.getComplains(), model2.getComplains()),
                sqrDif(model1.getWineAmount(), model2.getWineAmount()),
                sqrDif(model1.getFishAmount(), model2.getFishAmount()),
                sqrDif(model1.getMeatAmount(), model2.getMeatAmount()),
                sqrDif(model1.getFishAmount(), model2.getFishAmount()),
                sqrDif(model1.getSweetAmount(), model2.getSweetAmount()),
                sqrDif(model1.getGoldAmount(), model2.getGoldAmount()),
                sqrDif(model1.getDiscountPurchasesAmount(), model2.getDiscountPurchasesAmount()),
                sqrDif(model1.getAcceptedCampaignsAmount(), model2.getAcceptedCampaignsAmount()),
                sqrDif(model1.getWebPurchasesAmount(), model2.getWebPurchasesAmount()),
                sqrDif(model1.getCatalogPurchasesAmount(), model2.getCatalogPurchasesAmount()),
                sqrDif(model1.getStorePurchasesAmount(), model2.getStorePurchasesAmount()),
                sqrDif(model1.getWebsiteVisitsAmount(), model2.getWebsiteVisitsAmount())
        ));
    }

    private double sum(double... values) {
        return Arrays.stream(values).sum();
    }

    private double sqrDif(double value1, double value2) {
        return Math.pow(value1 - value2, 2);
    }

}
