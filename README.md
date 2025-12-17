# Jantar dos Filósofos – Programação Paralela e Distribuída #
## Visão Geral: ##
Este repositório contém a implementação de diferentes soluções para o clássico problema do Jantar dos Filósofos, desenvolvido como parte da disciplina de Programação Paralela e Distribuída. O objetivo é analisar e comparar abordagens de sincronização, prevenção de deadlock, starvation e fairness utilizando recursos da linguagem Java.
### As soluções incluem ###
- Implementação básica com possibilidade de deadlock
- Prevenção de deadlock por assimetria
- Solução com semáforos
- Solução com monitores e garantia de fairness

## Instruções de Compilação e Execução ##
### Pré-requisitos ###
- Java JDK 17 ou superior
- Terminal ou IDE (VS Code, IntelliJ, Eclipse, etc.)

## Compilação ##
Acesse o diretório do projeto e execute:
- javac *.java

## Execução ##
Após a compilação, execute a classe principal correspondente à solução desejada:
- java Main
Cada execução roda automaticamente por aproximadamente 2 minutos e exibe as estatísticas ao final.

## Execução dos Testes ##
Os testes são realizados por meio da execução contínua das threads durante um período fixo (2 minutos). Durante esse tempo:
- Logs são exibidos no console indicando o estado de cada filósofo
- Estatísticas de execução são coletadas automaticamente
- Ao final, é exibido o número de refeições realizadas por cada filósofo
Não é necessária nenhuma ferramenta externa para executar os testes.

## Relatório Comparativo ##
A análise completa, contendo metodologia, resultados e comparação crítica entre as soluções, está disponível no arquivo:
- RELATORIO.md
