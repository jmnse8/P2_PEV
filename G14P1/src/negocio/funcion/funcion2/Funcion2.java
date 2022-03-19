package negocio.funcion.funcion2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import negocio.funcion.Funcion;

public class Funcion2 implements Funcion, Cloneable {
	private ArrayList<Boolean> individuo;

	private double min1 = -10.0;
	private double max1 = 10.0;
	private int tamGen1;
	private int numGen = 2;
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
	private double fitness;

	public Funcion2(double intervalo) {
		tamGen1 = (int) (Math.log10(((max1 - min1) / intervalo) + 1) / Math.log10(2));
		individuo = new ArrayList<Boolean>();
		Random rnd = new Random();
		for (int i = 0; i < tamGen1 * numGen; i++) {
			individuo.add(rnd.nextBoolean());
		}
	}

	@Override
	public double getFitness() {
		return fitness;
	}

	@Override
	public void calculaFitness() {
		fitness = 1;
		double suma[] = new double[numGen];
		for (int i = 0; i < numGen; i++) {
			double x = getFenotipo(i);
			suma[i] = 0;
			for (int j = 1; j <= 5; j++) {
				suma[i] += (j * Math.cos((j + 1) * x + j));
			}
		}
		for (int i = 0; i < numGen; i++) {
			fitness *= suma[i];
		}
	}

	private double getFenotipo(int it) {
		String aux = "";
		for (int i = it * tamGen1; i < (tamGen1 * (it + 1)); i++)
			aux += (individuo.get(i)) ? "1" : "0";
		return (min1 + (Integer.parseInt(aux, 2) * ((max1 - min1) / Math.pow(2, tamGen1))));
	}

	@Override
	public Object getIndividuo() {
		ArrayList<Boolean> aux = new ArrayList<Boolean>();
		for (Boolean b : individuo) {
			boolean a = (b) ? true : false;
			aux.add(a);
		}
		return aux;
	}

	@Override
	public Comparator<Funcion> getComp() {
		return comp;
	}

	@Override
	public int getTam() {
		return tamGen1 * numGen;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setIndividuo(Object individuo) {
		ArrayList<Boolean> i = (ArrayList<Boolean>) individuo;
		ArrayList<Boolean> aux = new ArrayList<Boolean>();
		for (Boolean b : i) {
			boolean a = (b) ? true : false;
			aux.add(a);
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
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		String aux = "";
		aux += "\n  - Fitness: " + String.format("%.3f", fitness);
		for (int i = 0; i < numGen; i++) {
			double x = getFenotipo(i);
			aux += "\n  - x" + i + ": " + String.format("%.3f", x);
		}
		return aux;
	}

}
