package menjacnica;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class SOSacuvajUFajl {
	
	public static void sacuvajUFajl(LinkedList<Valuta> kursnaLista, String putanja){
		try{
			ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(putanja)));
			
			out.writeObject(kursnaLista);
			
			out.close();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

}
