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
	private String retorno;

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
	
	public String editar(Integer id) {
        Jogo j = JogoDao.getById(id);
        if(j != null){
        	jogo.setId(j.getId());
			jogo.setDescricao(j.getDescricao());
			jogo.setDataCriacao(j.getDataCriacao());
			jogo.setV1(j.getV1());
			jogo.setV2(j.getV2());
			jogo.setV3(j.getV3());
			jogo.setV4(j.getV4());
			jogo.setV5(j.getV5());
			jogo.setV6(j.getV6());
			jogo.setV7(j.getV7());
			jogo.setV8(j.getV8());
			jogo.setV9(j.getV9());
			jogo.setV10(j.getV10());
        }else{
        	FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro!", "Erro jogo não encontrado."));
        }
        return "atualizar";
	}
	
	public String atualizar() {
		try {
			JogoDao.atualizar(jogo);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "Jogo atualizado com sucesso."));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro!", "Erro ao atualizar o jogo."));
		}
		return null;
	}

	public void pares(Jogo jogo) throws NoSuchFieldException, SecurityException {
		retorno = "";
		if(jogo.getV1() % 2 == 0) {
			retorno = retorno + jogo.getV1().toString() + ",";
		}
		if(jogo.getV2() % 2 == 0) {
			retorno = retorno + jogo.getV2().toString() + ",";
		}
		if(jogo.getV3() % 2 == 0) {
			retorno = retorno + jogo.getV3().toString() + ",";
		}
		if(jogo.getV4() % 2 == 0) {
			retorno = retorno + jogo.getV4().toString() + ",";
		}
		if(jogo.getV5() % 2 == 0) {
			retorno = retorno + jogo.getV5().toString() + ",";
		}
		if(jogo.getV6() % 2 == 0) {
			retorno = retorno + jogo.getV6().toString() + ",";
		}
		if(jogo.getV7() % 2 == 0) {
			retorno = retorno + jogo.getV7().toString() + ",";
		}
		if(jogo.getV8() % 2 == 0) {
			retorno = retorno + jogo.getV8().toString() + ",";
		}
		if(jogo.getV9() % 2 == 0) {
			retorno = retorno + jogo.getV9().toString() + ",";
		}
		if(jogo.getV10() % 2 == 0) {
			retorno = retorno + jogo.getV10().toString() + ",";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Número pares", retorno));
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

	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}
	
	
}
