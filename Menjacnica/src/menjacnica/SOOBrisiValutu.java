package menjacnica;

import java.util.LinkedList;

public class SOOBrisiValutu {
	
	public static void obrisiValutu(Valuta valuta, LinkedList<Valuta> kursnaLista){
		if (!kursnaLista.contains(valuta))
			throw new RuntimeException("Valuta ne postoji u kursnoj listi");
		
		kursnaLista.remove(valuta);
	}

}
