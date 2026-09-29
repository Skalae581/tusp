package de.tuhh.tusp.autovl.service;

import java.util.List;

import de.tuhh.tusp.autovl.pojo.Auto;

public interface AutoService {

	void delete(Long id);

	Auto fetchById(Long id);

	List<Auto> findAll();

	int getCount();

	void insert(Auto Auto);

	void update(Auto Auto);
}