import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.GridLayout;

public class ClassListSystem {

    private static StudentManager studentmanager = new StudentManager();
    static ArrayList<String> studentfile = new ArrayList<>();
    static HashMap<String, String> index = new HashMap<String, String>(50);

    static Scanner scan = new Scanner(System.in);
    static String line = "";
    static int hashid = 0;

    public static void main(String[] args) {
        // Instantiating the Frame for Home Menu
        class Frame extends JFrame {
            public Frame() {
                setLayout(new FlowLayout(FlowLayout.LEFT));
                this.setSize(600, 600);
                this.getContentPane().setBackground(new Color(0x222222));
                setLocationRelativeTo(null);
                this.setResizable(false);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }
        Frame home = new Frame();
        home.setTitle("Student Manager");

        // Creating the Menu and Menu Items
        JMenu menu;
        JMenuItem op1, op2, op3, op4, op5, op6, op7, op8;
        JMenuBar mb = new JMenuBar();
        menu = new JMenu("Commands Menu");
        op1 = new JMenuItem("Enter info about a new Student");
        op2 = new JMenuItem("Enter info about a new Graduate Student");
        op3 = new JMenuItem("Print out all student info");
        op4 = new JMenuItem("Print average of student averages, as well as total number of students");
        op5 = new JMenuItem("Read input file and Load data");
        op6 = new JMenuItem("Save File Data to output file");
        op7 = new JMenuItem("Lookup via a HashMap key");
        op8 = new JMenuItem("End program");
        menu.add(op1);
        menu.add(op2);
        menu.add(op3);
        menu.add(op4);
        menu.add(op5);
        menu.add(op6);
        menu.add(op7);
        menu.add(op8);

        // Adding styles to the Menu and Menu Items
        op1.setFont(new Font("Arial", Font.BOLD, 16));
        op2.setFont(new Font("Arial", Font.BOLD, 16));
        op3.setFont(new Font("Arial", Font.BOLD, 16));
        op4.setFont(new Font("Arial", Font.BOLD, 16));
        op5.setFont(new Font("Arial", Font.BOLD, 16));
        op6.setFont(new Font("Arial", Font.BOLD, 16));
        op7.setFont(new Font("Arial", Font.BOLD, 16));
        op8.setFont(new Font("Arial", Font.BOLD, 16));

        mb.add(menu);
        home.setJMenuBar(mb);

        Font f = new FontUIResource(mb.getFont().getFontName(), mb.getFont().getStyle(), 30);
        UIManager.put("Menu.font", f);
        SwingUtilities.updateComponentTreeUI(home);
        mb.setOpaque(true);
        mb.setBackground(new Color(0xc700ff));
        menu.setForeground(new Color(0xffffff));
        Border emptyBorder3 = new EmptyBorder(10, 10, 10, 10);
        mb.setBorder(emptyBorder3);

        // Initialising the frames
        OPFrame enterinfostud = new OPFrame();
        OPFrame enterinfogradstud = new OPFrame();
        OPFrame printinfo = new OPFrame();
        OPFrame printavg = new OPFrame();
        OPFrame inputfile = new OPFrame();
        OPFrame outputfile = new OPFrame();
        OPFrame hashmapsearch = new OPFrame();

        op1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                enterinfostud.getContentPane().removeAll();
                enterinfostud.repaint();
                enterinfostud.setTitle("Enter Student Info");
                enterinfostud.setJMenuBar(mb);

                // Creating Panels for Displaying Components
                JPanel actionpanel = new JPanel();
                actionpanel.setLayout(new GridLayout(1, 2, 16, 16));
                actionpanel.setBackground(new Color(0x222222));
                actionpanel.setBounds(40, 80, 200, 200);

                // Panel for input fields
                JPanel inputs = new JPanel();
                inputs.setLayout(new FlowLayout(FlowLayout.CENTER));
                inputs.setBackground(new Color(0x222222));
                Border inputborder = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputborder);

                JLabel buyhead = new JLabel("Enter New Student Info");
                buyhead.setFont(new Font("Verdana", Font.BOLD, 21));
                buyhead.setForeground(new Color(0xc700ff));
                Border inputhead = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputhead);
                inputs.add(buyhead);

                // Input for Program
                JLabel program = new JLabel("Program : ");
                program.setFont(new Font("Sans-Serif", Font.BOLD, 20));
                program.setForeground(new Color(0xffffff));
                inputs.add(program);
                JTextField cb = new JTextField(10);
                cb.setFont(new Font("Arial", Font.BOLD, 18));
                inputs.add(cb);

                // Input for Year
                JLabel year = new JLabel("Year : ");
                year.setFont(new Font("Sans-Serif", Font.BOLD, 20));
                year.setForeground(new Color(0xffffff));
                inputs.add(year);
                JTextField t1 = new JTextField(10);
                t1.setFont(new Font("Arial", Font.BOLD, 18));
                inputs.add(t1);

                // Input for Average Grade
                JLabel avggrade = new JLabel("Average Grade : ");
                avggrade.setFont(new Font("Sans-Serif", Font.BOLD, 20));
                avggrade.setForeground(new Color(0xffffff));
                inputs.add(avggrade);
                JTextField t2 = new JTextField(5);
                t2.setFont(new Font("Arial", Font.BOLD, 18));
                inputs.add(t2);

                // Input for Last Name
                JLabel lname = new JLabel("Last Name : ");
                lname.setFont(new Font("Sans-Serif", Font.BOLD, 20));
                lname.setForeground(new Color(0xffffff));
                inputs.add(lname);
                JTextField t3 = new JTextField(6);
                t3.setFont(new Font("Arial", Font.BOLD, 18));
                inputs.add(t3);

                // Panel for buttons
                JPanel btns = new JPanel();
                btns.setLayout(new FlowLayout(FlowLayout.CENTER));
                btns.setBackground(new Color(0x222222));
                Border btnborder = new EmptyBorder(60, 50, 50, 50);
                btns.setBorder(btnborder);

                JButton reset = new JButton("Reset");
                JButton buyinv = new JButton("Add");

                reset.setFocusable(false);
                buyinv.setFocusable(false);

                // Styling the Buttons
                reset.setBackground(new Color(0xc700ff));
                reset.setForeground(new Color(0xffffff));
                buyinv.setBackground(new Color(0xc700ff));
                buyinv.setForeground(new Color(0xffffff));
                reset.setFont(new Font("Arial", Font.BOLD, 30));
                buyinv.setFont(new Font("Arial", Font.BOLD, 30));
                reset.setPreferredSize(new Dimension(140, 50));
                btns.add(reset);
                buyinv.setPreferredSize(new Dimension(140, 50));
                btns.add(buyinv);

                actionpanel.add(inputs);
                actionpanel.add(btns);

                // Panel for displaying the results
                JPanel msgpanel = new JPanel();
                msgpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                msgpanel.setBackground(new Color(0xffffff));
                msgpanel.setBounds(40, 80, 200, 200);

                JLabel gainh = new JLabel("Messages : ");
                gainh.setFont(new Font("Sans-Serif", Font.BOLD, 20));
                gainh.setForeground(new Color(0xc700ff));
                Border gaint = new EmptyBorder(10, 10, 10, 10);
                gainh.setBorder(gaint);

                // Initialising the Display textarea
                JTextArea display = new JTextArea(5, 30);
                display.setFont(new Font("Arial", Font.BOLD, 20));
                display.setLineWrap(true);
                display.setWrapStyleWord(true);

                Border displayborder = new EmptyBorder(25, 25, 25, 25);
                display.setBorder(displayborder);
                JScrollPane scroll = new JScrollPane(display);
                scroll.setForeground(new Color(0xc700ff));
                scroll.setOpaque(false);
                scroll.setBorder(null);
                display.setEditable(false);

                msgpanel.add(gainh);
                msgpanel.add(scroll);
                buyinv.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String program = cb.getText().toLowerCase();
                            if (program.length() == 0) {
                                String out = "Program cannot be left blank.";
                                display.setText(out);
                                display.setEditable(false);
                            } else {
                                int year = Integer.parseInt(t1.getText());
                                String avg = t2.getText();
                                float avggrade;
                                if (avg.length() == 0) {
                                    avggrade = 0.0f;
                                } else {
                                    avggrade = Float.parseFloat(avg);
                                }
                                String lastname = t3.getText().toLowerCase();
                                if (lastname.length() == 0) {
                                    String out = "Last Name cannot be left blank.";
                                    display.setText(out);
                                    display.setEditable(false);
                                } else {
                                    try {
                                        Students studentrecord = new Students(program, year, avggrade, lastname);
                                        if (studentmanager.checkstudent(program, year, avggrade, lastname) == null) {
                                            studentmanager.addStudents(studentrecord);
                                            String out = "Data for the Student added successfully.";
                                            display.setText(out);
                                            display.setEditable(false);
                                        } else {
                                            String out = "Data for this student already exists.";
                                            display.setText(out);
                                            display.setEditable(false);
                                        }
                                    } catch (Exception e1) {
                                        String out = e1.getMessage();
                                        display.setText(out);
                                        display.setEditable(false);
                                    }
                                }
                            }

                        } catch (Exception error) {
                            String out = "Something Went Wrong, Please Try Again.\n\nPlease make sure that\n1)You have not left Last Name, Year, or Program fields Blank\n2)You have entered a Number for Average grade and not a string and it is between 0 and 100 (inclusive) only.\n3)You have entered an Integer Number for Year and not a string.";
                            display.setText(out);
                            display.setEditable(false);
                        }
                    }
                });
                reset.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cb.setText("");
                        t1.setText("");
                        t2.setText("");
                        t3.setText("");
                        display.setText("");
                    }
                });
                // Adding the Panels
                enterinfostud.add(actionpanel);
                enterinfostud.add(msgpanel);
                enterinfostud.setVisible(true);

                // Disposing the Panels
                home.dispose();
                enterinfogradstud.dispose();
                printinfo.dispose();
                printavg.dispose();
                inputfile.dispose();
                outputfile.dispose();
                hashmapsearch.dispose();
            }
        });

        op2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                enterinfogradstud.getContentPane().removeAll();
                enterinfogradstud.repaint();
                enterinfogradstud.setTitle("Enter Graduate Student Info");
                enterinfogradstud.setJMenuBar(mb);

                // Creating Panels for Displaying Components
                JPanel actionpanel = new JPanel();
                actionpanel.setLayout(new GridLayout(1, 2, 16, 16));
                actionpanel.setBackground(new Color(0x222222));
                actionpanel.setBounds(40, 80, 200, 200);

                // Panel for input fields
                JPanel inputs = new JPanel();
                inputs.setLayout(new FlowLayout(FlowLayout.CENTER));
                inputs.setBackground(new Color(0x222222));
                Border inputborder = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputborder);

                JLabel buyhead = new JLabel("Enter Graduate Student Info");
                buyhead.setFont(new Font("Verdana", Font.BOLD, 17));
                buyhead.setForeground(new Color(0xc700ff));
                Border inputhead = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputhead);
                inputs.add(buyhead);

                // Input for Program
                JLabel program = new JLabel("Program : ");
                program.setFont(new Font("Sans-Serif", Font.BOLD, 16));
                program.setForeground(new Color(0xffffff));
                inputs.add(program);
                JTextField cb = new JTextField(10);
                cb.setFont(new Font("Arial", Font.BOLD, 18));
                inputs.add(cb);

                // Input for Year
                JLabel year = new JLabel("Year : ");
                year.setFont(new Font("Sans-Serif", Font.BOLD, 16));
                year.setForeground(new Color(0xffffff));
                inputs.add(year);
                JTextField t1 = new JTextField(10);
                t1.setFont(new Font("Arial", Font.BOLD, 18));
                inputs.add(t1);

                // Input for Average Grade
                JLabel avggrade = new JLabel("Average Grade : ");
                avggrade.setFont(new Font("Sans-Serif", Font.BOLD, 16));
                avggrade.setForeground(new Color(0xffffff));
                inputs.add(avggrade);
                JTextField t2 = new JTextField(5);
                t2.setFont(new Font("Arial", Font.BOLD, 16));
                inputs.add(t2);

                // Input for Supervisor Name
                JLabel supname = new JLabel("Supervisor Name : ");
                supname.setFont(new Font("Sans-Serif", Font.BOLD, 16));
                supname.setForeground(new Color(0xffffff));
                inputs.add(supname);
                JTextField t3 = new JTextField(6);
                t3.setFont(new Font("Arial", Font.BOLD, 16));
                inputs.add(t3);

                // Input for isPHD
                JLabel isphd = new JLabel("is student a PHD? : ");
                isphd.setFont(new Font("Sans-Serif", Font.BOLD, 16));
                isphd.setForeground(new Color(0xffffff));
                inputs.add(isphd);
                String[] choices = { "Yes", "No" };
                final JComboBox<String> t4 = new JComboBox<String>(choices);
                t4.setFont(new Font("Arial", Font.BOLD, 16));
                inputs.add(t4);

                // Input for Undaergraduate School name
                JLabel ugname = new JLabel("Undergraduate School: ");
                ugname.setFont(new Font("Sans-Serif", Font.BOLD, 16));
                ugname.setForeground(new Color(0xffffff));
                inputs.add(ugname);
                JTextField t5 = new JTextField(5);
                t5.setFont(new Font("Arial", Font.BOLD, 16));
                inputs.add(t5);

                // Input for Last Name
                JLabel lname = new JLabel("Last Name : ");
                lname.setFont(new Font("Sans-Serif", Font.BOLD, 16));
                lname.setForeground(new Color(0xffffff));
                inputs.add(lname);
                JTextField t6 = new JTextField(6);
                t6.setFont(new Font("Arial", Font.BOLD, 16));
                inputs.add(t6);

                // Panel for buttons
                JPanel btns = new JPanel();
                btns.setLayout(new FlowLayout(FlowLayout.CENTER));
                btns.setBackground(new Color(0x222222));
                Border btnborder = new EmptyBorder(60, 50, 50, 50);
                btns.setBorder(btnborder);

                JButton reset = new JButton("Reset");
                JButton buyinv = new JButton("Add");

                reset.setFocusable(false);
                buyinv.setFocusable(false);

                // Styling the Buttons
                reset.setBackground(new Color(0xc700ff));
                reset.setForeground(new Color(0xffffff));
                buyinv.setBackground(new Color(0xc700ff));
                buyinv.setForeground(new Color(0xffffff));
                reset.setFont(new Font("Arial", Font.BOLD, 30));
                buyinv.setFont(new Font("Arial", Font.BOLD, 30));
                reset.setPreferredSize(new Dimension(140, 50));
                btns.add(reset);
                buyinv.setPreferredSize(new Dimension(140, 50));
                btns.add(buyinv);

                actionpanel.add(inputs);
                actionpanel.add(btns);

                // Panel for displaying the results
                JPanel msgpanel = new JPanel();
                msgpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                msgpanel.setBackground(new Color(0xffffff));
                msgpanel.setBounds(40, 80, 200, 200);

                JLabel gainh = new JLabel("Messages : ");
                gainh.setFont(new Font("Sans-Serif", Font.BOLD, 20));
                gainh.setForeground(new Color(0xc700ff));
                Border gaint = new EmptyBorder(10, 10, 10, 10);
                gainh.setBorder(gaint);

                // Initialising the Display textarea
                JTextArea display = new JTextArea(5, 30);
                display.setFont(new Font("Arial", Font.BOLD, 20));
                display.setLineWrap(true);
                display.setWrapStyleWord(true);

                Border displayborder = new EmptyBorder(25, 25, 25, 25);
                display.setBorder(displayborder);
                JScrollPane scroll = new JScrollPane(display);
                scroll.setForeground(new Color(0xc700ff));
                scroll.setOpaque(false);
                scroll.setBorder(null);
                display.setEditable(false);

                msgpanel.add(gainh);
                msgpanel.add(scroll);

                buyinv.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String program = cb.getText().toLowerCase();
                            if (program.length() == 0) {
                                String out = "Program cannot be left blank.";
                                display.setText(out);
                                display.setEditable(false);
                            } else {
                                int year = Integer.parseInt(t1.getText());
                                String avg = t2.getText();
                                float avggrade;
                                if (avg.length() == 0) {
                                    avggrade = 0.0f;
                                } else {
                                    avggrade = Float.parseFloat(avg);
                                }
                                String supervisor = t3.getText().toLowerCase();
                                if (supervisor.length() == 0) {
                                    String out = "Supervisor Name cannot be left blank.";
                                    display.setText(out);
                                    display.setEditable(false);
                                } else {

                                    int isPHD;
                                    String phd = t4.getItemAt(t4.getSelectedIndex());
                                    if (phd.equals("Yes")) {
                                        isPHD = 1;
                                    } else {
                                        isPHD = 0;
                                    }
                                    String ugscul = t5.getText().toLowerCase();
                                    String undergname;
                                    if (ugscul.length() == 0) {
                                        undergname = "Not_Mentioned";
                                    } else {
                                        undergname = ugscul;
                                    }
                                    String lastname = t6.getText().toLowerCase();
                                    if (lastname.length() == 0) {
                                        String out = "Last Name cannot be left blank.";
                                        display.setText(out);
                                        display.setEditable(false);
                                    } else {
                                        try {
                                            GraduateStudents studentrecord = new GraduateStudents(program, year,
                                                    avggrade, lastname, supervisor, isPHD, undergname);
                                            if (studentmanager.checkgradstudent(program, year, avggrade, lastname,
                                                    supervisor, isPHD, undergname) == null) {
                                                studentmanager.addGraduateStudents(studentrecord);
                                                String out = "Data for the Student added successfully.";
                                                display.setText(out);
                                                display.setEditable(false);
                                            } else {
                                                String out = "Data for this student already exists.";
                                                display.setText(out);
                                                display.setEditable(false);
                                            }
                                        } catch (Exception e1) {
                                            String out = e1.getMessage();
                                            display.setText(out);
                                            display.setEditable(false);
                                        }
                                    }
                                }

                            }

                        } catch (Exception error) {
                            String out = "Something Went Wrong, Please Try Again.\n\nPlease make sure that\n1)You have not left Last Name, Year, Program, or Supervisor Name fields Blank\n2)You have entered a Number for Average grade and not a string and it is between 0 and 100 (inclusive) only.\n3)You have entered an Integer Number for Year and not a string.";
                            display.setText(out);
                            display.setEditable(false);
                        }
                    }
                });

                reset.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cb.setText("");
                        t1.setText("");
                        t2.setText("");
                        t3.setText("");
                        display.setText("");
                    }
                });
                // Adding the Panels
                enterinfogradstud.add(actionpanel);
                enterinfogradstud.add(msgpanel);
                enterinfogradstud.setVisible(true);

                // Disposing the Panels
                home.dispose();
                enterinfostud.dispose();
                printinfo.dispose();
                printavg.dispose();
                inputfile.dispose();
                outputfile.dispose();
                hashmapsearch.dispose();
            }
        });

        op3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                printinfo.getContentPane().removeAll();
                printinfo.repaint();
                printinfo.setTitle("Print out all Student Info");
                printinfo.setJMenuBar(mb);

                // Creating Panels for Displaying Components
                JPanel actionpanel = new JPanel();
                actionpanel.setLayout(new GridLayout(1, 2, 16, 16));
                actionpanel.setBackground(new Color(0x222222));
                actionpanel.setBounds(40, 80, 200, 200);

                // Panel for input fields
                JPanel inputs = new JPanel();
                inputs.setLayout(new FlowLayout(FlowLayout.CENTER));
                inputs.setBackground(new Color(0x222222));
                Border inputborder = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputborder);

                JLabel buyhead = new JLabel("All Student Data");
                buyhead.setFont(new Font("Verdana", Font.BOLD, 26));
                buyhead.setForeground(new Color(0xc700ff));
                Border inputhead = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputhead);
                inputs.add(buyhead);

                JLabel buyhead2 = new JLabel("Press Print to Output data.");
                buyhead2.setFont(new Font("Verdana", Font.PLAIN, 20));
                buyhead2.setForeground(new Color(0xffffff));
                Border inputhead2 = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputhead2);
                inputs.add(buyhead2);

                // Panel for buttons
                JPanel btns = new JPanel();
                btns.setLayout(new FlowLayout(FlowLayout.CENTER));
                btns.setBackground(new Color(0x222222));
                Border btnborder = new EmptyBorder(60, 50, 50, 50);
                btns.setBorder(btnborder);

                JButton reset = new JButton("Reset");
                JButton buyinv = new JButton("Print");

                reset.setFocusable(false);
                buyinv.setFocusable(false);

                // Styling the Buttons
                reset.setBackground(new Color(0xc700ff));
                reset.setForeground(new Color(0xffffff));
                buyinv.setBackground(new Color(0xc700ff));
                buyinv.setForeground(new Color(0xffffff));
                reset.setFont(new Font("Arial", Font.BOLD, 30));
                buyinv.setFont(new Font("Arial", Font.BOLD, 30));
                reset.setPreferredSize(new Dimension(140, 50));
                btns.add(reset);
                buyinv.setPreferredSize(new Dimension(140, 50));
                btns.add(buyinv);

                actionpanel.add(inputs);
                actionpanel.add(btns);

                // Panel for displaying the results
                JPanel msgpanel = new JPanel();
                msgpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                msgpanel.setBackground(new Color(0xffffff));
                msgpanel.setBounds(40, 80, 200, 200);

                JLabel gainh = new JLabel("Messages : ");
                gainh.setFont(new Font("Sans-Serif", Font.BOLD, 20));
                gainh.setForeground(new Color(0xc700ff));
                Border gaint = new EmptyBorder(10, 10, 10, 10);
                gainh.setBorder(gaint);

                // Initialising the Display textarea
                JTextArea display = new JTextArea(5, 30);
                display.setFont(new Font("Arial", Font.BOLD, 20));
                display.setLineWrap(true);
                display.setWrapStyleWord(true);

                Border displayborder = new EmptyBorder(25, 25, 25, 25);
                display.setBorder(displayborder);
                JScrollPane scroll = new JScrollPane(display);
                scroll.setForeground(new Color(0xc700ff));
                scroll.setOpaque(false);
                scroll.setBorder(null);
                display.setEditable(false);

                msgpanel.add(gainh);
                msgpanel.add(scroll);

                buyinv.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            int counter = 1;
                            int gcounter = 1;
                            String out = "";
                            if (studentmanager.getStudents() != null || studentmanager.getGraduateStudents() != null) {
                                if (studentmanager.getStudents() != null) {
                                    for (Students studentval : studentmanager.getStudents()) {
                                        out += "Data for Student Number " + counter + "\n\n" + studentval.toString()
                                                + "\n";
                                        counter++;
                                    }
                                } else {
                                    out += "No Data found for Undergraduate Students\n";
                                }
                                if (studentmanager.getGraduateStudents() != null) {
                                    for (GraduateStudents gradstudval : studentmanager.getGraduateStudents()) {
                                        out += "Data for Graduate Student Number " + gcounter + "\n\n"
                                                + gradstudval.toString() + "\n";
                                        display.setText(out);
                                        display.setEditable(false);
                                        gcounter++;
                                    }
                                } else {
                                    out += "No Data found for Graduate Students\n";
                                    display.setText(out);
                                    display.setEditable(false);
                                }
                            } else {
                                out += "No Data found for Any Students. Please consider adding some data first.\n";
                                display.setText(out);
                                display.setEditable(false);
                            }
                        } catch (Exception error) {
                            String out = "Something Went Wrong, Please Try Again.";
                            display.setText(out);
                            display.setEditable(false);
                        }
                    }
                });

                reset.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        display.setText("");
                    }
                });

                // Adding the Panels
                printinfo.add(actionpanel);
                printinfo.add(msgpanel);
                printinfo.setVisible(true);

                home.dispose();
                enterinfostud.dispose();
                enterinfogradstud.dispose();
                printavg.dispose();
                inputfile.dispose();
                outputfile.dispose();
                hashmapsearch.dispose();
            }
        });

        op4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                printavg.getContentPane().removeAll();
                printavg.repaint();
                printavg.setTitle("Print out Average Grades and No. of Students");
                printavg.setJMenuBar(mb);

                // Creating Panels for Displaying Components
                JPanel actionpanel = new JPanel();
                actionpanel.setLayout(new GridLayout(1, 2, 16, 16));
                actionpanel.setBackground(new Color(0x222222));
                actionpanel.setBounds(40, 80, 200, 200);

                // Panel for input fields
                JPanel inputs = new JPanel();
                inputs.setLayout(new FlowLayout(FlowLayout.CENTER));
                inputs.setBackground(new Color(0x222222));
                Border inputborder = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputborder);

                JLabel buyhead = new JLabel("Average Grades & No. of Students");
                buyhead.setFont(new Font("Verdana", Font.BOLD, 14));
                buyhead.setForeground(new Color(0xc700ff));
                Border inputhead = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputhead);
                inputs.add(buyhead);

                JLabel buyhead2 = new JLabel("Press Print to Output the Data.");
                buyhead2.setFont(new Font("Verdana", Font.PLAIN, 16));
                buyhead2.setForeground(new Color(0xffffff));
                Border inputhead2 = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputhead2);
                inputs.add(buyhead2);

                // Panel for buttons
                JPanel btns = new JPanel();
                btns.setLayout(new FlowLayout(FlowLayout.CENTER));
                btns.setBackground(new Color(0x222222));
                Border btnborder = new EmptyBorder(60, 50, 50, 50);
                btns.setBorder(btnborder);

                JButton reset = new JButton("Reset");
                JButton buyinv = new JButton("Print");

                reset.setFocusable(false);
                buyinv.setFocusable(false);

                // Styling the Buttons
                reset.setBackground(new Color(0xc700ff));
                reset.setForeground(new Color(0xffffff));
                buyinv.setBackground(new Color(0xc700ff));
                buyinv.setForeground(new Color(0xffffff));
                reset.setFont(new Font("Arial", Font.BOLD, 30));
                buyinv.setFont(new Font("Arial", Font.BOLD, 30));
                reset.setPreferredSize(new Dimension(140, 50));
                btns.add(reset);
                buyinv.setPreferredSize(new Dimension(140, 50));
                btns.add(buyinv);

                actionpanel.add(inputs);
                actionpanel.add(btns);

                // Panel for displaying the results
                JPanel msgpanel = new JPanel();
                msgpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                msgpanel.setBackground(new Color(0xffffff));
                msgpanel.setBounds(40, 80, 200, 200);

                JLabel gainh = new JLabel("Messages : ");
                gainh.setFont(new Font("Sans-Serif", Font.BOLD, 20));
                gainh.setForeground(new Color(0xc700ff));
                Border gaint = new EmptyBorder(10, 10, 10, 10);
                gainh.setBorder(gaint);

                // Initialising the Display textarea
                JTextArea display = new JTextArea(5, 30);
                display.setFont(new Font("Arial", Font.BOLD, 20));
                display.setLineWrap(true);
                display.setWrapStyleWord(true);

                Border displayborder = new EmptyBorder(25, 25, 25, 25);
                display.setBorder(displayborder);
                JScrollPane scroll = new JScrollPane(display);
                scroll.setForeground(new Color(0xc700ff));
                scroll.setOpaque(false);
                scroll.setBorder(null);
                display.setEditable(false);

                msgpanel.add(gainh);
                msgpanel.add(scroll);

                buyinv.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String out = "";
                        try {
                            float avg = 0.0f;
                            float avgSum = 0.0f;
                            int totalStud = 0;
                            if (studentmanager.getStudents() != null) {
                                for (Students studval : studentmanager.getStudents()) {
                                    avgSum += studval.getAverage();
                                    totalStud++;
                                }
                            }
                            if (studentmanager.getGraduateStudents() != null) {
                                for (GraduateStudents gradstudval : studentmanager.getGraduateStudents()) {
                                    avgSum += gradstudval.getAverage();
                                    totalStud++;
                                }
                            }
                            avg = avgSum / totalStud;
                            if (totalStud > 0) {
                                out += "Average of the average grades for all students is : " + avg + "\n\n";
                                out += "Total number of students is : " + totalStud + "\n";
                            } else {
                                out += "No Student data found. Please consider adding some data first";
                            }
                            display.setText(out);
                            display.setEditable(false);
                        } catch (Exception error) {
                            out = "Something Went Wrong, Please Try Again.";
                            display.setText(out);
                            display.setEditable(false);
                        }
                    }
                });

                reset.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        display.setText("");
                    }
                });

                // Adding the Panels
                printavg.add(actionpanel);
                printavg.add(msgpanel);
                printavg.setVisible(true);

                home.dispose();
                enterinfostud.dispose();
                enterinfogradstud.dispose();
                printinfo.dispose();
                inputfile.dispose();
                outputfile.dispose();
                hashmapsearch.dispose();
            }
        });

        op5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                inputfile.getContentPane().removeAll();
                inputfile.repaint();
                inputfile.setTitle("Load Data from Input File");
                inputfile.setJMenuBar(mb);

                // Creating Panels for Displaying Components
                JPanel actionpanel = new JPanel();
                actionpanel.setLayout(new GridLayout(1, 2, 16, 16));
                actionpanel.setBackground(new Color(0x222222));
                actionpanel.setBounds(40, 80, 200, 200);

                // Panel for input fields
                JPanel inputs = new JPanel();
                inputs.setLayout(new FlowLayout(FlowLayout.CENTER));
                inputs.setBackground(new Color(0x222222));
                Border inputborder = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputborder);

                JLabel buyhead = new JLabel("Load Data from a File");
                buyhead.setFont(new Font("Verdana", Font.BOLD, 21));
                buyhead.setForeground(new Color(0xc700ff));
                Border inputhead = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputhead);
                inputs.add(buyhead);

                // Input for filename
                JLabel fname = new JLabel("File Name : ");
                fname.setFont(new Font("Sans-Serif", Font.BOLD, 16));
                fname.setForeground(new Color(0xffffff));
                inputs.add(fname);
                JTextField cb = new JTextField(10);
                cb.setFont(new Font("Arial", Font.BOLD, 18));
                inputs.add(cb);

                // Panel for buttons
                JPanel btns = new JPanel();
                btns.setLayout(new FlowLayout(FlowLayout.CENTER));
                btns.setBackground(new Color(0x222222));
                Border btnborder = new EmptyBorder(60, 50, 50, 50);
                btns.setBorder(btnborder);

                JButton reset = new JButton("Reset");
                JButton buyinv = new JButton("Load");

                reset.setFocusable(false);
                buyinv.setFocusable(false);

                // Styling the Buttons
                reset.setBackground(new Color(0xc700ff));
                reset.setForeground(new Color(0xffffff));
                buyinv.setBackground(new Color(0xc700ff));
                buyinv.setForeground(new Color(0xffffff));
                reset.setFont(new Font("Arial", Font.BOLD, 30));
                buyinv.setFont(new Font("Arial", Font.BOLD, 30));
                reset.setPreferredSize(new Dimension(140, 50));
                btns.add(reset);
                buyinv.setPreferredSize(new Dimension(140, 50));
                btns.add(buyinv);

                actionpanel.add(inputs);
                actionpanel.add(btns);

                // Panel for displaying the results
                JPanel msgpanel = new JPanel();
                msgpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                msgpanel.setBackground(new Color(0xffffff));
                msgpanel.setBounds(40, 80, 200, 200);

                JLabel gainh = new JLabel("Messages : ");
                gainh.setFont(new Font("Sans-Serif", Font.BOLD, 20));
                gainh.setForeground(new Color(0xc700ff));
                Border gaint = new EmptyBorder(10, 10, 10, 10);
                gainh.setBorder(gaint);

                // Initialising the Display textarea
                JTextArea display = new JTextArea(5, 30);
                display.setFont(new Font("Arial", Font.BOLD, 20));
                display.setLineWrap(true);
                display.setWrapStyleWord(true);

                Border displayborder = new EmptyBorder(25, 25, 25, 25);
                display.setBorder(displayborder);
                JScrollPane scroll = new JScrollPane(display);
                scroll.setForeground(new Color(0xc700ff));
                scroll.setOpaque(false);
                scroll.setBorder(null);
                display.setEditable(false);

                msgpanel.add(gainh);
                msgpanel.add(scroll);

                buyinv.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String out = "";
                        try {
                            String fname = cb.getText();
                            out += "Loading Data from " + fname + "...\n";
                            try {
                                BufferedReader input = new BufferedReader(new FileReader(fname));
                                while ((line = input.readLine()) != null) {
                                    studentfile.add(line);
                                }
                                for (int i = 0; i < studentfile.size(); i++) {
                                    String[] items = studentfile.get(i).split("-");

                                    if (items.length == 4) {
                                        try {
                                            String uprogram = items[0];
                                            int uyear = Integer.parseInt(items[1]);
                                            float uavggrade = Float.parseFloat(items[2]);
                                            String ulastname = items[3];
                                            try {
                                                Students studentrecord = new Students(uprogram, uyear, uavggrade,
                                                        ulastname);
                                                if (studentmanager.checkstudent(uprogram, uyear, uavggrade,
                                                        ulastname) == null) {
                                                    studentmanager.addStudents(studentrecord);
                                                }
                                            } catch (Exception e2) {
                                                System.out.println(e2.getMessage());
                                            }
                                        } catch (Exception e1) {
                                            out += "\nSomething went Wrong. Please Make sure that,\n(1) You have entered a Whole Number for year\n(2) Average Grade should be between 0 and 100 (inclusive).";
                                        }
                                    } else if (items.length == 7) {
                                        try {
                                            String gprogram = items[0];
                                            int gyear = Integer.parseInt(items[1]);
                                            float gavggrade = Float.parseFloat(items[2]);
                                            String glastname = items[3];
                                            String gsuper = items[4];
                                            int gisphd = Integer.parseInt(items[5]);
                                            String gugname = items[6];
                                            try {
                                                GraduateStudents studentrecord = new GraduateStudents(gprogram, gyear,
                                                        gavggrade, glastname, gsuper, gisphd, gugname);
                                                if (studentmanager.checkgradstudent(gprogram, gyear, gavggrade,
                                                        glastname, gsuper, gisphd, gugname) == null) {
                                                    studentmanager.addGraduateStudents(studentrecord);
                                                }
                                            } catch (Exception e1) {
                                                System.out.println(e1.getMessage());
                                            }
                                        } catch (Exception e1) {
                                            out += "\nSomething went Wrong. Please Make sure that,\n(1) You have entered a Whole Number for year\n(2) Average Grade should be between 0 and 100 (inclusive). (3) The value of isPHD field is a Number, either 1 or 0 only.";
                                        }
                                    }
                                }
                                input.close();
                                out += "\nData loaded from file " + fname + " Successfully.\n";
                                display.setText(out);
                                display.setEditable(false);
                            } catch (IOException e1) {
                                out += "\nSomething Went Wrong, Could Not open the file " + fname
                                        + ", Please recheck the Name of the file you entered and make sure it exists.\n";
                                display.setText(out);
                                display.setEditable(false);
                            }
                        } catch (Exception error) {
                            out += "Something Went Wrong, Please Try Again. Make sure the File exists and is not encrypted or hidden.";
                            display.setText(out);
                            display.setEditable(false);
                        }
                    }
                });

                reset.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cb.setText("");
                        display.setText("");
                    }
                });
                // Adding the Panels
                inputfile.add(actionpanel);
                inputfile.add(msgpanel);
                inputfile.setVisible(true);

                // Disposing the Panels
                home.dispose();
                enterinfostud.dispose();
                printinfo.dispose();
                printavg.dispose();
                enterinfogradstud.dispose();
                outputfile.dispose();
                hashmapsearch.dispose();
            }
        });

        op6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                printinfo.getContentPane().removeAll();
                printinfo.repaint();
                printinfo.setTitle("Save Data to Output File");
                printinfo.setJMenuBar(mb);

                // Creating Panels for Displaying Components
                JPanel actionpanel = new JPanel();
                actionpanel.setLayout(new GridLayout(1, 2, 16, 16));
                actionpanel.setBackground(new Color(0x222222));
                actionpanel.setBounds(40, 80, 200, 200);

                // Panel for input fields
                JPanel inputs = new JPanel();
                inputs.setLayout(new FlowLayout(FlowLayout.CENTER));
                inputs.setBackground(new Color(0x222222));
                Border inputborder = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputborder);

                JLabel buyhead = new JLabel("Save Data to File");
                buyhead.setFont(new Font("Verdana", Font.BOLD, 26));
                buyhead.setForeground(new Color(0xc700ff));
                Border inputhead = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputhead);
                inputs.add(buyhead);

                JLabel buyhead2 = new JLabel("Press Save to save data.");
                buyhead2.setFont(new Font("Verdana", Font.PLAIN, 20));
                buyhead2.setForeground(new Color(0xffffff));
                Border inputhead2 = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputhead2);
                inputs.add(buyhead2);

                // Panel for buttons
                JPanel btns = new JPanel();
                btns.setLayout(new FlowLayout(FlowLayout.CENTER));
                btns.setBackground(new Color(0x222222));
                Border btnborder = new EmptyBorder(60, 50, 50, 50);
                btns.setBorder(btnborder);

                JButton reset = new JButton("Reset");
                JButton buyinv = new JButton("Save");

                reset.setFocusable(false);
                buyinv.setFocusable(false);

                // Styling the Buttons
                reset.setBackground(new Color(0xc700ff));
                reset.setForeground(new Color(0xffffff));
                buyinv.setBackground(new Color(0xc700ff));
                buyinv.setForeground(new Color(0xffffff));
                reset.setFont(new Font("Arial", Font.BOLD, 30));
                buyinv.setFont(new Font("Arial", Font.BOLD, 30));
                reset.setPreferredSize(new Dimension(140, 50));
                btns.add(reset);
                buyinv.setPreferredSize(new Dimension(140, 50));
                btns.add(buyinv);

                actionpanel.add(inputs);
                actionpanel.add(btns);

                // Panel for displaying the results
                JPanel msgpanel = new JPanel();
                msgpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                msgpanel.setBackground(new Color(0xffffff));
                msgpanel.setBounds(40, 80, 200, 200);

                JLabel gainh = new JLabel("Messages : ");
                gainh.setFont(new Font("Sans-Serif", Font.BOLD, 20));
                gainh.setForeground(new Color(0xc700ff));
                Border gaint = new EmptyBorder(10, 10, 10, 10);
                gainh.setBorder(gaint);

                // Initialising the Display textarea
                JTextArea display = new JTextArea(5, 30);
                display.setFont(new Font("Arial", Font.BOLD, 20));
                display.setLineWrap(true);
                display.setWrapStyleWord(true);

                Border displayborder = new EmptyBorder(25, 25, 25, 25);
                display.setBorder(displayborder);
                JScrollPane scroll = new JScrollPane(display);
                scroll.setForeground(new Color(0xc700ff));
                scroll.setOpaque(false);
                scroll.setBorder(null);
                display.setEditable(false);

                msgpanel.add(gainh);
                msgpanel.add(scroll);

                buyinv.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String out = "";
                        try {
                            out += "\nSaving into students.txt Output File...\n";
                            File studentsrecord = new File("students.txt");
                            try {
                                FileWriter fw = new FileWriter(studentsrecord);
                                Writer output = new BufferedWriter(fw);
                                for (Students studrec : studentmanager.getStudents()) {
                                    output.write(studrec.toFileString() + "\n");
                                }
                                for (GraduateStudents gradstudrec : studentmanager.getGraduateStudents()) {
                                    output.write(gradstudrec.toFileString() + "\n");
                                }
                                output.close();
                                out += "File Saved Successfully!\n";
                                display.setText(out);
                                display.setEditable(false);
                            } catch (IOException e1) {
                                out += "\nSomething Went Wrong, Cannot Save to the File.\n";
                            }
                        } catch (Exception error) {
                            out += "Something Went Wrong, Please Try Again.";
                            display.setText(out);
                            display.setEditable(false);
                        }
                    }
                });

                reset.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        display.setText("");
                    }
                });

                // Adding the Panels
                printinfo.add(actionpanel);
                printinfo.add(msgpanel);
                printinfo.setVisible(true);

                home.dispose();
                enterinfostud.dispose();
                enterinfogradstud.dispose();
                printavg.dispose();
                inputfile.dispose();
                outputfile.dispose();
                hashmapsearch.dispose();
            }
        });

        op7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                hashmapsearch.getContentPane().removeAll();
                hashmapsearch.repaint();
                hashmapsearch.setTitle("Search using Hashmap");
                hashmapsearch.setJMenuBar(mb);

                // Creating Panels for Displaying Components
                JPanel actionpanel = new JPanel();
                actionpanel.setLayout(new GridLayout(1, 2, 16, 16));
                actionpanel.setBackground(new Color(0x222222));
                actionpanel.setBounds(40, 80, 200, 200);

                // Panel for input fields
                JPanel inputs = new JPanel();
                inputs.setLayout(new FlowLayout(FlowLayout.CENTER));
                inputs.setBackground(new Color(0x222222));
                Border inputborder = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputborder);

                JLabel buyhead = new JLabel("Search using Hashmap");
                buyhead.setFont(new Font("Verdana", Font.BOLD, 21));
                buyhead.setForeground(new Color(0xc700ff));
                Border inputhead = new EmptyBorder(10, 10, 10, 10);
                inputs.setBorder(inputhead);
                inputs.add(buyhead);

                // Input for filename
                JLabel fname = new JLabel("Search keyword : ");
                fname.setFont(new Font("Sans-Serif", Font.BOLD, 16));
                fname.setForeground(new Color(0xffffff));
                inputs.add(fname);
                JTextField cb = new JTextField(10);
                cb.setFont(new Font("Arial", Font.BOLD, 18));
                inputs.add(cb);

                // Panel for buttons
                JPanel btns = new JPanel();
                btns.setLayout(new FlowLayout(FlowLayout.CENTER));
                btns.setBackground(new Color(0x222222));
                Border btnborder = new EmptyBorder(60, 50, 50, 50);
                btns.setBorder(btnborder);

                JButton reset = new JButton("Reset");
                JButton buyinv = new JButton("Search");

                reset.setFocusable(false);
                buyinv.setFocusable(false);

                // Styling the Buttons
                reset.setBackground(new Color(0xc700ff));
                reset.setForeground(new Color(0xffffff));
                buyinv.setBackground(new Color(0xc700ff));
                buyinv.setForeground(new Color(0xffffff));
                reset.setFont(new Font("Arial", Font.BOLD, 30));
                buyinv.setFont(new Font("Arial", Font.BOLD, 30));
                reset.setPreferredSize(new Dimension(140, 50));
                btns.add(reset);
                buyinv.setPreferredSize(new Dimension(140, 50));
                btns.add(buyinv);

                actionpanel.add(inputs);
                actionpanel.add(btns);

                // Panel for displaying the results
                JPanel msgpanel = new JPanel();
                msgpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                msgpanel.setBackground(new Color(0xffffff));
                msgpanel.setBounds(40, 80, 200, 200);

                JLabel gainh = new JLabel("Search Results : ");
                gainh.setFont(new Font("Sans-Serif", Font.BOLD, 20));
                gainh.setForeground(new Color(0xc700ff));
                Border gaint = new EmptyBorder(10, 10, 10, 10);
                gainh.setBorder(gaint);

                // Initialising the Display textarea
                JTextArea display = new JTextArea(5, 30);
                display.setFont(new Font("Arial", Font.BOLD, 20));
                display.setLineWrap(true);
                display.setWrapStyleWord(true);

                Border displayborder = new EmptyBorder(25, 25, 25, 25);
                display.setBorder(displayborder);
                JScrollPane scroll = new JScrollPane(display);
                scroll.setForeground(new Color(0xc700ff));
                scroll.setOpaque(false);
                scroll.setBorder(null);
                display.setEditable(false);

                msgpanel.add(gainh);
                msgpanel.add(scroll);

                buyinv.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String out = "";
                        try {
                            ArrayList<Integer> value = new ArrayList<Integer>();
                            if (studentmanager.getStudents() != null) {
                                for (Students studentiter : studentmanager.getStudents()) {
                                    hashid++;
                                    studentiter.setHashid(hashid);
                                    String filestr = studentiter.toFileString();
                                    String[] tokens = filestr.split("-");
                                    for (int i = 0; i < tokens.length; i++) {
                                        for (int j = 0; j < studentmanager.getStudents().size(); j++) {
                                            index.put(tokens[i].toLowerCase(), String.valueOf(hashid));
                                            value.clear();
                                        }
                                    }
                                }
                            }
                            if (studentmanager.getGraduateStudents() != null) {
                                for (GraduateStudents gradstudentiter : studentmanager.getGraduateStudents()) {
                                    hashid++;
                                    gradstudentiter.setHashid(hashid);
                                    String filestr = gradstudentiter.toFileString();
                                    String[] tokens = filestr.split("-");
                                    for (int i = 0; i < tokens.length; i++) {
                                        for (int j = 0; j < studentmanager.getGraduateStudents().size(); j++) {
                                            index.put(tokens[i].toLowerCase(), String.valueOf(hashid));
                                            value.clear();
                                        }
                                    }
                                }
                            }

                            String keyw = cb.getText().toLowerCase();
                            String[] keyar = keyw.split(" ");
                            ArrayList<Integer> idlist = new ArrayList<>();
                            for (int k = 0; k < keyar.length; k++) {
                                String word = keyar[k];
                                String listval = index.get(word);
                                if (!(listval == null)) {
                                    int id = Integer.parseInt(listval);
                                    idlist.add(id);
                                }
                            }

                            if (idlist.size() <= 0) {
                                out += "\nNo Results found for the keyword " + keyw + "\n";
                            } else {
                                out += "\nYour Search results : ";
                                for (int i = 0; i < idlist.size(); i++) {
                                    int pt = idlist.get(i);
                                    for (int j = 0; j < studentmanager.getStudents().size(); j++) {
                                        if (pt == studentmanager.getStudents().get(j).getHashid()) {
                                            out += "\n" + studentmanager.getStudents().get(j).toString();
                                        }
                                    }
                                }
                                for (int i = 0; i < idlist.size(); i++) {
                                    int pt = idlist.get(i);
                                    for (int j = 0; j < studentmanager.getGraduateStudents().size(); j++) {
                                        if (pt == studentmanager.getGraduateStudents().get(j).getHashid()) {
                                            out += "\n" + studentmanager.getGraduateStudents().get(j).toString();
                                        }
                                    }
                                }
                            }
                            display.setText(out);
                            display.setEditable(false);

                        } catch (Exception error) {
                            out += "Something Went Wrong, Please Try Again. Make sure the File exists and is not encrypted or hidden.";
                            display.setText(out);
                            display.setEditable(false);
                        }
                    }
                });

                reset.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cb.setText("");
                        display.setText("");
                    }
                });
                // Adding the Panels
                hashmapsearch.add(actionpanel);
                hashmapsearch.add(msgpanel);
                hashmapsearch.setVisible(true);

                // Disposing the Panels
                home.dispose();
                enterinfostud.dispose();
                printinfo.dispose();
                printavg.dispose();
                enterinfogradstud.dispose();
                inputfile.dispose();
                outputfile.dispose();
            }
        });

        op8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });

        // Creating the Intro Label
        JLabel intro = new JLabel("Student Class Management System");
        intro.setFont(new Font("Arial", Font.BOLD, 30));
        intro.setForeground(new Color(0xc700ff));
        Border emptyBorder = new EmptyBorder(120, 10, 10, 10);
        intro.setBorder(emptyBorder);

        // Creating the intro description
        JTextArea description = new JTextArea(5, 36);
        description.setText(
                "Choose a command by clicking an Option from the Commands menu above to Enter info about a new Student, Enter info about a new GraduateStudent, Print out all student info, Print average of student averages, as well as total number of students, Read input file, File Data output, Lookup via a HashMap key or quit the program.");
        description.setFont(new Font("Verdana", Font.PLAIN, 16));
        description.setForeground(new Color(0xffffff));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setOpaque(false);
        description.setEditable(false);
        Border emptyBorder2 = new EmptyBorder(30, 10, 10, 10);
        description.setBorder(emptyBorder2);

        // Adding the Elements to the Home Frame
        home.add(intro);
        home.add(description);
        home.setVisible(true);
    }
}