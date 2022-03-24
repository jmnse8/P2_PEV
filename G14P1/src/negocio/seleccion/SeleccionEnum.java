package negocio.seleccion;

import negocio.seleccion.seleccionRestos.SeleccionRestos;
import negocio.seleccion.seleccionEstocasticaUniversal.SeleccionEstocasticaUniversal;
import negocio.seleccion.seleccionRuleta.SeleccionRuleta;
import negocio.seleccion.seleccionTorneoDeterministico.SeleccionTorneoDeterministico;
import negocio.seleccion.seleccionTorneoProbabilistico.SeleccionTorneoProbabilistico;
import negocio.seleccion.seleccionTruncamiento.SeleccionTruncamiento;
import negocio.seleccion.seleccionRanking.SeleccionRanking;


public enum SeleccionEnum {
	Seleccion_Restos(new SeleccionRestos()),
	Seleccion_Estocastica_Universal(new SeleccionEstocasticaUniversal()),
	Seleccion_Ruleta(new SeleccionRuleta()),
	Seleccion_Torneo_Deterministico(new SeleccionTorneoDeterministico()),
	Seleccion_Torneo_Probabilistico(new SeleccionTorneoProbabilistico()),
	Seleccion_Truncamiento(new SeleccionTruncamiento()),
	Seleccion_Ranking(new SeleccionRanking());

	
	
	private Seleccion seleccion;
	
	private SeleccionEnum(Seleccion s) {
		seleccion = s;
	}

	public Seleccion getSeleccion() {
		return seleccion;
	}
}
