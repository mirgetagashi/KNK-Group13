package repository.Interface;

import Database.DBConnector;
import model.Period;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PeriodInterface {
    ArrayList<Period> getAllPeriods();
    Period getPeriodByName(String period);

    Period getById(int period_id);


}
