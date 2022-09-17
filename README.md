# Curso Mergulho Spring REST - Algaworks

Projeto do curso da Algaworks explorando possibilidades da API Rest. Criação de endpoints para um mini sistema de entregas, com entidades Entrega, Cliente, Destinatario, Ocorrencia , com CRUDs e algumas ações não CRUD feitas utilizando boas práticas

- Validação de campos com Bean Validations e passar de forma correta o erro para a mensagem da API, com o código de status correto
- Uso de ModelMapper para evitar que todo o objeto vá na requisição ou na resposta da API
- Uso de Lombok pra evitar código boilerplate e deixar mais prático a injeção de dependências via construtor
- Uso do ControllerAdvice para tratar exceções de forma global e customizar o erro de acordo com o tipo de exceção que foi disparado
