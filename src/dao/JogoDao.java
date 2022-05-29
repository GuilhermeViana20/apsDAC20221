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
	
	public static void update(Jogo jogo) {
		EntityManager s = JPAUtil.criarEntityManager();
		s.getTransaction().begin();
		s.merge(jogo);
		s.getTransaction().commit();
		s.close();
	}
	
	public static Jogo getById(Integer id) {
		EntityManager j = JPAUtil.criarEntityManager();
		j.getTransaction().begin();
		Jogo jogo = j.find(Jogo.class, id);
		j.close();
		return jogo;
	}
	
	public static List<Jogo> listar(){
		EntityManager j = JPAUtil.criarEntityManager();
		Query q = j.createQuery("select e from Jogo e");
		List<Jogo> lista = q.getResultList();
		j.close();
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
