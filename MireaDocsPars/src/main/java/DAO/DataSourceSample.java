package DAO;

/* Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.*/
/*
   DESCRIPTION
   The code sample shows how to use the DataSource API to establish a connection
   to the Database. You can specify properties with "setConnectionProperties".
   This is the recommended way to create connections to the Database.
   Note that an instance of oracle.jdbc.pool.OracleDataSource doesn't provide
   any connection pooling. It's just a connection factory. A connection pool,
   such as Universal Connection Pool (UCP), can be configured to use an
   instance of oracle.jdbc.pool.OracleDataSource to create connections and
   then cache them.

    Step 1: Enter the Database details in this file.
            DB_USER, DB_PASSWORD and DB_URL are required
    Step 2: Run the sample with "ant DataSourceSample"

   NOTES
    Use JDK 1.7 and above
   MODIFIED    (MM/DD/YY)
    nbsundar    02/17/15 - Creation
 */

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;

import Src.*;
import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.OracleConnection;
import org.apache.log4j.Logger;

import java.sql.DatabaseMetaData;

public class DataSourceSample {
    // The recommended format of a connection URL is the long format with the
    // connection descriptor.
    private final static String DB_URL= "jdbc:oracle:thin:@37.46.128.241:1521/XE";
    // For ATP and ADW - use the TNS Alias name along with the TNS_ADMIN
    // final static String DB_URL="jdbc:oracle:thin:@myhost:1521@wallet_dbname?TNS_ADMIN=/Users/test/wallet_dbname";
    private final static String DB_USER = "MIREA_DOCS";
    private final static String DB_PASSWORD = "pass";
    private OracleConnection connection;
    private DAO.Executor ex;

    /*
     * The method gets a database connection using
     * oracle.jdbc.pool.OracleDataSource. It also sets some connection
     * level properties, such as,
     * OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH,
     * OracleConnection.CONNECTION_PROPERTY_THIN_NET_CHECKSUM_TYPES, etc.,
     * There are many other connection related properties. Refer to
     * the OracleConnection interface to find more.
     */
    public void createDBConnect() throws SQLException {
        Properties info = new Properties();
        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
        info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");
        info.put(OracleConnection.CONNECTION_PROPERTY_THIN_NET_CHECKSUM_TYPES,
                "(MD5,SHA1,SHA256,SHA384,SHA512)");
        info.put(OracleConnection.CONNECTION_PROPERTY_THIN_NET_CHECKSUM_LEVEL,
                "REQUIRED");

        OracleDataSource ods = new OracleDataSource();
        ods.setURL(DB_URL);
        ods.setConnectionProperties(info);

        connection = (OracleConnection) ods.getConnection();
        connection.setAutoCommit(false);
        // Get the JDBC driver name and version
        DatabaseMetaData dbmd = connection.getMetaData();
        System.out.println("Driver Name: " + dbmd.getDriverName());
        System.out.println("Driver Version: " + dbmd.getDriverVersion());
        // Print some connection properties
        System.out.println("Default Row Prefetch Value is: " +
                connection.getDefaultRowPrefetch());
        System.out.println("Database Username is: " + connection.getUserName());
        System.out.println();
        // Perform a database operation

        ex = new DAO.Executor();
    }
    public void closeDBConnection () {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewPlan(Src.EducationPlan processedEdPlan, ArrayList<Src.Discipline> processedDisciplineArray, ArrayList<Competence> processedCompetenceArray) throws SQLException {
        /* Строгий сценарий добавления данных в таблицы */
        try {
            Logger log = Logger.getLogger(Discipline.class);
            int profileId, edPlanId, compId, discId, depId, hoursId;
            ex.addDirection(connection, processedEdPlan);
            profileId = ex.addProfile(connection, processedEdPlan);
            ex.addStandart(connection, processedEdPlan);
            edPlanId = ex.addEducationPlan(connection, processedEdPlan, profileId);
            for (Competence competence : processedCompetenceArray) {
                compId = ex.addCompetence(connection, competence);
                ex.addPlan2Comp(connection, edPlanId, compId);
                for (CompetenceDictionary competenceDictionary : competence.getCompetenceDictArr()) {
                    for (Src.Discipline discipline : processedDisciplineArray) {
                        if (discipline.getDisciplineName().equals(competenceDictionary.getDictionaryName())) {
                            if (discipline.getDisciplineId() == null) {
                                discId = ex.addDiscipline(connection, discipline);
                                discipline.setDisciplineId(discId);
                                depId = ex.addDepartment(connection, discipline);
                                ex.addDep2Disc(connection, depId, discipline.getDisciplineId());
                                ex.addPlan2Disc(connection, edPlanId, discipline.getDisciplineId());
                                for (Src.Hours hour : discipline.getHours()) {
                                    hoursId = ex.addHours(connection, hour);
                                    ex.addHours2Disc(connection, hoursId, discipline.getDisciplineId());
                                    ex.addHours2Comp(connection, hoursId, compId);
                                    ex.addSummaryHours(connection, discipline, discipline.getDisciplineId());

                                }
                            }
                            try {
                                ex.addDisc2Comp(connection, discipline.getDisciplineId(), compId, competenceDictionary.getDictionaryId());
                            } catch (Exception ee) {
                                System.out.println(discipline.getDisciplineId() + " " + competence.getCompetenceName()
                                + " " + discipline.getDisciplineName() + " ");
                            }
                        }
                    }
                }
            }
            connection.commit();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            connection.rollback();
        }
    }
    public void deleteEdplan (String edPlanName) throws SQLException {
        try {
            ex.deleteEdPlan(connection, edPlanName);
            connection.commit();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            connection.rollback();
        }
    }
}