package negocio.cruce.cruceBLX;

import java.util.ArrayList;
import java.util.Random;

import negocio.cruce.Cruce;
import negocio.funcion.Funcion;

public class CruceBLX implements Cruce{

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
			
			ArrayList<Double> gen1 = (ArrayList<Double>) poblacion.get(cruces.get(i)).getIndividuo();
			ArrayList<Double> gen2 = (ArrayList<Double>) poblacion.get(cruces.get(i+1)).getIndividuo();
			
			//Hacer aqui el cruce de i y i+1
			int tam = poblacion.get(0).getTam();
			int punto = rnd.nextInt(tam);
			
			for (int j = 0; j < tam; j++) {
				double val1 = gen1.get(j);
				double val2 = gen2.get(j);
				
				double alfa = rnd.nextDouble();
				double cMax = 0, cMin = 0;
				
				if (val1 > val2) {
					cMax = val1;
					cMin = val2;
				} else {
					cMax = val2;
					cMin = val1;
				}
					
				double diff = cMax - cMin;
				
				double valorMin = cMin - (diff * alfa);
				double valorMax = cMax + (diff * alfa);
				
				double aleatorio =  rnd.nextDouble()*(valorMax - valorMin) + valorMin;
				gen1.set(j, aleatorio);
				aleatorio =  rnd.nextDouble()*(valorMax - valorMin) + valorMin;
				gen2.set(j, aleatorio);
			
			}
			poblacion.get(cruces.get(i)).setIndividuo(gen1);
			poblacion.get(cruces.get(i+1)).setIndividuo(gen2);
		}
		
		return poblacion;
	}
	

}