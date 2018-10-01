package ni.org.ics.lab.inventario.domain.complex;

import java.util.List;

import ni.org.ics.lab.inventario.domain.Aliquot;
import ni.org.ics.lab.inventario.domain.Box;

public class BoxAliquots {
	
	private Box box;
	private List<Aliquot> aliquots;
	private String mensaje;
	
	public Box getBox() {
		return box;
	}
	public void setBox(Box box) {
		this.box = box;
	}
	public List<Aliquot> getAliquots() {
		return aliquots;
	}
	public void setAliquots(List<Aliquot> aliquots) {
		this.aliquots = aliquots;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	

}
