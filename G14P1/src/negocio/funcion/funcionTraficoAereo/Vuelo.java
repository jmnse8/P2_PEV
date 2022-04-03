package negocio.funcion.funcionTraficoAereo;

public class Vuelo {// clase con la información del vuelo
	public int numero;
	public String id;
	public char peso;
	public double tla;

	public Vuelo(int numero, String id, char peso) {
		super();
		this.numero = numero;
		this.id = id;
		this.peso = peso;
		tla = 0;
	}

	@Override
	public String toString() {
		String s = "";
		s += "|   " + numero;
		if (numero < 10)
			s += "   |";
		else if (numero < 100)
			s += "  |";
		else
			s += " |";

		int espBlancoLibres = 10 - id.length();// METE ID
		int espBlancoLibresMitad = espBlancoLibres / 2;
		for (int i = 0; i < espBlancoLibresMitad; i++)
			s += " ";
		s += id;
		for (int i = 0; i < 10 - id.length() - espBlancoLibresMitad; i++)
			s += " ";

		s += "|  " + tla;
		if (tla < 10)
			s += "  |";
		else if (tla < 100)
			s += " |";
		else
			s += "|";

		return s;
	}

}
