package com.algaworks.algalog.commom;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //Declara que é um componente spring de configuração
public class ModelMapperConfig {
	
	@Bean //Disponibiliza uma instância do ModelMapper para ser injetável como componente spring
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
