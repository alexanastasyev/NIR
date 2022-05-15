package dev.alexanastasyev.nirbackend;

import dev.alexanastasyev.nirbackend.util.clustering.AdjustmentMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class AdjustmentMatrixTest {


    @Test
    public void testSize() {
        AdjustmentMatrix adjustmentMatrix = new AdjustmentMatrix(10);
        double[][] matrix = adjustmentMatrix.getMatrix();
        Assertions.assertEquals(matrix.length, 10);
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(10, matrix.length);
        }
    }

    @Test
    public void testValues() {
        AdjustmentMatrix adjustmentMatrix = new AdjustmentMatrix(10);
        double[][] matrix = adjustmentMatrix.getMatrix();
        for (double[] row : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                Assertions.assertEquals(-1, row[j]);
            }
        }

        adjustmentMatrix.addDistance(1, 2, 5);

        Assertions.assertEquals(5, matrix[1][2]);
        Assertions.assertEquals(5, matrix[2][1]);
    }

    @Test
    public void testClusters1() {
        AdjustmentMatrix adjustmentMatrix = new AdjustmentMatrix(7);

        adjustmentMatrix.addDistance(0, 2, 1);
        adjustmentMatrix.addDistance(0, 3, 1);
        adjustmentMatrix.addDistance(1, 5, 1);
        adjustmentMatrix.addDistance(2, 4, 1);
        adjustmentMatrix.addDistance(3, 4, 1);

        List<Set<Integer>> clusters = adjustmentMatrix.getClusters();
        Assertions.assertEquals(3, clusters.size());
        Assertions.assertEquals(4, clusters.get(0).size());
        Assertions.assertEquals(2, clusters.get(1).size());
        Assertions.assertEquals(1, clusters.get(2).size());
    }

    @Test
    public void testClusters2() {
        AdjustmentMatrix adjustmentMatrix = new AdjustmentMatrix(7);

        adjustmentMatrix.addDistance(0, 1, 1);
        adjustmentMatrix.addDistance(0, 2, 1);

        adjustmentMatrix.addDistance(3, 4, 1);
        adjustmentMatrix.addDistance(3, 5, 1);
        adjustmentMatrix.addDistance(3, 6, 1);
        adjustmentMatrix.addDistance(4, 5, 1);
        adjustmentMatrix.addDistance(4, 6, 1);
        adjustmentMatrix.addDistance(5, 6, 1);

        List<Set<Integer>> clusters = adjustmentMatrix.getClusters();
        Assertions.assertEquals(2, clusters.size());
        Assertions.assertEquals(3, clusters.get(0).size());
        Assertions.assertEquals(4, clusters.get(1).size());
    }

    @Test
    public void testClustersNoConnections() {
        AdjustmentMatrix adjustmentMatrix = new AdjustmentMatrix(100);
        List<Set<Integer>> clusters = adjustmentMatrix.getClusters();
        Assertions.assertEquals(100, clusters.size());
    }

    @Test
    public void testClustersAllConnected() {
        AdjustmentMatrix adjustmentMatrix = new AdjustmentMatrix(5);

        adjustmentMatrix.addDistance(0, 1, 1);
        adjustmentMatrix.addDistance(1, 2, 1);
        adjustmentMatrix.addDistance(2, 4, 1);
        adjustmentMatrix.addDistance(4, 3, 1);

        List<Set<Integer>> clusters = adjustmentMatrix.getClusters();
        Assertions.assertEquals(1, clusters.size());
    }

    @Test
    public void testMatrixCopy() {
        AdjustmentMatrix adjustmentMatrix1 = new AdjustmentMatrix(5);
        AdjustmentMatrix adjustmentMatrix2 = adjustmentMatrix1.copy();

        adjustmentMatrix1.addDistance(1, 2, 10);
        Assertions.assertEquals(-1, adjustmentMatrix2.getMatrix()[1][2]);
    }

    @Test
    public void testRemoveEdges() {
        AdjustmentMatrix adjustmentMatrix = new AdjustmentMatrix(5);

        adjustmentMatrix.addDistance(0, 1, 10);
        adjustmentMatrix.addDistance(1, 2, 20);
        adjustmentMatrix.addDistance(2, 4, 10);
        adjustmentMatrix.addDistance(4, 3, 20);

        adjustmentMatrix.removeEdges(15);

        double[][] matrix = adjustmentMatrix.getMatrix();

        int counter = 0;
        for (double[] row : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (row[j] > 0) {
                    counter++;
                }
            }
        }

        Assertions.assertEquals(4, counter);
    }

}
