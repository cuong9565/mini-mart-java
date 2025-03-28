package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.*;
import DTO.*;

public class ProviderBUS {
    private static ProviderBUS instance;

    public ProviderBUS() {}
    public static ProviderBUS getInstance() {
        if (instance == null) instance = new ProviderBUS();
        return instance;
    }

    public List<ProviderDTO>getListProvider() {
        return ProviderDAO.getInstance().getListProvider();
    }
}
