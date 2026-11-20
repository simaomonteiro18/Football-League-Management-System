# Football League Management System

Aplicação de consola em Java para gerir uma liga de futebol: regista equipas, jogadores e jogos, calcula a classificação automaticamente e produz estatísticas sobre a competição.

Projeto pessoal desenvolvido para consolidar os fundamentos de Java e Programação Orientada a Objetos, com modelação de domínio feita de raiz.

## Funcionalidades

- **Classificação da liga** ordenada por pontos (vitória = 3, empate = 1, derrota = 0), atualizada automaticamente a cada jogo registado.
- **Melhor marcador** e **melhor assistente** de toda a liga.
- **Listagem de jogadores por posição** (guarda-redes, defesa, médio, avançado).
- **Média de golos por equipa**.
- **Validação de jogos** com exceção própria: recusa jogos entre equipas inexistentes na liga ou de uma equipa contra si mesma.

## Conceitos de Java demonstrados

Este projeto foi desenhado para exercitar, num único sistema coeso:

- **Programação Orientada a Objetos**: encapsulamento e objetos imutáveis (a classe `Player` expõe apenas construtor e getters).
- **Composição** em dois níveis: uma `League` contém equipas; cada `Team` contém jogadores.
- **Enumerações**: `Position` para as posições dos jogadores.
- **Coleções**: `List` para equipas, jogadores e jogos.
- **Programação funcional (Streams e lambdas)**: toda a lógica de consulta é expressa com streams, incluindo `filter`, `map`/`mapToInt`, `flatMap` (para achatar jogadores de todas as equipas numa só stream), `sorted` com `Comparator`, `max` e `average`.
- **Exceções personalizadas**: `InvalidTeamsException` para validação de regras de negócio.
- **Separação de responsabilidades**: a lógica vive nas entidades (a `League` sabe classificar-se e produzir estatísticas); a camada de aplicação apenas orquestra e apresenta.

## Estrutura

```
src/
├── entities/
│   ├── Player.java              // jogador: nome, posição, golos, assistências
│   ├── Team.java                // equipa: nome, jogadores, pontos
│   ├── Match.java               // jogo entre duas equipas, com resultado
│   ├── League.java              // liga: equipas, jogos e toda a lógica de consulta
│   ├── enums/
│   │   └── Position.java        // posições (GK, DF, MF, ATA)
│   └── exceptions/
│       └── InvalidTeamsException.java
└── application/
    └── Program.java             // ponto de entrada, com dados de demonstração
```

## Como correr

Requer Java 25.

Abrir o projeto no IntelliJ IDEA e correr `Program.java`, ou compilar e executar pela linha de comandos:

```bash
javac -d out src/entities/*.java src/entities/enums/*.java src/entities/exceptions/*.java src/application/*.java
java -cp out application.Program
```

## Exemplo de saída

```
=== CLASSIFICAÇÃO ===
1. Sport Lisboa e Benfica - 9 pts
2. Sporting CP - 4 pts
3. FC Porto - 3 pts
4. SC Braga - 1 pts

=== MELHOR MARCADOR ===
Vangelis Pavlidis - 15

=== MÉDIA DE GOLOS POR EQUIPA ===
Sport Lisboa e Benfica : 4.75
...
```

## Possíveis evoluções

- Leitura de equipas e jogos a partir de ficheiro CSV.
- Desempate na classificação por diferença de golos.
- Persistência em base de dados e exposição via API REST (Spring Boot).
