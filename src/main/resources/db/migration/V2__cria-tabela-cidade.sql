create table tbcidade (
	idcidade bigint not null auto_increment,
	idestado bigint not null,
	txnome varchar(80) not null,
	constraint pk_cdd primary key (idcidade),
	constraint fk_cdd_esd foreign key (idestado) references tbestado(idestado)
) engine=innodb default charset=utf8;