package com.hy.jsf;

import java.util.List;

public interface PersonDAOInterface {
	
	public List<PersonVO> getPersonList();

	public PersonVO getOne(int id);

	public String insert(PersonVO personVO);

	public String update(PersonVO personVO);

	public void delete(int id);

}
