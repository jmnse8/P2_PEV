package presentacion.paneles;


import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import presentacion.recursos.Colores;

public class PanelElitismo extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JCheckBox cB;
	private JLabel infoCB;
	private JSpinner pS;

	public PanelElitismo() {
		iniGUI();
	}

	private void iniGUI() {
		setLayout(null);
		setBounds(5, 385, 300, 60);
		setBackground(Colores.CLARO2);

		Border b = BorderFactory.createLineBorder(Colores.OSCURO2, 2);
		setBorder(BorderFactory.createTitledBorder(b, "ELIGE EL TIPO DE MUTACIÓN ", TitledBorder.LEFT, TitledBorder.TOP,new Font("Arial", Font.BOLD, 15),Colores.OSCURO2));
		
		cB = new JCheckBox();
		cB.setBounds(120, 20, 20, 20);
		cB.setBackground(Colores.CLARO2);
		cB.setForeground(Colores.OSCURO2);
		
		infoCB = new JLabel("¿Hay ELITISMO?:");
		infoCB.setBounds(20, 20, 120, 20);
		infoCB.setForeground(Colores.OSCURO2);
		
		SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(5, 0, 100, 1);
		pS = new JSpinner(modeloSpinner);
        pS.setBounds(200, 20, 70, 20);
        pS.setToolTipText("Seleccionar porcentaje de elitismo");
		
		add(cB);
		add(infoCB);
		add(pS);
	}
	
	public boolean getElitismoSeleccionado() {
		return cB.isSelected();
	}
	
	public int getPorcentajeElitismo() {
		return (int) pS.getValue();
	}

}
