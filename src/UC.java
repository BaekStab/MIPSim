import javax.swing.JOptionPane;

public class UC {
	
	public String nextStep(Processador proc, Memoria mem){
		if(mem.getCountInst() == 0){
			Principal.attStatus("Não há instrução montada para executar!");
		}else{
			if(mem.getCountInst() < Processador.getPC()){
				Montador.erroCompilacao("Não há mais instruções a serem executadas!");
			}else{
				String inst;
				inst = mem.read(proc.getPC());
				return inst;
	
			}
		}
		return "erro";
	}
	
	public void execute(Processador proc, Memoria mem){
		String inst = nextStep(proc,mem);
		String op = inst.substring(0, 6);
		switch(op){
			case "000000":
				String funct = inst.substring(26, 32);
				switch(funct){
					case "000000":
						proc.getUla().sll(decodeRegister(inst.substring(16,21),proc), decodeRegister(inst.substring(11,16),proc), Integer.parseInt(inst.substring(21,26),2));
						break;
					case "000010":
						proc.getUla().srl(decodeRegister(inst.substring(16,21),proc), decodeRegister(inst.substring(11,16),proc), Integer.parseInt(inst.substring(21,26),2));
						break;
					case "001000":
						proc.getUla().jr(decodeRegister(inst.substring(6,11),proc));
						break;
					case "100000":
						proc.getUla().add(decodeRegister(inst.substring(16,21),proc), decodeRegister(inst.substring(6,11),proc), decodeRegister(inst.substring(11,16),proc));
						break;
					case "100010":
						proc.getUla().sub(decodeRegister(inst.substring(16,21),proc), decodeRegister(inst.substring(6,11),proc), decodeRegister(inst.substring(11,16),proc));
						break;
					case "100100":
						proc.getUla().and(decodeRegister(inst.substring(16,21),proc), decodeRegister(inst.substring(6,11),proc), decodeRegister(inst.substring(11,16),proc));
						break;
					case "100101":
						proc.getUla().or(decodeRegister(inst.substring(16,21),proc), decodeRegister(inst.substring(6,11),proc), decodeRegister(inst.substring(11,16),proc));
						break;
					case "100111":
						proc.getUla().nor(decodeRegister(inst.substring(16,21),proc), decodeRegister(inst.substring(6,11),proc), decodeRegister(inst.substring(11,16),proc));
						break;
					case "101010":
						proc.getUla().slt(decodeRegister(inst.substring(16,21),proc), decodeRegister(inst.substring(6,11),proc), decodeRegister(inst.substring(11,16),proc));
						break;
				}
				break;
			case "000010":
				proc.getUla().j(Integer.parseInt(inst.substring(6, 32), 2));
				break;
			case "000011":
				proc.getUla().jal(Integer.parseInt(inst.substring(6, 32), 2));
				break;
			case "000100":
				proc.getUla().beq(decodeRegister(inst.substring(6,11),proc), decodeRegister(inst.substring(11,16),proc), Integer.parseInt(inst.substring(16,32),2));
				break;
			case "000101":
				proc.getUla().beq(decodeRegister(inst.substring(6,11),proc), decodeRegister(inst.substring(11,16),proc), Integer.parseInt(inst.substring(16,32),2));
				break;
			case "001000":
				proc.getUla().addi(decodeRegister(inst.substring(11, 16),proc), decodeRegister(inst.substring(6, 11),proc), Integer.parseInt(inst.substring(16,32), 2));
				break;
			case "001010":
				proc.getUla().slti(decodeRegister(inst.substring(11, 16),proc), decodeRegister(inst.substring(6, 11),proc), Integer.parseInt(inst.substring(16,32), 2));
				break;
			case "001100":
				proc.getUla().andi(decodeRegister(inst.substring(11, 16),proc), decodeRegister(inst.substring(6, 11),proc), Integer.parseInt(inst.substring(16,32), 2));
				break;
			case "001101":
				proc.getUla().ori(decodeRegister(inst.substring(11, 16),proc), decodeRegister(inst.substring(6, 11),proc), Integer.parseInt(inst.substring(16,32), 2));
				break;
			case "001111":
				//LUI
				break;
			case "100000":
				//LB
				break;
			case "100001":
				//LH
				break;
			case "100011":
				//LW
				proc.getUla().lw(decodeRegister(inst.substring(11, 16),proc), decodeRegister(inst.substring(6, 11),proc), Integer.parseInt(inst.substring(16, 32),2), mem);
				break;
			case "101000":
				//SB
				proc.getUla().sb(decodeRegister(inst.substring(11, 16),proc), decodeRegister(inst.substring(6, 11),proc), Integer.parseInt(inst.substring(16, 32),2), mem);
				break;
			case "101001":
				//SH
				proc.getUla().sh(decodeRegister(inst.substring(11, 16),proc), decodeRegister(inst.substring(6, 11),proc), Integer.parseInt(inst.substring(16, 32),2), mem);
				break;
			case "101011":
				//SW
				proc.getUla().sw(decodeRegister(inst.substring(11, 16),proc), decodeRegister(inst.substring(6, 11),proc), Integer.parseInt(inst.substring(16, 32),2), mem);
				break;
		}
		proc.updatePC();
	}
	
	public void executeAll(Processador proc, Memoria mem){
		if(mem.getCountInst() == 0){
			Principal.attStatus("Não há nenhuma instrução montada");
		}else{
			for(int i = 0; i < mem.getCountInst(); i++){
				execute(proc, mem);
			}
		}
		proc.setPC(0);
	}
	
	public Registrador decodeRegister(String reg, Processador proc){
		for(Registrador r : proc.getPubReg()){
			if(r.getCode().equals(reg)){
				return r;
			}
		}
		Montador.erroCompilacao("Registrador nao encontrado!!");
		Registrador r = new Registrador();
		return r;
	}
}
