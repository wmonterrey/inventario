package ni.org.ics.lab.inventario.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

import ni.org.ics.lab.inventario.domain.audit.Auditable;

/**
 * Simple objeto de dominio que representa un equipo
 * 
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "inv_equipos", catalog = "inventario")
public class Equipment extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String equipCode;
	private String equipName;
	private String equipObs;
	private Room equipRoom;
	private float equipTempMin;
	private float equipTempMax;
	private String equipUse;
	private String equipType;
	private int equipCapacity;
	private int equipRows;
	private int equipColumns;
	private String equipSerie;
	private String equipBrand;
	private String equipModel;
	private String equipResp;
	private int equipPriority;
	
	@Id
	@Column(name = "CODIGO_EQUIPO", nullable = false, length =50)
	public String getEquipCode() {
		return equipCode;
	}
	public void setEquipCode(String equipCode) {
		this.equipCode = equipCode;
	}
	
	@Column(name = "NOMBRE_EQUIPO", nullable = false, length =150)
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	
	@Column(name = "OBS_EQUIPO", nullable = true, length =500)
	public String getEquipObs() {
		return equipObs;
	}
	
	public void setEquipObs(String equipObs) {
		this.equipObs = equipObs;
	}
	
	@Column(name = "TEMP_MIN", nullable = false)
	public float getEquipTempMin() {
		return equipTempMin;
	}
	public void setEquipTempMin(float equipTempMin) {
		this.equipTempMin = equipTempMin;
	}
	@Column(name = "TEMP_MAX", nullable = false)
	public float getEquipTempMax() {
		return equipTempMax;
	}
	public void setEquipTempMax(float equipTempMax) {
		this.equipTempMax = equipTempMax;
	}
	@Column(name = "USO_EQUIPO", nullable = false, length =500)
	public String getEquipUse() {
		return equipUse;
	}
	public void setEquipUse(String equipUse) {
		this.equipUse = equipUse;
	}
	@Column(name = "TIPO_EQUIPO", nullable = false, length =10)
	public String getEquipType() {
		return equipType;
	}
	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}
	@Column(name = "CAPACIDAD", nullable = false)
	public int getEquipCapacity() {
		return equipCapacity;
	}
	public void setEquipCapacity(int equipCapacity) {
		this.equipCapacity = equipCapacity;
	}
	@Column(name = "FILAS", nullable = false)
	public int getEquipRows() {
		return equipRows;
	}
	public void setEquipRows(int equipRows) {
		this.equipRows = equipRows;
	}
	@Column(name = "COLUMNAS", nullable = false)
	public int getEquipColumns() {
		return equipColumns;
	}
	public void setEquipColumns(int equipColumns) {
		this.equipColumns = equipColumns;
	}
	@Column(name = "MARCA", nullable = true)
	public String getEquipBrand() {
		return equipBrand;
	}
	public void setEquipBrand(String equipBrand) {
		this.equipBrand = equipBrand;
	}
	@Column(name = "SERIE", nullable = true)
	public String getEquipSerie() {
		return equipSerie;
	}
	public void setEquipSerie(String equipSerie) {
		this.equipSerie = equipSerie;
	}
	@Column(name = "MODELO", nullable = true)
	public String getEquipModel() {
		return equipModel;
	}
	public void setEquipModel(String equipModel) {
		this.equipModel = equipModel;
	}
	@Column(name = "RESPONSABLE", nullable = true)
	public String getEquipResp() {
		return equipResp;
	}
	public void setEquipResp(String equipResp) {
		this.equipResp = equipResp;
	}
	@Column(name = "PRIORIDAD", nullable = false)
	public int getEquipPriority() {
		return equipPriority;
	}
	public void setEquipPriority(int equipPriority) {
		this.equipPriority = equipPriority;
	}
	@ManyToOne(optional=false)
	@JoinColumn(name="CODIGO_CUARTO")
	@ForeignKey(name = "EQUIPOS_CUARTOS_FK")
	public Room getEquipRoom() {
		return equipRoom;
	}
	public void setEquipRoom(Room equipRoom) {
		this.equipRoom = equipRoom;
	}
	@Override
	public String toString(){
		return equipName;
	}
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Equipment))
			return false;
		
		Equipment castOther = (Equipment) other;

		return (this.getEquipCode().equals(castOther.getEquipCode()));
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

