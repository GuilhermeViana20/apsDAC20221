package bean;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.JogoDao;
import entidade.Jogo;


@ManagedBean(name = "jogoBean")
@ViewScoped
public class JogoBean {
	
	private Jogo jogo = new Jogo();
	private List<Jogo> lista;
	private int count;

	public String salvar() {
		try {
			jogo.setDataCriacao(new Date());
			Class<Jogo> clazz = Jogo.class;
			for(var i = 1; i <= 10; i++) {
				Random aleatorio = new Random();
			    int valor = aleatorio.nextInt();
				clazz.getField('v'+Integer.toString(i)).set(valor, valor);
			}
			
			JogoDao.salvar(jogo);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Jogo cadastrado com sucesso!"));
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
