package com.algaworks.algalog.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {
	private EntregaRepository entregaRepository;
	private ClienteRepository clienteRepository;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = clienteRepository.findById(entrega.getCliente().getId()).orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setCliente(cliente);
		entrega.setDataPedido(OffsetDateTime.now());
		return entregaRepository.save(entrega);
	}
}
