package moduleAlgorithms;

import moduleAlgorithms.person.Person;
import moduleAlgorithms.person.PersonArrayFactory;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static final int PERSON_COUNT = 1_000_000;

    public static void main(String[] args) {
        Comparator<Person> ageComparator = Comparator.comparing(Person::getAge);
        Comparator<Person> heightComparator = Comparator.comparing(Person::getWeight);
        Comparator<Person> weightComparator = Comparator.comparing(Person::getHeight);

        //generating people array with O(n) time complexity
        long timeBuff = System.currentTimeMillis();
        Person[] people = PersonArrayFactory.getPersons(PERSON_COUNT);
        System.out.println("Array generating: "
                + (System.currentTimeMillis() - timeBuff) + "ms\n");

        //Making a copy of an array, so in multiple
        //sort tests the same array will be used
        Person[] copiedPeople = Arrays.copyOf(people, people.length);

        //for sorting, Arrays.sort() will be used.
        //It uses TimSort algorithm by default
        //with O(n) best case & O(n*log(n)) worst & average case performance
        //TimSort is also a stable sorting algorithm
        timeBuff = System.currentTimeMillis();
        Arrays.sort(copiedPeople, ageComparator);
        System.out.println("TimSort (Arrays.sort()) sorting by age: "
                + (System.currentTimeMillis() - timeBuff) + "ms");

        //Answering on the additional question:
        //  "is it possible to ensure linear complexity of sorting?"
        //Answer: for TimSort - yes, in best case
        //(when an array is already sorted)
        //we can ensure linear (O(n)) complexity
        timeBuff = System.currentTimeMillis();
        Arrays.sort(copiedPeople, ageComparator);
        System.out.println("TimSort (Arrays.sort()) sorting by age (for an already sorted array): "
                + (System.currentTimeMillis() - timeBuff) + "ms");

        //sorting the initial array by height
        copiedPeople = Arrays.copyOf(people, people.length);
        timeBuff = System.currentTimeMillis();
        Arrays.sort(copiedPeople, heightComparator);
        System.out.println("TimSort (Arrays.sort()) sorting by height: "
                + (System.currentTimeMillis() - timeBuff) + "ms");

        //sorting the initial array by weight
        copiedPeople = Arrays.copyOf(people, people.length);
        timeBuff = System.currentTimeMillis();
        Arrays.sort(copiedPeople, weightComparator);
        System.out.println("TimSort (Arrays.sort()) sorting by weight: "
                + (System.currentTimeMillis() - timeBuff) + "ms");

        //counting the persons with the same weight, but different height
        //this will be easier with a sorted by weight and then height people
        Comparator<Person> weightHeightComparator = Comparator
                .comparing(Person::getWeight)
                .thenComparing(Person::getHeight);

        copiedPeople = Arrays.copyOf(people, people.length);
        timeBuff = System.currentTimeMillis();
        Arrays.sort(copiedPeople, weightHeightComparator);
        System.out.println("TimSort (Arrays.sort()) sorting by weight and then height: "
                + (System.currentTimeMillis() - timeBuff) + "ms");

        System.out.println("\nPeople with same weight and different name count: " +
                countSameWeightDiffHeightPeople(copiedPeople));
    }

    //Time complexity of this algorithm is O(n) for a properly sorted array
    //It runs a single cycle checking Person's weight & height
    //and making some comparisons with previous Person's weight & height
    //with incrementing the counter if the condition is met
    //on a certain iteration
    private static int countSameWeightDiffHeightPeople(Person[] people) {
        int count = 0;
        int curWeight = people[0].getWeight();
        int curHeight = people[0].getHeight();
        boolean foundForThisWeight = false;

        for (int i = 1; i < people.length; i++) {
            if (curWeight == people[i].getWeight()) {
                if (curHeight != people[i].getHeight()) {
                    count++; //new height for same weight found
                    curHeight = people[i].getHeight();
                    foundForThisWeight = true;
                } //else same height for same weight found
            } else {
                //new weight found - looking for condition in next iteration
                curWeight = people[i].getWeight();
                curHeight = people[i].getHeight();
                if (foundForThisWeight) {
                    foundForThisWeight = false;
                    count++; //counting the first person that meets the counting condition too
                }
            }
        }

        if (foundForThisWeight) {
            count++; //if person was found on last iteration
        }
        return count;
    }
}