CREATE TABLE Usuarios( 
    Id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) CONSTRAINT Usuarios_PK PRIMARY KEY, 
    Usuario VARCHAR(30) NOT NULL UNIQUE,
    Pass VARCHAR(30) NOT NULL,
    Rol VARCHAR(30) NOT NULL,
    Activo BOOLEAN NOT NULL
);

CREATE TABLE Aulas(
    Id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) CONSTRAINT Aulas_PK PRIMARY KEY, 
    Nombre VARCHAR(30) NOT NULL UNIQUE,
    Ubicacion VARCHAR(50) NOT NULL
);

CREATE TABLE Profesores(
    Id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) CONSTRAINT Profesores_PK PRIMARY KEY, 
    Nombre VARCHAR(30) NOT NULL,
    Apellido VARCHAR(30) NOT NULL,
    Cedula CHAR(11) NOT NULL,
    Escuela VARCHAR(40) NOT NULL,
    IdUsuario INT NOT NULL,
    CONSTRAINT FK_Profesores_Usuarios
	  FOREIGN KEY (IdUsuario)
	  REFERENCES Usuarios (Id)
);

CREATE TABLE Asignaturas(
    Id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) CONSTRAINT Asignaturas_PK PRIMARY KEY, 
    Nombre VARCHAR(30) NOT NULL UNIQUE,
    CantCreditos INT NOT NULL,
    Escuela VARCHAR(40) NOT NULL
);

CREATE TABLE Secciones(
    Id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), 
    IdAsignatura INT NOT NULL,
    IdProfesor INT NOT NULL,
    IdAula INT NOT NULL,
    Dias VARCHAR(10) NOT NULL,
    Horas VARCHAR(20) NOT NULL,
    Activa BOOLEAN NOT NULL,
    PRIMARY KEY (Id, IdAsignatura, IdProfesor),
    CONSTRAINT FK_Secciones_Profesores
	  FOREIGN KEY (IdProfesor)
	  REFERENCES Profesores (Id),
    CONSTRAINT FK_Secciones_Asignaturas
	  FOREIGN KEY (IdAsignatura)
	  REFERENCES Asignaturas (Id),
    CONSTRAINT FK_Secciones_Aulas
	  FOREIGN KEY (IdAula)
	  REFERENCES Aulas (Id)
);

INSERT INTO Usuarios(Usuario,Pass,Rol,Activo)
  VALUES('Admin', '123456', 'Administrador', true);




