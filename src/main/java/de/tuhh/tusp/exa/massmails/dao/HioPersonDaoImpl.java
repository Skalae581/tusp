package de.tuhh.tusp.exa.massmails.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.exa.massmails.pojo.Receiver;
import de.tuhh.tusp.global.service.ConfigService;

@Component
public class HioPersonDaoImpl implements HioPersonDao {

	private static Logger logger = LogManager.getLogger(HioPersonDaoImpl.class);

	private static Connection dbCon = null;

	@Autowired
	private ConfigService config;

	@Override
	public void closeDbCon() {

		if (dbCon != null) {
			try {
				dbCon.close();
			} catch (SQLException e1) {
			}
		}
		dbCon = null;
	}

	private Connection getDbCon() {

		if (dbCon == null) {
			String username = config.get("de.tuhh.tusp.hio.db.username");
			String passowrd = config.get("de.tuhh.tusp.hio.db.password");
			String conStr = config.get("de.tuhh.tusp.hio.db.connectionString");

			try {
				Class.forName("org.postgresql.Driver");
				dbCon = DriverManager.getConnection(conStr, username, passowrd);
			} catch (Exception e) {
				logger.error("Konnte HIO DB nicht erreichen", e);
				throw new RuntimeException("Konnte HIO DB nicht erreichen", e);
			}
		}
		return dbCon;
	}

	@Override
	public List<Long> getLehrendenIds(Long termtypeId, Long termYear) {

		// Dozenten: Semesterabhängige Lehrveranstaltunge suchen und deren
		// Verantwortliche und Durchführnede Dozenten raussuchen.
		String sql = "SELECT DISTINCT " +

				"i.abstract_person_id AS person_id " +

				"FROM planelement pl " +

				"LEFT JOIN planned_dates pd ON pd.planable_id = pl.id " +

				"LEFT JOIN instructor i ON i.planned_dates_id = pd.id " +

				"LEFT JOIN unit u ON pl.unit_id = u.id " +

				"WHERE i.abstract_person_id IS NOT NULL " +

				"AND u.k_elementtype_id = 5  " + // 5 = Veranstaltung

				"AND pl.term_year = ? " +

				"AND pl.term_type_id = ?  "; // 30 = SoSe, 31 = WiSe

		Connection con = null;
		try {
			con = getDbCon();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, termYear);
			stmt.setLong(2, termtypeId);

			ResultSet rs = stmt.executeQuery();
			List<Long> result = new ArrayList<>();
			while (rs.next()) {
				Long id = rs.getLong("person_id");
				result.add(id);
			}
			return result;
		} catch (Exception e) {
			logger.error("SQL Exception: " + sql, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Long> getLehrpersonIds() {

		return getPersonenByRole(3l);
	}

	@Override
	public List<Long> getLehrpersonVertreterIds() {

		return getPersonenByRole(100116l);
	}

	@Override
	public Receiver getPersonData(Long personId) {

		String sql = "SELECT TRIM(CONCAT(t.uniquename, ' ', ad.uniquename, p.academicdegreesuffix)) as titel," +

				"p.firstname, p.surname, g.uniquename gender, a.eaddress " +

				"FROM person p " +

				"LEFT JOIN academicdegree ad ON ad.id = p.academicdegree_id " +

				"LEFT JOIN k_gender g ON g.id = p.k_gender_id " +

				"LEFT JOIN address a ON a.person_id = p.id AND a.addresstype = 'EMail'" +

				"LEFT JOIN title t ON t.id = p.title_id " +

				"LEFT JOIN k_addresstag at ON at.id = a.k_addresstag_id " +

				"WHERE p.id IN (?) " +

				"ORDER BY at.uniquename != 'rz' " +

				"LIMIT 1";

		Connection con = null;
		try {
			con = getDbCon();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, personId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Receiver r = new Receiver();
				r.setPersonid(personId);
				r.setNachname(rs.getString("surname"));
				r.setVorname(rs.getString("firstname"));
				r.setEmail(rs.getString("eaddress"));
				r.setTitel(rs.getString("titel"));

				String gender = rs.getString("gender");
				if ("M".equals(gender)) {
					r.setAnrede("Sehr geehrter Herr");
				} else if ("W".equals(gender)) {
					r.setAnrede("Sehr geehrte Frau");
				} else {
					r.setAnrede("Hallo");
				}

				return r;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("SQL Exception: " + sql, e);
			throw new RuntimeException(e);
		}
	}

	private List<Long> getPersonenByRole(Long roleId) {

		// Person mit Rolle: Alle mit der gültigen Rolle "roleId"
		String sql = "SELECT DISTINCT person_id " +

				"FROM orgrole org " +

				"WHERE role_id = " + roleId + "  " +

				"AND org.valid_from <= NOW() " +

				"AND org.valid_to >= NOW() ";

		Connection con = null;
		try {
			con = getDbCon();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List<Long> result = new ArrayList<>();
			while (rs.next()) {
				Long id = rs.getLong("person_id");
				result.add(id);
			}
			return result;
		} catch (Exception e) {
			logger.error("SQL Exception: " + sql, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Long> getPrueferIds() {

		return getPersonenByRole(74l);
	}

	@Override
	public List<Long> getPrueferIds(Long termtypeId, Long termYear) {

		// Prüfer: Semesterabhängige Pürfungen des Semester nehmen und die
		// Prüfer, und Zweitprüfer raus suchen
		String sql = "SELECT DISTINCT i.abstract_person_id AS person_id " +

				"FROM planelement pl " +

				"LEFT JOIN planned_dates pd ON pd.planable_id = pl.id " +

				"LEFT JOIN instructor i ON i.planned_dates_id = pd.id " +

				"LEFT JOIN unit u ON pl.unit_id = u.id " +

				"WHERE i.abstract_person_id IS NOT NULL " +

				"AND u.k_elementtype_id = 2 " + // 2 = Pruefung

				"AND pl.term_year = ? " +

				"AND pl.term_type_id = ?  " + // 30 = SoSe, 31 = WiSe

				"AND i.k_instructortask_id IN (1,2,3) "; // erst bis dritt
															// Prüfer

		Connection con = null;
		try {
			con = getDbCon();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, termYear);
			stmt.setLong(2, termtypeId);
			ResultSet rs = stmt.executeQuery();
			List<Long> result = new ArrayList<>();
			while (rs.next()) {
				Long id = rs.getLong("person_id");
				result.add(id);
			}
			return result;
		} catch (Exception e) {
			logger.error("SQL Exception: " + sql, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Long> getPrueferVertreterIds() {

		return getPersonenByRole(100037l);
	}

	@Override
	public List<Long> getPrueferVertreterIds(Long termtypeId, Long termYear) {

		// Prüfervertreter: Semesterabhängige Prüfungen des Semester nehmen und
		// die Prüfer, und Zweitprüfer raus suchen und deren Vertreter suchen
		String sql = "SELECT person_id, rp.parametervalue AS pruefer_ids " +

				"FROM orgrole org " +

				"JOIN roleparameter rp ON rp.orgrole_id = org.id " +

				"WHERE role_id = 100037 " + // Prüfervertreter

				"AND org.valid_from <= NOW() " +

				"AND org.valid_to >= NOW() ";

		// Prüfe, dass der Prüfer auch noch aktiv ist
		Connection con = null;
		try {
			con = getDbCon();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List<Long> result = new ArrayList<>();
			List<Long> prueferIds = getPrueferIds(termtypeId, termYear);
			while (rs.next()) {
				String[] pruefer = rs.getString("pruefer_ids").split(",");

				for (String p : pruefer) {
					if ("".equals(p)) {
						continue;
					}
					Long id = Long.parseLong(p);
					if (prueferIds.contains(id)) {

						Long pid = rs.getLong("person_id");
						result.add(pid);
						break;
					}
				}

			}
			return result;
		} catch (Exception e) {
			logger.error("SQL Exception: " + sql, e);
			throw new RuntimeException(e);
		}
	}

}
