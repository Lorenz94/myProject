package net.thumbtack.school.old.matrix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MatrixNonSimilarRows {

    private int[][] matrix;

    public MatrixNonSimilarRows(int[][] matrix) {
        this.matrix = matrix;
    }

    public Set<int[]> getNonSimilarRows() {
        Map<Set<Integer>,int[]> map = new HashMap<>();
        for (int[] ints : matrix) {
            map.putIfAbsent(transformArrayToSet(ints), ints);
        }

        return new HashSet<>(map.values());
    }

    private Set<Integer> transformArrayToSet(int[] array) {
        Set<Integer> newSet = new HashSet<>();
        for (int i : array) {
            newSet.add(i);
        }
        return newSet;
    }

}
