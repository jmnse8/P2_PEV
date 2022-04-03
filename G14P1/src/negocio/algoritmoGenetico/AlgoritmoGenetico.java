package negocio.algoritmoGenetico;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import org.math.plot.Plot2DPanel;

import negocio.cruce.Cruce;
import negocio.cruce.factoriaCruce.FactoriaCruce;
import negocio.funcion.Funcion;
import negocio.funcion.FuncionEnum;
import negocio.funcion.factoriaFuncion.FactoriaFuncion;
import negocio.mutacion.Mutacion;
import negocio.mutacion.factoriaMutacion.FactoriaMutacion;
import negocio.seleccion.Seleccion;
import negocio.seleccion.factoriaSeleccion.FactoriaSeleccion;
import presentacion.mainFrame.MainFrame;

public class AlgoritmoGenetico {

	private MainFrame mF;

	private double intervalo;

	private int tamPoblacion;
	private int numGeneraciones;

	private Seleccion seleccion;

	private Cruce cruce;
	private int porcentajeCruce;

	private Mutacion mutacion;
	private int porcentajeMutacion;

	private FuncionEnum funcionTipo;

	private boolean elitismoSeleccionado;
	private int porcentajeElitismo;
	private int numElite;
	private ArrayList<Funcion> elite;

	private ArrayList<Funcion> poblacion;
	private double[] mejorAbsoluto;
	private double[] mejor;
	private double[] media;
	private double[] presSelec;

	private boolean max;
	private double mejGenAct;
	private double mediaAct;
	private double presSelecAct;
	private double mejAbs;
	private Funcion mejorF;
	private Funcion mejorActF;

	public AlgoritmoGenetico() {
		mF = MainFrame.getInstance();

		intervalo = mF.getIntervalo();
		tamPoblacion = mF.getTamPoblacion();
		numGeneraciones = mF.getNumGeneraciones();

		seleccion = FactoriaSeleccion.getInstance().getSeleccion(mF.getSeleccion());

		cruce = FactoriaCruce.getInstance().getCruce(mF.getCruce());
		porcentajeCruce = mF.getPorcentajeCruce();

		mutacion = FactoriaMutacion.getInstance().getMutacion(mF.getMutacion());
		porcentajeMutacion = mF.getPorcentajeMutacion();

		funcionTipo = mF.getFuncion();

		elitismoSeleccionado = mF.getElitismoSeleccionado();
		porcentajeElitismo = mF.getPorcentajeElitismo();
		numElite = (porcentajeElitismo * tamPoblacion) / 100;

		mejorAbsoluto = new double[numGeneraciones];
		mejor = new double[numGeneraciones];
		media = new double[numGeneraciones];
		presSelec =  new double[numGeneraciones];

		iniPoblacion();
		algoritmo();
		dibujaGrafica();
	}

	private void algoritmo() {
		// evaluar pob
		evaluar();
		for (int generacionActual = 0; generacionActual < numGeneraciones; generacionActual++) {
			if (elitismoSeleccionado)
				guardarElite();// guardar elite
			poblacion = seleccion.execute(poblacion, presSelecAct);
			poblacion = cruce.execute(poblacion, porcentajeCruce);
			poblacion = mutacion.execute(poblacion, porcentajeMutacion);
			// ev poblacion
			evaluar();
			if (elitismoSeleccionado) {
				// meter elite
				meteElite();
				// reevaluar con elite
				evaluar();
			}
			guardarDatos(generacionActual);
		}
	}

	private void guardarDatos(int generacionActual) {
		mejor[generacionActual] = mejGenAct;
		media[generacionActual] = mediaAct;
		presSelec[generacionActual] = presSelecAct;
		if (max) {
			if (mejAbs < mejGenAct) {
				mejorAbsoluto[generacionActual] = mejGenAct;
				mejAbs = mejGenAct;
				mejorF = mejorActF.getCopy();
			} else
				mejorAbsoluto[generacionActual] = mejAbs;
		} else {
			if (mejAbs > mejGenAct) {
				mejorAbsoluto[generacionActual] = mejGenAct;
				mejAbs = mejGenAct;
				mejorF = mejorActF.getCopy();
			} else
				mejorAbsoluto[generacionActual] = mejAbs;
		}
	}

	private void iniPoblacion() {
		poblacion = new ArrayList<Funcion>();
		for (int i = 0; i < tamPoblacion; i++) {
			poblacion.add(FactoriaFuncion.getInstance().generaFuncion(funcionTipo, intervalo));
		}
		max = poblacion.get(0).getMax();
		presSelecAct = 0;
		if (max) {
			mejGenAct = -1;
			mediaAct = 0;
			mejAbs = -1;
		} else {
			mejGenAct = Double.MAX_VALUE;
			mediaAct = 0;
			mejAbs = Double.MAX_VALUE;
		}
	}

	private void dibujaGrafica() {
		Plot2DPanel plot = new Plot2DPanel();
		plot.setBounds(3, 3, 744, 574);
		double[] x = new double[numGeneraciones];
		for (int i = 1; i <= numGeneraciones; i++) {
			x[i - 1] = i;
		}
		plot.addLegend("SOUTH");
		plot.addLinePlot("Mejor Absoluto", Color.BLUE, x, mejorAbsoluto);
		plot.addLinePlot("Mejor de la Generación", Color.RED, x, mejor);
		plot.addLinePlot("Media Generación", Color.GREEN, x, media);//presSelec
		plot.addLinePlot("Presión Selectiva", Color.BLACK, x, presSelec);
		mF.setGrafica(plot);
		JOptionPane.showMessageDialog(null, "La mejor solución ha sido: \n" + mejorF.toString(), "Solución", JOptionPane.PLAIN_MESSAGE);
	}

	private void guardarElite() {
		elite = new ArrayList<Funcion>();
		for (int i = 0; i < numElite; i++)
			elite.add(poblacion.get(i));
	}

	private void meteElite() {
		int aux = tamPoblacion - 1;
		for (int i = 0; i < numElite; i++) {
			poblacion.remove(aux);
			aux--;
			poblacion.add(elite.get(i));
		}
	}

	private void evaluar() {
		double suma = 0.0, fA = 0.0, sumaAdaptado = 0.0;
		double mA = (max) ? -1 : Double.MAX_VALUE;
		
		for (int i = 0; i < tamPoblacion; i++) {
			poblacion.get(i).calculaFitness();
			fA = poblacion.get(i).getFitness();
			suma += fA;
			if (max) {
				if(mA < fA) {
					mA = fA;
					mejorActF = poblacion.get(i).getCopy();
				}
			}
			else {
				if(!(mA < fA)) {
					mA = fA;
					mejorActF = poblacion.get(i).getCopy();
				}
			}
		}
		Collections.sort(poblacion, poblacion.get(0).getComp());
		for (int i = 0; i < tamPoblacion; i++) {
			sumaAdaptado += (poblacion.get(tamPoblacion - 1).getFitness()  * 1.05) - poblacion.get(i).getFitness();
		}
		mediaAct = suma / tamPoblacion;
		mejGenAct = mA;
		double adaptado = (poblacion.get(tamPoblacion - 1).getFitness()  * 1.05) - poblacion.get(0).getFitness();
		presSelecAct = (adaptado/sumaAdaptado) * tamPoblacion;
	}
}
