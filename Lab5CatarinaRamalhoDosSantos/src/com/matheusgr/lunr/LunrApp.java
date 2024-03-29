package com.matheusgr.lunr;

import com.matheusgr.apresentacao.ApresentacaoController;
import com.matheusgr.apresentacao.ApresentacaoService;
import com.matheusgr.lunr.busca.BuscaController;
import com.matheusgr.lunr.busca.BuscaService;
import com.matheusgr.lunr.documento.DocumentoController;
import com.matheusgr.lunr.documento.DocumentoService;
import com.matheusgr.similaridade.SimilaridadeController;
import com.matheusgr.similaridade.SimilaridadeService;

/**
 * O Lunr é um sistema de cadastro e busca de documentos, bem como de realização
 * de operações de similaridade entre os documentos cadastrados.
 * 
 * O LunrApp por sua vez é responsável por centralizar todas as inicializações
 * de todos os objetos de controller e serviços existentes.
 * 
 * O Controller é responsável por ser uma interface com o usuário, e os serviços
 * são responsáveis pela lógica de negócio.
 * 
 * Por centralizar a inicialização dos objetos principais de lógica do sistema,
 * é importante que o LunrFacade seja a única fote de acesso as demais
 * operações.
 */
public class LunrApp {

	private DocumentoController documentoController;
	private BuscaController buscaController;
	private SimilaridadeController similaridadeController;
	private ApresentacaoController apresentacaoController;

	/**
	 * Inicializador do LunrApp.
	 * 
	 * Todas as entidades de interação no sistema devem ser obtidas a partir do
	 * LunrApp. Isto garante a correta inicialização das entidades.
	 */
	public LunrApp() {
		var documentoService = new DocumentoService();
		var buscaService = new BuscaService(documentoService);
		var similaridadeService = new SimilaridadeService(documentoService);
		var apresentacaoService = new ApresentacaoService(documentoService);

		this.documentoController = new DocumentoController(documentoService);
		this.buscaController = new BuscaController(buscaService);
		this.similaridadeController = new SimilaridadeController(similaridadeService);
		this.apresentacaoController = new ApresentacaoController(apresentacaoService);
	}

	/**
	 * Obtém o controller de documentos para interação com os usuários.
	 * 
	 * @return DocumentoController para receber requisições do usuário sobre os
	 *         documentos.
	 */
	public DocumentoController getDocumentoController() {
		return this.documentoController;
	}

	/**
	 * Obtém o controller de buscas para interação com os usuários.
	 * 
	 * @return BuscaController para receber requisições do usuário sobre as buscas.
	 */
	public BuscaController getBuscaController() {
		return this.buscaController;
	}

	/**
	 * Obtém o controller de similaridade para interação com os usuários.
	 * 
	 * @return SimilaridadeController para receber requisições do usuário sobre as
	 *         operações de similaridades entre documentos.
	 */
	public SimilaridadeController getSimilaridadeController() {
		return this.similaridadeController;
	}

	/**
	 * Obtém o controller de apresentacao para interação com os usuários.
	 * 
	 * @return ApresentacaoController para receber requisições do usuário sobre as
	 *         operações de apresentação de documentos.
	 */
	public ApresentacaoController getApresentacaoController() {
		return this.apresentacaoController;
	}

}