package ni.org.ics.lab.inventario.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ni.org.ics.lab.inventario.domain.audit.Auditable;


/**
 * Simple objeto de dominio que representa un Estudio
 * 
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "inv_estudios", catalog = "inventario" , uniqueConstraints = @UniqueConstraint(columnNames = "NOMBRE_ESTUDIO"))
public class Study extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String studyCode;
	private String studyName;
	private String studyPattern;
	private String studyObs;
	
	@Id
	@Column(name = "CODIGO_ESTUDIO", nullable = false, length =50)
	public String getStudyCode() {
		return studyCode;
	}
	public void setStudyCode(String studyCode) {
		this.studyCode = studyCode;
	}
	@Column(name = "NOMBRE_ESTUDIO", nullable = false, length =150)
	public String getStudyName() {
		return studyName;
	}
	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}
	@Override
	public String toString(){
		return studyName;
	}
	@Column(name = "OBS_ESTUDIO", nullable = true, length =500)
	public String getStudyObs() {
		return studyObs;
	}
	public void setStudyObs(String studyObs) {
		this.studyObs = studyObs;
	}
	@Column(name = "PATRON_ESTUDIO", nullable = true, length =750)
	public String getStudyPattern() {
		return studyPattern;
	}
	public void setStudyPattern(String studyPattern) {
		this.studyPattern = studyPattern;
	}
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Study))
			return false;
		
		Study castOther = (Study) other;

		return (this.getStudyCode().equals(castOther.getStudyCode()));
	}
	@Override
	public boolean isFieldAuditable(String fieldname) {
		//Campos no auditables en la tabla
		if(fieldname.matches("recordDate")||fieldname.matches("recordUser")){
			return false;
		}
		return true;
	}

}

