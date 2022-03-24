package presentacion.paneles;



import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import negocio.funcion.FuncionEnum;
import presentacion.recursos.Colores;

public class PanelFuncion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> cB;
	private JSpinner nS;

	public PanelFuncion() {
		iniGUI();
	}

	private void iniGUI() {
		setLayout(null);
		setBounds(5, 225, 300, 60);
		setBackground(Colores.CLARO2);

		Border b = BorderFactory.createLineBorder(Colores.OSCURO2, 2);
		setBorder(BorderFactory.createTitledBorder(b, "ELIGE LA FUNCI�N ", TitledBorder.LEFT, TitledBorder.TOP,new Font("Arial", Font.BOLD, 15),Colores.OSCURO2));

		cB = new JComboBox<String>();
		cB.addItem("Funcion 1");
		cB.addItem("Funcion 2");
		cB.addItem("Funcion 3");
		cB.addItem("Funcion 4");
		cB.addItem("Funcion 5");
		cB.addItem("Trafico Aereo");
		cB.setBounds(20, 20, 210, 20);
		cB.setBackground(Colores.CLARO1);
		cB.setForeground(Colores.OSCURO2);
		
		SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(4, 1, 10, 1);
		
        nS = new JSpinner(modeloSpinner);
        nS.setBounds(240, 20, 40, 20);
        nS.setToolTipText("Seleccionar n de la funci�n Michalewicz");
		
		add(cB);
		add(nS);
	}

	public FuncionEnum getFuncion() {
		switch ((String) cB.getSelectedItem()) {
		case "Funcion 1":
			return FuncionEnum.Funcion_1;
		case "Funcion 2":
			return FuncionEnum.Funcion_2;
		case "Funcion 3":
			return FuncionEnum.Funcion_3;
		case "Funcion 4":
			return FuncionEnum.Funcion_4;
		case "Funcion 5":
			return FuncionEnum.Funcion_5;
		case "Trafico Aereo":
			return FuncionEnum.Funcion_trafico_aereo;
		default:
			return FuncionEnum.Funcion_1;
		}
	}
	
	public int getNFuncion() {
		return (int) nS.getValue();
	}
}
