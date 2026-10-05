public class Usuario {
    private String matricula;
    private String nome;
    private int livrosEmprestados;

    Usuario(String matriculaReceb, String nomeReceb) {
        matricula = matriculaReceb;
        nome = nomeReceb;
        livrosEmprestados = 0;
    }

    public void emprestrarLivro() throws LimiteEmprestimosException {
        if (livrosEmprestados >= 3) {
            throw new LimiteEmprestimosException("Não é possível emprestar mais livros!");
        }
        livrosEmprestados = livrosEmprestados + 1;
    }

    public void devolverLivro() {
        livrosEmprestados = livrosEmprestados - 1;
    }

    public void mostrarUsuario() {
        System.out.println("Nome: " + nome);
        System.out.println("Livros emprestados: " + livrosEmprestados);
    }
}
