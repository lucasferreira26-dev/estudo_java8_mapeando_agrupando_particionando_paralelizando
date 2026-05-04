package Aula9_Mapeando_Particionando_Agrupando_Paralelizando_labs;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args){

        // LISTA DE LIVROS
        List<Livro> livros = Arrays.asList(
                new Livro("Dom Casmurro", "Machado de Assis", 192),
                new Livro("A Moreninha", "Joaquim Manuel de Macedo", 144),
                new Livro("Memórias Póstumas de Brás Cubas", "Machado de Assis", 300),
                new Livro("Grande Sertão: Veredas", "Guimarães Rosa", 500),
                new Livro("Viva o Povo Brasileiro", "João Ubaldo Ribeiro", 700)
        );

        System.out.println("📚SESSÃO DE ANÁLISE DA LISTA LIVROS\n");

        // ✅DESAFIO 1: CATÁLOGO DE LIVROS

        // USAR map PARA EXTRAIR APENAS OS TÍTULOS DOS LIVROS
        List<String> titulos = livros.stream()
                .map(Livro::getTitulo)
                .collect(Collectors.toList());

        System.out.println(" <== LIVROS ==>");

        titulos.forEach(System.out::println);

        System.out.println("-----------------------------------------------------------------------------");

        // USAR groupingBy PARA AGRUPAR LIVROS POR AUTOR
        Map<String, List<String>> livrosPorAutor = livros.stream()
                .collect(Collectors.groupingBy(Livro::getAutor,
                        Collectors.mapping(Livro::getTitulo, Collectors.toList())));

        System.out.println(" <== LIVROS POR AUTOR ==>");

        livrosPorAutor.forEach((autor, titulos1) -> {
            System.out.println("Autor: " + autor);
            titulos1.forEach(System.out::println);
            System.out.println("-----------------------------------------------------------------------------");
        });

        // USAR partitioningBy PARA SEPARAR LIVROS COM MAIS DE 300 PÁGINAS DOS QUE TÊM MENOS
        Map<Boolean, List<String>> livros300pags = livros.stream()
                .collect(Collectors.partitioningBy(l -> l.getNumPaginas() > 300,
                        Collectors.mapping(Livro::getTitulo, Collectors.toList())));

        livros300pags.forEach((b, livros1) -> {
            if (b == false){
                System.out.println(" <== LIVROS COM MENOS DE 300 PÁGINAS ==>");
                livros1.forEach(System.out::println);
            } else {
                System.out.println(" <== LIVROS COM MAIS DE 300 PÁGINAS ==>");
                livros1.forEach(System.out::println);
            }
            System.out.println("-----------------------------------------------------------------------------");
        });

        // USAR parallelStream PARA CONTAR O TOTAL DE PÁGINAS DE FORMA PARALELA
        int totalPags = livros.parallelStream()
                .mapToInt(Livro::getNumPaginas)
                .sum();

        System.out.println(" <== TOTAL DE PÁGINAS SOMANDO TODOS OS LIVROS ==>");

        System.out.println(totalPags);

        System.out.println("-----------------------------------------------------------------------------");


        // LISTA DE VENDAS
        List<Venda> vendas = Arrays.asList(
                new Venda("Bola de Futebol", 100, "Esportes"),
                new Venda("Notebook", 2500, "Eletrônicos"),
                new Venda("Camisa", 150, "Moda"),
                new Venda("Sapato", 200, "Moda"),
                new Venda("Televisão", 1500, "Eletrônicos"),
                new Venda("Liquidificador", 100, "Eletrodoméstico")
        );

        System.out.println("\n🛍️SESSÃO DE ANÁLISE DA LISTA VENDAS\n");

        // ✅DESAFIO 2: SISTEMA DE VENDAS

        // USAR O groupingBy PARA AGRUPAR VENDAS POR CATEGORIA E
        // CALCULAR O VALOR MÉDIO POR CATEGORIA USANDO O Collectors.averagingDouble().
        Map<String, Double> mediaDosProdutosPorCategoria = vendas.stream()
                .collect(Collectors.groupingBy(Venda::getCategoria,
                        Collectors.averagingDouble(Venda::getValor)));

        System.out.println(" <== MÉDIA EM VALOR DOS PRODUTOS VENDIDOS POR CATEGORIA ==>");
        mediaDosProdutosPorCategoria.forEach((categoria, media) ->{
            System.out.println("CATEGORIA ANALISADA: " + categoria);
            System.out.println("MÉDIA: R$" + media);
            System.out.println("-----------------------------------------------------------------------------");
        });

        // USAR partitioningBy PARA SEPARAR VENDAS ACIMA DE R$500 DAS ABAIXO E
        // E USAR mapping PARA RETORNAR UMA LISTA DOS PRODUTOS VENDIDOS SEGUNDO ESSE CRITÉRIO
        Map<Boolean, List<String>> vendasAcimaDe500 = vendas.stream()
                .collect(Collectors.partitioningBy(v -> v.getValor() > 500,
                        Collectors.mapping(Venda::getProduto, Collectors.toList())));

        vendasAcimaDe500.forEach((b, produtos) -> {
            if(b == false){
                System.out.println(" <== PRODUTOS COM VALOR MENOR QUE R$500 ==>");
                produtos.forEach(System.out::println);
            } else {
                System.out.println(" <== PRODUTOS COM VALOR MAIOR QUE R$500 ==>");
                produtos.forEach(System.out::println);
            }
            System.out.println("-----------------------------------------------------------------------------");
        });


        // USAR map PARA TRANSFORMAR A LISTA EM APENAS OS NOMES DOS PRODUTOS VENDIDOS
        List<String> produtosVendidos = vendas.stream()
                .map(Venda::getProduto)
                .collect(Collectors.toList());

        System.out.println(" <== TODOS OS PRODUTOS DA LISTA ==>");

        produtosVendidos.forEach(System.out::println);

        System.out.println("-----------------------------------------------------------------------------");


        // LISTA DE FUNCIONÁRIOS
        List<Funcionario> funcionarios = Arrays.asList(
                new Funcionario("Alice", 25, "Departamento de Informática"),
                new Funcionario("Carlos", 45, "Departamento de Robótica"),
                new Funcionario("Aloisio", 41, "Departamento de Robótica"),
                new Funcionario("Armando", 30, "Departamento de Informática"),
                new Funcionario("Enzo", 23, "Departamento de Programação"),
                new Funcionario("Carla", 23, "Departamento de Programação")
        );

        System.out.println("\n👨‍💼SESSÃO DE ANÁLISE DA LISTA FUNCIONÁRIOS\n");

        // ✅DESAFIO 3: CADASTRO DE FUNCIONÁRIOS

        // AGRUPAR FUNCIONÁRIOS POR DEPARTAMENTO USANDO O groupingBy ;
        // DENTRO DE CADA DEPARTAMENTO, ENCONTRAR O FUNCIONÁRIO MAIS VELHO USANDO Collectors.maxBy E;
        // E CASO HAJA FUNCIONÁRIOS NOS MESMO DEPARTAMENTO COM A MESMA IDADE USAR OS NOMES
        // COMO CRITÉRIO DE DESEMPATE (thenComparing())
        Map<String, Funcionario> funcionarioMaisVelho = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getDepartamento,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(
                        Funcionario::getIdade).thenComparing(Funcionario::getNome, Comparator.reverseOrder())),
                                optional -> optional.orElseThrow())));

        System.out.println(" <== FUNCIONÁRIO MAIS VELHO POR DEPARTAMENTO ==>");

        funcionarioMaisVelho.forEach((departamento, funcionario) -> {
            System.out.println("DEPARTAMENTO ANALISADO: " + departamento);
            System.out.println("FUNCIONÁRIO MAIS VELHO: ");
            System.out.println(funcionario);
            System.out.println("-----------------------------------------------------------------------------");
        });

        // USAR partitioningBy PARA SEPARAR OS FUNCIONÁRIOS QUE ACIMA DE QUARENTA ANOS DOS DEMAIS E
        // USAR parallelStream PARA CALCULAR A SOMA DAS IDADES DE FORMA PARALELA
        Map<Boolean, Integer> acimaEaBaixoDe40 = funcionarios.parallelStream()
                .collect(Collectors.partitioningBy(f -> f.getIdade() > 40,
                        Collectors.summingInt(Funcionario::getIdade)));

        acimaEaBaixoDe40.forEach((b, somaDasIdades) ->{
            if (b == false){
                System.out.println(" <== SOMA DAS IDADES DOS FUNCIONÁRIOS MENOR QUE 40 ANOS ==>");
                System.out.println(somaDasIdades);
            } else {
                System.out.println(" <== SOMA DAS IDADES DOS FUNCIONÁRIOS MAIOR QUE 40 ANOS ==>");
                System.out.println(somaDasIdades);
            }
            System.out.println("-----------------------------------------------------------------------------");
        });


        // LISTA DE FILMES
        List<Filme> filmes = Arrays.asList(
                new Filme("O Diabo Veste Prada 2", "Comédia", 3.7),
                new Filme("Velhos Bandidos", "Ação", 3.7),
                new Filme("Um Pai em Apuros", "Comédia", 3.0),
                new Filme("2DIE4: 24 horas no limite", "Ação", 3.0),
                new Filme("Super Mario Galaxy: O Filme", "Animação", 3.6),
                new Filme("Zuzubalândia O Filme", "Animação", 3.0)
        );

        System.out.println("\n🎬SESSÃO DE ANÁLISE DA LISTA FILMES\n");

        // ✅DESAFIO 4: PLATAFORMA DE STREAMING

        // AGRUPAR FILMES POR GÊNERO USANDO groupingBy
        // DENTRO DE CADA GÊNERO CALCULAR A MÉDIA DAS AVALIAÇÕES
        Map<String, Double> mediaAvaliacao = filmes.stream()
                .collect(Collectors.groupingBy(Filme::getGenero,
                        Collectors.averagingDouble(Filme::getAvaliacao)));

        System.out.println(" <== MÉDIA DE AVALIAÇÃO DOS FILMES POR GÊNERO ==>");

        mediaAvaliacao.forEach((genero, media) ->{
            System.out.println("GÊNERO ANALISADO: " + genero);
            System.out.println("Média: " + media);
            System.out.println("-----------------------------------------------------------------------------");
        });

        // USAR partitioningBy PARA SEPARAR FILMES "BEM AVALIADOS" (nota >= 3.5)
        // RETORNAR A LISTA DE VERDADEIRO OU FALSO APENAS COM OS NOMES DOS FILMES
        Map<Boolean, List<String>> bemAvaliadosOuNao = filmes.stream()
                .collect(Collectors.partitioningBy(f -> f.getAvaliacao() >= 3.5,
                        Collectors.mapping(Filme::getTitulo, Collectors.toList())));

        bemAvaliadosOuNao.forEach((b, filmes1) -> {
            if (b == false){
                System.out.println(" <== FILMES COM AVALIAÇÃO MENOR QUE 3.5 ==>");
                filmes1.forEach(System.out::println);
            } else {
                System.out.println(" <== FILMES COM AVALIAÇÃO MAIOR QUE 3.5 ==>");
                filmes1.forEach(System.out::println);
            }
            System.out.println("-----------------------------------------------------------------------------");
        });


        // USAR map PARA EXTRAIR APENAS OS TITULOS DOS FILMES COM NOTA >= 3.7
        List<String> avaliacaoMaiorOuIgual3_7 = filmes.stream()
                .filter(f -> f.getAvaliacao() >= 3.7)
                .map(Filme::getTitulo)
                .collect(Collectors.toList());

        System.out.println(" <== FILMES COM NOTA MAIOR OU IGUAL A 3.7 ==>");
        avaliacaoMaiorOuIgual3_7.forEach(System.out::println);

        System.out.println("-----------------------------------------------------------------------------");

        // LISTA DE PEDIDOS
        List<Pedido> pedidos = Arrays.asList(
                new Pedido(2500, "Pendente"),
                new Pedido(3520, "Pendente"),
                new Pedido(1000, "Pago"),
                new Pedido(500, "Pago"),
                new Pedido(900, "Pendente"),
                new Pedido(1500, "Pago")
        );

        System.out.println("\n🛒SESSÃO DE ANÁLISE DA LISTA PEDIDOS\n");

        // ✅DESAFIO 5: E-COMMERCE COM PARALELISMO

        // USAR parallelStream PARA CALCULAR O VALOR TOTAL DOS PEDIDOS
        double valorTotalPedidos = pedidos.parallelStream()
                .mapToDouble(Pedido::getValor)
                .sum();

        System.out.println(" <== SOMA TOTAL DOS PEDIDOS ==>");

        System.out.println("R$" + valorTotalPedidos);

        System.out.println("-----------------------------------------------------------------------------");

        // USAR groupingBy PARA AGRUPAR OS PEDIDOS PEDIDOS POR STATUS
        Map<String, List<Pedido>> pedidosPorStatus = pedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getStatus));

        System.out.println(" <== AGRUPANDO PEDIDOS POR STATUS ==>");

        pedidosPorStatus.forEach((status, pedidos1) -> {
            System.out.println("STATUS ANALISADO: " + status);
            pedidos1.forEach(System.out::println);
            System.out.println("-----------------------------------------------------------------------------");
        });

        // USAR partitioningBy PARA SEPARAR PEDIDOS ACIMA DE R$1000 DOS MENORES
        Map<Boolean, List<Pedido>> pedidosAcimaDe1000 = pedidos.stream()
                .collect(Collectors.partitioningBy(p -> p.getValor() > 1000));

        pedidosAcimaDe1000.forEach((b, pedidos2) -> {
            if (b == false){
                System.out.println(" <== PEDIDOS COM VALORES MENORES OU IGUAL A R$1000 ==>");
                pedidos2.forEach(System.out::println);
            } else {
                System.out.println(" <== PEDIDOS COM VALORES MAIORES QUE R$1000 ==>");
                pedidos2.forEach(System.out::println);
            }
            System.out.println("-----------------------------------------------------------------------------");
        });

        // USAR map PARA GERAR UMA LISTA APENAS COM OS IDs DOS PEDIDOS
        List<Integer> idsDosPedidos = pedidos.stream()
                .map(Pedido::getId)
                .collect(Collectors.toList());

        System.out.println(" <== IDs DOS PEDIDOS ==>");

        idsDosPedidos.forEach(System.out::println);
    }
}
