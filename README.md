![Projeto Customers](orbitall-payments.png)

By [Stefanini](https://stefanini.com/).

# Desafio Final - Hackaton Orbitall Payments 2022

## Estória da Hackaton Orbitall Payments 2022

**Obs: Paginação começando da página 1**

O cliente X solicitou a Orbitall Payments que necessita ter cartões virtuais de crédito para oferecer ao seus clientes o uso na APP Store. Dado a isto, precisamos que seja criado uma entidade de cartão (card) com os dados pessoais, conforme a diagrama de classe Card abaixo:

```sh
+----------------+
| Card           | -> nome da classe
+----------------+
| id             |
| cardNumber     |
| embossName     |
| customerName   |
| documentNumber | -> atributos da classe
| motherName     |
| address        |
| city           |
+----------------+
| getters        |
| setters        | -> getters / setters da classe
+----------------+
```

Conforme a estória citada acima, temos um recurso chamado cartão (card), então, devemos desenvolver uma REST API deste recurso:

Recurso: Card

```sh
GET     /cards
POST    /cards
PUT     /cards/{id}
DELETE  /cards/{id}
GET     /cards/{id}
GET     /cards/paginationAndSorting ***
```

A Orbitall Payments solicita como premissa as entregas dos itens abaixo:

1. É necessário desenvolver o REST API do recurso cartão (card) tanto em Java como em Node.js.
2. No recurso GET (/cards/{id}), caso não tenha o recurso no banco de dados, devolver a resposta do HTTP Status Code como 404 (NOT_FOUND), caso contrário, devolver 200 (OK).
3. Importante que no último REST API acima (/cards/paginationAndSorting), é necessário implementar o conceito de paginação e ordernação.

Boa sorte!

Orbitall Payments Teams
12/Fevereiro/2022

## **\*DICAS\*\***

### **GIT**

```sh
git clone <branch>
git add .
git commit -m 'Seu comentário'
git push origin <branch>
```

PS: Cuidado com o artefato oculto chamado .git, você tem que basear na sua repositória e não o que foi clonado!!!

### **Paginação & Ordenação**

#### NeBD -> <https://github.com/louischatriot/nedb>

#### Spring Boot -> <https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html>
