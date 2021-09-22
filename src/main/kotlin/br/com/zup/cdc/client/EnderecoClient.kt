package br.com.zup.cdc.client

import br.com.zup.cdc.dto.EnderecoDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("https://viacep.com.br/ws/")
interface EnderecoClient {

    @Get("{cep}/json/")//todo: verificar uma forma de mudar o formato que sera recebido atraves das variaveis de ambiente
    @Consumes(MediaType.APPLICATION_XML)
    fun consultarCEP(@QueryValue cep: String) : HttpResponse<EnderecoDTO>
}