/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufrn.bti.banco1000.model;

/**
 *
 * @author jose de barros
 */

 public class Transferencia {

    private Conta contaOrigem;
    private Conta contaDestino;
    private double valor;

    public Transferencia(Conta contaOrigem, Conta contaDestino, double valor) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
    }

    public boolean realizarTransferencia() {
        if (contaOrigem.sacar(valor)) {  // Tenta sacar da conta de origem
            contaDestino.depositar(valor); // Deposita na conta de destino
            contaDestino.getMovimentacao().add(new Movimentacao("FORMA", contaDestino.getCliente(), "ENTRADA POR TRANSFERENCIA", valor));
            contaOrigem.getMovimentacao().add(new Movimentacao("FORMA", contaOrigem.getCliente(), "SAIDA POR TRANSFERENCIA", valor));
            return true; // Transferência bem-sucedida
        }
        return false; // Transferência falhou
    }
}
