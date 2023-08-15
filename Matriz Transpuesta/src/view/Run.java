package view;

import javax.swing.SwingUtilities;

import controller.MatrixController;
import model.MatrixModel;

public class Run {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MatrixModel model = new MatrixModel();
                MatrixController controller = new MatrixController(model, null); 
                MatrixView view = new MatrixView(controller);
                controller.setView(view); 
                view.setVisible(true);
            }
        });
    }
}
