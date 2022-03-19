package presentacion.paneles;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import negocio.cruce.CruceEnum;
import negocio.funcion.FuncionEnum;
import negocio.mutacion.MutacionEnum;
import negocio.seleccion.SeleccionEnum;
import presentacion.recursos.Colores;

public class PanelGeneral extends JPanel{

	private static final long serialVersionUID = 1L;
	private PanelFuncion pF;
	private PanelGeneracion pG;
	private PanelMutacion pM;
	private PanelSeleccion pS;
	private PanelCruce pC;
	private PanelElitismo pE;
	private PanelIntervalo pI;
	private PanelEjecutar pEj;
	
	public PanelGeneral() {
		iniGUI();
	}
	
	public void iniGUI() {
		setLayout(null);
		setBounds(5, 85, 310, 580);
		setBackground(Colores.CLARO2);
		Border b = BorderFactory.createLineBorder(Colores.OSCURO2, 2);
		setBorder(b);
		
		pF = new PanelFuncion();
		pG = new PanelGeneracion();
		pM = new PanelMutacion();
		pS = new PanelSeleccion();
		pC = new PanelCruce();
		pE = new PanelElitismo();
		pI = new PanelIntervalo();
		pEj = new PanelEjecutar();
		
		add(pF);
		add(pM);
		add(pS);
		add(pG);
		add(pC);
		add(pE);
		add(pI);
		add(pEj);
	}
	
	public SeleccionEnum getSeleccion() {
		return pS.getSeleccion();
	}
	
	public MutacionEnum getMutacion() {
		return pM.getMutacion();
	}
	
	public int getPorcentajeMutacion() {
		return pM.getPorcentajeMutacion();
	}
	
	public double getIntervalo() {
		return pI.getIntervalo();
	}
	
	public int getTamPoblacion() {
		return pG.getTamPoblacion();
	}
	
	public int getNumGeneraciones() {
		return pG.getNumGeneraciones();
	}
	
	public FuncionEnum getFuncion() {
		return pF.getFuncion();
	}
	
	public boolean getElitismoSeleccionado() {
		return pE.getElitismoSeleccionado();
	}
	
	public int getPorcentajeElitismo() {
		return pE.getPorcentajeElitismo();
	}
	
	public CruceEnum getCruce() {
		return pC.getCruce();
	}
	
	public int getPorcentajeCruce() {
		return pC.getPorcentajeCruce();
	}
	
	public int getNFuncion() {
		return pF.getNFuncion();
	}
}
