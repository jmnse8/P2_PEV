package negocio.cruce;

import java.util.ArrayList;

import negocio.funcion.Funcion;

public interface Cruce {
	public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion, int porcentajeCruce);
}
