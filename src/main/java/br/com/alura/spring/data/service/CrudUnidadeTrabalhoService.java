package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    private Boolean system;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial(Scanner scanner){

        system = true;

        while(system) {
            System.out.println("Qual ação de unidade você quer executar?");
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
                    this.visualizar();
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
        System.out.println("Descrição da unidade");
        String descricao = scanner.next();
        System.out.println("Endereço da unidade");
        String endereco = scanner.next();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalho.setEndereco(endereco);

        unidadeTrabalhoRepository.save(unidadeTrabalho);

        System.out.println("Salvo!");
    }

    public void atualizar(Scanner scanner) {
        System.out.println("ID da unidade");
        Long id = scanner.nextLong();

        System.out.println("Descrição da unidade");
        String descricao = scanner.next();
        System.out.println("Endereço da unidade");
        String endereco = scanner.next();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalho.setEndereco(endereco);

        unidadeTrabalhoRepository.save(unidadeTrabalho);

        System.out.println("Atualizado!");
    }

    public void visualizar() {
        Iterable<UnidadeTrabalho> unidades = unidadeTrabalhoRepository.findAll();
        unidades.forEach(unidade -> System.out.println(unidade));
    }

    public void excluir(Scanner scanner) {
        System.out.println("ID da unidade");
        Long id = scanner.nextLong();
        unidadeTrabalhoRepository.deleteById(id);
        System.out.println("Excluído!");
    }

    public Optional<UnidadeTrabalho> retornarPorId(Long id) {
        return unidadeTrabalhoRepository.findById(id);
    }

}
