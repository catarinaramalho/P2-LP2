package com.matheusgr.lunr.busca;

import java.util.Map;

import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoService;
/**
 * BuscaInterface é a interface que define o método de busca de documentos e descrição de consulta.
 * @author Catarina Ramalho dos Santos - 121110708
 */
public interface BuscaInterface {
	/**
	 * busca é um método responsável pela busca de um mapa de documentos que satisfaz a semântica da busca (mediante termo de busca ou metadados).
	 * @param ds
	 * @return mapa de documentos que satisfaz a busca.
	 */
	public Map<Documento, Integer> busca(DocumentoService ds);
	/**
	 * descreve Consulta é responsável por descrever os dados da consulta.
	 * @return uma matriz com a descrição da consulta
	 */
	public String[][] descreveConsulta();

}
