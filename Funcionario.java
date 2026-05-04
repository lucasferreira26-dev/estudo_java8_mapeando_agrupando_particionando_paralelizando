package Aula9_Mapeando_Particionando_Agrupando_Paralelizando_labs;

public class Funcionario {

    private String nome;
    private int idade;
    private String departamento;

    public Funcionario(String nome, int idade, String departamento) {
        this.nome = nome;
        this.idade = idade;
        this.departamento = departamento;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getDepartamento() {
        return departamento;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "; Idade: " + idade;
    }
}
