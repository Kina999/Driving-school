package com.DrivingSchool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.DrivingSchool.enumClasses.WorkerType;
import com.DrivingSchool.model.Category;
import com.DrivingSchool.model.Worker;
import com.DrivingSchool.repository.CategoryRepository;
import com.DrivingSchool.repository.WorkerRepository;

@SpringBootApplication
public class DrivingSchoolApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private WorkerRepository workerRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DrivingSchoolApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category c = new Category("A1", 45);
		categoryRepository.save(c);
		c = new Category("A", 45);
		categoryRepository.save(c); 
		c = new Category("B", 45);
		categoryRepository.save(c);
		c = new Category("B1", 45);
		categoryRepository.save(c); 
		c = new Category("C1", 45);
		categoryRepository.save(c);
		c = new Category("C", 45);
		categoryRepository.save(c); 
		c = new Category("D1", 45);
		categoryRepository.save(c);
		c = new Category("D", 45);
		categoryRepository.save(c);
		c = new Category("BE", 45);
		categoryRepository.save(c);
		c = new Category("C1E", 45);
		categoryRepository.save(c);
		c = new Category("CE", 45);
		categoryRepository.save(c);
		c = new Category("D1E", 45);
		categoryRepository.save(c);
		c = new Category("DE", 45);
		categoryRepository.save(c); 
		
		Worker w = new Worker("kina", "k", "Katarina", "Zerajic", null);
		w.setWorkerType(WorkerType.ADMINISTRATOR);
		workerRepository.save(w);
	}
}
