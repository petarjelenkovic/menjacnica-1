package menjacnica;

import java.util.LinkedList;

public class SOVratiKursnuListu {
	
	private static LinkedList<Valuta> kursnaLista = new LinkedList<Valuta>();
	
	public static LinkedList<Valuta> vratiKursnuListu(){
		return kursnaLista;
	}

}
