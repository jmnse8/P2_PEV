package presentacion.paneles;

//import java.awt.Font;

//import javax.swing.BorderFactory;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.border.Border;
//import javax.swing.border.TitledBorder;

import presentacion.recursos.Colores;

public class PanelIntervalo extends JPanel{

	private static final long serialVersionUID = 1L;
	//private JTextField inte;
	
	public PanelIntervalo() {
		iniGUI();
	}

	private void iniGUI() {
		setLayout(null);
		setBounds(5, 445, 300, 60);
		setBackground(Colores.CLARO2);
/*
		Border b = BorderFactory.createLineBorder(Colores.OSCURO2, 2);
		setBorder(BorderFactory.createTitledBorder(b, "ELIGE EL INTERVALO DE PRECISIÓN ", TitledBorder.LEFT, TitledBorder.TOP,new Font("Arial", Font.BOLD, 15),Colores.OSCURO2));
		inte = new JTextField();
		inte.setBounds(20, 20, 260, 20);
		inte.setText("0.001");
		inte.setBackground(Colores.CLARO1);
		inte.setForeground(Colores.OSCURO2);
		
		add(inte);*/
	}
	
	public double getIntervalo() {
		/*
		double i = -1;
		try {
			i = Double.parseDouble(inte.getText());
			if(i > 1)
				throw new Exception();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Mete un número menor que uno.", "Error message", JOptionPane.ERROR_MESSAGE);
			i = 0.001;
		}
		
		return i;
		*/
		return 0.01;
	}
}
