package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logica.Cliente;
import logica.Empresa;
import logica.EmpresaRps;
import logica.Indepediente;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;

public class RegistarCliente extends JDialog {


	private final JPanel contentPanel = new JPanel();
	private JRadioButton rdbtnClienteIndepediente;
	private JRadioButton rdbtnEmpresa;
	private JLabel lblIndetificacion;
	private JLabel lblNombre;
	private JLabel lblAmpellido;
	private JLabel lblEmail;
	private JComboBox cbxProvincia;
	private JFormattedTextField txtNombre;
	private JFormattedTextField txtTelefono;
	private JFormattedTextField txtPais;
	private JFormattedTextField txtAmpellido;
	private JFormattedTextField txtEmail;
	private JFormattedTextField txtCiudad;
	private JFormattedTextField txtSector;
	private JFormattedTextField txtCedula;
	private JFormattedTextField txtRnc;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistarCliente dialog = new RegistarCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws ParseException 
	 */
	public RegistarCliente() throws ParseException {
		setTitle("Registrar Cliente");
		setBounds(100, 100, 500, 328);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelCliente = new JPanel();
			panelCliente.setBounds(5, 36, 474, 120);
			panelCliente.setBorder(new TitledBorder(null, "Datos de Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panelCliente);
			panelCliente.setLayout(null);
			
			lblIndetificacion = new JLabel("Cedula :");
			lblIndetificacion.setBounds(10, 27, 46, 14);
			panelCliente.add(lblIndetificacion);
			
			lblNombre = new JLabel("Nombre :");
			lblNombre.setBounds(10, 52, 88, 14);
			panelCliente.add(lblNombre);
			
			lblAmpellido = new JLabel("Ampellido :");
			lblAmpellido.setBounds(220, 52, 69, 14);
			panelCliente.add(lblAmpellido);
			
			JLabel lblTelefono = new JLabel("Telefono :");
			lblTelefono.setBounds(10, 77, 69, 14);
			panelCliente.add(lblTelefono);
			
			lblEmail = new JLabel("E-mail :");
			lblEmail.setBounds(220, 77, 46, 14);
			panelCliente.add(lblEmail);
			
			txtNombre = new JFormattedTextField(createDirr("????????????????????"));
			txtNombre.setBounds(66, 49, 127, 20);
			panelCliente.add(txtNombre);
			

			txtTelefono = new JFormattedTextField(createDirr("###-###-####"));
			txtTelefono.setBounds(66, 74, 127, 20);
			panelCliente.add(txtTelefono);
			
			txtAmpellido = new JFormattedTextField(createDirr("????????????????????"));
			txtAmpellido.setBounds(282, 49, 127, 20);
			panelCliente.add(txtAmpellido);
			
			txtEmail = new JFormattedTextField(createDirr("******************************"));
			txtEmail.setBounds(282, 74, 127, 20);
			panelCliente.add(txtEmail);
			
			txtCedula = new JFormattedTextField(createDirr("###-########-#"));
			txtCedula.setBounds(66, 24, 127, 20);
			panelCliente.add(txtCedula);
			
			txtRnc = new JFormattedTextField(createDirr("###-######-#"));
			txtRnc.setBounds(66, 24, 127, 20);
			txtRnc.setVisible(false);
			panelCliente.add(txtRnc);  
		}
		
		JPanel panelUbicacion = new JPanel();
		panelUbicacion.setBorder(new TitledBorder(null, "Datos de la Ubicacion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelUbicacion.setBounds(5, 158, 474, 92);
		contentPanel.add(panelUbicacion);
		panelUbicacion.setLayout(null);
		
		JLabel lblProvincia = new JLabel("Provincia :");
		lblProvincia.setBounds(10, 59, 84, 14);
		panelUbicacion.add(lblProvincia);
		
		JLabel lblCiudad = new JLabel("Ciudad :");
		lblCiudad.setBounds(219, 28, 46, 14);
		panelUbicacion.add(lblCiudad);
		
		JLabel lblSector = new JLabel("Sector :");
		lblSector.setBounds(219, 56, 71, 14);
		panelUbicacion.add(lblSector);
		
		cbxProvincia = new JComboBox();
		cbxProvincia.setModel(new DefaultComboBoxModel(new String[] {"Azua", "Bahoruco", "Barahona", "Dajab\u00F3n", "Destrito Nacional", "Duarte", "El\u00EDas Pi\u00F1a", "El Seibo", "Espaillat", "Hato Mayor", "Hermanas Mirabal", "Indenpedencia", "La Altagracia", "La Romana", "La Vega", "Mar\u00EDa Trinidad S\u00E1nchez", "Monse\u00F1or Nouel", "Montecristi", "Monte Plata", "Pedernales", "Peravia", "Puerto Plata", "Saman\u00E1", "San Crist\u00F3bal", "San Jos\u00E9 de Ocoa", "San Juan", "San Pedro de Macor\u00EDs", "S\u00E1nchez Ram\u00EDrez", "Santiago", "Santiago Rodr\u00EDguez", "Santo Domingo", "Valverde"}));
		cbxProvincia.setBounds(75, 56, 125, 20);
		panelUbicacion.add(cbxProvincia);
		
		JLabel lblPais = new JLabel("Pais :");
		lblPais.setBounds(10, 28, 46, 14);
		panelUbicacion.add(lblPais);
		
		txtPais = new JFormattedTextField(createDirr("????????????????????"));
		txtPais.setBounds(75, 25, 125, 20);
		panelUbicacion.add(txtPais);
		
		txtCiudad = new JFormattedTextField(createDirr("????????????????????"));
		txtCiudad.setBounds(284, 25, 125, 20);
		panelUbicacion.add(txtCiudad);
		
		txtSector = new JFormattedTextField(createDirr("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
		txtSector.setBounds(284, 53, 125, 20);
		panelUbicacion.add(txtSector);
		
		rdbtnEmpresa = new JRadioButton("Empresa");
		rdbtnEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				rdbtnClienteIndepediente.setSelected(false);
				rdbtnEmpresa.setSelected(true);
				lblIndetificacion.setText("RNC :");
				lblAmpellido.setVisible(false);
				txtAmpellido.setVisible(false);
				txtNombre.setBounds(66, 49, 343, 20);
				lblEmail.setVisible(true);
				txtEmail.setVisible(true);
				lblProvincia.setVisible(false);
				cbxProvincia.setVisible(false);
				lblSector.setVisible(false);
				txtSector.setVisible(false);
				txtCedula.setVisible(false);
				txtRnc.setVisible(true);
				
				
			}
		});
		rdbtnClienteIndepediente = new JRadioButton("Cliente Indepediente");
		
		rdbtnClienteIndepediente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				rdbtnClienteIndepediente.setSelected(true);
				rdbtnEmpresa.setSelected(false);
				lblIndetificacion.setText("Cedula :");
				lblAmpellido.setVisible(true);
				txtAmpellido.setVisible(true);
				txtNombre.setBounds(66, 49, 127, 20);
				lblEmail.setVisible(false);
				txtEmail.setVisible(false);
				lblProvincia.setVisible(true);
				cbxProvincia.setVisible(true);
				lblSector.setVisible(true);
				txtSector.setVisible(true);
				txtCedula.setVisible(true);
				txtRnc.setVisible(false);
				
				
			}
		});
		rdbtnClienteIndepediente.setSelected(true);
		rdbtnClienteIndepediente.setBounds(6, 7, 158, 23);
		contentPanel.add(rdbtnClienteIndepediente);
		
		
		rdbtnEmpresa.setBounds(166, 7, 109, 23);
		contentPanel.add(rdbtnEmpresa);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(rdbtnClienteIndepediente.isSelected()) {
							
							String cedula=txtCedula.getText();
							String nombre=txtNombre.getText();
							String ampellido=txtAmpellido.getText();
							String telefono=txtTelefono.getText();
							String email=txtEmail.getText();
							String pais=txtPais.getText();
							String provincia=cbxProvincia.getSelectedItem().toString();
							String ciudad=txtCiudad.getText();
							String sector=txtSector.getText();
							
							
							Cliente cliente=new Indepediente(cedula, nombre, ampellido, telefono, ciudad, sector, provincia, pais);
							EmpresaRps.getInstance().agregarCliente(cliente);
							
							txtCedula.setText("");
							txtNombre.setText("");
							txtAmpellido.setText("");
							txtTelefono.setText("");
							txtEmail.setText("");
							txtPais.setText("");
							cbxProvincia.setSelectedIndex(0);
							txtCiudad.setText("");
							txtSector.setText("");
							
						}
						
						if(rdbtnEmpresa.isSelected()) {
							String rnc=txtCedula.getText();
							String nombre=txtNombre.getText();
							String email=txtEmail.getText();
							String telefono=txtTelefono.getText();
							String ciudad=txtCiudad.getText();
							String pais=txtPais.getText();
							
							Cliente cliente=new Empresa(rnc, nombre, email, telefono, ciudad, pais);
							EmpresaRps.getInstance().agregarCliente(cliente);
							
							txtCedula.setText("");
							txtNombre.setText("");
							txtEmail.setText("");
							txtTelefono.setText("");
							txtCiudad.setText("");
							txtPais.setText("");
							
							
						}
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}	
		
	}
	
	static MaskFormatter createDirr(String format) {/*hace que un string de X tamaño permita solo lo 
		caracteres de setValidChatacters*/
		/*(ARGUMENTO)recibe un String llenado con '*' , hasta donde llegen los '*' sera su tamaño maximo*/
		//(RETORNA)Formato para FORMATETEXTFIELDS
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(format);
			mask.setValidCharacters(" qwertyuiopasdfghjklzxcvbnm"+" QWERTYUIOPASDFGHJKLZXCVBNM "+ "0123456789-");
			mask.setPlaceholderCharacter(' ');
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
			System.exit(-1);
		}
		return mask;
	}
}
