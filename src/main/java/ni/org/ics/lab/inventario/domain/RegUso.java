package ni.org.ics.lab.inventario.domain;

// Generated Nov 15, 2012 9:23:03 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;


/**
 * RegUso
 */
@Entity
@Table(name = "reg_alic_uso", catalog = "simlab")
public class RegUso implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6890416469363700411L;
	private String codigoAlic;
	private int codFreezer;
	private int posBox;
	private String codRack;
	private int codBox;
	private String negPos;
	private String volAlic;
	private Float pesoAlic;
	private String tipo;
	private String condicion;
	private String separada;
	private Date fehorReg;
	private String codUser;
	private int numDes;
	private String usoAlic;
	private String volUsado;
	private String usuarioUso;
	private Timestamp fechaUso;


	public RegUso() {
	}

	@Id
	@Column(name = "cod_alic", length =25)
	public String getCodigoAlic() {
		return codigoAlic;
	}

	public void setCodigoAlic(String codigoAlic) {
		this.codigoAlic = codigoAlic;
	}

	@Column(name = "cod_freezer", nullable = false)
	public int getCodFreezer() {
		return this.codFreezer;
	}

	public void setCodFreezer(int codFreezer) {
		this.codFreezer = codFreezer;
	}

	@Column(name = "cod_rack", length = 7, nullable = false)
	public String getCodRack() {
		return this.codRack;
	}

	public void setCodRack(String codRack) {
		this.codRack = codRack;
	}

	@Column(name = "cod_box", nullable = false)
	public int getCodBox() {
		return this.codBox;
	}

	public void setCodBox(int codBox) {
		this.codBox = codBox;
	}

	@Column(name = "neg_pos", length =3, nullable = false)
	public String getNegPos() {
		return this.negPos;
	}

	public void setNegPos(String negPos) {
		this.negPos = negPos;
	}

	@Column(name = "fehor_reg", length =19, nullable = false)
	public Date getFehorReg() {
		return this.fehorReg;
	}

	public void setFehorReg(Date fehorReg) {
		this.fehorReg = fehorReg;
	}

	@Column(name = "cod_user", length =20, nullable = false)
	public String getCodUser() {
		return this.codUser;
	}

	public void setCodUser(String codUser) {
		this.codUser = codUser;
	}

	@Column(name = "vol_alic", nullable = false)
	public String getVolAlic() {
		return volAlic;
	}

	public void setVolAlic(String volAlic) {
		this.volAlic = volAlic;
	}

	@Column(name = "peso_alic", nullable = false)
	public Float getPesoAlic() {
		return pesoAlic;
	}

	public void setPesoAlic(Float pesoAlic) {
		this.pesoAlic = pesoAlic;
	}

	@Column(name = "tipo", nullable = false, length = 20)
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "condicion", length =20, nullable = false)
	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	@Column(name = "separada", length =20, nullable = false)
	public String getSeparada() {
		return separada;
	}

	public void setSeparada(String separada) {
		this.separada = separada;
	}

	@Column(name = "num_desc", nullable = false)
	public int getNumDes() {
		return numDes;
	}

	public void setNumDes(int numDes) {
		this.numDes = numDes;
	}

	@Column(name = "pos_box", nullable = false)
	public int getPosBox() {
		return posBox;
	}

	public void setPosBox(int posBox) {
		this.posBox = posBox;
	}

	@Column(name = "uso", length =250, nullable = false)
	public String getUsoAlic() {
		return usoAlic;
	}

	public void setUsoAlic(String usoAlic) {
		this.usoAlic = usoAlic;
	}

	@Column(name = "vol_usado", length =25, nullable = false)
	public String getVolUsado() {
		return volUsado;
	}

	public void setVolUsado(String volUsado) {
		this.volUsado = volUsado;
	}

	@Column(name = "usuario", length =250, nullable = false)
	public String getUsuarioUso() {
		return usuarioUso;
	}

	public void setUsuarioUso(String usuarioUso) {
		this.usuarioUso = usuarioUso;
	}
	@Column(name = "fecha_uso", nullable = false)
	public Timestamp getFechaUso() {
		return fechaUso;
	}

	public void setFechaUso(Timestamp fechaUso) {
		this.fechaUso = fechaUso;
	}

	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RegUso))
			return false;
		
		RegUso castOther = (RegUso) other;

		return (this.getCodigoAlic().equals(castOther.getCodigoAlic()));
	}

}
