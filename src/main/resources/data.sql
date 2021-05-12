insert into cidadao 
(DTYPE, CPF, CARTAO_SUS, COMORBIDADES, DATA_NASC, EMAIL, NOME, PROFISSAO, SENHA, TELEFONE, CARGO, IS_APROVADO, LOCAL_DE_TRABALHO) 
values ('Admin', '123', null, null, null, null, 'Administrador', null, '123', null, null, null, null);

insert into cidadao 

(DTYPE, CPF, CARTAO_SUS, COMORBIDADES, DATA_NASC, EMAIL, NOME, PROFISSAO, SENHA, TELEFONE, CARGO, IS_APROVADO, LOCAL_DE_TRABALHO) 
values ('Cidadao', '1234', null, null, null, null, 'Kleberson', null, '1234', null, null, null, null);

insert into cidadao 
(DTYPE, CPF, CARTAO_SUS, COMORBIDADES, DATA_NASC, EMAIL, NOME, PROFISSAO, SENHA, TELEFONE, CARGO, IS_APROVADO, LOCAL_DE_TRABALHO) 
values ('Funcionario', '12345', null, null, null, null, 'Forlan', null, '12345', null, null, true, null);

(DTYPE, CPF, CARTAO_SUS, COMORBIDADES, DATA_NASC, EMAIL, NOME, PROFISSAO, SENHA, SITUACAO, TELEFONE, CARGO, IS_APROVADO, LOCAL_DE_TRABALHO) 
values ('Funcionario', '987654', '987654', null, '2000-05-11', 'exemplo@boll.com', 'José Bonifácio', 'Enermeiro', '123', 'NAO_HABILITADO', '99999999', 'Enfermeiro', true, 'PSF-II');

insert into cidadao 
(DTYPE, CPF, CARTAO_SUS, COMORBIDADES, DATA_NASC, EMAIL, NOME, PROFISSAO, SENHA, SITUACAO, TELEFONE, CARGO, IS_APROVADO, LOCAL_DE_TRABALHO) 
values ('Funcionario', '123456', '987654', 'Diabetes', '2000-05-11', 'exemplo@boll.com', 'José Bonifácio', 'Mecânico', '123', 'NAO_HABILITADO', '99999999', 'Enfermeiro', false, 'PSF-IV');

insert into vacina
(DIAS_PARA_SEGUNDA_DOSE, FABRICANTE, IS_DISPONIVEL, NOME, QUANTIDADE_DOSES)
values (30, 'Pfizer', false, 'CV19', 2);

insert into vacina
(DIAS_PARA_SEGUNDA_DOSE, FABRICANTE, IS_DISPONIVEL, NOME, QUANTIDADE_DOSES)
values (30, 'China', false, 'Coronavac', 2);

