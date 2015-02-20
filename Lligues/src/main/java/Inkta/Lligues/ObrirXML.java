package Inkta.Lligues;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ObrirXML {
	Lliga actual = null;
	DefaultTableModel dtm = new DefaultTableModel();
	JTable table = null;

	public void llegueix(File xml) {

		try {

			File fXmlFile = xml;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			// Nom de la lliga
			actual.setNom(doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("equip");

			ArrayList<Equip> equips = new ArrayList<Equip>();
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Equip equip = new Equip(eElement
							.getElementsByTagName("nom").item(0)
							.getTextContent());

					equip.setPartitsEmpatats(Integer.parseInt(eElement
							.getElementsByTagName("empatats").item(0)
							.getTextContent()));
					equip.setPartitsGuanyats(Integer.parseInt(eElement
							.getElementsByTagName("guanyats").item(0)
							.getTextContent()));
					equip.setPartitsPerduts(Integer.parseInt(eElement
							.getElementsByTagName("perduts").item(0)
							.getTextContent()));
					equip.setPuntuacio(Integer.parseInt(eElement
							.getElementsByTagName("punts").item(0)
							.getTextContent()));
					equips.add(equip);
				}
			}
			actual.setEquips(equips);
			ActualitzaTaula();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setLliga(Lliga actual) {
		this.actual = actual;
	}

	public void setTableModel(DefaultTableModel dtm) {
		this.dtm = dtm;
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

	public void setTable(JTable table) {
		this.table = table;
	}

}
