package negocio.cruce.cruceUniforme;

import java.util.ArrayList;

import negocio.cruce.Cruce;
import negocio.funcion.Funcion;

import java.util.Random; 

public class CruceUniforme implements Cruce{

	@Override
	public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion, int porcentajeCruce) {
		// TODO Auto-generated method stub
		
		int tam_pob = poblacion.size();
		Random rnd = new Random();
		double prob = (double)porcentajeCruce / 100;
		
		
		
		ArrayList<Integer> cruces;
		cruces = new ArrayList<Integer>();
		
		for(int i = 0; i < tam_pob; i++) {
			if (rnd.nextDouble() < prob)
				cruces.add(i);
		}
		
		int numCruces = cruces.size();
		
		if (numCruces % 2 != 0) {
			cruces.remove(numCruces / 2);
			numCruces--;
		}
		
		for(int i = 0; i < numCruces; i += 2) {
			
			ArrayList<Object> gen1 = (ArrayList<Object>) poblacion.get(cruces.get(i)).getIndividuo();
			ArrayList<Object> gen2 = (ArrayList<Object>) poblacion.get(cruces.get(i+1)).getIndividuo();
			
			//Hacer aqui el cruce de i y i+1
			int tam = poblacion.get(0).getTam();
			int punto = rnd.nextInt(tam);
			
			for (int j = 0; j < tam; j++) {
				
				if (rnd.nextDouble() < 0.5) {				
					Object aux = gen1.get(j);
					gen1.set(j, gen2.get(j));
					gen2.set(j, aux);
				}
			}
			poblacion.get(cruces.get(i)).setIndividuo(gen1);
			poblacion.get(cruces.get(i+1)).setIndividuo(gen2);
		}
		return poblacion;
	}

}
