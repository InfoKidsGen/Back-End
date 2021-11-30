CREATE TABLE `tb_temas` (
	`id` bigint(5) NOT NULL AUTO_INCREMENT,
	`assunto` varchar(155) NOT NULL,
	`publicoAlvo` varchar(155) NOT NULL,
	`idioma` varchar(155) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `tb_postagem` (
	`id` bigint(5) NOT NULL AUTO_INCREMENT,
	`titulo` varchar(255) NOT NULL,
	`texto` varchar(5000) NOT NULL,
	`data` DATETIME(255) NOT NULL,
	`midia` varchar(255) NOT NULL,
	`tema_id` bigint(5) NOT NULL,
	`usuario_id` bigint(5) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `tb_usuario` (
	`id` bigint(5) NOT NULL,
	`nomeCompleto` varchar(255) NOT NULL,
	`e-mail` varchar(255) NOT NULL,
	`senha` varchar(30) NOT NULL,
	`perfis` varchar(100) NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `tb_postagem` ADD CONSTRAINT `tb_postagem_fk0` FOREIGN KEY (`tema_id`) REFERENCES `tb_temas`(`id`);

ALTER TABLE `tb_postagem` ADD CONSTRAINT `tb_postagem_fk1` FOREIGN KEY (`usuario_id`) REFERENCES `tb_usuario`(`id`);




