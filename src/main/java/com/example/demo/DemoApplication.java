package com.example.demo;

import com.example.demo.domain.Activities;
import com.example.demo.domain.ActivitiesDO;
import com.example.demo.domain.ActivitySchedule;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.service.MongoService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//	mongoService.testQuery();
		//mongoService.saveTask();
		//mongoService.updateTask1();

	/*	List<Activities> records = new ArrayList<>();
		try (CSVReader csvReader = new CSVReader(new FileReader("C:\\Users\\Vinod\\test\\demo\\src\\main\\resources\\Book1.csv"));) {
			CsvToBean<Activities> cb = new CsvToBeanBuilder<Activities>(csvReader)
					.withType(Activities.class)
					.build();
			List<Activities> ac = cb.parse();
			List<ActivitiesDO> list = ac.stream().map(a -> {
				var ado = new ActivitiesDO();
				ado.setName(a.getName());
				ado.setFinishDate(a.getFinishDate());
				ado.setSorCost(Integer.parseInt(a.getSorCost().trim().replace(",", "")));
				ado.setUnit(a.getUnit().trim());
				ado.setWeightage(a.getWeightage());
				ado.setSorQuantity(Integer.parseInt(a.getSorQuantity().trim().replace(",", "")));
				ado.setUnitWeightage(a.getUnitWeightage());
				ado.setStartDate(a.getStartDate());
				return ado;
			}).toList();

			mongoTemplate.insertAll(list);
		}catch (Exception e) {

		}*/


		List<String[]> schs = null;
		try (Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\Vinod\\Documents\\schedule.csv"))) {
			try (CSVReader csvReader = new CSVReader(reader)) {
				schs = csvReader.readAll();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(schs);

		try  {


			Collection<ActivitiesDO> activitiesDOS = mongoTemplate.findAll(ActivitiesDO.class);
			int i =0;
			List<ActivitySchedule> activitySchedules = new ArrayList<>();
			String[] mon = {"FEB","MAR","APR", "MAY", "JUNE", "JULY", "AUG", "SEPT", "OCT", "NOV", "DEC","JAN","FEB","MAR","APR", "MAY"};


			List<ActivitySchedule> slist = new ArrayList<>();
			for(ActivitiesDO a : activitiesDOS) {


				System.out.println(a.getId());

				for(int l = 0; l< 2;l++) {

					String[] strings = schs.get(i + l);


					for(int k=1; k<=11; k++) {
						var as = new ActivitySchedule();
						as.setType(strings[0]);
						as.setMonth(mon[k-1]);
						as.setYear(2023);
						as.setActivityId(a.getId());
						as.setValue(StringUtils.isNotEmpty(strings[k])? Double.parseDouble(strings[k]) : 0);
						slist.add(as);

					}
					for(int k=12; k<=16; k++) {
						var as = new ActivitySchedule();
						as.setType(strings[0]);
						as.setMonth(mon[k-1]);
						as.setYear(2024);
						as.setActivityId(a.getId());
						as.setValue(StringUtils.isNotEmpty(strings[k])? Double.parseDouble(strings[k]) : 0);
						slist.add(as);
					}


				}
				i = i+ 2;

			}

			System.out.println(slist);


			mongoTemplate.insertAll(slist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
