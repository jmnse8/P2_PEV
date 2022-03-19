package negocio.funcion.funcion3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import negocio.funcion.Funcion;

public class Funcion3 implements Funcion, Cloneable {
	private ArrayList<Boolean> individuo;

	private double min1 = -512.0;
	private double min2 = -512.0;
	private double max1 = 512.0;
	private double max2 = 512.0;
	private int tamGen1;
	private int tamGen2;
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

	public Funcion3(double intervalo) {
		tamGen1 = (int) (Math.log10(((max1 - min1) / intervalo) + 1) / Math.log10(2));
		tamGen2 = (int) (Math.log10(((max2 - min2) / intervalo) + 1) / Math.log10(2));
		individuo = new ArrayList<Boolean>();
		Random rnd = new Random();
		for (int i = 0; i < tamGen1 + tamGen2; i++) {
			individuo.add(rnd.nextBoolean());
		}
		
	}

	@Override
	public double getFitness() {
		return fitness;
	}

	@Override
	public void calculaFitness() {
		double x1 = getFenotipo1(), x2 = getFenotipo2();
		fitness = (((-(x2 + 47)) * Math.sin(Math.sqrt(Math.abs(x2 + (x1 / 2) + 47))))
				- (x1 * Math.sin(Math.sqrt(Math.abs(x1 - (x2 + 47))))));
	}

	private double getFenotipo2() {
		String aux = "";
		for (int i = tamGen1; i < tamGen1 + tamGen2; i++)
			aux += (individuo.get(i)) ? "1" : "0";
		return (min2 + (Integer.parseInt(aux, 2) * ((max2 - min2) / Math.pow(2, tamGen2))));
	}

	private double getFenotipo1() {
		String aux = "";
		for (int i = 0; i < tamGen1; i++)
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
		return tamGen1 + tamGen2;
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
		return "\n  - Fitness: " + String.format("%.3f",fitness)+ "\n  - x1: " + String.format("%.3f",getFenotipo1()) + "\n  - x2: " + String.format("%.3f",getFenotipo2()) ;
	}
}
