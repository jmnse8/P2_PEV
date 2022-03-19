package negocio.seleccion.factoriaSeleccion.factoriaSeleccionImp;

import negocio.seleccion.Seleccion;
import negocio.seleccion.SeleccionEnum;
import negocio.seleccion.factoriaSeleccion.FactoriaSeleccion;

public class FactoriaSeleccionImp extends FactoriaSeleccion{

	@Override
	public Seleccion getSeleccion(SeleccionEnum seleccion) {
		return seleccion.getSeleccion();
	}

}
