package negocio.cruce.cruceOX;

import java.util.ArrayList;

import negocio.cruce.Cruce;
import negocio.funcion.Funcion;

import java.util.Random; 

public class CruceOXOP implements Cruce{

	@Override
	public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion, int porcentajeCruce) {
		// TODO Auto-generated method stub
		
		int tam_pob = poblacion.size();
		Random rnd = new Random();
		double prob = (double)porcentajeCruce / 100.0;
		
		int tam = poblacion.get(0).getTam();
		
		int porcentajeCambio = 40;
		
		int cambios = tam * porcentajeCambio / 100;
		
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
			
			ArrayList<Integer> gen1 = (ArrayList<Integer>)poblacion.get(cruces.get(i)).getIndividuo();
			ArrayList<Integer> gen2 = (ArrayList<Integer>)poblacion.get(cruces.get(i+1)).getIndividuo();
			
			
			ArrayList<Integer> hijo1 = new ArrayList<Integer>();
			ArrayList<Integer> hijo2 = new ArrayList<Integer>();
			
			for (int j = 0; i < tam_pob; j++) {
				hijo1.add(-1);
				hijo2.add(-1);
			}
			
			ArrayList<Integer> intercambios = new ArrayList<Integer>();
			int maxCambio = -1;
			
			for (int j = 0; j < cambios; j++) {
				int rand = -1;
				do {
					rand = rnd.nextInt(tam);
				}while (intercambios.contains(rand));
				
				intercambios.add(rand);
				if (rand > maxCambio) maxCambio = rand;
			}
			
			ArrayList<Integer> ciudades1 = new ArrayList<Integer>();
			
			for (int j = 0; j < cambios; j++) {
				ciudades1.add(gen1.get(intercambios.get(j)));
			}
			
			int act = 0;
			for (int j = 0; j < tam; j++) {
				if (!ciudades1.contains(gen2.get(j)))
					hijo2.add(j, gen2.get(j));
				else {
					hijo2.add(j, ciudades1.get(act));
					act++;
				}
			}
			
			
			ArrayList<Integer> ciudades2 = new ArrayList<Integer>();
			
			for (int j = 0; j < cambios; j++) {
				ciudades2.add(gen2.get(intercambios.get(j)));
			}
			
			act = 0;
			for (int j = 0; j < tam; j++) {
				if (!ciudades2.contains(gen1.get(j)))
					hijo1.add(j, gen1.get(j));
				else {
					hijo1.add(j, ciudades2.get(act));
					act++;
				}
			}
			
			poblacion.get(cruces.get(i)).setIndividuo(hijo1);
			poblacion.get(cruces.get(i+1)).setIndividuo(hijo2);
		}
		return poblacion;
	}

}
