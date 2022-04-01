package negocio.mutacion.mutacionIntercambio;

import java.util.ArrayList;
import java.util.Random;

import negocio.funcion.Funcion;
import negocio.mutacion.Mutacion;

public class MutacionIntercambio implements Mutacion{

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

				int punto1 = rnd.nextInt(tam_gen), punto2 = rnd.nextInt(tam_gen);// saco los dos puntos de intercambio
				while (punto1 == punto2) // los puntos de intercambio no pueden ser iguales
					punto2 = rnd.nextInt(tam_gen);
				
				Integer p1 = gen.get(punto1);//Los cojo
				Integer p2 = gen.get(punto2);
				
				gen.set(punto1, p2);//Los intercambio
				gen.set(punto2, p1);

				poblacion.get(i).setIndividuo(gen);// Guardo el gen
			}
		}

		return poblacion;
	}

}
