-- Vincolo Valid_Name : I nomi devono contenere solo caratteri compresi tra A-Z e a-z. Inoltre devono avere al più 35 caratteri.
CREATE DOMAIN NAME AS VARCHAR(35)
	NOT NULL
	CONSTRAINT Valid_Name
	CHECK VALUE NOT LIKE '[^a-zA-Z]';

