package ni.org.ics.lab.inventario.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

import ni.org.ics.lab.inventario.domain.audit.Auditable;

/**
 * Simple objeto de dominio que representa una alicuota
 * 
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "inv_alicuotas", catalog = "inventario", uniqueConstraints={@UniqueConstraint(columnNames = {"POSICION","CODIGO_CAJA","PASIVO"})})
public class Aliquot extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String aliCode;
	private Box aliBox;
	private int aliPosition;
	private float aliVol;
	private String alicTypeName;
	private String aliRes;
	private String aliCond;
	private String aliSep;
	private String aliObs;
	

	@Id
	@Column(name = "CODIGO_ALIC", nullable = false, length =50)
	public String getAliCode() {
		return aliCode;
	}
	public void setAliCode(String aliCode) {
		this.aliCode = aliCode;
	}
	@ManyToOne(optional=false)
	@JoinColumn(name="CODIGO_CAJA")
	@ForeignKey(name = "ALICS_BOX_FK")
	public Box getAliBox() {
		return aliBox;
	}
	public void setAliBox(Box aliBox) {
		this.aliBox = aliBox;
	}

	@Column(name = "POSICION", nullable = false)
	public int getAliPosition() {
		return aliPosition;
	}
	public void setAliPosition(int aliPosition) {
		this.aliPosition = aliPosition;
	}
	@Column(name = "VOLUMEN", nullable = false)
	public float getAliVol() {
		return aliVol;
	}
	public void setAliVol(float aliVol) {
		this.aliVol = aliVol;
	}
	@Column(name = "TIPO_ALIC", nullable = true, length =50)
	public String getAlicTypeName() {
		return alicTypeName;
	}
	public void setAlicTypeName(String alicTypeName) {
		this.alicTypeName = alicTypeName;
	}
	@Column(name = "RESULT_ALIC", nullable = true, length =10)
	public String getAliRes() {
		return aliRes;
	}
	public void setAliRes(String aliRes) {
		this.aliRes = aliRes;
	}
	@Column(name = "CONDICION_ALIC", nullable = true, length =10)
	public String getAliCond() {
		return aliCond;
	}
	public void setAliCond(String aliCond) {
		this.aliCond = aliCond;
	}
	@Column(name = "SEPARA_ALIC", nullable = true, length =10)
	public String getAliSep() {
		return aliSep;
	}
	public void setAliSep(String aliSep) {
		this.aliSep = aliSep;
	}
	@Column(name = "OBS_ALIC", nullable = true, length =500)
	public String getAliObs() {
		return aliObs;
	}
	public void setAliObs(String aliObs) {
		this.aliObs = aliObs;
	}
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Aliquot))
			return false;
		
		Aliquot castOther = (Aliquot) other;

		return (this.getAliCode().equals(castOther.getAliCode()));
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

