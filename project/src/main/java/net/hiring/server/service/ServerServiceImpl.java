package net.thumbtack.school.hiring.server.service;

import net.thumbtack.school.hiring.server.dao.ServerDao;
import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.request.database.LoadDataBaseDtoRequest;
import net.thumbtack.school.hiring.response.database.SaveDataBaseDtoResponse;
import net.thumbtack.school.hiring.server.service.serviceimpl.ServerService;

import java.io.*;

public class ServerServiceImpl extends ServiceUtil implements ServerService {
    private ServerDao dao = new ServerDao();

    public ServerServiceImpl() {
    }

    public void startServer() {
        dao.createDataBase();
    }

    public void startServer(String savedDataFileName) throws ServerException, IOException {
        if (savedDataFileName == null || savedDataFileName.equals("")) {
            throw new ServerException(ServerErrorCode.SERVER_FILE_IS_NULL);
        }
        dao.loadingDataBase(savedDataFileName);
    }

    public void stopServer(String saveDataFileName) throws ServerException, IOException {
        if (saveDataFileName == null) {
            throw new ServerException(ServerErrorCode.SERVER_FILE_IS_NULL);
        }

        dao.saveDataBaseToFile(saveDataFileName);
    }
}
