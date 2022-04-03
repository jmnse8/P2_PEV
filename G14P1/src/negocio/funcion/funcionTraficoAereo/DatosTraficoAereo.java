package negocio.funcion.funcionTraficoAereo;

import negocio.funcion.funcionTraficoAereo.datos.Datos1;
import negocio.funcion.funcionTraficoAereo.datos.Datos2;
import negocio.funcion.funcionTraficoAereo.datos.Datos3;

public class DatosTraficoAereo {// clase que devuelve los datos según el nº caso que le pases

	public static Vuelo[] getVuelos(int nCaso) {
		switch (nCaso) {
		case 1:
			return Datos1.getVuelos();
		case 2:
			return Datos2.getVuelos();
		case 3:
			return Datos3.getVuelos();
		default:
			return Datos1.getVuelos();
		}

	}
	
	public static int getTel(int nCaso, int i, int j) {
		switch (nCaso) {
		case 1:
			return Datos1.tel[i][j];
		case 2:
			return Datos2.tel[i][j];
		case 3:
			return Datos3.tel[i][j];
		default:
			return Datos1.tel[i][j];
		}
	}
	
	public static int getMenorTel(int nCaso, int i) {
		switch (nCaso) {
		case 1:
			return Datos1.menorTel[i];
		case 2:
			return Datos2.menorTel[i];
		case 3:
			return Datos3.menorTel[i];
		default:
			return Datos1.menorTel[i];
		}
	}
	
	public static double getSep(int nCaso, int i, int j) {
		switch (nCaso) {
		case 1:
			return Datos1.sep[i][j];
		case 2:
			return Datos2.sep[i][j];
		case 3:
			return Datos3.sep[i][j];
		default:
			return Datos1.sep[i][j];
		}
	}

	public static int getNumVuelos(int nCaso) {
		switch (nCaso) {
		case 1:
			return Datos1.getNumVuelos();
		case 2:
			return Datos2.getNumVuelos();
		case 3:
			return Datos3.getNumVuelos();
		default:
			return Datos1.getNumVuelos();
		}
	}

	public static int getNumPistas(int nCaso) {
		switch (nCaso) {
		case 1:
			return Datos1.getNumPistas();
		case 2:
			return Datos2.getNumPistas();
		case 3:
			return Datos3.getNumPistas();
		default:
			return Datos1.getNumPistas();
		}
	}
}
