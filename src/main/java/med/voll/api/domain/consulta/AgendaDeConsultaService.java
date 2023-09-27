//Clase creada para manejar metodos de validación para los registros de la API de consulta controller
package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // etiqueta de servicio
public class AgendaDeConsultaService {

	@Autowired // etiqueta de inyeccion de la clase
	private PacienteRepository pacienteRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired // Clase para la comunicacion con la BD
	private ConsultaRepository consultaRepository;

	// Metodo para agendar la consulta
	public void agendar(DatosAgendarConsulta datos) {
		
			//ispresent retora un boleano - optional para encontrar el id en la BD 
		if (pacienteRepository.findById(datos.idPaciente()).isPresent()) {
			throw new ValidacionDeIntegridad("este id para el paciente no fue encontrado");
		}
		
		// el metodo exist by id utiliza los datos traidos
		if (datos.idMedico() != null && medicoRepository.existsById(datos.idMedico())) {
			throw new ValidacionDeIntegridad("este id para el medico no fue encontrado");
		}

		var paciente = pacienteRepository.findById(datos.idPaciente()).get();// buscar el id del paciente

		var medico = seleccionarMedico(datos);

		var consulta = new Consulta(null, medico, paciente, datos.fecha());

		consultaRepository.save(consulta);

	}

	//seleccionar medico aleatorio 
	private Medico seleccionarMedico(DatosAgendarConsulta datos) {
		//validacion del id de medico
		if (datos.idMedico() != null) {
			return medicoRepository.getReferenceById(datos.idMedico());//
		}
		//valida que se haya escogido una especialidad
		if (datos.especialidad() == null) {
			throw new ValidacionDeIntegridad("debe seleccionarse una especialidad para el medico");
		}

		//Envio del metodo para seleccionar medico aleatorio
		return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(), datos.fecha());
	}
}