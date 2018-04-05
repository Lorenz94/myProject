package net.thumbtack.school.old.figures.v3;

import java.util.Objects;

public class Point3D extends Point2D {

	private int z;

	public Point3D(int x, int y, int z) {
		super(x, y);
		this.z = z;
	}

	public Point3D(int z) {
		super();
		this.z = z;
	}

	public Point3D() {
		super();
		z = 0;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public void move(int dx, int dy, int dz) {
		super.moveRel(dx, dy);
		z += dz;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Point3D point3D = (Point3D) o;
		return z == point3D.z;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), z);
	}
}