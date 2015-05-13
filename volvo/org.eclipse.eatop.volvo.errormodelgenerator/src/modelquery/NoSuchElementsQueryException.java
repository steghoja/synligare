package modelquery;

public class NoSuchElementsQueryException extends Exception{
	
	private String errorMsg = "No such elements exist in the model\n";
	private String inputMsg;
	
	public NoSuchElementsQueryException(String msg){
		this.inputMsg = msg;
	}
	
	public String toString(){
		return this.errorMsg + this.inputMsg;
	}
	
}
