  -- ----------------------------------------------------
  -- Autor: Paulo Lima
  -- Funcao: Desenvolvedor, Co-Fundador TeckSoft
  -- Data: 28/08/2014
  -- Todos os Direitos Reservados a TeckSoft
  -- ----------------------------------------------------
  
  create table administrador(
  id int not null auto_increment primary key,
  nome varchar(50),
  login varchar(20),
  senha varchar(20),
  data_hora_cadastro varchar(20)
  )engine = InnoDB DEFAULT CHARSET=utf8;
  -- ----------------------------------------------------
  create table caixa(
  id int not null auto_increment primary key,
  nunero_caixa varchar(4),
  atendente varchar(100),
  data_hora_cadastro varchar(20),
  administrador_id int,
  foreign key(administrador_id) references administrador(id)
  )engine = InnoDB DEFAULT CHARSET=utf8;
  -- ----------------------------------------------------
  create table chamada(
  id int not null auto_increment primary key,
  data varchar(20),
  hora varchar(20),
  caixa_id int,
  foreign key(caixa_id) references caixa(id)
  )engine = InnoDB DEFAULT CHARSET=utf8;
  -- ----------------------------------------------------
  create table preferencia(
  id int not null auto_increment primary key,
  texto varchar(100),
  data_hora_modificacao varchar(20),
  administrador_id int,
  foreign key(administrador_id) references administrador(id)
  )engine = InnoDB DEFAULT CHARSET=utf8;
  -- ----------------------------------------------------
  