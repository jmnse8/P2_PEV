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
			
			int tam = poblacion.get(0).getTam();
			
			ArrayList<Integer> gen1 = (ArrayList<Integer>)poblacion.get(cruces.get(i)).getIndividuo();
			ArrayList<Integer> gen2 = (ArrayList<Integer>)poblacion.get(cruces.get(i+1)).getIndividuo();
			
			
			ArrayList<Integer> hijo1 = new ArrayList<Integer>();
			ArrayList<Integer> hijo2 = new ArrayList<Integer>();
			
			for (int j = 0; j < tam; j++) {//Rellenamos los hijos a -1
				hijo1.add(-1);
				hijo2.add(-1);
			}
			
			//Elegimos los puntos de corte de forma aleatoria
			int r1 = rnd.nextInt(tam);
			int r2 = rnd.nextInt(tam);
			
			int c1 = Math.min(r1, r2);
			int c2 = Math.max(r1, r2);
			
			
			//intercambiamos la zona seleccionada
			for (int j = c1; j <= c2; j++ ) {
				hijo1.set(j, gen2.get(j));
				hijo2.set(j, gen1.get(j));
			}
			
			//El resto se rellena con lo que hay en el padre siempre que se pueda
			for (int j = 0; j < tam; j++) {
				if (hijo1.get(j) == -1) {
					if (!hijo1.contains(gen1.get(j))) {
						hijo1.set(j, gen1.get(j));
					}else {
						int aux = hijo2.get(getIndex(hijo1,gen1.get(j)));
						if (!hijo1.contains(aux)) {
							hijo1.set(j,aux);
						}
						
					}
					
					if (!hijo2.contains(gen2.get(j))) {
						hijo2.set(j, gen2.get(j));
					}else {
						int aux = hijo1.get(getIndex(hijo2,gen2.get(j)));
						if (!hijo2.contains(aux)) {
							hijo2.set(j,aux);
						}
						
					}
				}
			}
			
			//En caso de que no se encuentre un valor adecuado se rellena con los restantes del padre
			hijo1 = rellena(hijo1,gen1);
			hijo2 = rellena(hijo2,gen2);
			
			poblacion.get(cruces.get(i)).setIndividuo(hijo1);
			poblacion.get(cruces.get(i+1)).setIndividuo(hijo2);
		}
		return poblacion;
	}
	
	private int getIndex(ArrayList<Integer> indiv, int val) {
		return indiv.indexOf(val);
	}
	
	//Esta funcion crea un array con los elementos que hay en el padre y no en el hijo y los introduce
	//en el hijo siguiendo el orden relativo que tenian en el padre
	ArrayList<Integer> rellena(ArrayList<Integer> hijo, ArrayList<Integer> padre){
		ArrayList<Integer> sol = new ArrayList<Integer>();
		ArrayList<Integer> posibles = new ArrayList<Integer>();
		
		for (int i = 0; i < padre.size(); i++) {
			if (!hijo.contains(padre.get(i)))
				posibles.add(padre.get(i));
		}
		
		for (int i = 0; i < hijo.size(); i++) {
			if (hijo.get(i) == -1) {
				sol.add(posibles.get(0));
				posibles.remove(0);
			}else {
				sol.add(hijo.get(i));
			}
		}
		
		return sol;
	}

}
