package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import logica.EmpresaRps;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class CambioDeTasa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField psfContraseña;
	private JPasswordField psfConfirmacion;
	private JFormattedTextField ftxNuevaTasa;
	private JFormattedTextField precio;
	private JTextField txtTasaAtual;
	private DecimalFormatSymbols dfs;
	private DecimalFormat dFormat;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			CambioDeTasa dialog = new CambioDeTasa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CambioDeTasa() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CambioDeTasa.class.getResource("/img/002-money-bag.png")));
		setTitle("Tasa del Dolar");
		setBounds(100, 100, 368, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Esenciales", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		crearFTXTasa();
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Tasa Actual:");
			lblNewLabel.setBounds(30, 35, 113, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nueva Tasa");
			lblNewLabel_1.setBounds(30, 85, 113, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Contrase\u00F1a");
			lblNewLabel_2.setBounds(30, 135, 113, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel(" Confirmacion:");
			lblNewLabel_3.setBounds(30, 185, 113, 14);
			contentPanel.add(lblNewLabel_3);
		}
		{
			psfContraseña = new JPasswordField();
			psfContraseña.setBounds(135, 131, 186, 23);
			contentPanel.add(psfContraseña);
		}
		{
			psfConfirmacion = new JPasswordField();
			psfConfirmacion.setBounds(135, 181, 186, 23);
			contentPanel.add(psfConfirmacion);
		}
		
		txtTasaAtual = new JTextField();
		txtTasaAtual.setText(String.valueOf(EmpresaRps.getTasaDolar()));
		txtTasaAtual.setEditable(false);
		txtTasaAtual.setBounds(135, 31, 103, 23);
		contentPanel.add(txtTasaAtual);
		txtTasaAtual.setColumns(10);
		ftxNuevaTasa = precio;
		ftxNuevaTasa.setBounds(135, 81, 103, 23);
		contentPanel.add(ftxNuevaTasa);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Salvar");
				okButton.setIcon(new ImageIcon(CambioDeTasa.class.getResource("/img/001-technology.png")));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						char[] actual = psfContraseña.getPassword();
						char[] confir = psfConfirmacion.getPassword();
						if(actual.length>=8) {
							if(compararPassFields(actual,confir)) {
							int opcion = JOptionPane.showConfirmDialog(null, "Seguro de su nueva contraseña?? ","Advertencia",JOptionPane.WARNING_MESSAGE);
							if(opcion == JOptionPane.OK_OPTION){
								if(EmpresaRps.setTasaDolar(Float.valueOf(ftxNuevaTasa.getText()), actual)) {
									JOptionPane.showMessageDialog(null,  "Se ha cambiado la tasa para ventas", "Información",JOptionPane.INFORMATION_MESSAGE);
									dispose();
								}else {
									JOptionPane.showMessageDialog(null,  "La contraseña actual es incorrecta", "Error",JOptionPane.ERROR_MESSAGE);
								}
							}	
						}else {
							JOptionPane.showMessageDialog(null,  "Las contraseña nueva y confirmar deben ser identicas", "Error",JOptionPane.ERROR_MESSAGE);
						}
						}else {
							JOptionPane.showMessageDialog(null,  "La contraseña es incorrecta", "Error",JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setIcon(new ImageIcon(CambioDeTasa.class.getResource("/img/001-delete.png")));
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

	private void crearFTXTasa() {
		dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.'); //separator for the decimals
		dfs.setGroupingSeparator(','); //separator for the thousands
		dFormat = new DecimalFormat ("###,###,###,###,###,##0.##", dfs);
		precio = new JFormattedTextField(dFormat);
	}
}
