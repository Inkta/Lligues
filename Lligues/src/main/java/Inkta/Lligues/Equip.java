package Inkta.Lligues;

public class Equip {
	String nom;
	int puntuacio;
	int partitsGuanyats = 0;
	int partitsPerduts = 0;
	int partitsEmpatats = 0;

	public Equip(String a) {
		this.nom = a;
	}

	public String toString() {
		return this.nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPuntuacio() {
		return puntuacio;
	}

	public void setPuntuacio(int puntuacio) {
		this.puntuacio += puntuacio;
	}

	public int getPartitsGuanyats() {
		return partitsGuanyats;
	}

	public void setPartitsGuanyats(int partitsGuanyats) {
		this.partitsGuanyats += partitsGuanyats;
	}

	public int getPartitsPerduts() {
		return partitsPerduts;
	}

	public void setPartitsPerduts(int partitsPerduts) {
		this.partitsPerduts += partitsPerduts;
	}

	public int getPartitsEmpatats() {
		return partitsEmpatats;
	}

	public void setPartitsEmpatats(int partitsEmpatats) {
		this.partitsEmpatats += partitsEmpatats;
	}
}
