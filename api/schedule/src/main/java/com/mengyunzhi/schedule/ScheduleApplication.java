package com.mengyunzhi.schedule;
import com.mengyunzhi.schedule.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class  ScheduleApplication {

	@Autowired
	static StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(ScheduleApplication.class, args);
	}
}
