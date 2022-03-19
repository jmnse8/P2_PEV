package negocio.funcion;

import java.util.Comparator;

public interface Funcion {
	public double getFitness();
	public void calculaFitness();
	public Object getIndividuo();
	public Comparator<Funcion> getComp();
	public int getTam();
	public void setIndividuo(Object individuo);
	public boolean getMax();
	public Funcion getCopy();
}
