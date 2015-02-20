package Inkta.Lligues;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PartitNou extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	JTable table = null;
	Lliga actual = null;
	public JComboBox comboBox = null;
	public JComboBox comboBox_1 = null;
	DefaultTableModel dtm = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PartitNou frame = new PartitNou();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PartitNou() {
		setTitle("Partit Nou");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][][][grow]", "[][][][][][]"));
		textField = new JTextField();
		textField.setText("0");
		contentPane.add(textField, "cell 4 1,growx");
		textField.setColumns(10);



		textField_1 = new JTextField();
		textField_1.setText("0");

		contentPane.add(textField_1, "cell 4 2,growx");
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() == comboBox_1.getSelectedIndex()) {
					JOptionPane.showMessageDialog(contentPane,
							"Error, No pots seleccionar el mateix equip",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						int n1 = Integer.parseInt(textField.getText());
						int n2 = Integer.parseInt(textField_1.getText());

						if (n1 > n2) {
							actual.getEquips().get(comboBox.getSelectedIndex()).setPartitsGuanyats(1);
							actual.getEquips().get(comboBox.getSelectedIndex()).setPuntuacio(3);
							actual.getEquips().get(comboBox_1.getSelectedIndex()).setPartitsPerduts(1);
							dtm.setValueAt(actual.getEquips().get(comboBox.getSelectedIndex()).getPartitsGuanyats(),
									comboBox.getSelectedIndex(),
									1);

							dtm.setValueAt(actual.getEquips().get(comboBox.getSelectedIndex()).getPuntuacio(),
									comboBox.getSelectedIndex(),
									4);

							dtm.setValueAt(actual.getEquips().get(comboBox_1.getSelectedIndex()).getPartitsPerduts(),
									comboBox_1.getSelectedIndex(),
									2);
						} else if (n1 == n2) {
							actual.getEquips().get(comboBox.getSelectedIndex()).setPartitsEmpatats(1);
							actual.getEquips().get(comboBox_1.getSelectedIndex()).setPartitsEmpatats(1);
							actual.getEquips().get(comboBox.getSelectedIndex()).setPuntuacio(1);
							actual.getEquips().get(comboBox_1.getSelectedIndex()).setPuntuacio(1);

							dtm.setValueAt(actual.getEquips().get(comboBox.getSelectedIndex()).getPartitsEmpatats(),
									comboBox.getSelectedIndex(),
									3);

							dtm.setValueAt(actual.getEquips().get(comboBox_1.getSelectedIndex()).getPartitsEmpatats(),
									comboBox_1.getSelectedIndex(),
									3);

							dtm.setValueAt(actual.getEquips().get(comboBox_1.getSelectedIndex()).getPuntuacio(),
									comboBox_1.getSelectedIndex(),
									4);

							dtm.setValueAt(actual.getEquips().get(comboBox.getSelectedIndex()).getPuntuacio(),
									comboBox.getSelectedIndex(),
									4);

						} else {
							actual.getEquips().get(comboBox_1.getSelectedIndex()).setPartitsGuanyats(1);
							actual.getEquips().get(comboBox_1.getSelectedIndex()).setPuntuacio(3);
							actual.getEquips().get(comboBox.getSelectedIndex()).setPartitsPerduts(1);

							dtm.setValueAt(actual.getEquips().get(comboBox_1.getSelectedIndex()).getPartitsGuanyats(),
									comboBox_1.getSelectedIndex(),
									1);

							dtm.setValueAt(actual.getEquips().get(comboBox_1.getSelectedIndex()).getPuntuacio(),
									comboBox_1.getSelectedIndex(),
									4);

							dtm.setValueAt(actual.getEquips().get(comboBox.getSelectedIndex()).getPartitsPerduts(),
									comboBox.getSelectedIndex(),
									2);
						}

						exitActionPerformed(e);

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(contentPane,
								"Error, s'han de introduir valors numerics",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		contentPane.add(btnNewButton, "cell 1 5");

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				exitActionPerformed(evt);
			}
		});

		contentPane.add(btnNewButton_1, "cell 4 5");
	}

	private void exitActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void setTableModel(DefaultTableModel dtm) {
		this.dtm = dtm;
	}

	public void AfegirBox(Lliga actual) {
		this.actual = actual;
		String[] noms = new String[actual.getEquips().size()];

		for (int i=0; i < noms.length; i++) {
			noms[i] = actual.getEquips().get(i).getNom();
		}
		comboBox = new JComboBox(noms);
		contentPane.add(comboBox, "cell 1 1,growx");

		comboBox_1 = new JComboBox(noms);
		contentPane.add(comboBox_1, "cell 1 2,growx");
	}

}


