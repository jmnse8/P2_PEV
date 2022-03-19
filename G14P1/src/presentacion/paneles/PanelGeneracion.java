package presentacion.paneles;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import presentacion.recursos.Colores;

public class PanelGeneracion extends JPanel{
	
	private JLabel labelTP;
	private JLabel labelNG;
	private JSpinner tS;
	private JSpinner nS;
	
	private static final long serialVersionUID = 1L;

	public PanelGeneracion() {
		iniGUI();
	}
	
	private void iniGUI() {
		setLayout(null);
		setBounds(5,285,300, 100);		
		setBackground(Colores.CLARO2);

		Border b = BorderFactory.createLineBorder(Colores.OSCURO2, 2);
		setBorder(BorderFactory.createTitledBorder(b, "OPCIONES DE LA GENERACIÓN ", TitledBorder.LEFT, TitledBorder.TOP,new Font("Arial", Font.BOLD, 15),Colores.OSCURO2));
		
		SpinnerNumberModel modeloSpinner1 = new SpinnerNumberModel(100, 2, 1000, 1);
		SpinnerNumberModel modeloSpinner2 = new SpinnerNumberModel(100, 2, 1000, 1);
		
        tS = new JSpinner(modeloSpinner1);
        tS.setBounds(200, 20, 70, 20);
        tS.setToolTipText("Seleccionar tamaño de la población");
        
        nS = new JSpinner(modeloSpinner2);
        nS.setBounds(200, 60, 70, 20);
        nS.setToolTipText("Seleccionar número de generaciones");
        

        labelTP = new JLabel("Tamaño de la población:");
        labelTP.setBounds(20, 20, 180, 20);
        labelTP.setForeground(Colores.OSCURO2);
        
        labelNG = new JLabel("Número de generaciones:");
        labelNG.setBounds(20, 60, 180, 20);
        labelNG.setForeground(Colores.OSCURO2);
        
        add(tS);
        add(nS);
        add(labelTP);
        add(labelNG);
		
	}
	
	public int getTamPoblacion() {
		return (int) tS.getValue();
	}
	public int getNumGeneraciones() {
		return (int) nS.getValue();
	}

}
