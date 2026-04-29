package com.school.quiz.repository;

import com.school.quiz.entity.Question;
import com.school.quiz.enums.Subject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class QuestionRepository {

    private final List<Question> questions;

    public QuestionRepository() {
        this.questions = Collections.unmodifiableList(loadQuestions());
    }

    public List<Question> findBySubject(Subject subject) {
        return questions.stream()
                .filter(q -> q.getSubject() == subject)
                .collect(Collectors.toList());
    }

    public Optional<Question> findById(Long id) {
        if (id == null) return Optional.empty();
        return questions.stream().filter(q -> id.equals(q.getId())).findFirst();
    }

    private List<Question> loadQuestions() {
        List<Question> list = new ArrayList<>();
        long id = 1L;

        // ── MATHS (20 questions) ──────────────────────────────────────────
        list.add(new Question(id++, "Combien font 5 + 7 ?",                    "10",    "11",    "12",     "13",     "C", Subject.MATHS));
        list.add(new Question(id++, "Quelle est la racine carrée de 81 ?",     "7",     "8",     "9",      "10",     "C", Subject.MATHS));
        list.add(new Question(id++, "Combien font 12 × 12 ?",                  "124",   "144",   "164",    "184",    "B", Subject.MATHS));
        list.add(new Question(id++, "Quelle est la valeur de Pi (2 déc.) ?",   "3.12",  "3.14",  "3.16",   "3.18",   "B", Subject.MATHS));
        list.add(new Question(id++, "Combien font 100 ÷ 4 ?",                  "20",    "25",    "30",     "35",     "B", Subject.MATHS));
        list.add(new Question(id++, "Quel est le résultat de 7 × 8 ?",         "54",    "56",    "58",     "60",     "B", Subject.MATHS));
        list.add(new Question(id++, "Combien de côtés a un hexagone ?",        "5",     "6",     "7",      "8",      "B", Subject.MATHS));
        list.add(new Question(id++, "Complément à 100 de 37 ?",                "53",    "63",    "73",     "43",     "B", Subject.MATHS));
        list.add(new Question(id++, "Combien font 15 % de 200 ?",              "20",    "25",    "30",     "35",     "C", Subject.MATHS));
        list.add(new Question(id++, "Quel est le carré de 13 ?",               "159",   "169",   "179",    "189",    "B", Subject.MATHS));
        list.add(new Question(id++, "Combien font 3 × 9 ?",                    "24",    "27",    "30",     "33",     "B", Subject.MATHS));
        list.add(new Question(id++, "Combien font 50 + 25 ?",                  "70",    "75",    "80",     "85",     "B", Subject.MATHS));
        list.add(new Question(id++, "Combien font 20 % de 50 ?",               "10",    "12",    "15",     "20",     "A", Subject.MATHS));
        list.add(new Question(id++, "Quel est le carré de 8 ?",                "56",    "64",    "72",     "80",     "B", Subject.MATHS));
        list.add(new Question(id++, "Combien font 1000 ÷ 5 ?",                 "100",   "200",   "250",    "500",    "B", Subject.MATHS));
        list.add(new Question(id++, "Combien font 4 × 6 ?",                    "20",    "22",    "24",     "28",     "C", Subject.MATHS));
        list.add(new Question(id++, "Périmètre d'un carré de côté 5 ?",        "15",    "20",    "25",     "30",     "B", Subject.MATHS));
        list.add(new Question(id++, "Combien de côtés a un triangle ?",        "3",     "4",     "5",      "6",      "A", Subject.MATHS));
        list.add(new Question(id++, "Combien font 6 + 6 + 6 ?",                "16",    "17",    "18",     "20",     "C", Subject.MATHS));
        list.add(new Question(id++, "La moitié de 48 est ?",                   "24",    "26",    "28",     "22",     "A", Subject.MATHS));

        // ── ANGLAIS (20 questions) ────────────────────────────────────────
        list.add(new Question(id++, "Comment dit-on 'Pomme' en anglais ?",     "Grape", "Apple",    "Peach",      "Banana",    "B", Subject.ANGLAIS));
        list.add(new Question(id++, "Quel est le passé de 'Go' ?",             "Went",  "Gone",     "Goes",       "Going",     "A", Subject.ANGLAIS));
        list.add(new Question(id++, "Traduisez 'Bonjour' :",                   "Goodbye","Hello",   "Please",     "Thanks",    "B", Subject.ANGLAIS));
        list.add(new Question(id++, "Quel est le pluriel de 'Child' ?",        "Childs","Children", "Childrens",  "Childes",   "B", Subject.ANGLAIS));
        list.add(new Question(id++, "Comment dit-on 'Bleu' ?",                 "Red",   "Green",    "Blue",       "Yellow",    "C", Subject.ANGLAIS));
        list.add(new Question(id++, "Contraire de 'Fast' ?",                   "Quick", "Slow",     "Rapid",      "Early",     "B", Subject.ANGLAIS));
        list.add(new Question(id++, "Traduisez 'Livre' :",                     "Pen",   "Book",     "Table",      "Chair",     "B", Subject.ANGLAIS));
        list.add(new Question(id++, "Comment dit-on 'Merci' ?",                "Please","Sorry",    "Thank you",  "Welcome",   "C", Subject.ANGLAIS));
        list.add(new Question(id++, "Synonyme de 'Happy' ?",                   "Sad",   "Angry",    "Glad",       "Tired",     "C", Subject.ANGLAIS));
        list.add(new Question(id++, "Traduisez 'Chat' :",                      "Dog",   "Bird",     "Cat",        "Fish",      "C", Subject.ANGLAIS));
        list.add(new Question(id++, "Comment dit-on 'Maison' ?",               "Car",   "House",    "School",     "Garden",    "B", Subject.ANGLAIS));
        list.add(new Question(id++, "Comment dit-on 'Manger' ?",               "Eat",   "Sleep",    "Run",        "Play",      "A", Subject.ANGLAIS));
        list.add(new Question(id++, "Comment dit-on 'Chien' ?",                "Cat",   "Dog",      "Bird",       "Fish",      "B", Subject.ANGLAIS));
        list.add(new Question(id++, "Contraire de 'Big' ?",                    "Tall",  "Small",    "Short",      "Long",      "B", Subject.ANGLAIS));
        list.add(new Question(id++, "Pluriel de 'Man' ?",                      "Mans",  "Manes",    "Men",        "Mens",      "C", Subject.ANGLAIS));
        list.add(new Question(id++, "Passé de 'See' ?",                        "Saw",   "Seen",     "Seed",       "Sawn",      "A", Subject.ANGLAIS));
        list.add(new Question(id++, "Comment dit-on 'École' ?",                "Office","Shop",     "School",     "Library",   "C", Subject.ANGLAIS));
        list.add(new Question(id++, "Traduisez 'Rouge' :",                     "Blue",  "Green",    "Black",      "Red",       "D", Subject.ANGLAIS));
        list.add(new Question(id++, "Comment dit-on 'Eau' ?",                  "Milk",  "Juice",    "Water",      "Tea",       "C", Subject.ANGLAIS));
        list.add(new Question(id++, "Passé de 'Run' ?",                        "Raned", "Ran",      "Runned",     "Running",   "B", Subject.ANGLAIS));

        // ── CHIMIE (20 questions) ─────────────────────────────────────────
        list.add(new Question(id++, "Symbole chimique de l'eau ?",             "H2O",   "CO2",      "O2",         "NaCl",      "A", Subject.CHIMIE));
        list.add(new Question(id++, "Symbole de l'Or ?",                       "Ag",    "Fe",       "Au",         "Pb",        "C", Subject.CHIMIE));
        list.add(new Question(id++, "Gaz principalement respiré ?",            "Azote", "Oxygène",  "Hydrogène",  "Hélium",    "B", Subject.CHIMIE));
        list.add(new Question(id++, "pH d'une solution neutre ?",              "0",     "7",        "14",         "1",         "B", Subject.CHIMIE));
        list.add(new Question(id++, "Symbole du Fer ?",                        "Fe",    "F",        "Ir",         "Be",        "A", Subject.CHIMIE));
        list.add(new Question(id++, "Symbole du Carbone ?",                    "Ca",    "C",        "Co",         "Cr",        "B", Subject.CHIMIE));
        list.add(new Question(id++, "Symbole de l'Oxygène ?",                  "Ox",    "O",        "O2",         "Oy",        "B", Subject.CHIMIE));
        list.add(new Question(id++, "Symbole du Sodium ?",                     "S",     "So",       "Na",         "Sd",        "C", Subject.CHIMIE));
        list.add(new Question(id++, "Symbole de l'Argent ?",                   "Ar",    "Ag",       "Au",         "Al",        "B", Subject.CHIMIE));
        list.add(new Question(id++, "Symbole de l'Azote ?",                    "Az",    "A",        "N",          "Ni",        "C", Subject.CHIMIE));
        list.add(new Question(id++, "Symbole de l'Hydrogène ?",                "H",     "He",       "Hy",         "Hd",        "A", Subject.CHIMIE));
        list.add(new Question(id++, "Symbole du Potassium ?",                  "Po",    "K",        "Pt",         "Pu",        "B", Subject.CHIMIE));
        list.add(new Question(id++, "Symbole du Calcium ?",                    "Ca",    "C",        "Cm",         "Cs",        "A", Subject.CHIMIE));
        list.add(new Question(id++, "Symbole du Cuivre ?",                     "Cp",    "Cv",       "Cu",         "Co",        "C", Subject.CHIMIE));
        list.add(new Question(id++, "Formule du sel de table ?",               "KCl",   "NaCl",     "MgCl2",      "CaCl2",     "B", Subject.CHIMIE));
        list.add(new Question(id++, "Symbole du Chlore ?",                     "Ch",    "Cr",       "Cl",         "Ce",        "C", Subject.CHIMIE));
        list.add(new Question(id++, "Formule du dioxyde de carbone ?",         "CO",    "C2O",      "CO2",        "CO3",       "C", Subject.CHIMIE));
        list.add(new Question(id++, "Nombre de protons de l'hydrogène ?",      "1",     "2",        "3",          "0",         "A", Subject.CHIMIE));
        list.add(new Question(id++, "Symbole du Plomb ?",                      "Pl",    "Pb",       "Pd",         "Po",        "B", Subject.CHIMIE));
        list.add(new Question(id++, "Symbole de l'Hélium ?",                   "H",     "Hl",       "He",         "Hi",        "C", Subject.CHIMIE));

        return list;
    }
}
