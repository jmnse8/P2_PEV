package negocio.cruce.factoriaCruce.factoriaCruceImp;

import negocio.cruce.Cruce;
import negocio.cruce.CruceEnum;
import negocio.cruce.factoriaCruce.FactoriaCruce;

public class FactoriaCruceImp extends FactoriaCruce{

	@Override
	public Cruce getCruce(CruceEnum cruce) {
		return cruce.getCruce();
	}

}
