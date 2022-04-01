package presentacion.filtro;
/*
import negocio.cruce.CruceEnum;
import negocio.funcion.FuncionEnum;
import negocio.mutacion.MutacionEnum;
import presentacion.mainFrame.MainFrame;*/

public class Filtro {

	public Filtro() {

	}

	public static boolean aplicaFiltro() {
		/*MainFrame mF = MainFrame.getInstance();
		CruceEnum cruceTipo = mF.getCruce();
		MutacionEnum mutacionTipo = mF.getMutacion();
		FuncionEnum funcionTipo = mF.getFuncion();
		*/

		return true;
	}
	/*
	private static boolean compruebaMutacion(int fT, MutacionEnum mutacionTipo) {
		if(fT == 1) {
			if(mutacionTipo == MutacionEnum.Mutacion_Basica) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(mutacionTipo == MutacionEnum.Mutacion_Uniforme) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	private static boolean compruebaCruce(int fT,CruceEnum cruceTipo) {
		if(fT == 1) {
			if(cruceTipo == CruceEnum.Cruce_Monopunto || cruceTipo == CruceEnum.Cruce_Uniforme) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return true;
		}
		return true;
	}*/
}
