package de.tuhh.tusp.autovl.service;

import java.util.List;

import de.tuhh.tusp.autovl.pojo.Buchung;

public interface BuchungService {

	void delete(Long id);

	Buchung fetchById(Long id);

	List<Buchung> findAll();

	int getCount();

	void insert(Buchung Buchung);

	void update(Buchung Buchung);
}