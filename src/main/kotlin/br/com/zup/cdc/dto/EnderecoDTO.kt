package br.com.zup.cdc.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class EnderecoDTO(
//    val rua: String,
//    val cidade: String,
//    val estado: String

    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val ibge: String,
    val gia: String,
    val ddd: String,
    val siafi: String
)


