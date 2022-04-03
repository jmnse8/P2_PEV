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
		
		for(int i = 0; i < tam_pob; i++) { //Seleccionamos los individuos a cruzar
			if (rnd.nextDouble() < prob)
				cruces.add(i);
		}
		
		int numCruces = cruces.size();
		
		if (numCruces % 2 != 0) { //Si son impares eliminamos uno
			cruces.remove(numCruces / 2);
			numCruces--;
		}
		
		for(int i = 0; i < numCruces; i += 2) {
			
			ArrayList<Integer> gen1 = (ArrayList<Integer>)poblacion.get(cruces.get(i)).getIndividuo();
			ArrayList<Integer> gen2 = (ArrayList<Integer>)poblacion.get(cruces.get(i+1)).getIndividuo();
			
			
			ArrayList<Integer> hijo1 = new ArrayList<Integer>();
			ArrayList<Integer> hijo2 = new ArrayList<Integer>();
			
			for (int j = 0; j < tam; j++) {//Rellenamos los hijos a -1
				hijo1.add(-1);
				hijo2.add(-1);
			}
			
			//Seleccionamos las posiciones a intercambiar de forma aleatoria
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
			
			//Intercambiamos esas posiciones
			for (int j = 0; j < cambios; j++) {
				hijo1.set(intercambios.get(j), gen2.get(intercambios.get(j)));
				hijo2.set(intercambios.get(j), gen1.get(intercambios.get(j)));
			}
			
			int it = maxCambio + 1 , act = maxCambio +1;
			
			
			//rellenamos el resto de ambos hijos con los valores restantes de los padres de forma ordenada
			
			//Rellenamos hijo 1
			while (it != maxCambio) {
				it = it % tam;
				act = act % tam;
				
				if(hijo1.get(it) != -1) {
					it++;
				}else if(!hijo1.contains(gen1.get(act))) {
					hijo1.set(it, gen1.get(act));
					it++;
				}
				act++;
			}
			
			it = maxCambio + 1;
			act = maxCambio + 1;
			
			//Rellenamos hijo 2
			while (it != maxCambio) {
				it = it % tam;
				act = act % tam;
				
				if(hijo2.get(it) != -1) {
					it++;
				}else if(!hijo2.contains(gen2.get(act))) {
					hijo2.set(it, gen2.get(act));
					it++;
				}
				act++;
			}
			
			//Reintroducimos los hijos a la poblacion
			poblacion.get(cruces.get(i)).setIndividuo(hijo1);
			poblacion.get(cruces.get(i+1)).setIndividuo(hijo2);
		}
		return poblacion;
	}

}
