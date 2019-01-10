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
	private AliquotId aliId;
	private Box aliBox;
	private int aliPosition;
	private float aliVol;
	private String alicTypeName;
	private String aliRes;
	private String aliCond;
	private String aliSep;
	private String aliStatus;
	private int aliDesc=0;
	private String aliObs;
	
	@Id
	public AliquotId getAliId() {
		return aliId;
	}
	public void setAliId(AliquotId aliId) {
		this.aliId = aliId;
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
	@Column(name = "ESTADO_ALIC", nullable = true, length =10)
	public String getAliStatus() {
		return aliStatus;
	}
	public void setAliStatus(String aliStatus) {
		this.aliStatus = aliStatus;
	}
	@Column(name = "SEPARA_ALIC", nullable = true, length =10)
	public String getAliSep() {
		return aliSep;
	}
	public void setAliSep(String aliSep) {
		this.aliSep = aliSep;
	}
	@Column(name = "DESCONGELAMIENTOS", nullable = true)
	public int getAliDesc() {
		return aliDesc;
	}
	public void setAliDesc(int aliDesc) {
		this.aliDesc = aliDesc;
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

		return (this.getAliId().equals(castOther.getAliId()));
	}
	@Override
	public String toString(){
		return aliId.getAliCode();
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

