package negocio.funcion.funcionTraficoAereo;

public class ParejaPistaVuelo {// clase para asociar el tla conseguido por un vuelo en una pista y ver cual es menor
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
