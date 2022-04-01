package negocio.mutacion;

import negocio.mutacion.mutacionInsercion.MutacionInsercion;
import negocio.mutacion.mutacionIntercambio.MutacionIntercambio;
import negocio.mutacion.mutacionHeuristica.MutacionHeuristica;
import negocio.mutacion.mutacionInversion.MutacionInversion;

public enum MutacionEnum {

	Mutacion_Insercion(new MutacionInsercion()),
	Mutacion_Intercambio(new MutacionIntercambio()),
	Mutacion_Heuristica(new MutacionHeuristica()),
	Mutacion_Inversion(new MutacionInversion());
	
	private Mutacion mutacion;
	
	private MutacionEnum(Mutacion m) {
		mutacion = m;
	}
	
	public Mutacion getMutacion() {
		return mutacion;
	}
}
