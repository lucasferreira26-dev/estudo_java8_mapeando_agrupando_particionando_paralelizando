package Aula9_Mapeando_Particionando_Agrupando_Paralelizando_labs;

public class Venda {

    private String produto;
    private double valor;
    private String categoria;

    public Venda(String produto, double valor, String categoria) {
        this.produto = produto;
        this.valor = valor;
        this.categoria = categoria;
    }

    public String getProduto() {
        return produto;
    }

    public double getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Produto: " + produto + "; Valor: R$" + valor + "; Categoria: " + categoria;
    }
}
