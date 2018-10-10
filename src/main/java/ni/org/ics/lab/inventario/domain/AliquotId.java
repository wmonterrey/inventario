package ni.org.ics.lab.inventario.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class AliquotId implements Serializable {
	/**
	 * Objeto que representa la clave unica de relacion tipo de alicuota/estudio
	 * 
	 * @author William Aviles
	 **/
	
	private static final long serialVersionUID = 1L;
	private String aliCode;
	private String aliStudy;
	
	public AliquotId(){
		
	}
	
	

	public AliquotId(String aliCode, String aliStudy) {
		super();
		this.aliCode = aliCode;
		this.aliStudy = aliStudy;
	}



	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AliquotId))
			return false;
		AliquotId castOther = (AliquotId) other;

		return this.getAliCode().equals(castOther.getAliCode())
				&& this.getAliStudy().equals(castOther.getAliStudy());
	}

	public int hashCode() {
		int result = 17;
		result = 37 * 3 * this.aliCode.hashCode() * this.aliStudy.hashCode();
		return result;	
	}
	
	@Column(name = "CODIGO_ALIC", nullable = false, length =50)
	public String getAliCode() {
		return aliCode;
	}
	public void setAliCode(String aliCode) {
		this.aliCode = aliCode;
	}


	@Column(name = "CODIGO_ESTUDIO", nullable = false, length =50)
	public String getAliStudy() {
		return aliStudy;
	}
	public void setAliStudy(String aliStudy) {
		this.aliStudy = aliStudy;
	}

	@Override
	public String toString(){
		return aliCode;
	}



	

}