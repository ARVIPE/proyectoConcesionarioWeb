package model.controladores;

import java.util.List;		

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Coche;
import model.Controlador;
import model.Venta;


public class VentaControlador extends Controlador {

	private static VentaControlador controlador = null;

	public VentaControlador () {
		super(Venta.class, "VentaDeCoches");
	}
	
	/**
	 * 
	 * @return
	 */
	public static VentaControlador getControlador () {
		if (controlador == null) {
			controlador = new VentaControlador();
		}
		return controlador;
	}

	/**
	 *  
	 */
	public Venta find (int id) {
		return (Venta) super.find(id);
	}

	
	/**
	 * 
	 * @return
	 */
	public Venta findFirst () {
		try {
			EntityManager em = getEntityManagerFactory().createEntityManager();
			Query q = em.createQuery("SELECT v FROM Venta v order by v.id", Venta.class);
			Venta resultado = (Venta) q.setMaxResults(1).getSingleResult();
			em.close();
			return resultado;
		}
		catch (NoResultException nrEx) {
			return null;
		}
	}

	
	
	
	/**
	 * 
	 * @return
	 */
	public Venta findLast () {
		try {
			EntityManager em = getEntityManagerFactory().createEntityManager();
			Query q = em.createQuery("SELECT v FROM Venta v order by v.id desc", Venta.class);
			Venta resultado = (Venta) q.setMaxResults(1).getSingleResult();
			em.close();
			return resultado;
		}
		catch (NoResultException nrEx) {
			return null;
		}
	}

	
	
	
	/**
	 * 
	 * @return
	 */
	public Venta findNext (Venta v) {
		try {
			EntityManager em = getEntityManagerFactory().createEntityManager();
			Query q = em.createQuery("SELECT v FROM Venta v where v.id > :idActual order by v.id", Venta.class);
			q.setParameter("idActual", v.getId());
			Venta resultado = (Venta) q.setMaxResults(1).getSingleResult();
			em.close();
			return resultado;
		}
		catch (NoResultException nrEx) {
			return null;
		}
	}

	
	
	
	/**
	 * 
	 * @return
	 */
	public Venta findPrevious (Venta c) {
		try {
			EntityManager em = getEntityManagerFactory().createEntityManager();
			Query q = em.createQuery("SELECT v FROM Venta v where v.id < :idActual order by v.id desc", Venta.class);
			q.setParameter("idActual", c.getId());
			Venta resultado = (Venta) q.setMaxResults(1).getSingleResult();
			em.close();
			return resultado;
		}
		catch (NoResultException nrEx) {
			return null;
		}
	}

	
	
	/**
	 * 
	 * @param coche
	 * @return
	 */
	public boolean exists(Venta venta) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		
		boolean ok = true;
		try {
			Query q = em.createNativeQuery("SELECT * FROM tutorialjavacoches.venta where id = ?", Venta.class);
			q.setParameter(1, venta.getId());
			venta = (Venta) q.getSingleResult(); 
		}
		catch (NoResultException ex) {
			ok = false;
		}
		em.close();
		return ok;
	}
	
	
	
	public List<Venta> findAll () {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		Query q = em.createQuery("SELECT v FROM Venta v", Venta.class);
		List<Venta> resultado = (List<Venta>) q.getResultList();
		em.close();
		return resultado;
	}
	
	/**
	 * Método que limita a 5 los resultados de una lista
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<Venta> findDe5en5 (int limit, int offset) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		Query q = em.createQuery("SELECT c FROM Venta c", Coche.class);
		q.setMaxResults(limit);
		q.setFirstResult(offset);
		List<Venta> resultado = (List<Venta>) q.getResultList();
		em.close();
		return resultado;
	}
	
	/**
	 * Método que saca un entero con el valor de la cantidad de registros
	 * @return
	 */
	public int numRegistros() {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		Query q = em.createNativeQuery("SELECT count(*) FROM Venta");
		Long cantidad = (Long) q.getSingleResult();
		em.close();
		return cantidad.intValue();
		
	}

	

	
	public static String toString (Venta venta) {
		return venta.getCliente() + " " + venta.getConcesionario() + " " + venta.getCoche() + " " + venta.getFecha() + " " + venta.getPrecioVenta(); 
	}

	

}
