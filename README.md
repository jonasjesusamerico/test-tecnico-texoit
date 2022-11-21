# TESTE TECNICO TEXO IT

## Passos para execução

* Primeiro faça o clone do repositorio
  * https://github.com/jonasjesusamerico/test-tecnico-texoit.git
  * Entre no diretório e execute o seguinte comando
    ```bash
        ./mvnw spring-boot:run
    ```
  * Com o comando anterior ele rodará o sistema
  * Para conferir algumas rotas, está disponivel o swagger: ``` http://localhost:8080/swagger-ui.html ```
  * Para exebição da proposta do teste, está disponivel no link: ``` http://localhost:8080/producers/intervals ```
  * Para executar test com outros arquivos csv, basta mudar o caminho relativo do arquivo no arquivo de configuração: ```src/main/resiyrces/application.properties```
    * Propriedade: ```filename```
    * Execute os paços anterirores para repitir o teste
    * Caso precise que seja limpo a base a cada teste, altera a popriedade ```spring.jpa.hibernate.ddl-auto``` para ```create-drop```
  * Os teste se encontran no direitório ```src/test```
    * Para executar os teste execute o comando: ```./mvnw test```