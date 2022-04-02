package negocio.seleccion.seleccionTorneoProbabilistico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import negocio.funcion.Funcion;
import negocio.seleccion.Seleccion;

public class SeleccionTorneoProbabilistico implements Seleccion{

	@Override
	public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion, double presion) {
		
		int tam_pob = poblacion.size();
		ArrayList<Funcion> seleccion;
		ArrayList<Funcion> torneo;
        seleccion = new ArrayList<Funcion>();
        Random rnd = new Random();
        
        double p = 0.5; //para decidir cual coger
        
        for (int i = 0; i < tam_pob; i++) {
        	int k1 = -1;
        	int k2 = -1;
        	int k3 = -1;
        	
        	while (k1 == k2 || k1 == k3 || k2 == k3) {
        		if (k1 == -1)
        			k1 = rnd.nextInt(tam_pob);
        		if (k2 == -1 || k2 == k1)
        			k2 = rnd.nextInt(tam_pob);
        		k3 = rnd.nextInt(tam_pob);
        	}
        	
        	torneo = new ArrayList<Funcion>();
        	torneo.add(poblacion.get(k1));
        	torneo.add(poblacion.get(k2));
        	torneo.add(poblacion.get(k3));
        	Collections.sort(torneo, torneo.get(0).getComp());
        	
        	if (rnd.nextDouble() > p)
        		seleccion.add(torneo.get(0).getCopy());
        	else seleccion.add(torneo.get(2).getCopy());
        	
        }
        
		return seleccion;
	}
	
}
