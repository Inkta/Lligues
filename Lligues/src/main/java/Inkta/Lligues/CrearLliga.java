package Inkta.Lligues;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import net.miginfocom.swing.MigLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.ListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CrearLliga extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	DefaultListModel equipList = new DefaultListModel();
	Lliga actual = null;
	int index = 0;
	JTable table = null;
	DefaultTableModel dtm = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearLliga frame = new CrearLliga();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	JList list;

	/**
	 * Create the frame.
	 */
	public CrearLliga() {
		setTitle("Crear Lliga");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow][][grow]",
				"[][][][][][grow][]"));

		JLabel lblNewLabel = new JLabel("Nom de la Lliga");
		contentPane.add(lblNewLabel, "cell 1 1");

		textField = new JTextField();
		contentPane.add(textField, "cell 2 1,growx");
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Equip");
		contentPane.add(lblNewLabel_1, "cell 1 2");

		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 1 3,growx");
		textField_1.setColumns(10);

		list = new JList();

		contentPane.add(list, "cell 1 4 4 2,grow");

		JButton btnNewButton_1 = new JButton("Afegir ^");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equipList.add(index, new Equip(textField_1.getText()));
				index++;
				list.setModel(equipList);
			}

		});
		contentPane.add(btnNewButton_1, "cell 1 6");

		JButton btnNewButton = new JButton("Treure v");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() > -1) {
					equipList.remove(list.getSelectedIndex());
					index--;
					list.setModel(equipList);
				}
			}
		});
		contentPane.add(btnNewButton, "cell 2 6");

		JButton btnNewButton_2 = new JButton("Acabat");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ArrayList<Equip> equips = new ArrayList<Equip>();
				for (int i = 0; i < equipList.size(); i++) {
					equips.add((Equip) equipList.get(i));
				}
				if (equips.size() < 2) {
					JOptionPane.showMessageDialog(
									contentPane,
									"Error, Has de Introduïr almenys 2 equips",
									"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					if (!comprovaNom(equips)) {
						if (textField.getText().length() == 0) {
							JOptionPane
							.showMessageDialog(
									contentPane,
									"Error, El nom de la lliga no pot estar buit",
									"Error", JOptionPane.ERROR_MESSAGE);
						} else {

						actual.setNom(textField.getText().replaceAll(" ", ""));
						actual.setEquips(equips);
						CrearXML guarda = new CrearXML(actual);
						guarda.crea();
						ActualitzaTaula();
						exitActionPerformed(evt);
						}
					} else {
						JOptionPane
								.showMessageDialog(
										contentPane,
										"Error, No pots introduïr 2 o mes equips amb el mateix nom",
										"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		contentPane.add(btnNewButton_2, "cell 4 6");
	}

	public boolean comprovaNom(ArrayList<Equip> equips) {
		boolean trobat = false;
		for (int i = 0; i < equips.size(); i++) {
			for (int a = 0; a < equips.size(); a++) {
				if (equips.get(i).getNom().equals(equips.get(a).getNom())
						&& i != a) {
					System.out.println(equips.get(i) + " " + equips.get(a));
					trobat = true;
				}
			}
		}
		return trobat;
	}

	private void ActualitzaTaula() {
		while (dtm.getRowCount() > 0) {
			dtm.removeRow(0);
		}

		String[] noms = { "Nom", "Guanyats", "Perduts", "Empatats", "Puntuacio" };
		for (Equip e : actual.getEquips()) {
			Object[] dades = new Object[noms.length];
			dades[0] = e.getNom();
			dades[1] = e.getPartitsGuanyats() + "";
			dades[2] = e.getPartitsPerduts() + "";
			dades[3] = e.getPartitsEmpatats() + "";
			dades[4] = e.getPuntuacio() + "";
			dtm.addRow(dades);
		}

		table.setModel(dtm);
	}

	public void setTableModel(DefaultTableModel dtm) {
		this.dtm = dtm;
	}

	public void AfegirLineas() {

	}

	private void exitActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	public Lliga obtenirLliga() {
		return actual;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void setLliga(Lliga actual) {
		this.actual = actual;

	}

}
