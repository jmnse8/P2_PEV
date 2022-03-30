package negocio.cruce.cruceCO;

import java.util.ArrayList;
import java.util.Collections;

import negocio.cruce.Cruce;
import negocio.funcion.Funcion;

import java.util.Random; 

public class CruceCO implements Cruce{

	@Override
	public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion, int porcentajeCruce) {
		// TODO Auto-generated method stub
		
		int tam_pob = poblacion.size();
		Random rnd = new Random();
		double prob = (double)porcentajeCruce / 100.0;
		
		
		
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
			ArrayList<Integer> gen1copia = new ArrayList<Integer>();
			ArrayList<Integer> gen2copia = new ArrayList<Integer>();
			ArrayList<Integer> recorrido1 = new ArrayList<Integer>();
			ArrayList<Integer> recorrido2 = new ArrayList<Integer>();
			
			int tam = poblacion.get(0).getTam();
			
			for (int j = 0; j < tam; j++) {
				gen1copia.add(gen1.get(j));
				gen2copia.add(gen2.get(j));
			}
			
			Collections.sort(gen1copia);
			Collections.sort(gen2copia);
			
			for (int j = 0; j < tam; j++) {
				int pos = getIndex(gen1copia,gen1.get(j));
				recorrido1.add(pos);
				gen1copia.remove(pos);
				
				pos = getIndex(gen2copia,gen2.get(j));
				recorrido2.add(pos);
				gen2copia.remove(pos);
			}
			
			for(int j = tam/2; j < tam; j++) {
				int aux = recorrido1.get(j);
				recorrido1.add(j,recorrido2.get(j));
				recorrido2.add(j, aux);
			}
			
			for (int j = 0; j < tam; j++) {
				gen1copia.add(gen1.get(j));
				gen2copia.add(gen2.get(j));
			}
			
			Collections.sort(gen1copia);
			Collections.sort(gen2copia);
			
			for (int j = 0; j < tam; j++) {
				int pos = recorrido1.get(j);
				hijo1.add(gen1copia.get(pos));
				gen1copia.remove(pos);
				
				pos = recorrido2.get(j);
				hijo2.add(gen2copia.get(pos));
				gen2copia.remove(pos);
			}
			
			poblacion.get(cruces.get(i)).setIndividuo(hijo1);
			poblacion.get(cruces.get(i+1)).setIndividuo(hijo2);
		}
		return poblacion;
	}
	
	private int getIndex(ArrayList<Integer> indiv, int val) {
		int i = 0;
		while (true) {
			if (indiv.get(i) == val)
				return i;
			
			i++;
		}
	}

}