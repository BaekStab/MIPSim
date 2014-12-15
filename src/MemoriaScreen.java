import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MemoriaScreen {

	private JFrame frame;
	private JTable tableMemoria;
	private Memoria mem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Memoria mem = null;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemoriaScreen window = new MemoriaScreen(mem);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setMemoria(Memoria mem){
		this.mem = mem;
	}
	
	public void nullTable(){
		this.tableMemoria = null;
	}
	
	public JFrame getFrame(){
		return this.frame;
	}
	
	/**
	 * Create the application.
	 */
	public MemoriaScreen(Memoria mem) {
		this.mem = mem;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 413, 430);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMemria = new JLabel("Mem\u00F3ria");
		lblMemria.setBounds(10, 11, 63, 14);
		frame.getContentPane().add(lblMemria);
		
		JLabel lblVerEm = new JLabel("Ver em:");
		lblVerEm.setBounds(187, 11, 46, 14);
		frame.getContentPane().add(lblVerEm);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 377, 333);
		frame.getContentPane().add(scrollPane);
		
		startTable();
		updateTable();
		scrollPane.setViewportView(tableMemoria);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Binario", "Hexa", "Decimal"}));
		comboBox.setBounds(243, 8, 103, 20);
		comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Object selected = comboBox.getSelectedItem();
                String format = selected.toString();
                updateNumFormat(format);
                	
            }
        });
		frame.getContentPane().add(comboBox);
	}
	
	public void startTable(){
		String[] colunas = {"Index", "+1", "+2", "+3", "+4"};
		String[][] conteudo = new String[150][5];
		int hexa = 0;
		for(int i = 0; i< 150; i++){
			for(int j = 0; j<5; j++){
				if(j==0){
					conteudo[i][j] = "0x"+Integer.toHexString(hexa).toUpperCase();
				}else{
					conteudo[i][j] = "0";
				}
			}
			hexa += 4;
		}
		DefaultTableModel modelo = new DefaultTableModel(conteudo, colunas);
		tableMemoria = null;
		tableMemoria = new JTable(modelo);
	}
	
	public void updateTable(){
		for(int i = 0; i< 150; i++){
			for(int j = 1; j<5; j++){
				tableMemoria.getModel().setValueAt(mem.read(i,j-1),i, j);
			}
		}
	}
	
	public void updateNumFormat(String format){
		for(int i= 0; i<150; i++){
			 for(int j=1; j<5; j++){
				 
				 if(!(((String)tableMemoria.getValueAt(i, j)).equals("0"))){
					 
						if(format.equals("Binario")){
							tableMemoria.getModel().setValueAt(Integer.toBinaryString(Integer.parseInt((String)tableMemoria.getValueAt(i, j),2)), i, j);
						}else if(format.equals("Hexa")){
							tableMemoria.getModel().setValueAt(Integer.toHexString(Integer.parseInt((String)tableMemoria.getValueAt(i, j),16)), i, j);
						}else if(format.equals("Decimal")){
							tableMemoria.getModel().setValueAt(Integer.parseInt((String)tableMemoria.getValueAt(i, j),10), i, j);
						}
					 
				 }
			 }

		}
	}
}
