package ni.org.ics.lab.inventario.domain.relationships;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ni.org.ics.lab.inventario.domain.AliquotType;
import ni.org.ics.lab.inventario.domain.BaseMetaData;
import ni.org.ics.lab.inventario.domain.Study;
import ni.org.ics.lab.inventario.domain.audit.Auditable;

import org.hibernate.annotations.ForeignKey;

/**
 * Simple objeto de dominio que representa un tipo de alicuota para un estudio
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "inv_tipos_alicuotas_estudios", catalog = "inventario")
public class AlicTypeStudy extends BaseMetaData implements Auditable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AlicTypeStudyId tipoAlicuotaEstudioId;
	private Study estudio;
	private AliquotType tipoAlicuota;
	
	
	
	public AlicTypeStudy() {
		
	}

	public AlicTypeStudy(AlicTypeStudyId tipoAlicuotaEstudioId, Study estudio,
			AliquotType tipoAlicuota, String recordUser, Date recordDate) {
		super(recordDate, recordUser);
		this.tipoAlicuotaEstudioId = tipoAlicuotaEstudioId;
		this.estudio = estudio;
		this.tipoAlicuota = tipoAlicuota;
	}
	
	
	@Id
	public AlicTypeStudyId getTipoAlicuotaEstudioId() {
		return tipoAlicuotaEstudioId;
	}
	public void setTipoAlicuotaEstudioId(AlicTypeStudyId tipoAlicuotaEstudioId) {
		this.tipoAlicuotaEstudioId = tipoAlicuotaEstudioId;
	}
	
	@ManyToOne
	@JoinColumn(name="ESTUDIO", insertable = false, updatable = false)
	@ForeignKey(name = "TAS_ESTUDIOS_FK")
	public Study getEstudio() {
		return estudio;
	}
	
	public void setEstudio(Study estudio) {
		this.estudio = estudio;
	}
	@ManyToOne
	@JoinColumn(name="TIPO_ALICUOTA", insertable = false, updatable = false)
	@ForeignKey(name = "TAS_TALICUOTA_FK")
	public AliquotType getTipoAlicuota() {
		return tipoAlicuota;
	}
	public void setTipoAlicuota(AliquotType tipoAlicuota) {
		this.tipoAlicuota = tipoAlicuota;
	}
	
	@Override
	public String toString(){
		return tipoAlicuota.getAlicTypeName();
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
