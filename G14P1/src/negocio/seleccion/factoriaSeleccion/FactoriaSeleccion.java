package negocio.seleccion.factoriaSeleccion;

import negocio.seleccion.Seleccion;
import negocio.seleccion.SeleccionEnum;
import negocio.seleccion.factoriaSeleccion.factoriaSeleccionImp.FactoriaSeleccionImp;

public abstract class FactoriaSeleccion {

	private static FactoriaSeleccion instance;

	public synchronized static FactoriaSeleccion getInstance() {
		if (instance == null)
			instance = new FactoriaSeleccionImp();
		return instance;
	}

	public abstract Seleccion getSeleccion(SeleccionEnum seleccion);
}
