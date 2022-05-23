package com.hy.jsf;

import java.util.List;

public class PersonService {

	private PersonDAOInterface dao;

	public PersonService() {
		dao = new PersonDAO();
	}

	public List<PersonVO> getPersonList() {
		return dao.getPersonList();
	}

	public String insert(PersonVO personVO) {
		return dao.insert(personVO);
	}

	public PersonVO getOne(int id) {
		return dao.getOne(id);
	}

	public String edit(PersonVO personVO) {
		return dao.update(personVO);
	}

	public void delete(int id) {
		dao.delete(id);
	}

}
