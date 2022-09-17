package com.algaworks.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.algaworks.algalog.domain.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(groups = ValidationGroups.ClienteId.class) //Esse grupo custom permite que o id não seja validado no cadastro de cliente e seja validado na solicitação de entrega
	private Long id;
	
	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@Email
	@Size(max = 255)
	@NotBlank
	private String email;
	
	@NotBlank
	@Size(max = 20)
	@Column(name="fone")
	private String telefone;
}

