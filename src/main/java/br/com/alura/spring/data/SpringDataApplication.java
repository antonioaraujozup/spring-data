package br.com.alura.spring.data;

import br.com.alura.spring.data.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService crudCargoService;

	private final CrudFuncionarioService crudFuncionarioService;

	private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;

	private final RelatoriosService relatoriosService;

	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

	private Boolean system = true;

	public SpringDataApplication(CrudCargoService crudCargoService, CrudFuncionarioService crudFuncionarioService, CrudUnidadeTrabalhoService crudUnidadeTrabalhoService, RelatoriosService relatoriosService, RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
		this.crudCargoService = crudCargoService;
		this.crudFuncionarioService = crudFuncionarioService;
		this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
		this.relatoriosService = relatoriosService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Qual ação você quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionário");
			System.out.println("3 - Unidade");
			System.out.println("4 - Relatórios");
			System.out.println("5 - Relatório dinâmico");

			int action = scanner.nextInt();

			switch (action) {
				case 1:
					this.crudCargoService.inicial(scanner);
					break;
				case 2:
					this.crudFuncionarioService.inicial(scanner);
					break;
				case 3:
					this.crudUnidadeTrabalhoService.inicial(scanner);
					break;
				case 4:
					this.relatoriosService.inicial(scanner);
					break;
				case 5:
					this.relatorioFuncionarioDinamico.inicial(scanner);
					break;
				default:
					system = false;
					break;
			}

		}

	}
}
