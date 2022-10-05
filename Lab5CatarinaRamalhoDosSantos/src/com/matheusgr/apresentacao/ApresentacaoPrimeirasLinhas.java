package com.matheusgr.apresentacao;

import com.matheusgr.lunr.documento.Documento;

/**
 * Apresentacão Primeiras Linhas é responsável pela apresentação das 5 primeiras
 * linhas de um documento, implementando a interface Apresentacao.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 */
public class ApresentacaoPrimeirasLinhas implements Apresentacao {
	/**
	 * tamanhoLinhas é o tamanho de linhas a ser apresentado do documento.
	 */
	private int tamanhoLinhas;
	/**
	 * documento é o documento referenciado para a apresentação.
	 */
	private Documento documento;

	/**
	 * Inicializa a apresentação de um documento referenciado para a apresentação
	 * das primeiras linhas, inicializando a variável com um tamanho de 5 linhas.
	 * 
	 * @param documento Documento a ser utilizado.
	 */
	public ApresentacaoPrimeirasLinhas(Documento documento) {
		this.tamanhoLinhas = 5;
		this.documento = documento;
	}

	/**
	 * Sobrescreve o método de apresenta() da interface, operando sobre o original
	 * do documento para a apresentacao das 5 primeiras linhas ou menos, caso o
	 * tamanho do orginal for menor.
	 * 
	 * @return apresentacao em formato textual das 5 primeiras linhas.
	 */
	@Override
	public String apresenta() {
		String[] linhas = this.documento.getOriginal().split("\n");
		if (linhas.length < 5) {
			tamanhoLinhas = linhas.length;
		}
		String apresentacao = "";
		for (int i = 0; i < tamanhoLinhas; i++) {
			apresentacao += linhas[i] + "\n";
		}
		return apresentacao.trim();
	}
}
