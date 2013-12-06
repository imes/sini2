// +--------------------------------------------------+
// |                   Lecturer          			  |
// +--------------------------------------------------+
// |-name:String             						  |
// |-specialization:ArrayList<String>                 |
// |+availability:ArrayList<Integer>                  |
// +--------------------------------------------------+
// |+setName(arg:String):void                         |
// |+addSpecialization(arg:String):void               |
// |+addSpecialization(args:ArrayList<String>):void   |
// |+addAvailability(arg:int):void                    |
// |+removeAvailability(arg:int):void                 |
// |+getName():String                                 |
// |+getSpecialization():ArrayList<String>            |
// |+getAvailability():ArrayList<Integer>             |
// |+equals(other:Object):boolean                     |
// |+toString():String                                |
// +--------------------------------------------------+


import java.util.ArrayList;

public class Lecturer {
	private String name;
    public ArrayList<Integer> availability = new ArrayList<Integer>(java.util.Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
	private ArrayList<String> specialization = new ArrayList<String>();

	public Lecturer(String name){
		setName(name);
	}

	public Lecturer(String name, ArrayList<String> specialization){
		setName(name);
		addSpecialization(specialization);
	}

	public Lecturer(String name, String specialization){
		setName(name);
		addSpecialization(specialization);
	}

	public void setName(String arg){ name = arg; }
	public void addSpecialization(String arg){
		if(specialization.size()<3)
			specialization.add(arg.toUpperCase());
		else ;// add action
	}
	public void addSpecialization(ArrayList<String> args){
		for (String arg : args){
			if(specialization.size()<3)
				specialization.add(arg.toUpperCase());
			else
				break;
		}
	}
	public void addAvailability(int arg){ availability.add(arg); }
	public void removeAvailability(int arg){ availability.remove(new Integer(arg)); }

	public String getName(){ return name; }
	public ArrayList<String> getSpecialization(){ return specialization; }
	public ArrayList<Integer> getAvailability(){ return availability; }

	@Override
	public boolean equals(Object other) {
	    if (!(other instanceof Lecturer))
	        return false;

	    Lecturer that = (Lecturer) other;

	    return this.name.equalsIgnoreCase(that.name);
	}

	public String toString(){
		return getName() + ", " + getSpecialization();
	}
}