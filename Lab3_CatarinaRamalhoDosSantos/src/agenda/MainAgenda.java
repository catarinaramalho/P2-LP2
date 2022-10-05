package agenda;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular uma agenda de contatos.
 * 
 * @author Catarina Ramalho dos Santos - 121110708
 *
 */
public class MainAgenda {
	/**
	 * Inicializa uma agenda, carrega a agenda inicial com contatos já cadastrados e
	 * interage com o usuário por meio do menu de opções de ações.
	 * 
	 * @param args, um array de strings.
	 */
	public static void main(String[] args) {
		Agenda agenda = new Agenda();

		System.out.println("Carregando agenda inicial");
		try {
			/*
			 * Essa é a maneira de lidar com possíveis erros por falta do arquivo.
			 */
			carregaAgenda("agenda_inicial.csv", agenda);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro lendo arquivo: " + e.getMessage());
		}

		Scanner scanner = new Scanner(System.in);
		String escolha = "";
		while (true) {
			escolha = menu(scanner);
			comando(escolha, agenda, scanner);
		}

	}

	/**
	 * Exibe o menu e captura a escolha do/a usuário/a.
	 * 
	 * @param scanner Para captura da opção do usuário.
	 * @return O comando escolhido.
	 */
	private static String menu(Scanner scanner) {
		System.out.println("\n---\nMENU\n" + "(C)adastrar Contato\n" + "(L)istar Contatos\n" + "(E)xibir Contato\n"
				+ "(F)avoritos\n" + "(A)dicionar Favorito\n" + "(T)ags\n" + "(R)emover Contato\n" + "(S)air\n" + "\n"
				+ "Opção> ");
		return scanner.next().toUpperCase();
	}

	/**
	 * Interpreta a opção escolhida por quem está usando o sistema.
	 * 
	 * @param opcao   Opção digitada.
	 * @param agenda  A agenda que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void comando(String opcao, Agenda agenda, Scanner scanner) {
		switch (opcao) {
		case "C":
			cadastraContato(agenda, scanner);
			break;
		case "L":
			listaContatos(agenda);
			break;
		case "E":
			exibeContato(agenda, scanner);
			break;
		case "F":
			listaFavoritos(agenda);
			break;
		case "A":
			adicionaFavoritos(agenda, scanner);
			break;
		case "T":
			adicionaTag(agenda, scanner);
			break;
		case "R":
			removeContatos(agenda, scanner);
			break;
		case "S":
			sai();
			break;
		default:
			System.out.println("Opção inválida!");
		}
	}

	/**
	 * Imprime lista de contatos da agenda.
	 * 
	 * @param agenda A agenda sendo manipulada.
	 */
	private static void listaContatos(Agenda agenda) {
		System.out.println("\nLista de contatos: ");
		System.out.println(agenda.listaContatos());
	}

	/**
	 * Imprime lista de contatos favoritos da agenda.
	 * 
	 * @param agenda A agenda sendo manipulada.
	 */
	private static void listaFavoritos(Agenda agenda) {
		System.out.println("\nLista de favoritos: ");
		System.out.println(agenda.listaFavoritos());
	}

	/**
	 * Imprime os detalhes de um dos contatos da agenda em uma posição válida. Caso
	 * ele esteja na lista de favoritos, imprime com um coração.
	 * 
	 * @param agenda  A agenda.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void exibeContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nQual contato> ");
		int posicao = scanner.nextInt();
		System.out.println(agenda.exibirContato(posicao));

	}

	/**
	 * Cadastra um contato na agenda através do método de cadastraContato da Agenda,
	 * com os dados informados.
	 * 
	 * @param agenda  A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void cadastraContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nPosição na agenda> ");
		int posicao = scanner.nextInt();
		if (posicao > 100 || posicao < 1) {
			System.out.print("POSIÇÃO INVÁLIDA");
			return;
		}
		scanner.nextLine();
		System.out.print("\nNome> ");
		String nome = scanner.nextLine();
		System.out.print("\nSobrenome> ");
		String sobrenome = scanner.nextLine();
		System.out.print("\nTelefone> ");
		String telefone = scanner.nextLine();
		System.out.print(agenda.cadastraContato(posicao, nome, sobrenome, telefone));
	}

	/**
	 * Remove um ou mais contato da agenda. Se a remoção do contato foi verdadeira,
	 * remove o contato da agenda de favoritos, caso ele também seja favorito. Caso
	 * contrário, é porque a posição de remoção foi com posição inválida e não
	 * remove os demais, retornando o menu imediatamente, conforme especificado na
	 * documentação.
	 * 
	 * @param agenda  A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void removeContatos(Agenda agenda, Scanner scanner) {
		System.out.print("\nContato(s)> ");
		scanner.nextLine();
		String[] posicaoContatos = (scanner.nextLine()).split(" ");
		if (agenda.removeContatos(posicaoContatos) == false) {
			System.out.print("POSIÇÃO INVÁLIDA");
		}
	}

	/**
	 * Adiciona um contato existente em uma posição válida de intervalo [1,100] aos
	 * favoritos de uma agenda em uma posição válida de intervalo [1,10] e que seja
	 * diferente dos contatos já cadastrados na lista de favoritos.
	 * 
	 * @param agenda  A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void adicionaFavoritos(Agenda agenda, Scanner scanner) {
		System.out.print("\nContato> ");
		int contatoPosicao = scanner.nextInt();
		if (contatoPosicao > 100 || contatoPosicao < 1 || agenda.getContato(contatoPosicao) == null) {
			System.out.print("POSIÇÃO INVÁLIDA");
			return;
		}
		System.out.print("\nPosição> ");
		int posicao = scanner.nextInt();
		System.out.print(agenda.adicionarFavorito(posicao, agenda.getContato(contatoPosicao)));
	}

	/**
	 * Adiciona uma tag na posição informada, válida entre o intervalo [1,5], aos
	 * contatos acessados por meio das posições deles informadas.
	 * 
	 * @param agenda  A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void adicionaTag(Agenda agenda, Scanner scanner) {
		System.out.print("\nContato(s)> ");
		scanner.nextLine();
		String[] posicaoContatos = (scanner.nextLine()).split(" ");
		System.out.print("\nTag> ");
		String tag = scanner.nextLine();
		System.out.print("\nPosição tag> ");
		int posicaoTag = scanner.nextInt();
		if (posicaoTag > 5 || posicaoTag < 1) {
			System.out.print("POSIÇÃO INVÁLIDA");
			return;
		}
		agenda.aplicaTag(posicaoContatos, posicaoTag, tag);

	}

	/**
	 * Sai da aplicação.
	 */
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}

	/**
	 * Lê uma agenda de um arquivo csv.
	 * 
	 * @param arquivoContatos O caminho para o arquivo.
	 * @param agenda          A agenda que deve ser populada com os dados.
	 * @throws FileNotFoundException Caso o arquivo não exista.
	 * @throws IOException           Caso o não possa ser lido.
	 */
	private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
		LeitorDeAgenda leitor = new LeitorDeAgenda();
		int carregados = leitor.carregaContatos(arquivoContatos, agenda);
		System.out.println("Carregamos " + carregados + " registros.");
	}
}
