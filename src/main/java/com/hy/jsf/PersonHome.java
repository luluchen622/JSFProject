package com.hy.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PersonHome {

	private PersonVO personVO = new PersonVO();
	private List<PersonVO> personList = new ArrayList<PersonVO>();
	private PersonService service = new PersonService();

	public PersonVO getPersonVO() {
		return personVO;
	}

	public List<PersonVO> getPersonList() {
		return service.getPersonList();
	}

	public String getOne(int id) {
		PersonVO personOne = service.getOne(id);
		if (personOne != null) {
			return "edit.xhtml";
		} else {
			return "index.xhtml";
		}
	}

	public String edit(PersonVO personVO) {

		var res = service.edit(personVO);

		if ("SUCCESS".equals(res)) {
			return "index.xhtml";
		} else {
			return "edit.xhtml";
		}
	}

	public String insert() {

		var res = service.insert(personVO);
		personVO = new PersonVO();
		if ("SUCCESS".equals(res)) {
			return "index.xhtml";
		} else {
			return "add.xhtml";
		}

	}

	public void delete(int id) {
		service.delete(id);
	}

}
