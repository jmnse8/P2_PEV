package negocio.funcion.factoriaFuncion.factoriaFuncionImp;

import negocio.funcion.Funcion;
import negocio.funcion.FuncionEnum;
import negocio.funcion.factoriaFuncion.FactoriaFuncion;
import negocio.funcion.funcion1.Funcion1;
import negocio.funcion.funcion2.Funcion2;
import negocio.funcion.funcion3.Funcion3;
import negocio.funcion.funcion4.Funcion4;
import negocio.funcion.funcion5.Funcion5;

public class FactoriaFuncionImp extends FactoriaFuncion{

	@Override
	public Funcion generaFuncion(FuncionEnum funcion, double intervalo) {
		switch (funcion) {
		case Funcion_1:
			return new Funcion1(intervalo);
		case Funcion_2:
			return new Funcion2(intervalo);
		case Funcion_3:
			return new Funcion3(intervalo);
		case Funcion_4:
			return new Funcion4(intervalo);
		case Funcion_5:
			return new Funcion5(intervalo);
		default:
			return new Funcion1(intervalo);
		}
	}

}
