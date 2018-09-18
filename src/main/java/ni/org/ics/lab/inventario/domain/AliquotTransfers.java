package ni.org.ics.lab.inventario.domain;

import ni.org.ics.lab.inventario.domain.audit.Auditable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Simple objeto de dominio que representa una alicuota
 * 
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "inv_traslado_alicuotas", catalog = "inventario")
public class AliquotTransfers extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String transferCode;
    private String centerCode;
	private String aliCode;
	private int aliPosition;
	private String aliEquip;
	private String aliRack;
	private String aliBox;
	private String aliRes;
	private float aliVol;
	private String aliDestination;
	private String codUser;
	private Date departureDate;
    private String alicTypeName;
	private String aliCond;
	private String aliSep;
	private int numDesc;
	private String eNic;
	private String transportation;
	private String containerNum;
	private String carrier;
	private String searchManager;
	private String packingManager;
	private String boxNum;
	private String request;
	private String approve;
	private String purpose;


    @Id
    @Column(name = "CODIGO_TRASLADO", nullable = false, length = 36)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    public String getTransferCode() {
        return transferCode;
    }

    public void setTransferCode(String transferCode) {
        this.transferCode = transferCode;
    }

    @Column(name = "CODIGO_CENTRO", nullable = false, length =50)
    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }


    @Column(name = "CODIGO_ALIC", nullable = false, length =50)
    public String getAliCode() {
        return aliCode;
    }

    public void setAliCode(String aliCode) {
        this.aliCode = aliCode;
    }

    @Column(name = "CODIGO_CAJA",length =50)
    public String getAliBox() {
        return aliBox;
    }

    public void setAliBox(String aliBox) {
        this.aliBox = aliBox;
    }

	@Column(name = "POSICION")
	public int getAliPosition() {
		return aliPosition;
	}
	public void setAliPosition(int aliPosition) {
		this.aliPosition = aliPosition;
	}
	@Column(name = "VOLUMEN")
	public float getAliVol() {
		return aliVol;
	}
	public void setAliVol(float aliVol) {
		this.aliVol = aliVol;
	}
	@Column(name = "RESULT_ALIC", length =10)
	public String getAliRes() {
		return aliRes;
	}
	public void setAliRes(String aliRes) {
		this.aliRes = aliRes;
	}
	@Column(name = "SEPARA_ALIC", length =10)
	public String getAliSep() {
		return aliSep;
	}
	public void setAliSep(String aliSep) {
		this.aliSep = aliSep;
	}
	@Column(name = "CONDICION_ALIC", length =10)
	public String getAliCond() {
		return aliCond;
	}
	public void setAliCond(String aliCond) {
		this.aliCond = aliCond;
	}

    @Column(name = "CODIGO_EQUIPO", length =50)
    public String getAliEquip() {
        return aliEquip;
    }

    public void setAliEquip(String aliEquip) {
        this.aliEquip = aliEquip;
    }

    @Column(name = "CODIGO_RACK", length =50)
    public String getAliRack() {
        return aliRack;
    }

    public void setAliRack(String aliRack) {
        this.aliRack = aliRack;
    }

    @Column(name = "DESTINO", nullable = false, length =500)
    public String getAliDestination() {
        return aliDestination;
    }

    public void setAliDestination(String aliDestination) {
        this.aliDestination = aliDestination;
    }

    @Column(name = "COD_USER", length =50)
    public String getCodUser() {
        return codUser;
    }

    public void setCodUser(String codUser) {
        this.codUser = codUser;
    }

    @Column(name = "FECHA_SALIDA", nullable = false)
    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    @Column(name = "TIPO_ALIC", length =50)
    public String getAlicTypeName() {
        return alicTypeName;
    }

    public void setAlicTypeName(String alicTypeName) {
        this.alicTypeName = alicTypeName;
    }

    @Column(name = "NUM_DESC")
    public int getNumDesc() {
        return numDesc;
    }

    public void setNumDesc(int numDesc) {
        this.numDesc = numDesc;
    }

    @Column(name = "E_NIC", nullable = false, length =50)
    public String geteNic() {
        return eNic;
    }

    public void seteNic(String eNic) {
        this.eNic = eNic;
    }


    @Column(name = "MEDIO_TRANSP", nullable = false, length =25)
    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }


    @Column(name = "NUM_CONTENEDOR", nullable = false, length =150)
    public String getContainerNum() {
        return containerNum;
    }

    public void setContainerNum(String containerNum) {
        this.containerNum = containerNum;
    }

    @Column(name = "TRANSPORTA", nullable = false, length =150)
    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    @Column(name = "RESP_BUSQUEDA", nullable = false, length =150)
    public String getSearchManager() {
        return searchManager;
    }

    public void setSearchManager(String searchManager) {
        this.searchManager = searchManager;
    }

    @Column(name = "RESP_EMBALAJE", nullable = false, length =150)
    public String getPackingManager() {
        return packingManager;
    }

    public void setPackingManager(String packingManager) {
        this.packingManager = packingManager;
    }

    @Column(name = "NUM_CAJA", nullable = false, length =50)
    public String getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(String boxNum) {
        this.boxNum = boxNum;
    }

    @Column(name = "SOLICITA", nullable = false, length =100)
    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Column(name = "APRUEBA", nullable = false, length =100)
    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    @Column(name = "PROPOSITO", nullable = false, length =255)
    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AliquotTransfers))
			return false;
		
		AliquotTransfers castOther = (AliquotTransfers) other;

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

