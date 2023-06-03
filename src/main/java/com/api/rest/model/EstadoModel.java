package com.api.rest.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "TBESTADO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EstadoModel
{
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDESTADO")
	private Long idEstado;
	
	@Column(name = "TXNOME", nullable = false)
	private String txNome;
	
	@Column(name = "TXSIGLA")
	private String txSigla;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDESTADO", insertable = false, updatable = false)
	private List<CidadeModel> cidades;
}