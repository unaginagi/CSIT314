package boundary;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.GetMovieListController;
import entity.Movie;

public class GetMovieListBoundary{
	private final GetMovieListController gmlc = new GetMovieListController();
	private int movieID;
	
	public int getMovieID() {
		return movieID;
	}
	
	public JPanel constructBoundary(JDialog dialog){
		JPanel panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets= new Insets(15, 15, 15, 15);
		
		Map<JButton, Integer> ids = new HashMap<>();
		
		try {
			for(Movie m: gmlc.executeTask()) {
				JButton movieButton = new JButton(m.getName());
				movieButton.setFont(new Font("Arial", Font.BOLD, 25));
				movieButton.setOpaque(false);
				movieButton.setContentAreaFilled(false);
				movieButton.setBorderPainted(false);
				
				ids.put(movieButton, m.getId());
				
				movieButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						JButton button = (JButton) (evt.getSource());
						movieID = ids.get(button);
						dialog.setVisible(false);
					}
				});
				
				gbc.gridy++;
				panel.add(movieButton, gbc);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
						
		} catch (Exception e) {
			JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);

		}
		
		return panel;
	}
}
