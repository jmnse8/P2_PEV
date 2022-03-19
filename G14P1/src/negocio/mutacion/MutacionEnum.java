package negocio.mutacion;

import negocio.mutacion.mutacionBasica.MutacionBasica;
import negocio.mutacion.mutacionUniforme.MutacionUniforme;

public enum MutacionEnum {

	Mutacion_Basica(new MutacionBasica()),
	Mutacion_Uniforme(new MutacionUniforme());
	
	private Mutacion mutacion;
	
	private MutacionEnum(Mutacion m) {
		mutacion = m;
	}
	
	public Mutacion getMutacion() {
		return mutacion;
	}
}
