package negocio.seleccion;

import java.util.ArrayList;

import negocio.funcion.Funcion;

public interface Seleccion {
	public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion, double presion);
}
