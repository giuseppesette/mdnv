package it.mdnv.facade;

import it.mdnv.dao.ClientiDAO;
import it.mdnv.model.Clienti;

import java.util.List;

public class ClientiFacade {
	
	private ClientiDAO clientiDAO = new ClientiDAO();

	/***
	public Integer getNextAutoincrementId(){
		clientiDAO.beginTransaction();
		Integer nextAutoincrementId = clientiDAO.getNextAutoincrementId();
		if(nextAutoincrementId != null)
			return nextAutoincrementId;
		else
			return new Integer(1);
	}// END getNextAutoincrementId
	***/
	
	public Integer getMaxId(){
		clientiDAO.beginTransaction();
		Integer maxIdIncrementato = clientiDAO.getMaxId();
		if(maxIdIncrementato != null)
			return maxIdIncrementato;
		else
			return new Integer(1);
		//return clientiDAO.getMaxId();
	}// END getMaxId
	
	public Clienti createCliente(Clienti cliente){
		clientiDAO.beginTransaction();
		Clienti newcli = clientiDAO.create(cliente);
		if(newcli != null && newcli.getId() != 0)
			clientiDAO.commitAndCloseTransaction();
		return newcli;
	}// END createCliente
	
	public Clienti updateCliente(Clienti cliente){
		clientiDAO.beginTransaction();
		Clienti clienteUpdated = clientiDAO.update(cliente);
		clientiDAO.commitAndCloseTransaction();
		return clienteUpdated;
	}// END updateCliente

	public Clienti findClienteByCodCliente(String cod_cliente) {
		clientiDAO.beginTransaction();
		Clienti cliente = clientiDAO.findClienteByCodCliente(cod_cliente);

		if (cliente == null || !cliente.getCodCliente().equals(cod_cliente) ) {
			return null;
		}

		return cliente;
	} // END findClienteByCodCliente
	
	public Clienti findClienteByIdCliente(int id) {
		clientiDAO.beginTransaction();
		Clienti cliente = clientiDAO.findClienteByIdCliente(id);

		if (cliente == null || cliente.getId() != id) {
			return null;
		}

		return cliente;
	} // END findClienteByIdCliente
	
	public List<Clienti> getListaClienti() {
		clientiDAO.beginTransaction();
		return clientiDAO.findAll();
	} // END getListaClienti
	
	public boolean cancellaCliente(Clienti cliente){
		boolean isCancelled = false;
		try{
			clientiDAO.beginTransaction();
			Clienti cliToBeRemoved = clientiDAO.find(cliente.getId());
			clientiDAO.delete(cliToBeRemoved);
			clientiDAO.commitAndCloseTransaction();
			isCancelled = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return isCancelled;
	} // END cancellaCliente
	
} // end class