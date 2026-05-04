package Aula9_Mapeando_Particionando_Agrupando_Paralelizando_labs;

public class Livro {

    private String titulo;
    private String autor;
    private int numPaginas;

    public Livro(String titulo, String autor, int numPaginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.numPaginas = numPaginas;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo + "; Autor: " + autor + "; Número de Páginas: " + numPaginas;
    }
}
