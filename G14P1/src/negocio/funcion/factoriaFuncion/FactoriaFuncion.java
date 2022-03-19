package negocio.funcion.factoriaFuncion;

import negocio.funcion.Funcion;
import negocio.funcion.FuncionEnum;
import negocio.funcion.factoriaFuncion.factoriaFuncionImp.FactoriaFuncionImp;

public abstract class FactoriaFuncion {

	private static FactoriaFuncion instance;

	public synchronized static FactoriaFuncion getInstance() {
		if (instance == null)
			instance = new FactoriaFuncionImp();
		return instance;
	}

	public abstract Funcion generaFuncion(FuncionEnum funcion, double intervalo);
}
