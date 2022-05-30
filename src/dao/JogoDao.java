package dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidade.Jogo;
import util.JPAUtil;

public class JogoDao {
	
	public static void salvar(Jogo jogo)
	{
		EntityManager entityManager = JPAUtil.criarEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(jogo);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static void deletar(Integer id)
	{
		EntityManager entityManager = JPAUtil.criarEntityManager();
		entityManager.getTransaction().begin();
		Jogo jogo = entityManager.find(Jogo.class, id);
		entityManager.remove(jogo);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static void atualizar(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(jogo);
		em.getTransaction().commit();
		em.close();
	}
	
	public static Jogo getById(Integer id) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		Jogo jogo = em.find(Jogo.class, id);
		em.close();
		return jogo;
	}
	
	public static List<Jogo> listar(){
		EntityManager em = JPAUtil.criarEntityManager();
		Query query = em.createQuery("SELECT jogo FROM Jogo jogo");
		List<Jogo> lista = query.getResultList();
		em.close();
		return lista;
	}
	
	public static Integer count(){
		EntityManager j = JPAUtil.criarEntityManager();
		Query q = j.createNativeQuery("select count(id) from Jogo");
		int count = ((BigInteger) q.getSingleResult()).intValue();
		j.close();
		return count;
	}

}
