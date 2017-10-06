package ni.org.ics.lab.inventario.domain.relationships;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class AlicTypeStudyId implements Serializable {
	/**
	 * Objeto que representa la clave unica de relacion tipo de alicuota/estudio
	 * 
	 * @author William Aviles
	 **/
	
	private static final long serialVersionUID = 1L;
	private String estudio;
	private String tipoAlicuota;
	
	public AlicTypeStudyId(){
		
	}
	
	

	public AlicTypeStudyId(String estudio, String tipoAlicota) {
		super();
		this.estudio = estudio;
		this.tipoAlicuota = tipoAlicota;
	}



	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AlicTypeStudyId))
			return false;
		AlicTypeStudyId castOther = (AlicTypeStudyId) other;

		return this.getEstudio().equals(castOther.getEstudio())
				&& this.getTipoAlicuota().equals(castOther.getTipoAlicuota());
	}

	public int hashCode() {
		int result = 17;
		result = 37 * 3 * this.estudio.hashCode() * this.tipoAlicuota.hashCode();
		return result;	
	}
	
	@Column(name = "ESTUDIO", nullable = false, length =50)
	public String getEstudio() {
		return estudio;
	}


	public void setEstudio(String estudio) {
		this.estudio = estudio;
	}

	@Column(name = "TIPO_ALICUOTA", nullable = false, length =50)
	public String getTipoAlicuota() {
		return tipoAlicuota;
	}


	public void setTipoAlicuota(String tipoAlicuota) {
		this.tipoAlicuota = tipoAlicuota;
	}

	@Override
	public String toString(){
		return estudio;
	}

}