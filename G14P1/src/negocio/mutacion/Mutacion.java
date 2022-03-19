package negocio.mutacion;

import java.util.ArrayList;

import negocio.funcion.Funcion;

public interface Mutacion {
	public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion, int porcentajeMutacion);
}
