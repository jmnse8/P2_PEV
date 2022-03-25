package negocio.mutacion.mutacionBasica;

import java.util.ArrayList;

import java.util.Random;

import negocio.funcion.Funcion;
import negocio.mutacion.Mutacion;

public class MutacionBasica implements Mutacion{

	@Override
	public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion, int porcentajeMutacion) {
		
		int tam_pob = poblacion.size();
		int tam_gen = poblacion.get(0).getTam();
		double prob = (double)porcentajeMutacion/100;
		Random rnd = new Random();
		
		for (int i = 0; i < tam_pob; i++){
			@SuppressWarnings("unchecked")
			ArrayList<Boolean> gen = (ArrayList<Boolean>) poblacion.get(i).getIndividuo();
			for (int j = 0; j < tam_gen; j++) {
				if (rnd.nextDouble() < prob)
					gen.set(j,!gen.get(j));
			}
			poblacion.get(i).setIndividuo(gen);
		}
		
		return poblacion;
	}

}
