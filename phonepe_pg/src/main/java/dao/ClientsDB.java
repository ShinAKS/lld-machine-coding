package dao;

import exception.ClientExistsException;
import exception.ClientNotFoundException;
import modes.ModeEnum;

import java.util.*;

public class ClientsDB {

    private static ClientsDB CLIENTSDBINSTANCE;

//    client -> list of suppported modes
    Map<String,Set<ModeEnum>> clientsVsMode ;

//    mode -> list of clients
    Map<ModeEnum,Set<String>> modesVsClient ;


    private ClientsDB(){

        this.clientsVsMode = new HashMap<>();
        this.modesVsClient = new HashMap<>();
    }

    public static ClientsDB getInstance(){
        if (CLIENTSDBINSTANCE == null){
            CLIENTSDBINSTANCE = new ClientsDB();
        }
        return CLIENTSDBINSTANCE;
    }

    public boolean addClient(String clientName) throws ClientExistsException {
        if (this.clientsVsMode.containsKey(clientName)){
            throw new ClientExistsException();
        }
        this.clientsVsMode.put(clientName,new HashSet<>());
        return true;
    }

    public boolean addModeForClient(String client, ModeEnum mode) throws ClientNotFoundException {
        if (!this.clientsVsMode.containsKey(client)){
            throw new ClientNotFoundException();
        }
        Set<ModeEnum> modes = this.clientsVsMode.getOrDefault(client, new HashSet<>());
        modes.add(mode);
        this.clientsVsMode.put(client,modes);

        Set<String> clientsForMode = this.modesVsClient.getOrDefault(client, new HashSet<>());
        clientsForMode.add(client);
        this.modesVsClient.put(mode,clientsForMode);
        return true;
    }

    public boolean removeClient(String client){
        Set<ModeEnum> availableModeForClient = clientsVsMode.get(client);
        for (ModeEnum modeEnum : availableModeForClient){
            System.out.println("Mode to remove client from " + modeEnum);
            if (Objects.nonNull(modesVsClient.get(modeEnum)) &&
                modesVsClient.get(modeEnum).contains(client)){
                modesVsClient.get(modeEnum).remove(client);
            }

        }
        clientsVsMode.remove(client);
        return true;
    }

    public boolean removePaymentSupportForClient(String client, ModeEnum mode){

        Set<ModeEnum> modeEnumSet = clientsVsMode.get(client);
        if (modeEnumSet.contains(mode)){
            modeEnumSet.remove(mode);
            modesVsClient.get(mode).remove(client);
        }
        return true;
    }

    public Set<String> getClientsWithPaymentMode(ModeEnum mode){
        return this.modesVsClient.get(mode);
    }

    public Set<ModeEnum> getAvailablePaymentModes(String client){
        return clientsVsMode.get(client);
    }

    public boolean hasClient(String client){
        if (this.clientsVsMode.containsKey(client)){
            return true;
        }
        return false;
    }

    public List<String> getAllClients(){
        return this.clientsVsMode.keySet().stream().toList();
    }



}
