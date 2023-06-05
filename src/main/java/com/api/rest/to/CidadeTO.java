package com.api.rest.to;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tbcidade")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CidadeTO {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcidade")
	private Long idCidade;

	@Column(name = "txnome", nullable = false)
	private String txNome;

	@Column(name = "idestado")
	private Long idEstado;

	@ManyToOne
	@JoinColumn(name = "idestado", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_CDD_ESD"))
	private EstadoTO estadoTO;

}