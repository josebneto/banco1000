/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufrn.bti.banco1000.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose de barros
 */

 public class Conta {
    private Cliente cliente;
    private int agencia;
    private int numeroConta;
    private String tipo;
    private double saldo;
    private ArrayList<Movimentacao> movimentacao = new ArrayList<>();

    public Conta(Cliente cliente, int agencia, int numeroConta, String tipo, double saldo) {
        this.cliente = cliente;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.tipo = tipo;
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Movimentacao> getMovimentacao() {
        return movimentacao;
    }

    public int getNumConta() {
        return this.numeroConta;
    }

    public int getAgencia() {
        return this.agencia;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void depositar(double valor) {
        this.saldo += valor;
        this.movimentacao.add(new Movimentacao("DEPOSITO", this.cliente, "Depósito", valor));
    }

    public boolean sacar(double valor) {
        if (this.saldo - valor >= 0) {
            this.saldo -= valor;
            this.movimentacao.add(new Movimentacao("SAQUE", this.cliente, "Saque", valor));
            return true;
        }
        return false;
    }

    public boolean transferir(Conta conta, double valor) {
        if (this.saldo - valor >= 0) {
            this.sacar(valor);
            conta.depositar(valor);
            conta.movimentacao.add(new Movimentacao("TRANSFERENCIA", this.cliente, "Entrada por Transferência", valor));
            this.movimentacao.add(new Movimentacao("TRANSFERENCIA", this.cliente, "Saída por Transferência", valor));
            return true;
        }
        return false;
    }

    public double getSaldo() {
        return this.saldo;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numeroConta=" + numeroConta +
                ", tipo='" + tipo + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}