package br.com.fatepi.sistemas.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.fatepi.sistemas.dao.PessoaDAO;
import br.com.fatepi.sistemas.entidades.Pessoa;
import br.com.fatepi.sistemas.models.PessoaModel;
import br.com.fatepi.sistemas.uteis.Utils;

@ManagedBean
@ViewScoped
public class PessoaController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	PessoaDAO pessoaDAO;
	PessoaModel pessoa;
	private List<PessoaModel> pessoas;
	
	@PostConstruct
	public void init() {
		
		this.pessoa = new PessoaModel();
		this.pessoaDAO = new PessoaDAO();
		this.pessoas = new ArrayList<PessoaModel>();
	}
	
	
	public void salvarPessoa(){
				 
		this.pessoaDAO.salvarNovoRegistro(this.pessoa);
 
		this.init();
 
		Utils.mensagemInfo("Registro cadastrado com sucesso");
 
	}
	
	public void consultar() {
		
		this.pessoas.clear();
		this.pessoas.addAll(this.pessoaDAO.consultar());
	}
	
	public void editar(PessoaModel pessoaModel) {
		 
		/*PEGA APENAS A PRIMEIRA LETRA DO SEXO PARA SETAR NO CAMPO(M OU F)*/
		pessoaModel.setSexo(pessoaModel.getSexo().substring(0, 1));
 
		this.pessoa = (PessoaModel) pessoaModel;
 
	}
	
	public void alterar(){
		 
		this.pessoaDAO.alterar(this.pessoa);
		this.pessoa = new PessoaModel();
		this.consultar();
	}
	
	public void excluir(PessoaModel pessoaModel){
		 
		//EXCLUI A PESSOA DO BANCO DE DADOS
		this.pessoaDAO.excluir(pessoaModel.getCodigo());
 
		//REMOVENDO A PESSOA DA LISTA
		//ASSIM QUE É A PESSOA É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.pessoas.remove(pessoaModel);
 
	}


	public PessoaModel getPessoa() {
		return pessoa;
	}


	public void setPessoa(PessoaModel pessoa) {
		this.pessoa = pessoa;
	}


	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

}
