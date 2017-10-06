package ni.org.ics.lab.inventario.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass 
public class BaseMetaData implements Serializable 
{  

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date recordDate;
	private String recordUser;
	private char pasive = '0';
	
	public BaseMetaData() {

	}

	public BaseMetaData(Date recordDate, String recordUser) {
		super();
		this.recordDate = recordDate;
		this.recordUser = recordUser;
	}

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="FECHA_REGISTRO")
	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	@Column(name="USUARIO_REGISTRO", length = 50)
	public String getRecordUser() {
		return recordUser;
	}

	public void setRecordUser(String recordUser) {
		this.recordUser = recordUser;
	}
	
	@Column(name="PASIVO", nullable = false, length = 1)
	public char getPasive() {
		return pasive;
	}

	public void setPasive(char pasive) {
		this.pasive = pasive;
	}

}  
