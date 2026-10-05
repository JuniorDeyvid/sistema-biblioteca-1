public class Livro {
    private String isbn;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private boolean disponivel;

    Livro(String isbnReceb, String tituloReceb, String autorReceb, int anoPubReceb) {
        isbn = isbnReceb;
        titulo = tituloReceb;
        autor = autorReceb;
        anoPublicacao = anoPubReceb;
        disponivel = true;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public boolean setDisponivel(boolean novoStatus) {
        return disponivel = novoStatus;
    }

    public void mostrarLivrosAutor() {
        System.out.println(isbn);
        System.out.println(titulo);
        System.out.println(anoPublicacao);
    }

    public void mostrar() {
        System.out.println("Isbn: " + isbn);
        System.out.println("Titulo: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Ano de publicação: " + anoPublicacao);
        System.out.println("Disponível: " + disponivel);
    }

    public String getAutor() {
        return autor;
    }

}
