package ru.nechay.zuzextest.testwork.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.nechay.zuzextest.testwork.models.MainPage;

@Repository
public interface MainPageRepo extends JpaRepository<MainPage, Integer> {
	Optional<MainPage> findMainPageById(Integer id);
}
