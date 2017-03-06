package fr.ebiz.cdb.it;

import com.ibatis.common.jdbc.ScriptRunner;
import fr.ebiz.cdb.dao.ConnectionPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by bpestre on 03/03/17.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({DashboardPaginationTest.class, FormAddComputerTest.class, OrderByTest.class, SortTest.class})
public class TestSuiteIT {

    @BeforeClass
    public static void setUp() {
        cleanUpDb();
    }

    @AfterClass
    public static void tearDown() {
        cleanUpDb();
    }

    private static void cleanUpDb() {

        try (Connection con = ConnectionPool.INSTANCE.getConnection()){

            ScriptRunner sr = new ScriptRunner(con, false, false);

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            File file = new File(classLoader.getResource("db/1-SCHEMA.sql").getFile());
            Reader reader = new BufferedReader(new FileReader(file));
            sr.runScript(reader);

            File file2 = new File(classLoader.getResource("db/3-ENTRIES.sql").getFile());
            Reader reader2 = new BufferedReader(new FileReader(file2));
            sr.runScript(reader2);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
