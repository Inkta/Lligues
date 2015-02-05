package Inkta.Lligues;

import java.util.ArrayList;

public class Lliga {
	String nom = "";
	ArrayList<Equip> equips;

	public Lliga() {
	}

	public String getNom() {
		if (nom.equals("")) {
			return "Nocreada";
		}
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Equip> getEquips() {
		return equips;
	}

	public void setEquips(ArrayList<Equip> equips) {
		this.equips = equips;
	}

}
