package DAO;

import Src.Competence;
import Src.Discipline;
import Src.EducationPlan;
import Src.Hours;
import oracle.jdbc.OracleConnection;

import java.sql.*;
import java.text.ParseException;



public class Executor {

    public Integer addEducationPlan (OracleConnection connection, EducationPlan educationPlan) throws SQLException, ParseException {
        final String addEdPlanString = "call mirea_docs.mirea_pars_pack.add_ed_plan(?, ?, ?, ?)";
        CallableStatement addEdPlanCall = connection.prepareCall(addEdPlanString);
        addEdPlanCall.setString("in_ed_plan_name", educationPlan.getName());
        addEdPlanCall.setDate("in_ed_plan_start_date", educationPlan.getStartDate());
        addEdPlanCall.setDate("in_ed_plan_created_date", educationPlan.getEdPlanCreateDate());
//      addEdPlanCall.setInt("in_ed_plan_st_ext_id", educationPlan.getStandartId());
//      addEdPlanCall.setString("in_ed_plan_id_dir_encr", educationPlan.getDirEncr());
//      addEdPlanCall.setInt("in_ed_plan_prof_id", 0); // autogen
         addEdPlanCall.registerOutParameter("out_ed_plan_id", Types.INTEGER);
         addEdPlanCall.execute();
        return addEdPlanCall.getInt("out_ed_plan_id");
    }

    public Integer addCompetence (OracleConnection connection, Competence competence) throws SQLException {
        final String addCompString = "call mirea_docs.mirea_pars_pack.add_competence(?, ?, ?)";
        CallableStatement addCompCall = connection.prepareCall(addCompString);
        addCompCall.setString("in_comp_name", competence.getCompetenceName());
        addCompCall.setString("in_comp_name", competence.getCompetenceDescr());
        addCompCall.registerOutParameter("out_comp_id", Types.INTEGER);
        addCompCall.execute();
        return addCompCall.getInt("out_comp_id");

    }

    public Integer addDiscipline (OracleConnection connection, Discipline discipline) throws SQLException {
        final String addDisc = "call mirea_docs.mirea_pars_pack.add_discipline(?, ?, ?)";
        CallableStatement addDiscCall = connection.prepareCall(addDisc);
        addDiscCall.setString("in_disc_name", discipline.getDisciplineName());
        addDiscCall.setString("in_disc_comp_key", "");
        addDiscCall.registerOutParameter("out_disc_id", Types.INTEGER);
        addDiscCall.execute();
        return addDiscCall.getInt("out_disc_id");
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
        return addHoursCall.getInt("out_hours_id");

    }

    public void addDirection (OracleConnection connection, EducationPlan educationPlan) throws SQLException {
        final String addDir = "call mirea_docs.mirea_pars_pack.add_direction(?, ?)";
        CallableStatement addDirCall = connection.prepareCall(addDir);
        addDirCall.setString("in_dir_encryption", educationPlan.getDirEncr());
        addDirCall.setString("in_dir_name", educationPlan.getDirName());
        addDirCall.execute();
    }

    public void addStandart (OracleConnection connection, EducationPlan educationPlan) throws SQLException {
        final String addStandart = "call mirea_docs.mirea_pars_pack.add_standart(?, ?, ?, ?)";
        CallableStatement addStandartCall = connection.prepareCall(addStandart);
        addStandartCall.setInt("in_st_ext_id", educationPlan.getStandartId());
        addStandartCall.setDate("in_st_dt_create", educationPlan.getStandartCreateDate());
        addStandartCall.setInt("in_st_order", educationPlan.getStandartOrder());
        addStandartCall.setString("in_st_file_path", "");
        addStandartCall.execute();
    }

    public Integer addProfile (OracleConnection connection, EducationPlan educationPlan) throws SQLException {
        final String addProfile = "call mirea_docs.mirea_pars_pack.add_profile(?, ?, ?)";
        CallableStatement addProfileCall = connection.prepareCall(addProfile);
        addProfileCall.setString("in_prof_name", educationPlan.getProfName());
        addProfileCall.setString("in_prof_id_dir_encr", educationPlan.getDirEncr());
        addProfileCall.registerOutParameter("out_prof_id", Types.INTEGER);
        addProfileCall.execute();
        return addProfileCall.getInt("out_prof_id");
    }

    public void addDepartment (OracleConnection connection, Object o) throws SQLException {
        final  String addDepartment = "call mirea_docs.mirea_pars_pack.add_profile(?)";
        CallableStatement addDepCall = connection.prepareCall(addDepartment);
        addDepCall.setString("in_dep_name", "");
        addDepCall.execute();
    }
/* Поддержка связей таблиц*/
    public void addPlan2Comp (OracleConnection connection, int planId, int compId) throws SQLException {
        final  String addPlan2Comp = "call mirea_docs.mirea_pars_pack.add_profile(?)";
        CallableStatement addPlan2CompCall = connection.prepareCall(addPlan2Comp);
        addPlan2CompCall.setInt("in_ed_plan_id", planId);
        addPlan2CompCall.setInt("in_comp_id", compId);
        addPlan2CompCall.execute();
    }
    public void addDisc2Comp (OracleConnection connection, int discId, int compId) throws SQLException {
        final  String addDisc2Comp = "call mirea_docs.mirea_pars_pack.add_profile(?)";
        CallableStatement addDisc2CompCall = connection.prepareCall(addDisc2Comp);
        addDisc2CompCall.setInt("in_disc_id", discId);
        addDisc2CompCall.setInt("in_comp_id", compId);
        addDisc2CompCall.execute();
    }
    public void addDep2Disc (OracleConnection connection, int depId, int discId, String discName) throws SQLException {
        final  String addDep2Disc = "call mirea_docs.mirea_pars_pack.add_profile(?)";
        CallableStatement addDep2DiscCall = connection.prepareCall(addDep2Disc);
        addDep2DiscCall.setInt("in_disc_id", discId);
        addDep2DiscCall.setInt("in_dep_id", depId);
        addDep2DiscCall.setString("in_dep2disc_name", discName);
        addDep2DiscCall.execute();
    }
    public void addPlan2Disc (OracleConnection connection, int planId, int discId, int index) throws SQLException {
        final  String addPlan2Disc = "call mirea_docs.mirea_pars_pack.add_profile(?)";
        CallableStatement addPlan2DiscCall = connection.prepareCall(addPlan2Disc);
        addPlan2DiscCall.setInt("in_plan_id", planId);
        addPlan2DiscCall.setInt("in_disc_id", discId);
        addPlan2DiscCall.setInt("in_pl2disc_index", index);
        addPlan2DiscCall.execute();
    }
    public void addHours2Comp (OracleConnection connection, int hoursId, int compId) throws SQLException {
        final  String addHours2Comp = "call mirea_docs.mirea_pars_pack.add_profile(?)";
        CallableStatement addHours2CompCall = connection.prepareCall(addHours2Comp);
        addHours2CompCall.setInt("in_hours_id", hoursId);
        addHours2CompCall.setInt("in_comp_id", compId);
        addHours2CompCall.execute();
    }
    public void addHours2Disc (OracleConnection connection, int hoursId, int discId) throws SQLException {
        final  String addHours2Disc = "call mirea_docs.mirea_pars_pack.add_profile(?)";
        CallableStatement addHours2DiscCall = connection.prepareCall(addHours2Disc);
        addHours2DiscCall.setInt("in_hours_id", hoursId);
        addHours2DiscCall.setInt("in_disc_id", discId);
        addHours2DiscCall.execute();
    }




}
