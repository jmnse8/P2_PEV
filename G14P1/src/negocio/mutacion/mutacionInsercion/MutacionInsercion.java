package negocio.mutacion.mutacionInsercion;

import java.util.ArrayList;
import java.util.Random;
import negocio.funcion.Funcion;
import negocio.mutacion.Mutacion;

public class MutacionInsercion implements Mutacion{

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
	
				ArrayList<Integer> arrayPosicionesSacar = new ArrayList<Integer>();
				ArrayList<Integer> arrayPosicionesMeter = new ArrayList<Integer>();
				
				int numeroAInsertar = -1, contPosicionesSacadas = 0, posSacar = 0, posMeter = 0;
				
				while(numeroAInsertar < 1) 
					numeroAInsertar = rnd.nextInt(tam_gen / 2);// Lo limito a la mitad del tamaño para que no haya muchas inserciones

				while(contPosicionesSacadas < numeroAInsertar) {
					posSacar = rnd.nextInt(tam_gen);
					posMeter = rnd.nextInt(tam_gen);
					if(!arrayPosicionesSacar.contains(posSacar) && posSacar > posMeter) {
						arrayPosicionesSacar.add(posSacar);
						arrayPosicionesMeter.add(posMeter);
						contPosicionesSacadas++;
					}
				}
				
				for(int j = 0; j < numeroAInsertar; j++) {// inserto el correspondiente nº de arrayPosicionesSacar en el nº de arrayPosicionesMeter
					int aux = gen.get((int) arrayPosicionesSacar.get(j));//lo saco
					gen.remove((int) arrayPosicionesSacar.get(j));// lo borro
					gen.add(arrayPosicionesMeter.get(j), aux);// lo meto
				}

				poblacion.get(i).setIndividuo(gen);// Guardo el gen
			}
		}

		return poblacion;
	}
/*
	public static void main(String[] args) {
		ArrayList<Funcion> poblacion = new ArrayList<Funcion>();
		int porcentajeMutacion = 100;
		poblacion.add(new FuncionTraficoAereo());
		poblacion.add(new FuncionTraficoAereo());
		poblacion.add(new FuncionTraficoAereo());
		
		MutacionInsercion m = new MutacionInsercion();
		
		//System.out.println(poblacion.toString());
		
		m.execute(poblacion, porcentajeMutacion);
		
		//System.out.println(poblacion.toString());
	
	}*/
}
