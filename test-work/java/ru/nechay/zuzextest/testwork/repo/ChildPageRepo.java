package ru.nechay.zuzextest.testwork.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.nechay.zuzextest.testwork.models.ChildPage;

@Repository
public interface ChildPageRepo extends JpaRepository<ChildPage, Integer> {

}
