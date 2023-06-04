create table tbendereco (
	idendereco bigint not null auto_increment,
	txlogradouro varchar(255) not null,
	txnumero varchar(255) not null,
	txcomplemento varchar(255) null,
	txbairro varchar(255) not null,
	nrcep bigint not null,
	idcidade bigint not null,
	constraint pk_end primary key (idendereco),
	constraint fk_end_cdd foreign key (idcidade) references tbcidade(idcidade)
) engine=innodb default charset=utf8;