package ni.org.ics.lab.inventario.domain.utils;

/**
 * Simple objeto de dominio que representa un centro
 * 
 * 
 * @author William Aviles
 **/

public class SearchText{
	/**
	 * 
	 */
	private String id;
	private String text;
	
	
	public SearchText(String id, String text) {
		super();
		this.id = id;
		this.text = text;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}
	
	
}

