package lab2;

/**
 * A classe Descanso é uma representação da rotina de descanso alcançado com
 * atividades de lazer de um estudante, com dados e métodos sobre o status das
 * horas e da quantidade de semanas descansadas.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 *
 */
public class Descanso {

	/**
	 * horasDescanso é um atributo que representa um número inteiro com a quantidade
	 * de horas na rotina de descanso;
	 */
	private int horasDescanso;
	/**
	 * numeroSemanas é um atributo que representa um número inteiro com a quantidade
	 * de semanas na rotina de descanso;
	 */
	private int numeroSemanas;

	/**
	 * O construtor Descanso() constrói a representação do descanso do aluno,
	 * inicialiazando os atributos horasDescanso e numeroSemanas com 0.
	 */
	public Descanso() {
		this.horasDescanso = 0;
		this.numeroSemanas = 0;
	}

	/**
	 * O método getStatusGeral(), sem parâmetros, retorna o status geral do aluno. O
	 * aluno recebe status geral de "descansado", caso obter o número de horas de
	 * descanso pelo número de semanas maior ou igual a 26 horas/semana (média
	 * suficiente). Assim como, recebe o status de "cansado", caso sua média esteja
	 * insuficiente ou algum dos seus dados (hora ou descanso) estejam zerados.
	 * 
	 * @return o status geral de descanso do aluno por meio da String statusGeral.
	 */
	public String getStatusGeral() {
		String statusGeral;
		if ((this.horasDescanso == 0) || (this.numeroSemanas == 0)) {
			statusGeral = "cansado";
		} else if ((this.horasDescanso / this.numeroSemanas) >= 26) {
			statusGeral = "descansado";
		} else {
			statusGeral = "cansado";
		}
		return statusGeral;
	}

	/**
	 * O método, sem retorno, defineHorasDescanso(int valor), atribui o valor das
	 * horas de descanso passado no parâmetro para o atributo horasDescanso da
	 * classe.
	 * 
	 * @param valor do tipo inteiro que representa a quantidade de horas de descanso
	 *              a ser atribuída.
	 */
	public void defineHorasDescanso(int valor) {
		this.horasDescanso = valor;
	}

	/**
	 * O método, sem retorno, defineNumeroSemanas(int valor), atribui o valor do
	 * número de semanas passado no parâmetro para o atributo de numeroSemanas da
	 * classe.
	 * 
	 * @param valor do tipo inteiro que representa o número de semanas de descanso a
	 *              ser atribuído.
	 */
	public void defineNumeroSemanas(int valor) {
		this.numeroSemanas = valor;
	}
}
