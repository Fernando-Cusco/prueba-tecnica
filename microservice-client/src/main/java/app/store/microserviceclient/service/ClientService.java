package app.store.microserviceclient.service;

import app.store.microserviceclient.dto.ClientDto;
import app.store.microserviceclient.dto.ResponseDto;

public interface ClientService {
    ResponseDto saveClient(ClientDto clientDto);
    ResponseDto getClientById(Long clientId);
    ResponseDto updateClient(Long clientId, ClientDto clientDto);
    ResponseDto deleteClient(Long clientId);
    ResponseDto getAllClients();
}
