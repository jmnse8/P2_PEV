package negocio.funcion.funcionTraficoAereo;

import java.util.ArrayList;

public class Pista {
	private ArrayList<Vuelo> avionesEnPista;
	private int numeroPista;

	public Pista(int numeroPista) {
		avionesEnPista = new ArrayList<Vuelo>();
		this.numeroPista = numeroPista;
	}

	public double cuantoCuestaMeterEnPista(Vuelo vueloMeter) {
		
		if (avionesEnPista.isEmpty()) {
			return DatosTraficoAereo.tel[numeroPista - 1][vueloMeter.numero - 1];
		} else {
			double resta = Math.abs(avionesEnPista.get(avionesEnPista.size() - 1).tla - DatosTraficoAereo.tel[numeroPista - 1][vueloMeter.numero - 1]);
			double sep = DatosTraficoAereo.sep[pesoToInt(avionesEnPista.get(avionesEnPista.size() - 1).peso)][pesoToInt(vueloMeter.peso)];
			if(avionesEnPista.get(avionesEnPista.size() - 1).tla > DatosTraficoAereo.tel[numeroPista - 1][vueloMeter.numero - 1])
				return Double.MAX_VALUE;
			if(resta >= sep) {
				return DatosTraficoAereo.tel[numeroPista - 1][vueloMeter.numero - 1];
			}
			else {
				double tiempoAñadir = sep - resta;
				return DatosTraficoAereo.tel[numeroPista - 1][vueloMeter.numero - 1] + tiempoAñadir;
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
	public ArrayList<Vuelo> getAvionesEnPista(){
		return avionesEnPista;
	}
}
