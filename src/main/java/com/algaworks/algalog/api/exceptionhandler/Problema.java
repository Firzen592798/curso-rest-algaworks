package com.algaworks.algalog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL) //O JSON gerado inclui apenas os campos não nulos, fazendo com que campos seja ignorado caso não precise
@Getter
@Setter
public class Problema {
	
	private Integer status;
	
	private OffsetDateTime dataHora;
	
	private String titulo;
	
	private List<Campo> campos;
	
	@Getter
	@AllArgsConstructor
	public static class Campo{
		private String nome;
		private String mensagem;
	}
	
}
