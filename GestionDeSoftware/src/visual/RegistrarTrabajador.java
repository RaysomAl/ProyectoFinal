package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JRadioButton;

public class RegistrarTrabajador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

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
			{
				JLabel lblNewLabel_1 = new JLabel("Nombres:");
				lblNewLabel_1.setBounds(10, 59, 46, 14);
				datosGenerales.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Apellidos:");
				lblNewLabel_2.setBounds(10, 96, 46, 14);
				datosGenerales.add(lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Sexo:");
				lblNewLabel_3.setBounds(10, 133, 46, 14);
				datosGenerales.add(lblNewLabel_3);
			}
			{
				JFormattedTextField formattedTextField = new JFormattedTextField();
				formattedTextField.setBounds(77, 19, 154, 20);
				datosGenerales.add(formattedTextField);
			}
			{
				textField = new JTextField();
				textField.setBounds(77, 56, 154, 20);
				datosGenerales.add(textField);
				textField.setColumns(10);
			}
			{
				textField_1 = new JTextField();
				textField_1.setBounds(77, 93, 154, 20);
				datosGenerales.add(textField_1);
				textField_1.setColumns(10);
			}
			{
				JComboBox comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Masculino", "Femenino"}));
				comboBox.setBounds(77, 130, 154, 20);
				datosGenerales.add(comboBox);
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
				lblNewLabel_7.setBounds(269, 133, 110, 14);
				datosGenerales.add(lblNewLabel_7);
			}
			{
				JSpinner spinner = new JSpinner();
				spinner.setBounds(390, 93, 178, 20);
				datosGenerales.add(spinner);
			}
			{
				textField_2 = new JTextField();
				textField_2.setBounds(389, 130, 179, 20);
				datosGenerales.add(textField_2);
				textField_2.setColumns(10);
			}
			{
				JFormattedTextField formattedTextField = new JFormattedTextField();
				formattedTextField.setBounds(390, 56, 178, 20);
				datosGenerales.add(formattedTextField);
			}
			{
				JSpinner spinner = new JSpinner();
				spinner.setBounds(390, 19, 178, 20);
				datosGenerales.add(spinner);
			}
		}
		{
			JPanel datosUbicacion = new JPanel();
			datosUbicacion.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos de Ubucaci\u00F3n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			datosUbicacion.setBounds(10, 192, 578, 89);
			contentPanel.add(datosUbicacion);
			datosUbicacion.setLayout(null);
			{
				JLabel lblProvincia = new JLabel("Provincia:");
				lblProvincia.setBounds(10, 20, 70, 14);
				datosUbicacion.add(lblProvincia);
			}
			{
				JComboBox comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Azua", "Bahoruco", "Barahona", "Dajab\u00F3n", "Distrito Nacional", "Duarte", "El\u00EDas Pi\u00F1a", "El Seibo", "Espaillat", "Hato Mayor", "Hermanas Mirabal", "Independencia", "La Altagracia", "La Romana", "La Vega", "Mar\u00EDa Trinidad S\u00E1nchez", "Monse\u00F1or Nouel", "Monte Cristi", "Monte Plata", "Pedernales", "Peravia", "Puerto Plata", "Saman\u00E1", "San Crist\u00F3bal", "San Jos\u00E9 De Ocoa", "San Juan", "San Pedro de Macor\u00EDs", "S\u00E1nchez Ramirez", "Santiago", "Santiago Rodr\u00EDguez", "Santo Domingo", "Valverde"}));
				comboBox.setBounds(78, 20, 165, 20);
				datosUbicacion.add(comboBox);
			}
			{
				JLabel lblCiudad = new JLabel("Ciudad:");
				lblCiudad.setBounds(10, 58, 46, 14);
				datosUbicacion.add(lblCiudad);
			}
			{
				textField_3 = new JTextField();
				textField_3.setBounds(78, 55, 165, 20);
				datosUbicacion.add(textField_3);
				textField_3.setColumns(10);
			}
			{
				JLabel lblSector = new JLabel("Sector:");
				lblSector.setBounds(276, 20, 61, 14);
				datosUbicacion.add(lblSector);
			}
			{
				textField_4 = new JTextField();
				textField_4.setBounds(316, 20, 252, 20);
				datosUbicacion.add(textField_4);
				textField_4.setColumns(10);
			}
			{
				JLabel lblCalle = new JLabel("Calle:");
				lblCalle.setBounds(276, 58, 46, 14);
				datosUbicacion.add(lblCalle);
			}
			{
				textField_5 = new JTextField();
				textField_5.setBounds(316, 55, 134, 20);
				datosUbicacion.add(textField_5);
				textField_5.setColumns(10);
			}
			{
				JLabel lblNo = new JLabel("No:");
				lblNo.setBounds(459, 58, 27, 14);
				datosUbicacion.add(lblNo);
			}
			{
				JSpinner spinner = new JSpinner();
				spinner.setBounds(488, 55, 80, 20);
				datosUbicacion.add(spinner);
			}
		}
		{
			JPanel tipoTrabajador = new JPanel();
			tipoTrabajador.setBorder(new TitledBorder(null, "Tipo de Trabajador", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			tipoTrabajador.setBounds(10, 292, 578, 51);
			contentPanel.add(tipoTrabajador);
			tipoTrabajador.setLayout(null);
			
			JRadioButton rdbtnJefeDeProyecto = new JRadioButton("Jefe de Proyecto");
			rdbtnJefeDeProyecto.setSelected(true);
			rdbtnJefeDeProyecto.setBounds(10, 16, 109, 23);
			tipoTrabajador.add(rdbtnJefeDeProyecto);
			
			JRadioButton rdbtnProgramador = new JRadioButton("Programador");
			rdbtnProgramador.setBounds(162, 16, 109, 23);
			tipoTrabajador.add(rdbtnProgramador);
			
			JRadioButton rdbtnPlaneador = new JRadioButton("Planificador");
			rdbtnPlaneador.setBounds(314, 16, 109, 23);
			tipoTrabajador.add(rdbtnPlaneador);
			
			JRadioButton rdbtnDiseador = new JRadioButton("Dise\u00F1ador");
			rdbtnDiseador.setBounds(466, 16, 109, 23);
			tipoTrabajador.add(rdbtnDiseador);
		}
		
		JPanel jefeProyecto = new JPanel();
		jefeProyecto.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jefeProyecto.setBounds(10, 354, 578, 66);
		contentPanel.add(jefeProyecto);
		jefeProyecto.setLayout(null);
		
		JLabel lblAosDeExperiencia = new JLabel("A\u00F1os de Experiencia:");
		lblAosDeExperiencia.setBounds(13, 24, 113, 14);
		jefeProyecto.add(lblAosDeExperiencia);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(129, 21, 136, 20);
		jefeProyecto.add(spinner);
		
		JPanel programador = new JPanel();
		programador.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		programador.setBounds(10, 354, 578, 66);
		contentPanel.add(programador);
		programador.setLayout(null);
		{
			JLabel lblNewLabel_8 = new JLabel("Lenguaje:");
			lblNewLabel_8.setBounds(13, 24, 72, 14);
			programador.add(lblNewLabel_8);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(107, 21, 159, 20);
			programador.add(comboBox);
		}
		{
			JLabel lblNewLabel_9 = new JLabel("Tipo de programador:");
			lblNewLabel_9.setBounds(300, 24, 121, 14);
			programador.add(lblNewLabel_9);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(440, 24, 128, 20);
			programador.add(comboBox);
		}
		{
			JPanel planeador = new JPanel();
			planeador.setBounds(10, 354, 578, 66);
			contentPanel.add(planeador);
		}
		{
			JPanel Disenador = new JPanel();
			Disenador.setBounds(10, 354, 578, 66);
			contentPanel.add(Disenador);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
