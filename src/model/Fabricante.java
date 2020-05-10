package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the fabricante database table.
 * 
 */
@Entity
@NamedQuery(name="Fabricante.findAll", query="SELECT f FROM Fabricante f")
public class Fabricante extends Entidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String cif;

	private String nombre;

	//bi-directional many-to-one association to Coche
	@OneToMany(mappedBy="fabricante")
	private List<Coche> coches;

	public Fabricante() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Coche> getCoches() {
		return this.coches;
	}

	public void setCoches(List<Coche> coches) {
		this.coches = coches;
	}


	@Override
	public String toString() {
		return "" + nombre + "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		result = prime * result + ((coches == null) ? 0 : coches.hashCode());
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fabricante other = (Fabricante) obj;
		if (cif == null) {
			if (other.cif != null)
				return false;
		} else if (!cif.equals(other.cif))
			return false;
		if (coches == null) {
			if (other.coches != null)
				return false;
		} else if (!coches.equals(other.coches))
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	public Coche addCoch(Coche coch) {
		getCoches().add(coch);
		coch.setFabricante(this);

		return coch;
	}

	public Coche removeCoch(Coche coch) {
		getCoches().remove(coch);
		coch.setFabricante(null);

		return coch;
	}

}