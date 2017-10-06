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
 * Simple objeto de dominio que representa un rack
 * 
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "inv_racks", catalog = "inventario", uniqueConstraints={@UniqueConstraint(columnNames = {"POSICION","CODIGO_EQUIPO","PASIVO"})})
public class Rack extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rackCode;
	private String rackName;
	private String rackObs;
	private Equipment rackEquip;
	private int rackPosition;
	private int rackCapacity;
	private int rackRows;
	private int rackColumns;
	private int rackPriority;
	
	
	@Id
	@Column(name = "CODIGO_RACK", nullable = false, length =50)
	public String getRackCode() {
		return rackCode;
	}
	public void setRackCode(String rackCode) {
		this.rackCode = rackCode;
	}
	
	@Column(name = "NOMBRE_RACK", nullable = false, length =150)
	public String getRackName() {
		return rackName;
	}
	public void setRackName(String rackName) {
		this.rackName = rackName;
	}
	
	@Column(name = "OBS_RACK", nullable = true, length =500)
	public String getRackObs() {
		return rackObs;
	}
	
	public void setRackObs(String rackObs) {
		this.rackObs = rackObs;
	}
	@Column(name = "CAPACIDAD", nullable = false)
	public int getRackCapacity() {
		return rackCapacity;
	}
	public void setRackCapacity(int rackCapacity) {
		this.rackCapacity = rackCapacity;
	}
	@Column(name = "FILAS", nullable = false)
	public int getRackRows() {
		return rackRows;
	}
	public void setRackRows(int rackRows) {
		this.rackRows = rackRows;
	}
	@Column(name = "COLUMNAS", nullable = false)
	public int getRackColumns() {
		return rackColumns;
	}
	public void setRackColumns(int rackColumns) {
		this.rackColumns = rackColumns;
	}
	@Column(name = "POSICION", nullable = false)
	public int getRackPosition() {
		return rackPosition;
	}
	public void setRackPosition(int rackPosition) {
		this.rackPosition = rackPosition;
	}
	@Column(name = "PRIORIDAD", nullable = false)
	public int getRackPriority() {
		return rackPriority;
	}
	public void setRackPriority(int rackPriority) {
		this.rackPriority = rackPriority;
	}
	@ManyToOne(optional=false)
	@JoinColumn(name="CODIGO_EQUIPO")
	@ForeignKey(name = "RACKS_EQUIPOS_FK")
	public Equipment getRackEquip() {
		return rackEquip;
	}
	public void setRackEquip(Equipment rackEquip) {
		this.rackEquip = rackEquip;
	}
	@Override
	public String toString(){
		return rackName;
	}
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Rack))
			return false;
		
		Rack castOther = (Rack) other;

		return (this.getRackCode().equals(castOther.getRackCode()));
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

