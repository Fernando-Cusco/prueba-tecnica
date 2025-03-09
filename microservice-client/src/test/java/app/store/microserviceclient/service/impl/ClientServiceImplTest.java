package app.store.microserviceclient.service.impl;

import app.store.microserviceclient.domain.entity.ClientEntity;
import app.store.microserviceclient.domain.repository.ClientRepository;
import app.store.microserviceclient.domain.repository.PersonRepository;
import app.store.microserviceclient.dto.ClientDto;
import app.store.microserviceclient.dto.ResponseDto;
import app.store.microserviceclient.mapper.ClientMapper;
import app.store.microserviceclient.producer.KafkaEventProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClientServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private KafkaEventProducer eventProducer;

    @InjectMocks
    private ClientServiceImpl clientServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveClientSuccess() {
        ClientDto clientDto = ClientDto.builder()
                .idPerson(1L)
                .idClient(1L)
                .clienteId(1L)
                .nombres("John Doe")
                .identificacion("1234567890")
                .estado(true)
                .edad(29)
                .contrasena("xxxxxxx")
                .genero("Masculino")
                .telefono("1234567890")
                .build();
        ClientEntity clientEntity = ClientMapper.toEntityClient(clientDto);
        when(clientRepository.save(clientEntity)).thenReturn(clientEntity);
        ResponseDto response = clientServiceImpl.saveClient(clientDto);
        assertNotNull(response);
        assertEquals("Client saved successfully", response.getMessage());
    }

    @Test
    void saveClientNull() {
        ResponseDto response = clientServiceImpl.saveClient(null);
        assertNotNull(response);
        assertEquals("Client data is required", response.getMessage());
    }

    @Test
    void getClientByIdSuccess() {
        Long clientId = 1L;
        ClientDto clientDto = ClientDto.builder()
                .idPerson(clientId)
                .idClient(clientId)
                .clienteId(clientId)
                .nombres("John Doe")
                .identificacion("1234567890")
                .estado(true)
                .edad(29)
                .contrasena("xxxxxxx")
                .genero("Masculino")
                .telefono("1234567890")
                .build();
        ClientEntity clientEntity = ClientMapper.toEntityClient(clientDto);
        clientEntity.setId(clientId);
        clientEntity.setClientId(clientId);
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(clientEntity));
        ResponseDto response = clientServiceImpl.getClientById(clientId);

        assertNotNull(response);
        assertEquals("Client retrieved successfully", response.getMessage());
    }

    @Test
    void getClientByIdNotFound() {
        Long clientId = 1L;
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());
        ResponseDto response = clientServiceImpl.getClientById(clientId);
        assertNotNull(response);
        assertEquals("Client not found", response.getMessage());
    }

}