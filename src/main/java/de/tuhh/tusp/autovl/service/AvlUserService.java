package de.tuhh.tusp.autovl.service;

import java.util.List;

import de.tuhh.tusp.autovl.pojo.AvlUser;

public interface AvlUserService {

	void delete(Long id);

	AvlUser fetchById(Long id);

	List<AvlUser> findAll();

	int getCount();

	void insert(AvlUser AvlUser);

	void update(AvlUser AvlUser);
}