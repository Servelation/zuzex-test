package ru.nechay.zuzextest.testwork.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

@Entity

public class ChildPage implements Serializable {

	@Id
	@Column(name="child_id",nullable = false, updatable= false)
//	@GenericGenerator(name = "generator",strategy = "increment")
//	@GeneratedValue(generator ="generator")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "selfUrl")
	@Pattern(regexp = "(http|https)\\:\\/\\/.+\\..+", message = "Найдена не абсолютная ссылка!")
	private String selfUrl;
	
	@Column(name = "count")
	private Integer count;
	
	@Column(name = "snumber")
	private Integer snumber;
	
	@Column(name="main_id",nullable = false)
	private Integer mainId;

	//UUID:
	@Column(nullable = false, updatable = false)
    private String childPageCode;
	
	@Override
	public String toString() {
	    return "ChildPage{" +
	            "id=" + id +
	            ", selfUrl='" + selfUrl + '\'' +
	            ", count=" + count +
	            ", snumber=" + snumber +
	            ", main_id=" + mainId +
	            '}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((childPageCode == null) ? 0 : childPageCode.hashCode());
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mainId == null) ? 0 : mainId.hashCode());
		result = prime * result + ((selfUrl == null) ? 0 : selfUrl.hashCode());
		result = prime * result + ((snumber == null) ? 0 : snumber.hashCode());
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
		ChildPage other = (ChildPage) obj;
		if (childPageCode == null) {
			if (other.childPageCode != null)
				return false;
		} else if (!childPageCode.equals(other.childPageCode))
			return false;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mainId == null) {
			if (other.mainId != null)
				return false;
		} else if (!mainId.equals(other.mainId))
			return false;
		if (selfUrl == null) {
			if (other.selfUrl != null)
				return false;
		} else if (!selfUrl.equals(other.selfUrl))
			return false;
		if (snumber == null) {
			if (other.snumber != null)
				return false;
		} else if (!snumber.equals(other.snumber))
			return false;
		return true;
	}

	public ChildPage() {
		
	}
	public ChildPage(String selfUrl, Integer snumber, Integer mainId) {
		this.selfUrl = selfUrl;
		this.snumber = snumber;
		this.mainId = mainId;
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

	public void setSelfUrl(String selfUrl) {
		this.selfUrl = selfUrl;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getSnumber() {
		return snumber;
	}

	public void setSnumber(Integer snumber) {
		this.snumber = snumber;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}


	public String getChildPageCode() {
		return childPageCode;
	}


	public void setChildPageCode(String childPageCode) {
		this.childPageCode = childPageCode;
	}
	
	
}
