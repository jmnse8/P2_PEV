package negocio.mutacion.mutacionHeuristica;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

import negocio.funcion.Funcion;
import negocio.funcion.funcionTraficoAereo.FuncionTraficoAereo;
import negocio.mutacion.Mutacion;

public class MutacionHeuristica implements Mutacion {
	private PriorityQueue<Funcion> permutaciones;
	private int tam_gen;

	@Override
	public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion, int porcentajeMutacion) {
		int tam_pob = poblacion.size();
		tam_gen = poblacion.get(0).getTam();
		double prob = (double) porcentajeMutacion / 100;
		Random rnd = new Random();

		for (int i = 0; i < tam_pob; i++) {// Recorro toda la población
			if (rnd.nextDouble() < prob) {// Miro si hay que mutar
				@SuppressWarnings("unchecked")
				ArrayList<Integer> gen = (ArrayList<Integer>) poblacion.get(i).getIndividuo();// Cojo el gen
				ArrayList<Integer> arrayPosicionesPermutar = new ArrayList<Integer>();// aquí guardo las posiciones que hay que permutar
				
				int numeroAPermutar = -1, contPosicionesSacadas = 0, posPermutar = 0;

				while (numeroAPermutar < 2 || numeroAPermutar > 5) // mínimo 2
					numeroAPermutar = rnd.nextInt(tam_gen);// Limitado a 5 para que como mucho haya 120 permutaciones, 
															//si fueran por ejemplo 10 para que no salgan más de 3.628.800 permutaciones
				while (contPosicionesSacadas < numeroAPermutar) {// saco aleatoriamente las posiciones
					posPermutar = rnd.nextInt(tam_gen);
					if (!arrayPosicionesPermutar.contains(posPermutar)) {
						arrayPosicionesPermutar.add(posPermutar);
						contPosicionesSacadas++;
					}
				}
				
				Funcion aux = new FuncionTraficoAereo();// para cojer el comparador
				permutaciones = new PriorityQueue<Funcion>(1, aux.getComp());// cola de prioridad para cojer el de menor fitness

				permutaciones(arrayPosicionesPermutar, gen, arrayPosicionesPermutar.size());// genero las permutaciones
				
				poblacion.get(i).setIndividuo(permutaciones.peek().getIndividuo());// Guardo el gen
			}
		}

		return poblacion;
	}

	private void permutaciones(final ArrayList<Integer> arrayPosicionesPermutar, ArrayList<Integer> gen, int k) {
		if (1 == k) {// cuando k llega a uno para y lo mete a la cola de prioridad
			FuncionTraficoAereo sol = new FuncionTraficoAereo();
			sol.setIndividuo(new ArrayList<Integer>(gen));
			sol.calculaFitness();
			permutaciones.add(sol);
		} else {
			for (int i = 0; i < k - 1; i++) {
				permutaciones(arrayPosicionesPermutar, gen, k - 1);
				if (k % 2 == 0) {
					intercambia(gen, i, k - 1, arrayPosicionesPermutar);
				} else {
					intercambia(gen, 0, k - 1, arrayPosicionesPermutar);
				}
			}
			permutaciones(arrayPosicionesPermutar, gen, k - 1);
		}
	}

	private void intercambia(ArrayList<Integer> gen, int i, int j, ArrayList<Integer> arrayPosicionesPermutar) {// intercambia las dos posiciones del gen
		Integer t = gen.get(arrayPosicionesPermutar.get(i));
		Integer t2 = gen.get(arrayPosicionesPermutar.get(j));
		gen.set(arrayPosicionesPermutar.get(i), t2);
		gen.set(arrayPosicionesPermutar.get(j), t);
	}
/*
	public static void main(String[] args) {
		ArrayList<Funcion> poblacion = new ArrayList<Funcion>();
		int porcentajeMutacion = 100;
		poblacion.add(new FuncionTraficoAereo());
		poblacion.add(new FuncionTraficoAereo());
		poblacion.add(new FuncionTraficoAereo());

		MutacionHeuristica m = new MutacionHeuristica();

		// System.out.println(poblacion.toString());

		m.execute(poblacion, porcentajeMutacion);

		// System.out.println(poblacion.toString());

	}*/
}
