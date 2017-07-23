package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import logica.Contrato;
import logica.EmpresaRps;
import logica.Proyecto;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PassDSaldar extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private Contrato contr;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			PassDSaldar dialog = new PassDSaldar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PassDSaldar(Contrato auxx) {
		contr = auxx;
		setTitle("Seguridad");
		setBounds(100, 100, 395, 164);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Complete Campos Para Proseguir", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(25, 38, 92, 14);
		contentPanel.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(127, 35, 182, 20);
		contentPanel.add(passwordField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Continuar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						char[] pass = passwordField.getPassword();
						if(EmpresaRps.getIntento()!=0) { 
							if(compararPass(pass, EmpresaRps.getContraseña())) {
								CrearContrato aux = new CrearContrato(new Proyecto("", "", "", null), true, auxx);
								aux.setModal(true);
								aux.setVisible(true);
								dispose();
						}else {
							JOptionPane.showMessageDialog(null, "Contraseña Incorrecta", "Error",JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Por favor cree una contraseña", "Falta de seguridad",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcion = JOptionPane.showConfirmDialog(null, "Desea cerra??  No sé salvará ningún Dato","Advertencia",JOptionPane.WARNING_MESSAGE);
						if(opcion == JOptionPane.OK_OPTION){
							dispose();
						}
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private boolean compararPass(char[] pass, char[] contraseña) {
		if(pass.length==contraseña.length) {
			for (int i = 0; i < contraseña.length; i++) 
				if(contraseña[i]!=pass[i])
					return false;
			return true;
		}else {
			return false;
		}
	}
}
