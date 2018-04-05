package net.thumbtack.school.hiring.server.service.serviceimpl;

import net.thumbtack.school.hiring.exception.ServerException;

import java.io.IOException;

public interface ServerService {
    void startServer();
    void startServer(String savedDataFileName) throws IOException, ServerException;
    void stopServer(String saveDataFileName) throws ServerException, IOException;
}
