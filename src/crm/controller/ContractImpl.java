package crm.controller;

import crm.enums.ContractType;
import crm.enums.State;
import crm.model.Contract;
import crm.service.TelecomServiceImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContractImpl extends TelecomServiceImpl<Contract> {
    @Override
    public void create(Contract contract) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO CONTRACT(ID, ContractType, CreatedDate, State, CID, ContactId) VALUES(?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, contract.getId());
            statement.setString(2, contract.getContractType().toString());
            statement.setDate(3, Date.valueOf(contract.getCreatedDate()));
            statement.setString(4, contract.getState().toString());
            statement.setInt(5, contract.getCID());
            statement.setInt(6, contract.getContactID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Contract contract, int ID) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE Contract SET ContractType = ?, CreatedDate = ?, State = ?, CID=?, ContactId=? WHERE ID =?")) {
            statement.setString(1, contract.getContractType().toString());
            statement.setDate(2, Date.valueOf(contract.getCreatedDate()));
            statement.setString(3, contract.getState().toString());
            statement.setInt(4, contract.getCID());
            statement.setInt(5, contract.getContactID());
            statement.setInt(6, ID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM CONTRACT WHERE ID=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contract findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM CONTRACT WHERE ID = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int contractId = resultSet.getInt("ID");
                    ContractType contractType = ContractType.valueOf(resultSet.getString("ContractType"));
                    LocalDate createdDate = resultSet.getDate("CreatedDate").toLocalDate();
                    State state = State.valueOf(resultSet.getString("State"));
                    int CID = resultSet.getInt("CID");
                    int contactId = resultSet.getInt("ContactId");
                    return new Contract(contractId, contractType, Date.valueOf(createdDate).toLocalDate(), state, CID, contactId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Contract> findAll() {
        List<Contract> contracts = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM CONTRACT")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int contractId = resultSet.getInt("ID");
                    ContractType contractType = ContractType.valueOf(resultSet.getString("ContractType"));
                    LocalDate createdDate = resultSet.getDate("CreatedDate").toLocalDate();
                    State state = State.valueOf(resultSet.getString("State"));
                    int CID = resultSet.getInt("CID");
                    int contactId = resultSet.getInt("ContactId");
                    contracts.add(new Contract(contractId, contractType, Date.valueOf(createdDate).toLocalDate(), state, CID, contactId));
                }
                return contracts;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ContractImpl c = new ContractImpl();
        LocalDate currentDate = LocalDate.now();
        Date sqlCurrentDate = Date.valueOf(currentDate);
        Contract contract2 = new Contract(2, ContractType.POSTPAID, currentDate, State.INACTIVE, 1, 1);
       c.create(contract2);
       // c.update(contract2, 1);
       // c.delete(2);
       List<Contract> contract =  c.findAll();
        for(Contract contra : contract) {
            System.out.println(contra.getContractType());
        }
    }
}
