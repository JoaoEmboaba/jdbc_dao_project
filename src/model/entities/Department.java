package model.entities;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Department implements Serializable, Comparable<Department> {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	
	public Department(Integer id) {
		this.id = id;
	}

	public Department(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Department() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}

	public static Department instanceDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public int compareTo(Department o) {
		return name.compareTo(o.getName());
	}
}
