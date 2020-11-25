/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Luis Mendoza
 * Created: 05-may-2020
 */

CREATE TABLE voluntario (
id int PRIMARY key AUTO_INCREMENT,
dni varchar(255),
nombre varchar(255),
sexo varchar(255),
fecha_nacimiento varchar(255),
telefono_1 varchar(255),
telefono_2 varchar(255),
email varchar(255),
direccion_1 varchar(255),
codigo_postal_1 varchar(255),
poblacion_1 varchar(255),
provincia varchar(255),
nacionalidad varchar(255),
estado_civil varchar(255),
estudios varchar(255),
lugar_estudios varchar(255),
observaciones_formacion varchar(800),
idioma_1 varchar(255),
idioma_2 varchar(255),
idioma_3 varchar(255),
nivel_1 varchar(255),
nivel_2 varchar(255),
nivel_3 varchar(255),
informatica varchar(255),
situacion_laboral varchar(255),
carnet varchar(255),
experiencia_laboral varchar(255),
fecha_alta varchar(255),
zona varchar(255),
poblacion_2 varchar(255),
equipo_1 varchar(255),
direccion_2 varchar(255),
codigo_postal_2 varchar(255),
telefono_3 varchar(255),
cargo_1 varchar(255),
programa_1 varchar(255),
programa_2 varchar(255),
cargo_2 varchar(255),
cargo_3 varchar(255),
observaciones_voluntario varchar(800),
consejo_diocesano varchar(255),
ficha_voluntario varchar(255),
delitos_sexuales varchar(255),
publicacion_imagen varchar(255),
acuerdo_colaboracion varchar(255),
antecedentes_penales varchar(255),
autorizacion_paterna varchar(255),
rgpd varchar(255),
envios_email varchar(255),
envios_postales varchar(255),
observaciones_documentacion varchar(800),
baja_caritas varchar(255),
fecha_baja varchar(255),
foto mediumblob,
tiene_foto varchar(255)
);

CREATE TABLE entrevistado (
id int PRIMARY key AUTO_INCREMENT,
dni varchar(255),
nombre varchar(255),
sexo varchar(255),
fecha_nacimiento varchar(255),
telefono1 varchar(255),
telefono2 varchar(255),
email varchar(255),
direccion varchar(255),
codigo_postal varchar(255),
poblacion varchar(255),
provincia varchar(255),
nacionalidad varchar(255),
estado_civil varchar(255),
fecha_entrevista varchar(255),
observaciones varchar(800),
foto mediumblob,
tiene_foto varchar(255),
es_voluntario varchar(255)
);

CREATE TABLE parroquias (
id int PRIMARY key AUTO_INCREMENT,
nombre varchar(255),
parroco varchar(255),
zona varchar(255),
poblacion varchar(255),
direccion varchar(255),
codigo_postal varchar(255),
provincia varchar(255),
carteles varchar(255),
tripticos varchar(255),
sobres varchar(255),
unidades_didacticas varchar(255)
);

CREATE TABLE cargos (
id int PRIMARY key AUTO_INCREMENT,
cargo varchar(255)
);

CREATE TABLE poblaciones (
id int PRIMARY key AUTO_INCREMENT,
nombre varchar(255)
);

CREATE TABLE provincias (
id int PRIMARY key AUTO_INCREMENT,
nombre varchar(255)
);

CREATE TABLE estado_civil (
id int PRIMARY key AUTO_INCREMENT,
estado varchar(255)
);

CREATE TABLE estudios (
id int PRIMARY key AUTO_INCREMENT,
estudios varchar(255)
);

CREATE TABLE idiomas (
id int PRIMARY key AUTO_INCREMENT,
idioma varchar(255)
);

CREATE TABLE nivel (
id int PRIMARY key AUTO_INCREMENT,
nivel varchar(255)
);

CREATE TABLE situacion_laboral (
id int PRIMARY key AUTO_INCREMENT,
situacion varchar(255)
);

CREATE TABLE zonas (
id int PRIMARY key AUTO_INCREMENT,
zona varchar(255)
);

CREATE TABLE equipos (
id int PRIMARY key AUTO_INCREMENT,
nombre varchar(255),
zona varchar(255),
poblacion varchar(255),
direccion varchar(255),
codigo_postal varchar(255),
telefono varchar(255),
provincia varchar(255)
);

CREATE TABLE programas (
id int PRIMARY key AUTO_INCREMENT,
programa varchar(255)
);

CREATE TABLE imagenes (
id int PRIMARY key AUTO_INCREMENT,
nombre varchar(255),
imagen mediumblob
);

