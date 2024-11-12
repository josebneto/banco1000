/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufrn.bti.banco1000.model;

import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author jose de barros
 */

 public class SistemaBanco {
    private ArrayList<Cliente> clientes;
    private Cliente clienteLogado;

    public SistemaBanco() {
        this.clientes = new ArrayList<>();
        this.clienteLogado = null;
    }

    public boolean cadastrarCliente(String nome, String cpf, String senha, String email, String telefone) {
        if (buscarClientePorCPF(cpf).isPresent()) {
            return false; // CPF já cadastrado
        }
        Cliente novoCliente = new Cliente(nome, cpf, senha, email, telefone);
        clientes.add(novoCliente);
        return true;
    }

    public boolean criarContaParaCliente(String cpf, int agencia, int numeroConta, String tipo, double saldoInicial) {
        Optional<Cliente> cliente = buscarClientePorCPF(cpf);
        if (cliente.isPresent()) {
            Conta novaConta = new Conta(cliente.get(), agencia, numeroConta, tipo, saldoInicial);
            cliente.get().adicionarConta(novaConta);
            return true;
        }
        return false;
    }

    public Optional<Cliente> buscarClientePorCPF(String cpf) {
        return clientes.stream().filter(c -> c.getCpf().equals(cpf)).findFirst();
    }

    public boolean login(String cpf, String senha) {
        Optional<Cliente> cliente = buscarClientePorCPF(cpf);
        if (cliente.isPresent() && cliente.get().autenticar(senha)) {
            this.clienteLogado = cliente.get();
            return true;
        }
        return false;
    }

    public void logout() {
        this.clienteLogado = null;
    }

    public double consultarSaldo(int numeroConta) {
        if (clienteLogado != null) {
            for (Conta conta : clienteLogado.getContas()) {
                if (conta.getNumConta() == numeroConta) {
                    return conta.getSaldo();
                }
            }
        }
        return -1; // conta não encontrada ou não logado
    }

    public boolean depositar(int numeroConta, double valor) {
        if (clienteLogado != null) {
            for (Conta conta : clienteLogado.getContas()) {
                if (conta.getNumConta() == numeroConta) {
                    conta.depositar(valor);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean sacar(int numeroConta, double valor) {
        if (clienteLogado != null) {
            for (Conta conta : clienteLogado.getContas()) {
                if (conta.getNumConta() == numeroConta) {
                    return conta.sacar(valor);
                }
            }
        }
        return false;
    }

    public boolean transferir(int numeroContaOrigem, int numeroContaDestino, double valor) {
        if (clienteLogado != null) {
            // Encontra a conta de origem
            Conta contaOrigem = clienteLogado.getContas().stream()
                                             .filter(c -> c.getNumConta() == numeroContaOrigem)
                                             .findFirst().orElse(null);
            if (contaOrigem != null) {
                // Encontra a conta de destino
                for (Cliente cliente : clientes) {
                    for (Conta contaDestino : cliente.getContas()) {
                        if (contaDestino.getNumConta() == numeroContaDestino) {
                            // Cria a transferência e executa
                            Transferencia transferencia = new Transferencia(contaOrigem, contaDestino, valor);
                            return transferencia.realizarTransferencia(); // Chama o método que realiza a transferência
                        }
                    }
                }
            }
        }
        return false; // Caso não encontre contas ou algum erro
    }
}