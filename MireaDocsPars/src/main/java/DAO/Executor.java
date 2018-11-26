package DAO;

import Src.Competence;
import Src.Discipline;
import Src.EducationPlan;
import Src.Hours;
import oracle.jdbc.OracleConnection;

import java.sql.*;


public class Executor {

    public Integer addEducationPlan (OracleConnection connection, EducationPlan educationPlan, Integer profId) throws SQLException {
        final String addEdPlanString = "call mirea_docs.mirea_pars_pack.add_ed_plan(?, ?, ?, ?, ?, ?, ?)";
        CallableStatement addEdPlanCall = connection.prepareCall(addEdPlanString);
        addEdPlanCall.setString("in_ed_plan_name", educationPlan.getName());
        addEdPlanCall.setDate("in_ed_plan_start_date", educationPlan.getStartDate());
        addEdPlanCall.setDate("in_ed_plan_created_date", educationPlan.getEdPlanCreateDate());
        addEdPlanCall.setInt("in_ed_plan_st_ext_id", educationPlan.getStandartId());
        addEdPlanCall.setString("in_ed_plan_id_dir_encr", educationPlan.getDirEncr());
        addEdPlanCall.setInt("in_ed_plan_prof_id", profId); // autogen
        addEdPlanCall.registerOutParameter("out_ed_plan_id", Types.INTEGER);
        addEdPlanCall.execute();
        int edPlanid =  addEdPlanCall.getInt("out_ed_plan_id");
        addEdPlanCall.close();
        return edPlanid;
    }

    public Integer addCompetence (OracleConnection connection, Competence competence) throws SQLException {
        final String addCompString = "call mirea_docs.mirea_pars_pack.add_competence(?, ?, ?)";
        CallableStatement addCompCall = connection.prepareCall(addCompString);
        addCompCall.setString("in_comp_name", competence.getCompetenceName());
        addCompCall.setString("in_comp_descr", competence.getCompetenceDescr());
        addCompCall.registerOutParameter("out_comp_id", Types.INTEGER);
        addCompCall.execute();
        int compId = addCompCall.getInt("out_comp_id");
        addCompCall.close();
        return compId;

    }

    public Integer addDiscipline (OracleConnection connection, Discipline discipline) throws SQLException {
        final String addDisc = "call mirea_docs.mirea_pars_pack.add_discipline(?, ?)";
        CallableStatement addDiscCall = connection.prepareCall(addDisc);
        addDiscCall.setString("in_disc_name", discipline.getDisciplineName());
        addDiscCall.registerOutParameter("out_disc_id", Types.INTEGER);
        addDiscCall.execute();
        int discId = addDiscCall.getInt("out_disc_id");
        addDiscCall.close();
        return discId;
    }

    public Integer addHours (OracleConnection connection, Hours hours) throws SQLException {
        final String addHours = "call mirea_docs.mirea_pars_pack.add_hours(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        CallableStatement addHoursCall = connection.prepareCall(addHours);
        addHoursCall.setInt("in_hours_semestr", hours.getSemestr());
        addHoursCall.setInt("in_hours_lectures", hours.getLectures());
        addHoursCall.setInt("in_hours_lab", hours.getLab());
        addHoursCall.setInt("in_hours_practice", hours.getPractice());
        addHoursCall.setInt("in_hours_ksr", hours.getKsr());
        addHoursCall.setInt("in_hours_srs", hours.getSrs());
        addHoursCall.setInt("in_hours_control", hours.getControl());
        addHoursCall.setInt("in_hours_b_ecz", hours.isEcz());
        addHoursCall.setInt("in_hours_b_zachet", hours.isZachet());
        addHoursCall.registerOutParameter("out_hours_id", Types.INTEGER);
        addHoursCall.execute();
        int hoursId = addHoursCall.getInt("out_hours_id");
        addHoursCall.close();
        return hoursId;

    }

    public void addDirection (OracleConnection connection, EducationPlan educationPlan) throws SQLException {
        final String addDir = "call mirea_docs.mirea_pars_pack.add_direction(?, ?)";
        CallableStatement addDirCall = connection.prepareCall(addDir);
        addDirCall.setString("in_dir_encryption", educationPlan.getDirEncr());
        addDirCall.setString("in_dir_name", educationPlan.getDirName());
        addDirCall.execute();
        addDirCall.close();
    }

    public void addStandart (OracleConnection connection, EducationPlan educationPlan) throws SQLException {
        final String addStandart = "call mirea_docs.mirea_pars_pack.add_standart(?, ?, ?, ?)";
        CallableStatement addStandartCall = connection.prepareCall(addStandart);
        addStandartCall.setInt("in_st_ext_id", educationPlan.getStandartId());
        addStandartCall.setDate("in_st_dt_create", educationPlan.getStandartCreateDate());
        addStandartCall.setInt("in_st_order", educationPlan.getStandartOrder());
        addStandartCall.setString("in_st_file_path", "");
        addStandartCall.execute();
        addStandartCall.close();
    }

    public Integer addProfile (OracleConnection connection, EducationPlan educationPlan) throws SQLException {
        final String addProfile = "call mirea_docs.mirea_pars_pack.add_profile(?, ?, ?)";
        CallableStatement addProfileCall = connection.prepareCall(addProfile);
        addProfileCall.setString("in_prof_name", educationPlan.getProfName());
        addProfileCall.setString("in_prof_id_dir_encr", educationPlan.getDirEncr());
        addProfileCall.registerOutParameter("out_prof_id", Types.INTEGER);
        addProfileCall.execute();
        int profileId = addProfileCall.getInt("out_prof_id");
        addProfileCall.close();
        return profileId;
    }

    public Integer addDepartment (OracleConnection connection, Discipline discipline) throws SQLException {
        final  String addDepartment = "call mirea_docs.mirea_pars_pack.add_department(?, ?, ?)";
        CallableStatement addDepCall = connection.prepareCall(addDepartment);
        addDepCall.setString("in_dep_name", discipline.getDepName());
        addDepCall.setInt("in_dep_code", discipline.getDepNumber());
        addDepCall.registerOutParameter("out_dep_id", Types.INTEGER);
        addDepCall.execute();
        int depId = addDepCall.getInt("out_dep_id");
        addDepCall.close();
        return depId;
    }
/* Поддержка связей таблиц*/
    public void addPlan2Comp (OracleConnection connection, int planId, int compId) throws SQLException {
        final  String addPlan2Comp = "call mirea_docs.mirea_pars_pack.add_educ_plan2competence(?, ?)";
        CallableStatement addPlan2CompCall = connection.prepareCall(addPlan2Comp);
        addPlan2CompCall.setInt("in_ed_plan_id", planId);
        addPlan2CompCall.setInt("in_comp_id", compId);
        addPlan2CompCall.execute();
        addPlan2CompCall.close();
    }
    public void addDisc2Comp (OracleConnection connection, int discId, int compId, String discCompKey) throws SQLException {
        final  String addDisc2Comp = "call mirea_docs.mirea_pars_pack.add_discipline2comp(?, ?, ?)";
        CallableStatement addDisc2CompCall = connection.prepareCall(addDisc2Comp);
        addDisc2CompCall.setInt("in_disc_id", discId);
        addDisc2CompCall.setInt("in_comp_id", compId);
        addDisc2CompCall.setString("in_disc_comp_key", discCompKey);
        addDisc2CompCall.execute();
        addDisc2CompCall.close();
    }
    public void addDep2Disc (OracleConnection connection, int depId, int discId) throws SQLException {
        final  String addDep2Disc = "call mirea_docs.mirea_pars_pack.add_dep2discipline(?, ?)";
        CallableStatement addDep2DiscCall = connection.prepareCall(addDep2Disc);
        addDep2DiscCall.setInt("in_disc_id", discId);
        addDep2DiscCall.setInt("in_dep_id", depId);
        addDep2DiscCall.execute();
        addDep2DiscCall.close();
    }
    public void addPlan2Disc (OracleConnection connection, int planId, int discId) throws SQLException {
        final  String addPlan2Disc = "call mirea_docs.mirea_pars_pack.add_educ_plan2disc(?, ?)";
        CallableStatement addPlan2DiscCall = connection.prepareCall(addPlan2Disc);
        addPlan2DiscCall.setInt("in_plan_id", planId);
        addPlan2DiscCall.setInt("in_disc_id", discId);
        addPlan2DiscCall.execute();
        addPlan2DiscCall.close();
    }
    public void addHours2Comp (OracleConnection connection, int hoursId, int compId) throws SQLException {
        final  String addHours2Comp = "call mirea_docs.mirea_pars_pack.add_hours2competence(?, ?)";
        CallableStatement addHours2CompCall = connection.prepareCall(addHours2Comp);
        addHours2CompCall.setInt("in_hours_id", hoursId);
        addHours2CompCall.setInt("in_comp_id", compId);
        addHours2CompCall.execute();
        addHours2CompCall.close();
    }
    public void addHours2Disc (OracleConnection connection, int hoursId, int discId) throws SQLException {
        final  String addHours2Disc = "call mirea_docs.mirea_pars_pack.add_hours2discipline(?, ?)";
        CallableStatement addHours2DiscCall = connection.prepareCall(addHours2Disc);
        addHours2DiscCall.setInt("in_hours_id", hoursId);
        addHours2DiscCall.setInt("in_disc_id", discId);
        addHours2DiscCall.execute();
        addHours2DiscCall.close();
    }
    void deleteEdPlan (OracleConnection connection, String edPlanName) throws SQLException{
        final String deleteEdPlan = "call mirea_docs.mirea_pars_pack.delete_education_plan(?)";
        CallableStatement deleteEdPlanCall = connection.prepareCall(deleteEdPlan);
        deleteEdPlanCall.setString("in_ed_plan_name", edPlanName);
        deleteEdPlanCall.execute();
        deleteEdPlanCall.close();

    }




}
