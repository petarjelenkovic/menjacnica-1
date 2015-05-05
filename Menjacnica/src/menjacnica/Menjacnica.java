package menjacnica;


import java.util.LinkedList;

public class Menjacnica implements MenjacnicaInterface{
	
	@Override
	public void dodajValutu(Valuta valuta) {
		SODodajValutu.dodajValutu(valuta, kursnaLista);
			}

	@Override
	public void obrisiValutu(Valuta valuta) {
		SOOBrisiValutu.obrisiValutu(valuta, kursnaLista);
	}

	@Override
	public double izvrsiTransakciju(Valuta valuta, boolean prodaja, double iznos) {
		return SOIzvrsiTransakciju.izvrsiTransakciju(valuta, prodaja, iznos);
	}

	@Override
	public LinkedList<Valuta> vratiKursnuListu() {
		return SOVratiKursnuListu.vratiKursnuListu();
	}

	@Override
	public void ucitajIzFajla(String putanja) {
		SOUcitajIzFajla.ucitajIzFajla(kursnaLista, putanja);
	}

	@Override
	public void sacuvajUFajl(String putanja) {
		SOSacuvajUFajl.sacuvajUFajl(kursnaLista, putanja);
	}

	private LinkedList<Valuta> kursnaLista = SOVratiKursnuListu.vratiKursnuListu();

}
