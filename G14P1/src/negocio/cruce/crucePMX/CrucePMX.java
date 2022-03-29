package negocio.cruce.crucePMX;

import java.util.ArrayList;

import negocio.cruce.Cruce;
import negocio.funcion.Funcion;

import java.util.Random; 

public class CrucePMX implements Cruce{

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
			
			for (int j = 0; i < tam_pob; j++) {
				hijo1.add(-1);
				hijo2.add(-1);
			}
			int r1 = rnd.nextInt(tam_pob);
			int r2 = rnd.nextInt(tam_pob);
			
			int c1 = Math.min(r1, r2);
			int c2 = Math.max(r1, r2);
			
			int tam = poblacion.get(0).getTam();
			
			for (int j = c1; j <= c2; j++ ) {
				hijo1.add(j, gen2.get(j));
				hijo2.add(j, gen1.get(j));
			}
			
			for (int j = 0; j < tam; j++) {
				if (hijo1.get(j) == -1) {
					if (!hijo1.contains(gen1.get(j))) {
						hijo1.add(j, gen1.get(j));
					}else {
						hijo1.add(j, gen2.get(getIndex(gen1,gen1.get(j))));
					}
					
					if (!hijo2.contains(gen2.get(j))) {
						hijo2.add(j, gen2.get(j));
					}else {
						hijo2.add(j, gen1.get(getIndex(gen2,gen2.get(j))));
					}
				}
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
