import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Label;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.Button;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Panel;

import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;


public class Principal {

	public static JLabel viewReg1, viewReg2, viewReg3, viewReg4, viewPC, lblMemoria, lblUC, lblULA, lblMemBus, lblUCBus, lblULABus, lblViewStatus;
	public static Boolean animations;
	private JFrame frame;
	private MemoriaScreen memoriaScreen;
	private RegisterScreen registerScreen;
	private Processador processador;
	private Memoria memoria;
	private JComboBox combo1, combo2, combo3, combo4;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void attStatus(String novo){
		Principal.lblViewStatus.setText(novo);
	}
	
	/*private void animMontador(){
		lblMemBus.setIcon();
	}*/
	
	
	/**
	 * Create the application.
	 */
	public Principal() {
		memoriaScreen = null;
		animations = true;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		memoria = new Memoria();
		processador = new Processador();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setBackground(SystemColor.control);
		frame.setBounds(100, 100, 552, 544);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 258, 218);
		frame.getContentPane().add(scrollPane);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		scrollPane.setViewportView(editorPane);
		
		combo1 = new JComboBox();
		combo1.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		combo1.setModel(new DefaultComboBoxModel(new String[] {"$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7", "$t8", "$t9", "$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7", "$a0", "$a1", "$a2", "$a3", "$v0", "$v1"}));
		combo1.setSelectedIndex(0);
		combo1.setBounds(278, 11, 84, 20);
		combo1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Object selected = combo1.getSelectedItem();
				viewReg1.setText(getRegVal(((String)selected).substring(1)));
			}
		});
		frame.getContentPane().add(combo1);
		
		viewReg1 = new JLabel("0000");
		viewReg1.setOpaque(true);
		viewReg1.setForeground(Color.WHITE);
		viewReg1.setFont(new Font("Digital-7", Font.PLAIN, 25));
		viewReg1.setHorizontalTextPosition(SwingConstants.LEADING);
		viewReg1.setHorizontalAlignment(SwingConstants.RIGHT);
		viewReg1.setBackground(new Color(0, 0, 0));
		viewReg1.setBounds(278, 31, 142, 26);
		frame.getContentPane().add(viewReg1);
		
		viewReg2 = new JLabel("0000");
		viewReg2.setOpaque(true);
		viewReg2.setHorizontalTextPosition(SwingConstants.LEADING);
		viewReg2.setHorizontalAlignment(SwingConstants.RIGHT);
		viewReg2.setForeground(Color.WHITE);
		viewReg2.setFont(new Font("Digital-7", Font.PLAIN, 25));
		viewReg2.setBackground(Color.BLACK);
		viewReg2.setBounds(278, 88, 142, 26);
		frame.getContentPane().add(viewReg2);
		
		combo2 = new JComboBox();
		combo2.setModel(new DefaultComboBoxModel(new String[] {"$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7", "$t8", "$t9", "$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7", "$a0", "$a1", "$a2", "$a3", "$v0", "$v1"}));
		combo2.setSelectedIndex(0);
		combo2.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		combo2.setBounds(278, 68, 84, 20);
		combo2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Object selected = combo2.getSelectedItem();
				viewReg2.setText(getRegVal(((String)selected).substring(1)));
			}
		});
		frame.getContentPane().add(combo2);
		
		combo3 = new JComboBox();
		combo3.setModel(new DefaultComboBoxModel(new String[] {"$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7", "$t8", "$t9", "$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7", "$a0", "$a1", "$a2", "$a3", "$v0", "$v1"}));
		combo3.setSelectedIndex(0);
		combo3.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		combo3.setBounds(278, 125, 84, 20);
		combo3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Object selected = combo3.getSelectedItem();
				viewReg3.setText(getRegVal(((String)selected).substring(1)));
			}
		});
		frame.getContentPane().add(combo3);
		
		viewReg3 = new JLabel("0000");
		viewReg3.setOpaque(true);
		viewReg3.setHorizontalTextPosition(SwingConstants.LEADING);
		viewReg3.setHorizontalAlignment(SwingConstants.RIGHT);
		viewReg3.setForeground(Color.WHITE);
		viewReg3.setFont(new Font("Digital-7", Font.PLAIN, 25));
		viewReg3.setBackground(Color.BLACK);
		viewReg3.setBounds(278, 145, 142, 26);
		frame.getContentPane().add(viewReg3);
		
		combo4 = new JComboBox();
		combo4.setModel(new DefaultComboBoxModel(new String[] {"$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7", "$t8", "$t9", "$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7", "$a0", "$a1", "$a2", "$a3", "$v0", "$v1"}));
		combo4.setSelectedIndex(0);
		combo4.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		combo4.setBounds(278, 182, 84, 20);
		combo4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Object selected = combo4.getSelectedItem();
				viewReg4.setText(getRegVal(((String)selected).substring(1)));
			}
		});
		frame.getContentPane().add(combo4);
		
		viewReg4 = new JLabel("0000");
		viewReg4.setOpaque(true);
		viewReg4.setHorizontalTextPosition(SwingConstants.LEADING);
		viewReg4.setHorizontalAlignment(SwingConstants.RIGHT);
		viewReg4.setForeground(Color.WHITE);
		viewReg4.setFont(new Font("Digital-7", Font.PLAIN, 25));
		viewReg4.setBackground(Color.BLACK);
		viewReg4.setBounds(278, 202, 142, 26);
		frame.getContentPane().add(viewReg4);
		
		JButton btnMontar = new JButton("");
		btnMontar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Montador m = new Montador();
				m.montar(editorPane.getText(), memoria);
				if(animations == true)
					memLoadAnimation();
				Principal.attStatus("Montado");
				if(memoriaScreen != null){
					memoriaScreen.updateTable();
				}
			}
		});
		btnMontar.setIcon(new ImageIcon(Principal.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		btnMontar.setBounds(437, 10, 29, 26);
		frame.getContentPane().add(btnMontar);
		
		JButton btnExecutar = new JButton("");
		btnExecutar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(memoria.getCountInst()>0){
					processador.getUc().executeAll(processador, memoria);
					processador.setPC(memoria.getCountInst());
					attStatus("Todos os passos foram executados!");
					updateRegLabels();
					if(registerScreen != null){
						registerScreen.updateTable();
					}
				}
			}
		});
		btnExecutar.setIcon(new ImageIcon(Principal.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
		btnExecutar.setBounds(437, 47, 29, 26);
		frame.getContentPane().add(btnExecutar);
		
		JButton btnProximo = new JButton("");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Processador.getPC()<memoria.getCountInst()){
					processador.getUc().execute(processador, memoria);
					attStatus("Passo Executado.");
					updateRegLabels();
					Processador.updatePC();
					if(registerScreen!=null){
						registerScreen.updateTable();
					}
				}else{
					attStatus("Não há mais instruções para serem executadas!!");
				}
			}
		});
		btnProximo.setIcon(new ImageIcon(Principal.class.getResource("/com/sun/javafx/scene/web/skin/Redo_16x16_JFX.png")));
		btnProximo.setBounds(437, 88, 29, 26);
		frame.getContentPane().add(btnProximo);
		
		JLabel lblPc = new JLabel("PC:");
		lblPc.setBounds(437, 176, 29, 26);
		frame.getContentPane().add(lblPc);
		
		viewPC = new JLabel("0000");
		viewPC.setOpaque(true);
		viewPC.setHorizontalTextPosition(SwingConstants.LEADING);
		viewPC.setHorizontalAlignment(SwingConstants.RIGHT);
		viewPC.setForeground(Color.WHITE);
		viewPC.setFont(new Font("Digital-7", Font.PLAIN, 25));
		viewPC.setBackground(Color.BLACK);
		viewPC.setBounds(432, 202, 104, 26);
		frame.getContentPane().add(viewPC);
		
		lblMemoria = new JLabel("");
		lblMemoria.setIcon(new ImageIcon("Images/memoria0.png"));
		lblMemoria.setBounds(21, 356, 154, 63);
		frame.getContentPane().add(lblMemoria);
		
		lblUC = new JLabel("");
		lblUC.setIcon(new ImageIcon("Images/uc_off.png"));
		lblUC.setBounds(298, 255, 85, 85);
		frame.getContentPane().add(lblUC);
		
		lblULA = new JLabel("");
		lblULA.setIcon(new ImageIcon("Images/ula_off.png"));
		lblULA.setBounds(400, 355, 91, 64);
		frame.getContentPane().add(lblULA);
		
		lblMemBus = new JLabel("");
		lblMemBus.setIcon(new ImageIcon("Images/membus0.png"));
		lblMemBus.setBounds(34, 228, 125, 128);
		frame.getContentPane().add(lblMemBus);
		
		lblUCBus = new JLabel("");
		lblUCBus.setIcon(new ImageIcon("Images/ucbus0.png"));
		lblUCBus.setBounds(126, 304, 200, 152);
		frame.getContentPane().add(lblUCBus);
		
		lblULABus = new JLabel("");
		lblULABus.setIcon(new ImageIcon("Images/ulabus0.png"));
		lblULABus.setBounds(388, 281, 62, 67);
		frame.getContentPane().add(lblULABus);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(49, 459, 476, 36);
		frame.getContentPane().add(scrollPane_1);
		
		lblViewStatus = new JLabel("Parado");
		lblViewStatus.setOpaque(true);
		lblViewStatus.setBackground(SystemColor.controlHighlight);
		scrollPane_1.setViewportView(lblViewStatus);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(6, 470, 43, 14);
		frame.getContentPane().add(lblStatus);
		
		JButton btnZerarReg = new JButton("Zerar Reg.");
		btnZerarReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processador.zerarReg();
				updateRegLabels();
				if(registerScreen != null)
					registerScreen.updateTable();
			}
		});
		btnZerarReg.setBounds(430, 123, 106, 23);
		frame.getContentPane().add(btnZerarReg);
		
		JButton btnZerarMem = new JButton("Zerar Mem.");
		btnZerarMem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				memoria.zeraMatriz();
				if(memoriaScreen != null)
					memoriaScreen.updateTable();
			}
		});
		btnZerarMem.setBounds(430, 154, 106, 23);
		frame.getContentPane().add(btnZerarMem);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir...");
		mntmAbrir.setIcon(new ImageIcon(Principal.class.getResource("/com/sun/java/swing/plaf/windows/icons/NewFolder.gif")));
		mnArquivo.add(mntmAbrir);
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.setIcon(new ImageIcon(Principal.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		mnArquivo.add(mntmSalvar);
		
		JMenuItem mntmSalavarComo = new JMenuItem("Salavar Como...");
		mntmSalavarComo.setIcon(new ImageIcon(Principal.class.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")));
		mnArquivo.add(mntmSalavarComo);
		
		JMenuItem mntmFechar = new JMenuItem("Fechar");
		mntmFechar.setIcon(new ImageIcon(Principal.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		mnArquivo.add(mntmFechar);
		
		JMenu mnVisualizacao = new JMenu("Visualiza\u00E7\u00E3o");
		menuBar.add(mnVisualizacao);
		
		JMenuItem mntmTodosReg = new JMenuItem("Ver todos os registradores");
		mntmTodosReg.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(registerScreen == null){
					registerScreen = new RegisterScreen();
					registerScreen.getFrame().setVisible(true);
				}else{
					registerScreen.getFrame().setVisible(true);
				}
			}
		});
		mnVisualizacao.add(mntmTodosReg);
		
		JMenuItem mntmAnimacoes = new JMenuItem("Desligar anima\u00E7\u00F5es");
		mntmAnimacoes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(animations == true)
					animations = false;
				else
					animations = true;
			}
		});
		mnVisualizacao.add(mntmAnimacoes);
		
		JMenuItem mntmVerMemoria = new JMenuItem("Visualizar a mem\u00F3ria");
		mntmVerMemoria.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(memoriaScreen == null){
					memoriaScreen = new MemoriaScreen(memoria);
					memoriaScreen.getFrame().setVisible(true);
				}else{
					memoriaScreen.getFrame().setVisible(true);
				}
			}
			
		});
		mnVisualizacao.add(mntmVerMemoria);
		
		JMenu mnCompilao = new JMenu("Compila\u00E7\u00E3o");
		menuBar.add(mnCompilao);
		
		JMenuItem mntmMontar = new JMenuItem("Montar");
		mntmMontar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Montador m = new Montador();
				m.montar(editorPane.getText(),memoria);
				Principal.attStatus("Montado");
				if(memoriaScreen != null){
					memoriaScreen.updateTable();
				}
			}
		});
		mntmMontar.setIcon(new ImageIcon(Principal.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		mnCompilao.add(mntmMontar);
		
		JMenu mnExecutar = new JMenu("Executar");
		mnCompilao.add(mnExecutar);
		
		JMenuItem mntmTudo = new JMenuItem("Tudo");
		mntmTudo.setIcon(new ImageIcon(Principal.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
		mnExecutar.add(mntmTudo);
		
		JMenuItem mntmProximoPasso = new JMenuItem("Pr\u00F3ximo Passo");
		mntmProximoPasso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Processador.getPC()<memoria.getCountInst()){
					processador.getUc().execute(processador, memoria);
					attStatus("Passo Executado.");
					updateRegLabels();
					Processador.updatePC();
					registerScreen.updateTable();
				}else{
					attStatus("Não há mais instruções para serem executadas!!");
				}
			}
		});
		mntmProximoPasso.setIcon(new ImageIcon(Principal.class.getResource("/com/sun/javafx/scene/web/skin/Redo_16x16_JFX.png")));
		mnExecutar.add(mntmProximoPasso);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mnAjuda.add(mntmSobre);
		
		JMenuItem mntmDesenvolvimento = new JMenuItem("Desenvolvimento");
		mnAjuda.add(mntmDesenvolvimento);
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	public String getRegVal(String nome){
		for(Registrador r : processador.getPubReg()){
			if(r.getNome().equals(nome)){
				return Integer.toString(r.getValor());
			}
		}
		return "erro";
	}
	
	public void updateRegLabels(){
		viewReg1.setText(getRegVal(((String)combo1.getSelectedItem()).substring(1)));
		viewReg2.setText(getRegVal(((String)combo2.getSelectedItem()).substring(1)));
		viewReg3.setText(getRegVal(((String)combo3.getSelectedItem()).substring(1)));
		viewReg4.setText(getRegVal(((String)combo4.getSelectedItem()).substring(1)));
	}
	
	public void memLoadAnimation(){
	}
}
