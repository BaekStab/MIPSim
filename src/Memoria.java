public class Memoria {
	private String[][] bytes;
	//public static final int POS_INST = 0;
	private int countBytes;
	private int countInst;
	
	public Memoria(){
		this.bytes = new String[150][4];
		//iniciaMatriz();
		this.countBytes = 0;
		this.countInst = 0;
	}
	
	public void updateCountBytes(){
		this.countBytes++;
	}
	public int getCountBytes(){
		return this.countBytes;
	}
	public int getCountInst(){
		return this.countInst;
	}
	
	
	/*public String[][] getMemoria(){
		return this.bytes;
	}*/
	
	public void write(String exp, int i){
		this.bytes[i][0] = exp.substring(0, 8);
		//JOptionPane.showMessageDialog(null, this.bytes[i][0]);
		this.bytes[i][1] = exp.substring(8, 16);
		//JOptionPane.showMessageDialog(null, this.bytes[i][1]);
		this.bytes[i][2] = exp.substring(16, 24);
		//JOptionPane.showMessageDialog(null, this.bytes[i][2]);
		this.bytes[i][3] = exp.substring(24, 32);
		//JOptionPane.showMessageDialog(null, this.bytes[i][3]);
		this.countInst++;
	}
	
	public String read(int i){
		return this.bytes[i][0]+this.bytes[i][1]+this.bytes[i][2]+this.bytes[i][3];
	}
	
	public String read(int i, int j){
		return this.bytes[i][j];
	}
	
	public void zeraMatriz(){
		for(int i =0; i<150; i++){
			for(int j=0; j<4; j++){
				this.bytes[i][j] = "0";
			}
		}
		this.countBytes = 0;
		this.countInst = 0;
	}
}
