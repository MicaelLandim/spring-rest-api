package com.api.rest.to;

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
@Table(name = "tbestado")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EstadoTO
{
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idestado")
	private Long idEstado;
	
	@Column(name = "txnome", nullable = false)
	private String txNome;
	
	@Column(name = "txsigla")
	private String txSigla;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "idestado", insertable = false, updatable = false)
	private List<CidadeTO> cidades;
}