package negocio.cruce.factoriaCruce;

import negocio.cruce.Cruce;
import negocio.cruce.CruceEnum;
import negocio.cruce.factoriaCruce.factoriaCruceImp.FactoriaCruceImp;

public abstract class FactoriaCruce {

	private static FactoriaCruce instance;

	public synchronized static FactoriaCruce getInstance() {
		if (instance == null)
			instance = new FactoriaCruceImp();
		return instance;
	}

	public abstract Cruce getCruce(CruceEnum cruce);
}
