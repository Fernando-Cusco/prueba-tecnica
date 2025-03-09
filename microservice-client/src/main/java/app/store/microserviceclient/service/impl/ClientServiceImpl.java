package app.store.microserviceclient.service.impl;

import app.store.microserviceclient.domain.entity.ClientEntity;
import app.store.microserviceclient.domain.entity.PersonEntity;
import app.store.microserviceclient.domain.repository.ClientRepository;
import app.store.microserviceclient.domain.repository.PersonRepository;
import app.store.microserviceclient.dto.ClientDto;
import app.store.microserviceclient.dto.ResponseDto;
import app.store.microserviceclient.producer.KafkaEventProducer;
import app.store.microserviceclient.mapper.ClientMapper;
import app.store.microserviceclient.service.ClientService;
import app.store.microserviceclient.service.common.ServiceCommon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl extends ServiceCommon implements ClientService {

    private static final String CLASS_NAME = ClientServiceImpl.class.getSimpleName();

    private final PersonRepository personRepository;

    private final ClientRepository clientRepository;

    private final KafkaEventProducer eventProducer;

    @Override
    public ResponseDto saveClient(ClientDto clientDto) {
        if (clientDto == null) {
            return generateResponseError("Client data is required", null);
        }
        log.info("{}.saveClient() method is called", CLASS_NAME);
        PersonEntity personEntity = ClientMapper.toEntityPerson(clientDto);
        ClientEntity clientEntity = ClientMapper.toEntityClient(clientDto);
        clientRepository.save(clientEntity);
        clientDto = ClientMapper.toDtoClient(personEntity, clientEntity);
        eventProducer.send(clientDto);
        return generateResponseSuccessCreated("Client saved successfully", clientDto);
    }

    @Override
    public ResponseDto getClientById(Long clientId) {
        PersonEntity personEntity = personRepository.findById(clientId).orElse(null);
        if (personEntity == null) {
            return generateResponseNotFound("Client not found");
        }
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setStatus(((ClientEntity) personEntity).getStatus());
        clientEntity.setPassword(((ClientEntity) personEntity).getPassword());
        clientEntity.setClientId(((ClientEntity) personEntity).getClientId());
        clientEntity.setId((personEntity).getId());
        ClientDto clientDto = ClientMapper.toDtoClient(personEntity, clientEntity);
        return generateResponseSuccess("Client retrieved successfully", clientDto);
    }

    @Override
    public ResponseDto updateClient(Long clientId, ClientDto clientDto) {
        PersonEntity personEntity = personRepository.findById(clientId).orElse(null);
        if (personEntity == null) {
            return generateResponseNotFound("Client not found");
        }
        ClientEntity clientEntity = updateClient(personEntity, clientDto);
        clientRepository.save(clientEntity);
        return generateResponseSuccess("Client updated successfully", clientDto);
    }

    private ClientEntity updateClient(PersonEntity personEntity, ClientDto clientDto) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientId(((ClientEntity) personEntity).getClientId());
        clientEntity.setId(personEntity.getId());
        setIfNotNull(clientDto.getEstado(), clientEntity::setStatus);
        setIfNotNull(clientDto.getContrasena(), clientEntity::setPassword);
        setIfNotNull(clientDto.getNombres(), clientEntity::setName);
        setIfNotNull(clientDto.getGenero(), clientEntity::setGender);
        setIfNotNull(clientDto.getEdad(), clientEntity::setAge);
        setIfNotNull(clientDto.getIdentificacion(), clientEntity::setIdentification);
        setIfNotNull(clientDto.getDireccion(), clientEntity::setAddress);
        setIfNotNull(clientDto.getTelefono(), clientEntity::setPhone);
        return clientEntity;
    }

    private <T> void setIfNotNull(T value, java.util.function.Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }


    @Override
    public ResponseDto deleteClient(Long clientId) {
        PersonEntity personEntity = personRepository.findById(clientId).orElse(null);
        if (personEntity == null) {
            return generateResponseNotFound("Client not found");
        }
        personRepository.delete(personEntity);
        return generateResponseSuccess("Client deleted successfully", null);
    }

    @Override
    public ResponseDto getAllClients() {
        Iterable<ClientEntity> clientEntities = clientRepository.findAll();
        List<ClientEntity> clients = new ArrayList<>();
        clientEntities.forEach(clients::add);
        return generateResponseSuccess("Clients retrieved successfully", clients);
    }
}
