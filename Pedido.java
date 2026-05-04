package Aula9_Mapeando_Particionando_Agrupando_Paralelizando_labs;

public class Pedido {

    private static int count;

    private int id;
    private double valor;
    private String status;

    public Pedido(double valor, String status) {
        this.id = ++count;
        this.valor = valor;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Id: " + id + "; Valor: R$" + valor + "; Status: " + status;
    }
}
