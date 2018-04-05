package net.thumbtack.school.hiring.server.dao;

import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.response.database.SaveDataBaseDtoResponse;
import net.thumbtack.school.hiring.person.Employee;
import net.thumbtack.school.hiring.person.Employer;
import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class ServerDao extends BaseDao {


    public void loadingDataBase(String savedDataFileName) throws IOException, ServerException {
        dataBase.loadDataBase(savedDataFileName);
    }

    public void createDataBase(){
        dataBase.createDataBase();
    }

    public void saveDataBaseToFile(String saveDataFileName) throws IOException {
        dataBase.saveDataBaseToFile(saveDataFileName);
    }
}
