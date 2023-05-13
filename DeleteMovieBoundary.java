package boundary;

import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import controller.DeleteMovieController;

public class DeleteMovieBoundary {
	private final DeleteMovieController dmc = new DeleteMovieController();
	
	public void constructBoundary(JDialog dialog, int id){		
		try {
			JOptionPane.showMessageDialog(dialog, dmc.executeTask(id));
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);

		}
	}
}
