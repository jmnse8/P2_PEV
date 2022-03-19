package negocio.mutacion.factoriaMutacion.factoriaMutacionImp;

import negocio.mutacion.Mutacion;
import negocio.mutacion.MutacionEnum;
import negocio.mutacion.factoriaMutacion.FactoriaMutacion;

public class FactoriaMutacionImp extends FactoriaMutacion{

	@Override
	public Mutacion getMutacion(MutacionEnum mutacion) {
		return mutacion.getMutacion();
	}

}
