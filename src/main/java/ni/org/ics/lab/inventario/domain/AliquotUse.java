package ni.org.ics.lab.inventario.domain;

import ni.org.ics.lab.inventario.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Simple objeto de dominio que representa el uso de una alicuota
 *
 * @author ics
 **/

@Entity
@Table(name = "inv_alicuotas_uso", catalog = "inventario")
public class AliquotUse extends BaseMetaData implements Auditable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String useCode;
    private String aliCode;
    private String aliStudy;
    private Box aliBox;
    private int aliPosition;
    private float aliVol;
    private float aliUsedVol;
    private String aliUse;
    private int aliDesc=0;
    private String aliCondition;

    @Id
    @Column(name = "CODIGO_USO", nullable = false, length = 36)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    public String getUseCode() {
        return useCode;
    }

    public void setUseCode(String useCode) {
        this.useCode = useCode;
    }


    @Column(name = "CODIGO_ALIC", nullable = false, length = 50)
    public String getAliCode() {
        return aliCode;
    }

    public void setAliCode(String aliCode) {
        this.aliCode = aliCode;
    }

    @Column(name = "CODIGO_ESTUDIO", nullable = false, length = 50)
    public String getAliStudy() {
		return aliStudy;
	}

	public void setAliStudy(String aliStudy) {
		this.aliStudy = aliStudy;
	}

	@ManyToOne(optional = false)
    @JoinColumn(name = "CODIGO_CAJA")
    @ForeignKey(name = "ALICS_BOX_FK1")
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

    @Column(name = "VOLUMEN_USADO", nullable = false)
    public float getAliUsedVol() {
        return aliUsedVol;
    }

    public void setAliUsedVol(float aliUsedVol) {
        this.aliUsedVol = aliUsedVol;
    }

    @Column(name = "USO_ALIC", nullable = false)
    public String getAliUse() {
        return aliUse;
    }

    public void setAliUse(String aliUse) {
        this.aliUse = aliUse;
    }

    @Column(name = "CONDICION_ALIC", nullable = false)
    public String getAliCondition() {
        return aliCondition;
    }

    public void setAliCondition(String aliCondition) {
        this.aliCondition = aliCondition;
    }
    @Column(name = "DESCONGELAMIENTOS", nullable = true)
	public int getAliDesc() {
		return aliDesc;
	}
	public void setAliDesc(int aliDesc) {
		this.aliDesc = aliDesc;
	}

    @Override
    public boolean equals(Object other) {

        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof AliquotUse))
            return false;

        AliquotUse castOther = (AliquotUse) other;

        return (this.getAliCode().equals( castOther.getAliCode() ));
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        //Campos no auditables en la tabla
        if (fieldname.matches( "recordDate" ) || fieldname.matches( "recordUser" )) {
            return false;
        }
        return true;
    }

}

