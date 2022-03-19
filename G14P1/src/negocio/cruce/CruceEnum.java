package negocio.cruce;

import negocio.cruce.cruceAritmetico.CruceAritmetico;
import negocio.cruce.cruceBLX.CruceBLX;
import negocio.cruce.cruceMonopunto.CruceMonopunto;
import negocio.cruce.cruceUniforme.CruceUniforme;

public enum CruceEnum {
	Cruce_Arimetico(new CruceAritmetico()),
	Cruce_BLX(new CruceBLX()),
	Cruce_Monopunto(new CruceMonopunto()),
	Cruce_Uniforme(new CruceUniforme());
	
	private Cruce cruce;
	
	private CruceEnum(Cruce c) {
		cruce = c;
	}
	
	public Cruce getCruce() {
		return cruce;
	}
}
