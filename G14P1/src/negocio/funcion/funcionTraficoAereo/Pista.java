package negocio.funcion.funcionTraficoAereo;

import java.util.ArrayList;

public class Pista {
	private ArrayList<Vuelo> avionesEnPista;
	private int numeroPista;

	public Pista(int numeroPista) {
		avionesEnPista = new ArrayList<Vuelo>();
		this.numeroPista = numeroPista;
	}

	public double cuantoCuestaMeterEnPista(Vuelo vueloMeter, int nCaso) {
		double telAdaptado = 0.0;
		if (avionesEnPista.isEmpty()) {// si esta vacia cuesta lo mismo que el tel
			return DatosTraficoAereo.getTel(nCaso, numeroPista - 1, vueloMeter.numero - 1);
		} else {
			if (avionesEnPista.get(avionesEnPista.size() - 1).tla > DatosTraficoAereo.getTel(nCaso, numeroPista - 1,
					vueloMeter.numero - 1))// si el anterior tiene más tla que el tel me lo guardo con el tla anterior
				telAdaptado = avionesEnPista.get(avionesEnPista.size() - 1).tla;
			else//si no si puede usar su tel
				telAdaptado = DatosTraficoAereo.getTel(nCaso, numeroPista - 1, vueloMeter.numero - 1);

			double resta = Math.abs(avionesEnPista.get(avionesEnPista.size() - 1).tla - telAdaptado);//diferencia entre el tla anterior y el telAdaptado
			double sep = DatosTraficoAereo.getSep(nCaso, pesoToInt(avionesEnPista.get(avionesEnPista.size() - 1).peso),
					pesoToInt(vueloMeter.peso));// sep que le correspondería acatar

			if (resta >= sep) {// si hay más diferencia que el sep
				return telAdaptado;// devuelvo el telAdaptado
			} else {
				double tiempoAñadir = sep - resta;
				return telAdaptado + tiempoAñadir;// si no le añado lo que hace falta para que lo cumpla
			}
		}
	}

	public void meteVuelo(Vuelo meterVuelo, double tla) {
		meterVuelo.tla = tla;
		avionesEnPista.add(meterVuelo);
	}

	public int getNPista() {
		return numeroPista;
	}

	public Vuelo getVuelo(int i) {
		return avionesEnPista.get(i);
	}

	public boolean hayVuelo(int i) {
		return (avionesEnPista.size() - 1 >= i) ? true : false;
	}

	private int pesoToInt(char p) {
		if (p == 'w')
			return 0;
		else if (p == 'g')
			return 1;
		else
			return 2;
	}

	public ArrayList<Vuelo> getAvionesEnPista() {
		return avionesEnPista;
	}
}
