package test01_2;
/*
 * T 자료형에 대한 제한
 * 
 * 가능한 상위 클래스 개념 만들어내서
 * 범위를 제한하는 게 좋다 -- 아무 클래스 넣을 수 없게
 */
public class GenericPrinter<T extends Material> {

	private T material;

	public T getMaterial() {
		return material;
	}

	public void setMaterial(T material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return material.toString();
	}
}
