import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Montador {
	private ArrayList<Label> labelList;
	private int qtdLabel;
	
	//Metodo Construtor
	public Montador(){
		this.qtdLabel = 0;
		this.labelList = new ArrayList<Label>();
	}
	
	//GET AND SET
	public ArrayList<Label> getAllLabel(){
		return this.labelList;
	}
	public void setQtdLabel(int val){
		this.qtdLabel = val;
	}
	public int getQtdLabel(){
		return this.qtdLabel;
	}
	//END GET AND SET
	
	//Separa por linhas
	public String[] splitInst(String texto){
		texto = texto.replaceAll("\r","");
		return texto.replaceAll(" ","").split("\n");
	}
	
	//Checa se a Label já foi registrada
	public int checkLabel(ArrayList<Label> label, String check){
		int i;
		JOptionPane.showMessageDialog(null, getQtdLabel());
		for(i=0; i<getQtdLabel(); i++){
			JOptionPane.showMessageDialog(null, getQtdLabel());
			if(label.get(i).getText().equals(check)){
						return label.get(i).getPosLabel();				
						}
		}
		return -1;//-1
	}
	
	//Fecha o programa e acusa erro de compilação
	public static void erroCompilacao(String erro){
		Principal.attStatus("ERRO! "+erro);
	}

	
	public int labelInst(String label){
		for(Label l : labelList){
			JOptionPane.showMessageDialog(null, l.getText()+" "+label);
			if(l.getText().equals(label)){
				return l.getPosLabel();
			}
		}
		return -1;
	}
	
	//Verifica se há um label na linha
	public String verificarLabel(String texto[], int i){
		if(texto[i].indexOf(":") == 0){ // Label Invalido
			erroCompilacao("LABEL VAZIA!");
		}else if(texto[i].indexOf(":") > 0){ //Label valido
			String substring = texto[i].substring(0, texto[i].indexOf(":")); //pega label
			JOptionPane.showMessageDialog(null, substring);
			int compare = checkLabel(getAllLabel(), substring);
			if(compare >= 0){ //Label já registrado
				getAllLabel().get(compare).setPosLabel(i);
				erroCompilacao("LABEL JA REGISTRADA");
			}else{ //Label não registrado
				Label l = new Label();
				JOptionPane.showMessageDialog(null, "!!!!"+i);
				l.setPosLabel(i);
				l.setText(substring);
				setQtdLabel(getQtdLabel()+1);
				getAllLabel().add(l);
				JOptionPane.showMessageDialog(null, "!"+getQtdLabel());
			}
			return texto[i].substring(texto[i].indexOf(":")+1);//Retorna a expressao sem o label
		}
		return texto[i];//Nao possui label, retorna toda a expressao
	}
	
	public boolean verificarComentario(String texto[], int i){
		System.out.println(i+" "+texto[i]);
		if(texto[i].length()>=0){
		if(texto[i].equals("")){
			return true;
		}
		if(texto[i].charAt(0)=='#'){
			return true;
		}else{
			return false;
		}
		}else{
			return true;
		}
	}
	
	//NOVO
	public String[] primeiraPassada(String[] texto){
		for(int i=0; i<texto.length; i++){
			if(!verificarComentario(texto, i)){
				if(texto[i].indexOf(":")>1){
					Label l = new Label();
					String label = texto[i].substring(0,texto[i].indexOf(":"));
					l.setText(label);
					l.setPosLabel(i);
					labelList.add(l);
					setQtdLabel(getQtdLabel()+1);
					texto[i] = texto[i].substring(texto[i].indexOf(":")+1);
				}else if(texto[i].indexOf(":") == 0){
					erroCompilacao("Label vazia!");
				}
			}
		}
		return texto;
	}
	
	public String[] varrerLabels(String texto[]){
		for(int i = 0; i < texto.length; i++){
			if(!verificarComentario(texto, i)){//A linha é um comentario?
				//Se NAO for comentario, continua
				texto[i] = verificarLabel(texto, i);
			}
		}
		return texto;
	}
	
	public void montar(String inst, Memoria mem){
		String texto[] = splitInst(inst);
		int cont = 0;
		//String word[] = texto;
		texto = primeiraPassada(texto);
		//texto = varrerLabels(texto);
		for(int i = 0; i<texto.length; i++){
			System.out.println("FOR "+i);
			if(!verificarComentario(texto, i)){
				//Nao e comentario
				mem.write(traduzir(texto[i],i), cont);
				cont++;
			}
		}
		}
	
	public String traduzir(String exp, int pos){
		String op="", funct="", shamt = "00000", rs="", rt="", rd="", instfinal = "", end="";
		int aux = 0, num = 0;
		if(exp.indexOf("$")>0){
			op = exp.substring(0, exp.indexOf("$"));
			switch(op){
			case "add":
				exp = exp.replace("add", "");
				op = "000000";
				funct = "100000";
				rd = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				rs = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				if(exp.contains("#")){
					rt = checkRegCode(exp.substring(0, exp.indexOf("#")));
				}else{
					rt = checkRegCode(exp.substring(0, exp.length()));
				}
				instfinal = op+rs+rt+rd+shamt+funct;
				break;
			case "addi":
				exp = exp.replace("addi", "");
				op = "001000";
				//JOptionPane.showMessageDialog(null, exp.substring(0, exp.indexOf(",")));
				if((exp.substring(0, exp.indexOf(","))).equals("$zero"))
					erroCompilacao("$zero não pode receber valores!");
				rt = checkRegCode(exp.substring(0, exp.indexOf(",")));
				
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				rs = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				if(exp.contains("#")){
					num = Integer.parseInt(exp.substring(0,exp.indexOf("#")));
					end = Integer.toBinaryString(num);
				}else{
					num = Integer.parseInt(exp.substring(0,exp.length()));
					end = Integer.toBinaryString(num);
				}
				if(num > 0){
					end = completaZero(end,16);
				}else{
					end = completaUm(end,16);
				}
				instfinal = op+rs+rt+end;
				break;
			case "sub":
				exp = exp.replace("sub", "");
				op = "000000";
				funct = "010100";
				rd = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				rs = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				if(exp.contains("#")){
					rt = checkRegCode(exp.substring(0, exp.indexOf("#")));
				}else{
					rt = checkRegCode(exp.substring(0, exp.length()));
				}
				instfinal = op+rs+rt+rd+shamt+funct;
				break;
			case "sll":
				exp = exp.replace("sll", "");
				op = "000000";
				funct = "000000";
				rs = "00000";
				rd = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				rt = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				if(exp.contains("#")){
					shamt = Integer.toBinaryString(Integer.parseInt(exp.substring(0,exp.indexOf("#"))));
				}else{
					shamt = Integer.toBinaryString(Integer.parseInt(exp.substring(0,exp.length())));
				}
				shamt = completaZero(shamt,5);
				instfinal = op+rs+rt+rd+shamt+funct;
				break;
			case "srl":
				exp = exp.replace("sll", "");
				op = "000000";
				funct = "000010";
				rs = "00000";
				rd = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				rt = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				if(exp.contains("#")){
					shamt = Integer.toBinaryString(Integer.parseInt(exp.substring(0,exp.indexOf("#"))));
				}else{
					shamt = Integer.toBinaryString(Integer.parseInt(exp.substring(0,exp.length())));
				}
				shamt = completaZero(shamt,5);
				instfinal = op+rs+rt+rd+shamt+funct;
				break;
			case "beq":
				exp = exp.replace("beq", "");
				op = "000100";
				rs = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0,exp.indexOf(",")+1),"");
				rt = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0,exp.indexOf(",")+1),"");
				if(exp.contains("#")){
					exp = exp.substring(0, exp.indexOf("#"));
				}
				aux = labelInst(exp);
				if(aux >= 0){
					end = Integer.toBinaryString(aux-pos);
					num = aux-pos;
					if(num>=0)
						end = completaZero(end,16);
					else
						end = completaUm(end,16);
				}else{
					erroCompilacao("LABEL REFERENCIADA NAO EXISTE!");
				}
				instfinal = op+rs+rt+end;
				break;
			case "bne":
				exp = exp.replace("bne", "");
				op = "000101";
				rs = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0,exp.indexOf(",")+1),"");
				rt = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0,exp.indexOf(",")+1),"");
				if(exp.contains("#")){
					exp = exp.substring(0, exp.indexOf("#"));
				}
				aux = labelInst(exp);
				if(aux >= 0){
					end = Integer.toBinaryString(aux-pos);
					num = aux-pos;
					if(num>=0)
						end = completaZero(end,16);
					else
						end = completaUm(end,16);
				}else{
					erroCompilacao("LABEL REFERENCIADA NAO EXISTE!");
				}
				instfinal = op+rs+rt+end;
				break;
			case "sw":
				exp = exp.replace("sw", "");
				op = "101011";
				rt = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0,exp.indexOf(",")+1),"");
				end = Integer.toBinaryString(Integer.parseInt(exp.substring(0,exp.indexOf("("))));
				end = completaZero(end,16);
				exp = exp.replace(exp.substring(0,exp.indexOf("(")+1), "");
				rs = checkRegCode(exp.substring(0, exp.indexOf(")")));
				instfinal = op+rs+rt+end;
				break;
			case "lw":
				exp = exp.replace("lw", "");
				op = "100011";
				rt = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0,exp.indexOf(",")+1),"");
				end = Integer.toBinaryString(Integer.parseInt(exp.substring(0,exp.indexOf("("))));
				end = completaZero(end,16);
				exp = exp.replace(exp.substring(0,exp.indexOf("(")+1), "");
				rs = checkRegCode(exp.substring(0, exp.indexOf(")")));
				instfinal = op+rs+rt+end;
				break;
			case "jr":
				exp = exp.replace("jr", "");
				op = "000000";
				funct = "001000";
				rt = "00000";
				rd = "00000";
				shamt = "00000";
				if(exp.contains("#")){
					rs = checkRegCode(exp.substring(0, exp.indexOf("#")));
				}else{
					rs = checkRegCode(exp.substring(0, exp.length()));
				}
				instfinal = op+rs+rt+rd+shamt+funct;
				break;
			case "lb":
				exp = exp.replace("lb", "");
				op = "100000";
				rt = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0,exp.indexOf(",")+1),"");
				end = Integer.toBinaryString(Integer.parseInt(exp.substring(0,exp.indexOf("("))));
				end = completaZero(end,16);
				exp = exp.replace(exp.substring(0,exp.indexOf("(")+1), "");
				rs = checkRegCode(exp.substring(0, exp.indexOf(")")));
				instfinal = op+rs+rt+end;
				break;
			case "sb":
				exp = exp.replace("sb", "");
				op = "101000";
				rt = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0,exp.indexOf(",")+1),"");
				end = Integer.toBinaryString(Integer.parseInt(exp.substring(0,exp.indexOf("("))));
				end = completaZero(end,16);
				exp = exp.replace(exp.substring(0,exp.indexOf("(")+1), "");
				rs = checkRegCode(exp.substring(0, exp.indexOf(")")));
				instfinal = op+rs+rt+end;
				break;
			case "slt":
				exp = exp.replace("slt", "");
				op = "000000";
				funct = "101010";
				shamt = "00000";
				rd = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0,exp.indexOf(",")+1), "");
				rs = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0,exp.indexOf(",")+1), "");
				if(exp.contains("#")){
					rt = checkRegCode(exp.substring(0, exp.indexOf("#")));
				}else{
					rt = checkRegCode(exp.substring(0, exp.length()));
				}
				instfinal = op+rs+rt+rd+shamt+funct;
				break;
			case "slti":
				exp = exp.replace("slti", "");
				op = "001010";
				rs = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0,exp.indexOf(",")+1), "");
				rt = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0,exp.indexOf(",")+1), "");
				if(exp.contains("#")){
					num = Integer.parseInt(exp.substring(0,exp.indexOf("#")));
					end = Integer.toBinaryString(num);
				}else{
					num = Integer.parseInt(exp.substring(0, exp.length()));
					end = Integer.toBinaryString(num);
				}
				if(num>0)
					end = completaZero(end,16);
				else
					end = completaUm(end,16);
				instfinal = op+rs+rt+end;
				break;
			case "or":
				exp = exp.replace("or", "");
				op = "000000";
				funct = "100101";
				rd = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				rs = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				if(exp.contains("#")){
					rt = checkRegCode(exp.substring(0, exp.indexOf("#")));
				}else{
					rt = checkRegCode(exp.substring(0, exp.length()));
				}
				instfinal = op+rs+rt+rd+shamt+funct;
				break;
			case "nor":
				exp = exp.replace("nor", "");
				op = "000000";
				funct = "100111";
				rd = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				rs = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				if(exp.contains("#")){
					rt = checkRegCode(exp.substring(0, exp.indexOf("#")));
				}else{
					rt = checkRegCode(exp.substring(0, exp.length()));
				}
				instfinal = op+rs+rt+rd+shamt+funct;
				break;
			case "and":
				exp = exp.replace("and", "");
				op = "000000";
				funct = "100100";
				rd = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				rs = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				if(exp.contains("#")){
					rt = checkRegCode(exp.substring(0, exp.indexOf("#")));
				}else{
					rt = checkRegCode(exp.substring(0, exp.length()));
				}
				instfinal = op+rs+rt+rd+shamt+funct;
				break;
			case "ori":
				exp = exp.replace("ori", "");
				op = "001101";
				rt = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				rs = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				if(exp.contains("#")){
					num = Integer.parseInt(exp.substring(0,exp.indexOf("#")));
					end = Integer.toBinaryString(num);
				}else{
					num = Integer.parseInt(exp.substring(0,exp.length()));
					end = Integer.toBinaryString(num);
				}
				if(num>0)
					end = completaZero(end,16);
				else
					end = completaUm(end,16);
				instfinal = op+rs+rt+end;
				break;
			case "andi":
				exp = exp.replace("andi", "");
				op = "001100";
				rt = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				rs = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				if(exp.contains("#")){
					num = Integer.parseInt(exp.substring(0,exp.indexOf("#")));
					end = Integer.toBinaryString(num);
				}else{
					num = Integer.parseInt(exp.substring(0,exp.length()));
					end = Integer.toBinaryString(num);
				}
				if(num>0)
					end = completaZero(end,16);
				else
					end = completaUm(end,16);
				instfinal = op+rs+rt+end;
				break;
			case "lh":
				exp = exp.replace("lh", "");
				op = "100001";
				rt = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0,exp.indexOf(",")+1),"");
				end = Integer.toBinaryString(Integer.parseInt(exp.substring(0,exp.indexOf("("))));
				end = completaZero(end,16);
				exp = exp.replace(exp.substring(0,exp.indexOf("(")+1), "");
				rs = checkRegCode(exp.substring(0, exp.indexOf(")")));
				instfinal = op+rs+rt+end;
				break;
			case "sh":
				exp = exp.replace("sh", "");
				op = "101001";
				rt = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0,exp.indexOf(",")+1),"");
				end = Integer.toBinaryString(Integer.parseInt(exp.substring(0,exp.indexOf("("))));
				end = completaZero(end,16);
				exp = exp.replace(exp.substring(0,exp.indexOf("(")+1), "");
				rs = checkRegCode(exp.substring(0, exp.indexOf(")")));
				instfinal = op+rs+rt+end;
				break;
			case "lui":
				exp = exp.replace("lui", "");
				op = "001111";
				rt = checkRegCode(exp.substring(0, exp.indexOf(",")));
				exp = exp.replace(exp.substring(0, exp.indexOf(",")+1),"");
				rs = "00000";
				if(exp.contains("#")){
					end = Integer.toBinaryString(Integer.parseInt(exp.substring(0,exp.indexOf("#"))));
				}else{
					end = Integer.toBinaryString(Integer.parseInt(exp.substring(0,exp.length())));
				}
				end = completaZero(end,16);
				instfinal = op+rs+rt+end;
				break;
			default:
				erroCompilacao("OPERAÇÃO INVALIDA!!!");
			}
		}else if(exp.charAt(0) == 'j'){
			if(exp.charAt(1)=='a' && exp.charAt(2)=='l'){
				//JAL
				exp = exp.replace("jal", "");
				op = "000011";
				if(exp.contains("#")){
					exp = exp.substring(0, exp.indexOf("#"));
				}
				aux = checkLabel(getAllLabel(),exp);
				if(aux >= 0){
					/*end = Integer.toBinaryString(aux-pos);
					num = aux-pos;*/
					if(aux>=0)
						end = completaZero(Integer.toBinaryString(aux),26);
					else
						end = completaUm(Integer.toBinaryString(aux),26);
				}else{
					erroCompilacao("LABEL REFERENCIADA NAO EXISTE!");
				}
				instfinal = op+end;
			}else{
				//J
				exp = exp.replace("j", "");
				op = "000010";
				if(exp.contains("#")){
					exp = exp.substring(0, exp.indexOf("#"));
				}
				aux = checkLabel(getAllLabel(),exp);
				if(aux >= 0){
					/*end = Integer.toBinaryString(aux-pos);
					num = aux-pos;*/
					if(aux>=0)
						end = completaZero(Integer.toBinaryString(aux),26);
					else
						end = completaUm(Integer.toBinaryString(aux),26);
				}else{
					erroCompilacao("LABEL REFERENCIADA NAO EXISTE!");
				}
				instfinal = op+end;
			}
		}
		System.out.println(instfinal);
		return instfinal;
	}
	
	public static String completaZero(String exp, int num){
		if(exp.length()>num){
			erroCompilacao("Número muito grande!");
		}
		if(exp.length() < num){
				int tam = exp.length();
				for(int i=0; i<num-tam; i++){
					exp = "0".concat(exp);
				}
			}
		return exp;
	}
	
	public static String completaUm(String exp, int num){
		if(exp.length()>num){
			erroCompilacao("Número muito grande!");
		}
		if(exp.length() < num){
				int tam = exp.length();
				for(int i=0; i<num-tam; i++){
					exp = "1".concat(exp);
				}
			}
		return exp;
	}

	
	public String checkRegCode(String nome){
		nome = nome.substring(1);
		 for(Registrador r : Processador.pubReg){
			 System.out.println(r.getNome());
			 System.out.println(nome);
			 System.out.println(r.getNome().equals(nome));
			 if(r.getNome().equals(nome)){
				 return r.getCode();
			 }
		 }
		 erroCompilacao("Registrador Inválido!");
		 return "erro";
	}
}