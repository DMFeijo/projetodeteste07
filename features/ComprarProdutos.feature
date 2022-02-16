#language: pt
#encoding: iso-8859-1

Funcionalidade: Comprar Produtos
Como um usuario da loja de livros
eu quero adiciomar produtos no carrinho de compras
de modo que eu possa realizar a compra dos produtos

Esquema do Cenario: Adicionar produtos no carrinho de compras
Dado acessar a pagina principal da loja de livros
E Seleciona o livro <titulo> para compra
E informar a quantidade desejada <quantidade>
Quando Solicitar realizacao da compra
Então Sistema informa que o livro <titulo> foi adicionado com sucesso

Exemplos:
| titulo               | quantidade |
| "Fortaleza Digital"  |          2 |
| "O Código da Vinci"  |          1 |
| "O Caçador de Pipas" |          3 |