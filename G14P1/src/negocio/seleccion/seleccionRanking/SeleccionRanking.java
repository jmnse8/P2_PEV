package negocio.seleccion.seleccionRanking;

import java.util.ArrayList;

import negocio.funcion.Funcion;
import negocio.seleccion.Seleccion;

import java.util.Random; 

public class SeleccionRanking implements Seleccion{

    @Override
    public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion, double presion) {
        // TODO Auto-generated method stub
    	int tam_pob = poblacion.size();
        //double sumaFitness = 0;
        //double fMax = Double.MIN_NORMAL;
        ArrayList<Double> prob = new ArrayList<Double>();
        //ArrayList<Double> puntuacion = new ArrayList<Double>();
        ArrayList<Funcion> seleccion = new ArrayList<Funcion>();

        Random rnd = new Random();
        
        double beta = presion; //Habría que pasarle la presion selectiva aqui

        for (int i = 0; i < tam_pob; i++){
        	double probabilidad = (1.0 / (double)tam_pob) * (beta -(2.0 * (beta - 1.0) * (double)i / (double)(tam_pob - 1)));
        	prob.add(i, probabilidad);
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
