package com.school.quiz.config;

import com.school.quiz.entity.Question;
import com.school.quiz.enums.Subject;
import com.school.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired private QuestionRepository questionRepository;

    @Override
    public void run(String... args) throws Exception {

        // --- Questions : chargées uniquement si la table est vide ---
        
        if (questionRepository.count() > 0) {
            return;
        }

        // MATHS (10 questions)

        questionRepository.saveAll(Arrays.asList(
            new Question(null, "Combien font 5 + 7 ?",               "10",  "11",  "12",  "13",  "C", Subject.MATHS),
            new Question(null, "Quelle est la racine carrée de 81 ?", "7",   "8",   "9",   "10",  "C", Subject.MATHS),
            new Question(null, "Combien font 12 x 12 ?",             "124", "144", "164", "184", "B", Subject.MATHS),
            new Question(null, "Quelle est la valeur de Pi (2 déc)?", "3.12","3.14","3.16","3.18","B", Subject.MATHS),
            new Question(null, "Combien font 100 / 4 ?",             "20",  "25",  "30",  "35",  "B", Subject.MATHS),
            new Question(null, "Quel est le résultat de 7 x 8 ?",    "54",  "56",  "58",  "60",  "B", Subject.MATHS),
            new Question(null, "Combien de côtés a un hexagone ?",   "5",   "6",   "7",   "8",   "B", Subject.MATHS),
            new Question(null, "Complément à 100 de 37 ?",           "53",  "63",  "73",  "43",  "B", Subject.MATHS),
            new Question(null, "Combien font 15% de 200 ?",          "20",  "25",  "30",  "35",  "C", Subject.MATHS),
            new Question(null, "Quel est le carré de 13 ?",          "159", "169", "179", "189", "B", Subject.MATHS)
        ));

        // ANGLAIS (10 questions)

        questionRepository.saveAll(Arrays.asList(
            new Question(null, "Comment dit-on 'Pomme' en anglais ?", "Grape", "Apple",    "Peach",    "Banana",   "B", Subject.ANGLAIS),
            new Question(null, "Quel est le passé de 'Go' ?",         "Went",  "Gone",     "Goes",     "Going",    "A", Subject.ANGLAIS),
            new Question(null, "Traduisez 'Bonjour' :",               "Goodbye","Hello",   "Please",   "Thanks",   "B", Subject.ANGLAIS),
            new Question(null, "Quel est le pluriel de 'Child' ?",    "Childs","Children", "Childrens","Childes",  "B", Subject.ANGLAIS),
            new Question(null, "Comment dit-on 'Bleu' ?",             "Red",   "Green",    "Blue",     "Yellow",   "C", Subject.ANGLAIS),
            new Question(null, "Contraire de 'Fast' ?",               "Quick", "Slow",     "Rapid",    "Early",    "B", Subject.ANGLAIS),
            new Question(null, "Traduisez 'Livre' :",                 "Pen",   "Book",     "Table",    "Chair",    "B", Subject.ANGLAIS),
            new Question(null, "Comment dit-on 'Merci' ?",            "Please","Sorry",    "Thank you","Welcome",  "C", Subject.ANGLAIS),
            new Question(null, "Synonyme de 'Happy' ?",               "Sad",   "Angry",    "Glad",     "Tired",    "C", Subject.ANGLAIS),
            new Question(null, "Traduisez 'Chat' :",                  "Dog",   "Bird",     "Cat",      "Fish",     "C", Subject.ANGLAIS)
        ));

        // CHIMIE (10 questions)

        questionRepository.saveAll(Arrays.asList(
            new Question(null, "Symbole chimique de l'eau ?",         "H2O",  "CO2",  "O2",  "NaCl", "A", Subject.CHIMIE),
            new Question(null, "Symbole de l'Or ?",                   "Ag",   "Fe",   "Au",  "Pb",   "C", Subject.CHIMIE),
            new Question(null, "Gaz respiré principalement ?",        "Azote","Oxygène","Hydrogène","Hélium","B", Subject.CHIMIE),
            new Question(null, "pH d'une solution neutre ?",          "0",    "7",    "14",  "1",    "B", Subject.CHIMIE),
            new Question(null, "Symbole du Fer ?",                    "Fe",   "F",    "Ir",  "Be",   "A", Subject.CHIMIE),
            new Question(null, "Symbole du Carbone ?",                "Ca",   "C",    "Co",  "Cr",   "B", Subject.CHIMIE),
            new Question(null, "Symbole de l'Oxygène ?",              "Ox",   "O",    "O2",  "Oy",   "B", Subject.CHIMIE),
            new Question(null, "Symbole du Sodium ?",                 "S",    "So",   "Na",  "Sd",   "C", Subject.CHIMIE),
            new Question(null, "Symbole de l'Argent ?",               "Ar",   "Ag",   "Au",  "Al",   "B", Subject.CHIMIE),
            new Question(null, "Symbole de l'Azote ?",                "Az",   "A",    "N",   "Ni",   "C", Subject.CHIMIE)
        ));
    }
}
