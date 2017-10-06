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
 * Simple objeto de dominio que representa una caja
 * 
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "inv_cajas", catalog = "inventario", uniqueConstraints={@UniqueConstraint(columnNames = {"POSICION","CODIGO_RACK","PASIVO"})})
public class Box extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String boxCode;
	private String boxName;
	private String boxObs;
	private Rack boxRack;
	private int boxPosition;
	private Study boxStudy;
	private String boxAlicType;
	private String boxAlicUse;
	private float boxTemp;
	private String boxUse;
	private String boxResult;
	private int boxRows;
	private int boxColumns;
	private int boxCapacity;
	private int boxPriority;
	
	
	@Id
	@Column(name = "CODIGO_CAJA", nullable = false, length =50)
	public String getBoxCode() {
		return boxCode;
	}
	public void setBoxCode(String boxCode) {
		this.boxCode = boxCode;
	}
	
	@Column(name = "NOMBRE_CAJA", nullable = false, length =150)
	public String getBoxName() {
		return boxName;
	}
	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}
	
	@Column(name = "OBS_CAJA", nullable = true, length =500)
	public String getBoxObs() {
		return boxObs;
	}
	
	public void setBoxObs(String boxObs) {
		this.boxObs = boxObs;
	}
	@Column(name = "FILAS", nullable = false)
	public int getBoxRows() {
		return boxRows;
	}
	public void setBoxRows(int boxRows) {
		this.boxRows = boxRows;
	}
	@Column(name = "COLUMNAS", nullable = false)
	public int getBoxColumns() {
		return boxColumns;
	}
	public void setBoxColumns(int boxColumns) {
		this.boxColumns = boxColumns;
	}
	@Column(name = "CAPACIDAD", nullable = false)
	public int getBoxCapacity() {
		return boxCapacity;
	}
	public void setBoxCapacity(int boxCapacity) {
		this.boxCapacity = boxCapacity;
	}
	@Column(name = "POSICION", nullable = false)
	public int getBoxPosition() {
		return boxPosition;
	}
	public void setBoxPosition(int boxPosition) {
		this.boxPosition = boxPosition;
	}
	@Column(name = "PRIORIDAD", nullable = false)
	public int getBoxPriority() {
		return boxPriority;
	}
	public void setBoxPriority(int boxPriority) {
		this.boxPriority = boxPriority;
	}
	@ManyToOne(optional=false)
	@JoinColumn(name="CODIGO_RACK")
	@ForeignKey(name = "BOXES_RACK_FK")
	public Rack getBoxRack() {
		return boxRack;
	}
	public void setBoxRack(Rack boxRack) {
		this.boxRack = boxRack;
	}
	@ManyToOne(optional=false)
	@JoinColumn(name="CODIGO_ESTUDIO")
	@ForeignKey(name = "BOXES_STUDY_FK")
	public Study getBoxStudy() {
		return boxStudy;
	}
	public void setBoxStudy(Study boxStudy) {
		this.boxStudy = boxStudy;
	}
	@Column(name = "USO_ALICUOTAS", nullable = false, length =50)
	public String getBoxAlicUse() {
		return boxAlicUse;
	}
	public void setBoxAlicUse(String boxAlicUse) {
		this.boxAlicUse = boxAlicUse;
	}
	@Column(name = "TIPO_ALICUOTAS", nullable = false, length =500)
	public String getBoxAlicType() {
		return boxAlicType;
	}
	public void setBoxAlicType(String boxAlicType) {
		this.boxAlicType = boxAlicType;
	}
	@Column(name = "TEMPERATURA", nullable = true)
	public float getBoxTemp() {
		return boxTemp;
	}
	public void setBoxTemp(float boxTemp) {
		this.boxTemp = boxTemp;
	}
	@Column(name = "USO_CAJA", nullable = true, length =15)
	public String getBoxUse() {
		return boxUse;
	}
	public void setBoxUse(String boxUse) {
		this.boxUse = boxUse;
	}
	@Column(name = "TIPO_RESULTADO", nullable = true, length =15)
	public String getBoxResult() {
		return boxResult;
	}
	public void setBoxResult(String boxResult) {
		this.boxResult = boxResult;
	}
	@Override
	public String toString(){
		return boxName;
	}
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Box))
			return false;
		
		Box castOther = (Box) other;

		return (this.getBoxCode().equals(castOther.getBoxCode()));
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

