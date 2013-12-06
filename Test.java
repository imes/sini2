//+-------------------------------------+
//|			Test 				        |
//+-------------------------------------+
//|+courses:ArrayList<Course>           |
//|__________________________           |
//|+lecturers:ArrayList<Lecturer>       |
//|______________________________       |
//|+venues:ArrayList<Venue>             |
//|________________________             |
//|+schedules:ArrayList<Schedule>       |
//|______________________________       |
//|+sections:ArrayList<Section>         |
//|_____________________________        |
//+-------------------------------------+
//|+save():void                         |
//|____________                         |
//|+add(o:Object):void                  |
//|_____________________________        |
//|+remove(o:Object ):void              |
//|_______________________              |
//|+generateSections():void             |
//|_______________________              |
//+-------------------------------------+
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {
	public static ArrayList<Course> courses = new ArrayList<Course>();
	public static ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
	public static ArrayList<Venue> venues = new ArrayList<Venue>();
	public static ArrayList<Schedule> schedules = new ArrayList<Schedule>();
	public static ArrayList<Section> sections = new ArrayList<Section>();

	public static void main(String[] args) {

		courses.add(new Course("CSC1100", "Elements of Programming", 3, 4));
		courses.add(new Course("CSC1102", "Web Programming Fundamental", 3, 1));
		courses.add(new Course("CSC1103", "Object Oriented Programming", 3, 3));

		lecturers.add(new Lecturer("Dr Suriani", "CSC1100"));
		lecturers.add(new Lecturer("Dr Amelia", "CSC1100"));
		lecturers.add(new Lecturer("Dr Rizal", "CSC1102"));
		lecturers.add(new Lecturer("Dr Azlin", "CSC1103"));
		lecturers.add(new Lecturer("Dr Norsaremah", new ArrayList<String>(java.util.Arrays.asList("CSC1100", "CSC1102", "CSC1103", "CSC1104"))));

		venues.add(new Venue("Lab 3", "LAB"));
		venues.add(new Venue("Lab 6", "LAB"));
		venues.get(0).addCourse("CSC1102");
		venues.get(1).addCourse("CSC1100");
		venues.get(1).addCourse("CSC1103");

		// System.out.println(venues);

		// create sections based on specialization
		generateSections();

		for (Section s : sections) {
			s.setVenue(venues);
			// System.out.println(s);
		}

		for (Section s : sections) {
			s.setLecturer(lecturers, false);
			// System.out.println(s);
		}

		for (Section s : sections) {
			if(!s.generateSchedule(sections, true))
				System.out.println("Failed.");
			System.out.println(s);
		}

		save();
	}

	public static void save(){
 
		File file = new File("text.txt");
		String content;
 
		try (FileOutputStream fop = new FileOutputStream(file)) {
 
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get content of courses
			for (Course c : courses) {
				content = c.getCode();
				content += ", " + c.getTitle();
				content += ", " + c.getCredit();
				content += ", " + c.getRequiredSections() + ";\n";

				byte[] contentInBytes = content.getBytes();
				fop.write(contentInBytes);
				fop.flush();
			}

			// get content of lecturers
			for (Lecturer l : lecturers) {
				content = l.getName();
				content += ", " + l.getSpecialization();
				content += ", " + l.getAvailability() + ";\n";

				byte[] contentInBytes = content.getBytes();
				fop.write(contentInBytes);
				fop.flush();
			}

			// get content of venues
			for (Venue v : venues) {
				content = v.getName();
				content += ", " + v.getName();
				content += ", " + v.getType();
				content += ", " + v.getCourses();
				content += ", " + v.getAvailability() + ";\n";

				byte[] contentInBytes = content.getBytes();
				fop.write(contentInBytes);
				fop.flush();
			}

			// get content of sections
			for (Section s : sections) {
				content = s.getDay() + ", " + s.getTime();
				content += ", " + s.getStudentLimit();
				content += ", " + s.getSectionNum();
				content += ", " + s.getCourse();
				content += ", " + s.getLecturer();
				content += ", " + s.getVenue() + ";\n";

				byte[] contentInBytes = content.getBytes();
				fop.write(contentInBytes);
				fop.flush();
			}
			
			fop.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void add(Object o){
		boolean exist = false;
		try{
			if(o instanceof Lecturer){
				Lecturer that = (Lecturer) o;
				for (Lecturer l : lecturers)
					if(l.equals(that)){
						exist = true;
						break;
					}
				if(!exist)
					lecturers.add(that);
			}
			else if (o instanceof Course){
				Course that = (Course) o;
				for (Course c : courses)
					if(c.equals(that)){
						exist = true;
						break;
					}
				if(!exist)
					courses.add(that);
			}
			else if (o instanceof Venue){
				Venue that = (Venue) o;
				for (Venue v : venues)
					if(v.equals(that)){
						exist = true;
						break;
					}
				if(!exist)
					venues.add(that);
			}
		}
		catch(Exception e){} // Action

	}

	public static void remove(Object o){
		try{
			if(o instanceof Lecturer){
				// for (Section s : sections) {
				// 	if(s.getLecturer().equals(o))
				// 		s.setLecturer(new Lecturer("TO BE DETERMINED"));
				// }
				// lecturers.remove(o);
				Lecturer lect = (Lecturer) o;
				lect.setName("TO BE DETERMINED");
			}
			else if (o instanceof Course) {
				ArrayList<Section> tempSections = new ArrayList<>();
				for (Section s : sections) {
					if(s.getCourse().equals(o)){
						tempSections.add(s);
					}
				}
				for (Section s : tempSections) {
					sections.remove(s);
				}
				courses.remove(o);
			}
			else if (o instanceof Venue) {
				// for (Section s : sections) {
				// 	if(s.getVenue().equals(o))
				// 		s.setVenue(new Venue("TO BE DETERMINED", ""));
				// }
				// venues.remove(o);
				Venue ven = (Venue) o;
				ven.setName("TO BE DETERMINED");
			}
		}
		catch(Exception e){} // Action
	}

	public static void generateSections(){
		for (Course course : courses)
			for (int i=0;i<course.getRequiredSections();++i)
				sections.add(new Section(course, new Lecturer("TBD"), i+1));
	}
}