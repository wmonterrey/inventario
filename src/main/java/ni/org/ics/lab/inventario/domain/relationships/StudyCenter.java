package ni.org.ics.lab.inventario.domain.relationships;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ni.org.ics.lab.inventario.domain.BaseMetaData;
import ni.org.ics.lab.inventario.domain.Center;
import ni.org.ics.lab.inventario.domain.Study;
import ni.org.ics.lab.inventario.domain.audit.Auditable;

import org.hibernate.annotations.ForeignKey;

/**
 * Simple objeto de dominio que representa un centro para un estudio
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "inv_estudios_centros", catalog = "inventario")
public class StudyCenter extends BaseMetaData implements Auditable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StudyCenterId estudioCentroId;
	private Study estudio;
	private Center centro;
	
	
	
	public StudyCenter() {
		
	}

	public StudyCenter(StudyCenterId estudioCentroId, Study estudio,
			Center centro, String recordUser, Date recordDate) {
		super(recordDate, recordUser);
		this.estudioCentroId = estudioCentroId;
		this.estudio = estudio;
		this.centro = centro;
	}
	
	
	@Id
	public StudyCenterId getEstudioCentroId() {
		return estudioCentroId;
	}
	public void setEstudioCentroId(StudyCenterId estudioCentroId) {
		this.estudioCentroId = estudioCentroId;
	}
	
	@ManyToOne
	@JoinColumn(name="ESTUDIO", insertable = false, updatable = false)
	@ForeignKey(name = "SC_ESTUDIOS_FK")
	public Study getEstudio() {
		return estudio;
	}
	
	public void setEstudio(Study estudio) {
		this.estudio = estudio;
	}
	@ManyToOne
	@JoinColumn(name="CENTRO", insertable = false, updatable = false)
	@ForeignKey(name = "SC_CENTROS_FK")
	public Center getCentro() {
		return centro;
	}
	public void setCentro(Center centro) {
		this.centro = centro;
	}
	
	@Override
	public String toString(){
		return estudio.getStudyName();
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
