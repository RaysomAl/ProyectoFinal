package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import logica.*;


public class RegistrarTrabajador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtPago;
	private JTextField txtCiudad;
	private JTextField txtSector;
	private JTextField txtCalle;
	private JSpinner spnEdad;
	private JSpinner spnHorasTrab;
	private JSpinner spnNo;
	private JRadioButton rbdJefeProyecto;
	private JRadioButton rbdProgramador;
	private JRadioButton rbdPlanificador;
	private JRadioButton rbdDisenador;
	private JLabel lbExperienciaJefe;
	private JLabel lbLenguajeProgramador;
	private JLabel lbTipoProgramador;
	private JLabel lbExperienciaPlanificador;
	private JLabel lbLenguajeDiseno;
	private JSpinner SpnExperienciaJefe;
	private JComboBox cbxLenguajeProgramador;
	private JComboBox cbxTipoProgramador;
	private JSpinner spnExperienciaPlaneador;
	private JComboBox cbxLenguajeDiseno;
	private JFormattedTextField ftCedula;
	private JComboBox cbxSexo ;
	private JFormattedTextField ftTelefono;
	private JComboBox cbxProvincia;
	private JPanel jefeProyecto;
	private JPanel programador;
	private JPanel planeador;
	private JPanel Disenador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarTrabajador dialog = new RegistrarTrabajador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarTrabajador() {
		setTitle("Registrar Trabajador");
		setBounds(100, 100, 616, 503);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel datosGenerales = new JPanel();
			datosGenerales.setBorder(new TitledBorder(null, "Datos Generales", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			datosGenerales.setBounds(10, 11, 578, 170);
			contentPanel.add(datosGenerales);
			datosGenerales.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("C\u00E9dula: ");
				lblNewLabel.setBounds(10, 22, 46, 14);
				datosGenerales.add(lblNewLabel);
			}
			MaskFormatter cedula = null;
			MaskFormatter telefono = null;
			try {
				cedula = new MaskFormatter("###-#######-#");
				cedula.setPlaceholderCharacter('_');
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				telefono = new MaskFormatter("###-###-####");
				telefono.setPlaceholderCharacter('_');
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Nombres:");
				lblNewLabel_1.setBounds(10, 59, 103, 14);
				datosGenerales.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Apellidos:");
				lblNewLabel_2.setBounds(10, 96, 103, 14);
				datosGenerales.add(lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Sexo:");
				lblNewLabel_3.setBounds(10, 133, 46, 14);
				datosGenerales.add(lblNewLabel_3);
			}
			{
				ftCedula = new JFormattedTextField(cedula);
				ftCedula.setBounds(77, 19, 154, 20);
				datosGenerales.add(ftCedula);
				
			}
			{
				txtNombres = new JTextField();
				txtNombres.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						char nom = e.getKeyChar();
						if((nom < 'a' || nom > 'z') && (nom < 'A' || nom > 'Z') && (nom != ' ' && nom != '�' && nom != '�' && nom != '�' && nom != '�' && nom != '�'))
							e.consume();
					}
					
				});
				txtNombres.setBounds(77, 56, 154, 20);
				datosGenerales.add(txtNombres);
				txtNombres.setColumns(10);
			}
			{
				txtApellidos = new JTextField();
				txtApellidos.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						char apelli = e.getKeyChar();
						if((apelli < 'a' || apelli > 'z') && (apelli < 'A' || apelli > 'Z') && (apelli != ' ' && apelli != '�' && apelli != '�' && apelli != '�' && apelli != '�' && apelli != '�'))
							e.consume();
					}
				});
				txtApellidos.setBounds(77, 93, 154, 20);
				datosGenerales.add(txtApellidos);
				txtApellidos.setColumns(10);
			}
			{
				cbxSexo = new JComboBox();
				cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Masculino", "Femenino"}));
				cbxSexo.setBounds(77, 130, 154, 20);
				datosGenerales.add(cbxSexo);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Edad:");
				lblNewLabel_4.setBounds(269, 22, 46, 14);
				datosGenerales.add(lblNewLabel_4);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("Tel\u00E9fono: ");
				lblNewLabel_5.setBounds(269, 59, 72, 14);
				datosGenerales.add(lblNewLabel_5);
			}
			{
				JLabel lblNewLabel_6 = new JLabel("Horas de trabajo:");
				lblNewLabel_6.setBounds(269, 96, 98, 14);
				datosGenerales.add(lblNewLabel_6);
			}
			{
				JLabel lblNewLabel_7 = new JLabel("Pago por hora:   RD$");
				lblNewLabel_7.setBounds(269, 133, 160, 14);
				datosGenerales.add(lblNewLabel_7);
			}
			{
				spnHorasTrab = new JSpinner();
				spnHorasTrab.setEnabled(false);
				SpinnerNumberModel horasTrab = new SpinnerNumberModel();
				horasTrab.setValue(8);
				horasTrab.setStepSize(1);
				horasTrab.setMaximum(8);
				horasTrab.setMinimum(8);
				spnHorasTrab.setModel(horasTrab);
				spnHorasTrab.setBounds(390, 93, 178, 20);
				datosGenerales.add(spnHorasTrab);
			}
			{
				txtPago = new JTextField();
				txtPago.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						char c =  e.getKeyChar();
						if((c < '0' || c > '9') && c != '.')
							e.consume();
						
					}
				});
				txtPago.setBounds(389, 130, 179, 20);
				datosGenerales.add(txtPago);
				txtPago.setColumns(10);
			}
			{
				ftTelefono = new JFormattedTextField(telefono);
				ftTelefono.setBounds(390, 56, 178, 20);
				datosGenerales.add(ftTelefono);
				
			}
			{
				spnEdad = new JSpinner();
				SpinnerNumberModel edad = new SpinnerNumberModel();
				edad.setValue(20);
				edad.setStepSize(1);
				edad.setMaximum(30);
				edad.setMinimum(20);
		        spnEdad.setModel(edad);
				spnEdad.setBounds(390, 19, 178, 20);
				datosGenerales.add(spnEdad);
			}
		}
		{
			JPanel datosUbicacion = new JPanel();
			datosUbicacion.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos de Ubicaci\u00F3n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			datosUbicacion.setBounds(10, 192, 578, 89);
			contentPanel.add(datosUbicacion);
			datosUbicacion.setLayout(null);
			{
				JLabel lblProvincia = new JLabel("Provincia:");
				lblProvincia.setBounds(10, 20, 70, 14);
				datosUbicacion.add(lblProvincia);
			}
			{
				cbxProvincia = new JComboBox();
				cbxProvincia.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Azua", "Bahoruco", "Barahona", "Dajab\u00F3n", "Distrito Nacional", "Duarte", "El\u00EDas Pi\u00F1a", "El Seibo", "Espaillat", "Hato Mayor", "Hermanas Mirabal", "Independencia", "La Altagracia", "La Romana", "La Vega", "Mar\u00EDa Trinidad S\u00E1nchez", "Monse\u00F1or Nouel", "Monte Cristi", "Monte Plata", "Pedernales", "Peravia", "Puerto Plata", "Saman\u00E1", "San Crist\u00F3bal", "San Jos\u00E9 De Ocoa", "San Juan", "San Pedro de Macor\u00EDs", "S\u00E1nchez Ramirez", "Santiago", "Santiago Rodr\u00EDguez", "Santo Domingo", "Valverde"}));
				cbxProvincia.setBounds(78, 20, 165, 20);
				datosUbicacion.add(cbxProvincia);
			}
			{
				JLabel lblCiudad = new JLabel("Ciudad:");
				lblCiudad.setBounds(10, 58, 46, 14);
				datosUbicacion.add(lblCiudad);
			}
			{
				txtCiudad = new JTextField();
				txtCiudad.setBounds(78, 55, 165, 20);
				datosUbicacion.add(txtCiudad);
				txtCiudad.setColumns(10);
			}
			{
				JLabel lblSector = new JLabel("Sector:");
				lblSector.setBounds(276, 20, 61, 14);
				datosUbicacion.add(lblSector);
			}
			{
				txtSector = new JTextField();
				txtSector.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						char sect = e.getKeyChar();
						if((sect < 'a' || sect > 'z') && (sect < 'A' || sect > 'Z') && (sect != ' ' && sect != '�' && sect != '�' && sect != '�' && sect != '�' && sect != '�'))
							e.consume();
					}
				});
				txtSector.setBounds(327, 20, 241, 20);
				datosUbicacion.add(txtSector);
				txtSector.setColumns(10);
			}
			{
				JLabel lblCalle = new JLabel("Calle:");
				lblCalle.setBounds(276, 58, 46, 14);
				datosUbicacion.add(lblCalle);
			}
			{
				txtCalle = new JTextField();
				txtCalle.setBounds(327, 55, 122, 20);
				datosUbicacion.add(txtCalle);
				txtCalle.setColumns(10);
			}
			{
				JLabel lblNo = new JLabel("No:");
				lblNo.setBounds(459, 58, 27, 14);
				datosUbicacion.add(lblNo);
			}
			{
				spnNo = new JSpinner();
				SpinnerNumberModel num = new SpinnerNumberModel();
				num.setValue(0);
				num.setStepSize(1);
				num.setMinimum(0);
				spnNo.setModel(num);
				spnNo.setBounds(488, 55, 80, 20);
				datosUbicacion.add(spnNo);
			}
		}
		{
			JPanel tipoTrabajador = new JPanel();
			tipoTrabajador.setBorder(new TitledBorder(null, "Tipo de Trabajador", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			tipoTrabajador.setBounds(10, 292, 578, 51);
			contentPanel.add(tipoTrabajador);
			tipoTrabajador.setLayout(null);
			
			rbdJefeProyecto = new JRadioButton("Jefe de Proyecto");
			rbdJefeProyecto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//tipos de trabajadores
					rbdJefeProyecto.setSelected(true);
					rbdPlanificador.setSelected(false);
					rbdProgramador.setSelected(false);
					rbdDisenador.setSelected(false);
					//paneles activos
					jefeProyecto.setVisible(true);
					programador.setVisible(false);
					planeador.setVisible(false);
					Disenador.setVisible(false);
					//campos referentes a jefe de proyecto
					lbExperienciaJefe.setVisible(true);
					SpnExperienciaJefe.setVisible(true);
					//campos referentes a los demas trabajadores
					lbExperienciaPlanificador.setVisible(false);
					spnExperienciaPlaneador.setVisible(false);
					lbLenguajeDiseno.setVisible(false);
					cbxLenguajeDiseno.setVisible(false);
					lbLenguajeProgramador.setVisible(false);
					cbxLenguajeProgramador.setVisible(false);
					lbTipoProgramador.setVisible(false);
					cbxTipoProgramador.setVisible(false);
					
					
				}
			});
			rbdJefeProyecto.setSelected(true);
			rbdJefeProyecto.setBounds(10, 16, 146, 23);
			tipoTrabajador.add(rbdJefeProyecto);
			
			rbdProgramador = new JRadioButton("Programador");
			rbdProgramador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//tipos de trabajadores y paneles
					rbdProgramador.setSelected(true);
					rbdJefeProyecto.setSelected(false);
					rbdPlanificador.setSelected(false);
					rbdDisenador.setSelected(false);
					//paneles activos
					jefeProyecto.setVisible(false);
					programador.setVisible(true);
					planeador.setVisible(false);
					Disenador.setVisible(false);
					//campos referentes a programador
					lbLenguajeProgramador.setVisible(true);
					cbxLenguajeProgramador.setVisible(true);
					lbTipoProgramador.setVisible(true);
					cbxTipoProgramador.setVisible(true);
					//campos referentes a los demas trabajadores
					lbExperienciaPlanificador.setVisible(false);
					spnExperienciaPlaneador.setVisible(false);
					lbLenguajeDiseno.setVisible(false);
					cbxLenguajeDiseno.setVisible(false);
					lbExperienciaJefe.setVisible(false);
					SpnExperienciaJefe.setVisible(false);
				}
			});
			rbdProgramador.setBounds(162, 16, 109, 23);
			tipoTrabajador.add(rbdProgramador);
			
			rbdPlanificador = new JRadioButton("Planificador");
			rbdPlanificador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//tipos de trabajadores
					rbdPlanificador.setSelected(true);
					rbdProgramador.setSelected(false);
					rbdJefeProyecto.setSelected(false);
					rbdDisenador.setSelected(false);
					//paneles activos
					jefeProyecto.setVisible(false);
					programador.setVisible(false);
					planeador.setVisible(true);
					Disenador.setVisible(false);
					//campos referentes a planificador
					lbExperienciaPlanificador.setVisible(true);
					spnExperienciaPlaneador.setVisible(true);
					//campos referentes a los demas trabajadores
					lbLenguajeDiseno.setVisible(false);
					cbxLenguajeDiseno.setVisible(false);
					lbExperienciaJefe.setVisible(false);
					SpnExperienciaJefe.setVisible(false);
					lbLenguajeProgramador.setVisible(false);
					cbxLenguajeProgramador.setVisible(false);
					lbTipoProgramador.setVisible(false);
					cbxTipoProgramador.setVisible(false);
				}
			});
			rbdPlanificador.setBounds(314, 16, 109, 23);
			tipoTrabajador.add(rbdPlanificador);
			
			rbdDisenador = new JRadioButton("Dise\u00F1ador");
			rbdDisenador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//tipos de trabajadores
					rbdDisenador.setSelected(true);
					rbdPlanificador.setSelected(false);
					rbdProgramador.setSelected(false);
					rbdJefeProyecto.setSelected(false);
					//paneles activos
					jefeProyecto.setVisible(false);
					programador.setVisible(false);
					planeador.setVisible(false);
					Disenador.setVisible(true);
					//campos referentes a disenador
					lbLenguajeDiseno.setVisible(true);
					cbxLenguajeDiseno.setVisible(true);
					//campos referentes a los demas trabajadores
					lbExperienciaJefe.setVisible(false);
					SpnExperienciaJefe.setVisible(false);
					lbLenguajeProgramador.setVisible(false);
					cbxLenguajeProgramador.setVisible(false);
					lbTipoProgramador.setVisible(false);
					cbxTipoProgramador.setVisible(false);
					lbExperienciaPlanificador.setVisible(false);
					spnExperienciaPlaneador.setVisible(false);
				}
			});
			rbdDisenador.setBounds(466, 16, 109, 23);
			tipoTrabajador.add(rbdDisenador);
		}
		
		jefeProyecto = new JPanel();
		jefeProyecto.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jefeProyecto.setBounds(10, 354, 578, 66);
		contentPanel.add(jefeProyecto);
		jefeProyecto.setLayout(null);
		
		lbExperienciaJefe = new JLabel("A\u00F1os de Experiencia:");
		lbExperienciaJefe.setBounds(13, 24, 144, 14);
		jefeProyecto.add(lbExperienciaJefe);
		
		SpnExperienciaJefe = new JSpinner();
		SpinnerNumberModel ExpJefe = new SpinnerNumberModel();
		ExpJefe.setValue(0);
		ExpJefe.setStepSize(1);
		ExpJefe.setMinimum(0);
		SpnExperienciaJefe.setModel(ExpJefe);
		SpnExperienciaJefe.setBounds(140, 21, 136, 20);
		jefeProyecto.add(SpnExperienciaJefe);
		
		programador = new JPanel();
		programador.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		programador.setBounds(10, 354, 578, 66);
		contentPanel.add(programador);
		programador.setLayout(null);
		{
			lbLenguajeProgramador = new JLabel("Lenguaje:");
			lbLenguajeProgramador.setBounds(13, 24, 72, 14);
			programador.add(lbLenguajeProgramador);
			
			
		}
		{
			cbxLenguajeProgramador = new JComboBox();
			cbxLenguajeProgramador.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Java", "C", "C++", "Python", "C#", "Visual Basic. NET", "JavaScript", "PHP", "Perl", "Assembly language"}));
			cbxLenguajeProgramador.setBounds(80, 21, 159, 20);
			programador.add(cbxLenguajeProgramador);
		}
		{
			lbTipoProgramador = new JLabel("Tipo de programador:");
			lbTipoProgramador.setBounds(300, 24, 140, 14);
			programador.add(lbTipoProgramador);
		}
		{
			cbxTipoProgramador = new JComboBox();
			cbxTipoProgramador.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Escritorio", "Web"}));
			cbxTipoProgramador.setBounds(435, 24, 128, 20);
			programador.add(cbxTipoProgramador);
		}
		{
			planeador = new JPanel();
			planeador.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			planeador.setBounds(10, 354, 578, 66);
			contentPanel.add(planeador);
			planeador.setLayout(null);
			
			lbExperienciaPlanificador = new JLabel("A\u00F1os de experiencia:");
			lbExperienciaPlanificador.setBounds(13, 24, 144, 14);
			planeador.add(lbExperienciaPlanificador);
			
			spnExperienciaPlaneador = new JSpinner();
			SpinnerNumberModel ExpPlanificador = new SpinnerNumberModel();
			ExpPlanificador.setValue(0);
			ExpPlanificador.setStepSize(1);
			ExpPlanificador.setMinimum(0);
			spnExperienciaPlaneador.setModel(ExpPlanificador);
			spnExperienciaPlaneador.setBounds(140, 21, 136, 20);
			planeador.add(spnExperienciaPlaneador);
		}
		{
			Disenador = new JPanel();
			Disenador.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			Disenador.setBounds(10, 354, 578, 66);
			contentPanel.add(Disenador);
			Disenador.setLayout(null);
			
			lbLenguajeDiseno = new JLabel("Lenguaje de dise\u00F1o:");
			lbLenguajeDiseno.setBounds(13, 24, 113, 14);
			Disenador.add(lbLenguajeDiseno);
			
			cbxLenguajeDiseno = new JComboBox();
			cbxLenguajeDiseno.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Java", "C", "C++", "Python", "C#", "Visual Basic. NET", "JavaScript", "PHP", "Perl", "Assembly language"}));
			cbxLenguajeDiseno.setBounds(135, 21, 159, 20);
			Disenador.add(cbxLenguajeDiseno);
			
			
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						JefeDeProyecto jefeDeProyecto = null;
						Programador programador =null;
						Planificador planificador= null;
						Dise�ador disenador = null;
						//datos generales 
						String cedula = ftCedula.getText();
						String nom = txtNombres.getText();
						String apellido = txtApellidos.getText();
						String telef = ftTelefono.getText();
						String pago = txtPago.getText();
						int edad = new Integer(spnEdad.getValue().toString());
						String sex = cbxSexo.getSelectedItem().toString();
						int horasTrab = new Integer(spnHorasTrab.getValue().toString());
						//datos de ubicacion
						String provincia = cbxProvincia.getSelectedItem().toString();
						String ciudad = txtCiudad.getText();
						String sector = txtSector.getText();
						String calle = txtCalle.getText();
						int numCalle = new Integer(spnNo.getValue().toString());
						
						//
						if (ftCedula.getText().equals("___-_______-_")||txtApellidos.getText().equals("")||cbxProvincia.getSelectedIndex()==0||txtNombres.getText().equals("")||cbxSexo.getSelectedIndex()==0||ftTelefono.getText().equals("___-___-____")||txtPago.getText().equals("")||txtSector.getText().equals("")||txtCalle.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Por favor llene todos los campos para continuar","Hay campos obligatorios vacios", JOptionPane.WARNING_MESSAGE, null);
					    else if ((rbdProgramador.isSelected()&&(cbxLenguajeProgramador.getSelectedIndex()==0||cbxTipoProgramador.getSelectedIndex()==0))||rbdDisenador.isSelected()&&(cbxLenguajeDiseno.getSelectedIndex()==0)) {
					    	JOptionPane.showMessageDialog(null, "Hay campos obligatorios vacios","Por favor llene todos los campos para continuar", JOptionPane.WARNING_MESSAGE, null);
					    }
					    else if(buscarTrabajador(ftCedula.getText())){
					    	JOptionPane.showMessageDialog(null,"El trabajador ya existe");
					    }
					    else if(rbdJefeProyecto.isSelected()){
					    	
					    	jefeDeProyecto = new JefeDeProyecto();
					    	jefeDeProyecto.setAnosExperiencia((int) SpnExperienciaJefe.getValue());	
					    	EmpresaRps.getInstance().getMistrabajadores().add(jefeDeProyecto);
					    	jefeDeProyecto.setDireccion(cbxProvincia.getSelectedItem()+"/"+txtCiudad.getText()+"/"+txtSector.getText()+"/"+txtCalle.getText()+"/"+spnNo.getValue());
					    	System.out.println(EmpresaRps.getInstance().getMistrabajadores().size());
					    	resetearCampo();
					    }
					    else if (rbdProgramador.isSelected()) {
					    	programador = new Programador();
					    	programador.setTipoProgamador(cbxTipoProgramador.getSelectedItem().toString());
					    	programador.setLenguajeProgramacion(cbxLenguajeProgramador.getSelectedItem().toString());
					    	EmpresaRps.getInstance().getMistrabajadores().add(programador);
					    	programador.setDireccion(cbxProvincia.getSelectedItem()+"/"+txtCiudad+"/"+txtSector.getText()+"/"+txtCalle.getText()+"/"+spnNo.getValue());
					    	System.out.println(EmpresaRps.getInstance().getMistrabajadores().size());
					    	resetearCampo();
						}
					    else if (rbdPlanificador.isSelected()) {
					    	planificador = new Planificador();
					    	planificador.setAnosExp((int) spnExperienciaPlaneador.getValue());
					    	EmpresaRps.getInstance().getMistrabajadores().add(planificador);
					    	planificador.setDireccion(cbxProvincia.getSelectedItem()+"/"+txtCiudad+"/"+txtSector.getText()+"/"+txtCalle.getText()+"/"+spnNo.getValue());
					    	System.out.println(EmpresaRps.getInstance().getMistrabajadores().size());
					    	resetearCampo();
						}
					    else if(rbdDisenador.isSelected()){
					    	disenador = new Dise�ador();
					    	disenador.setLenguajeDiseno(cbxLenguajeDiseno.getSelectedItem().toString());
					    	EmpresaRps.getInstance().getMistrabajadores().add(disenador);
					    	disenador.setDireccion(cbxProvincia.getSelectedItem()+"/"+txtCiudad+"/"+txtSector.getText()+"/"+txtCalle.getText()+"/"+spnNo.getValue());
					    	System.out.println(EmpresaRps.getInstance().getMistrabajadores().size());
					    	resetearCampo();
					    	
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
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	 public boolean buscarTrabajador(String cedula){
	    	boolean aux = false;
	    	for (Trabajador trab : EmpresaRps.getInstance().getMistrabajadores()) {
				if(trab.getCedula().equalsIgnoreCase(cedula)){
					aux = true;
				}
			}
	    	return aux;
	    	
	    }
	 
	 private void resetearCampo() {
		 ftCedula.setValue(null);
		   txtApellidos.setText("");
		   txtNombres.setText("");
		   cbxSexo.setSelectedIndex(0);
		   txtPago.setText("");
		   spnHorasTrab.setValue(8);
		   spnEdad.setValue(20);
		   ftTelefono.setValue(null);
		   cbxProvincia.setSelectedIndex(0);
		   txtCiudad.setText("");
		   txtSector.setText("");
		   txtCalle.setText("");
		   spnNo.setValue(0);
		 //tipos de trabajadores
			rbdJefeProyecto.setSelected(true);
			rbdPlanificador.setSelected(false);
			rbdProgramador.setSelected(false);
			rbdDisenador.setSelected(false);
			//paneles activos
			jefeProyecto.setVisible(true);
			programador.setVisible(false);
			planeador.setVisible(false);
			Disenador.setVisible(false);
			//campos referentes a jefe de proyecto
			lbExperienciaJefe.setVisible(true);
			SpnExperienciaJefe.setVisible(true);
			//campos referentes a los demas trabajadores
			lbExperienciaPlanificador.setVisible(false);
			spnExperienciaPlaneador.setVisible(false);
			lbLenguajeDiseno.setVisible(false);
			cbxLenguajeDiseno.setVisible(false);
			lbLenguajeProgramador.setVisible(false);
			cbxLenguajeProgramador.setVisible(false);
			lbTipoProgramador.setVisible(false);
			cbxTipoProgramador.setVisible(false);
			
		}
}
