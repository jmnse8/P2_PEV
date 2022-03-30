package negocio.cruce;

import negocio.cruce.cruceAritmetico.CruceAritmetico;
import negocio.cruce.cruceBLX.CruceBLX;
import negocio.cruce.cruceMonopunto.CruceMonopunto;
import negocio.cruce.cruceUniforme.CruceUniforme;
import negocio.cruce.crucePMX.CrucePMX;
import negocio.cruce.cruceCX.CruceCX;
import negocio.cruce.cruceCO.CruceCO;
import negocio.cruce.cruceOX.CruceOX;
import negocio.cruce.cruceOX.CruceOXPP;
import negocio.cruce.cruceOX.CruceOXOP;

public enum CruceEnum {
	Cruce_Arimetico(new CruceAritmetico()),
	Cruce_BLX(new CruceBLX()),
	Cruce_Monopunto(new CruceMonopunto()),
	Cruce_Uniforme(new CruceUniforme()),
	Cruce_CO(new CruceCO()),
	Cruce_CX(new CruceCX()),
	Cruce_PMX(new CrucePMX()),
	Cruce_OX(new CruceOX()),
	Cruce_OXPP(new CruceOXPP()),
	Cruce_OXOP(new CruceOXOP());
	
	private Cruce cruce;
	
	private CruceEnum(Cruce c) {
		cruce = c;
	}
	
	public Cruce getCruce() {
		return cruce;
	}
}
