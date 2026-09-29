package de.tuhh.tusp.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class BaseCrudController<T> {

	private static Logger logger = LogManager.getLogger(BaseCrudController.class);

	protected CrudService<T> service;
	private Class<?> t;
	private String viewFolder;

	@Autowired
	protected MessageContainer messageContainer;

	/**
	 * Initialize the BaseCrudController functionality
	 *
	 * @param service    Der Service zur Datenverarbeitung
	 * @param t          Die Klasse der zu verarbeitenden Daten. Es reicht leider
	 *                   nicht aus den Type-Parameter beim extend zu setzen. Hier
	 *                   muss die Selbe Klasse angegeben werden.
	 * @param viewFolder Der Pfad zu den View-JSPs. Relativ zu WEB-INF/views/. In
	 *                   dem angegebenen Verzeichnis muss es eine list.jsp und eine
	 *                   form.jsp geben. In der list.jsp stehen die daten als
	 *                   ${objects} und in der form.jsp als ${object} zur Verfügung.
	 */
	protected BaseCrudController(CrudService<T> service, Class<?> t, String viewFolder) {
		this.service = service;
		this.t = t;
		this.viewFolder = viewFolder;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/delete")
	public String delete(ModelMap model, @RequestParam Long id) {

		try {
			service.delete(id);
			messageContainer.addSuccessKey("success.delete");
		} catch (Exception e) {
			logger.debug("Konnte Datensatz nicht löschen", e);
			messageContainer.addErrorKey("error.generic");
		}

		return list(model);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public String list(ModelMap model) {

		model.put("objects", service.findAll());

		return viewFolder + "list";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/show")
	public String save(ModelMap model, @RequestParam Long id, T object) {

		try {
			if (id == null || id == 0) {
				id = service.insert(object);
				messageContainer.addSuccessKey("success.insert");
			} else {
				service.update(object);
				messageContainer.addSuccessKey("success.update");
			}
		} catch (Exception e) {
			logger.error("Saving workflow faild!", e);
			messageContainer.addErrorKey("error.generic");
		}

		return show(model, id);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/show")
	public String show(ModelMap model, @RequestParam Long id) {

		T object;
		if (id == null || id == 0) {
			try {
				object = (T) t.newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
				logger.error("Konnte kein leeres Objekt vom Typ T erstellen", e);
				throw new RuntimeException(e);
			}
		} else {
			object = service.fetchById(id);
		}
		model.put("object", object);

		return viewFolder + "form";
	}

}
