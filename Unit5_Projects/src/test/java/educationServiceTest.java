/*
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class educationServiceTest {
//*********************************************************************
//********************* Step1 - Mock Annotations **********************
//*********************************************************************

    // Rule - the 'Mockito' rule
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    // Mock - the mock data we are analyzing - to be used to create and inject mocked instances easily, using what would normally be already created class services.
    @Mock
    private StudentDataObject studentDataObject;

    @Mock
    private InstructorDataObject instructorDataObject;

    @Mock
    private ClassDataObject classDataObject;

    // InjectMocks - the implementation of our mock interface
    // This injects our mocked fields above into the implementation below automatically.
    @InjectMocks
    private BusinessObjectImpl businessObjectImpl;

//*********************************************************************
//*************** Step2 - Writing a Unit Test using BDD ***************
//*********************************************************************

    @Test
    public void testStudentsWithMathClasses() {

        // So:
        // Given I have all my students,
        // When I have students with classes labeled as "math" for their subject,
        // Then I will provide the list of students in 'math' classes.

        // More specifically since we know there are 2 students in math classes:
        // Given I have all my (three) students,
        // When I have a student in a 'math' class,
        // Then I will display our students (two) in a 'math' class.

        // Hoping I understood this correctly.

        // I guess this is the 'Given' portion, what with them being our variables and put into our 'given' method.
        // I was going to not include Joy Ma, but I guess since we need to check all students (normally initialized elsewhere I think)
        // The 'Student' below would technically be a class Object created to house our students' information.
        Student joyMa = new Student("Joy Ma", "Spanish");
        Student julioHernandez = new Student("Julio Hernandez", "Algebra");
        student jennyJones = new Student("Jenny Jones", "Calculus");

        // We assign our students into a list for ease of convenience when testing below.
        // We get an error, of course, because our 'Student' object class has not been created, so I'm sure IntelliJ considers it as a nameless Object.
        // I would use an Array because I'm more used to them, but it does seem that lists have more uses (plus it's higher in hierarchy), and it wouldn't be as complicated so...
        List<Student> allStudents = Arrays.asList(joyMa, julioHernandez, jennyJones);

        // This specifically would be the 'Given' part of our 'Given' portion, due to the fact we utilize the given method.
        // If I understand correctly, this essentially accepts our mock instance and returns what we asked for - all the students above.
        // Though, I wasn't sure how to write the first half and looked up the instruction's example, I assume we're basically asking when given all students, return all students?
        given(studentDataObject.getAllStudents()).willReturn(allStudents);

        // This will be our 'When'.
        // We want to create a list of students with a class that correlates to the 'math' subject.
        // We will have to use our businessObjectImpl class due to the fact that this is where our students and their classes are together.
        // This would just make it easier to pull students with a class with 'math' - something that would normally be a variable already set when a class is created.
        // We use a list again to stay consistent with how we collected our students above, and it just makes sense.
        List<String> mathStudents = businessObjectImpl.getAllStudentsBySubject("math");

        // Then this makes this the "Then" portion where we actually attempt the test.
        // The first assert checks if the math students we pulled is equal to 2.
        // The second assert checks if the math students pulled include Julio and Jenny (since we know the two are in math classes).
        assertThat(mathStudents.size(), is(2));
        assertThat(mathStudents, hasItems(julioHernandez, jennyJones));
    }

//*********************************************************************
//******************** Step3 - Test Function Calls ********************
//*********************************************************************

    @Test
    public void testMarkCurrentClassesInactive() {
        // [GIVEN] I have all of my classes

        // Since it was done above, I also created the depicted classes in our instructions below to illustrate our test usage.
        ClassData geometrySu2022 = new ClassData("Geometry", "Summer 2022");
        ClassData environmentalScienceFa2022 = new ClassData("Environmental Science", "Fall 2022");
        ClassData comparativeLiteratureSp2022 = new ClassData("Comparative Literature", "Spring 2022");

        // Labeled 'ClassData' to avoid default Class calls for confusion.
        List<ClassData> allClasses = Arrays.asList(geometrySu2022, environmentalScienceFa2022, comparativeLiteratureSp2022);

        given(classDataObject.getAllClasses()).willReturn(allClasses);

        // [WHEN] I have marked a class as inactive
        clientBusinessObjectImpl.markCurrentClassesInactive();

        // [THEN] I will see that class marked as inactive

        // We verify if the class "classDataObject"s method "markInactive" runs for our geometry class.
        verify(classDataObject).markInactive(geometrySu2022);

        // We verify if the class "classDataObject"s method "markInactive" ran once for our geometry class.
        verify(classDataObject, Mockito.times(1)).markInactive(geometrySu2022);

        // We also need to verify that the above "markInactive" method doesn't run for the rest of our classes.
        // Though kind of unnecessary, as our "markInactive" method takes in a single Class Object as an argument,
        // the only way for the method to affect any other class is to have really faulty code that would pull in another class to mark inactive somehow.
        verify(classDataObject, Mockito.never()).markInactive(environmentalScienceFa2022);
        verify(classDataObject, Mockito.never()).markInactive(comparativeLiteratureSp2022);
    }

//*********************************************************************
//********************** Step4 - Argument Captor **********************
//*********************************************************************

    @Captor
    ArgumentCaptor<ClassData> classDataArgumentCaptor;

    @Test
    public void testMarkCurrentClassesInactive() {
        // [GIVEN] I have all of my classes

        ClassData geometrySu2022 = new ClassData("Geometry", "Summer 2022");
        ClassData environmentalScienceFa2022 = new ClassData("Environmental Science", "Fall 2022");
        ClassData comparativeLiteratureSp2022 = new ClassData("Comparative Literature", "Spring 2022");

        List<ClassData> allClasses = Arrays.asList(geometrySu2022, environmentalScienceFa2022, comparativeLiteratureSp2022);

        given(classDataObject.getAllClasses()).willReturn(allClasses);

        // [WHEN] I have marked a class as inactive
        clientBusinessObjectImpl.markCurrentClassesInactive();

        // [THEN] I will see that class marked as inactive

        // We verify if the class "classDataObject"s method "markInactive" runs for our geometry class via our argument captor.
        verify(classDataObject).markInactive(classDataArgumentCaptor.capture());

        // Then we can just have the argument captor's 'value', via .getValue(), is equal to our class data "geometrySu2022".
        assertEquals(geometrySu2022, classDataArgumentCaptor.getValue());
    }

//*********************************************************************
//************************ Step5 - Exploration ************************
//*********************************************************************

    @Captor
    ArgumentCaptor<Instructor> instructorArgumentCaptor;

    @Test
    public void testGetClassesByInstructor() {
        // [GIVEN] I have all of my instructors
        //                                        String name      String[] classes
        Instructor drDrakeRussel = new Instructor("Drake Russel", ["Calculus", "Algebra", "Statistics", "Geometry"]);
        Instructor drBethTrusdale = new Instructor("Jake Sanchez", ["Spanish", "History", "Geograpyhy"]);
        Instructor drJakeSanchez = new Instructor ("Beth Trusdale", ["Environmental Science", "Compartive Literature"]);

        List<Instructor> allInstructors = Arrays.asList(drDrakeRussel, drBethTrusdale, drJakeSanchez);

        // [WHEN] I have an instructor's classes I want to see
        instructorDataObjectImpl.instructorsClasses();

        // [THEN] I will see that classes taught by that instructor
        verify(instructorDataObject.getClassesByInstructor(instructorArgumentCaptor.capture()));
        assertEquals(drDrakeRussel, instructorArgumentCaptor.getValue());
    }

    @Test
    public void testGetStudentsInClass() {
        // [GIVEN] I have all of my classes
        //                                              String className     String classSemester
        ClassData geometrySu2022 =            new ClassData("Geometry",         "Summer 2022");
        ClassData environmentalScienceFa2022 = new ClassData("Environmental Science", "Fall 2022");
        ClassData comparativeLiteratureSp2022 = new ClassData("Comparative Literature", "Spring 2022");

        List<ClassData> allClasses = Arrays.asList(geometrySu2022, environmentalScienceFa2022, comparativeLiteratureSp2022);

        // [WHEN] I have a class whose students I want to see
        // We can reuse our @Captor for classes above.
        // We will say that the clientBusinessObjectImpl is where our method will come from.
        clientBusinessObjectImpl.getAllStudentsInClass();

        // [THEN] I will see all the students in that class
        verify(classDataObject.getStudentsFromClass(classDataArgumentCaptor.capture()));
        assertEquals(environmentalScienceFa2022, classDataArgumentCaptor.getValue());
    }
}
*/