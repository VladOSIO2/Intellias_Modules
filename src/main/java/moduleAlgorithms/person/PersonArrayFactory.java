package moduleAlgorithms.person;

public class PersonArrayFactory {
    public static Person[] getPersons(int count) {
        if (count <= 1) {
            throw new IllegalArgumentException(
                    "cannot create " + Person.class + " array size of " + count);
        }

        Person[] people = new Person[count];
        for (int i = 0; i < people.length; i++) {
            people[i] = RandomPersonFactory.nextPerson();
        }
        return people;
    }
}
