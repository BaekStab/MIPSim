
public class Label {
	private int posLabel;
	private int posExp;
	private String text;
	
	public Label(){
		this.posLabel = -1;
		this.posExp = -1;
		this.text = "";
	}
	
	public void setPosLabel(int i){
		this.posLabel = i;
	}
	public void setPosExp(int i){
		this.posExp = i;
	}
	public void setText(String s){
		this.text = s;
	}
	public int getPosLabel(){
		return this.posLabel;
	}
	public int getPosExp(){
		return this.posExp;
	}
	public String getText(){
		return this.text;
	}
}
