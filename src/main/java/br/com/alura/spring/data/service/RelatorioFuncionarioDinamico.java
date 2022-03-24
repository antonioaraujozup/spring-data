package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {

    private final FuncionarioRepository funcionarioRepository;

    private DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {

        // Nome.
        System.out.println("Digite o nome do funcionário:");
        String nome = scanner.next();
        if(nome.equalsIgnoreCase("NULL")) {
            nome = null;
        }

        // Cpf.
        System.out.println("Digite o cpf do funcionário:");
        String cpf = scanner.next();
        if(cpf.equalsIgnoreCase("NULL")) {
            cpf = null;
        }

        // Salário.
        System.out.println("Digite o salário do funcionário:");
        String salarioFuncionario = scanner.next();
        BigDecimal salario;
        if(salarioFuncionario.equalsIgnoreCase("NULL")) {
            salario = null;
        } else {
            salario = new BigDecimal(salarioFuncionario);
        }

        // Data de contratação.
        System.out.println("Digite a data de contratação do funcionário:");
        String data = scanner.next();
        LocalDate dataContratacao;
        if(data.equalsIgnoreCase("NULL")) {
            dataContratacao = null;
        } else {
            dataContratacao = LocalDate.parse(data, formatador);
        }

        List<Funcionario> funcionarios = funcionarioRepository
                .findAll(
                        Specification.where(SpecificationFuncionario.nome(nome))
                        .or(SpecificationFuncionario.cpf(cpf))
                        .or(SpecificationFuncionario.salario(salario))
                        .or(SpecificationFuncionario.dataContratacao(dataContratacao))
                );
        funcionarios.forEach(System.out::println);

    }

}
