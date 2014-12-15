import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class RegisterScreen {

	private JFrame frame;
	private JTable tableRegister;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterScreen window = new RegisterScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegisterScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 310, 350);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRegistradores = new JLabel("Registradores:");
		lblRegistradores.setBounds(10, 11, 110, 14);
		frame.getContentPane().add(lblRegistradores);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 32, 274, 269);
		frame.getContentPane().add(scrollPane);
		
		
		startTable();
		scrollPane.setViewportView(tableRegister);
	}

	public void startTable(){
		String[] colunas = {"Nome", "Valor"};
		String[][] conteudo = new String[25][2];
		for(int i = 0; i< 25; i++){
			for(int j = 0; j<2; j++){
				if(j==0){
					conteudo[i][j] = Processador.pubReg.get(i).getNome();
				}else{
					conteudo[i][j] = Integer.toString(Processador.pubReg.get(i).getValor());
				}
			}
		}
		DefaultTableModel modelo = new DefaultTableModel(conteudo, colunas);
		tableRegister = null;
		tableRegister = new JTable(modelo);
	}
	
	public void updateTable(){
		for(int i = 0; i < 25; i++){
			tableRegister.getModel().setValueAt(Integer.toString(Processador.pubReg.get(i).getValor()), i, 1);
		}
	}
	
	public JFrame getFrame(){
		return this.frame;
	}
}
