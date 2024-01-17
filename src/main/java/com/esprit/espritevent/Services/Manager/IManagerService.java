package com.esprit.espritevent.Services.Manager;

import com.esprit.espritevent.Models.Manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IManagerService {


//    ArrayList<M> readAll() throws SQLException;
//
//    M get(int id) throws SQLException;


    void addManager(Manager manager)throws SQLException;
    List<Manager> getAllManger() throws SQLException;
    void updateManager(Manager manager) throws  SQLException;
    void deleteManager(long idManager)throws  SQLException;











}
