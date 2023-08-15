package model;

import java.util.ArrayList;

public class MatrixModel {
    private ArrayList<ArrayList<Integer>> matrix;

    public MatrixModel() {
        matrix = new ArrayList<>();
    }

    public void setMatrix(ArrayList<ArrayList<Integer>> matrix) {
        this.matrix = matrix;
    }

    public ArrayList<ArrayList<Integer>> getMatrix() {
        return matrix;
    }
}
