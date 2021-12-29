package ru.job4j.stream;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class AnalyzeTest {

    @Test
    public void whenEmptyListOfPupilThenAverageScoreEqualsZero() {
        List<Pupil> pupils = List.of();
        double average = Analyze.averageScore(pupils.stream());
        assertThat(average, is(0d));
    }

    @Test
    public void whenEmptyListOfSubjectThenAverageScoreEqualsZero() {
        List<Subject> subjects = List.of();
        double average = Analyze.averageScore(
                List.of(
                        new Pupil("Ivanov", subjects)
                ).stream()
        );
        assertThat(average, is(0d));
    }

    @Test
    public void whenSinglePupil() {
        double average = Analyze.averageScore(
                List.of(
                        new Pupil("Ivanov", List.of(new Subject("Math", 100)))
                ).stream()
        );
        assertThat(average, is(100d));
    }

    @Test
    public void whenPupilAverage() {
        double average = Analyze.averageScore(
                List.of(
                        new Pupil("Ivanov", List.of(new Subject("Math", 100))),
                        new Pupil("Petrov", List.of(new Subject("Math", 60)))
                ).stream()
        );
        assertThat(average, is(80d));
    }

    @Test
    public void whenListOfPupilAverage() {
        List<Tuple> average = Analyze.averageScoreBySubject(
                List.of(
                        new Pupil("Ivanov",
                                List.of(
                                        new Subject("Math", 100),
                                        new Subject("Lang", 100)
                                )
                        ),
                        new Pupil("Petrov",
                                List.of(
                                        new Subject("Math", 60),
                                        new Subject("Lang", 60)
                                )
                        )
                ).stream()
        );
        assertThat(average, is(List.of(
                new Tuple("Ivanov", 100d),
                new Tuple("Petrov", 60d)
        )));
    }

    @Test
    public void whenListOfSubjectAverage() {
        List<Tuple> average = Analyze.averageScoreByPupil(
                List.of(
                        new Pupil("Ivanov",
                                List.of(
                                        new Subject("Math", 100),
                                        new Subject("Lang", 100),
                                        new Subject("Philosophy", 100)
                                )
                        ),
                        new Pupil("Petrov",
                                List.of(
                                        new Subject("Math", 60),
                                        new Subject("Lang", 60),
                                        new Subject("Philosophy", 60)
                                )
                        )
                ).stream()
        );
        assertThat(average, is(List.of(
                new Tuple("Math", 80d),
                new Tuple("Lang", 80d),
                new Tuple("Philosophy", 80d)
        )));
    }

    @Test
    public void whenBestPupil() {
        Tuple best = Analyze.bestStudent(
                List.of(
                        new Pupil("Ivanov",
                                List.of(
                                        new Subject("Math", 100),
                                        new Subject("Lang", 100)
                                )
                        ),
                        new Pupil("Petrov",
                                List.of(
                                        new Subject("Math", 60),
                                        new Subject("Lang", 60)
                                )
                        )
                ).stream()
        );
        assertThat(best, is(new Tuple("Ivanov", 200d)));
    }

    @Test
    public void whenEmptyListOfPupilThenTheBestStudentInNull() {
        List<Pupil> pupils = List.of();
        assertNull(Analyze.bestStudent(pupils.stream()));
    }

    @Test
    public void whenBestSubject() {
        Tuple best = Analyze.bestSubject(
                List.of(
                        new Pupil("Ivanov",
                                List.of(
                                        new Subject("Math", 100),
                                        new Subject("Lang", 40)
                                )
                        ),
                        new Pupil("Petrov",
                                List.of(
                                        new Subject("Math", 60),
                                        new Subject("Lang", 60)
                                )
                        )
                ).stream()
        );
        assertThat(best, is(new Tuple("Math", 160d)));
    }

    @Test
    public void whenEmptyListOfPupilThenTheBestSubjectInNull() {
        List<Pupil> pupils = List.of();
        assertNull(Analyze.bestSubject(pupils.stream()));
    }
}
