package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private Boolean system;

    private final FuncionarioRepository funcionarioRepository;

    private DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner){

        system = true;

        while(system) {
            System.out.println("Qual relatório você quer executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Buscar funcionário por nome");
            System.out.println("2 - Buscar funcionário por nome, salário maior e data de contratação");
            System.out.println("3 - Buscar funcionário por data de contratação maior");
            System.out.println("4 - Pesquisa funcionários com salário");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    this.buscarPorNome(scanner);
                    break;
                case 2:
                    this.buscarPorNomeSalarioMaiorDataContratacao(scanner);
                    break;
                case 3:
                    this.buscarPorDataContratacaoMaior(scanner);
                    break;
                case 4:
                    this.pesquisaFuncionarioSalario();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    public void buscarPorNome(Scanner scanner) {
        System.out.println("Digite o nome do funcionário");
        String nome = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
        funcionarios.forEach(System.out::println);
    }

    public void buscarPorNomeSalarioMaiorDataContratacao(Scanner scanner) {
        System.out.println("Digite o nome do funcionário");
        String nome = scanner.next();
        System.out.println("Digite o salário do funcionário");
        BigDecimal salario = scanner.nextBigDecimal();
        System.out.println("Digite a data de contratação do funcionário");
        String data = scanner.next();
        LocalDate dataFormatada = LocalDate.parse(data,formatador);
        List<Funcionario> funcionarios = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome,salario,dataFormatada);
        funcionarios.forEach(System.out::println);
    }

    public void buscarPorDataContratacaoMaior(Scanner scanner) {
        System.out.println("Digite a data de contratação");
        String data = scanner.next();
        LocalDate dataFormatada = LocalDate.parse(data, formatador);
        List<Funcionario> funcionarios = funcionarioRepository.findDataContratacaoMaior(dataFormatada);
        funcionarios.forEach(System.out::println);
    }

    public void pesquisaFuncionarioSalario() {
        List<FuncionarioProjecao> funcionarios = funcionarioRepository.findFuncionarioSalario();
        funcionarios.forEach(f -> System.out.println("Funcionário: id =" + f.getId() +
                " | nome = " + f.getNome() + " | salário = " + f.getSalario()));
    }

}
