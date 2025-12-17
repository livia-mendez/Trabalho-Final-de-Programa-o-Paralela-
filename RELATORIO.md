# Relatório Final – Jantar dos Filósofos #

## Introdução ##

O problema do Jantar dos Filósofos é um clássico da computação concorrente, proposto por Edsger Dijkstra, que ilustra desafios fundamentais no controle de concorrência, como deadlock, starvation e sincronização de recursos compartilhados.  
O problema consiste em cinco filósofos sentados ao redor de uma mesa circular, onde cada filósofo alterna entre pensar e comer. Para comer, é necessário adquirir dois recursos compartilhados (garfos), um à esquerda e outro à direita.

Este trabalho analisa três soluções distintas para o problema, utilizando diferentes técnicas de sincronização em Java.

## Metodologia ##

Os testes foram realizados utilizando a linguagem Java, com execução contínua de cada solução por um período mínimo de 2 minutos.  
Durante a execução, foram coletadas as seguintes métricas:

- Número de refeições realizadas por cada filósofo
- Ocorrência (ou não) de deadlock
- Ocorrência (ou não) de starvation
- Comportamento geral do sistema sob concorrência

Cada solução utilizou o mesmo sistema de logging e coleta de estatísticas, garantindo comparabilidade entre os resultados.

## Resultados ##

### Estatísticas de Execução (exemplo) ###

| Filósofo | Tarefa 1 | Tarefa 2 | Tarefa 3 | Monitor |
|--------|----------|----------|----------|---------|
| F1 | 3 | 18 | 22 | 20 |
| F2 | 2 | 17 | 21 | 19 |
| F3 | 1 | 16 | 20 | 20 |
| F4 | 1 | 25 | 23 | 20 |
| F5 | 0 | 15 | 21 | 21 |

> Observação: Na Tarefa 1, o sistema frequentemente entrava em estado de bloqueio total.

*(Gráficos podem ser gerados a partir dessa tabela para visualização comparativa.)*

---

## Análise ##

### Prevenção de Deadlock ###

- **Tarefa 1:** Não previne deadlock. O uso incorreto de `wait()` e `notify()` pode levar à perda de notificações.
- **Tarefa 2:** Previne deadlock através da assimetria na ordem de aquisição dos recursos.
- **Tarefa 3:** Previne deadlock limitando o número de filósofos concorrentes com semáforos.
- **Monitor:** Previne deadlock completamente ao centralizar o controle dos recursos.

### Prevenção de Starvation ###

- **Tarefa 1:** Starvation possível e frequente.
- **Tarefa 2:** Ainda existe possibilidade de starvation.
- **Tarefa 3:** Starvation reduzida, mas não totalmente eliminada.
- **Monitor:** Starvation evitada por meio de mecanismo de fairness (fila).

### Performance / Throughput ###

- **Tarefa 1:** Baixo throughput devido a bloqueios.
- **Tarefa 2:** Melhor desempenho, porém desigual.
- **Tarefa 3:** Alto throughput e bom balanceamento.
- **Monitor:** Desempenho estável, porém com maior custo de sincronização.

### Complexidade de Implementação ###

- **Tarefa 1:** Baixa, porém conceitualmente incorreta.
- **Tarefa 2:** Simples e fácil de entender.
- **Tarefa 3:** Média, exige entendimento de semáforos.
- **Monitor:** Alta, requer controle centralizado e lógica de fairness.

### Uso de Recursos ###

- **Tarefa 1:** Ineficiente.
- **Tarefa 2:** Uso moderado.
- **Tarefa 3:** Bom uso de CPU e controle de concorrência.
- **Monitor:** Maior uso de sincronização, porém previsível.

---

## Conclusão ##

Cada solução é adequada para diferentes cenários:

- **Solução Assimétrica (Tarefa 2):** Ideal para sistemas simples onde starvation não é crítica.
- **Solução com Semáforos (Tarefa 3):** Boa escolha para sistemas com alta concorrência e foco em desempenho.
- **Solução com Monitores:** A mais robusta, indicada para sistemas críticos que exigem justiça, previsibilidade e ausência total de deadlock e starvation.

Apesar da maior complexidade, a solução com monitores apresenta o melhor equilíbrio entre segurança e correção.
