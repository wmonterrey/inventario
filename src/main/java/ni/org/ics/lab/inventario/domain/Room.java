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
 * Simple objeto de dominio que representa un cuarto
 * 
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "inv_cuartos", catalog = "inventario", uniqueConstraints={@UniqueConstraint(columnNames = {"NOMBRE_CUARTO" , "CODIGO_CENTRO"})})
public class Room extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roomCode;
	private String roomName;
	private String roomObs;
	private Center roomCenter;
	
	@Id
	@Column(name = "CODIGO_CUARTO", nullable = false, length =50)
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	
	@Column(name = "NOMBRE_CUARTO", nullable = false, length =150)
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	@Column(name = "OBS_CUARTO", nullable = true, length =500)
	public String getRoomObs() {
		return roomObs;
	}
	
	public void setRoomObs(String roomObs) {
		this.roomObs = roomObs;
	}
	
	@ManyToOne(optional=false)
	@JoinColumn(name="CODIGO_CENTRO")
	@ForeignKey(name = "CUARTOS_CENTROS_FK")
	public Center getRoomCenter() {
		return roomCenter;
	}
	public void setRoomCenter(Center roomCenter) {
		this.roomCenter = roomCenter;
	}
	@Override
	public String toString(){
		return roomName;
	}
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Room))
			return false;
		
		Room castOther = (Room) other;

		return (this.getRoomCode().equals(castOther.getRoomCode()));
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

