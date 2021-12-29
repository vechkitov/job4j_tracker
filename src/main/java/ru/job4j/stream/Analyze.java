package ru.job4j.stream;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Подсчет статистики по аттестатам.
 */
public class Analyze {

    /**
     * Вычисляет общий средний балл.
     *
     * @param stream
     * @return
     */
    public static double averageScore(Stream<Pupil> stream) {
        return stream
                .flatMap(pupil -> pupil.getSubjects().stream())
                .mapToInt(Subject::getScore)
                .average()
                .orElse(0d);
    }

    /**
     * Вычисляет средний балл ученика по его предметам.
     *
     * @param stream
     * @return List<Tuple> - имя ученика и средний балл
     */
    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream
                .map(
                        pupil -> new Tuple(
                                pupil.getName(),
                                averageScore(Stream.of(pupil))
                        )
                ).collect(Collectors.toList());
    }

    /**
     * Вычисляет средний балл по всем предметам для каждого ученика.
     *
     * @param stream
     * @return List<Tuple> - название предмета и средний балл
     */
    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream
                .flatMap(pupil -> pupil.getSubjects().stream())
                .collect(
                        Collectors.groupingBy(
                                Subject::getName,
                                LinkedHashMap::new,
                                Collectors.averagingDouble(Subject::getScore)
                        )
                )
                .entrySet()
                .stream()
                .map(entry -> new Tuple(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * Возвращает лучшего ученика. Лучшим считается ученик с наибольшим баллом по всем предметам.
     *
     * @param stream
     * @return
     */
    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream
                .map(
                        pupil -> new Tuple(
                                pupil.getName(),
                                pupil.getSubjects().stream()
                                        .mapToDouble(Subject::getScore)
                                        .sum()
                        )
                )
                .max(Comparator.comparingDouble(Tuple::getScore))
                .orElse(null);
    }

    /**
     * Возвращает предмет с наибольшим баллом для всех студентов.
     *
     * @param stream
     * @return
     */
    public static Tuple bestSubject(Stream<Pupil> stream) {
        return stream
                .flatMap(pupil -> pupil.getSubjects().stream())
                .collect(
                        Collectors.groupingBy(
                                Subject::getName,
                                Collectors.summingDouble(Subject::getScore)
                        )
                )
                .entrySet()
                .stream()
                .map(entry -> new Tuple(entry.getKey(), entry.getValue()))
                .max(Comparator.comparingDouble(Tuple::getScore))
                .orElse(null);
    }
}
