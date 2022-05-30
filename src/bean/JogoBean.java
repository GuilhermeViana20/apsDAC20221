package bean;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.JogoDao;
import entidade.Jogo;

@ManagedBean
public class JogoBean {
	
	private Jogo jogo = new Jogo();
	private List<Jogo> lista;
	private int count;

	public String salvar() {
		try {
			jogo.setDataCriacao(new Date());

			for(int i = 1; i <= 10; i++) {
				Random aleatorio = new Random();
				Integer valor = aleatorio.nextInt(30) + 1;
			    Field ageJogo = jogo.getClass().getDeclaredField("v"+i);
			    ageJogo.setAccessible(true);
			    ageJogo.set(jogo, valor);
			}
			
			JogoDao.salvar(jogo);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo cadastrado com sucesso!"));
			jogo = new Jogo();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ops! Não foi possível realizar essa operação."));
		}
		return null;		
	}
	
	public String deletar(Integer id) {
		try {
			JogoDao.deletar(id);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo deletado com sucesso!"));
			jogo = new Jogo();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ops! Não foi possível realizar essa operação."));
		}
		return null;		
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public List<Jogo> getLista() {
		if(lista == null) {
			lista = JogoDao.listar();
		}
		return lista;
	}

	public void setLista(List<Jogo> lista) {
		this.lista = lista;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
