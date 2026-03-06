package com.school.quiz.controller;

import com.school.quiz.constant.AppConstants;
import com.school.quiz.entity.Question;
import com.school.quiz.enums.Subject;
import com.school.quiz.service.QuizService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/")
    public String index() {
        return "redirect:" + AppConstants.DASHBOARD_ROUTE;
    }

    @GetMapping(AppConstants.DASHBOARD_ROUTE)
    public String dashboard(Model model, HttpSession session) {
        session.setAttribute("isQuizActive", false);
        return "dashboard";
    }

    /* ========================================================
     *  DÉMARRER UN QUIZ
     * ======================================================== */

    @GetMapping(AppConstants.QUIZ_ROUTE)
    public String startQuiz(@RequestParam Subject subject, HttpSession session) {
        List<Question> questions = quizService.getQuestionsBySubject(subject);

        if (questions == null || questions.isEmpty()) {
            return "redirect:" + AppConstants.DASHBOARD_ROUTE;
        }

        session.setAttribute("questions",            questions);
        session.setAttribute("currentQuestionIndex", 0);
        session.setAttribute("score",                0);
        session.setAttribute("subject",              subject);
        session.setAttribute("userAnswers",          new HashMap<Integer, String>());
        session.setAttribute("isQuizActive",         true);

        return "redirect:/quiz/question";
    }

    /* ========================================================
     *  AFFICHER LA QUESTION COURANTE
     * ======================================================== */

    @GetMapping("/quiz/question")
    public String showQuestion(Model model, HttpSession session) {
        List<Question> questions = getQuestionsFromSession(session);
        Integer index = (Integer) session.getAttribute("currentQuestionIndex");

        if (questions == null || index == null) {
            return "redirect:" + AppConstants.DASHBOARD_ROUTE;
        }

        // Fin du quiz → page résultats

        if (index >= questions.size()) {
            session.setAttribute("isQuizActive", false);
            return "redirect:" + AppConstants.RESULT_ROUTE;
        }

        Question question = questions.get(index);
        model.addAttribute("question",        question);
        model.addAttribute("currentIndex",    index + 1);
        model.addAttribute("totalQuestions",  questions.size());
        model.addAttribute("subject",         session.getAttribute("subject"));

        // Si la question a déjà été répondue (retour en arrière), afficher le feedback

        @SuppressWarnings("unchecked")
        Map<Integer, String> userAnswers =
                (Map<Integer, String>) session.getAttribute("userAnswers");

        if (userAnswers != null && userAnswers.containsKey(index)) {
            String previousAnswer = userAnswers.get(index);
            boolean wasCorrect    = quizService.checkAnswer(question.getId(), previousAnswer);
            model.addAttribute("showFeedback",   true);
            model.addAttribute("userAnswer",     previousAnswer);
            model.addAttribute("correctAnswer",  question.getCorrectAnswer());
            model.addAttribute("isCorrect",      wasCorrect);
        } else {
            model.addAttribute("showFeedback", false);
        }

        return "quiz";
    }

    /* ========================================================
     *  SOUMETTRE UNE RÉPONSE
     * ======================================================== */

    @PostMapping("/quiz/submit")
    public String submitAnswer(@RequestParam String answer,
                               HttpSession session,
                               Model model) {

        List<Question> questions = getQuestionsFromSession(session);
        Integer index            = (Integer) session.getAttribute("currentQuestionIndex");
        Integer score            = (Integer) session.getAttribute("score");

        if (questions == null || index == null || index >= questions.size()) {
            return "redirect:" + AppConstants.DASHBOARD_ROUTE;
        }

        @SuppressWarnings("unchecked")
        Map<Integer, String> userAnswers =
                (Map<Integer, String>) session.getAttribute("userAnswers");
        if (userAnswers == null) {
            userAnswers = new HashMap<>();
        }
        if (score == null) score = 0;

        Question currentQuestion = questions.get(index);

        // Cas TIMEOUT : temps écoulé → réponse comptée comme fausse, on affiche la bonne réponse

        boolean isTimeout = "TIMEOUT".equals(answer);
        boolean isCorrect = !isTimeout && quizService.checkAnswer(currentQuestion.getId(), answer);

        // Ajustement du score en cas de modification d'une réponse déjà donnée

        String  previousAnswer       = userAnswers.get(index);
        boolean wasPreviouslyCorrect = (previousAnswer != null && !"TIMEOUT".equals(previousAnswer))
                && quizService.checkAnswer(currentQuestion.getId(), previousAnswer);

        if (isCorrect && !wasPreviouslyCorrect) {
            score++;
        } else if (!isCorrect && wasPreviouslyCorrect) {
            score--;
        }

        // Sauvegarder la réponse et le score

        userAnswers.put(index, answer);
        session.setAttribute("userAnswers", userAnswers);
        session.setAttribute("score",       score);
        // NE PAS incrémenter l'index ici – c'est /quiz/next qui le fait

        // Préparer l'affichage du feedback

        model.addAttribute("question",       currentQuestion);
        model.addAttribute("userAnswer",     isTimeout ? "" : answer);
        model.addAttribute("correctAnswer",  currentQuestion.getCorrectAnswer());
        model.addAttribute("isCorrect",      isCorrect);
        model.addAttribute("isTimeout",      isTimeout);
        model.addAttribute("currentIndex",   index + 1);
        model.addAttribute("totalQuestions", questions.size());
        model.addAttribute("subject",        session.getAttribute("subject"));
        model.addAttribute("showFeedback",   true);

        return "quiz";
    }

    /* ========================================================
     *  QUESTION SUIVANTE
     * ======================================================== */

    @GetMapping("/quiz/next")
    public String nextQuestion(HttpSession session) {
        List<Question> questions = getQuestionsFromSession(session);
        Integer index            = (Integer) session.getAttribute("currentQuestionIndex");

        if (questions == null || index == null) {
            return "redirect:" + AppConstants.DASHBOARD_ROUTE;
        }

        int nextIndex = index + 1;
        session.setAttribute("currentQuestionIndex", nextIndex);

        if (nextIndex >= questions.size()) {
            session.setAttribute("isQuizActive", false);
            return "redirect:" + AppConstants.RESULT_ROUTE;
        }

        return "redirect:/quiz/question";
    }

    /* ========================================================
     *  QUESTION PRÉCÉDENTE
     * ======================================================== */

    @GetMapping("/quiz/previous")
    public String previousQuestion(HttpSession session) {
        Integer index = (Integer) session.getAttribute("currentQuestionIndex");

        if (index == null || index <= 0) {
            return "redirect:/quiz/question";
        }

        session.setAttribute("currentQuestionIndex", index - 1);
        return "redirect:/quiz/question";
    }

    /* ========================================================
     *  PAGE RÉSULTATS
     * ======================================================== */

    @GetMapping(AppConstants.RESULT_ROUTE)
    public String showResult(Model model, HttpSession session) {
        List<?> questions = getQuestionsFromSession(session);
        Integer score     = (Integer) session.getAttribute("score");

        // Protection contre une session expirée ou accès direct
        if (questions == null || score == null) {
            return "redirect:" + AppConstants.DASHBOARD_ROUTE;
        }

        model.addAttribute("score", score);
        model.addAttribute("total", questions.size());
        session.setAttribute("isQuizActive", false);
        return "result";
    }

    /* ========================================================
     *  UTILITAIRE
     * ======================================================== */
    
    @SuppressWarnings("unchecked")
    private List<Question> getQuestionsFromSession(HttpSession session) {
        return (List<Question>) session.getAttribute("questions");
    }
}
