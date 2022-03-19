package presentacion.mainFrame.mainFrameImp;

import java.awt.Dimension;//G.14
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

import negocio.cruce.CruceEnum;
import negocio.funcion.FuncionEnum;
import negocio.mutacion.MutacionEnum;
import negocio.seleccion.SeleccionEnum;
import presentacion.mainFrame.MainFrame;
import presentacion.paneles.PanelGeneral;
import presentacion.recursos.Colores;

public class MainFrameImp  extends MainFrame{
	private static final long serialVersionUID = 1L;
	
	private JPanel plotPanel;
	private Plot2DPanel plot;
	private PanelGeneral pG;
	private JLabel titulo;
	private JLabel nombres;
	
	public MainFrameImp() {
		iniGUI();
	}

	
	private void iniGUI() {
		setVisible(true);
		setTitle("Práctica 2");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1090,708));
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout (null);
		getContentPane().setBackground(Colores.CLARO1);
		
		plot = new Plot2DPanel();
		plot.setBounds(3,3,744,574);
		
		plotPanel = new JPanel();
		plotPanel.setBounds(320, 85, 750, 580);
		plotPanel.setLayout(null);
		plotPanel.setBackground(Colores.OSCURO2);
		plotPanel.add(plot);
		
		pG = new PanelGeneral();//570
		
		titulo = new JLabel("Práctica 2. ALGORITMO GENÉTICO ");
		titulo.setBounds(10,10,425,60);
		titulo.setFont(new Font("Arial", Font.BOLD, 24));
		titulo.setForeground(Colores.OSCURO2);
		add(titulo);
		
		nombres = new JLabel("GRUPO 14 -> Juan Manuel Núñez y Daniel Gil");
		nombres.setBounds(425,20,500,50);
		nombres.setFont(new Font("Arial", Font.BOLD, 14));
		nombres.setForeground(Colores.OSCURO2);
		add(nombres);
		
		add(plotPanel);
		add(pG);
		repaint();
		revalidate();
	}


	@Override
	public SeleccionEnum getSeleccion() {
		return pG.getSeleccion();
	}


	@Override
	public MutacionEnum getMutacion() {
		return pG.getMutacion();
	}


	@Override
	public int getPorcentajeMutacion() {
		return pG.getPorcentajeMutacion();
	}


	@Override
	public double getIntervalo() {
		return pG.getIntervalo();
	}


	@Override
	public int getTamPoblacion() {
		return pG.getTamPoblacion();
	}


	@Override
	public int getNumGeneraciones() {
		return pG.getNumGeneraciones();
	}


	@Override
	public FuncionEnum getFuncion() {
		return pG.getFuncion();
	}


	@Override
	public boolean getElitismoSeleccionado() {
		return pG.getElitismoSeleccionado();
	}


	@Override
	public int getPorcentajeElitismo() {
		return pG.getPorcentajeElitismo();
	}


	@Override
	public CruceEnum getCruce() {
		return pG.getCruce();
	}


	@Override
	public int getPorcentajeCruce() {
		return pG.getPorcentajeCruce();
	}


	@Override
	public void setGrafica(Plot2DPanel grafica) {
		plotPanel.remove(plot);
		plot = grafica;
		plotPanel.add(plot);
		repaint();
		revalidate();
	}


	@Override
	public int getNFuncion() {
		return pG.getNFuncion();
	}

}
