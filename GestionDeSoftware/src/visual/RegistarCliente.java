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
import javax.swing.ImageIcon;
import java.awt.Toolkit;

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
	private JFormattedTextField txtCiudad;
	private JFormattedTextField txtSector;
	private JFormattedTextField txtCedula;
	private JFormattedTextField txtRnc;
	private JTextField txtEmail;
	private JButton btnRegistrar;
	private JLabel lblSector;
	private JLabel lblProvincia ;
	private static EmpresaRps empresaRps=null;
	private Cliente modiCliente=null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistarCliente dialog = new RegistarCliente(null, "Registrar Cliente", null);//Ralddy- mantener el null de la derecha para la principal
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
	public RegistarCliente(EmpresaRps emp,String titulo,Cliente modifica) throws ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistarCliente.class.getResource("/img/add.cliente48.png")));
		cargaCliente();
		empresaRps=emp;
		modiCliente=modifica;
		setTitle(titulo);
		setBounds(100, 100, 505, 341);
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
			
			txtCedula = new JFormattedTextField(createDirr("###-#######-#"));
			txtCedula.setBounds(66, 24, 127, 20);
			panelCliente.add(txtCedula);
			
			txtRnc = new JFormattedTextField(createDirr("###-#####-#"));
			txtRnc.setBounds(66, 24, 127, 20);
			txtRnc.setVisible(false);
			panelCliente.add(txtRnc);  
			
			txtEmail = new JTextField();
			txtEmail.setBounds(282, 74, 127, 20);
			panelCliente.add(txtEmail);
			txtEmail.setColumns(10);
		}
		
		JPanel panelUbicacion = new JPanel();
		panelUbicacion.setBorder(new TitledBorder(null, "Datos de la Ubicacion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelUbicacion.setBounds(5, 158, 474, 92);
		contentPanel.add(panelUbicacion);
		panelUbicacion.setLayout(null);
		
		lblProvincia = new JLabel("Provincia :");
		lblProvincia.setBounds(10, 59, 84, 14);
		panelUbicacion.add(lblProvincia);
		
		JLabel lblCiudad = new JLabel("Ciudad :");
		lblCiudad.setBounds(219, 28, 46, 14);
		panelUbicacion.add(lblCiudad);
		
		lblSector = new JLabel("Sector :");
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
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.setIcon(new ImageIcon(RegistarCliente.class.getResource("/img/add.cliente.png")));
				if(modiCliente==null) {
					btnRegistrar.setText("Registrar");
				} else {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(modiCliente==null) {
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
								
								
								Cliente cliente=new Indepediente(cedula, nombre, ampellido, telefono, email, ciudad, sector, provincia, pais);
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
								String rnc=txtRnc.getText();
								String nombre=txtNombre.getText();
								String email=txtEmail.getText();
								String telefono=txtTelefono.getText();
								String ciudad=txtCiudad.getText();
								String pais=txtPais.getText();
								
								Cliente cliente=new Empresa(rnc, nombre, email, telefono, ciudad, pais);
								EmpresaRps.getInstance().agregarCliente(cliente);
								
								txtRnc.setText("");
								txtNombre.setText("");
								txtEmail.setText("");
								txtTelefono.setText("");
								txtCiudad.setText("");
								txtPais.setText("");
								
								
							} 
						} else {
							
							if(rdbtnClienteIndepediente.isSelected()) {
								

								String nombre=txtNombre.getText();
								String ampellido=txtAmpellido.getText();
								String telefono=txtTelefono.getText();
								String email=txtEmail.getText();
								String pais=txtPais.getText();
								String provincia=cbxProvincia.getSelectedItem().toString();
								String ciudad=txtCiudad.getText();
								String sector=txtSector.getText();
								modiCliente.setNombre(nombre);
								((Indepediente)modiCliente).setAmpellido(ampellido);
								modiCliente.setTelefono(telefono);
								modiCliente.setEmail(email);
								modiCliente.setPais(pais);
								((Indepediente)modiCliente).setProvincia(provincia);
								((Indepediente)modiCliente).setCiudad(ciudad);
								((Indepediente)modiCliente).setSector(sector);
								
								
								EmpresaRps.getInstance().modificarCliente(modiCliente);
								
								

								
							}
							
							if(rdbtnEmpresa.isSelected()) {

								String nombre=txtNombre.getText();
								String email=txtEmail.getText();
								String telefono=txtTelefono.getText();
								String ciudad=txtCiudad.getText();
								String pais=txtPais.getText();
								modiCliente.setNombre(nombre);
								modiCliente.setEmail(email);
								modiCliente.setTelefono(telefono);
								modiCliente.setCiudad(ciudad);
								modiCliente.setPais(pais);
								
								EmpresaRps.getInstance().modificarCliente(modiCliente);
														
								
							} 
							ListarClientes.getBtnEliminar().setEnabled(false);
							ListarClientes.getBtnModificar().setEnabled(false);
							ListarClientes.cargaTablaClientes();
							ListarClientes.cargaTablaContractos(null);
							ListarClientes.getBtnBuscar().setEnabled(false);

							dispose();
						}
						 
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setIcon(new ImageIcon(RegistarCliente.class.getResource("/img/cancelar.cliente.png")));
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
	
	public void cargaCliente() {//ralddy-metodo para obtener los datos de un cliente y llenan los campos cuando se modifique
		if(modiCliente!=null) {
			rdbtnClienteIndepediente.setEnabled(false);
			rdbtnEmpresa.setEnabled(false);
			
			if (modiCliente instanceof Indepediente) {
				
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
				
				txtCedula.setText(((Indepediente) modiCliente).getCedula());
				txtNombre.setText(modiCliente.getNombre());
				txtAmpellido.setText(((Indepediente) modiCliente).getAmpellido());
				txtTelefono.setText(modiCliente.getTelefono());
				txtEmail.setText(modiCliente.getEmail());
				txtPais.setText(modiCliente.getPais());
				txtSector.setText(((Indepediente) modiCliente).getSector());
				txtCiudad.setText(modiCliente.getCiudad());
				cbxProvincia.setSelectedItem(((Indepediente) modiCliente).getProvincia());
				
				
			} else if (modiCliente instanceof Empresa) {
				
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
				
				txtRnc.setText(((Empresa) modiCliente).getRnc());
				txtNombre.setText(modiCliente.getNombre());
				txtTelefono.setText(modiCliente.getTelefono());
				txtEmail.setText(modiCliente.getEmail());
				txtPais.setText(modiCliente.getPais());
				txtCiudad.setText(modiCliente.getCiudad());


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
