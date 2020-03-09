//Reginald Eskridge
package project2pkg;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import project1.Patient;
import project1.PatientCollection;
import java.util.ArrayList;


public class MainPanel extends JPanel {
	private JPanel infoPanel; //Panel where information is displayed
	private JPanel buttonPanel; //Panel that holds the buttons
	private JButton searchButton; //Button that when clicked will display a dropdown list for patient selection (by id)
	private JComboBox comboBox; //Dropdown list
	private PatientCollection myPats;
	private JLabel patLabel; //Label displaying patient information
	private JTextArea viewAllPats; //Text area showing all patients
	private JScrollPane scrollPane; //Scroll pane for the text area
	private JComboBox patUpdateCombo; //ComboBox to choose a specific patient to update
	private final ButtonGroup buttonGroup = new ButtonGroup(); 
	private JRadioButton rdbtnNewRadioButton; //Radio button for CR
	private JRadioButton rdbtnNewRadioButton_1; //Radio button for DP
	private JComboBox removePatCombo; //ComboBox to remove a patient based on id
	
	public MainPanel() {
			super(new BorderLayout());
			myPats = new PatientCollection();
			
		this.setSize(800, 500);
		this.setBackground(Color.LIGHT_GRAY);
		
			buttonPanel = new JPanel();
			buttonPanel.setPreferredSize(new Dimension(200,500));
			buttonPanel.setBackground(Color.GRAY);
			
			infoPanel = new JPanel();
			infoPanel.setPreferredSize(new Dimension(600,500));
			infoPanel.setBackground(Color.DARK_GRAY);
			infoPanel.setLayout(null);
			
			searchButton = new JButton("Patient Search");
			searchButton.setBounds(48, 5, 103, 23);
			searchButton.addActionListener(new ShowComboBox());
			buttonPanel.setLayout(null);
			buttonPanel.add(searchButton);
			

			comboBox = new JComboBox();
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					Patient pat = myPats.getPatient((String)comboBox.getSelectedItem());
					patLabel = new JLabel();
					patLabel.setText(pat.toString());
					infoPanel.add(patLabel);
				}
			});
			comboBox.isEditable();
			comboBox.setVisible(false);
			ArrayList<String> ids = myPats.getIds();
			comboBox.setModel(new DefaultComboBoxModel(ids.toArray()));
			comboBox.setBounds(32, 11, 55, 22);
			
			
			infoPanel.add(comboBox);
			
		this.add(buttonPanel, BorderLayout.WEST); //adds panel to left side of MainPanel
		
		JButton btnUpdatePatient = new JButton("Update Patient");
		btnUpdatePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patUpdateCombo.setVisible(true);
			}
		});
		btnUpdatePatient.setBounds(48, 80, 103, 23);
		buttonPanel.add(btnUpdatePatient);
		
		JButton btnViewPatients = new JButton("View Patients");
		btnViewPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewAllPats.setVisible(true);
				scrollPane.setVisible(true);
			}
		});
		btnViewPatients.setBounds(48, 177, 103, 23);
		buttonPanel.add(btnViewPatients);
		
		JButton btnRemovePatient = new JButton("Remove Patient");
		btnRemovePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePatCombo.setVisible(true);
			}
		});
		btnRemovePatient.setBounds(48, 282, 103, 23);
		buttonPanel.add(btnRemovePatient);
		this.add(infoPanel, BorderLayout.EAST);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(277, 243, 218, 203);
		scrollPane.setVisible(false);
		infoPanel.add(scrollPane);
		
		viewAllPats = new JTextArea();
		scrollPane.setViewportView(viewAllPats);
		viewAllPats.setText(myPats.toString());
		viewAllPats.setVisible(false);
		
		patUpdateCombo = new JComboBox();
		patUpdateCombo.setVisible(false);
		patUpdateCombo.setModel(new DefaultComboBoxModel(ids.toArray()));
		patUpdateCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Patient pat = myPats.getPatient((String)patUpdateCombo.getSelectedItem());
				patLabel = new JLabel();
				patLabel.setText(pat.toString());
				if(rdbtnNewRadioButton.isSelected()) {
					pat.setResult(rdbtnNewRadioButton.getText());
				}
				else {
					pat.setResult(rdbtnNewRadioButton_1.getText());
				}
			}
		});
		patUpdateCombo.setBounds(32, 73, 55, 22);
		infoPanel.add(patUpdateCombo);	
				
		rdbtnNewRadioButton = new JRadioButton("CR");
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(125, 73, 109, 23);
		infoPanel.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("DP");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(125, 116, 109, 23);
		infoPanel.add(rdbtnNewRadioButton_1);
		
		removePatCombo = new JComboBox();
		removePatCombo.setBounds(32, 280, 55, 22);
		removePatCombo.setVisible(false);
		removePatCombo.setModel(new DefaultComboBoxModel(ids.toArray()));
		removePatCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Patient pat = myPats.getPatient((String)removePatCombo.getSelectedItem());
				myPats.removePatient(pat.getId());
			}
		});
		infoPanel.add(removePatCombo);
		
	}
	
	private class ShowComboBox implements ActionListener {
		public void actionPerformed(ActionEvent e) { //When button is clicked dropdown list 
			comboBox.setVisible(true); //will become visible
		}
		
	}
	
	
	public void doClose() {
		myPats.writeFile("./textwrite.csv"); //writes to text file when user closes the jframe
	}
}
