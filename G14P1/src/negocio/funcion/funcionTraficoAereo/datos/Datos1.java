package negocio.funcion.funcionTraficoAereo.datos;

import negocio.funcion.funcionTraficoAereo.Vuelo;

public class Datos1 {

	public static Vuelo[] getVuelos() {
		Vuelo[] vuelos = { new Vuelo(1, "UA138", 'w'), new Vuelo(2, "UA532", 'g'),
				new Vuelo(3, "UA599", 'w'), new Vuelo(4, "NW358", 'w'), new Vuelo(5, "UA2987", 'p'),
				new Vuelo(6, "AA128", 'w'), new Vuelo(7, "UA1482", 'g'), new Vuelo(8, "NW357", 'w'),
				new Vuelo(9, "AA129", 'w'), new Vuelo(10, "UA2408", 'p'), new Vuelo(11, "UA805", 'w'),
				new Vuelo(12, "AA309", 'g') };
		return vuelos;
	}

	public final static int[][] tel = { { 11, 15, 6, 6, 9, 7, 15, 6, 6, 9, 7, 9 },
			{ 10, 17, 7, 7, 12, 6, 17, 7, 7, 12, 6, 7 }, { 9, 19, 8, 8, 15, 5, 19, 8, 8, 15, 5, 5 } };
	
	public final static int[] menorTel = {9, 15, 6, 6, 9, 5, 15, 6, 6, 9, 5, 5};

	public final static double[][] sep = { { 1, 1.5, 2 }, { 1, 1.5, 1.5 }, { 1, 1, 1 } };

	public static int getNumVuelos() {
		return 12;
	}
	public static int getNumPistas() {
		return 3;
	}
}
