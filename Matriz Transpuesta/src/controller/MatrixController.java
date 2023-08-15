package controller;

import java.util.ArrayList;

import model.MatrixModel;
import view.MatrixView;

public class MatrixController {
    private MatrixModel model;
    private MatrixView view;

    public MatrixController(MatrixModel model, MatrixView view) {
        this.model = model;
        this.view = view;
    }

    public void generateMatrix(int size) {
        view.createInputFields(size);
    }
    
    public void setView(MatrixView view) {
        this.view = view;
    }

    public void transposeMatrix(ArrayList<ArrayList<Integer>> matrix) {
        ArrayList<ArrayList<Integer>> transposedMatrix = transpose(matrix);
        model.setMatrix(matrix);
        view.showResultMatrix(matrix, transposedMatrix);
    }

    private ArrayList<ArrayList<Integer>> transpose(ArrayList<ArrayList<Integer>> matrix) {
        int numRows = matrix.size();
        int numCols = matrix.get(0).size();

        ArrayList<ArrayList<Integer>> transposedMatrix = new ArrayList<>();

        for (int col = 0; col < numCols; col++) {
            ArrayList<Integer> newRow = new ArrayList<>();
            for (int row = 0; row < numRows; row++) {
                newRow.add(matrix.get(row).get(col));
            }
            transposedMatrix.add(newRow);
        }

        return transposedMatrix;
    }
}
