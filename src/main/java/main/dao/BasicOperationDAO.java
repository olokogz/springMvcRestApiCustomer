package main.dao;

import java.util.List;

public interface BasicOperationDAO {

    public Object getObjectById(int objectId);

    public List<?> getObjectList();
}
