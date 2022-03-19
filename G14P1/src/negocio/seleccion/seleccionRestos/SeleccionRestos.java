package negocio.seleccion.seleccionRestos;

import java.util.ArrayList;
import java.util.Random;

import negocio.funcion.Funcion;
import negocio.seleccion.Seleccion;

public class SeleccionRestos implements Seleccion{

	@Override
	public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion) {
		// TODO Auto-generated method stub
		int tam_pob = poblacion.size();
        double sumaFitness = 0;
        boolean max = poblacion.get(0).getMax();
        int mod;
        double minFitness = Double.MIN_NORMAL;
        ArrayList<Double> prob = new ArrayList<Double>();
        ArrayList<Funcion> seleccion = new ArrayList<Funcion>();

        Random rnd = new Random();
        
        if (max) mod = 1;
        else mod = -1;

        for (int i = 0; i < tam_pob; i++){
        	double fitness = (poblacion.get(i).getFitness()) * mod;
        	if (fitness < minFitness) minFitness = fitness;
            sumaFitness += fitness;
        }
        
        sumaFitness += (-minFitness + 1) * tam_pob;
        
        double totalProb = 0;
        
        for (int i = 0; i < tam_pob; i++){
            double probabilidad =(poblacion.get(i).getFitness() - minFitness + 1)/ sumaFitness;
            prob.add(probabilidad);
            totalProb += probabilidad;
        }
        
        double ajuste = (double)1/totalProb;
        for (int i = 0; i < tam_pob; i++){
            prob.set(i, prob.get(i) * ajuste);
        }
        
        int sel = tam_pob/10;
        
        for (int i = 0; i < tam_pob; i++){
           if (prob.get(i) * sel >= 1) {
               seleccion.add(poblacion.get(i).getCopy());
               poblacion.remove(i);
               i--;
               tam_pob--;
           }
        }
        
        // Ajuste de la nueva probabilidad
        totalProb = 0;
        
        for (int i = 0; i < tam_pob; i++){
            totalProb += prob.get(i);
        }
        
        ajuste = (double)1/totalProb;
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
