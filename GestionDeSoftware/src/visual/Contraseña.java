package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.EmpresaRps;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Contraseña extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField psfActual;
	private JPasswordField psfNueva;
	private JPasswordField psfConfirmar;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			Contraseña dialog = new Contraseña();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Contraseña() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Contraseña.class.getResource("/img/001-lock.png")));
		setTitle("Cambiar Contrase\u00F1a");
		setBounds(100, 100, 340, 245);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblContraseaActual = new JLabel("Contrase\u00F1a Actual:");
		lblContraseaActual.setBounds(25, 25, 113, 14);
		contentPanel.add(lblContraseaActual);
		
		psfActual = new JPasswordField();
		psfActual.setBounds(153, 25, 161, 20);
		contentPanel.add(psfActual);
		
		JLabel lblContraseaNueva = new JLabel("Contrase\u00F1a Nueva:");
		lblContraseaNueva.setBounds(25, 75, 129, 14);
		contentPanel.add(lblContraseaNueva);
		
		JLabel lblConfirmarNueva = new JLabel("Confirmar Nueva:");
		lblConfirmarNueva.setBounds(25, 125, 173, 14);
		contentPanel.add(lblConfirmarNueva);
		
		psfNueva = new JPasswordField();
		psfNueva.setBounds(153, 72, 161, 20);
		contentPanel.add(psfNueva);
		
		psfConfirmar = new JPasswordField();
		psfConfirmar.setBounds(153, 122, 161, 20);
		contentPanel.add(psfConfirmar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Salvar");
				okButton.setIcon(new ImageIcon(Contraseña.class.getResource("/img/001-technology.png")));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						char[] actual = psfActual.getPassword();
						char[] nueva = psfNueva.getPassword();
						char[] confirmar = psfConfirmar.getPassword();
						if(nueva.length>=8) {
							if(compararPassFields(nueva,confirmar)) {
								int opcion = JOptionPane.showConfirmDialog(null, "Seguro de su nueva contraseña?? ","Advertencia",JOptionPane.WARNING_MESSAGE);
								if(opcion == JOptionPane.OK_OPTION){
									if(EmpresaRps.setContraseña(actual, nueva)) {
										JOptionPane.showMessageDialog(null,  "Se ha cambiado la contraseña", "Información",JOptionPane.INFORMATION_MESSAGE);
										dispose();
									}else {
										JOptionPane.showMessageDialog(null,  "La contraseña actual es incorrecta", "Error",JOptionPane.ERROR_MESSAGE);
									}
								}	
							}else {
								JOptionPane.showMessageDialog(null,  "Las contraseña nueva y confirmar deben ser identicas", "Error",JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null,  "Por seguridad, contraseña de minimo 8 caracteres", "Advertencia",JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setIcon(new ImageIcon(Contraseña.class.getResource("/img/001-delete.png")));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcion = JOptionPane.showConfirmDialog(null, "¿¿Seguro desea cerrar?? no se salvara ningun dato","Advertencia",JOptionPane.WARNING_MESSAGE);
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
	private boolean compararPassFields(char[] nueva, char[] confirmar) {
		if(nueva.length==confirmar.length) {
			for (int i = 0; i < confirmar.length; i++) 
				if(confirmar[i]!=nueva[i])
					return false;
			return true;
		}else {
			return false;
		}
	}
}
