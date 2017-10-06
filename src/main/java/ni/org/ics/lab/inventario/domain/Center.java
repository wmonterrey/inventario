package ni.org.ics.lab.inventario.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ni.org.ics.lab.inventario.domain.audit.Auditable;

/**
 * Simple objeto de dominio que representa un centro
 * 
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "inv_centros", catalog = "inventario", uniqueConstraints = @UniqueConstraint(columnNames = "NOMBRE_CENTRO"))
public class Center extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String centerCode;
	private String centerName;
	private String centerContact;
	private String centerAddress;
	private String centerPhoneNumber;
	private String centerEmail;
	private String centerObs;
	
	@Id
	@Column(name = "CODIGO_CENTRO", nullable = false, length =50)
	public String getCenterCode() {
		return centerCode;
	}
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}
	
	@Column(name = "NOMBRE_CENTRO", nullable = false, length =150)
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	
	@Column(name = "CONTACTO_CENTRO", nullable = true, length =150)
	public String getCenterContact() {
		return centerContact;
	}
	public void setCenterContact(String centerContact) {
		this.centerContact = centerContact;
	}
	
	@Column(name = "DIRECCION_CENTRO", nullable = true, length =255)
	public String getCenterAddress() {
		return centerAddress;
	}
	public void setCenterAddress(String centerAddress) {
		this.centerAddress = centerAddress;
	}
	
	@Column(name = "TELEFONO_CENTRO", nullable = true, length =25)	
	public String getCenterPhoneNumber() {
		return centerPhoneNumber;
	}
	public void setCenterPhoneNumber(String centerPhoneNumber) {
		this.centerPhoneNumber = centerPhoneNumber;
	}
	
	@Column(name = "EMAIL_CENTRO", nullable = true, length =50)
	public String getCenterEmail() {
		return centerEmail;
	}
	public void setCenterEmail(String centerEmail) {
		this.centerEmail = centerEmail;
	}
	
	@Column(name = "OBS_CENTRO", nullable = true, length =500)
	public String getCenterObs() {
		return centerObs;
	}
	
	public void setCenterObs(String centerObs) {
		this.centerObs = centerObs;
	}
	@Override
	public String toString(){
		return centerName;
	}
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Center))
			return false;
		
		Center castOther = (Center) other;

		return (this.getCenterCode().equals(castOther.getCenterCode()));
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

