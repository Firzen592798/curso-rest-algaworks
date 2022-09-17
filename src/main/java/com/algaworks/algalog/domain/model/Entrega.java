package com.algaworks.algalog.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.algalog.domain.ValidationGroups;
import com.algaworks.algalog.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(groups = ValidationGroups.ClienteId.class) 
	private Long id;
	
	@ManyToOne //Se não tiver o JoinColumn, por padrão a chave fica cliente_id
	@NotNull
	//Muda o grupo padrão do validation group e assim ser possível que o erro específico da falta do atributo id seja capturado
	//Depois do uso do EntregaInput como entidade de request, não é mais necessário manter
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	@Valid
	private Cliente cliente;
	
	@Embedded //Mantém os dados do destinatário na mesma tabela de entrega lá no banco
	private Destinatario destinatario;
	
	@NotNull
	private BigDecimal taxa;
	
	//O mappedBy coloca o nome da propriedade no relacionamento inverso
	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	 //Essa anotação evita o problema de segurança de permitir que o usuário da API especifique esse atributo 
	//Depois do uso do EntregaInput como entidade de request, não é mais necessário manter esse atributo
	@JsonProperty(access = Access.READ_ONLY)
	private StatusEntrega status;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataPedido;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;

	public Ocorrencia adicionarOcorrencia(String descricao) {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao(descricao);
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencia.setEntrega(this);
		
		this.getOcorrencias().add(ocorrencia);
		
		return ocorrencia;
	}

	public void finalizar() {
		if(!podeSerFinalizada()) {
			throw new NegocioException("Entrega não pode ser finalizada");
		}
		setStatus(StatusEntrega.FINALIZADA);
		setDataFinalizacao(OffsetDateTime.now());
	}
	
	public boolean podeSerFinalizada() {
		return StatusEntrega.PENDENTE.equals(getStatus());
	}
}
