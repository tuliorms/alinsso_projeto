package br.com.fatepi.sistemas.dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fatepi.sistemas.entidades.Pessoa;
import br.com.fatepi.sistemas.models.PessoaModel;
import br.com.fatepi.sistemas.uteis.Utils;

public class PessoaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public void salvarNovoRegistro(PessoaModel pessoaModel) {

		EntityManager entityManager = Utils.getEntityManager();

		Pessoa pessoa = new Pessoa();
		pessoa.setDataCadastro(LocalDateTime.now());
		pessoa.setEmail(pessoaModel.getEmail());
		pessoa.setEndereco(pessoaModel.getEndereco());
		pessoa.setNome(pessoaModel.getNome());
		pessoa.setSexo(pessoaModel.getSexo());

		entityManager.persist(pessoa);

	}
	
	public List<PessoaModel> consultar() {
		
		EntityManager entityManager = Utils.getEntityManager();
		
		Query query = entityManager.createNamedQuery("Pessoa.todos");
		
		return convertPessoaToPessoaModel(query.getResultList());		
		
	}
	
	private List<PessoaModel> convertPessoaToPessoaModel(List<Pessoa> lista) {
		
		List<PessoaModel> listaPessoasModel = new ArrayList<PessoaModel>();
		
		for (Pessoa pessoa : lista) {
			PessoaModel pessoaModel = new PessoaModel();
			pessoaModel.setCodigo(pessoa.getCodigo());
			pessoaModel.setDataCadastro(pessoa.getDataCadastro());
			pessoaModel.setEmail(pessoa.getEmail());
			pessoaModel.setEndereco(pessoa.getEndereco());
			pessoaModel.setNome(pessoa.getNome());
			pessoaModel.setSexo(pessoa.getSexo());
			
			listaPessoasModel.add(pessoaModel);
		}
		
		return listaPessoasModel;
	}
	
	private Pessoa getPessoaById(Long codigo){
		 
		EntityManager entityManager = Utils.getEntityManager();
 
		return entityManager.find(Pessoa.class, codigo);
	}
	
	public void alterar(PessoaModel pessoaModel){
		 
		EntityManager entityManager = Utils.getEntityManager();
 
		Pessoa pessoa = this.getPessoaById(pessoaModel.getCodigo());
 
		pessoa.setEmail(pessoaModel.getEmail());
		pessoa.setEndereco(pessoaModel.getEndereco());
		pessoa.setNome(pessoaModel.getNome());
		pessoa.setSexo(pessoaModel.getSexo());
 
		entityManager.merge(pessoa);
	}
	
	public void excluir(Long codigo){
		 
		EntityManager entityManager = Utils.getEntityManager();	
 
		Pessoa pessoa = this.getPessoaById(codigo);
 
		entityManager.remove(pessoa);
	}

}
