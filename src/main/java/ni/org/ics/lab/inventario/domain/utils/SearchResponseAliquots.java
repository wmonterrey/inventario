package ni.org.ics.lab.inventario.domain.utils;

import java.util.List;

import ni.org.ics.lab.inventario.domain.Aliquot;
import ni.org.ics.lab.inventario.domain.AliquotOutput;
import ni.org.ics.lab.inventario.domain.AliquotUse;

public class SearchResponseAliquots {
	
	
	private List<Aliquot> alics;
	private List<AliquotUse> alicsused;
	private List<AliquotOutput> alicssent;

	public SearchResponseAliquots() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Aliquot> getAlics() {
		return alics;
	}

	public void setAlics(List<Aliquot> alics) {
		this.alics = alics;
	}

	public List<AliquotUse> getAlicsused() {
		return alicsused;
	}

	public void setAlicsused(List<AliquotUse> alicsused) {
		this.alicsused = alicsused;
	}

	public List<AliquotOutput> getAlicssent() {
		return alicssent;
	}

	public void setAlicssent(List<AliquotOutput> alicssent) {
		this.alicssent = alicssent;
	}
	
	
	

}
