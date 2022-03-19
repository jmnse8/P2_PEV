package presentacion.paneles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import negocio.algoritmoGenetico.AlgoritmoGenetico;
import presentacion.filtro.Filtro;
import presentacion.recursos.Colores;

public class PanelEjecutar extends JPanel{

	private static final long serialVersionUID = 1L;
	private JButton executeB;

	public PanelEjecutar() {
		iniGUI();
	}

	private void iniGUI() {
		setLayout(null);
		setBounds(8, 515, 294, 60);
		setBackground(Colores.CLARO2);

		Border b = BorderFactory.createLineBorder(Colores.OSCURO2, 2);
		setBorder(b);
		
		executeB = new JButton();
		executeB.setBounds(17, 20, 260, 20);
		executeB.setText("EJECUTAR");
		executeB.setBackground(Colores.CLARO1);
		executeB.setForeground(Colores.OSCURO2);
		executeBListener();
		
		add(executeB);
	}
	
	private void executeBListener() {
        executeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	if(Filtro.aplicaFiltro())
            		new AlgoritmoGenetico();
            	else
            		JOptionPane.showMessageDialog(null, "Error al elegir el cruce o la mutación con esa función.", "Error message", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
