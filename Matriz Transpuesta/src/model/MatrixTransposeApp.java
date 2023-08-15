package model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MatrixTransposeApp extends JFrame {
    private JTextField sizeField;
    private JPanel inputPanel;
    private JPanel resultPanel;

    public MatrixTransposeApp() {
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
                createInputFields(size);
                inputPanel.revalidate();
                inputPanel.repaint();
            }
        });

        add(controlPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);
    }

    private void createInputFields(int size) {
        inputPanel.removeAll();
        inputPanel.setLayout(new GridLayout(size, size));
        
        ArrayList<ArrayList<JTextField>> inputFields = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            ArrayList<JTextField> rowFields = new ArrayList<>();
            JPanel rowPanel = new JPanel(new FlowLayout());
            for (int j = 0; j < size; j++) {
                JTextField textField = new JTextField(5);
                rowFields.add(textField);
                inputPanel.add(textField);
            }
            inputFields.add(rowFields);
        }

        JButton transposeButton = new JButton("Transpose Matrix");
        transposeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ArrayList<Integer>> matrix = readInputMatrix(size, inputFields);
                ArrayList<ArrayList<Integer>> transposedMatrix = transposeMatrix(matrix);
                showResultMatrix(matrix, transposedMatrix);
            }
        });

        resultPanel.removeAll();
        resultPanel.add(transposeButton);

        revalidate();
        repaint();
    }

    private ArrayList<ArrayList<Integer>> readInputMatrix(int size, ArrayList<ArrayList<JTextField>> fields) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        for (ArrayList<JTextField> rowFields : fields) {
            ArrayList<Integer> row = new ArrayList<>();
            for (JTextField field : rowFields) {
                row.add(Integer.parseInt(field.getText()));
            }
            matrix.add(row);
        }

        return matrix;
    }

    private ArrayList<ArrayList<Integer>> transposeMatrix(ArrayList<ArrayList<Integer>> matrix) {
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

    private void showResultMatrix(ArrayList<ArrayList<Integer>> originalMatrix,
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MatrixTransposeApp().setVisible(true);
            }
        });
    }
}