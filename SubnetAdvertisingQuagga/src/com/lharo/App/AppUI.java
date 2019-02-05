package com.lharo.App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppUI {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppUI window = new AppUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 141, 414, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText((String) comboBox.getSelectedItem());
			}
		});
		comboBox.setBounds(10, 81, 134, 27);
		frame.getContentPane().add(comboBox);		
		
		JLabel lblAnnouncingNetworks = new JLabel("Advertised Networks");
		lblAnnouncingNetworks.setBounds(10, 56, 178, 14);
		frame.getContentPane().add(lblAnnouncingNetworks);
		
		JLabel lblDroppedNetworks = new JLabel("Dropped Networks");
		lblDroppedNetworks.setBounds(306, 56, 89, 14);
		frame.getContentPane().add(lblDroppedNetworks);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText((String) comboBox.getSelectedItem());
			}
		});
		comboBox_1.setBounds(267, 84, 134, 20);
		frame.getContentPane().add(comboBox_1);
		
		JButton button = new JButton("Advertise");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String network = textField.getText();
				System.out.print("The network " + network + " will be advertised\n");
				comboBox.addItem(network);
				com.lharo.App.Principal.advertiseNetwork(true, network);
			}
		});
		button.setBounds(10, 195, 89, 23);
		frame.getContentPane().add(button);
		
		JButton btnStopAdvertising = new JButton("Stop Advertising");
		btnStopAdvertising.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String network = textField.getText();
				System.out.print("The network " + network + " will be advertised\n");
				comboBox_1.addItem(network);
				com.lharo.App.Principal.advertiseNetwork(false, network);			
			}
		});
		btnStopAdvertising.setBounds(278, 195, 117, 23);
		frame.getContentPane().add(btnStopAdvertising);		

	}
}
