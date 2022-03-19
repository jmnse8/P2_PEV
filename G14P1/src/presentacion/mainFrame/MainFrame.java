package presentacion.mainFrame;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import negocio.cruce.CruceEnum;
import negocio.funcion.FuncionEnum;
import negocio.mutacion.MutacionEnum;
import negocio.seleccion.SeleccionEnum;
import presentacion.mainFrame.mainFrameImp.MainFrameImp;

public abstract class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private static MainFrame instance;
	
	public synchronized static MainFrame getInstance()  {
		if (instance == null)
			instance = new MainFrameImp();
		return instance;
	}
	
	public abstract SeleccionEnum getSeleccion();
	public abstract MutacionEnum getMutacion();
	public abstract int getPorcentajeMutacion();
	public abstract double getIntervalo();
	public abstract int getTamPoblacion();
	public abstract int getNumGeneraciones();
	public abstract FuncionEnum getFuncion();
	public abstract boolean getElitismoSeleccionado();
	public abstract int getPorcentajeElitismo();
	public abstract CruceEnum getCruce();
	public abstract int getPorcentajeCruce();
	public abstract void setGrafica(Plot2DPanel grafica);
	public abstract int getNFuncion();
	
}
