package com.api.rest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "TBENDERECO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EnderecoModel
{
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDENDERECO")
	private Long idEndereco;
	
	@Column(name = "TXLOGRADOURO")
	private String txLogradouro;
	
	@Column(name = "TXNUMERO")
	private String txNumero;
	
	@Column(name = "TXCOMPLEMENTO")
	private String txComplemento;
	
	@Column(name = "TXBAIRRO")
	private String txBairro;
	
	@Column(name = "NRCEP")
	private Long nrCEP;
	
	@Column(name = "IDCIDADE")
	private Long idCidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDCIDADE", insertable = false, updatable = false)
	private CidadeModel cidadeModel;
	
}
