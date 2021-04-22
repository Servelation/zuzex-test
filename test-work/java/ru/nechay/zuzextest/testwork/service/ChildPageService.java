package ru.nechay.zuzextest.testwork.service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.nechay.zuzextest.testwork.models.ChildPage;
import ru.nechay.zuzextest.testwork.repo.ChildPageRepo;
@Service
public class ChildPageService {
	 
	private final ChildPageRepo childPageRepo;

	
	@Autowired
	public ChildPageService(ChildPageRepo childPageRepo) {
		this.childPageRepo = childPageRepo;
	}
	
	public ChildPage addChildPage(ChildPage childPage) {
		childPage.setChildPageCode(UUID.randomUUID().toString());
		return childPageRepo.save(childPage);
	}
	
	public List<ChildPage> findAllChildPages(){
		return childPageRepo.findAll();
	}
	
	public List<ChildPage> findChildPageByMainPageId(Integer id){
		//Сортируем в порядке появления
		return childPageRepo.findAll()
					.stream()
					.filter(page -> page.getMainId().equals(id))
					.sorted((o1,o2) -> o1.getSnumber().
							compareTo(o2.getSnumber()))
					.collect(Collectors.toList());
	}
	
	 
}
