package negocio.funcion.funcionTraficoAereo;

public class ParejaPistaVuelo {
	public int numeroPista;
	public double tla;
	public ParejaPistaVuelo(int numeroPista, double tla) {
		this.numeroPista = numeroPista;
		this.tla = tla;
	}
	@Override
	public String toString() {
		return "ParejaPistaVuelo [numeroPista=" + numeroPista + ", tla=" + tla + "]";
	}
	
	
}
