package negocio.funcion.funcionTraficoAereo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
//import javax.swing.JOptionPane;
//import javax.swing.plaf.FontUIResource;

import negocio.funcion.Funcion;
import presentacion.mainFrame.MainFrame;

public class FuncionTraficoAereo implements Funcion, Cloneable {

	private ArrayList<Integer> individuo;// individuo array de enteros con orden de vuelos
	private Vuelo[] vuelos;// información de los vuelos
	private double fitness;
	private ArrayList<Pista> pistas;//Array de listas
	private int nCaso;//número de caso elegido a ejecutar

	final static Comparator<Funcion> comp = new Comparator<Funcion>() {// comparador de esta función mediante el fitness
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
	private final static Comparator<ParejaPistaVuelo> compPV = new Comparator<ParejaPistaVuelo>() { //comprador de ParejaPistaVuelo mediante el tla conseguido
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
		individuo = new ArrayList<Integer>();// inicializo individuo
		nCaso = MainFrame.getInstance().getNFuncion();// cojo nº caso
		
		Random rd = new Random();
		int nVuelos = DatosTraficoAereo.getNumVuelos(nCaso);
		int cont = 0;
		while (cont < nVuelos) {// meto los nº del individuo mediante aleatorios
			int numero = rd.nextInt(nVuelos);
			if (!individuo.contains(numero + 1)) {
				individuo.add(numero + 1);
				cont++;
			}
		}
		pistas = new ArrayList<Pista>();// inicializo las pistas
		for (int i = 1; i <= DatosTraficoAereo.getNumPistas(nCaso); i++) {
			pistas.add(new Pista(i));
		}
	}

	@Override
	public double getFitness() {
		return fitness;
	}

	@Override
	public void calculaFitness() {
		pistas = new ArrayList<Pista>();// inicializo las pistas
		vuelos = DatosTraficoAereo.getVuelos(nCaso);
		for (int i = 1; i <= DatosTraficoAereo.getNumPistas(nCaso); i++) {
			pistas.add(new Pista(i));
		}
		for (Integer ent : individuo) {// recorro todos los individuos
			ArrayList<ParejaPistaVuelo> mejor = new ArrayList<ParejaPistaVuelo>();// array ordenado con la mejor pista posible
			for (Pista p : pistas) {// calculo el tla de cada pista
				mejor.add(new ParejaPistaVuelo(p.getNPista(), p.cuantoCuestaMeterEnPista(vuelos[ent - 1], nCaso)));
			}
			Collections.sort(mejor, compPV);// ordeno las posibilidades por el menor tla
			pistas.get(mejor.get(0).numeroPista - 1).meteVuelo(vuelos[ent - 1], mejor.get(0).tla);// meto el que ha salido menor
		}
		
		double suma = 0.0;// calculo el fitness con la suma de los cuadrados de la diferencia entre el tla conseguido y su menor tel posible
		for (Pista p : pistas) {
			for (Vuelo v : p.getAvionesEnPista()) {
				suma += Math.pow((v.tla - DatosTraficoAereo.getMenorTel(nCaso,v.numero - 1)), 2);// suma += (tla - menorTel)^2
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
		return DatosTraficoAereo.getNumVuelos(nCaso);
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
	public String toString() {// dibujo mediante un string los datos de la solución
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
					sol += "              |          PISTA " + p.getNPista() + "         |";
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
			for(Pista p:pistas) {
				sol += "              ";
				if (p.hayVuelo(i))
					sol += p.getVuelo(i);
				else
					sol += "|                          |";
			}
			sol += "\n";
		}
		for (Pista p : pistas) {
			sol += "              |__________________________|";
		}
		sol += "\n\n                 EL FITNESS RESULTANTE ES " + fitness + ".\n\n";
		sol += "                EL INDIVIDUO HA SIDO "	+ individuo.toString() + ".";
		return sol;
	}
	/*
	  public static void main(String[] args) {
	  javax.swing.UIManager.put("OptionPane.messageFont", new FontUIResource(new java.awt.Font("Consolas", java.awt.Font.PLAIN, 11)));
	  FuncionTraficoAereo f = new
	  FuncionTraficoAereo(); f.calculaFitness();
	  JOptionPane.showMessageDialog(null, "La mejor solución ha sido:\n" +
	  f.toString(), "Solución", JOptionPane.PLAIN_MESSAGE); }
	 
*/
}