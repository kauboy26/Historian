import java.util.HashSet;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.io.Serializable;

public class DataStructurePrinter {
	private HashSet<Class<?>> printNowSet;
	private HashSet<Field> dontPrintTheseSet;
	private int tabWidth = 4;


	/**
	* @param clazzes All the Classes that should be printed immediately on being encountered.
	*/
	public DataStructurePrinter(Class... clazzes) {
		this();
		for (Class c: clazzes) {
			printNowSet.add(c);
		}
	}

	public DataStructurePrinter() {

		// don't print these
		dontPrintTheseSet = new HashSet<Field>(
			Arrays.asList((new Object()).getClass().getDeclaredFields()));

		dontPrintTheseSet.addAll(Arrays.asList(
			Object[].class.getDeclaredFields()));

		for (Field f: dontPrintTheseSet) {
			System.out.println("f " + f.getName());
		}

		// load the HashSet with the classes to be printed instantly.
		printNowSet = new HashSet<Class<?>>();
		printNowSet.add(int.class);
		printNowSet.add(boolean.class);
		printNowSet.add(long.class);
		printNowSet.add(short.class);
		printNowSet.add(byte.class);
		printNowSet.add(void.class);
		printNowSet.add(float.class);
		printNowSet.add(double.class);
		printNowSet.add(char.class);

		printNowSet.add(Integer.class);
		printNowSet.add(Boolean.class);
		printNowSet.add(Long.class);
		printNowSet.add(Short.class);
		printNowSet.add(Double.class);
		printNowSet.add(Float.class);
		printNowSet.add(Character.class);
		printNowSet.add(Void.class);
		printNowSet.add(Byte.class);
		printNowSet.add(Object[].class);

		// Strings too.
		printNowSet.add(String.class);
	}

	/**
	* Go through some object, and print out all its fields if they are either primitive or arrays.
	* If a field is non-primitive, look at its fields and repeat the same process.
	* @param The thing to be printed out.
	*/
	public void printObject(Object obj) {
		try {
			printObjectRecursive(obj, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printObjectRecursive(Object obj, int tabCount) throws IllegalAccessException{
		if (obj == null) {
			printTabs(tabCount);
			System.out.println("null");
			return;
		}

		if (shouldBePrinted(obj.getClass())) {
			if (obj.getClass().equals(Object[].class)) {
				printTabs(tabCount);
				System.out.println("Printing array:");
				for (int i = 0; i < ((Object[]) obj).length; i++) {
					printTabs(tabCount);
					System.out.println("index " + i);
					printObjectRecursive(((Object[]) obj)[i], tabCount + 1);
				}
			} else {
				printTabs(tabCount);
				System.out.println(obj);
			}
		} else {
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field f: fields) {
				printTabs(tabCount);
				System.out.println(f.getName() + " : ");
				printObjectRecursive(f.get(obj), tabCount + 1);
			}
		}
	}

	/**
	* Determines whether a field should be printed immediately, or examined.
	*/
	public boolean shouldBePrinted(Class<?> clazz) {
		return printNowSet.contains(clazz);
	}

	private void printTabs(int tabCount) {
		for (;tabCount > 0; tabCount--) {
			System.out.print('\t');
		}
	}

	public int getTabWidth() {
		return tabWidth;
	}

	public void setTabWidth(int tabWidth) {
		this.tabWidth = tabWidth;
	}
}