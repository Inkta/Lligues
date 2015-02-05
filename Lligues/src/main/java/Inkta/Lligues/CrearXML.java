
package Inkta.Lligues;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CrearXML {

	static Lliga nova;


	public CrearXML(Lliga a) {
		this.nova = a;
	}


	public static void crea() {

	try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("Lliga");
		doc.appendChild(rootElement);
		Attr attr = doc.createAttribute("id");

		attr.setValue(nova.getNom());
		rootElement.setAttributeNode(attr);

		Element equips = doc.createElement("equips");
		rootElement.appendChild(equips);

		for (Equip e: nova.getEquips()) {
			Element equip = doc.createElement("equip");
			equips.appendChild(equip);

			Element nom = doc.createElement("nom");
			nom.appendChild(doc.createTextNode(e.getNom()));
			System.out.println(e.getNom());
			equip.appendChild(nom);

			Element guanyats = doc.createElement("guanyats");
			guanyats.appendChild(doc.createTextNode(e.getPartitsGuanyats()+""));
			equip.appendChild(guanyats);
			System.out.println(e.partitsEmpatats);
			Element empatats = doc.createElement("empatats");
			empatats.appendChild(doc.createTextNode(e.getPartitsEmpatats()+""));
			equip.appendChild(empatats);

			Element perduts = doc.createElement("perduts");
			perduts.appendChild(doc.createTextNode(e.getPartitsPerduts()+""));
			equip.appendChild(perduts);

			Element punts = doc.createElement("punts");
			punts.appendChild(doc.createTextNode(e.getPuntuacio()+""));
			equip.appendChild(punts);

		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(nova.getNom() + ".xml"));


		transformer.transform(source, result);

	} catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	} catch (TransformerException tfe) {
		tfe.printStackTrace();
	}
	}
}
