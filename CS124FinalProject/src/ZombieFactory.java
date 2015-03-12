import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class ZombieFactory {
	private ArrayList<String> zombieList;
	private ZombieFlyWeight basis;
	public ZombieFactory() throws FileNotFoundException{
		zombieList = new ArrayList<String>();
		basis = new ZombieFlyWeight();
		File zList = new File("zombie.cfg");
		Scanner in = new Scanner(zList);
		while(in.hasNextLine()){
			zombieList.add(in.nextLine().trim());
		}	
	}

	public Zombie createZombie(){
		int chance = (int)(Math.random() * zombieList.size() * 0);
		//if(chance == 0){
			//return null;
		//}
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		Class myClass;
		Zombie thisZombie = null;
		try {
			myClass = classLoader.loadClass(zombieList.get(chance));
			thisZombie = (Zombie) myClass.newInstance();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println(thisZombie.getClass().getName());
		thisZombie.setSource(basis); // Setting Flyweight Reference
		return thisZombie;
	}
}
