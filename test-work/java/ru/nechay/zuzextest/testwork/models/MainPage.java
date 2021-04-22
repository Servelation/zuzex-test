package ru.nechay.zuzextest.testwork.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class MainPage implements Serializable {

	@Id
	@Column(name="main_id",nullable = false, updatable= false)
	@GenericGenerator(name = "generator",strategy = "increment")
	@GeneratedValue(generator ="generator")
	private Integer id;

	@Column(name = "url")
	//делаем валидацию, чтобы не смогли вставить ерунду, а только ссылки
	@Pattern(regexp = "(http|https)\\:\\/\\/.+\\..+", message = "Нужно вставлять только ссылки!")
	private String selfUrl;

	//UUID:
	@Column(nullable = false, updatable = false)
    private String mainPageCode;
	
	
	@Override
	public String toString() {
		return "mainPage{" +
	            "id=" + id +
	            ", url='" + selfUrl + '\'' +
	            '}';
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mainPageCode == null) ? 0 : mainPageCode.hashCode());
		result = prime * result + ((selfUrl == null) ? 0 : selfUrl.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MainPage other = (MainPage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mainPageCode == null) {
			if (other.mainPageCode != null)
				return false;
		} else if (!mainPageCode.equals(other.mainPageCode))
			return false;
		if (selfUrl == null) {
			if (other.selfUrl != null)
				return false;
		} else if (!selfUrl.equals(other.selfUrl))
			return false;
		return true;
	}
	public MainPage() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSelfUrl() {
		return selfUrl;
	}

	public void setSelfUrl(String url) {
		this.selfUrl = url;
	}


	public String getMainPageCode() {
		return mainPageCode;
	}


	public void setMainPageCode(String mainPageCode) {
		this.mainPageCode = mainPageCode;
	}
	
}
