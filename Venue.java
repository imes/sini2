//+------------------------------------------+
//|			Venue					         |
//+------------------------------------------+
//|-name:String 							 |
//|-type:String                              |
//|-courses:ArrayList<String>                |
//|+availability:ArrayList<Integer>          |
//+------------------------------------------+
//|+setName(arg:String):void                 |
//|+setType(arg:String):void                 |
//|+addCourse(arg:String):void               |
//|+addCourse(args:ArrayList<String>):void   |
//|+addAvailability(arg:int):void            |
//|+removeAvailability(arg:int):void         |
//|+getName():String                         |
//|+getType():String                         |
//|+getCourses():ArrayList<String>           |
//|+getAvailability():ArrayList<Integer>     |
//|+equals(other:Object):boolean             |
//|+toString():String                        |
//+------------------------------------------+


import java.util.ArrayList;

public class Venue {
	private String name, type = "";
	public ArrayList<Integer> availability = new ArrayList<Integer>(java.util.Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
	private ArrayList<String> courses = new ArrayList<String>();

	public Venue(String name, String type){
		setName(name);
		setType(type);
	}

	public void setName(String arg){ name = arg; }
	public void setType(String arg){ type = arg; }
	public void addCourse(String arg){ courses.add(arg.toUpperCase()); }
	public void addCourse(ArrayList<String> args){for (String arg : args) courses.add(arg.toUpperCase()); }
	public void addAvailability(int arg){ availability.add(arg); }
	public void removeAvailability(int arg){ availability.remove(new Integer(arg)); }

	public String getName(){ return name; }
	public String getType(){ return type; }
	public ArrayList<String> getCourses(){ return courses; }
	public ArrayList<Integer> getAvailability(){ return availability; }

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Venue))
			return false;

		Venue that = (Venue) other;

		return this.name.equalsIgnoreCase(that.name)
			&& this.type.equalsIgnoreCase(that.type);
	}

	public String toString(){
		return getName() + ", " + getCourses();
	}
}