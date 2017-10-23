-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 03, 2017 at 06:28 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `biblioteca`
--
CREATE DATABASE IF NOT EXISTS `biblioteca` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `biblioteca`;

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `AppartenenzaCollana`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AppartenenzaCollana` (IN `CodiceVolume` VARCHAR(30), `NomeCollana` VARCHAR(40), `NumeroOrdineCollana` INTEGER)  begin

insert into Appartiene values(CodiceVolume,NomeCollana,NumeroOrdineCollana);

end$$

DROP PROCEDURE IF EXISTS `azzeraAvvisi`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `azzeraAvvisi` (IN `tessera` INTEGER)  begin

update utente set TotaleAvvisi = 0 where NumeroTessera = tessera;

end$$

DROP PROCEDURE IF EXISTS `cancellaautore`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `cancellaautore` (IN `codiceFiscale` CHAR(16))  begin
DECLARE special CONDITION FOR SQLSTATE '45005';
if exists (select codicefiscale
			from autore
			where autore.codiceFiscale = codiceFiscale)
		then
delete from autore where autore.CodiceFiscale = codiceFiscale;
else
 SIGNAL 

SQLSTATE '45005'
      SET MESSAGE_TEXT = 'Impossibile cancellare autore.';
      
end if;

end$$

DROP PROCEDURE IF EXISTS `controllaPrestiti`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `controllaPrestiti` ()  begin

update utente set TotaleAvvisi = TotaleAvvisi + 1
where NumeroTessera in  (SELECT ut.NumeroTessera
from (select * from utente) as ut join prestito on ut.NumeroTessera = prestito.NumTessUtente
where (current_date() > prestito.DataRestituzione) and (utente.TotaleAvvisi < 10) );

end$$

DROP PROCEDURE IF EXISTS `creaAutore`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `creaAutore` (IN `CodiceFiscale` CHAR(16), `Nome` VARCHAR(20), `Cognome` VARCHAR(20), `DataNascita` VARCHAR(10), `CittaResidenza` VARCHAR(30))  begin

insert into Autore values(CodiceFiscale,Nome,Cognome,DataNascita,CittaResidenza);

end$$

DROP PROCEDURE IF EXISTS `creaCasaEditrice`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `creaCasaEditrice` (IN `Denominazione` VARCHAR(50), `CittaDiAppartenenza` VARCHAR(30))  begin

insert into casaeditrice values(Denominazione,CittaDiAppartenenza);

end$$

DROP PROCEDURE IF EXISTS `creaCollana`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `creaCollana` (IN `Nome` VARCHAR(20))  begin

insert into Collana values(Nome);

end$$

DROP PROCEDURE IF EXISTS `creaCopia`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `creaCopia` (IN `NumeroRegistrazione` VARCHAR(40), `NumeroScaffale` CHAR(3), `Posizione` INTEGER, `CodiceVolume` VARCHAR(30), `Disponibilita` INT(1))  begin

insert into copia values(NumeroRegistrazione,NumeroScaffale,Posizione,CodiceVolume,Disponibilita);

end$$

DROP PROCEDURE IF EXISTS `creaScritto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `creaScritto` (IN `codAutore` CHAR(16), IN `codVolume` VARCHAR(30))  begin

insert into scritto values(codAutore, codVolume);

end$$

DROP PROCEDURE IF EXISTS `creaUtente`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `creaUtente` (IN `nome` VARCHAR(20), IN `cognome` VARCHAR(20), IN `indirizzo` VARCHAR(30))  begin

insert into utente values(NULL, nome, cognome, 0, indirizzo);

end$$

DROP PROCEDURE IF EXISTS `creaVolume`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `creaVolume` (IN `codice` VARCHAR(30), IN `titolo` VARCHAR(30), IN `edizione` INTEGER, IN `DataPubblicazione` VARCHAR(10), IN `DurataMaxPrestito` INTEGER, IN `Lingua` VARCHAR(20), IN `Denominazione` VARCHAR(50), IN `CittaEditore` VARCHAR(30), IN `nomeA` VARCHAR(20), IN `cognomeA` VARCHAR(20))  begin
declare cf char(16);

start transaction;
set cf = (select autore.codicefiscale from autore where nome = nomeA and cognome = cognomeA);
set foreign_key_checks=0;
insert into Volume values (codice, titolo, edizione, DataPubblicazione, DurataMaxPrestito, Lingua, Denominazione, CittaEditore);
set foreign_key_checks=1;
insert into scritto values (cf,codice);

if( codice = any(select codice from volume) )
then
commit;
else 
rollback;

end if;
end$$

DROP PROCEDURE IF EXISTS `inserisciDizionario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisciDizionario` (IN `codice` VARCHAR(30), IN `titolo` VARCHAR(30), IN `edizione` INTEGER, IN `DataPubblicazione` VARCHAR(10), IN `DurataMaxPrestito` INTEGER, IN `Lingua` VARCHAR(20), IN `Denominazione` VARCHAR(50), IN `CittaEditore` VARCHAR(30), IN `SecondaLingua` VARCHAR(20), IN `nomeA` VARCHAR(20), IN `cognomeA` VARCHAR(20))  begin
declare genere varchar(15);
set genere = 'dizionario';

start transaction;
call creaVolume(codice, titolo, edizione, DataPubblicazione, DurataMaxPrestito, Lingua, Denominazione, CittaEditore, nomeA, cognomeA);
insert into manuale values(codice,genere);
insert into dizionario values(codice,SecondaLingua);

if(codice in (select volume.codice from volume))
then
commit;
else
rollback;

end if;
end$$

DROP PROCEDURE IF EXISTS `inserisciLibro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisciLibro` (IN `codice` VARCHAR(30), IN `titolo` VARCHAR(30), IN `edizione` INTEGER, IN `DataPubblicazione` VARCHAR(10), IN `DurataMaxPrestito` INTEGER, IN `Lingua` VARCHAR(20), IN `Denominazione` VARCHAR(50), IN `CittaEditore` VARCHAR(30), IN `Genere` VARCHAR(20), IN `Tipo` VARCHAR(20), IN `nomeA` VARCHAR(20), IN `cognomeA` VARCHAR(20))  begin

start transaction;
call creaVolume(codice, titolo, edizione, DataPubblicazione, DurataMaxPrestito, Lingua, Denominazione, CittaEditore, nomeA, cognomeA);
insert into libro values(codice,Genere,Tipo);

if(codice in (select volume.codice from volume))
then
commit;
else
rollback;
end if;
end$$

DROP PROCEDURE IF EXISTS `inserisciManuale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisciManuale` (IN `codice` VARCHAR(30), IN `titolo` VARCHAR(30), IN `edizione` INTEGER, IN `DataPubblicazione` VARCHAR(10), IN `DurataMaxPrestito` INTEGER, IN `Lingua` VARCHAR(20), IN `Denominazione` VARCHAR(50), IN `CittaEditore` VARCHAR(30), IN `Categoria` VARCHAR(20), IN `nomeA` VARCHAR(20), IN `cognomeA` VARCHAR(20))  begin

start transaction;
call creaVolume(codice, titolo, edizione, DataPubblicazione, DurataMaxPrestito, Lingua, Denominazione, CittaEditore, nomeA, cognomeA);
insert into manuale values(codice,Categoria);

if(codice in (select volume.codice from volume))
then
commit;
else
rollback;

end if;
end$$

DROP PROCEDURE IF EXISTS `inserisciPeriodico`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisciPeriodico` (IN `codice` VARCHAR(30), IN `titolo` VARCHAR(30), IN `edizione` INTEGER, IN `DataPubblicazione` VARCHAR(10), IN `DurataMaxPrestito` INTEGER, IN `Lingua` VARCHAR(20), IN `Denominazione` VARCHAR(50), IN `CittaEditore` VARCHAR(30), IN `Frequenza` VARCHAR(20), IN `nomeA` VARCHAR(20), IN `cognomeA` VARCHAR(20))  begin

start transaction;
call creaVolume(codice, titolo, edizione, DataPubblicazione, DurataMaxPrestito, Lingua, Denominazione, CittaEditore, nomeA, cognomeA);
insert into periodico values(codice,Frequenza);


if(codice in (select volume.codice from volume))
then
commit;
else
rollback;
end if;
end$$

DROP PROCEDURE IF EXISTS `prestito`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `prestito` (IN `copia` VARCHAR(50), IN `scaffale` CHAR(3), IN `posizione` INTEGER, IN `tessera` INTEGER)  begin
declare maxp integer default 0;
DECLARE special CONDITION FOR SQLSTATE '45005';


set maxp = (select duratamaxprestito
from volume join copia on volume.codice = copia.codiceVolume
where copia.NumeroRegistrazione = copia);

if( 1 = ( select copia.Disponibilità
from copia
where copia.NumeroRegistrazione = copia and copia.NumeroScaffale = scaffale and copia.Posizione = posizione ))
then 
insert into prestito values (tessera, copia, scaffale, posizione, current_date(), adddate(current_date(), maxp));
update copia set Disponibilità = 0 where NumeroRegistrazione = copia;

else
 SIGNAL 

SQLSTATE '45005'
      SET MESSAGE_TEXT = 'Prestito non possibile.';

end if;
end$$

DROP PROCEDURE IF EXISTS `restituzione`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `restituzione` (IN `copia` VARCHAR(50))  begin

delete from prestito where prestito.NumRegCopia = copia;

update copia set Disponibilità = 1 where copia.NumeroRegistrazione = copia;

end$$

DROP PROCEDURE IF EXISTS `ricercaAutore`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ricercaAutore` (IN `nome` VARCHAR(40), IN `cognome` VARCHAR(40))  begin
select autore.nome, autore.cognome, scritto.CodVolume
from autore join scritto on autore.CodiceFiscale = scritto.CodAutore
where autore.nome=nome and autore.cognome=cognome;
end$$

DROP PROCEDURE IF EXISTS `ricercaCopia`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ricercaCopia` (IN `volume` VARCHAR(30))  begin
select *
from copia
where copia.CodiceVolume  = volume; 

end$$

DROP PROCEDURE IF EXISTS `ricercaDizionario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ricercaDizionario` (IN `titolo` VARCHAR(50))  begin
select volume.*, dizionario.SecondaLingua
from dizionario join volume on volume.codice = dizionario.codvolume
where volume.titolo= titolo;
end$$

DROP PROCEDURE IF EXISTS `ricercaEditore`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ricercaEditore` (IN `nomeE` VARCHAR(20))  select *
from volume
where DenominazioneEditore = nomeE$$

DROP PROCEDURE IF EXISTS `ricercaGenere`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ricercaGenere` (IN `tipo` VARCHAR(30))  begin
select volume.*
from volume join libro on volume.Codice = libro.CodVolume
where libro.tipo = tipo;

end$$

DROP PROCEDURE IF EXISTS `ricercaLibro`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ricercaLibro` (IN `titolo` VARCHAR(40))  begin
select volume.*, libro.tipo, libro.genere
from libro join volume on volume.codice = libro.codvolume
where volume.titolo= titolo;
end$$

DROP PROCEDURE IF EXISTS `ricercaManuale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ricercaManuale` (IN `titolo` VARCHAR(40))  begin
select volume.*, manuale.categoria
from manuale join volume on volume.codice = manuale.codvolume
where volume.titolo= titolo;
end$$

DROP PROCEDURE IF EXISTS `ricercaPeriodico`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ricercaPeriodico` (IN `titolo` VARCHAR(40))  begin
select volume.*, periodico.frequenza
from periodico join volume on volume.codice = periodico.codvolume
where volume.titolo= titolo;
end$$

DROP PROCEDURE IF EXISTS `ricercaPrestitiUtente`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ricercaPrestitiUtente` (IN `numeroTessera` INTEGER)  begin


select prestito.* 
from copia join prestito on 

numeroRegistrazione = numRegCopia and numeroScaffale = numScafCopia and posizione = posCopia
		

where prestito.numTessUtente = numeroTessera;
end$$

DROP PROCEDURE IF EXISTS `VisualizzaUtenti`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `VisualizzaUtenti` (IN `numero` INTEGER)  begin
select * from utente
where utente.NumeroTessera= numero;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `appartiene`
--

DROP TABLE IF EXISTS `appartiene`;
CREATE TABLE `appartiene` (
  `CodVolume` varchar(30) NOT NULL,
  `NomeCollana` varchar(40) DEFAULT NULL,
  `NumeroOrdineCollana` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `appartiene`
--

INSERT INTO `appartiene` (`CodVolume`, `NomeCollana`, `NumeroOrdineCollana`) VALUES
('9788845210273', 'Il Signore Degli Anelli', 1);

-- --------------------------------------------------------

--
-- Table structure for table `autore`
--

DROP TABLE IF EXISTS `autore`;
CREATE TABLE `autore` (
  `CodiceFiscale` char(16) NOT NULL,
  `Nome` varchar(20) NOT NULL,
  `Cognome` varchar(20) NOT NULL,
  `DataNascita` varchar(10) DEFAULT NULL,
  `CittaResidenza` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `autore`
--

INSERT INTO `autore` (`CodiceFiscale`, `Nome`, `Cognome`, `DataNascita`, `CittaResidenza`) VALUES
('BKKGBR62D28Z126S', 'Gerbrand', 'Bakker', '1962-04-28', 'Ibiza\r'),
('RMSGRD65A19K303Y', 'Ramsay', 'Gordon', '1965-01-19', 'Panama\r'),
('SCHCRL22S26Z404B', 'Charles Monroe', 'Schulz', '1922-11-26', 'Ignoto\r'),
('TLKJNR92HGMNHJMX', 'John Ronald Reuel', 'Tolkien', '1892-01-03', 'Boston\r'),
('VNNMRC43C03D612J', 'Marco', 'Vannini', '1943-03-03', 'Battipaglia\r'),
('ZRLGRG67D07H707M', 'Giorgio', 'Zarrelli', '1967-04-07', 'Castelmaggiore\r');

-- --------------------------------------------------------

--
-- Table structure for table `casaeditrice`
--

DROP TABLE IF EXISTS `casaeditrice`;
CREATE TABLE `casaeditrice` (
  `Denominazione` varchar(50) NOT NULL DEFAULT '',
  `CittaDiAppartenenza` varchar(30) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casaeditrice`
--

INSERT INTO `casaeditrice` (`Denominazione`, `CittaDiAppartenenza`) VALUES
('Baldini&Castoldi', 'Milano\r'),
('Einaudi', 'Torino\r'),
('Il mattino', 'Milano\r'),
('La stampa', 'Torino\r'),
('Mondadori', 'Milano\r'),
('Treccani', 'Parma\r'),
('Zanichelli', 'Torino');

-- --------------------------------------------------------

--
-- Table structure for table `collana`
--

DROP TABLE IF EXISTS `collana`;
CREATE TABLE `collana` (
  `NomeCollana` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `collana`
--

INSERT INTO `collana` (`NomeCollana`) VALUES
('Hunger Games\r'),
('Il Signore Degli Anelli\r');

-- --------------------------------------------------------

--
-- Table structure for table `copia`
--

DROP TABLE IF EXISTS `copia`;
CREATE TABLE `copia` (
  `NumeroRegistrazione` varchar(40) NOT NULL DEFAULT '',
  `NumeroScaffale` char(3) NOT NULL DEFAULT '',
  `Posizione` int(11) NOT NULL DEFAULT '0',
  `CodiceVolume` varchar(30) NOT NULL,
  `Disponibilità` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `copia`
--

INSERT INTO `copia` (`NumeroRegistrazione`, `NumeroScaffale`, `Posizione`, `CodiceVolume`, `Disponibilità`) VALUES
('139788804655169-1', '3A', 1, '139788804655169', 0),
('139788804655169-2', '3A', 2, '139788804655169', 0),
('139788804655169-3', '3A', 3, '139788804655169', 1),
('139788806220396-1', '1A', 8, '139788806220396', 1),
('139788806220396-2', '1A', 9, '139788806220396', 0),
('139788806220396-3', '1A', 10, '139788806220396', 1),
('139788868527938-1', '2B', 1, '139788868527938', 0),
('139788868527938-2', '2B', 2, '139788868527938', 1),
('139788868527938-3', '2B', 3, '139788868527938', 0);

--
-- Triggers `copia`
--
DROP TRIGGER IF EXISTS `limitaPeriodico`;
DELIMITER $$
CREATE TRIGGER `limitaPeriodico` BEFORE INSERT ON `copia` FOR EACH ROW begin
DECLARE 

speciale CONDITION FOR SQLSTATE '45000';
DECLARE special CONDITION FOR SQLSTATE '45005';

if new.CodiceVolume in (select codVolume
				

from copia join periodico on copia.codiceVolume = periodico.codVolume
                )
then 
SIGNAL 

SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Non posso inserire! Periodico già esistente.';

end if;

if((new.NumeroScaffale in( select NumeroScaffale from copia) ) 

and (new.Posizione in (select Posizione from copia) ))
then
SIGNAL 

SQLSTATE '45005'
      SET MESSAGE_TEXT = "Questa posizione e' gia occupata!";
end if;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `dizionario`
--

DROP TABLE IF EXISTS `dizionario`;
CREATE TABLE `dizionario` (
  `CodVolume` varchar(30) NOT NULL,
  `SecondaLingua` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dizionario`
--

INSERT INTO `dizionario` (`CodVolume`, `SecondaLingua`) VALUES
('1234567890', 'Inglese'),
('8563408981', 'Spagnolo');

--
-- Triggers `dizionario`
--
DROP TRIGGER IF EXISTS `controllaSeDizionario`;
DELIMITER $$
CREATE TRIGGER `controllaSeDizionario` BEFORE INSERT ON `dizionario` FOR EACH ROW begin
DECLARE 

speciale CONDITION FOR SQLSTATE '45007';

if(new.CodVolume in(select CodVolume from libro) or (new.CodVolume in (select CodVolume from periodico)))
then 
SIGNAL 

SQLSTATE '45007'
      SET MESSAGE_TEXT = 'Non posso inserire! Il volume già è assegnato ad un'' altra categoria.';
end if;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
CREATE TABLE `libro` (
  `CodVolume` varchar(30) NOT NULL,
  `Genere` varchar(20) NOT NULL,
  `Tipo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `libro`
--

INSERT INTO `libro` (`CodVolume`, `Genere`, `Tipo`) VALUES
('139788804655169', 'Classico', 'Letterario'),
('139788806220396', 'Giallo', 'Letterario\r'),
('139788868527938', 'Medicina', 'Scientifico');

--
-- Triggers `libro`
--
DROP TRIGGER IF EXISTS `controllaSeLibro`;
DELIMITER $$
CREATE TRIGGER `controllaSeLibro` BEFORE INSERT ON `libro` FOR EACH ROW begin
DECLARE 

speciale CONDITION FOR SQLSTATE '45007';

if(new.CodVolume in (select CodVolume from manuale) or (new.CodVolume in(select CodVolume from dizionario) or (new.CodVolume in (select CodVolume from periodico))))
then 

SIGNAL 

SQLSTATE '45007'
      SET MESSAGE_TEXT = 'Non posso inserire! Il volume già è assegnato ad un'' altra categoria.';
end if;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `manuale`
--

DROP TABLE IF EXISTS `manuale`;
CREATE TABLE `manuale` (
  `CodVolume` varchar(30) NOT NULL,
  `Categoria` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `manuale`
--

INSERT INTO `manuale` (`CodVolume`, `Categoria`) VALUES
('1234567890', 'dizionario'),
('3435789640', 'Cucina\r'),
('5767890317', 'Utente\r'),
('8563408981', 'dizionario');

--
-- Triggers `manuale`
--
DROP TRIGGER IF EXISTS `controllaSeManuale`;
DELIMITER $$
CREATE TRIGGER `controllaSeManuale` BEFORE INSERT ON `manuale` FOR EACH ROW begin
DECLARE 

speciale CONDITION FOR SQLSTATE '45007';

if(new.CodVolume in (select CodVolume from libro) or (new.CodVolume in (select CodVolume from periodico)))
then 
SIGNAL 

SQLSTATE '45007'
      SET MESSAGE_TEXT = 'Non posso inserire! Il volume già è assegnato ad un'' altra categoria.';
end if;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `periodico`
--

DROP TABLE IF EXISTS `periodico`;
CREATE TABLE `periodico` (
  `CodVolume` varchar(30) NOT NULL,
  `Frequenza` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `periodico`
--

INSERT INTO `periodico` (`CodVolume`, `Frequenza`) VALUES
('5680234871', 'Giornaliero\r'),
('7829108476', 'Giornaliero');

--
-- Triggers `periodico`
--
DROP TRIGGER IF EXISTS `controllaSePeriodico`;
DELIMITER $$
CREATE TRIGGER `controllaSePeriodico` BEFORE INSERT ON `periodico` FOR EACH ROW begin
DECLARE 

speciale CONDITION FOR SQLSTATE '45007';

if(new.CodVolume in (select CodVolume from manuale) or (new.CodVolume in(select CodVolume from libro) or (new.CodVolume in (select CodVolume from dizionario))))
then 
SIGNAL 

SQLSTATE '45007'
      SET MESSAGE_TEXT = 'Non posso inserire! Il volume già è assegnato ad un'' altra categoria.';
end if;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `prestitiattivi`
--
DROP VIEW IF EXISTS `prestitiattivi`;
CREATE TABLE `prestitiattivi` (
`NumeroCopia` varchar(40)
,`CodiceVolume` varchar(30)
,`TesseraUtente` int(11)
);

-- --------------------------------------------------------

--
-- Table structure for table `prestito`
--

DROP TABLE IF EXISTS `prestito`;
CREATE TABLE `prestito` (
  `NumTessUtente` int(11) NOT NULL DEFAULT '0',
  `NumRegCopia` varchar(40) NOT NULL DEFAULT '',
  `NumScafCopia` char(3) NOT NULL DEFAULT '',
  `PosCopia` int(11) NOT NULL DEFAULT '0',
  `DataPrestito` date NOT NULL,
  `DataRestituzione` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `prestito`
--

INSERT INTO `prestito` (`NumTessUtente`, `NumRegCopia`, `NumScafCopia`, `PosCopia`, `DataPrestito`, `DataRestituzione`) VALUES
(1, '139788804655169-1', '3A', 1, '2015-06-29', '2015-07-03'),
(2, '139788868527938-1', '2B', 1, '2015-06-20', '2015-07-15'),
(3, '139788804655169-2', '3A', 2, '2015-06-30', '2015-07-15'),
(3, '139788868527938-3', '2B', 3, '2015-06-25', '2015-07-01'),
(4, '139788806220396-2', '1A', 9, '2015-06-19', '2015-07-10');

--
-- Triggers `prestito`
--
DROP TRIGGER IF EXISTS `concessionePrestito`;
DELIMITER $$
CREATE TRIGGER `concessionePrestito` BEFORE INSERT ON `prestito` FOR EACH ROW begin
DECLARE speciale CONDITION FOR SQLSTATE '45000';
DECLARE speciale1 CONDITION FOR SQLSTATE '45001';
DECLARE speciale2 CONDITION FOR SQLSTATE '45002';
declare speciale3 CONDITION FOR SQLSTATE '45003'; 

if new.NumRegCopia in (select NumeroRegistrazione
			

	from copia join periodico on copia.codiceVolume = periodico.codVolume
                )
then 
SIGNAL 

SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Non posso prestare un periodico!';

end if;

if 

new.NumRegCopia not in ( select NumeroRegistrazione
					from copia
		

	)
then
SIGNAL SQLSTATE '45001'
      SET MESSAGE_TEXT = 'Non ci sono copie disponibili';

end if;


if substring(new.NumRegCopia,1,15) in (select substring(NumRegCopia, 1, 15) from prestito
 where NumTessUtente = new.NumTessUtente)

                        then 
				SIGNAL SQLSTATE '45002'
                
   

   SET MESSAGE_TEXT = 'Hai gia preso una copia!';	

end if;

if( new.NumTessUtente in (
	select NumeroTessera
    from utente
    where TotaleAvvisi >= 10) )
then 
SIGNAL SQLSTATE '45003'
   SET MESSAGE_TEXT = 'La tua tessera è bloccata!';

end if;

end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `scritto`
--

DROP TABLE IF EXISTS `scritto`;
CREATE TABLE `scritto` (
  `CodAutore` char(16) NOT NULL DEFAULT '',
  `CodVolume` varchar(30) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `scritto`
--

INSERT INTO `scritto` (`CodAutore`, `CodVolume`) VALUES
('BKKGBR62D28Z126S', '139788806220396\r'),
('RMSGRD65A19K303Y', '3435789640'),
('SCHCRL22S26Z404B', '139788868527938\r'),
('TLKJNR92HGMNHJMX', '9788845210273\r'),
('VNNMRC43C03D612J', '1234567890'),
('VNNMRC43C03D612J', '139788804655169\r'),
('VNNMRC43C03D612J', '8563408981'),
('ZRLGRG67D07H707M', '3435789640\r');

-- --------------------------------------------------------

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
CREATE TABLE `utente` (
  `NumeroTessera` int(11) NOT NULL,
  `Nome` varchar(20) NOT NULL,
  `Cognome` varchar(20) NOT NULL,
  `TotaleAvvisi` int(11) DEFAULT '0',
  `Indirizzo` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `utente`
--

INSERT INTO `utente` (`NumeroTessera`, `Nome`, `Cognome`, `TotaleAvvisi`, `Indirizzo`) VALUES
(1, 'Daniele', 'Lupo', 0, 'Via boh n23\r'),
(2, 'Donato', 'Faraldi', 7, 'Via Corso Claudio n7\r'),
(3, 'Vittorio', 'D''Argenio', 9, 'Via Fontanatetta n26\r'),
(4, 'Umberto', 'Picariello', 5, 'Piazza Carlo Muscetta n10D ');

-- --------------------------------------------------------

--
-- Table structure for table `volume`
--

DROP TABLE IF EXISTS `volume`;
CREATE TABLE `volume` (
  `Codice` varchar(30) NOT NULL,
  `Titolo` varchar(50) NOT NULL,
  `Edizione` int(11) NOT NULL,
  `DataPubblicazione` varchar(10) NOT NULL,
  `DurataMaxPrestito` int(11) DEFAULT '30',
  `Lingua` varchar(20) NOT NULL,
  `DenominazioneEditore` varchar(50) DEFAULT NULL,
  `CittaEditore` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `volume`
--

INSERT INTO `volume` (`Codice`, `Titolo`, `Edizione`, `DataPubblicazione`, `DurataMaxPrestito`, `Lingua`, `DenominazioneEditore`, `CittaEditore`) VALUES
('1234567890', 'Dizionario Ita-En', 1, '2004-10-1', 30, 'Italiano', 'Mondadori', 'Milano'),
('139788804655169', 'L''anticristo. Storia e mito', 1, '2015-06-01', 10, 'Italiano', 'Mondadori', 'Milano\r'),
('139788806220396', 'La deviazione', 1, '2015-05-26', 7, 'Italiano', 'Einaudi', 'Torino\r'),
('139788868527938', 'Charlie Brown', 1, '2015-05-26', 7, 'Italiano', 'Baldini & Castoldi', 'Milano\r'),
('3435789640', 'Un sano Appetito', 1, '2011-03-07', 7, 'Italiano', 'Einaudi', 'Torino\r'),
('5680234871', 'Il mattino', 1, '2015-06-19', 0, 'Italiano', 'Il mattino', 'Milano\r'),
('7829108476', 'La stampa', 1, '2015-06-20', 0, 'Italiano', 'La stampa', 'Torino\r'),
('8563408981', 'Dizionario Ita-Spa', 1, '2007-01-20', 30, 'Italiano', 'Mondadori', 'Milano'),
('9788845210273', 'Il Signore degli anelli - La compagnia dell''anello', 1, '2004-01-07', 10, 'Italiano', 'Mondadori', 'Milano\r');

-- --------------------------------------------------------

--
-- Structure for view `prestitiattivi`
--
DROP TABLE IF EXISTS `prestitiattivi`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `prestitiattivi`  AS  select `prestito`.`NumRegCopia` AS `NumeroCopia`,`copia`.`CodiceVolume` AS `CodiceVolume`,`prestito`.`NumTessUtente` AS `TesseraUtente` from (`prestito` join `copia`) where ((`prestito`.`NumRegCopia` = `copia`.`NumeroRegistrazione`) and (`prestito`.`NumScafCopia` = `copia`.`NumeroScaffale`) and (`prestito`.`PosCopia` = `copia`.`Posizione`)) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appartiene`
--
ALTER TABLE `appartiene`
  ADD PRIMARY KEY (`CodVolume`,`NumeroOrdineCollana`),
  ADD KEY `appartiene_ibfk_2` (`NomeCollana`),
  ADD KEY `appartiene_ibfk_1` (`CodVolume`);

--
-- Indexes for table `autore`
--
ALTER TABLE `autore`
  ADD PRIMARY KEY (`CodiceFiscale`);

--
-- Indexes for table `casaeditrice`
--
ALTER TABLE `casaeditrice`
  ADD PRIMARY KEY (`Denominazione`,`CittaDiAppartenenza`);

--
-- Indexes for table `collana`
--
ALTER TABLE `collana`
  ADD PRIMARY KEY (`NomeCollana`);

--
-- Indexes for table `copia`
--
ALTER TABLE `copia`
  ADD PRIMARY KEY (`NumeroRegistrazione`,`NumeroScaffale`,`Posizione`),
  ADD KEY `copia_chiave_idx` (`CodiceVolume`);

--
-- Indexes for table `dizionario`
--
ALTER TABLE `dizionario`
  ADD PRIMARY KEY (`CodVolume`);

--
-- Indexes for table `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`CodVolume`);

--
-- Indexes for table `manuale`
--
ALTER TABLE `manuale`
  ADD PRIMARY KEY (`CodVolume`);

--
-- Indexes for table `periodico`
--
ALTER TABLE `periodico`
  ADD PRIMARY KEY (`CodVolume`);

--
-- Indexes for table `prestito`
--
ALTER TABLE `prestito`
  ADD PRIMARY KEY (`NumTessUtente`,`NumRegCopia`,`NumScafCopia`,`PosCopia`),
  ADD KEY `prestito_ibfk_1` (`NumRegCopia`,`NumScafCopia`,`PosCopia`);

--
-- Indexes for table `scritto`
--
ALTER TABLE `scritto`
  ADD PRIMARY KEY (`CodAutore`,`CodVolume`),
  ADD KEY `scritto` (`CodVolume`);

--
-- Indexes for table `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`NumeroTessera`);

--
-- Indexes for table `volume`
--
ALTER TABLE `volume`
  ADD PRIMARY KEY (`Codice`),
  ADD KEY `volume_ibfk_1` (`DenominazioneEditore`,`CittaEditore`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `utente`
--
ALTER TABLE `utente`
  MODIFY `NumeroTessera` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `appartiene`
--
ALTER TABLE `appartiene`
  ADD CONSTRAINT `appartiene.volume` FOREIGN KEY (`CodVolume`) REFERENCES `volume` (`Codice`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `copia`
--
ALTER TABLE `copia`
  ADD CONSTRAINT `copia_chiave` FOREIGN KEY (`CodiceVolume`) REFERENCES `volume` (`Codice`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `dizionario`
--
ALTER TABLE `dizionario`
  ADD CONSTRAINT `biblio.manuale` FOREIGN KEY (`CodVolume`) REFERENCES `manuale` (`CodVolume`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `libro`
--
ALTER TABLE `libro`
  ADD CONSTRAINT `libro_ibfk_1` FOREIGN KEY (`CodVolume`) REFERENCES `volume` (`Codice`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `manuale`
--
ALTER TABLE `manuale`
  ADD CONSTRAINT `manuale_ibfk_1` FOREIGN KEY (`CodVolume`) REFERENCES `volume` (`Codice`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `periodico`
--
ALTER TABLE `periodico`
  ADD CONSTRAINT `periodico_ibfk_1` FOREIGN KEY (`CodVolume`) REFERENCES `volume` (`Codice`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `prestito`
--
ALTER TABLE `prestito`
  ADD CONSTRAINT `prestito_ibfk_1` FOREIGN KEY (`NumRegCopia`,`NumScafCopia`,`PosCopia`) REFERENCES `copia` (`NumeroRegistrazione`, `NumeroScaffale`, `Posizione`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `prestito_utente` FOREIGN KEY (`NumTessUtente`) REFERENCES `utente` (`NumeroTessera`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `scritto`
--
ALTER TABLE `scritto`
  ADD CONSTRAINT `scritto_ibfk_1` FOREIGN KEY (`CodAutore`) REFERENCES `autore` (`CodiceFiscale`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `scritto_ibfk_2` FOREIGN KEY (`CodVolume`) REFERENCES `volume` (`Codice`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `volume`
--
ALTER TABLE `volume`
  ADD CONSTRAINT `volume_ibfk_1` FOREIGN KEY (`DenominazioneEditore`,`CittaEditore`) REFERENCES `casaeditrice` (`Denominazione`, `CittaDiAppartenenza`) ON DELETE SET NULL ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
