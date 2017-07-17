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
import logica.Contrato;
import logica.Empresa;
import logica.EmpresaRps;
import logica.Indepediente;
import logica.Proyecto;
import logica.Trabajador;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

public class CrearContrato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtProyecto;
	private JTextField txtLenguaje;
	private JTextField txtRD;
	private JTextField txtUS;
	private JLabel lblCedula;
	private JLabel lblCliente;
	private JFormattedTextField ftxCodigo;
	private JFormattedTextField ftxCedula;
	private JFormattedTextField ftxEmpresa;
	private JRadioButton rdbIndependiente;
	private JRadioButton rdbEmpresa;
	private JDateChooser dtcNow;
	private JDateChooser dtcFinal;
	private JSpinner spnTasa;
	private static Cliente cliente;
	private JTextField txtObs;
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			CrearContrato dialog = new CrearContrato();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param nuevo 
	 */
	public CrearContrato(Proyecto proyect) {
		setTitle("Creacion de Contrato");
		setBounds(100, 100, 552, 430);
		/*Indepediente c = new Indepediente("000-0000000-1", "mario", "", "", "", "", "", "");
		EmpresaRps.getInstance().getMisclientes().add(c);*/
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Detalles del contrato", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(128, 128, 128)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Fecha inicial:");
			lblNewLabel_1.setBounds(10, 164, 104, 22);
			panel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Fecha final:");
			lblNewLabel_2.setBounds(270, 164, 93, 22);
			panel.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Proyecto:");
			lblNewLabel_3.setBounds(10, 214, 62, 22);
			panel.add(lblNewLabel_3);
			
			txtProyecto = new JTextField();
			txtProyecto.setText(proyect.getNombreproyecto());
			txtProyecto.setEnabled(false);
			txtProyecto.setBounds(89, 214, 150, 23);
			panel.add(txtProyecto);
			txtProyecto.setColumns(10);
			
			JLabel lblLenguaje = new JLabel("Lenguaje:");
			lblLenguaje.setBounds(10, 264, 62, 22);
			panel.add(lblLenguaje);
			
			txtLenguaje = new JTextField();
			txtLenguaje.setText(proyect.getLenguaje());
			txtLenguaje.setEnabled(false);
			txtLenguaje.setBounds(89, 264, 150, 23);
			panel.add(txtLenguaje);
			txtLenguaje.setColumns(10);
			
			JLabel lblTasa = new JLabel("Tasa:");
			lblTasa.setBounds(270, 264, 46, 22);
			panel.add(lblTasa);
			
			spnTasa = new JSpinner();
			spnTasa.setBounds(347, 264, 150, 23);
			spnTasa.setModel(new SpinnerNumberModel(new Float(48), new Float(34), new Float(65), new Float(1)));
			panel.add(spnTasa);
			
			JLabel lblPrecioRd = new JLabel("Precio RD$:");
			lblPrecioRd.setBounds(10, 314, 81, 22);
			panel.add(lblPrecioRd);
			
			txtRD = new JTextField();
			txtRD.setText("0");
			txtRD.setEnabled(false);
			txtRD.setBounds(89, 314, 150, 23);
			panel.add(txtRD);
			txtRD.setColumns(10);
			
			JLabel lblPrecioUs = new JLabel("Precio US$:");
			lblPrecioUs.setBounds(270, 314, 110, 22);
			panel.add(lblPrecioUs);
			
			txtUS = new JTextField();
			txtUS.setText("0");
			txtUS.setEnabled(false);
			txtUS.setBounds(347, 314, 150, 23);
			panel.add(txtUS);
			txtUS.setColumns(10);
			
			JPanel panel_1 = new JPanel();
			panel_1.setForeground(Color.GRAY);
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
			panel_1.setBounds(10, 22, 506, 114);
			panel.add(panel_1);
			panel_1.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("C\u00F3digo: ");
				lblNewLabel.setBounds(10, 30, 46, 22);
				panel_1.add(lblNewLabel);
			}
			{
				ftxCodigo = new JFormattedTextField();
				ftxCodigo.setBounds(78, 30, 138, 23);
				panel_1.add(ftxCodigo);
				ftxCodigo.setText(Contrato.getCode());
				ftxCodigo.setEnabled(false);
			}
			{
				lblCliente = new JLabel("Empresa:");
				lblCliente.setBounds(10, 80, 70, 22);
				panel_1.add(lblCliente);
			}
			{
				ftxCedula = new JFormattedTextField(createFormatter("###-#######-#"));
				ftxCedula.setBounds(78, 81, 138, 23);
				ftxCedula.setVisible(false);
				panel_1.add(ftxCedula);
			}
			
			JButton btnNewButton = new JButton("New button");
			btnNewButton.setBounds(226, 80, 32, 23);
			panel_1.add(btnNewButton);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(268, 80, 197, 23);
			panel_1.add(txtNombre);
			txtNombre.setEditable(false);
			txtNombre.setColumns(10);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo De Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
			panel_2.setBounds(254, 11, 225, 58);
			panel_1.add(panel_2);
			panel_2.setLayout(null);
			
			rdbEmpresa = new JRadioButton("Empresa");
			rdbEmpresa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ftxEmpresa.setVisible(true);
					lblCliente.setVisible(true);
					lblCedula.setVisible(false);
					ftxCedula.setVisible(false);
					rdbIndependiente.setSelected(false);
				}
			});
			rdbEmpresa.setSelected(true);
			rdbEmpresa.setBounds(6, 20, 86, 23);
			panel_2.add(rdbEmpresa);
			
			rdbIndependiente = new JRadioButton("Independiente");
			rdbIndependiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ftxEmpresa.setVisible(false);
					lblCliente.setVisible(false);
					lblCedula.setVisible(true);
					ftxCedula.setVisible(true);
					rdbEmpresa.setSelected(false);
				}
			});
			rdbIndependiente.setBounds(100, 20, 119, 23);
			panel_2.add(rdbIndependiente);
			
			ftxEmpresa = new JFormattedTextField(createFormatter("###-#####-#"));
			ftxEmpresa.setBounds(78, 81, 138, 23);
			panel_1.add(ftxEmpresa);
			ftxEmpresa.setVisible(true);
			
			lblCedula = new JLabel("Cedula");
			lblCedula.setVisible(false);
			lblCedula.setBounds(10, 84, 46, 14);
			panel_1.add(lblCedula);
			
			dtcNow = new JDateChooser();
			Calendar now = Calendar.getInstance();
			dtcNow.setCalendar(now);
			dtcNow.setBounds(89, 164, 150, 23);
			panel.add(dtcNow);
			
			dtcFinal = new JDateChooser();
			dtcFinal.setBounds(347, 164, 150, 23);
			now.add(Calendar.DAY_OF_MONTH, 1);
			dtcFinal.setMinSelectableDate(now.getTime());
			now.add(Calendar.DAY_OF_MONTH, -1);
			now.add(Calendar.MONTH, 1);
			dtcFinal.setCalendar(now);
			panel.add(dtcFinal);
			
			JLabel lblObs = new JLabel("Obs.:");
			lblObs.setBounds(270, 218, 46, 14);
			panel.add(lblObs);
			
			txtObs = new JTextField();
			txtObs.setText(proyect.getTipo());
			txtObs.setEditable(false);
			txtObs.setBounds(347, 214, 150, 23);
			panel.add(txtObs);
			txtObs.setColumns(10);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rdbEmpresa.isSelected())
						for (Cliente client : EmpresaRps.getInstance().getMisclientes())
							if(client instanceof Empresa)
								if(((Empresa)client).getRnc().equalsIgnoreCase(ftxEmpresa.getText())){
									cliente = client;
									txtNombre.setText(cliente.getNombre());
								}else{
									JOptionPane.showMessageDialog(null, "Este empresa RNC: "+ftxEmpresa.getText()+", NO ESTA REGISTRADA", "Error",JOptionPane.ERROR_MESSAGE);
								}
					if(rdbIndependiente.isSelected())
						for (Cliente client1 : EmpresaRps.getInstance().getMisclientes())
							if(client1 instanceof Indepediente)
								if(((Indepediente)client1).getCedula().equalsIgnoreCase(ftxCedula.getText())){
									cliente = client1;
									txtNombre.setText(cliente.getNombre());
								}else{
									JOptionPane.showMessageDialog(null, "Este cliente ceduka: "+ftxCedula.getText()+", NO ESTA REGISTRADA", "Error",JOptionPane.ERROR_MESSAGE);
								}
					
				}
			});
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Salvar Contrato");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Contrato nuevoc = new Contrato(ftxCodigo.getText(), cliente, dtcNow.getCalendar(), dtcFinal.getCalendar(), proyect, Float.valueOf(txtRD.getText()), (float) spnTasa.getValue(), Float.valueOf(txtUS.getText()));
						activarEmpleados(nuevoc);
						EmpresaRps.getInstance().getMiscontratos().add(nuevoc);
						EmpresaRps.getInstance().getMisproyectos().add(proyect);
						JOptionPane.showMessageDialog(null, "El proyecto :"+proyect.getCodigo()+", y el contrato: "+nuevoc.getCodigoContrato()+", Se han salvado existosamente", "Informacion",JOptionPane.INFORMATION_MESSAGE);
						dispose();
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
						int opcion = JOptionPane.showConfirmDialog(null, "Desea cerra??  No sé salvará ningún Dato y se borrara el Proyecto","Advertencia",JOptionPane.WARNING_MESSAGE);
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
	static MaskFormatter createFormatter(String format) {//validador numeros X un formato dado
		MaskFormatter mask = null;
		try {
            mask = new MaskFormatter(format);
            mask.setPlaceholderCharacter(' ');
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
		return mask;
	}
	private void activarEmpleados(Contrato nuevoc) {
		for (Trabajador worker : EmpresaRps.getInstance().getMistrabajadores()) {
			for (Trabajador trabajador : nuevoc.getProyecto().getJefe().getMisTrabajadores()) {
				if(worker == trabajador){
					worker.setDisponible(false);
					trabajador.setDisponible(false);
				}
			}
		}
		
	}
}
