# Primer nivel de encabezado

 *cursiva*
 _Cursiva_
 **enfasis**
 
 
 
# _ELEMENTOS A CREAR_ 
 
###Controlador -> donde enviamos y recibimos peticionas que se conecta con postman - insomnia

###DTOS -> Representacion de las entidades del negocio
###endidades JPA -> Lo que representa las tabalas en la BD
###Repository -> se encarga de hacer las conexiones con la BD, consultas.
###Migraciones -> crear tabalas, registros en BD. 
###Spring Security -> seguridad de SPRING 
###Reglas de negocio || servicio -> donde se crearan las reglas. 



Para esta clase Consulta se procede a crear toda la logica de una nueva entidad encargada de las citas. Vincula usuarios y medicos. 
Se procede a crear el controlador, los DTO - Clase que representa la entidad de la BD y record correspondientes para las consultas y respuestas de la api. Adicionalmente se crea el migrations en version 6

_Recordar que los atributos del DTO o clase que representa la entidad de la BD deben tener el mismo nombre que el JSON que se envia para evitar errores _ 
El estandar para llamar estas propiedades es camel case idUsuario nombreUsuario 

!!!!En caso tal que se haya contruido la API sin seguir estas normas, se puede utilizar la etiqueta @JsonAlias("nombre que llega en el json") para generar una vinculacion con las clases dentro de java  !!!

##Ej: json alias

	public record DatosCompra(
		@JsonAlias("producto_id") Long idProducto,
		@JsonAlias("fecha_compra") LocalDate fechaCompra
		){}
		
## Json alias con varios parametros
si quiere reconocer más de un alias se puede agregar un objeto en la etiqueta para que spring lo tome como posibles nombres 
	
	public record DatosCompra(
	@JsonAlias("producto_id") Long idProducto,
	@JsonAlias("fecha_compra") LocalDate fechaCompra
	){}
	La anotación @JsonAlias sirve para mapear "alias" alternativos para los campos que se recibirán del JSON, y es posible asignar múltiples alias:
	public record DatosCompra(
	@JsonAlias({"producto_id", "id_producto"}) Long idProducto,
	@JsonAlias({"fecha_compra", "fecha"}) LocalDate fechaCompra
	){}


### Formatacion de fecha (para obtener un formato construido con la informacion de la BD)
Si queremos agregar un formato de fecha especifico al momento de recibir los datos de la BD podemos usar la etiqueta json format que nos permite generar un formato personalizado de la fecha y hora

	@NotNull
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	LocalDateTime data


#_ CLASE SERVICE AGENDA DE CONSULTAS - usando el patron service
se crea la clase agendadeconsultaservice para gestionar las validaciones para guardar datos en la bd

#_ VALIDACIÓN DE LA INTEGRIDAD 
Se crean las validaciones para revisar los datos de la BD, se crea una clase de tratador de errores en infra.errores validacion de integridad que se encarga de enviar un runtime exception si el id de pacientes o medicos no se encuentra en la bd. EN AGENDA DE CONSULTA SERVICE SE UTILIZA 



## Segundo nivel de encabezado

### Tercer nivel de encabezado

#### Cuarto nivel de encabezado

