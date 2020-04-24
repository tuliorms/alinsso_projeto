package br.com.fatepi.sistemas.uteis;

import javax.persistence.Persistence;

public class Conexao {

	public static void main(String[] args) {
		
		Persistence.createEntityManagerFactory("projeto");

	}

}
