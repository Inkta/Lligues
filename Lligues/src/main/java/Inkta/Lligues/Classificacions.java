package Inkta.Lligues;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JScrollPane;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class Classificacions extends JFrame {

	private JPanel contentPane;
	public Lliga actual = new Lliga();
	DefaultTableModel dtm = new DefaultTableModel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Classificacions frame = new Classificacions();
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
	public Classificacions() {
		setTitle("Classificacions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Nova Lliga");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CrearLliga frame = null;
				try {
					frame = new CrearLliga();
					frame.setLliga(actual);
					frame.setTableModel(dtm);
					frame.setTable(table);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Obrir Lliga");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				file.showOpenDialog(null);
				if (file.getSelectedFile() != null) {
					File xml = file.getSelectedFile();
					if (validarXML(xml)) {
						ObrirXML obre = new ObrirXML();
						obre.setLliga(actual);
						obre.setTable(table);
						obre.setTableModel(dtm);
						obre.llegueix(xml);
					} else {
						JOptionPane.showMessageDialog(contentPane,
								"Error, l'arxiu no es valid",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmDesarLliga = new JMenuItem("Desar Lliga");
		mntmDesarLliga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!actual.getNom().equals("Nocreada")) {
					CrearXML guarda = new CrearXML(actual);
					guarda.crea();
				} else {
					JOptionPane.showMessageDialog(contentPane,
							"Error, Has de crear una lliga abans de guardar",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnNewMenu.add(mntmDesarLliga);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Sortir");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenu mnNewMenu_1 = new JMenu("Partits");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Entrar nova jornada");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (actual.getNom().equals("Nocreada")) {
					JOptionPane
							.showMessageDialog(
									contentPane,
									"Error, Has de crear la lliga abans de modificar partits",
									"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						PartitNou frame = new PartitNou();
						frame.setTable(table);
						frame.setTableModel(dtm);
						frame.AfegirBox(actual);
						frame.setVisible(true);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[206px,grow][186px][34px]",
				"[15px,grow][145px][25px][]"));

		JLabel lblNewLabel = new JLabel("Lliga");
		contentPane.add(lblNewLabel, "cell 2 0,alignx left,aligny top");
		String[] noms = { "Nom", "Guanyats", "Perduts", "Empatats", "Puntuacio" };
		Columnes(noms);

		JButton btnNewButton = new JButton("Entrar una nova Jornada");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (actual.getNom().equals("Nocreada")) {
					JOptionPane
							.showMessageDialog(
									contentPane,
									"Error, Has de crear la lliga abans de modificar partits",
									"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						PartitNou frame = new PartitNou();
						frame.setTable(table);
						frame.setTableModel(dtm);
						frame.AfegirBox(actual);
						frame.setVisible(true);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1 2 1,grow");

		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.add(btnNewButton, "cell 0 3,alignx left,aligny top");
	}

	public void Columnes(String[] noms) {
		for (int i = 0; i < noms.length; i++) {
			dtm.addColumn(noms[i]);
		}
	}

	public static boolean validarXML(File xml) {
		try {
			SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File("validar.xsd"));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xml));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
