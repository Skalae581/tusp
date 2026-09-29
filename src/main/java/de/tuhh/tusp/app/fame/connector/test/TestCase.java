package de.tuhh.tusp.app.fame.connector.test;

import java.util.HashMap;
import java.util.Map;

import de.tuhh.tusp.app.fame.connector.Case;
import de.tuhh.tusp.app.fame.connector.Validity;
import de.tuhh.tusp.app.fame.connector.pojo.ConnectorPojo;
import lombok.Data;

@Data
public class TestCase implements Case {

	private String id;
	private String importErrors = "";
	private Map<String, ConnectorPojo> properties = new HashMap<>();

	@Override
	public Validity getValid() {

		return null;
	}

	@Override
	public void lazyLoad() {

	}

	@Override
	public void setValid(Validity v) {

	}
}
