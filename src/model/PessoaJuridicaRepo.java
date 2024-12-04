/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaJuridicaRepo {
    // Lista privada de pessoas jurídicas
    private ArrayList<PessoaJuridica> lista = new ArrayList<>();

    // Método para inserir uma nova pessoa jurídica
    public void inserir(PessoaJuridica pj) {
        lista.add(pj);
    }

    // Método para alterar uma pessoa jurídica existente (baseado no ID)
    public void alterar(PessoaJuridica pj) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == pj.getId()) {
                lista.set(i, pj);
                return;
            }
        }
    }

    // Método para excluir uma pessoa jurídica pelo ID
    public void excluir(int id) {
        lista.removeIf(pj -> pj.getId() == id);
    }

    // Método para obter uma pessoa jurídica pelo ID
    public PessoaJuridica obter(int id) {
        for (PessoaJuridica pj : lista) {
            if (pj.getId() == id) {
                return pj;
            }
        }
        return null;
    }

    // Método para obter todas as pessoas jurídicas
    public ArrayList<PessoaJuridica> obterTodos() {
        return new ArrayList<>(lista);
    }

    // Método para persistir os dados no disco
    public void persistir(String arquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(lista);
        }
    }

    // Método para recuperar os dados do disco
    public void recuperar(String arquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            lista = (ArrayList<PessoaJuridica>) ois.readObject();
        }
    }
}
