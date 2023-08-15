package view;

import javax.swing.*;

import controller.MatrixController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MatrixView extends JFrame {
    private JTextField sizeField;
    private ArrayList<ArrayList<JTextField>> inputFields;
    private JPanel inputPanel;
    private JPanel resultPanel;
    private MatrixController controller;
    
    public void setController(MatrixController controller) {
        this.controller = controller;
    }

    public MatrixView(MatrixController controller) {
        this.controller = controller;

        setTitle("Matrix Transpose App");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel(new FlowLayout());
        JLabel sizeLabel = new JLabel("Matrix Size:");
        sizeField = new JTextField(5);
        JButton generateButton = new JButton("Generate Matrix");

        controlPanel.add(sizeLabel);
        controlPanel.add(sizeField);
        controlPanel.add(generateButton);

        inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(2, 1));

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(sizeField.getText());
                controller.generateMatrix(size);
            }
        });

        add(controlPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);
    }

    public void createInputFields(int size) {
        inputPanel.removeAll();
        
        inputFields = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            ArrayList<JTextField> rowFields = new ArrayList<>();
            JPanel rowPanel = new JPanel(new FlowLayout());

            for (int j = 0; j < size; j++) {
                JTextField textField = new JTextField(5);
                rowFields.add(textField);
                rowPanel.add(textField);
            }

            inputFields.add(rowFields);
            inputPanel.add(rowPanel);
        }

        JButton transposeButton = new JButton("Transpose Matrix");
        transposeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<Integer>> matrix = readInputMatrix(size);
                controller.transposeMatrix(matrix);
            }
        });

        resultPanel.removeAll();
        resultPanel.add(transposeButton);

        revalidate();
        repaint();
    }

    private ArrayList<ArrayList<Integer>> readInputMatrix(int size) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        for (ArrayList<JTextField> rowFields : inputFields) {
            ArrayList<Integer> row = new ArrayList<>();
            for (JTextField field : rowFields) {
                row.add(Integer.parseInt(field.getText()));
            }
            matrix.add(row);
        }

        return matrix;
    }

    public void showResultMatrix(ArrayList<ArrayList<Integer>> originalMatrix,
                                  ArrayList<ArrayList<Integer>> transposedMatrix) {
        JPanel resultMatrixPanel = new JPanel(new GridLayout(1, 2));

        JPanel originalPanel = createMatrixPanel("Original Matrix", originalMatrix);
        JPanel transposedPanel = createMatrixPanel("Transposed Matrix", transposedMatrix);

        resultMatrixPanel.add(originalPanel);
        resultMatrixPanel.add(transposedPanel);

        resultPanel.removeAll();
        resultPanel.add(resultMatrixPanel);

        revalidate();
        repaint();
    }

    private JPanel createMatrixPanel(String title, ArrayList<ArrayList<Integer>> matrix) {
        JPanel matrixPanel = new JPanel(new GridLayout(matrix.size(), matrix.get(0).size()));
        matrixPanel.setBorder(BorderFactory.createTitledBorder(title));

        for (ArrayList<Integer> row : matrix) {
            for (Integer value : row) {
                JTextField textField = new JTextField(value.toString());
                textField.setEditable(false);
                matrixPanel.add(textField);
            }
        }

        return matrixPanel;
    }
}
