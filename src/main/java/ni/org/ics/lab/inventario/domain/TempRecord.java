package ni.org.ics.lab.inventario.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

import ni.org.ics.lab.inventario.domain.audit.Auditable;

/**
 * Simple objeto de dominio que representa un rack
 * 
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "inv_temps", catalog = "inventario")
public class TempRecord extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tempCode;
	private Date tempDate;
	private float tempRecord;
	private String tempObs;
	private Equipment tempEquip;
	private char malEstado = '0';
	private char fueraRango = '0';
	
	@Id
	@Column(name = "CODIGO_TEMP", nullable = false, length =50)
	public String getTempCode() {
		return tempCode;
	}
	public void setTempCode(String tempCode) {
		this.tempCode = tempCode;
	}
	
	@Column(name = "FECHA_TEMP", nullable = false)
	public Date getTempDate() {
		return tempDate;
	}
	public void setTempDate(Date tempDate) {
		this.tempDate = tempDate;
	}
	@ManyToOne(optional=false)
	@JoinColumn(name="CODIGO_EQUIPO")
	@ForeignKey(name = "TEMPS_EQUIPOS_FK")
	public Equipment getTempEquip() {
		return tempEquip;
	}
	public void setTempEquip(Equipment tempEquip) {
		this.tempEquip = tempEquip;
	}
	
	@Column(name = "TEMP", nullable = false)
	public float getTempRecord() {
		return tempRecord;
	}
	public void setTempRecord(float tempRecord) {
		this.tempRecord = tempRecord;
	}
	
	@Column(name = "OBS_TEMP", nullable = true, length =500)
	public String getTempObs() {
		return tempObs;
	}
	public void setTempObs(String tempObs) {
		this.tempObs = tempObs;
	}
	
	
	@Column(name = "MAL_ESTADO", nullable = false)
	public char getMalEstado() {
		return malEstado;
	}
	public void setMalEstado(char malEstado) {
		this.malEstado = malEstado;
	}
	
	@Column(name = "FUERA_RANGO", nullable = false)
	public char getFueraRango() {
		return fueraRango;
	}
	public void setFueraRango(char fueraRango) {
		this.fueraRango = fueraRango;
	}
	@Override
	public String toString(){
		return tempCode;
	}
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TempRecord))
			return false;
		
		TempRecord castOther = (TempRecord) other;

		return (this.getTempCode().equals(castOther.getTempCode()));
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

