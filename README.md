# Problema da barbearia de Hilzer

Trabalho do curso de ciência da computação da disciplina de processamento paralelo cursado na Universidade Feevale.

Realizado no primeiro semestre de 2023.

---

## Enunciado do trabalho

Problema da barbearia de Hilzer

William Stallings (Stallings, 2012) apresenta uma versão mais complicada do problema da barbearia, que ele atribui a Ralph Hilzer da Universidade da Califórnia. O problema consiste em uma barbearia com três barbeiros e três cadeiras próprias de barbeiros, também existe uma série de lugares para que os clientes possam esperar. Tanto os barbeiros quanto os clientes devem ser implementados como Threads.

Requisitos:

- Três cadeiras;
- Três barbeiros; 
- Uma sala de espera com um sofá de quatro lugares;
- O número total de clientes permitidos na barbearia é 20;
- Nenhum cliente entrará se a capacidade do local estiver satisfeita;
- Se o cliente entrou e tiver lugar no sofá ele se senta, caso contrário ele espera em pé;
- Quando um barbeiro está livre para atender, o cliente que está a mais tempo no sofá é atendido e o que está a mais tempo em pé se senta;
- Qualquer barbeiro pode aceitar pagamento, mas somente um cliente pode pagar por vez, porque só há uma maquina de cartão (POS / TEF);
- Os barbeiros dividem o seu tempo entre cortar cabelo, receber pagamento e dormir enquanto esperam por um cliente.

Referencias: Stallings, W., & Paul, G. K. (2012). Operating systems: internals and design principles (Vol. 9). New York: Pearson.
