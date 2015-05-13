package modelquery;

public class ModelQueryException extends Exception{
	
	String errorMsg;
	
	public ModelQueryException(String errorMsg){
		this.errorMsg = errorMsg;
	}
	
	public String toString(){
		return errorMsg;
	}
}
