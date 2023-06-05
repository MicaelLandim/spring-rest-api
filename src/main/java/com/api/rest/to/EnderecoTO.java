package com.api.rest.to;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
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
@Table(name = "tbendereco")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EnderecoTO
{
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idendereco")
	private Long idEndereco;
	
	@Column(name = "txlogradouro")
	private String txLogradouro;
	
	@Column(name = "txnumero")
	private String txNumero;
	
	@Column(name = "txcomplemento")
	private String txComplemento;
	
	@Column(name = "txbairro")
	private String txBairro;
	
	@Column(name = "nrcep")
	private Long nrCEP;
	
	@Column(name = "idcidade")
	private Long idCidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcidade", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_END_CDD"))
	private CidadeTO cidadeTO;
	
}
