package negocio.mutacion.mutacionInversion;

import java.util.ArrayList;
import java.util.Random;

import negocio.funcion.Funcion;
import negocio.mutacion.Mutacion;

public class MutacionInversion implements Mutacion {

	@Override
	public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion, int porcentajeMutacion) {
		int tam_pob = poblacion.size();
		int tam_gen = poblacion.get(0).getTam();
		double prob = (double) porcentajeMutacion / 100;
		Random rnd = new Random();

		for (int i = 0; i < tam_pob; i++) {// Recorro toda la población
			if (rnd.nextDouble() < prob) {// Miro si hay que mutar
				@SuppressWarnings("unchecked")
				ArrayList<Integer> gen = (ArrayList<Integer>) poblacion.get(i).getIndividuo();// Cojo el gen
				ArrayList<Integer> genNuevo = new ArrayList<Integer>();// me preparo el nuevo
				
				int ini = rnd.nextInt(tam_gen - 1), fin = -1;// saco el punto de inicio de inversión
				while (fin <= ini) // saco el punto de fin de inversión que tiene que ser mayor que ini
					fin = rnd.nextInt(tam_gen);
				
				for (int j = 0; j < ini; j++) // Meto los de la izquierda del intervalo
					genNuevo.add((int)gen.get(j));

				for (int j = fin - 1; j >= ini; j--) // Meto el intervalo INVERTIDO
					genNuevo.add((int)gen.get(j));

				for (int j = fin; j < tam_gen; j++) // Meto los de la derecha del intervalo
					genNuevo.add((int)gen.get(j));
				
				poblacion.get(i).setIndividuo(genNuevo);// Guardo el gen
			}
		}

		return poblacion;
	}
}
