package com.test.config;

import com.test.entity.Person;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 8/22/2018.
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger logger = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            logger.info("JOB FINISHED! Time to verify the results");

            List<Person> results = jdbcTemplate.query("select first_name, last_name from people", new RowMapper<Person>() {
                @Override
                public Person mapRow(ResultSet rs, int i) throws SQLException {
                    return new Person(rs.getString(1),rs.getString(2));
                }
            });
            for(Person person:results){
                logger.info("Found <"+ person + "> in the database.");
            }
        }
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("......before job......");
        super.beforeJob(jobExecution);
    }
}
