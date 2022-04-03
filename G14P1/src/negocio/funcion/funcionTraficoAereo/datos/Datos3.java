package negocio.funcion.funcionTraficoAereo.datos;

import negocio.funcion.funcionTraficoAereo.Vuelo;

public class Datos3 {

	public static Vuelo[] getVuelos() {
		Vuelo[] vuelos = { new Vuelo(1, "UA138", 'w'), new Vuelo(2, "UA532", 'g'),
				new Vuelo(3, "UA599", 'w'), new Vuelo(4, "NW358", 'w'), new Vuelo(5, "UA2987", 'p'),
				new Vuelo(6, "AA128", 'w'), new Vuelo(7, "UA1482", 'g'), new Vuelo(8, "NW357", 'w'),
				new Vuelo(9, "AA129", 'w'), new Vuelo(10, "UA2408", 'p'), new Vuelo(11, "UA805", 'w'),
				new Vuelo(12, "AA309", 'g'), new Vuelo(13, "AA309", 'p'), new Vuelo(14, "AA309", 'g'),
				new Vuelo(15, "AA309", 'w'), new Vuelo(16, "AA309", 'w'), new Vuelo(17, "AA309", 'g'),
				new Vuelo(18, "AA309", 'w'), new Vuelo(19, "AA309", 'p'), new Vuelo(20, "AA309", 'p'),
				new Vuelo(21, "AA309", 'w')};
		return vuelos;
	}//9 mas

	public final static int[][] tel = { { 11, 15, 6, 6,  9, 7, 15, 6, 6,  9, 7, 9, 12, 15, 3, 17, 9 , 22, 13, 19, 26},
										{ 10, 17, 7, 7, 12, 6, 17, 7, 7, 12, 6, 7, 10, 17, 5, 20, 7 , 27, 15, 17, 22},
										{  9, 19, 8, 8, 15, 5, 19, 8, 8, 15, 5, 5,  9, 16, 6, 15, 12, 20, 17, 18, 24}};
	
	public final static int[] menorTel = {9, 15, 6, 6, 9, 5, 15, 6, 6, 9, 5, 5, 9, 15, 3, 15, 7, 20, 13, 17, 22};

	public final static double[][] sep = { { 1, 1.5, 2 }, { 1, 1.5, 1.5 }, { 1, 1, 1 } };

	public static int getNumVuelos() {
		return 21;
	}
	public static int getNumPistas() {
		return 3;
	}
}
