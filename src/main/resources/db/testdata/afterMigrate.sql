delete from tbcidade;
delete from tbestado;
delete from tbendereco;

insert into tbestado (idestado, txnome, txsigla) values (1, 'MINAS GERAIS', 'MG');
insert into tbestado (idestado, txnome, txsigla) values (2, 'SAO PAULO', 'SP');
insert into tbestado (idestado, txnome, txsigla) values (3, 'CEARA', 'CE');

insert into tbcidade (idcidade, txnome, idestado) values (1, 'UBERLANDIA', 1);
insert into tbcidade (idcidade, txnome, idestado) values (2, 'BELO HORIZONTE', 1);
insert into tbcidade (idcidade, txnome, idestado) values (3, 'SAO PAULO', 2);
insert into tbcidade (idcidade, txnome, idestado) values (4, 'CAMPINAS', 2);
insert into tbcidade (idcidade, txnome, idestado) values (5, 'FORTALEZA', 3);