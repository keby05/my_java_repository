import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LabActivity6SwingToDoList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoListViewer());
    }
}

class ToDoListViewer extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton addButton;
    private ToDoListForm formWindow;

    public ToDoListViewer() {
        setTitle("To-Do List Viewer");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new Object[]{"Task Name", "Task Description", "Status"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        addButton = new JButton("Add Task");
        addButton.addActionListener(e -> {
            if (formWindow == null || !formWindow.isShowing()) {
                formWindow = new ToDoListForm(this);
                formWindow.setVisible(true);
            }
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(addButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public void addTask(String name, String description, String status) {
        tableModel.addRow(new Object[]{name, description, status});
    }
}

class ToDoListForm extends JFrame {
    private JTextField taskNameField;
    private JTextArea taskDescArea;
    private JComboBox<String> statusBox;
    private JButton saveButton;

    private ToDoListViewer mainWindow;

    public ToDoListForm(ToDoListViewer viewer) {
        this.mainWindow = viewer;

        setTitle("Add New Task");
        setSize(400, 250);
        setLocationRelativeTo(viewer);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Task Name:"));
        taskNameField = new JTextField();
        formPanel.add(taskNameField);

        formPanel.add(new JLabel("Task Description:"));
        taskDescArea = new JTextArea(2, 20);
        taskDescArea.setLineWrap(true);
        taskDescArea.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(taskDescArea);
        formPanel.add(descScroll);

        formPanel.add(new JLabel("Status:"));
        String[] statuses = {"", "Not Started", "Ongoing", "Completed"};
        statusBox = new JComboBox<>(statuses);
        formPanel.add(statusBox);

        saveButton = new JButton("Save Task");
        saveButton.addActionListener(new SaveButtonListener());

        add(formPanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(saveButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = taskNameField.getText().trim();
            String desc = taskDescArea.getText().trim();
            String status = (String) statusBox.getSelectedItem();

            if (name.isEmpty() || status == null || status.isEmpty()) {
                JOptionPane.showMessageDialog(ToDoListForm.this,
                        "Please fill in Task Name and Status.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            mainWindow.addTask(name, desc, status);
            dispose();
        }
    }
}