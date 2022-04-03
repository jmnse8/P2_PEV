package negocio.cruce.cruceCX;

import java.util.ArrayList;

import negocio.cruce.Cruce;
import negocio.funcion.Funcion;

import java.util.Random; 

public class CruceCX implements Cruce{

	@Override
	public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion, int porcentajeCruce) {
		// TODO Auto-generated method stub
		
		int tam_pob = poblacion.size();
		Random rnd = new Random();
		double prob = (double)porcentajeCruce / 100.0;
		
		
		
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
			
			int tam = poblacion.get(0).getTam();
			
			for (int j = 0; j < tam; j++) { //Rellenamos los hijos a -1
				hijo1.add(-1);
				hijo2.add(-1);
			}
			
			
			//Cogemos el primer elemento del individuo y empezamos el ciclo
			int first = gen1.get(0);
			hijo1.set(0, first);
			int pair = gen2.get(0);
			
			boolean cont = true;
			
			while (cont) { //Continuamos el ciclo hasta que encontremos un elemento ya este
				int it = getIndex(gen1,pair);
				hijo1.set(it, pair);
				pair = gen2.get(it);
				cont = (hijo1.get(gen1.indexOf(pair)) == -1);
			}
			
			for (int j = 0; j < tam; j++) { //rellenamos el resto con el otro padre
				if (hijo1.get(j) == -1)
					hijo1.set(j, gen2.get(j));
			}
			
			//Realizamos el mismo proceso que en el hijo anterior
			first = gen2.get(0);
			hijo2.set(0, first);
			pair = gen1.get(0);
			
			cont = true;
			
			while (cont) {
				int it = getIndex(gen2,pair);
				hijo2.set(it, pair);
				pair = gen1.get(it);
				cont = (hijo2.get(gen2.indexOf(pair)) == -1);
			}
			
			for (int j = 0; j < tam; j++) {
				if (hijo2.get(j) == -1)
					hijo2.set(j, gen1.get(j));
			}
			
			//Reintroducimos los hijos a la poblacion
			poblacion.get(cruces.get(i)).setIndividuo(hijo1);
			poblacion.get(cruces.get(i+1)).setIndividuo(hijo2);
		}
		return poblacion;
	}
	
	private int getIndex(ArrayList<Integer> indiv, int val) {
		return indiv.indexOf(val);
	}

}
