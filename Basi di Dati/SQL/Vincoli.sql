-- Vincolo Valid_Name : I nomi devono contenere solo caratteri compresi tra A-Z e a-z. Inoltre devono avere al più 35 caratteri.
CREATE DOMAIN MYNAME AS VARCHAR(35)
CONSTRAINT Valid_Name
CHECK ((VALUE <> '') AND (VALUE LIKE '([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])
			  ([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])
			  ([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])
			  ([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])([A-Z]|[a-z])'));
