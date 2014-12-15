import java.util.ArrayList;

public class Processador {
	private static ArrayList<Registrador> privReg;
	private UC uc;
	private ULA ula;
	public static ArrayList<Registrador> pubReg;
	
	public Processador(){
		pubReg = new ArrayList<Registrador>();
		initPubReg();
		privReg = new ArrayList<Registrador>();
		initPrivReg();
		this.ula = new ULA(this);
		this.uc = new UC();
	}
	
	//get and set
	public ULA getUla(){
		return this.ula;
	}
	public UC getUc(){
		return this.uc;
	}
	public Registrador getPubRegN(int n){
		return pubReg.get(n);
	}
	public ArrayList<Registrador> getPubReg(){
		return pubReg;
	}
	
	public static int getPC(){
		return privReg.get(0).getValor();
	}
	public static void updatePC(){
		privReg.get(0).setValor(getPC()+1);
	}
	public void setPC(int valor){
		privReg.get(0).setValor(valor);
	}
	public void setRA(int valor){
		privReg.get(1).setValor(valor);
	}
	public int getRA(){
		return privReg.get(1).getValor();
	}
	//.......
	
	private void initPrivReg(){
		Registrador pc = new Registrador("pc","11001",0);
		privReg.add(pc);
		Registrador ra = new Registrador("ra","11010",0);
		privReg.add(ra);
	}
	
	private void initPubReg(){
		Registrador zero = new Registrador("zero","00000",0);
		pubReg.add(zero);
		Registrador v0 = new Registrador("v0","00010",0);
		pubReg.add(v0);
		Registrador v1 = new Registrador("v1","00011",0);
		pubReg.add(v1);
		Registrador a0 = new Registrador("a0","00100",0);
		pubReg.add(a0);
		Registrador a1 = new Registrador("a1","00101",0);
		pubReg.add(a1);
		Registrador a2 = new Registrador("a2","00110",0);
		pubReg.add(a2);
		Registrador a3 = new Registrador("a3","00111",0);
		pubReg.add(a3);
		Registrador t0 = new Registrador("t0","01000",0);
		pubReg.add(t0);
		Registrador t1 = new Registrador("t1","01001",0);
		pubReg.add(t1);
		Registrador t2 = new Registrador("t2","01010",0);
		pubReg.add(t2);
		Registrador t3 = new Registrador("t3","01011",0);
		pubReg.add(t3);
		Registrador t4 = new Registrador("t4","01100",0);
		pubReg.add(t4);
		Registrador t5 = new Registrador("t5","01101",0);
		pubReg.add(t5);
		Registrador t6 = new Registrador("t6","01110",0);
		pubReg.add(t6);
		Registrador t7 = new Registrador("t7","01111",0);
		pubReg.add(t7);
		Registrador t8 = new Registrador("t8","10000",0);
		pubReg.add(t8);
		Registrador t9 = new Registrador("t0","10001",0);
		pubReg.add(t9);
		Registrador s0 = new Registrador("s0","00001",0);
		pubReg.add(s0);
		Registrador s1 = new Registrador("s1","10010",0);
		pubReg.add(s1);
		Registrador s2 = new Registrador("s2","10011",0);
		pubReg.add(s2);
		Registrador s3 = new Registrador("s3","10100",0);
		pubReg.add(s3);
		Registrador s4 = new Registrador("s4","10101",0);
		pubReg.add(s4);
		Registrador s5 = new Registrador("s5","10110",0);
		pubReg.add(s5);
		Registrador s6 = new Registrador("s6","10111",0);
		pubReg.add(s6);
		Registrador s7 = new Registrador("s7","11000",0);
		pubReg.add(s7);
	}
	
	public void zerarReg(){
		for(int i=1; i<25; i++){
			pubReg.get(i).setValor(0);
		}
	}
}
