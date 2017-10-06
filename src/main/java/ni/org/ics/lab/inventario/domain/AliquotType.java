package ni.org.ics.lab.inventario.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ni.org.ics.lab.inventario.domain.audit.Auditable;


/**
 * Simple objeto de dominio que representa un tipos_alicuota
 * 
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "inv_tipos_alicuota", catalog = "inventario" , uniqueConstraints = {@UniqueConstraint(columnNames = {"NOMBRE_TIPO_ALIC","TEMPERATURA"})})
public class AliquotType extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String alicTypeCode;
	private String alicTypeName;
	private String alicTypeUse;
	private float alicTypeTemp;
	private float alicTypeVol;
	private String alicTypeSample;
	private String alicTypeObs;
	
	@Id
	@Column(name = "CODIGO_TIPO_ALIC", nullable = false, length =50)
	public String getAlicTypeCode() {
		return alicTypeCode;
	}
	public void setAlicTypeCode(String alicTypeCode) {
		this.alicTypeCode = alicTypeCode;
	}
	@Column(name = "NOMBRE_TIPO_ALIC", nullable = false, length =50)
	public String getAlicTypeName() {
		return alicTypeName;
	}
	public void setAlicTypeName(String alicTypeName) {
		this.alicTypeName = alicTypeName;
	}
	@Override
	public String toString(){
		return alicTypeName;
	}
	@Column(name = "OBS_TIPO_ALIC", nullable = true, length =500)
	public String getAlicTypeObs() {
		return alicTypeObs;
	}
	public void setAlicTypeObs(String alicTypeObs) {
		this.alicTypeObs = alicTypeObs;
	}
	@Column(name = "USO_ALIC", nullable = false, length =50)
	public String getAlicTypeUse() {
		return alicTypeUse;
	}
	public void setAlicTypeUse(String alicTypeUse) {
		this.alicTypeUse = alicTypeUse;
	}
	@Column(name = "TEMPERATURA", nullable = true)
	public float getAlicTypeTemp() {
		return alicTypeTemp;
	}
	public void setAlicTypeTemp(float alicTypeTemp) {
		this.alicTypeTemp = alicTypeTemp;
	}
	@Column(name = "VOLUMEN", nullable = true)
	public float getAlicTypeVol() {
		return alicTypeVol;
	}
	public void setAlicTypeVol(float alicTypeVol) {
		this.alicTypeVol = alicTypeVol;
	}
	@Column(name = "TIPO_MUESTRA", nullable = false, length =50)
	public String getAlicTypeSample() {
		return alicTypeSample;
	}
	public void setAlicTypeSample(String alicTypeSample) {
		this.alicTypeSample = alicTypeSample;
	}
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AliquotType))
			return false;
		
		AliquotType castOther = (AliquotType) other;

		return (this.getAlicTypeCode().equals(castOther.getAlicTypeCode()));
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

