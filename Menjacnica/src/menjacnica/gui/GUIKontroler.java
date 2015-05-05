package menjacnica.gui;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import menjacnica.Menjacnica;
import menjacnica.Valuta;


public class GUIKontroler {
	
	private static Menjacnica sistem ;
	private static MenjacnicaGUI frame;
	private static DodajKursGUI prozor;
	private static Valuta valuta;
	private static ObrisiKursGUI obrs;
	private static IzvrsiZamenuGUI izv;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sistem = new Menjacnica();
					frame = new MenjacnicaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public static void prikaziDodajKursGUI() {
		prozor = new DodajKursGUI(frame);
		prozor.setLocationRelativeTo(frame.getContentPane());
		prozor.setVisible(true);
	}
	
	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(frame.getContentPane(),
				"Da li ZAISTA zelite da izadjete iz apliacije", "Izlazak",
				JOptionPane.YES_NO_OPTION);

		if (opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	public static void prikaziAboutProzor(){
		JOptionPane.showMessageDialog(frame.getContentPane(),
				"Autor: Bojan Tomic, Verzija 1.0", "O programu Menjacnica",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void sacuvajUFajl() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(frame.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				sistem.sacuvajUFajl(file.getAbsolutePath());
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(frame.getContentPane(), e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void ucitajIzFajla() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(frame.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				sistem.ucitajIzFajla(file.getAbsolutePath());
				frame.prikaziSveValute();
			}	
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(frame.getContentPane(), e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static Object[] setListData(){
		return sistem.vratiKursnuListu().toArray();
	}
	
	public static void prikaziObrisiKursGUI(Object obj) {
		if (obj != null) {
			valuta = (Valuta) obj;
			obrs = new ObrisiKursGUI(frame,
					valuta);
			obrs.setLocationRelativeTo(frame.getContentPane());
			obrs .setVisible(true);
		}
	}
	
	public static void unesiKurs(String naziv, String skraceniNaziv, int sifra, double prodajni, double kupovni, double srednji) {
		try {
			Valuta valuta = new Valuta();

			// Punjenje podataka o valuti
			valuta.setNaziv(naziv);
			valuta.setSkraceniNaziv(skraceniNaziv);
			valuta.setSifra(sifra);
			valuta.setProdajni(prodajni);
			valuta.setKupovni(kupovni);
			valuta.setSrednji(srednji);
			
			// Dodavanje valute u kursnu listu
			sistem.dodajValutu(valuta);

			// Osvezavanje glavnog prozora
			frame.prikaziSveValute();
			
			//Zatvaranje DodajValutuGUI prozora
			prozor.dispose();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(prozor.getContentPane(), e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void obrisiValutu() {
		try{
			sistem.obrisiValutu(valuta);
			
			frame.prikaziSveValute();
			obrs.dispose();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(obrs.getContentPane(), e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static Object valutineVrednosti(int a){
		if(a==1) return valuta.getNaziv();
		if(a==2) return valuta.getSkraceniNaziv();
		if(a==3) return valuta.getSifra();
		if(a==4) return valuta.getProdajni();
		if(a==5) return valuta.getKupovni();
		if(a==6) return valuta.getSrednji();
		return null;
	}
	
	public static void prikaziIzvrsiZamenuGUI(Object obj) {
		if (obj != null) {
			valuta = (Valuta) obj;
			izv = new IzvrsiZamenuGUI(frame,
					valuta);
			izv.setLocationRelativeTo(frame.getContentPane());
			izv.setVisible(true);
		}
	}
	
	public static double izvrsiZamenu(boolean a, String v){
		try{
			double konacniIznos = 
					sistem.izvrsiTransakciju(valuta,
							a, 
							Double.parseDouble(v));
					return konacniIznos;
		
			
		} catch (Exception e1) {
		JOptionPane.showMessageDialog(izv.getContentPane(), e1.getMessage(),
				"Greska", JOptionPane.ERROR_MESSAGE);
	}
		return 0;
	}

}
