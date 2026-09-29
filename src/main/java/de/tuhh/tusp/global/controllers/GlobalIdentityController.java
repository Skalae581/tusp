package de.tuhh.tusp.global.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.global.pojo.Identity;
import de.tuhh.tusp.global.service.IdentityService;
import de.tuhh.tusp.utils.BaseCrudController;
import de.tuhh.tusp.utils.XssFilter;

@Controller
@RequestMapping(value = "/global/admin/identity")
@AccessConfig(path = "/global/admin/identity/**", access = { Rollen.ADMIN })
public class GlobalIdentityController extends BaseCrudController<Identity> {

	@Autowired
	protected GlobalIdentityController(IdentityService service) {
		super(service, Identity.class, "global/admin/identity/");

		XssFilter.disableFor("/global/admin/identity/show");
	}
}
