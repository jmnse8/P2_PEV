package negocio.funcion.funcionTraficoAereo;

//import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

//import javax.swing.JOptionPane;
//import javax.swing.plaf.FontUIResource;

import negocio.funcion.Funcion;

public class FuncionTraficoAereo implements Funcion, Cloneable {

	private ArrayList<Integer> individuo;
	private Vuelo[] vuelos;
	private double fitness;
	private ArrayList<Pista> pistas;

	final static Comparator<Funcion> comp = new Comparator<Funcion>() {
		@Override
		public int compare(Funcion o1, Funcion o2) {
			Double aux = o1.getFitness(), aux2 = o2.getFitness();
			int sol = aux.compareTo(aux2);
			if (sol > 0)
				return 1;
			else if (sol == 0)
				return 0;
			else
				return -1;
		}
	};
	private final static Comparator<ParejaPistaVuelo> compPV = new Comparator<ParejaPistaVuelo>() {
		@Override
		public int compare(ParejaPistaVuelo o1, ParejaPistaVuelo o2) {
			if (o1.tla > o2.tla)
				return 1;
			else if (o1.tla < o2.tla)
				return -1;
			else
				return 0;
		}
	};

	public FuncionTraficoAereo() {
		individuo = new ArrayList<Integer>();

		// individuo.add(8);individuo.add(12);individuo.add(11);individuo.add(4);individuo.add(3);individuo.add(10);individuo.add(5);
		// individuo.add(6);individuo.add(7);individuo.add(9);individuo.add(1);individuo.add(2);

		// individuo.add(8);individuo.add(9);individuo.add(10);individuo.add(11);individuo.add(12);individuo.add(7);individuo.add(6);
		// individuo.add(5);individuo.add(4);individuo.add(3);individuo.add(2);individuo.add(1);

		Random rd = new Random();
		int nVuelos = DatosTraficoAereo.getNumVuelos();

		for (int i = 0; i < nVuelos; i++) {
			int numero = rd.nextInt(nVuelos);
			if (!individuo.contains(numero))
				individuo.add(numero + 1);
		}
		pistas = new ArrayList<Pista>();
		for (int i = 1; i <= DatosTraficoAereo.getNumPistas(); i++) {
			pistas.add(new Pista(i));
		}
	}

	@Override
	public double getFitness() {
		return fitness;
	}

	@Override
	public void calculaFitness() {
		pistas = new ArrayList<Pista>();
		vuelos = DatosTraficoAereo.getVuelos();
		for (int i = 1; i <= DatosTraficoAereo.getNumPistas(); i++) {
			pistas.add(new Pista(i));
		}
		for (Integer ent : individuo) {
			ArrayList<ParejaPistaVuelo> mejor = new ArrayList<ParejaPistaVuelo>();
			for (Pista p : pistas) {
				mejor.add(new ParejaPistaVuelo(p.getNPista(), p.cuantoCuestaMeterEnPista(vuelos[ent - 1])));
			}
			Collections.sort(mejor, compPV);
			pistas.get(mejor.get(0).numeroPista - 1).meteVuelo(vuelos[ent - 1], mejor.get(0).tla);
		}
		double suma = 0.0;
		for (Pista p : pistas) {
			for (Vuelo v : p.getAvionesEnPista()) {
				suma += Math.pow((v.tla - DatosTraficoAereo.menorTel[v.numero - 1]), 2);

			}
		}
		fitness = suma;
	}

	@Override
	public Object getIndividuo() {
		ArrayList<Integer> aux = new ArrayList<Integer>();
		for (Integer b : individuo) {
			aux.add(b);
		}
		return aux;
	}

	@Override
	public Comparator<Funcion> getComp() {
		return comp;
	}

	@Override
	public int getTam() {
		return DatosTraficoAereo.getNumVuelos();
	}

	@Override
	public void setIndividuo(Object individuo) {
		@SuppressWarnings("unchecked")
		ArrayList<Integer> i = (ArrayList<Integer>) individuo;
		ArrayList<Integer> aux = new ArrayList<Integer>();
		for (Integer b : i) {
			aux.add(b);
		}
		this.individuo = aux;
	}

	@Override
	public boolean getMax() {
		return false;
	}

	@Override
	public Funcion getCopy() {
		Funcion aux = null;
		try {
			aux = (Funcion) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return aux;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		String sol = "   _____ ____  _   _ _______ _____   ____  _         _____  ______    _______ _____           ______ _____ _____ ____              ______ _____  ______ ____  \r\n"
				+ "  / ____/ __ \\| \\ | |__   __|  __ \\ / __ \\| |       |  __ \\|  ____|  |__   __|  __ \\    /\\   |  ____|_   _/ ____/ __ \\       /\\   |  ____|  __ \\|  ____/ __ \\ \r\n"
				+ " | |   | |  | |  \\| |  | |  | |__) | |  | | |       | |  | | |__        | |  | |__) |  /  \\  | |__    | || |   | |  | |     /  \\  | |__  | |__) | |__ | |  | |\r\n"
				+ " | |   | |  | | . ` |  | |  |  _  /| |  | | |       | |  | |  __|       | |  |  _  /  / /\\ \\ |  __|   | || |   | |  | |    / /\\ \\ |  __| |  _  /|  __|| |  | |\r\n"
				+ " | |___| |__| | |\\  |  | |  | | \\ \\| |__| | |____   | |__| | |____      | |  | | \\ \\ / ____ \\| |     _| || |___| |__| |   / ____ \\| |____| | \\ \\| |___| |__| |\r\n"
				+ "  \\_____\\____/|_| \\_|  |_|  |_|  \\_\\\\____/|______|  |_____/|______|     |_|  |_|  \\_/_/    \\_|_|    |_____\\_____\\____/   /_/    \\_|______|_|  \\_|______\\____/ \r\n"
				+ "                                                                                                                                                              \r\n"
				+ "                                                                                                                                                              \r\n";
		int tablaMasLarga = -1;
		for (int i = 0; i < 5; i++) {
			for (Pista p : pistas) {
				if (i == 0) {
					if (p.getAvionesEnPista().size() > tablaMasLarga)
						tablaMasLarga = p.getAvionesEnPista().size();
					sol += "               __________________________ ";
				} else if (i == 1) {
					sol += "              |          TABLA " + p.getNPista() + "         |";
				} else if (i == 2) {
					sol += "              |--------------------------|";
				} else if (i == 3) {
					sol += "              | vuelo |  nombre  |  TLA  |";
				} else {
					sol += "              |--------------------------|";
				}
			}
			sol += "\n";
		}

		for (int i = 0; i < tablaMasLarga; i++) {
			sol += "              ";
			if (pistas.get(0).hayVuelo(i))
				sol += pistas.get(0).getVuelo(i);
			else
				sol += "|                          |";
			sol += "              ";
			if (pistas.get(1).hayVuelo(i))
				sol += pistas.get(1).getVuelo(i);
			else
				sol += "|                          |";
			sol += "              ";
			if (pistas.get(2).hayVuelo(i))
				sol += pistas.get(2).getVuelo(i);
			else
				sol += "|                          |";
			sol += "\n";
		}
		for (Pista p : pistas) {
			sol += "              |__________________________|";
		}
		sol += "\n\n                 EL FITNESS RESULTANTE ES " + fitness + ".\n";
		return sol;

	}
/*
	public static void main(String[] args) {
		javax.swing.UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Consolas", Font.PLAIN, 11)));
		FuncionTraficoAereo f = new FuncionTraficoAereo();
		f.calculaFitness();
		JOptionPane.showMessageDialog(null, "La mejor solución ha sido:\n" + f.toString(), "Solución",
				JOptionPane.PLAIN_MESSAGE);
	}*/

}