package negocio.mutacion.mutacionHeuristica;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

import negocio.funcion.Funcion;
import negocio.funcion.funcionTraficoAereo.FuncionTraficoAereo;
import negocio.mutacion.Mutacion;

public class MutacionHeuristica implements Mutacion{

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
				@SuppressWarnings("unchecked")
				ArrayList<Integer> genCopia = (ArrayList<Integer>) poblacion.get(i).getIndividuo();
				ArrayList<Integer> arrayPosicionesPermutar = new ArrayList<Integer>();
				
				int numeroAPermutar = -1, contPosicionesSacadas = 0, posPermutar = 0;
				
				while(numeroAPermutar < 2) // mínimo 2
					numeroAPermutar = rnd.nextInt(10);// Limitado a 10 para que no salgan más de 3628800 permutaciones

				while(contPosicionesSacadas < numeroAPermutar) {
					posPermutar = rnd.nextInt(tam_gen);
					if(!arrayPosicionesPermutar.contains(posPermutar)) {
						arrayPosicionesPermutar.add(posPermutar);
						contPosicionesSacadas++;
					}
				}
				Funcion aux = new FuncionTraficoAereo();
				PriorityQueue<Funcion> permutaciones = new PriorityQueue<Funcion>(0, aux.getComp());
				
				ArrayList<Integer> indPerm = new ArrayList<Integer>();
				
				

				poblacion.get(i).setIndividuo(gen);// Guardo el gen
			}
		}

		return poblacion;
	}
	

}
