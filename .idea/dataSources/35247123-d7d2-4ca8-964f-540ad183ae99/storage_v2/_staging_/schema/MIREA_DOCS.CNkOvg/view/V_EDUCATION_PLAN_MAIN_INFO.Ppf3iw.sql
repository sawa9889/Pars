create view V_EDUCATION_PLAN_MAIN_INFO as
  with stub_data as (select 'КБ-4 test' reduct_pulpit, 'Бакалавр test' qualification, 'Очная test' study_format , 'Test teacher' teacher, 1 coursework from dual)
  select ed.ed_plan_name, prof.prof_name, 
      dir.dir_name, dir.dir_encryption,
      stub_data.reduct_pulpit, stub_data.qualification,
      stub_data.study_format,
      comp.comp_name, comp.comp_descr,
      dep.dep_name, dep.dep_code,
      disc2comp.disc_comp_key,
      disc.disc_name, stub_data.teacher,
      h.hours_b_ecz as is_exam, h.hours_b_zachet as is_offset , h.hours_control, 
      h.hours_ksr, h.hours_lab, h.hours_lectures, 
      h.hours_practice, h.hours_semestr, h.hours_srs,
      sh.control, sh.hourscontact as hours_contact, sh.hoursexpert as hours_expert, 
      sh.sr, sh.withzet as with_zet,
      ceil(h.hours_semestr/2) as course_work
    from education_plan ed, profiles prof, direction dir                        
    , educ_plan2competence ed2comp, educ_plan2disc ed2disc               
    , competence comp, discipline disc                      
    , dep2discipline dep2disc, department dep                       
    , discipline2comp disc2comp, hours2discipline h2disc              
    , hours h, summary_hours sh                     
    ,stub_data
   where ed.ed_plan_prof_id = prof.prof_id 
     and dir.dir_encryption = ed.ed_plan_id_dir_encr 
     and ed2comp.ed_plan_id = ed.ed_plan_id 
     and ed2disc.plan_id = ed.ed_plan_id 
     and ed2comp.comp_id = comp.comp_id 
     and disc.disc_id = ed2disc.disc_id 
     and dep2disc.disc_id = disc.disc_id 
     and dep.dep_id = dep2disc.dep_id
     and disc.disc_id = disc2comp.disc_id and comp.comp_id = disc2comp.comp_id
     and h2disc.disc_id = disc.disc_id
     and h.hours_id = h2disc.hours_id
     and sh.disc_id = disc.disc_id
WITH CHECK OPTION
/

