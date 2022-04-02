package negocio.seleccion.seleccionTruncamiento;

import java.util.ArrayList;
import java.util.Collections;

import negocio.funcion.Funcion;
import negocio.seleccion.Seleccion;

public class SeleccionTruncamiento implements Seleccion{
	
	//Habrá que ver como meter esta variable
	int trunc = 50;

	@Override
	public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion, double presion) {
		// TODO Auto-generated method stub
		int tam_pob = poblacion.size();
		int distintos = trunc * tam_pob / 100;
		int copias = tam_pob / distintos;
		int restos = tam_pob % distintos;
		ArrayList<Funcion> seleccion;
        seleccion = new ArrayList<Funcion>();
		Collections.sort(poblacion, poblacion.get(0).getComp());
		
		for (int i = 0; i < distintos; i++) {
			for (int j = 0; j < copias; j++) {
				seleccion.add(poblacion.get(i).getCopy());
			}
			
			if (i < restos)
				seleccion.add(poblacion.get(i).getCopy());
		}
		
		return seleccion;
	}


}
