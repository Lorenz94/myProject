package net.thumbtack.school.hiring.server.dao;

import net.thumbtack.school.hiring.database.DataBase;

public abstract class BaseDao {

    protected DataBase dataBase;

    protected BaseDao() {
        this.dataBase = DataBase.getInstance();
    }
}
