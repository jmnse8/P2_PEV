package negocio.mutacion;

import negocio.mutacion.mutacionBasica.MutacionBasica;
import negocio.mutacion.mutacionUniforme.MutacionUniforme;
import negocio.mutacion.mutacionInsercion.MutacionInsercion;
import negocio.mutacion.mutacionIntercambio.MutacionIntercambio;
import negocio.mutacion.mutacionHeuristica.MutacionHeuristica;
import negocio.mutacion.mutacionInversion.MutacionInversion;

public enum MutacionEnum {

	Mutacion_Basica(new MutacionBasica()),
	Mutacion_Uniforme(new MutacionUniforme()),
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
