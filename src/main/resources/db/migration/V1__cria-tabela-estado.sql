create table tbestado (
	idestado bigint not null auto_increment,
	txnome varchar(80) not null,
	txsigla varchar(3) not null,
	constraint pk_esd primary key (idestado)
) engine=innodb default charset=utf8;