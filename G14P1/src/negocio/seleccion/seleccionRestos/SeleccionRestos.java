package negocio.seleccion.seleccionRestos;

import java.util.ArrayList;
import java.util.Random;

import negocio.funcion.Funcion;
import negocio.seleccion.Seleccion;

public class SeleccionRestos implements Seleccion{

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
        
        int sel = tam_pob/10;
        
        for (int i = 0; i < tam_pob; i++){
           if ((prob.get(i) * sel) >= 1) {
               seleccion.add(poblacion.get(i).getCopy());
               poblacion.remove(i);
               i--;
               tam_pob--;
           }
        }
        
        // Ajuste de la nueva probabilidad
        double totalProb = 0;
        
        for (int i = 0; i < tam_pob; i++){
            totalProb += prob.get(i);
        }
        
        double ajuste = (double)1/totalProb;
        for (int i = 0; i < tam_pob; i++){
            prob.set(i, prob.get(i) * ajuste);
        }
        
        for (int i = 0; i < tam_pob; i++){
            double random = rnd.nextDouble();//Meter un numero aleatorio entre 0 y 1
            seleccion.add(poblacion.get(getSelectedPosition(prob,random)).getCopy());
        }
		
		return seleccion;
	}
	
	private int getSelectedPosition(ArrayList<Double> probs, double rnd){

        double act = 0;
        int i = -1;
        while(act < rnd){
            i++;
            act += probs.get(i);
        }

        return i;
    }

}
