package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    private final CrudCargoService crudCargoService;

    private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;

    private DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Boolean system;

    private Boolean unidades;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CrudCargoService crudCargoService, CrudUnidadeTrabalhoService crudUnidadeTrabalhoService) {
        this.funcionarioRepository = funcionarioRepository;
        this.crudCargoService = crudCargoService;
        this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
    }

    public void inicial(Scanner scanner){

        system = true;

        while(system) {
            System.out.println("Qual ação de funcionário você quer executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Excluir");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    this.salvar(scanner);
                    break;
                case 2:
                    this.atualizar(scanner);
                    break;
                case 3:
                    this.visualizar(scanner);
                    break;
                case 4:
                    this.excluir(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }

    }

    public void salvar(Scanner scanner) {
        System.out.println("Nome do funcionário");
        String nome = scanner.next();
        System.out.println("CPF do funcionário");
        String cpf = scanner.next();
        System.out.println("Salário do funcionário");
        BigDecimal salario = scanner.nextBigDecimal();
        System.out.println("Data de contratação do funcionário");
        LocalDate dataContratacao = LocalDate.parse(scanner.next(), formatador);
        System.out.println("Cargo do funcionário");
        Long idCargo = scanner.nextLong();

        List<Long> idsUnidades = new ArrayList<>();

        unidades = true;

        while(unidades) {
            System.out.println("Unidade(s) de trabalho do funcionário");
            System.out.println("0 - Sair");
            System.out.println("1 - Adicionar");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    System.out.println("ID da unidade");
                    Long idUnidade = scanner.nextLong();
                    idsUnidades.add(idUnidade);
                    break;
                default:
                    unidades = false;
                    break;
            }

        }

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(dataContratacao);

        Optional<Cargo> cargo = crudCargoService.retornarPorId(idCargo);
        funcionario.setCargo(cargo.get());

        idsUnidades.forEach(idUnidade -> {
            Optional<UnidadeTrabalho> unidade = crudUnidadeTrabalhoService.retornarPorId(idUnidade);
            funcionario.adicionaUnidadeTrabalho(unidade.get());
        });

        funcionarioRepository.save(funcionario);

        System.out.println("Salvo!");
    }

    public void atualizar(Scanner scanner) {
        System.out.println("ID do funcionário");
        Long id = scanner.nextLong();

        System.out.println("Nome do funcionário");
        String nome = scanner.next();
        System.out.println("CPF do funcionário");
        String cpf = scanner.next();
        System.out.println("Salário do funcionário");
        BigDecimal salario = scanner.nextBigDecimal();
        System.out.println("Data de contratação do funcionário");
        LocalDate dataContratacao = LocalDate.parse(scanner.next(), formatador);
        System.out.println("Cargo do funcionário");
        Long idCargo = scanner.nextLong();

        List<Long> idsUnidades = new ArrayList<>();

        unidades = true;

        while(unidades) {
            System.out.println("Unidade(s) de trabalho do funcionário");
            System.out.println("0 - Sair");
            System.out.println("1 - Adicionar");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    System.out.println("ID da unidade");
                    Long idUnidade = scanner.nextLong();
                    idsUnidades.add(idUnidade);
                    break;
                default:
                    unidades = false;
                    break;
            }

        }

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(dataContratacao);

        Optional<Cargo> cargo = crudCargoService.retornarPorId(idCargo);
        funcionario.setCargo(cargo.get());

        idsUnidades.forEach(idUnidade -> {
            Optional<UnidadeTrabalho> unidade = crudUnidadeTrabalhoService.retornarPorId(idUnidade);
            funcionario.adicionaUnidadeTrabalho(unidade.get());
        });

        funcionarioRepository.save(funcionario);

        System.out.println("Atualizado!");
    }

    public void visualizar(Scanner scanner) {
        System.out.println("Qual página deseja visualizar?");
        Integer pagina = scanner.nextInt();

        PageRequest pageRequest = PageRequest.of(pagina,5, Sort.by(Sort.Direction.ASC, "nome"));
        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageRequest);

        System.out.println(funcionarios);
        System.out.println("Página atual: " + funcionarios.getNumber());
        System.out.println("Total de elementos: " + funcionarios.getTotalElements());
        funcionarios.forEach(funcionario -> System.out.println(funcionario));
    }

    public void excluir(Scanner scanner) {
        System.out.println("ID do funcionário");
        Long id = scanner.nextLong();
        funcionarioRepository.deleteById(id);
        System.out.println("Excluído!");
    }

}
