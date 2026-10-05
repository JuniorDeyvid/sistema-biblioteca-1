import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        Scanner Leitor = new Scanner(System.in);
        HashMap<String, Livro> livros = new HashMap<>();
        HashMap<String, Usuario> usuarios = new HashMap<>();

        Livro l1 = new Livro("001", "abc", "joao", 89);
        livros.put("001", l1);

        Livro l2 = new Livro("002", "acb2", "jj", 95);
        livros.put("002", l2);

        Usuario u1 = new Usuario("af22", "Carlin");
        usuarios.put("af22", u1);

        while(true) {
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Cadastrar usuário");
            System.out.println("3 - Emprestar um livro");
            System.out.println("4 - Devolver livro");
            System.out.println("5 - Listar livros disponíveis");
            System.out.println("6 - Listar livros emprestados");
            System.out.println("7 - Buscar livro por autor");
            System.out.println("8 - Ver situação de um usuário");
            System.out.println("9 - Sair");

            System.out.println("Qual opção?");
            try {
                int opcao = Leitor.nextInt();
                Leitor.nextLine();

                if (opcao == 1) {
                    System.out.println("ISBN?");
                    String isbn = Leitor.nextLine();

                    System.out.println("Titulo?");
                    String titulo = Leitor.nextLine();

                    System.out.println("Autor?");
                    String autor = Leitor.nextLine();

                    System.out.println("Ano de Publicação?");
                    int anoPublicacao = Leitor.nextInt();
                    Leitor.nextLine();

                    try {
                        if (livros.containsKey(isbn)) {
                            throw new IsbnDuplicadoException("Este Isnb já existe!");
                        }

                        Livro l = new Livro(isbn, titulo, autor, anoPublicacao);
                        livros.put(isbn, l);

                        System.out.println("Livro cadastrado com sucesso!");

                    } catch (IsbnDuplicadoException e) {
                        System.out.println(e.getMessage());
                    }


                } else if (opcao == 2) {

                    System.out.println("Matrícula?");
                    String matricula = Leitor.nextLine();

                    System.out.println("Nome?");
                    String nome = Leitor.nextLine();

                    try {
                        if (usuarios.containsKey(matricula)) {
                            throw new MatriculaDuplicadaException("Essa matrícula já existe!");
                        }

                        Usuario u = new Usuario(matricula, nome);
                        usuarios.put(matricula, u);

                        System.out.println("Usuário cadastrado com sucesso!");

                    } catch (MatriculaDuplicadaException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (opcao == 3) {
                    System.out.println("Isbn?");
                    String isbn = Leitor.nextLine();

                    System.out.println("Matrícula?");
                    String matricula = Leitor.nextLine();

                    Livro l = livros.get(isbn);
                    Usuario u = usuarios.get(matricula);

                    try {
                        if (l == null) {
                            throw new LivroNaoEncontradoException("Livro não encontrado!");
                        }
                        try {
                            if (u == null) {
                                throw new UsuarioNaoEncontradoException("Usuário não encontrado!");
                            }
                            try {
                                if (l.getDisponivel() == false) {
                                    throw new LivroIndisponivelException("Livro indisponível!");
                                }
                                try {
                                    u.emprestrarLivro();
                                    l.setDisponivel(false);
                                    System.out.println("Livro emprestado com sucesso!");

                                } catch (LimiteEmprestimosException e) {
                                    System.out.println(e.getMessage());
                                }
                            } catch (LivroIndisponivelException e) {
                                System.out.println(e.getMessage());
                            }
                        } catch (UsuarioNaoEncontradoException e) {
                            System.out.println(e.getMessage());
                        }
                    } catch (LivroNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (opcao == 4) {
                    System.out.println("Isbn?");
                    String isbn = Leitor.nextLine();

                    System.out.println("Matrícula?");
                    String matricula = Leitor.nextLine();

                    Livro l = livros.get(isbn);
                    Usuario u = usuarios.get(matricula);

                    try {
                        if (l == null) {
                            throw new LivroNaoEncontradoException("Livro não encontrado!");
                        } try {
                            if (u == null) {
                                throw new UsuarioNaoEncontradoException("Usuário não encontrado!");
                            }

                            l.setDisponivel(true);
                            u.devolverLivro();
                            System.out.println("Livro devolvido com sucesso!");

                        } catch (UsuarioNaoEncontradoException e) {
                            System.out.println(e.getMessage());
                        }

                    } catch (LivroNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (opcao == 5) {

                    boolean encontrado = false;
                    for (Livro l : livros.values()) {
                        if (l.getDisponivel() == true) {
                            encontrado = true;
                            l.mostrar();
                            System.out.println(" ");
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Nenhum livro encontrado!");
                    }

                } else if (opcao == 6) {

                    boolean encontrado = false;
                    for (Livro l : livros.values()) {
                        if (l.getDisponivel() == false) {
                            encontrado = true;
                            l.mostrar();
                            System.out.println(" ");
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Nenhum livro encontrado!");
                    }

                } else if (opcao == 7) {
                    System.out.println("Nome do autor?");
                    String autor = Leitor.nextLine();

                    boolean encontrado = false;
                    for(Livro l : livros.values()) {
                        if (l.getAutor().equals(autor)) {
                            encontrado = true;
                            l.mostrarLivrosAutor();
                            System.out.println(" ");
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Autor não encontrado!");
                    }

                } else if (opcao == 8) {

                    System.out.println("Matrícula?");
                    String matricula = Leitor.nextLine();

                    Usuario u = usuarios.get(matricula);

                    try {
                        if (u == null) {
                            throw new UsuarioNaoEncontradoException("Usuário não encontrado!");
                        }
                        u.mostrarUsuario();
                        System.out.println(" ");

                    } catch (UsuarioNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (opcao == 9) {
                    break;

                } else {
                    System.out.println("Digite uma opção válida do menu!");
                }

            } catch (InputMismatchException e) {
                System.out.println("Escolha uma das opções do menu!");
                Leitor.nextLine();
            }
        }
    }
}