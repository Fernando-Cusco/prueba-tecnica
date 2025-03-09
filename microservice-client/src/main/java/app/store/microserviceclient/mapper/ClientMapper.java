package app.store.microserviceclient.mapper;

import app.store.microserviceclient.domain.entity.ClientEntity;
import app.store.microserviceclient.domain.entity.PersonEntity;
import app.store.microserviceclient.dto.ClientDto;

public class ClientMapper {

    private ClientMapper() {
    }

    public static PersonEntity toEntityPerson(ClientDto clientDto) {
        return PersonEntity.builder()
                .id(clientDto.getIdPerson())
                .name(clientDto.getNombres())
                .gender(clientDto.getGenero())
                .age(clientDto.getEdad())
                .identification(clientDto.getIdentificacion())
                .address(clientDto.getDireccion())
                .phone(clientDto.getTelefono())
                .build();
    }

    public static ClientEntity toEntityClient(ClientDto clientDto) {
        ClientEntity clientEntity = ClientEntity.builder()
                .clientId(clientDto.getClienteId())
                .password(clientDto.getContrasena())
                .status(clientDto.getEstado())
                .name(clientDto.getNombres())
                .gender(clientDto.getGenero())
                .age(clientDto.getEdad())
                .identification(clientDto.getIdentificacion())
                .address(clientDto.getDireccion())
                .phone(clientDto.getTelefono())
                .build();
        clientEntity.setId(clientDto.getIdClient());
        return clientEntity;
    }

    public static ClientDto toDtoClient(PersonEntity personEntity, ClientEntity clientEntity) {
        return ClientDto.builder()
                .idPerson(personEntity.getId())
                .idClient(clientEntity.getId())
                .nombres(personEntity.getName())
                .genero(personEntity.getGender())
                .edad(personEntity.getAge())
                .identificacion(personEntity.getIdentification())
                .direccion(personEntity.getAddress())
                .telefono(personEntity.getPhone())
                .clienteId(clientEntity.getClientId())
                .contrasena(clientEntity.getPassword())
                .estado(clientEntity.getStatus())
                .build();
    }

}
