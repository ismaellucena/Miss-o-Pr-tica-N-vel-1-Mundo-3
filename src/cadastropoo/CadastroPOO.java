/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastropoo;

import java.util.Scanner;
import model.*;

public class CadastroPOO {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
        
        while (true) {
            System.out.println("\n==============================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("==============================");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1: // Incluir Pessoa
                    System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
                    String tipoIncluir = scanner.nextLine().toUpperCase();

                    if (tipoIncluir.equals("F")) {
                        System.out.print("Digite o id da pessoa: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consumir quebra de linha
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Idade: ");
                        int idade = scanner.nextInt();
                        scanner.nextLine();

                        repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
                    } else if (tipoIncluir.equals("J")) {
                        System.out.print("Digite o id da pessoa: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("CNPJ: ");
                        String cnpj = scanner.nextLine();

                        repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
                    }
                    break;

                case 2: // Alterar Pessoa
                    System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
                    String tipoAlterar = scanner.nextLine().toUpperCase();

                    if (tipoAlterar.equals("F")) {
                        System.out.print("Digite o id da pessoa: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        PessoaFisica pf = repoFisica.obter(id);
                        if (pf != null) {
                            System.out.println("Dados atuais: ");
                            pf.exibir();

                            System.out.print("Novo nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("Novo CPF: ");
                            String cpf = scanner.nextLine();
                            System.out.print("Nova idade: ");
                            int idade = scanner.nextInt();
                            scanner.nextLine();

                            repoFisica.alterar(id,new PessoaFisica(id, nome, cpf, idade));
                        } else {
                            System.out.println("Pessoa não encontrada.");
                        }
                    } else if (tipoAlterar.equals("J")) {
                        System.out.print("Digite o id da pessoa: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        PessoaJuridica pj = repoJuridica.obter(id);
                        if (pj != null) {
                            System.out.println("Dados atuais: ");
                            pj.exibir();

                            System.out.print("Novo nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("Novo CNPJ: ");
                            String cnpj = scanner.nextLine();

                            repoJuridica.alterar(new PessoaJuridica(id, nome, cnpj));
                        } else {
                            System.out.println("Pessoa não encontrada.");
                        }
                    }
                    break;

                case 3: // Excluir Pessoa
                    System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
                    String tipoExcluir = scanner.nextLine().toUpperCase();

                    System.out.print("Digite o id da pessoa: ");
                    int idExcluir = scanner.nextInt();
                    scanner.nextLine();

                    if (tipoExcluir.equals("F")) {
                        repoFisica.excluir(idExcluir);
                    } else if (tipoExcluir.equals("J")) {
                        repoJuridica.excluir(idExcluir);
                    }
                    break;

                case 4: // Buscar pelo Id
                    System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
                    String tipoBuscar = scanner.nextLine().toUpperCase();

                    System.out.print("Digite o id da pessoa: ");
                    int idBuscar = scanner.nextInt();
                    scanner.nextLine();

                    if (tipoBuscar.equals("F")) {
                        PessoaFisica pf = repoFisica.obter(idBuscar);
                        if (pf != null) {
                            pf.exibir();
                        } else {
                            System.out.println("Pessoa não encontrada.");
                        }
                    } else if (tipoBuscar.equals("J")) {
                        PessoaJuridica pj = repoJuridica.obter(idBuscar);
                        if (pj != null) {
                            pj.exibir();
                        } else {
                            System.out.println("Pessoa não encontrada.");
                        }
                    }
                    break;

                case 5: // Exibir Todos
                    System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
                    String tipoExibir = scanner.nextLine().toUpperCase();

                    if (tipoExibir.equals("F")) {
                        for (PessoaFisica pf : repoFisica.obterTodos()) {
                            pf.exibir();
                            System.out.println();
                        }
                    } else if (tipoExibir.equals("J")) {
                        for (PessoaJuridica pj : repoJuridica.obterTodos()) {
                            pj.exibir();
                            System.out.println();
                        }
                    }
                    break;

                case 6: // Persistir Dados
                    System.out.print("Digite o prefixo do arquivo: ");
                    String prefixoSalvar = scanner.nextLine();

                    try {
                        repoFisica.persistir(prefixoSalvar + ".fisica.bin");
                        repoJuridica.persistir(prefixoSalvar + ".juridica.bin");
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar os dados: " + e.getMessage());
                    }
                    break;

                case 7: // Recuperar Dados
                    System.out.print("Digite o prefixo do arquivo: ");
                    String prefixoRecuperar = scanner.nextLine();

                    try {
                        repoFisica.recuperar(prefixoRecuperar + ".fisica.bin");
                        repoJuridica.recuperar(prefixoRecuperar + ".juridica.bin");
                    } catch (Exception e) {
                        System.out.println("Erro ao recuperar os dados: " + e.getMessage());
                    }
                    break;

                case 0: // Finalizar Programa
                    System.out.println("Encerrando o programa...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
