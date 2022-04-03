package negocio.seleccion.seleccionEstocasticaUniversal;

import java.util.ArrayList;
import java.util.Random;

import negocio.funcion.Funcion;
import negocio.seleccion.Seleccion;

public class SeleccionEstocasticaUniversal implements Seleccion{

	@Override
    public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion, double presion) {
        // TODO Auto-generated method stub
		int tam_pob = poblacion.size();
        double sumaFitness = 0;
        double fMax = Double.MIN_VALUE;
        double fMin = Double.MAX_VALUE;
        ArrayList<Double> prob = new ArrayList<Double>();
        ArrayList<Double> puntuacion = new ArrayList<Double>();
        ArrayList<Funcion> seleccion = new ArrayList<Funcion>();
        boolean maximize = poblacion.get(0).getMax();

        Random rnd = new Random();

        for (int i = 0; i < tam_pob; i++){
        	double fitness = poblacion.get(i).getFitness();
        	if (fitness > fMax) fMax = fitness;
        	if (fitness < fMin) fMin = fitness;
        }
        
        if (fMax > 0) fMax *= 1.05;
        else fMax /= 1.05;
        
        for (int i = 0; i < tam_pob; i++){
        	if (!maximize) {
            double adaptada = fMax - poblacion.get(i).getFitness();
            sumaFitness += adaptada;
            puntuacion.add(adaptada);
        	}else {
        		double adaptada = fMin + poblacion.get(i).getFitness();
                sumaFitness += adaptada;
                puntuacion.add(adaptada);
        	}
        }
        
        for (int i = 0; i < tam_pob; i++){
            prob.add(puntuacion.get(i) / sumaFitness);
        }
        
        double diff = (double)1 / (double)tam_pob;
        double ini = (rnd.nextDouble() * diff);
        
        for (int i = 0; i < tam_pob; i++){
            double next =(double) (i*diff) + ini;
            Funcion aux = poblacion.get(getSelectedPosition(prob,next)).getCopy();
            seleccion.add(aux);
        }
        
        return seleccion;
    }

    private int getSelectedPosition(ArrayList<Double> probs, double rnd){

        double act = 0;
        int i = -1;//Cambiado a 0
        while(act <= rnd){
            act += probs.get(++i);
        }

        return i;
    }
}
