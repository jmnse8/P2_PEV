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
				//@SuppressWarnings("unchecked")
				//ArrayList<Integer> genCopia = (ArrayList<Integer>) poblacion.get(i).getIndividuo();
				ArrayList<Integer> arrayPosicionesPermutar = new ArrayList<Integer>();

				System.err.println(gen);
				int numeroAPermutar = -1, contPosicionesSacadas = 0, posPermutar = 0;

				while (numeroAPermutar < 2 || numeroAPermutar > 10) // mínimo 2
					numeroAPermutar = rnd.nextInt(tam_gen);// Limitado a 10 para que no salgan más de 3.628.800 permutaciones
				numeroAPermutar = 3;
				
				while (contPosicionesSacadas < numeroAPermutar) {
					posPermutar = rnd.nextInt(tam_gen);
					if (!arrayPosicionesPermutar.contains(posPermutar)) {
						arrayPosicionesPermutar.add(posPermutar);
						contPosicionesSacadas++;
					}
				}
				//System.out.println("\n" + arrayPosicionesPermutar + "\n");
				Funcion aux = new FuncionTraficoAereo();
				permutaciones = new PriorityQueue<Funcion>(1, aux.getComp());

				ArrayList<Integer> indPerm = new ArrayList<Integer>();
				for (int j = 0; j < tam_gen; j++) {
					int nu = gen.get(j);
					if (!arrayPosicionesPermutar.contains((Integer) nu))
						indPerm.add(nu);
				}
				/*boolean[] cogido = new boolean[arrayPosicionesPermutar.size()];
				for(int j = 0; j < cogido.length; j++) {
					cogido[j] = false;
				}*/

				//permutaciones(arrayPosicionesPermutar, gen, cogido, indPerm, 0);
				permutaciones(arrayPosicionesPermutar, gen, 3);
				permutaciones.stream().forEach(p -> System.out.println(p.getFitness() + " " + p.getIndividuo() + "\n"));
				
				poblacion.get(i).setIndividuo(permutaciones.peek().getIndividuo());// Guardo el gen
			}
		}

		return poblacion;
	}
	
	private void permutaciones(final ArrayList<Integer> arrayPosicionesPermutar, ArrayList<Integer> gen,int k) {
		if (1 == k){//(genActual.size() == tam_gen) {
			FuncionTraficoAereo sol = new FuncionTraficoAereo();
			sol.setIndividuo(new ArrayList<Integer>(gen));
			sol.calculaFitness();
			//System.err.println("\n" + sol.getFitness() + " " + sol.getIndividuo() + "\n");
			permutaciones.add(sol);
		} else {
			for(int i = 0; i < k - 1; i++) {
				permutaciones(arrayPosicionesPermutar, gen, k - 1);
				if(k % 2 == 0) {
					intercambia(gen, i, k - 1, arrayPosicionesPermutar);
				}
				else {
					intercambia(gen, i, k - 1, arrayPosicionesPermutar);
				}
			}
			permutaciones(arrayPosicionesPermutar, gen, k - 1);
		}
	}
	
	private void intercambia(ArrayList<Integer> gen, int i, int j, ArrayList<Integer> arrayPosicionesPermutar) {
		Integer t = gen.get(arrayPosicionesPermutar.get(i));
		Integer t2 = gen.get(arrayPosicionesPermutar.get(j));
		gen.set(arrayPosicionesPermutar.get(i), t2);
		gen.set(arrayPosicionesPermutar.get(j), t);
	}

	public static void main(String[] args) {
		ArrayList<Funcion> poblacion = new ArrayList<Funcion>();
		int porcentajeMutacion = 100;
		poblacion.add(new FuncionTraficoAereo());
		poblacion.add(new FuncionTraficoAereo());
		poblacion.add(new FuncionTraficoAereo());
		
		MutacionHeuristica m = new MutacionHeuristica();
		
		//System.out.println(poblacion.toString());
		
		m.execute(poblacion, porcentajeMutacion);
		
		//System.out.println(poblacion.toString());
	
	}
}
