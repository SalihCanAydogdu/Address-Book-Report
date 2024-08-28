import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PersonService {

	public static String FILE_NAME = "persons.txt";
	private Person[] persons;
	int size = 0;
	int currentIndex = -1;
	int currentId = 1;

	public PersonService() throws IOException {
		this.persons = readAllPerson();
		this.currentIndex = this.persons[0] != null ? 0 : -1;
	}

	public Person[] readAllPerson() throws IOException {
		Person[] persons = new Person[200];
		try {
			int currentIndex = -1;
			
			List<String> lines = Files.readAllLines(Path.of(FILE_NAME));
			for (String line : lines) {
				size++;
				persons[++currentIndex] = Person.toPerson(line);
				currentId = Math.max(currentId, persons[currentId - 1].getId()) + 1;
			}
		}catch (Exception e) {
		}
		return persons;
	}
	
	public void writePersonToFile(Person person) throws FileNotFoundException {
		person.setId(currentId++);
		PrintWriter writer = new PrintWriter(new FileOutputStream(new File("persons.txt"), true)); 
		writer.println(person);
		writer.flush();
		writer.close();
	}
	
	public Person getFirst() {
		if(currentIndex != -1) {
			currentIndex = 0;
			return persons[currentIndex];
		}else {
			return null;
		}
	}
	
	public Person getLast() {
		if(size != 0) {
			currentIndex = size - 1;
			return persons[currentIndex];
		}else {
			return null;
		}
	}
	
	
	public Person next() {
		if(size != 0) {
			if (currentIndex != size -1) {
				currentIndex++;
				return persons[currentIndex];
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	public Person previous() {
		if(size != 0) {
			if (currentIndex != 0) {
				currentIndex--;
				return persons[currentIndex];
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	public Person getCurrent() {
		return currentIndex != -1 ? persons[currentIndex] : null;
	}
	
	

	public void addPerson(Person person) throws FileNotFoundException {
		writePersonToFile(person);
		persons[size++] = person;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}
	
	public Person search(int id) {
		for (int i = 0; i < size; i++) {
			if(persons[i].getId() == id) {
				currentIndex = i;
				return persons[i];
			}
		}
		return null;
	}
	
	public Person update(int id, Person person) {
		person.setId(id);
		for (int i = 0; i < size; i++) {
			if(persons[i].getId() == id) {
				currentIndex = i;
				persons[i] = person;
				return persons[i];
			}
		}
		return null;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	

}
