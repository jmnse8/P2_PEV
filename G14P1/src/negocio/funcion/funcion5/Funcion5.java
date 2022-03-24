package negocio.funcion.funcion5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import negocio.funcion.Funcion;
import negocio.funcion.funcionTraficoAereo.Pista;
import presentacion.mainFrame.MainFrame;

public class Funcion5 implements Funcion, Cloneable {
	ArrayList<Double> individuo;
	private double max1 = Math.PI;
	private int n;
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
	

	public Funcion5(double intervalo) {
		n = MainFrame.getInstance().getNFuncion();
		individuo = new ArrayList<Double>();
		Random rd = new Random();
		for(int i = 0; i < n;i++) {
			individuo.add(rd.nextDouble() * max1);
		}
	}

	@Override
	public double getFitness() {
		return fitness;
	}
	
	@Override
	public void calculaFitness() {
		double suma = 0;
		for (int i = 0; i < n; i++) {
			double x = getFenotipo(i);
			suma += (Math.sin(x) * Math.sin(Math.pow(((i + 1) * Math.pow(x, 2)) / Math.PI, 20)));
		}
		fitness = -suma;
	}

	private double getFenotipo(int it) {
		double aux = individuo.get(it);
		return aux;
	}

	@Override
	public Object getIndividuo() {
		ArrayList<Double> aux = new ArrayList<Double>();
		for (Double b : individuo) {
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
		return n;
	}

	@Override
	public void setIndividuo(Object individuo) {
		@SuppressWarnings("unchecked")
		ArrayList<Double> i = (ArrayList<Double>) individuo;
		ArrayList<Double> aux = new ArrayList<Double>();
		for (Double b : i) {
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
		String aux = "";
		aux += "\n  - Fitness: " + String.format("%.3f", fitness);
		for (int i = 0; i < n; i++) {
			double x = getFenotipo(i);
			aux += "\n  - x" + i + ": " + String.format("%.3f", x);
		}
		return aux;
	}
}
