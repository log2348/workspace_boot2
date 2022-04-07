package test01_2;

public class MainTest {

	public static void main(String[] args) {
		Powder powder = new Powder();
		Plastic plastic = new Plastic();
		
		GenericPrinter<Material> genericPrinter1 = new GenericPrinter<>();
		genericPrinter1.setMaterial(plastic);
		System.out.println(genericPrinter1.getMaterial());
		
		genericPrinter1.setMaterial(powder);
		System.out.println(genericPrinter1.getMaterial());
		
		GenericPrinter<Powder> genericPrinter2 = new GenericPrinter<Powder>();
		genericPrinter2.setMaterial(powder);
		System.out.println(genericPrinter2.getMaterial());

	}

}
