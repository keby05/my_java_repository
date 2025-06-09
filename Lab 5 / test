import java.awt.*;
import java.awt.event.*;

public class LabActivity5QuizAppAWT extends Frame implements ActionListener {
    Label questionLabel, messageLabel;
    CheckboxGroup choiceGroup;
    Checkbox[] choices = new Checkbox[4];
    Button nextButton;

    String[] questions = {
        "What is the capital of France?",
        "Which language is used for Android development?",
        "What is the result of 2 + 2 * 2?"
    };

    String[][] options = {
        {"A. Paris", "B. Rome", "C. Berlin", "D. Madrid"},
        {"A. Swift", "B. Java", "C. Python", "D. Kotlin"},
        {"A. 6", "B. 8", "C. 4", "D. 10"}
    };

    int[] correctAnswers = {0, 1, 0}; // Indices of correct answers
    int currentQuestion = 0;
    int score = 0;

    public LabActivity5QuizAppAWT() {
        setTitle("Quiz App");
        setSize(500, 300);
        setLayout(new BorderLayout());
        setResizable(false);

        // Top label for question
        questionLabel = new Label("", Label.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(questionLabel, BorderLayout.NORTH);

        // Panel for choices
        Panel centerPanel = new Panel(new GridLayout(4, 1));
        choiceGroup = new CheckboxGroup();

        for (int i = 0; i < 4; i++) {
            choices[i] = new Checkbox("", choiceGroup, false);
            choices[i].setFont(new Font("Arial", Font.PLAIN, 14));
            choices[i].setForeground(Color.BLUE); // Customization
            centerPanel.add(choices[i]);
        }

        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel for button and message
        Panel bottomPanel = new Panel(new BorderLayout());
        messageLabel = new Label("", Label.CENTER);
        messageLabel.setForeground(Color.RED);
        bottomPanel.add(messageLabel, BorderLayout.NORTH);

        nextButton = new Button("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.addActionListener(this);
        bottomPanel.add(nextButton, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        loadQuestion();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void loadQuestion() {
        questionLabel.setText(questions[currentQuestion]);
        for (int i = 0; i < 4; i++) {
            choices[i].setLabel(options[currentQuestion][i]);
            choices[i].setEnabled(true);
        }
        if (choiceGroup.getSelectedCheckbox() != null) {
            choiceGroup.setSelectedCheckbox(null);
        }
        messageLabel.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        Checkbox selected = choiceGroup.getSelectedCheckbox();

        if (selected == null) {
            messageLabel.setText("Please select an answer.");
            return;
        }

        int selectedIndex = -1;
        for (int i = 0; i < 4; i++) {
            if (choices[i] == selected) {
                selectedIndex = i;
                break;
            }
        }

        if (selectedIndex == correctAnswers[currentQuestion]) {
            score++;
        }

        currentQuestion++;

        if (currentQuestion < questions.length) {
            loadQuestion();
        } else {
            questionLabel.setText("Quiz Completed! Your Score: " + score + " out of " + questions.length);
            for (int i = 0; i < 4; i++) {
                choices[i].setEnabled(false);
            }
            nextButton.setEnabled(false);
            messageLabel.setText("");
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new LabActivity5QuizAppAWT());
    }
}