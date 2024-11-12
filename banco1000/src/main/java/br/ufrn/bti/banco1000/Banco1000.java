/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.ufrn.bti.banco1000;

import br.ufrn.bti.banco1000.model.SistemaBanco;

import java.util.Scanner;

/**
 *
 * @author jose de barros
 */

 public class Banco1000 {
    public static void main(String[] args) {
        SistemaBanco sistemaBanco = new SistemaBanco();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nBem-vindo ao Banco 1000!");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Login");
            System.out.println("3 - Criar Conta");
            System.out.println("4 - Consultar Saldo");
            System.out.println("5 - Depositar");
            System.out.println("6 - Sacar");
            System.out.println("7 - Transferir");
            System.out.println("8 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            
            if (opcao == 1) {
                // Cadastrar cliente
                System.out.print("Digite o nome: ");
                String nome = scanner.next();
                System.out.print("Digite o CPF: ");
                String cpf = scanner.next();
                System.out.print("Digite a senha: ");
                String senha = scanner.next();
                System.out.print("Digite o email: ");
                String email = scanner.next();
                System.out.print("Digite o telefone: ");
                String telefone = scanner.next();
                
                if (sistemaBanco.cadastrarCliente(nome, cpf, senha, email, telefone)) {
                    System.out.println("Cliente cadastrado com sucesso!");
                } else {
                    System.out.println("Erro: Cliente com esse CPF já cadastrado.");
                }
            } else if (opcao == 2) {
                // Login
                System.out.print("Digite o CPF para login: ");
                String cpfLogin = scanner.next();
                System.out.print("Digite a senha: ");
                String senhaLogin = scanner.next();
                
                if (sistemaBanco.login(cpfLogin, senhaLogin)) {
                    System.out.println("Login realizado com sucesso!");
                } else {
                    System.out.println("Falha no login! CPF ou senha incorretos.");
                }
            } else if (opcao == 3) {
                // Criar conta
                System.out.print("Digite o CPF do cliente para criar a conta: ");
                String cpfConta = scanner.next();
                System.out.print("Digite o número da agência: ");
                int agencia = scanner.nextInt();
                System.out.print("Digite o número da conta: ");
                int numeroConta = scanner.nextInt();
                System.out.print("Digite o tipo de conta (corrente/poupanca): ");
                String tipo = scanner.next();
                System.out.print("Digite o saldo inicial: ");
                double saldoInicial = scanner.nextDouble();
                
                if (sistemaBanco.criarContaParaCliente(cpfConta, agencia, numeroConta, tipo, saldoInicial)) {
                    System.out.println("Conta criada com sucesso!");
                } else {
                    System.out.println("Erro: Cliente não encontrado.");
                }
            } else if (opcao == 4) {
                // Consultar saldo
                System.out.print("Digite o número da conta: ");
                int numeroConta = scanner.nextInt();
                
                double saldo = sistemaBanco.consultarSaldo(numeroConta);
                if (saldo != -1) {
                    System.out.println("Saldo atual: " + saldo);
                } else {
                    System.out.println("Conta não encontrada ou você não está logado.");
                }
            } else if (opcao == 5) {
                // Depositar
                System.out.print("Digite o número da conta para depósito: ");
                int numeroConta = scanner.nextInt();
                System.out.print("Digite o valor a ser depositado: ");
                double valor = scanner.nextDouble();
                
                if (sistemaBanco.depositar(numeroConta, valor)) {
                    System.out.println("Depósito realizado com sucesso!");
                } else {
                    System.out.println("Erro no depósito.");
                }
            } else if (opcao == 6) {
                // Sacar
                System.out.print("Digite o número da conta para saque: ");
                int numeroConta = scanner.nextInt();
                System.out.print("Digite o valor a ser sacado: ");
                double valor = scanner.nextDouble();
                
                if (sistemaBanco.sacar(numeroConta, valor)) {
                    System.out.println("Saque realizado com sucesso!");
                } else {
                    System.out.println("Erro no saque.");
                }
            } else if (opcao == 7) {
                // Transferir
                System.out.print("Digite o número da conta de origem: ");
                int numeroContaOrigem = scanner.nextInt();
                System.out.print("Digite o número da conta de destino: ");
                int numeroContaDestino = scanner.nextInt();
                System.out.print("Digite o valor a ser transferido: ");
                double valor = scanner.nextDouble();
                
                if (sistemaBanco.transferir(numeroContaOrigem, numeroContaDestino, valor)) {
                    System.out.println("Transferência realizada com sucesso!");
                } else {
                    System.out.println("Erro na transferência.");
                }
            } else if (opcao == 8) {
                // Sair
                System.out.println("Saindo do sistema. Até logo!");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }
        
        scanner.close();
    }
}