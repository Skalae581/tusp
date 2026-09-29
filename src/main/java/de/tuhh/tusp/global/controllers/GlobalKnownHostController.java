package de.tuhh.tusp.global.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.global.pojo.KnownHost;
import de.tuhh.tusp.global.service.KnownHostService;
import de.tuhh.tusp.utils.BaseCrudController;
import de.tuhh.tusp.utils.XssFilter;

@Controller
@RequestMapping(value = "/global/admin/knownhost")
@AccessConfig(path = "/global/admin/knownhost/**", access = { Rollen.ADMIN })
public class GlobalKnownHostController extends BaseCrudController<KnownHost> {

	@Autowired
	KnownHostService service;

	@Autowired
	protected GlobalKnownHostController(KnownHostService service) {
		super(service, KnownHost.class, "global/admin/knownhost/");

		XssFilter.disableFor("/global/admin/knownhost/show");
	}
}
