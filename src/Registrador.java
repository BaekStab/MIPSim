public class Registrador {
	private String nome;
	private String code;
	private int valor;
	
	//Construtores
	public Registrador(){
		
	}
	public Registrador(String nome, String code){
		this.nome = nome;
		this.code = code;
	}	
	public Registrador(String nome, String code, int valor){
		this.nome = nome;
		this.code = code;
		this.valor = valor;
	}
	//end construtores
	
	//get and set
	public String getNome(){
		return this.nome;
	}
	
	public String getCode(){
		return this.code;
	}
	
	public int getValor(){
		return this.valor;
	}
	public void setValor(int n){
		if(!this.getNome().equals("zero"))
			this.valor = n;
		else
			Montador.erroCompilacao("Impossivel modifificar o registrador $zero!");
	}
	//end get and set
}
