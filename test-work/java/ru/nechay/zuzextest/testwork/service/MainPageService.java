package ru.nechay.zuzextest.testwork.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.nechay.zuzextest.testwork.exceptions.MainPageNotFoundException;
import ru.nechay.zuzextest.testwork.models.MainPage;
import ru.nechay.zuzextest.testwork.repo.MainPageRepo;



@Service
public class MainPageService {
	
	private final MainPageRepo mainPageRepo;

	@Autowired
	public MainPageService(MainPageRepo mainPageRepo) {
		this.mainPageRepo = mainPageRepo;
	}
	
	public MainPage addMainPage(MainPage mainPage) {
		mainPage.setMainPageCode(UUID.randomUUID().toString());
		return mainPageRepo.save(mainPage);
	}
	
	public List<MainPage> findAllMainPages(){
		return mainPageRepo.findAll();
	}
	
	
	
	public MainPage findMainPageById(Integer id) {
		return mainPageRepo.findMainPageById(id)
							.orElseThrow(() -> new MainPageNotFoundException("MainPage by id" + id+ "was not found"));
							
	}
	
	
}
