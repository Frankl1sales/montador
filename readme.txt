----------------------

MONTADOR

É o programa do sistema responsável por traduzir o código assembly gerado no 
processo de compilação para uma linguagem de máquina. Traz cada instrução do programa
para sequência de bits que codifica a instrução de máquina.

O processo de montagem recebe como entrada um arquivo texto com o código mfonte do
programa em assembly e gera como saída um arquivo binário [mpodulo objeto], contendo
o código de máquina.

------------------------

ESTRUTURA DE PROGRAMAS ASSEMBLY 

------------------------


MONTADOR BÁSICO DE DOIS PASSOS

Assume que todos os opcodes e operandos ocupam uma palavra de memória 

Formatos de Instruções
1.    "opcode"
2.    "opcode"    "Endereço do operando"
3.    "opcode"    "Endereço do operando_1"  "Endereço do operando_2"




-----------------------

Operation Code Table (OPTAB)
    is used to look up mnemonic operation 
    codes and translate them to their machine language 

    It is usually organized as a hash table, with mnemonic operation code as the key 

Symbol Table
    is used to store values (addres ) assigned to labels
    it is organized as a hash table

Location Counter
    LOCCTR - this is a variable to the beginning to help in the assignment of addres.
    It is initialized to beginning addres specified in the START statement

    thus whenever we reach a label in the source program, 
    the current value of it gives the address to be associated with label


PASS 1
     During Pass 1, labels are entered into SYMTAB as they encountered in the 
     source program, along with their assigned addreessses (from LOCCTR)
PASS 2
    During Pass 2, symbols used as operands are looked upo in SYMTAB to 
    obtain the address to be inserted in the assembled instructions


