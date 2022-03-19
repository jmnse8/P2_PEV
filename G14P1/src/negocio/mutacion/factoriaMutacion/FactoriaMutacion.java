package negocio.mutacion.factoriaMutacion;

import negocio.mutacion.Mutacion;
import negocio.mutacion.MutacionEnum;
import negocio.mutacion.factoriaMutacion.factoriaMutacionImp.FactoriaMutacionImp;

public abstract class FactoriaMutacion {

	private static FactoriaMutacion instance;

	public synchronized static FactoriaMutacion getInstance() {
		if (instance == null)
			instance = new FactoriaMutacionImp();
		return instance;
	}

	public abstract Mutacion getMutacion(MutacionEnum mutacion);
}
