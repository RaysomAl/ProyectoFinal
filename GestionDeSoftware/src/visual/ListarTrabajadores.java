package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;

public class ListarTrabajadores extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarTrabajadores dialog = new ListarTrabajadores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarTrabajadores() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarTrabajadores.class.getResource("/img/listar32.png")));
		setTitle("Lista de Trabajadores");
		setResizable(false);
		setBounds(100, 100,  1050, 597);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel ListaTrabajadores = new JPanel();
		ListaTrabajadores.setBorder(new TitledBorder(null, "Lista de trabajadores", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		ListaTrabajadores.setBounds(12, 82, 691, 452);
		contentPanel.add(ListaTrabajadores);
		ListaTrabajadores.setLayout(null);
		
		JScrollPane scrollPaneLista = new JScrollPane();
		scrollPaneLista.setBounds(10, 21, 671, 420);
		ListaTrabajadores.add(scrollPaneLista);
		
		JPanel proyectosActivos = new JPanel();
		proyectosActivos.setBorder(new TitledBorder(null, "Proyectos activos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		proyectosActivos.setBounds(715, 11, 319, 523);
		contentPanel.add(proyectosActivos);
		proyectosActivos.setLayout(null);
		
		JScrollPane scrollPaneProyectos = new JScrollPane();
		scrollPaneProyectos.setBounds(10, 21, 299, 491);
		proyectosActivos.add(scrollPaneProyectos);
		
		JPanel FiltroTipo = new JPanel();
		FiltroTipo.setBorder(new TitledBorder(null, "Filtro por Tipo", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		FiltroTipo.setBounds(12, 11, 329, 60);
		contentPanel.add(FiltroTipo);
		FiltroTipo.setLayout(null);
		
		JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setBounds(10, 24, 61, 14);
		FiltroTipo.add(lblFiltro);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(81, 21, 198, 20);
		FiltroTipo.add(comboBox);
		
		JPanel Busquedad = new JPanel();
		Busquedad.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "B\u00FAsqueda", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Busquedad.setBounds(390, 11, 313, 60);
		contentPanel.add(Busquedad);
		Busquedad.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00E9dula:");
		lblNewLabel.setBounds(10, 23, 59, 14);
		Busquedad.add(lblNewLabel);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(67, 20, 222, 20);
		Busquedad.add(formattedTextField);
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
