
public class ULA {
	private static Processador processador;
	
	
	
	public ULA(Processador proc)
	{
		processador = proc;
	}
	public int complemento1(int valor)
	{
		String algo;
		algo = Integer.toBinaryString(valor);
		char []binario = algo.toCharArray();
		
		
		for(int i=0; i<binario.length; i++)
		{
			if(binario[i] == '1') binario[i] = '0';
			else binario[i] = '1';
			
			//System.out.println(binario[i]);
		}
		
		String temp = new String(binario);
		//System.out.println("testando"+ temp );
		valor = Integer.parseInt(temp, 2);
		
		return valor;
		
	}
	
	public void add(Registrador r1, Registrador r2, Registrador r3)
	{
		r1.setValor(r2.getValor() + r3.getValor());
	}
	
	public void addi(Registrador r1, Registrador r2, int imediato)
	{
		r1.setValor(r2.getValor() + imediato);
	}
	
	public void sub(Registrador r1, Registrador r2, Registrador r3)
	{
	  r1.setValor(r2.getValor() - r3.getValor());
	}
	
	public void sll(Registrador r1, Registrador r2, int numeroShifts)
	{
        int temp = r2.getValor() << numeroShifts;
        r1.setValor(temp);
	}
	
	public void srl(Registrador r1, Registrador r2, int numeroShifts)
	{
        int temp = r2.getValor() >> numeroShifts;
        r1.setValor(temp);
	}
	
	public void beq(Registrador r1, Registrador r2, int shamt)
	{
		if(r1.getValor() == r2.getValor())
		{
		 int aux = processador.getPC() + shamt;
		 processador.setPC(aux-1);
		}
		
		
	}
	
	public void bnq(Registrador r1, Registrador r2, int shamt)
	{
		if(r1.getValor() != r2.getValor())
		{
		 int aux = processador.getPC() + shamt;
		 processador.setPC(aux-1);
		}
		
	}
	

	public void jr(Registrador ra)
	{
      int temp = ra.getValor();
      processador.setPC(temp); //armazena o valor de ra no PC
	}
	
	
	
	public void slt(Registrador r1, Registrador r2, Registrador r3)
	{
		if(r2.getValor() < r3.getValor())
		 r1.setValor(0);
		
		else r1.setValor(1);
	}
	
	public void slti(Registrador r1, Registrador r2, int imediato)
	{
		if(r2.getValor() < imediato)
		r1.setValor(0);
		
		else r1.setValor(1);
	}
	
	public void or(Registrador r1, Registrador r2, Registrador r3)
	{
		r1.setValor(r2.getValor()|r3.getValor());
	}
	
	public void nor(Registrador r1, Registrador r2, Registrador r3)
	{ 
		int temp1, temp2;
		temp1 = complemento1(r2.getValor());
		temp2 =complemento1(r3.getValor());
		r1.setValor(temp1&temp2);
	}
	
	public void and(Registrador r1, Registrador r2, Registrador r3)
	{
		r1.setValor(r2.getValor()&r3.getValor());
	}
	
	public void andi(Registrador r1, Registrador r2, int imediate)
	{
		
		r1.setValor(r2.getValor()&imediate);
	}
	
	public void nand(Registrador r1, Registrador r2, Registrador r3)
	{
		int temp1, temp2;
		temp1 = complemento1(r2.getValor());
		temp2 = complemento1(r3.getValor());
		r1.setValor(temp1|temp2);
	}
	
	public void ori(Registrador r1, Registrador r2, int imediate)
	{
		r1.setValor(r2.getValor()|imediate);
	}
	
	
	
	public void j(int endereco)
	{
		/*int aux = endereco << 2;
		aux = processador.getPC() | aux;*/
		processador.setPC(endereco-1);
		
	}
	
	public void jal(int endereco)
	{
		/*int aux = processador.getPC() + 4; //Soma PC + 4
		processador.setRA(aux);*/  //armazena endereco de retorno em $RA
		processador.setPC(endereco-1);
	}
	
	public void sw(Registrador r1, Registrador r2, int num, Memoria mem) {
		int nume = r2.getValor()+num;
		String r = Montador.completaZero(Integer.toBinaryString(r1.getValor()), 32);
		mem.write(r, nume);

	}

	public void lw(Registrador r1, Registrador r2, int num, Memoria mem) {
		int nume = r2.getValor()+num;
		r1.setValor(Integer.parseInt(mem.read(nume)));
	}
	
	public void sb(Registrador r1, Registrador r2, int num, Memoria mem) {
		int nume = r2.getValor()+num;
		String r = Montador.completaZero(Integer.toBinaryString(r1.getValor()), 32);
		mem.write(r, nume);

	}
	
	public void sh(Registrador r1, Registrador r2, int num, Memoria mem) {
		int nume = r2.getValor()+num;
		String r = Montador.completaZero(Integer.toBinaryString(r1.getValor()), 32);
		mem.write(r, nume);

	}
	
	
	/*public static void main(String []args)
	{
		Ula ula = new Ula();
		Registrador s1, s2, s3;
		s1 = new Registrador("$s1","10001", 0);
		s2 = new Registrador("$s2", "10010", 4);
		s3 = new Registrador("$s3", "10010", 3);
		
		ula.add(s1, s2, s3);
		System.out.println("add = " + s1.getValor()+"  "+Integer.toBinaryString(s1.getValor()));
		ula.sub(s1, s2, s3);
		System.out.println("sub = " + s1.getValor()+"  "+Integer.toBinaryString(s1.getValor()));
		ula.addi(s1, s2, 2);
		System.out.println("addi = " + s1.getValor()+"  "+Integer.toBinaryString(s1.getValor()));
		ula.slt(s1, s2, s3);
		System.out.println("slt = "+ s1.getValor());
	    ula.slti(s1, s2, 1);
	    System.out.println("slti = "+ s1.getValor());
		ula.or(s1,s2,s3);
		System.out.println("or = "+s1.getValor());
		ula.nor(s1,s2,s3);
		System.out.println("nor = "+s1.getValor());
		ula.and(s1,s2,s3);
		System.out.println("and = "+s1.getValor());
		ula.andi(s1,s2,4);
		System.out.println("andi = "+s1.getValor());
		ula.nand(s1,s2,s3);
		System.out.println("nand = "+s1.getValor());

		System.out.println("binario negativo = "+Integer.toBinaryString(-2));
		ula.sll(s1,s2,4);
		System.out.println("sll = "+s1.getValor());
		ula.srl(s1,s2,4);
		System.out.println("srl = "+s1.getValor());
		
		
		ula.beq(s1,s2,4);
		System.out.println("beq   valor de PC = "+processador.getPC().getValor());
		ula.bnq(s1,s2,6);
		System.out.println("bnq   valor de PC = "+processador.getPC().getValor());
	}*/


}
