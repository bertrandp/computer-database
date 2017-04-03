package fr.ebiz.cdb.webapp.it;

import com.ibatis.common.jdbc.ScriptRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

/**
 * Created by bpestre on 03/03/17.
 */
@Component
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

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-persistence.xml");

        DataSource ds = (DataSource) applicationContext.getBean("dataSource");

        try {

            ScriptRunner sr = new ScriptRunner(ds.getConnection(), false, false);

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
