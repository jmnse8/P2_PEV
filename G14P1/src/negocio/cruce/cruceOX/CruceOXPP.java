package negocio.cruce.cruceOX;

import java.util.ArrayList;

import negocio.cruce.Cruce;
import negocio.funcion.Funcion;

import java.util.Random; 

public class CruceOXPP implements Cruce{

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
			
			for (int j = 0; j < cambios; j++) {
				hijo1.add(intercambios.get(j), gen2.get(intercambios.get(j)));
				hijo2.add(intercambios.get(j), gen1.get(intercambios.get(j)));
			}
			
			int it = maxCambio + 1 , act = maxCambio +1;
			
			while (it != maxCambio) {
				it = it % tam;
				act = act % tam;
				
				if(hijo1.get(it) != -1) {
					it++;
				}else if(!hijo1.contains(gen1.get(act))) {
					hijo1.add(it, gen1.get(act));
					it++;
				}
				act++;
			}
			
			it = maxCambio + 1;
			act = maxCambio + 1;
			
			while (it != maxCambio) {
				it = it % tam;
				act = act % tam;
				
				if(hijo2.get(it) != -1) {
					it++;
				}else if(!hijo2.contains(gen2.get(act))) {
					hijo2.add(it, gen2.get(act));
					it++;
				}
				act++;
			}
			
			
			poblacion.get(cruces.get(i)).setIndividuo(hijo1);
			poblacion.get(cruces.get(i+1)).setIndividuo(hijo2);
		}
		return poblacion;
	}

}
