package lab2;

/**
 * A classe RegistroTempoOnline é uma representação da quantidade de tempo
 * online dedicado a disciplina de forma remota, com dados e métodos sobre o
 * registro do tempo online e sobre a meta de tempo esperado a ser atingida.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 *
 */
public class RegistroTempoOnline {
	/**
	 * tempoOnline é um atributo que representa um número inteiro com a quantidade
	 * de tempoOnline dedicado pelo aluno a referida disciplina;
	 */
	private int tempoOnline;
	/**
	 * nomeDisciplina é um atributo que representa uma String com o nome
	 * identificatório da referida disciplina;
	 */
	private String nomeDisciplina;
	/**
	 * tempoOnlineEsperado é um atributo que representa um número inteiro com a meta
	 * de tempo online esperado a ser dedicado pelo aluno naquela referida
	 * disciplina;
	 */
	private int tempoOnlineEsperado;

	/**
	 * O construtor RegistroTempoOnline(String nomeDisciplina, int
	 * tempoOnlineEsperado) constrói a representação da classe, inicializando os
	 * atributos de nomeDisciplina e de tempoOnlineEsperado da classe com os
	 * respectivos valores passados no parâmetro, assim como incializa o atributo
	 * tempoOnline com 0.
	 * 
	 * @param nomeDisciplina      do tipo String que representa o nome da disciplina
	 *                            a ser atribuída.
	 * @param tempoOnlineEsperado do tipo inteiro que representa a quantidade de
	 *                            tempo online esperada para aquela disciplina.
	 */
	public RegistroTempoOnline(String nomeDisciplina, int tempoOnlineEsperado) {
		this.tempoOnline = 0;
		this.nomeDisciplina = nomeDisciplina;
		this.tempoOnlineEsperado = tempoOnlineEsperado;
	}

	/**
	 * O outro construtor RegistroTempoOnline(String nomeDisciplina) constrói a
	 * representação da classe, inicializando os atributos de nomeDisciplina com seu
	 * respectivo nome passado no parâmetro, assim como incializa o atributo
	 * tempoOnline com 0 e define um padrão de 120 horas para o atributo de
	 * tempoOnlineEsperado, quando este não é informado.
	 * 
	 * @param nomeDisciplina do tipo String que representa o nome da disciplina a
	 *                       ser atribuída.
	 */
	public RegistroTempoOnline(String nomeDisciplina) {
		this.tempoOnline = 0;
		this.nomeDisciplina = nomeDisciplina;
		this.tempoOnlineEsperado = 120;
	}

	/**
	 * O método, sem retorno, adicionaTempoOnline(int tempoOnline), adiciona o valor
	 * do tempo online passado no parâmetro para o atributo tempoOnline da classe.
	 * 
	 * @param tempoOnline do tipo inteiro que representa a quantidade de tempo
	 *                    online dedicado pelo aluno para aquela disciplina.
	 */
	public void adicionaTempoOnline(int tempoOnline) {
		this.tempoOnline += tempoOnline;
	}

	/**
	 * O método atingiuMetaTempoOnline(), sem parâmetros, retorna se o tempo online
	 * dedicado pelo aluno atingiu o tempo online esperado para aquela disciplina,
	 * por meio de verdadeiro ou falso. Caso o tempo online dedicado for maior ou
	 * igual ao tempo online esperado, representados pelos atributos tempoOnline e
	 * tempoOnlineEsperado da classe, respectivamente, então retorna verdadeiro, se
	 * não, falso.
	 * 
	 * @return um valor lógico do tipo boolean true (verdadeiro) ou false (falso)
	 *         para a condição da meta de tempo estabelecida para disciplina.
	 */
	public Boolean atingiuMetaTempoOnline() {
		if (this.tempoOnline >= this.tempoOnlineEsperado) {
			return true;
		}
		return false;
	}

	/**
	 * O método toStrig(), sem parâmetros, retorna uma string no seguinte formato
	 * "Nome da Diciplina tempoOnline/tempoONlineEsperado", com os devidos atributos
	 * da classe. Ex.: "Programação 1 12/120".
	 * 
	 * @return uma String com a representação do registro de tempo online do aluno.
	 */
	@Override
	public String toString() {
		return this.nomeDisciplina + " " + this.tempoOnline + "/" + this.tempoOnlineEsperado;
	}
}
