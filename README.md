# 📚 Java 8 - Streams: Mapeamento, Agrupamento, Particionamento e Paralelismo

Este projeto contém uma série de exercícios práticos com foco no uso da **API de Streams do Java 8**, explorando desde operações básicas até composições mais avançadas de *Collectors*.

---

## 🚀 Tecnologias utilizadas

- Java 8+
- API de Streams
- IntelliJ IDEA

---

## 📌 Objetivo

Praticar e consolidar conceitos de programação funcional em Java, utilizando Streams para manipulação de coleções de forma declarativa, legível e eficiente.

---

## 📂 Estrutura do Projeto

O projeto simula cenários comuns de aplicações reais com as seguintes entidades:

- `Livro`
- `Venda`
- `Funcionario`
- `Filme`
- `Pedido`

Cada domínio foi utilizado para aplicar diferentes operações com Streams.

---

## 🧠 Desafios Implementados

### 📚 Desafio 1 - Catálogo de Livros

#### • Extração de títulos com `map`
![Livros](images/livros.png)
#### • Agrupamento por autor com `groupingBy` + `mapping`
![Livros_Por_Autor](images/livros_por_autor.png)
#### • Particionamento por número de páginas
![Particionando_Páginas](images/particionando_paginas.png)
#### • Soma total de páginas (uso didático de `parallelStream`)
![Soma_De_Páginas](images/quantidade_total_paginas.png)

---

### 🛍️ Desafio 2 - Sistema de Vendas

#### • Média de valores por categoria (`averagingDouble`)
![Média_De_Valores](images/media_por_categoria.png)
#### • Particionamento por valor (> R$500)
![Particionando_Valor_Produto](images/particionando_valor_produtos.png)
#### • Transformação para lista de produtos vendidos
![Mapeando_Produtos](images/mapeando_produtos.png)

---

### 👨‍💼 Desafio 3 - Cadastro de Funcionários

#### • Agrupamento por departamento
- Identificação do funcionário mais velho por departamento:
  - Uso de `maxBy`
  - `Comparator` encadeado com critério de desempate por nome
  - Uso de `collectingAndThen` para remover `Optional`
![Funcionários_Por_Departamento](images/agrupando_mais_velho.png)
#### • Soma das idades com particionamento (> 40 anos)
![Particionando_E_Somando_idades](images/particionado_e_somando_idades.png)

---

### 🎬 Desafio 4 - Plataforma de Filmes

#### • Média de avaliação por gênero
![Média_Avaliação_Por_Gênero](images/media_por_genero.png)
#### • Particionamento por avaliação (>= 3.5)
![Particionamento_Por_Avaliação](images/particionando_filmes_por_avaliacao.png)
#### • Filtro de filmes com alta avaliação (>= 3.7)
![Filtro_de_Filmes_Com_Alta_Avaliação](images/filtrando_e_mapeando_filmes.png)

---

### 🛒 Desafio 5 - E-commerce

#### • Soma total de pedidos (`parallelStream` para fins didáticos)
![Soma_Total_Pedidos](images/soma_total_pedidos.png)
#### • Agrupamento por status
![Agrupando_por_Status](images/pedidos_por_status.png)
#### • Particionamento por valor (> R$1000)
![Particionamento_por_Valor](images/particionando_pedidos.png)
#### • Extração de IDs dos pedidos
![Extração_IDs_Pedidos](images/id's_pedidos.png)

---

## ⚙️ Conceitos Aplicados

- `map`, `filter`
- `mapToInt`, `mapToDouble`
- `collect`
- `Collectors.groupingBy`
- `Collectors.partitioningBy`
- `Collectors.mapping`
- `Collectors.averagingDouble`
- `Collectors.summingInt`
- `Collectors.maxBy`
- `Collectors.collectingAndThen`
- `Comparator.comparing` e `thenComparing`
- `Optional` e `Optional::orElseThrow`
- `parallelStream` (uso controlado)

---

## ✔ Boas práticas aplicadas
- Código organizado por domínio (livros, vendas, etc.)
- Saídas formatadas para melhor leitura
- Nomes de variáveis mais descritivos
- Redução de ambiguidade em critérios de filtragem

---

## ⚠️ Observações
- O uso de parallelStream foi mantido com fins didáticos.
- Em cenários reais, deve ser utilizado apenas com grandes volumes de dados e operações custosas.


---

## 📈 Aprendizados
- Escrita de código mais declarativo com Streams
- Uso de coletores compostos
- Controle fino de ordenação com Comparator
- Tratamento de resultados com Optional
- Organização de dados em estruturas como Map e List
